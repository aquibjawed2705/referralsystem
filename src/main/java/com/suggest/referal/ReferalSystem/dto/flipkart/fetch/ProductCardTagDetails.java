package com.suggest.referal.ReferalSystem.dto.flipkart.fetch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductCardTagDetails {

	private Image image;

	private TagDetails tagDetails;

	private String type;

}
