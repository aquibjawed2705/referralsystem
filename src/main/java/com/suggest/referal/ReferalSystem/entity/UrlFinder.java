package com.suggest.referal.ReferalSystem.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UrlFinder {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 2000)
  private String href;

  private String name;

  @Column(length = 2000)
  private String src;
}
