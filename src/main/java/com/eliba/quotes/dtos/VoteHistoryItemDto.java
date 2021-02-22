package com.eliba.quotes.dtos;

import com.eliba.quotes.mappers.VoteHistoryItem;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VoteHistoryItemDto {
  private long upvotes;
  private long downvotes;
  private long delta;
  private long createdAt;
  
  public VoteHistoryItemDto(VoteHistoryItem vote) {
    upvotes = vote.getUpvotes().longValue();
    downvotes = vote.getDownvotes().longValue();
    delta = vote.getDelta().longValue();
    createdAt = vote.getCreatedAt().getTime();
  }
}
