package com.eliba.quotes.dtos;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EditQuoteDto {
  @NotNull
  private String content;
}
