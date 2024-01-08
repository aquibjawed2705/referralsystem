package com.suggest.referal.ReferalSystem.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Component
@Slf4j
public class FlipkartClient {

	@Autowired
	private RestTemplate restTemplate;

	public ResponseEntity<String> getFlipkartSearchResult(String searchString) {
		String url = "https://www.flipkart.com/search?q=" + searchString
				+ "&otracker=search&otracker1=search&marketplace=FLIPKART&as-show=on&as=off&sort=relevance";
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class,
				Optional.ofNullable(null));
		return response;
	}

	public ResponseEntity<String> getFlipkartData(String searchString, String resultCount) {
		String url = "https://affiliate-api.flipkart.net/affiliate/1.0/search.json?query=" + searchString
				+ "&resultCount=" + resultCount;
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Fk-Affiliate-Id", "ajaquibaj");
		headers.add("Fk-Affiliate-Token", "1953fd15da7446db8a423a2d40b565a5");
		HttpEntity<?> entity = new HttpEntity<>(headers);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class,
				Optional.ofNullable(null));
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
			response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class, Optional.ofNullable(null));
		}
		catch (Exception e) {
			log.info("Exception while fetching data for productId {}", productId);
		}
		return response;
	}

	public ResponseEntity<String> getFlipkartDataByFetch(String searchQuery) {
		ResponseEntity<String> responseEntity = null;
		try {
			String url = "http://1.rome.api.flipkart.com/api/4/page/fetch";

			HttpHeaders headers = new HttpHeaders();
			headers.set("X-User-Agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 FKUA/website/42/website/Desktop");

			// Adjusted the URL parameters based on robots.txt rules
			String encodedSearchQuery = URLEncoder.encode(searchQuery, StandardCharsets.UTF_8.toString());
			String requestBody = String.format(
					"{\"pageUri\":\"/search?q=%s&otracker=search&otracker1=search&marketplace=FLIPKART&as-show=off&as=off\",\"pageContext\":{\"fetchSeoData\":true,\"paginatedFetch\":false,\"pageNumber\":1},\"requestContext\":{\"type\":\"BROWSE_PAGE\",\"ssid\":\"kaz6vjr8gg0000001704167842908\",\"sqid\":\"eoe4ww1ag00000001704168232880\"}}",
					encodedSearchQuery);

			HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

			responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
		}
		catch (Exception e) {
			log.error("Exception while fetching data for searchQuery from Flipkart. Exception: {}", e.getMessage());
		}
		return responseEntity;
	}

}
