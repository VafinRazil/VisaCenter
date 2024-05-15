package com.rvafin.visacenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class})
@EnableScheduling
public class VisaCenterApplication {

    //@todo лучше избавиться от expression-ов, сделать через @AfterMapping
    //@todo отрефакторить StringFormatter (добавить возможность указания формата для Double, LocalDateTime)
    //@todo добавить TouristInfoDTO - долгий ящик
    //@todo добавить паттерн "Состояние" для отслеживания статусов оформления/присвоения визы - долгий ящик
    //@todo добавить статус для заявки на визу ,где показано, что данные были изменены
    public static void main(String[] args) {
        SpringApplication.run(VisaCenterApplication.class, args);
    }

}
