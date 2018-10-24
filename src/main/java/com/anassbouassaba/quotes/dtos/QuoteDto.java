package com.anassbouassaba.quotes.dtos;

import com.anassbouassaba.quotes.entities.Quote;

import lombok.Data;

@Data
public class QuoteDto {
  private Long id;
  private String content;
  private Integer votes;
  private String user;
  
  public QuoteDto() {}
  
  public QuoteDto(Quote quote) {
    this.id = quote.getId();
    this.content = quote.getContent();
    this.votes = quote.getVotes();
    if (quote.getUser() != null) {
      this.user = quote.getUser().getUsername();
    }
  }
}
