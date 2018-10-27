package com.anassbouassaba.quotes.dtos;

import com.anassbouassaba.quotes.entities.VoteSnapshot;

import lombok.Data;

@Data
public class VoteSnapshotDto {
  private String quoteId;
  private String upvotes;
  private long upvotesValue;
  private String downvotes;
  private long downvotesValue;
  private long timestamp;
  
  public VoteSnapshotDto() {}
  
  public VoteSnapshotDto(VoteSnapshot vote) {
    quoteId = Long.toString(vote.getQuoteId());
    upvotes = QuoteDto.upvotesToString(vote.getUpvotes());
    upvotesValue = vote.getUpvotes();
    downvotes = QuoteDto.downvotesToString(vote.getDownvotes());
    downvotesValue = vote.getDownvotes();
    timestamp = vote.getCreatedAt().getTimeInMillis();
  }
}
