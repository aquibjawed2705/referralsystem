package com.suggest.referal.ReferalSystem.dto.flipkart.fetch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Media {

	private List<Image> images;

}
