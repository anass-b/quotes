package com.anassbouassaba.quotes.entities;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
@Table(name = "quotes")
public class Quote {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private long id;
  
  private String content;
  
  private long upvotes;
  
  private long downvotes;
  
  @OneToOne
  @JoinColumn(name = "user_id")
  private User user;
  
  @CreationTimestamp
  private Timestamp createdAt;
  
  @UpdateTimestamp
  private Timestamp updatedAt;
}
