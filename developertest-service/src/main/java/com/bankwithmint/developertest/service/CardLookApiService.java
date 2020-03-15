package com.bankwithmint.developertest.service;

import com.bankwithmint.developertest.binlist.CardLookupApiResponse;

public interface CardLookApiService {
    CardLookupApiResponse verifyCard(String cardNumber);
}
