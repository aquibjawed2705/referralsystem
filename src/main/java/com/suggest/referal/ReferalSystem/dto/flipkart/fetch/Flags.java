package com.suggest.referal.ReferalSystem.dto.flipkart.fetch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Flags {

	private boolean enableChat;

	private boolean enableCompare;

	private boolean enableFlipkartAdvantage;

	private boolean enableOfferTag;

	private boolean enableVisualDiscovery;

	private boolean enableWishlist;

	private boolean showSecondaryTitle;

	private boolean swatchAvailableOnBrowsePage;

}
