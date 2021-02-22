package com.eliba.quotes.controllers;

import com.eliba.quotes.dtos.RegisterDto;
import com.eliba.quotes.entities.Authority;
import com.eliba.quotes.entities.User;
import com.eliba.quotes.repositories.AuthorityRepository;
import com.eliba.quotes.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("register")
public class RegisterController {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private AuthorityRepository authorityRepository;
  
  @PostMapping
  public void register(@RequestBody RegisterDto body) {
    User user = new User();
    user.setFullName(body.getFullName());
    user.setUsername(body.getUsername());
    user.setPassword(new BCryptPasswordEncoder().encode(body.getPassword()));
    user.setEnabled(true);
    userRepository.save(user);

    Authority authority = new Authority();
    authority.setUser(user);
    authority.setAuthority("ROLE_ADMIN");
    authorityRepository.save(authority);
  }
}