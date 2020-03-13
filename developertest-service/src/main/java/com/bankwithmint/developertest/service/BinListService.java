package com.bankwithmint.developertest.service;

import com.bankwithmint.developertest.binlist.CardLookup;

public interface BinListService {
    CardLookup verifyCard(String cardNumber);
}
