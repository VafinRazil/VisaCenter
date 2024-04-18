package com.rvafin.visacenter.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VisaGenerateConfiguration {

    @Value("${visa.period}")
    private int visaIssuancePeriodInMonths;

    public int getVisaIssuancePeriodInMonths() {
        return visaIssuancePeriodInMonths;
    }
}
