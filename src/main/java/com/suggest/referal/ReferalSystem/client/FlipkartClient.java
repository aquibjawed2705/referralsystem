package com.suggest.referal.ReferalSystem.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
@Slf4j
public class FlipkartClient {

  @Autowired
  private RestTemplate restTemplate;

  public ResponseEntity<String> getFlipkartSearchResult(String searchString) {
    String url = "https://www.flipkart.com/search?q=" + searchString + "&otracker=search&otracker1=search&marketplace=FLIPKART&as-show=on&as=off&sort=relevance";
    ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class, Optional.ofNullable(null));
    return response;
  }

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

  public ResponseEntity<String> getFlipkartDataByProductId(String productId) {
    String url = "https://affiliate-api.flipkart.net/affiliate/1.0/product.json?id=" + productId;
    MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
    headers.add("Fk-Affiliate-Id", "ajaquibaj");
    headers.add("Fk-Affiliate-Token", "1953fd15da7446db8a423a2d40b565a5");
    HttpEntity<?> entity = new HttpEntity<>(headers);
    ResponseEntity<String> response = null;
    try {
      response =
          restTemplate.exchange(url, HttpMethod.GET, entity, String.class, Optional.ofNullable(null));
    } catch (Exception e) {
      log.info("Exception while fetching data for productId {}", productId);
    }
    return response;
  }

  public ResponseEntity<String> getFlipkartDataByFetch(String searchQuery) {
    String url = "https://1.rome.api.flipkart.com/api/4/page/fetch";

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("Accept", "*/*");
    headers.set("Accept-Language", "en-US,en;q=0.9");
    headers.set("Connection", "keep-alive");
    headers.set("Content-Type", "application/json");
    headers.set("Origin", "https://www.flipkart.com");
    headers.set("Referer", "https://www.flipkart.com/");
    headers.set("Sec-Fetch-Dest", "empty");
    headers.set("Sec-Fetch-Mode", "cors");
    headers.set("Sec-Fetch-Site", "same-site");
    headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");
    headers.set("X-User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 FKUA/website/42/website/Desktop");
    headers.set("sec-ch-ua", "\"Not_A Brand\";v=\"8\", \"Chromium\";v=\"120\", \"Google Chrome\";v=\"120\"");
    headers.set("sec-ch-ua-mobile", "?0");
    headers.set("sec-ch-ua-platform", "\"Windows\"");
    String requestBody = String.format("{\"pageUri\":\"/search?q=%s&otracker=search&otracker1=search&marketplace=FLIPKART&as-show=off&as=off\",\"pageContext\":{\"fetchSeoData\":true,\"paginatedFetch\":false,\"pageNumber\":1},\"requestContext\":{\"type\":\"BROWSE_PAGE\",\"ssid\":\"kaz6vjr8gg0000001704167842908\",\"sqid\":\"eoe4ww1ag00000001704168232880\"}}", searchQuery);
    HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
    ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
    return responseEntity;
  }
}
