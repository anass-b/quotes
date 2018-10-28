package com.anassbouassaba.quotes.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "authorities")
@Data
public class Authority {
  @Id
  @GeneratedValue
  private long id;

  @OneToOne
  @JoinColumn(
    name = "username", 
    referencedColumnName = "username"
  )
  private User user;

  private String authority;
}