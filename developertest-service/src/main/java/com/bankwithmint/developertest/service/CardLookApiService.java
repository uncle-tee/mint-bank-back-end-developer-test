package com.bankwithmint.developertest.service;

import com.bankwithmint.developertest.CardLookupApiResponse;

public interface CardLookApiService {
    CardLookupApiResponse verifyCard(String cardNumber);
}
