package com.suggest.referal.ReferalSystem.dto.flipkart;

import lombok.Data;

import java.util.List;

@Data
public class ProductBaseInfoV1 {
  private String productId;
  private String title;
  private String productDescription;
  private ImageUrls imageUrls;
  private List<String> productFamily;
  private MaximumRetailPrice maximumRetailPrice;
  private FlipkartSellingPrice flipkartSellingPrice;
  private FlipkartSpecialPrice flipkartSpecialPrice;
  private String productUrl;
  private String productBrand;
  private boolean inStock;
  private boolean codAvailable;
  private double discountPercentage;
  private List<String> offers;
  private String categoryPath;
  private Attributes attributes;
}
