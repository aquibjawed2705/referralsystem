package com.suggest.referal.ReferalSystem.dto.flipkart.fetch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pricing {

	private DeliveryCharge deliveryCharge;

	private int discountAmount;

	private EmiPrice emiPrice;

	private ExtraDiscount extraDiscount;

	private FinalPrice finalPrice;

	private String finalSavingsText;

	private List<String> knowMore;

	private String listingPriceType;

	private Mrp mrp;

	private List<Price> prices;

}
