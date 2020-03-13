package com.bankwithmint.developertest.service;

import com.bankwithmint.developertest.binlist.CardLookup;

public interface CardLookApiService {
    CardLookup verifyCard(String cardNumber);
}
