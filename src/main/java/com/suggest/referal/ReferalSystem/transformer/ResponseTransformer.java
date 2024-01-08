package com.suggest.referal.ReferalSystem.transformer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.suggest.referal.ReferalSystem.dto.flipkart.Product;
import com.suggest.referal.ReferalSystem.dto.flipkart.ProductResponses;
import com.suggest.referal.ReferalSystem.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ResponseTransformer {

	public static ProductResponses transform(String response) {
		try {
			return JsonUtil.convert(response, ProductResponses.class);
		}
		catch (JsonProcessingException e) {
			log.error("Error while parsing and Exception is {}", e);
			return null;
		}
	}

	public static Product transformIntoProduct(String response) {
		try {
			return JsonUtil.convert(response, Product.class);
		}
		catch (JsonProcessingException e) {
			log.error("Error while parsing and Exception is {}", e);
			return null;
		}
	}

}
