package com.rvafin.visacenter.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImpreciseDataMatcherConfiguration {

    @Value("${imprecise.data.matcher.delimiter}")
    private String delimiter;

    public String getDelimiter() {
        return delimiter;
    }
}
