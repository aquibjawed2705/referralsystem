package com.suggest.referal.ReferalSystem.dto.flipkart;

import lombok.Data;

import java.util.List;

@Data
public class CategorySpecificInfoV1 {

	private List<String> keySpecs;

	private List<String> detailedSpecs;

	private List<SpecificationList> specificationList;

	private BooksInfo booksInfo;

	private LifeStyleInfo lifeStyleInfo;

}
