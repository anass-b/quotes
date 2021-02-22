package com.eliba.quotes.dtos;

import com.eliba.quotes.entities.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProfileDto {
  private String fullName;
  private String username;

  public ProfileDto(User user) {
    username = user.getUsername();
    fullName = user.getFullName();
  }
}
