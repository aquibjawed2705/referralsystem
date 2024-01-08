package com.suggest.referal.ReferalSystem.response;

import lombok.Data;

@Data
public class SearchResponse {

	private String imageUrl;

	private String productUrl;

	private double price;

	private String currency;

	private String title;

	private String description;

}
