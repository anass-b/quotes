package com.eliba.quotes.repositories;

import com.eliba.quotes.entities.User;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
  User findByUsername(String username);
}
