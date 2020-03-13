package com.bankwithmint.developertest.serviceimpl;

import com.bankwithmint.developertest.binlist.CardLookup;
import com.bankwithmint.developertest.dao.CardLookUpRepository;
import com.bankwithmint.developertest.domain.CardLookUp;
import com.bankwithmint.developertest.service.CardLookApiService;
import com.bankwithmint.developertest.service.CardLookupService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.Optional;

@Named
public class CardLookUpServiceImpl implements CardLookupService {

    final CardLookApiService cardLookApiService;

    final CardLookUpRepository cardLookUpRepository;

    public CardLookUpServiceImpl(CardLookApiService cardLookApiService, CardLookUpRepository cardLookUpRepository) {
        this.cardLookApiService = cardLookApiService;
        this.cardLookUpRepository = cardLookUpRepository;
    }

    @Transactional
    @Override

    public Optional<com.bankwithmint.developertest.binlist.CardLookup> doCardLookUp(String cardNumber) {
        try {
            CardLookup cardLookup = cardLookApiService.verifyCard(cardNumber);
            CardLookUp persistedCardLook = cardLookUpRepository.findByNumber(cardNumber).orElseGet(() -> {
                CardLookUp cardLookUp = new CardLookUp();
                cardLookUp.setCount(0L);
                cardLookUp.setNumber(cardNumber);
                return cardLookUp;
            });
            Long increaseCount = persistedCardLook.getCount() + 1L;
            persistedCardLook.setCount(increaseCount);
            cardLookUpRepository.save(persistedCardLook);
            return Optional.of(cardLookup);

        } catch (Exception ex) {
            throw ex;
        }

    }
}
