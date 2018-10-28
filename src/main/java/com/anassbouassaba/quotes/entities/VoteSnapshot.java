package com.anassbouassaba.quotes.entities;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Data
public class VoteSnapshot {
  @Id
  @GeneratedValue
  private long id;
  
  private long upvotes;

  private long downvotes;
  
  private long quoteId;
  
  @CreationTimestamp
  private Timestamp createdAt;

  public VoteSnapshot() {}

  public VoteSnapshot(Quote quote) {
    quoteId = quote.getId();
    downvotes = quote.getDownvotes();
    upvotes = quote.getUpvotes();
  }
}
