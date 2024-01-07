package com.suggest.referal.ReferalSystem.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.suggest.referal.ReferalSystem.builder.ResponseBuilder;
import com.suggest.referal.ReferalSystem.cache.ReferralCache;
import com.suggest.referal.ReferalSystem.client.FlipkartClient;
import com.suggest.referal.ReferalSystem.dto.flipkart.fetch.ProductInfo;
import com.suggest.referal.ReferalSystem.entity.UrlFinder;
import com.suggest.referal.ReferalSystem.response.SearchResponse;
import com.suggest.referal.ReferalSystem.service.UrlService;
import com.suggest.referal.ReferalSystem.util.JsonUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/referral/system")
@Slf4j
public class ReferralSystemController {

  @Autowired
  UrlService urlService;

  @Autowired
  private FlipkartClient flipkartClient;

  @Autowired
  private ReferralCache referralCache;

  @GetMapping("/search")
  public List<SearchResponse> getUrlName(@RequestParam String searchString, @RequestParam int count, HttpServletResponse httpResponse) {
    httpResponse.addHeader("Access-Control-Allow-Origin", "*");

    if(referralCache.get(searchString)!=null){
      return referralCache.get(searchString);
    }

    List<SearchResponse> searchResponse = null;

    try {
      log.info("Received request for search string: {}", searchString);
      String searchResult = flipkartClient.getFlipkartDataByFetch(searchString).getBody();
      log.info("Search Result is {}", searchResult);
      JsonNode jsonNode = JsonUtil.convert(searchResult, JsonNode.class);
      List<JsonNode> jsonNodes = new ArrayList<>();
      jsonNode.get("RESPONSE").get("slots").elements().forEachRemaining(jsonNode1 -> {
        if (!jsonNode1.get("slotType").asText().equals("LOGICAL")) {
          jsonNode1.get("widget").get("data").elements().forEachRemaining(jsonNode2 -> {
            jsonNode2.elements().forEachRemaining(s -> {
              if (s.get("adInfo") != null || s.get("addToWishlist") != null) {
                jsonNodes.add(s.get("productInfo").get("value"));
              }
            });
          });
        }
      });

      List<ProductInfo> products = new ArrayList<>();
      for (JsonNode jsonNode1:jsonNodes) {
        ProductInfo productInfo = JsonUtil.convert(jsonNode1, ProductInfo.class);
        products.add(productInfo);
      }
      searchResponse = ResponseBuilder.buildSearchResponses(products);
      if(referralCache.get(searchString)==null && searchString.equals("trending")){
        referralCache.put("trending",searchResponse);
      }
    } catch (Exception e) {
      log.error("Exception is {}", e);
    }
    log.info("Response is {}", searchResponse);
    return searchResponse;
  }

  @GetMapping("/fetch")
  public String saveUrl(@RequestParam String searchString) {
    try {
      log.info("Received request for search string: {}", searchString);
      String searchResult = flipkartClient.getFlipkartDataByFetch(searchString).getBody();
      log.info("Search Result is {}", searchResult);
      return searchResult;
    } catch (Exception e) {
      log.error("Exception is {}", e);
    }
    return "unable to fetch data!!";
  }

  private void getData(String link, UrlFinder urlFinder) {
    String href = null;
    String src = null;
    if (link.startsWith("<a")) {
      String[] parts = link.split("href=");
      String part1[] = parts[1].split("target=\"_blank\"");
      String part2[] = part1[1].split("src=");
      String part3[] = part2[1].split(" ></a><img ");
      href = part1[0];
      src = part3[0];
    } else if (link.contains("<iframe")) {
      String[] parts = link.split("src=");
      String part1[] = parts[1].split("></iframe>");
      src = part1[0];
    }
    if (href != null) {
      href = href.substring(1, href.length() - 1);
      urlFinder.setHref(href);
    }
    src = src.substring(1, src.length() - 1);
    urlFinder.setSrc(src);
  }
}
