package com.anassbouassaba.quotes.services;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Tuple;

import com.anassbouassaba.quotes.mappers.VoteHistoryItem;

import org.springframework.stereotype.Service;

@Service
public class QuoteService {
  @PersistenceContext
  private EntityManager entityManager;

  public List<VoteHistoryItem> getVoteHistory(Long quoteId, String unit, Integer limit) {
    Query query = entityManager.createNativeQuery("SELECT date_trunc('" + unit + "', v.created_at) createdAt, " + 
      "max(v.upvotes) - max(v.downvotes) delta, " +
      "max(v.upvotes) upvotes, " +
      "max(v.downvotes) downvotes FROM " +
      "vote_snapshots v WHERE quote_id = :quoteId GROUP BY 1 ORDER BY createdAt DESC LIMIT :limit", Tuple.class);

    query.setParameter("quoteId", quoteId);
    query.setParameter("limit", limit);

    List<VoteHistoryItem> result = new ArrayList<>();
    
    @SuppressWarnings("unchecked")
    List<Tuple> tuples = query.getResultList();

    for (Tuple tuple: tuples) {
      VoteHistoryItem item = VoteHistoryItem.builder()
        .upvotes((BigInteger)tuple.get("upvotes"))
        .downvotes((BigInteger)tuple.get("downvotes"))
        .delta((BigInteger)tuple.get("delta"))
        .createdAt((Timestamp)tuple.get("createdAt"))
        .build();

      result.add(0, item);
    }

    return result;
  }
}