package com.anassbouassaba.quotes.mappers;

import java.sql.Timestamp;

public interface VoteHistoryItem {
  long getDownvotes();
  long getUpvotes();
  long getDelta();
  Timestamp getCreatedAt();
}