package com.suggest.referal.ReferalSystem.dto.flipkart;

import lombok.Data;

@Data
public class Product {

	private ProductBaseInfoV1 productBaseInfoV1;

	private ProductShippingInfoV1 productShippingInfoV1;

	private CategorySpecificInfoV1 categorySpecificInfoV1;

}
