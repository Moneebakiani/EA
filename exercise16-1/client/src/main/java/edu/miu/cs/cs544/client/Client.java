package edu.miu.cs.cs544.client;

import edu.miu.cs.cs544.client.model.Country;
import edu.miu.cs.cs544.client.service.CountryServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@SpringBootApplication
@EnableCircuitBreaker
public class Client {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Client.class, args);

        CountryServiceClient client = context.getBean(CountryServiceClient.class);

        Country country = client.findById("US");
        log.info("COUNTRY -> {}", country);

        List<Country> countries = client.findAll();
        countries.forEach(System.out::println);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
