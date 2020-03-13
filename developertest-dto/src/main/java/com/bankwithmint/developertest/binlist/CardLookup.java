package com.bankwithmint.developertest.binlist;

public class CardLookup {
    private String scheme;
    private String type;
    private CardBank bank;

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
}
