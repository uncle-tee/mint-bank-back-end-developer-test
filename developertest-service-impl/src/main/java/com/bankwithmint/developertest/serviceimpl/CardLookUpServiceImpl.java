package com.bankwithmint.developertest.serviceimpl;

import com.bankwithmint.developertest.binlist.CardLookup;
import com.bankwithmint.developertest.dao.CardLookUpRepository;
import com.bankwithmint.developertest.domain.CardLookUp;
import com.bankwithmint.developertest.service.CardLookApiService;
import com.bankwithmint.developertest.service.CardLookupService;
import com.bankwithmint.developertest.service.Producer;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Named
public class CardLookUpServiceImpl implements CardLookupService {

    final CardLookApiService cardLookApiService;

    final CardLookUpRepository cardLookUpRepository;

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
    public Optional<com.bankwithmint.developertest.binlist.CardLookup> doCardLookUp(String cardNumber) {
        try {
            CardLookup cardLookupResponse = cardLookApiService.verifyCard(cardNumber);
            cardLookupResponse.setCardNumber(cardNumber);
            producer.publish("com.ng.vela.even.card_verified", gson.toJson(cardLookupResponse));
            return Optional.of(cardLookupResponse);
        } catch (Exception ex) {
            throw ex;
        }

    }

    @Transactional
    @Override
    public void persistCardLookUp(CardLookup cardLookupResponse) {
        CardLookUp persistedCardLook = cardLookUpRepository.findByNumber(cardLookupResponse.getCardNumber()).orElseGet(() -> {
            CardLookUp cardLookUp = new CardLookUp();
            cardLookUp.setCount(0L);
            cardLookUp.setDateCreated(Date.from(Instant.now()));
            cardLookUp.setDateUpdated(Date.from(Instant.now()));
            String s = gson.toJson(cardLookupResponse);
            byte[] b = s.getBytes(StandardCharsets.UTF_8);
            cardLookUp.setDump(b);
            cardLookUp.setNumber(cardLookupResponse.getCardNumber());
            return cardLookUp;
        });
        Long increaseCount = persistedCardLook.getCount() + 1L;
        persistedCardLook.setCount(increaseCount);
        persistedCardLook.setDateUpdated(Date.from(Instant.now()));
        cardLookUpRepository.save(persistedCardLook);
    }
}
