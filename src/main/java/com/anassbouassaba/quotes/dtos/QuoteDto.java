package com.anassbouassaba.quotes.dtos;

import com.anassbouassaba.quotes.entities.Quote;

import lombok.Data;

@Data
public class QuoteDto {
  private String id;
  private String content;
  private String upvotes;
  private long upvotesValue;
  private String downvotes;
  private long downvotesValue;
  private String user;
  
  public QuoteDto() {}
  
  public QuoteDto(Quote quote) {
    id = Long.toString(quote.getId());
    content = quote.getContent();
    upvotes = upvotesToString(quote.getUpvotes());
    upvotesValue = quote.getUpvotes();
    downvotes = downvotesToString(quote.getDownvotes());
    downvotesValue = quote.getDownvotes();
    if (quote.getAppUser() != null) {
      user = quote.getAppUser().getUsername();
    }
  }

  public static String upvotesToString(long votes) {
    String result = "";
    if (votes > 0) {
      result += "+";
    }
    result += Long.toString(votes);

    return result;
  }

  public static String downvotesToString(long votes) {
    String result = "";
    if (votes > 0) {
      result += "-";
    }
    result += Long.toString(votes);

    return result;
  }
}
