package com.suggest.referal.ReferalSystem.dto.flipkart.fetch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductInfo {
  private Media media;
  private Pricing pricing;
  private Rating rating;
  private String smartUrl;
  private List<String> tags;
  private Titles titles;
}

