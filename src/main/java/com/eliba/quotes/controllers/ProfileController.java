package com.eliba.quotes.controllers;

import java.security.Principal;

import com.eliba.quotes.dtos.ProfileDto;
import com.eliba.quotes.entities.User;
import com.eliba.quotes.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("profile")
public class ProfileController {
  @Autowired
  private UserRepository userRepository;

  @GetMapping
  public ProfileDto get(Principal principal) {
    User user = userRepository.findByUsername(principal.getName());
    return new ProfileDto(user);
  }
}