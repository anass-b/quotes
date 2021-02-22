package com.eliba.quotes.repositories;

import com.eliba.quotes.entities.Authority;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface AuthorityRepository extends PagingAndSortingRepository<Authority, Long> {}
