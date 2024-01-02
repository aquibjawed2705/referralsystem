package com.suggest.referal.ReferalSystem.controller;

import com.suggest.referal.ReferalSystem.builder.ResponseBuilder;
import com.suggest.referal.ReferalSystem.client.FlipkartClient;
import com.suggest.referal.ReferalSystem.dto.flipkart.Product;
import com.suggest.referal.ReferalSystem.dto.flipkart.ProductResponses;
import com.suggest.referal.ReferalSystem.entity.UrlFinder;
import com.suggest.referal.ReferalSystem.response.SearchResponse;
import com.suggest.referal.ReferalSystem.service.UrlService;
import com.suggest.referal.ReferalSystem.transformer.ResponseTransformer;
import com.suggest.referal.ReferalSystem.util.ProductUtils;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

@RestController
@RequestMapping("/referral/system")
@Slf4j
public class ReferralSystemController {

  @Autowired
  UrlService urlService;

  @Autowired
  private FlipkartClient flipkartClient;

  @GetMapping("/search")
  public List<SearchResponse> getUrlName(@RequestParam String searchString, @RequestParam int count, HttpServletResponse httpResponse) {
    httpResponse.addHeader("Access-Control-Allow-Origin", "*");
    List<SearchResponse> searchResponse = null;
    try {
      log.info("Received request for search string: {}", searchString);
      String searchResult = flipkartClient.getFlipkartDataByFetch(searchString).getBody();
      log.info("Search Result is {}", searchResult);
      Set<String> productIds = ProductUtils.getFilteredDataFromFetch(searchResult);
      log.info("Product Ids are {}", productIds);
      List<Product> products = new ArrayList<>();
      ExecutorService executorService = Executors.newFixedThreadPool(5); // Adjust the pool size as needed
      List<Future<Product>> futures = new ArrayList<>();
      for (String productId : productIds) {
        Callable<Product> callable = () -> {
          ResponseEntity<String> result = flipkartClient.getFlipkartDataByProductId(productId);
          if (result != null) {
            return ResponseTransformer.transformIntoProduct(result.getBody());
          }
          return null;
        };

        futures.add(executorService.submit(callable));
      }

      for (Future<Product> future : futures) {
        try {
          Product product = future.get(); // This will block until the result is available
          if (product != null) {
            products.add(product);
          }
        } catch (InterruptedException | ExecutionException e) {
         log.error("Exception while paralleism is {}", e);
        }

        if (products.size() >= count) {
          break;
        }
      }
      executorService.shutdown(); // Shutdown the executor service

      searchResponse = ResponseBuilder.buildSearchResponses(new ProductResponses(products));
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
    }catch (Exception e) {
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
