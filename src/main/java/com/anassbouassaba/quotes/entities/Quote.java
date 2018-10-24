package com.anassbouassaba.quotes.entities;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
public class Quote {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;
  
  private String content;
  
  private Integer votes;
  
  @OneToOne
  @JoinColumn(name = "user_id")
  private User user;
  
  @CreationTimestamp
  private Calendar createdAt;
  
  @UpdateTimestamp
  private Calendar updatedAt;
}
