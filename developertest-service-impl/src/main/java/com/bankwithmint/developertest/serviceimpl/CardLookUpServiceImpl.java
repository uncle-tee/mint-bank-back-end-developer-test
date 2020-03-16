package com.bankwithmint.developertest.serviceimpl;

import com.bankwithmint.developertest.CardLookupApiResponse;
import com.bankwithmint.developertest.dao.CardLookUpRepository;
import com.bankwithmint.developertest.domain.CardLookUp;
import com.bankwithmint.developertest.service.CardLookApiService;
import com.bankwithmint.developertest.service.CardLookupService;
import com.bankwithmint.developertest.service.Producer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.inject.Named;
import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Named
public class CardLookUpServiceImpl implements CardLookupService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());
    final CardLookApiService cardLookApiService;

    final CardLookUpRepository cardLookUpRepository;

    @Value(value = "${data.stale.time}")
    private String staleTime;

    @Value(value = "${binylist.card.verification}")
    public String binListCardListVerification;


    final
    Producer producer;

    final
    Gson gson;

    public CardLookUpServiceImpl(CardLookApiService cardLookApiService, CardLookUpRepository cardLookUpRepository, Gson gson, Producer producer) {
        this.cardLookApiService = cardLookApiService;
        this.cardLookUpRepository = cardLookUpRepository;
        this.gson = gson;
        this.producer = producer;
    }


    @Override
    public Optional<CardLookupApiResponse> doCardLookUp(String cardNumber) {
        CardLookupApiResponse cardLookupApiResponse = cardLookUpRepository.findByNumber(cardNumber)
                .map(cardLookUp -> {
                    long timeDiff = cardLookUp.getDateCreated().getTime() - Date.from(Instant.now()).getTime();
                    long diffInHours = timeDiff / (60 * 1000) % 60;
                    long dataStaleTime = staleTime != null ? Long.parseLong(staleTime) : 6L;
                    if (diffInHours > dataStaleTime) {
                        return null;
                    }
                    long count = cardLookUp.getCount() + 1L;
                    cardLookUp.setCount(count);
                    cardLookUpRepository.save(cardLookUp);
                    return gson.<CardLookupApiResponse>fromJson(cardLookUp.getDump(), new TypeToken<CardLookupApiResponse>() {
                    }.getType());
                }).orElseGet(() -> {
                    try {
                        CardLookupApiResponse cardLookupApiResponseResponse = cardLookApiService.verifyCard(cardNumber);
                        cardLookupApiResponseResponse.setCardNumber(cardNumber);
                        producer.publish(binListCardListVerification, gson.toJson(cardLookupApiResponseResponse));
                        return cardLookupApiResponseResponse;
                    } catch (Exception ex) {
                        throw ex;
                    }
                });

        return Optional.of(cardLookupApiResponse);
    }

    @Transactional
    @Override
    public void persistCardLookUp(CardLookupApiResponse cardLookupApiResponseResponse) {
        CardLookUp persistedCardLook = cardLookUpRepository.findByNumber(cardLookupApiResponseResponse.getCardNumber()).orElseGet(() -> {
            CardLookUp cardLookUp = new CardLookUp();
            cardLookUp.setCount(0L);
            cardLookUp.setDateCreated(Date.from(Instant.now()));
            cardLookUp.setDateUpdated(Date.from(Instant.now()));
            String s = gson.toJson(cardLookupApiResponseResponse);
            cardLookUp.setDump(s);
            cardLookUp.setNumber(cardLookupApiResponseResponse.getCardNumber());
            return cardLookUp;
        });
        Long increaseCount = persistedCardLook.getCount() + 1L;
        persistedCardLook.setCount(increaseCount);
        persistedCardLook.setDateUpdated(Date.from(Instant.now()));
        cardLookUpRepository.save(persistedCardLook);
    }
}
