package com.suggest.referal.ReferalSystem.dto.flipkart.fetch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Image {

	private String aspectRatio;

	private String contentInfo;

	private String height;

	private String url;

	private String width;

}
