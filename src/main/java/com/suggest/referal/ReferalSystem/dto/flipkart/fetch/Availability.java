package com.suggest.referal.ReferalSystem.dto.flipkart.fetch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Availability {

	private String messageIntent;

	private String displayState;

	private String intent;

	private boolean showMessage;

}
