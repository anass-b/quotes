package com.anassbouassaba.quotes.dtos;

import com.anassbouassaba.quotes.entities.User;

import lombok.Data;

@Data
public class UserDto {
  private Long id;
  private String fullName;
  private String username;
  private String role;

  public UserDto() {}

  public UserDto(User user) {
    this.id = user.getId();
    this.username = user.getUsername();
    this.fullName = user.getFullName();
    this.role = user.getRole();
  }
}
