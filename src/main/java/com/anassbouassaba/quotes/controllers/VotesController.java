package com.anassbouassaba.quotes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.anassbouassaba.quotes.repositories.QuoteRepository;

@RestController
public class VotesController {
  @Autowired
  QuoteRepository quoteRepository;
}
