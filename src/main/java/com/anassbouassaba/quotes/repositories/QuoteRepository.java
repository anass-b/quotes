package com.anassbouassaba.quotes.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.anassbouassaba.quotes.entities.Quote;

@RepositoryRestResource(collectionResourceRel = "quotes", path = "quotes")
public interface QuoteRepository extends PagingAndSortingRepository<Quote, Long> {
  @Transactional
  @Modifying
  @Query("update Quote set votes = votes + 1 where id = :id")
  void upvote(@Param("id") Long id);
  
  @Transactional
  @Modifying
  @Query("update Quote set votes = votes - 1 where id = :id")
  void downvote(@Param("id") Long id);
}
