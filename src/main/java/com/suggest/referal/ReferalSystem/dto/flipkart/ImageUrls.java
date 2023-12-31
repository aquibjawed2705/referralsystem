package com.suggest.referal.ReferalSystem.dto.flipkart;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ImageUrls {
  @JsonProperty("200x200")
  private String _200x200;
  @JsonProperty("400x400")
  private String _400x400;
  @JsonProperty("800x800")
  private String _800x800;
}
