package com.suggest.referal.ReferalSystem.dto.flipkart;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class LifeStyleInfo {
  private String sleeve;
  private String neck;
  @JsonProperty("idealFor")
  private List<String> idealFor;
}
