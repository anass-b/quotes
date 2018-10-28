package com.anassbouassaba.quotes.controllers;

import java.math.BigInteger;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.validation.Valid;

import com.anassbouassaba.quotes.dtos.CreateQuoteDto;
import com.anassbouassaba.quotes.dtos.QuoteDto;
import com.anassbouassaba.quotes.dtos.VoteHistoryItemDto;
import com.anassbouassaba.quotes.entities.Quote;
import com.anassbouassaba.quotes.entities.User;
import com.anassbouassaba.quotes.entities.VoteSnapshot;
import com.anassbouassaba.quotes.mappers.VoteHistoryItem;
import com.anassbouassaba.quotes.repositories.QuoteRepository;
import com.anassbouassaba.quotes.repositories.UserRepository;
import com.anassbouassaba.quotes.repositories.VoteSnaphotRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("quotes")
public class QuotesController {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private QuoteRepository quoteRepository;
  
  @Autowired
  private VoteSnaphotRepository voteCountRepository;

  @PersistenceContext
  private EntityManager entityManager;
  
  @PostMapping
  public QuoteDto create(@Valid @RequestBody CreateQuoteDto body, Principal principal) {
    User user = userRepository.findByUsername(principal.getName());

    Quote quote = new Quote();
    quote.setContent(body.getContent());
    quote.setUser(user);
    
    quoteRepository.save(quote);
    
    VoteSnapshot voteCount = new VoteSnapshot();
    voteCount.setQuote(quote);
    voteCountRepository.save(voteCount);

    return new QuoteDto(quote);
  }
  
  @GetMapping("top10")
  public List<QuoteDto> top10() {
    return quoteRepository.top(PageRequest.of(0, 10))
        .stream()
        .map(x -> new QuoteDto(x))
        .collect(Collectors.toList());
  }
  
  @GetMapping("flop10")
  public List<QuoteDto> flop10() {
    return quoteRepository.flop(PageRequest.of(0, 10))
        .stream()
        .map(x -> new QuoteDto(x))
        .collect(Collectors.toList());
  }
  
  @GetMapping("latest") ResponseEntity<QuoteDto> latest() {
    Quote latest = quoteRepository.findTopByOrderByCreatedAtDesc(); 
    if (latest != null) {
      return new ResponseEntity<QuoteDto>(new QuoteDto(latest), HttpStatus.OK);
    } else {
      return new ResponseEntity<QuoteDto>(HttpStatus.NOT_FOUND);
    }
  }
  
  @PostMapping("{id}/upvote")
  public ResponseEntity<?> upvote(@PathVariable("id") Long id) {
    quoteRepository.upvote(id);
    
    Optional<Quote> quote = quoteRepository.findById(id);
    
    VoteSnapshot voteCount = new VoteSnapshot(quote.get());
    voteCountRepository.save(voteCount);
    
    return new ResponseEntity<>(null, HttpStatus.OK);
  }
  
  @PostMapping("{id}/downvote")
  public ResponseEntity<?> downvote(@PathVariable("id") Long id) {    
    quoteRepository.downvote(id);
    
    Optional<Quote> quote = quoteRepository.findById(id);
    
    VoteSnapshot voteCount = new VoteSnapshot(quote.get());
    voteCountRepository.save(voteCount);

    return new ResponseEntity<>(null, HttpStatus.OK);
  }
  
  @GetMapping("{id}/vote-history/{unit}/{limit}")
  public ResponseEntity<?> voteHistory(
    @PathVariable("id") Long id,
    @PathVariable("unit") String unit,
    @PathVariable("limit") Integer limit) {
    // Here we explicitly check the value of unit to avoid an SQL injection
    if (!unit.equalsIgnoreCase("year") &&
        !unit.equalsIgnoreCase("month") &&
        !unit.equalsIgnoreCase("week") &&
        !unit.equalsIgnoreCase("day") &&
        !unit.equalsIgnoreCase("hour") &&
        !unit.equalsIgnoreCase("minute") &&
        !unit.equalsIgnoreCase("second")) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    List<VoteHistoryItem> voteHistory = getVoteHistory(id, unit.trim().toLowerCase(), limit);

    List<VoteHistoryItemDto> dtos = voteHistory.stream()
      .map(x -> new VoteHistoryItemDto(x))
      .collect(Collectors.toList());

    return new ResponseEntity<>(dtos, HttpStatus.OK);
  }

  private List<VoteHistoryItem> getVoteHistory(Long quoteId, String unit, Integer limit) {
    Query query = entityManager.createNativeQuery("SELECT date_trunc('" + unit + "', v.created_at) createdAt, " + 
      "max(v.upvotes) - max(v.downvotes) delta, " +
      "max(v.upvotes) upvotes, " +
      "max(v.downvotes) downvotes FROM " +
      "vote_snapshot v WHERE quote_id = :quoteId GROUP BY 1 ORDER BY createdAt DESC LIMIT :limit", Tuple.class);

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
