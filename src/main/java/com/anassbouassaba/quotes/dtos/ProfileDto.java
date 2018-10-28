package com.anassbouassaba.quotes.dtos;

import com.anassbouassaba.quotes.entities.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProfileDto {
  private String id;
  private String fullName;
  private String username;

  public ProfileDto(User user) {
    id = Long.toString(user.getId());
    username = user.getUsername();
    fullName = user.getFullName();
  }
}
