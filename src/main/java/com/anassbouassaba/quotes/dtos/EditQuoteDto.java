package com.anassbouassaba.quotes.dtos;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class EditQuoteDto {
  @NotNull
  private String content;
}
