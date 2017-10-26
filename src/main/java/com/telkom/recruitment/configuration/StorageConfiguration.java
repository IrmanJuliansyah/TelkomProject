package com.telkom.recruitment.configuration;

import com.telkom.recruitment.helper.storage.StorageHelper;
import com.telkom.recruitment.helper.storage.StorageProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(StorageProperties.class)
public class StorageConfiguration {

    @Bean
    CommandLineRunner init(StorageHelper storageHelper) {
        return (args) -> storageHelper.init();
    }

}