package com.eliba.quotes.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private long id;
  
  private String fullName;
  
  @NaturalId
  @Column(unique=true)
  private String username;
  
  private String password;
  
  private boolean enabled;
  
  @CreationTimestamp
  private Timestamp createdAt;
  
  @UpdateTimestamp
  private Timestamp updatedAt;
}
