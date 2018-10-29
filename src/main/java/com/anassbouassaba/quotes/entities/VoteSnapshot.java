package com.anassbouassaba.quotes.entities;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
  
  @ManyToOne
  @JoinColumn(name = "quote_id")
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonIgnore
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
