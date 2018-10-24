package com.anassbouassaba.quotes.controllers;

import java.util.List;
import java.util.stream.Collectors;

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

import com.anassbouassaba.quotes.dtos.CreateQuoteDto;
import com.anassbouassaba.quotes.dtos.QuoteDto;
import com.anassbouassaba.quotes.entities.Quote;
import com.anassbouassaba.quotes.repositories.QuoteRepository;
import com.anassbouassaba.quotes.repositories.UserRepository;

@RestController
@RequestMapping("quotes")
public class QuotesController {
  @Autowired
  QuoteRepository quoteRepository;
  
  @Autowired
  UserRepository userRepository;
  
  @PostMapping
  public QuoteDto create(@RequestBody CreateQuoteDto body) {    
    Quote quote = new Quote();
    quote.setContent(body.getContent());
    quote.setVotes(0);
    
    quoteRepository.save(quote);
        
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
    return new ResponseEntity<>(null, HttpStatus.OK);
  }
  
  @PostMapping("{id}/downvote")
  public ResponseEntity<?> downvote(@PathVariable("id") Long id) {
    quoteRepository.downvote(id);
    return new ResponseEntity<>(null, HttpStatus.OK);
  }
}
