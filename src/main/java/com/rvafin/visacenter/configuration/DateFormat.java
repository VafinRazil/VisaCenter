package com.rvafin.visacenter.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.text.Format;

@Configuration
public class DateFormat {

    private String dateFormat;

    private String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";

}
