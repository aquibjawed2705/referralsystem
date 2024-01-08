package com.suggest.referal.ReferalSystem.dto.flipkart;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProductResponses {

	private List<Product> products;

}
