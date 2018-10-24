package com.anassbouassaba.quotes.dtos;

import lombok.Data;

@Data
public class CreateUserDto {
  private String fullName;
  private String username;
  private String password;
}
