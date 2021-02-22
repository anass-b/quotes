package com.eliba.quotes.mappers;

import java.math.BigInteger;
import java.sql.Timestamp;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class VoteHistoryItem {
  private BigInteger downvotes;
  private BigInteger upvotes;
  private BigInteger delta;
  Timestamp createdAt;
}