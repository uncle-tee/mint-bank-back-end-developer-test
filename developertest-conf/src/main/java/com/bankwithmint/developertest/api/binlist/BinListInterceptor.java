package com.bankwithmint.developertest.api.binlist;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class BinListInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request newRequest = originalRequest.newBuilder()
                .addHeader("Accept-Version", "3")
                .build();
        return chain.proceed(newRequest);
    }
}
