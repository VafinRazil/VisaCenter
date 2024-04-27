package com.rvafin.visacenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class VisaCenterApplication {

    //@todo лучше избавиться от expression-ов, сделать через @AfterMapping
    //@todo отрефакторить StringFormatter (добавить возможность указания формата для Double, LocalDateTime)
    //@todo добавить TouristInfoDTO - долгий ящик
    //@todo добавить паттерн "Состояние" для отслеживания статусов оформления/присвоения визы - долгий ящик
    public static void main(String[] args) {
        SpringApplication.run(VisaCenterApplication.class, args);
    }

}
