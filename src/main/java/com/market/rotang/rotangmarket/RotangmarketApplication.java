package com.market.rotang.rotangmarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:image.properties")
@SpringBootApplication
public class RotangmarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(RotangmarketApplication.class, args);
    }

}
