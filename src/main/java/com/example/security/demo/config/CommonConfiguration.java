package com.example.security.demo.config;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

@Configuration
public class CommonConfiguration {
    public static final String APP_CONVERSION_SERVICE = "APP_CONVERSION_SERVICE";

    @Autowired
    private Set<Converter> converters;

    @Bean
    @Qualifier(APP_CONVERSION_SERVICE)
    public ConversionService appConversionService() {
        ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
        bean.setConverters(converters);
        bean.afterPropertiesSet();
        ConversionService object = bean.getObject();

        return object;
    }
}
