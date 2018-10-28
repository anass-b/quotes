package com.anassbouassaba.quotes.entities;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Table(name = "vote_snapshots")
@Data
public class VoteSnapshot {
  @Id
  @GeneratedValue
  private long id;
  
  private long upvotes;

  private long downvotes;
  
  @OneToOne
  @JoinColumn(name = "quote_id")
  private Quote quote;
  
  @CreationTimestamp
  private Timestamp createdAt;

  public VoteSnapshot() {}

  public VoteSnapshot(Quote quote) {
    this.quote = quote;
    this.downvotes = quote.getDownvotes();
    this.upvotes = quote.getUpvotes();
  }
}
