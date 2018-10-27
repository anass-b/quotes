package com.anassbouassaba.quotes.dtos;

import com.anassbouassaba.quotes.entities.AppUser;

import lombok.Data;

@Data
public class UserDto {
  private String id;
  private String fullName;
  private String username;
  private String role;

  public UserDto() {}

  public UserDto(AppUser user) {
    id = user.getId().toString();
    username = user.getUsername();
    fullName = user.getFullName();
    role = user.getRole();
  }
}
