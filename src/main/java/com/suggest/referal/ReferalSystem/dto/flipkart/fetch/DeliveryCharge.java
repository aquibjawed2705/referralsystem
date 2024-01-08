package com.suggest.referal.ReferalSystem.dto.flipkart.fetch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeliveryCharge {

	private String additionalText;

	private String currency;

	private String decimalValue;

	private int discount;

	private double downpaymentRate;

	private boolean downpaymentRequired;

	private String name;

	private boolean strikeOff;

	private String textStyle;

	private double value;

}
