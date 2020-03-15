package com.bankwithmint.developertest.binlist;

import org.omg.CORBA.PRIVATE_MEMBER;

public class CardLookupApiResponse {
    private String scheme;
    private String type;
    private CardBank bank;
    private String cardNumber;

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CardBank getBank() {
        return bank;
    }

    public void setBank(CardBank bank) {
        this.bank = bank;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
