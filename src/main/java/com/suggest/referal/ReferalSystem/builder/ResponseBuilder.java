package com.suggest.referal.ReferalSystem.builder;

import com.suggest.referal.ReferalSystem.dto.flipkart.ProductResponses;
import com.suggest.referal.ReferalSystem.dto.flipkart.fetch.ProductInfo;
import com.suggest.referal.ReferalSystem.response.SearchResponse;

import java.util.List;
import java.util.stream.Collectors;

public class ResponseBuilder {

	public static List<SearchResponse> buildSearchResponses(ProductResponses productResponses) {
		List<SearchResponse> searchResponses = productResponses.getProducts().stream().map(product -> {
			SearchResponse searchResponse = new SearchResponse();
			searchResponse.setImageUrl(product.getProductBaseInfoV1().getImageUrls().get_200x200());
			searchResponse.setProductUrl(product.getProductBaseInfoV1().getProductUrl());
			searchResponse.setPrice(product.getProductBaseInfoV1().getFlipkartSpecialPrice().getAmount());
			searchResponse.setCurrency(product.getProductBaseInfoV1().getFlipkartSpecialPrice().getCurrency());
			searchResponse.setTitle(product.getProductBaseInfoV1().getTitle());
			searchResponse.setDescription(product.getProductBaseInfoV1().getProductDescription());
			return searchResponse;
		}).collect(Collectors.toList());
		return searchResponses;
	}

	public static List<SearchResponse> buildSearchResponses(List<ProductInfo> products) {
		List<SearchResponse> searchResponses = products.stream().map(product -> {
			try {
				SearchResponse searchResponse = new SearchResponse();
				searchResponse.setImageUrl(product.getMedia().getImages().get(0).getUrl().replace("{@width}", "200")
						.replace("{@height}", "200").replace("{@quality}", "90")
						.replaceFirst("http://rukmini1", "https://rukminim1"));
				searchResponse.setProductUrl(product.getSmartUrl().replaceFirst("http", "https") + "&affid=ajaquibaj");
				searchResponse.setPrice(product.getPricing().getFinalPrice().getValue());
				searchResponse.setCurrency(product.getPricing().getFinalPrice().getCurrency());
				searchResponse.setTitle(product.getTitles().getTitle());
				searchResponse.setDescription(product.getTitles().getNewTitle());
				return searchResponse;
			}
			catch (Exception e) {
				return null;
			}
		}).filter(p -> p != null).collect(Collectors.toList());
		return searchResponses;
	}

}
