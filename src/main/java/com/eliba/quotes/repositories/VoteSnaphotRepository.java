package com.eliba.quotes.repositories;

import com.eliba.quotes.entities.VoteSnapshot;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface VoteSnaphotRepository extends PagingAndSortingRepository<VoteSnapshot, Long> {}
