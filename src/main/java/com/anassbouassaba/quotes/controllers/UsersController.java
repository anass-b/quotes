package com.anassbouassaba.quotes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anassbouassaba.quotes.dtos.CreateUserDto;
import com.anassbouassaba.quotes.dtos.UserDto;
import com.anassbouassaba.quotes.entities.AppUser;
import com.anassbouassaba.quotes.repositories.UserRepository;

@RestController
@RequestMapping("users")
public class UsersController {
  @Autowired
  private UserRepository userRepository;
  
  @PostMapping
  public UserDto create(@RequestBody CreateUserDto body) {
    AppUser user = new AppUser();
    user.setFullName(body.getFullName());
    user.setUsername(body.getUsername());
    user.setPassword(body.getPassword());
    
    userRepository.save(user);
    
    return new UserDto(user);
  }
}
