package com.suggest.referal.ReferalSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ReferalSystemApplication {

  public static void main(String[] args) {
    SpringApplication.run(ReferalSystemApplication.class, args);
  }

  @Bean
  public RestTemplate restTemplate() {
    RestTemplate restTemplate = new RestTemplate();
    SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();

    // Set the read timeout in milliseconds
    int timeout = 120000; // 5 seconds
    requestFactory.setReadTimeout(timeout);

    restTemplate.setRequestFactory(requestFactory);
    return restTemplate;
  }
}
