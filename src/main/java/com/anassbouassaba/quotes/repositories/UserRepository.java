package com.anassbouassaba.quotes.repositories;

import com.anassbouassaba.quotes.entities.User;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
  User findByUsername(String username);
}
