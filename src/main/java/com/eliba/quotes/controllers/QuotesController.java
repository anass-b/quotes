package com.eliba.quotes.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.eliba.quotes.dtos.CreateQuoteDto;
import com.eliba.quotes.dtos.EditQuoteDto;
import com.eliba.quotes.dtos.QuoteDto;
import com.eliba.quotes.dtos.VoteHistoryItemDto;
import com.eliba.quotes.entities.Quote;
import com.eliba.quotes.entities.User;
import com.eliba.quotes.entities.VoteSnapshot;
import com.eliba.quotes.mappers.VoteHistoryItem;
import com.eliba.quotes.repositories.QuoteRepository;
import com.eliba.quotes.repositories.UserRepository;
import com.eliba.quotes.repositories.VoteSnaphotRepository;
import com.eliba.quotes.services.QuoteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

  @Autowired
  private QuoteService quoteService;
  
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

  @PostMapping("{id}/edit")
  public ResponseEntity<?> edit(
    @PathVariable("id") Long id,
    @Valid @RequestBody EditQuoteDto body, Principal principal) {
    User user = userRepository.findByUsername(principal.getName());
    Optional<Quote> quote = quoteRepository.findById(id);
    if (quote.isPresent()) {
      if (quote.get().getUser().getId() == user.getId()) {
        quote.get().setContent(body.getContent());
        quoteRepository.save(quote.get());
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
      } else {
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
      }
    } else {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("{id}")
  public ResponseEntity<?> delete(@PathVariable("id") Long id, Principal principal) {
    User user = userRepository.findByUsername(principal.getName());
    Optional<Quote> quote = quoteRepository.findById(id);
    if (quote.isPresent()) {
      if (quote.get().getUser().getId() == user.getId()) {
        quoteRepository.delete(quote.get());
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
      } else {
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
      }
    } else {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
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

    List<VoteHistoryItem> voteHistory = quoteService.getVoteHistory(id, unit.trim().toLowerCase(), limit);

    List<VoteHistoryItemDto> dtos = voteHistory.stream()
      .map(x -> new VoteHistoryItemDto(x))
      .collect(Collectors.toList());

    return new ResponseEntity<>(dtos, HttpStatus.OK);
  }
}
