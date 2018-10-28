package com.anassbouassaba.quotes.dtos;

import com.anassbouassaba.quotes.mappers.VoteHistoryItem;

import lombok.Data;

@Data
public class VoteHistoryItemDto {
  private long upvotes;
  private long downvotes;
  private long delta;
  private long createdAt;
  
  public VoteHistoryItemDto() {}
  
  public VoteHistoryItemDto(VoteHistoryItem vote) {
    upvotes = vote.getUpvotes().longValue();
    downvotes = vote.getDownvotes().longValue();
    delta = vote.getDelta().longValue();
    createdAt = vote.getCreatedAt().getTime();
  }
}
