package com.anassbouassaba.quotes.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.anassbouassaba.quotes.entities.AppUser;

public interface UserRepository extends PagingAndSortingRepository<AppUser, Long> {}
