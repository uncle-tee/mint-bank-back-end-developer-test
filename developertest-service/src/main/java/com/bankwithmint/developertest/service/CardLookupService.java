package com.bankwithmint.developertest.service;

import com.bankwithmint.developertest.binlist.CardLookup;


import java.util.Optional;

public interface CardLookupService {
    Optional<CardLookup> doCardLookUp(String cardNumber);

    void persistCardLookUp(CardLookup cardLookupResponse);
}
