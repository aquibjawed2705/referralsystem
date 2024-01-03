package com.suggest.referal.ReferalSystem.dto.flipkart.fetch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Rating {
  private String type;
  private double average;
  private int base;
  private List<Integer> breakup;
  private int count;
  private int histogramBaseCount;
  private int reviewCount;
  private String roundOffCount;
}
