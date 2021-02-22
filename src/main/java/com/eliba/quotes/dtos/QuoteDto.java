package com.eliba.quotes.dtos;

import com.eliba.quotes.entities.Quote;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuoteDto {
  private String id;
  private String content;
  private String upvotes;
  private long upvotesValue;
  private String downvotes;
  private long downvotesValue;
  private String user;
  
  public QuoteDto(Quote quote) {
    id = Long.toString(quote.getId());
    content = quote.getContent();
    upvotes = upvotesToString(quote.getUpvotes());
    upvotesValue = quote.getUpvotes();
    downvotes = downvotesToString(quote.getDownvotes());
    downvotesValue = quote.getDownvotes();
    if (quote.getUser() != null) {
      user = quote.getUser().getUsername();
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
