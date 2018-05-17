package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class XlytestApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(XlytestApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate()
    {
        return new RestTemplateBuilder().additionalMessageConverters(new FormHttpMessageConverter()).build();
    }
}
