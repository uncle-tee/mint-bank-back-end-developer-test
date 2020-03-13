package com.bankwithmint.developertest.api.binlist;

import cloud.dlabs.apiutil.RetinaSpringStarter;
import com.bankwithmint.developertest.binlist.CardLookup;
import com.bankwithmint.developertest.service.CardLookApiService;
import okhttp3.Interceptor;
import retrofit2.Response;


import javax.inject.Named;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Named
public class BinListApiService extends RetinaSpringStarter<BinListApi> implements CardLookApiService {

    BinListApi binListApi = null;

    @Override
    protected String baseUrl() {
        return "https://lookup.binlist.net/";
    }

    @Override
    protected Class<BinListApi> apiService() {
        return BinListApi.class;
    }

    @Override
    protected void afterRetinaInit(BinListApi apiService) {
        binListApi = apiService;
    }

    @Override
    protected List<okhttp3.Interceptor> interceptors() {
        ArrayList<Interceptor> interceptors = new ArrayList<>();
        interceptors.add(new BinListInterceptor());
        return interceptors;
    }

    @Override
    public CardLookup verifyCard(String cardNumber) {
        Response<CardLookup> execute = null;
        try {
            execute = binListApi.lookUpCard(cardNumber).execute();
            if (execute.isSuccessful() && execute.body() != null) {
                return execute.body();
            }
            if (!execute.isSuccessful()) {
                if (execute.code() == 404) {
                    throw new IllegalArgumentException(String.valueOf(404));
                }
                if (execute.code() == 429) {
                    throw new IllegalArgumentException("Sorry we cannot verify card at this time");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        throw new IllegalArgumentException("There was an error while trying to validate card");

    }
}
