package com.anassbouassaba.quotes.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.anassbouassaba.quotes.entities.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {}
