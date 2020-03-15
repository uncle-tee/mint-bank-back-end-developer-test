package com.bankwithmint.developertest;

import cloud.dlabs.conf.ServiceLayerConfiguration;
import configuration.CustomConfig;
import configuration.WebConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootApplication(exclude = {
        HibernateJpaAutoConfiguration.class,
        ValidationAutoConfiguration.class


})


@Import({
        CustomConfig.class,
        WebConfiguration.class,
        ServiceLayerConfiguration.class
})

public class DevelopertestApplication {
    public static void main(String[] args) {
        SpringApplication.run(DevelopertestApplication.class, args);
    }

}
