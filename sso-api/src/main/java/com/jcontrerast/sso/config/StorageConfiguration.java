package com.jcontrerast.sso.config;

import com.jcontrerast.utils.core.StorageService;
import com.jcontrerast.utils.service.StorageServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfiguration {
    @Bean
    StorageService storageService() {
        return new StorageServiceImpl();
    }
}
