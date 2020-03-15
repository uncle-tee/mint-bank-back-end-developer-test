package com.bankwithmint.developertest.service;

import com.bankwithmint.developertest.CardLookupApiResponse;


import java.util.Optional;

public interface CardLookupService {
    Optional<CardLookupApiResponse> doCardLookUp(String cardNumber);

    void persistCardLookUp(CardLookupApiResponse cardLookupApiResponseResponse);
}
