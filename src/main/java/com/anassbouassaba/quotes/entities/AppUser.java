package com.anassbouassaba.quotes.entities;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
public class AppUser {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;
  
  private String fullName;
  
  @Column(unique=true)
  private String username;
  
  private String password;
  
  private String role;
  
  @CreationTimestamp
  private Calendar createdAt;
  
  @UpdateTimestamp
  private Calendar updatedAt;
}
