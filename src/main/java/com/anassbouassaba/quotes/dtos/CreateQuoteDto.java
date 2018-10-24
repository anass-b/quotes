package com.anassbouassaba.quotes.dtos;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CreateQuoteDto {
  @NotNull
  private String content;
}
