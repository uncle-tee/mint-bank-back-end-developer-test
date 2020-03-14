package configuration;


import cloud.dlabs.datautils.OffsetBasedPageRequest;
import com.bankwithmint.developertest.adapters.HibernateProxyTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@ComponentScan({
        "com.bankwithmint.developertest",
        "cloud.dlabs",
})

@EnableJpaRepositories({
        "com.bankwithmint.developertest.dao"

})
public class CustomConfig {
    @Bean
    public Gson gson() {
        GsonBuilder gsonBuilder = new GsonBuilder()
                .registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
        return gsonBuilder.create();
    }

    @Bean
    public OkHttpClient httpClient() {
        return new OkHttpClient();
    }



}
