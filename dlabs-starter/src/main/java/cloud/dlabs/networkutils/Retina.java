package cloud.dlabs.networkutils;


import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Retina is a retrofit wrapper to make quick API calls.
 */
public abstract class Retina<T> {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * This will be used to initialise all retrofit
     */
    protected void init() {
        String baseUrl = baseUrl();
        apiService();
        if (StringUtils.isBlank(baseUrl)) {
            throw new IllegalArgumentException("Base Url must be provided to initialise Retina.");
        }
        if (!baseUrl.endsWith("/")) {
            baseUrl = baseUrl + "/";
        }
        logger.info(String.format("%s base url {%s}", apiService().getSimpleName(), baseUrl));

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        interceptors().forEach(builder::addInterceptor);
        builder.addInterceptor(requestLogger());
        OkHttpClient okHttpClient = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        afterRetinaInit(retrofit.create(apiService()));

    }

    protected abstract String baseUrl();

    protected abstract Class<T> apiService();

    protected abstract void afterRetinaInit(T apiService);

    protected List<Interceptor> interceptors() {
        return new ArrayList<Interceptor>();
    }


    public Interceptor requestLogger() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;

    }
}
