package com.anassbouassaba.quotes.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.anassbouassaba.quotes.entities.VoteSnapshot;

public interface VoteSnaphotRepository extends PagingAndSortingRepository<VoteSnapshot, Long> {
  @Query("select v from VoteSnapshot v where v.quoteId = :quoteId order by createdAt desc")
  List<VoteSnapshot> findByQuoteId(@Param("quoteId") Long quoteId, Pageable pageable);
  
  @Query("select v from VoteSnapshot v where v.quoteId = :quoteId order by createdAt desc")
  List<VoteSnapshot> findByQuoteId(@Param("quoteId") Long quoteId);
}
