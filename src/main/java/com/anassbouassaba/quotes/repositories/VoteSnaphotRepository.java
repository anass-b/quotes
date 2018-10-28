package com.anassbouassaba.quotes.repositories;

import com.anassbouassaba.quotes.entities.VoteSnapshot;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface VoteSnaphotRepository extends PagingAndSortingRepository<VoteSnapshot, Long> {}
