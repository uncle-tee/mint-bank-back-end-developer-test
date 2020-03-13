package com.bankwithmint.developertest.controller;

import com.bankwithmint.developertest.binlist.CardLookUpResponse;
import com.bankwithmint.developertest.binlist.CardLookup;
import com.bankwithmint.developertest.response.Response;
import com.bankwithmint.developertest.service.BinListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController()
public class CardLookUpController {

    @Inject
    BinListService binListService;

    @GetMapping("/card-scheme/verify/{cardNumber}")
    public ResponseEntity<?> doCardLookUp(@PathVariable String cardNumber) {
        CardLookup cardLookup = binListService.verifyCard(cardNumber);
        CardLookUpResponse cardLookUpResponse = new CardLookUpResponse();
        cardLookUpResponse.bank = cardLookup.getBank().getName();
        cardLookUpResponse.scheme = cardLookup.getScheme();
        cardLookUpResponse.type = cardLookup.getType();
        return ResponseEntity.ok(new Response<>(true, cardLookUpResponse));

    }
}

