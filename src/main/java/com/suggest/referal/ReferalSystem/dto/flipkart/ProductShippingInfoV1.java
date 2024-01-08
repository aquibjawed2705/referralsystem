package com.suggest.referal.ReferalSystem.dto.flipkart;

import lombok.Data;

@Data
public class ProductShippingInfoV1 {

	private ShippingCharges shippingCharges;

	private String estimatedDeliveryTime;

	private String sellerName;

	private Integer sellerAverageRating;

	private int sellerNoOfRatings;

	private int sellerNoOfReviews;

}
