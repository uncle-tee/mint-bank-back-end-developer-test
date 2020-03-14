package com.bankwithmint.developertest.controller;

import com.bankwithmint.developertest.binlist.CardLookUpResponse;
import com.bankwithmint.developertest.dao.CardLookUpRepository;
import com.bankwithmint.developertest.dao.OffsetBasePageRequest;
import com.bankwithmint.developertest.domain.CardLookUp;
import com.bankwithmint.developertest.response.PageableResponse;
import com.bankwithmint.developertest.response.Response;
import com.bankwithmint.developertest.service.CardLookupService;
import com.bankwithmint.developertest.service.Producer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("card-scheme")
public class CardLookUpController {

    final CardLookupService cardLookApiService;

    final CardLookUpRepository cardLookUpRepository;


    public CardLookUpController(CardLookupService cardLookApiService,
                                CardLookUpRepository cardLookUpRepository) {
        this.cardLookApiService = cardLookApiService;
        this.cardLookUpRepository = cardLookUpRepository;

    }

    @GetMapping("/verify/{cardNumber}")
    public ResponseEntity<?> doCardLookUp(@PathVariable String cardNumber) {

        return cardLookApiService.doCardLookUp(cardNumber).map(cardLookup -> {
            CardLookUpResponse cardLookUpResponse = new CardLookUpResponse();
            cardLookUpResponse.bank = cardLookup.getBank().getName();
            cardLookUpResponse.scheme = cardLookup.getScheme();
            cardLookUpResponse.type = cardLookup.getType();
            return ResponseEntity.ok(new Response<>(true, cardLookUpResponse));
        }).get();

    }


    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    @GetMapping("/stats")
    public ResponseEntity<?> getStats(@RequestParam("start") Optional<Long> optionalStart,
                                      @RequestParam("limit") Optional<Integer> optionalLimit) {
        Pageable pageable = OffsetBasePageRequest
                .of(optionalStart.orElse(0L), optionalLimit.orElse(20), Sort.by(Sort.Direction.DESC, "count"));
        Page<CardLookUp> cardLookUpBy = cardLookUpRepository
                .findCardLookUpBy(pageable);
        Map<String, Long> lookUpCount = new HashMap<>();
        cardLookUpBy.getTotalElements();
        cardLookUpBy.forEach(cardLookUp -> {
            lookUpCount.put(cardLookUp.getNumber(), cardLookUp.getCount());
        });
        return ResponseEntity.ok(new PageableResponse<>(true,
                lookUpCount,
                pageable.getOffset(),
                optionalLimit.orElse(20), cardLookUpBy.getTotalElements()));


    }
}

