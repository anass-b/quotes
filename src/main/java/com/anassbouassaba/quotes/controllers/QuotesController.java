package com.anassbouassaba.quotes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anassbouassaba.quotes.dtos.CreateQuoteDto;
import com.anassbouassaba.quotes.entities.Quote;
import com.anassbouassaba.quotes.entities.User;
import com.anassbouassaba.quotes.repositories.QuoteRepository;
import com.anassbouassaba.quotes.repositories.UserRepository;

@RepositoryRestController
public class QuotesController {
  @Autowired
  QuoteRepository quoteRepository;
  
  @Autowired
  UserRepository userRepository;
  
  @PostMapping("quotes")
  public @ResponseBody ResponseEntity<?> create(@RequestBody CreateQuoteDto body) {
    User user = userRepository.findById((long)1).get();
    
    Quote quote = new Quote();
    quote.setContent(body.getContent());
    quote.setUser(user);
    quote.setVotes(0);
    
    quoteRepository.save(quote);
        
    return ResponseEntity.ok(new Resource<>(quote));
  }
  
  @PostMapping("quotes/{id}/upvote")
  public ResponseEntity<?> upvote(@PathVariable("id") Long id) {
    quoteRepository.upvote(id);
    return new ResponseEntity<>(null, HttpStatus.OK);
  }
  
  @PostMapping("quotes/{id}/downvote")
  public ResponseEntity<?> downvote(@PathVariable("id") Long id) {
    quoteRepository.downvote(id);
    return new ResponseEntity<>(null, HttpStatus.OK);
  }
}
