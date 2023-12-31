package com.suggest.referal.ReferalSystem.dto.flipkart;

import java.util.List;
import lombok.Data;

@Data
public class BooksInfo {
  private String language;
  private String binding;
  private int pages;
  private String publisher;
  private int year;
  private List<String> authors;
}
