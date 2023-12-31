package com.suggest.referal.ReferalSystem.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class FlipkartClient {

  @Autowired
  private RestTemplate restTemplate;

  public ResponseEntity<String> getFlipkartData(String searchString, String resultCount) {
    String url =
        "https://affiliate-api.flipkart.net/affiliate/1.0/search.json?query="
            + searchString
            + "&resultCount=" + resultCount;
    MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
    headers.add("Fk-Affiliate-Id", "ajaquibaj");
    headers.add("Fk-Affiliate-Token", "1953fd15da7446db8a423a2d40b565a5");
    HttpEntity<?> entity = new HttpEntity<>(headers);
    ResponseEntity<String> response =
        restTemplate.exchange(url, HttpMethod.GET, entity, String.class, Optional.ofNullable(null));
    return response;
  }
}
