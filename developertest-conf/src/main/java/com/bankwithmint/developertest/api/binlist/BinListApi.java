package com.bankwithmint.developertest.api.binlist;

import com.bankwithmint.developertest.binlist.CardLookupApiResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BinListApi {
    @GET("{cardNumber}")
    Call<CardLookupApiResponse> lookUpCard(@Path("cardNumber") String cardNumber);
}
