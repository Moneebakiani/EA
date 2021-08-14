package edu.miu.cs.cs544.client.service;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import edu.miu.cs.cs544.client.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CountryServiceClient {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${base-service-url}")
    private String serverName;

    @Value("${country-service.username}")
    private String username;

    @Value("${country-service.password}")
    private String password;

//    @HystrixCommand(fallbackMethod = "findByIdFallBack")
    public Country findById(String countryCode) {
        //Thread.sleep(1000);
        String url = getBaseServiceUrl() + "/countries/" + countryCode;
        return restTemplate.exchange(url, HttpMethod.GET, createHttpEntity(), Country.class).getBody();
    }

    public Country findByIdFallBack(String countryCode) {
        System.out.println("Find by ID Fallback");
        return new Country();
    }

//    @HystrixCommand(fallbackMethod = "findAllFallBack")
    public List<Country> findAll() {
        //Thread.sleep(1000);
//        Country[] countries = restTemplate
//                .getForObject(getBaseServiceUrl() + "/countries",
//                        Country[].class);
        Country[] countries = restTemplate.
                exchange(getBaseServiceUrl() + "/countries",
                        HttpMethod.GET, createHttpEntity(),
                        Country[].class).getBody();
        return Arrays.asList(countries);
    }

    public List<Country> findAllFallBack() {
        System.out.println("Find ALL Fallback");
        return new ArrayList<>();
    }

    private String getBaseServiceUrl() {
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serverName);
        serviceInstances.forEach(System.out::println);
        return serviceInstances.get(0).getUri().toString();
    }

    private HttpEntity<Object> createHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setBasicAuth(username, password);

        return new HttpEntity<>(headers);
    }

}
