package com.microservice.stock.dbservice.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.stock.dbservice.model.Quote;
import com.microservice.stock.dbservice.model.Quotes;
import com.microservice.stock.dbservice.respository.QuotesRepository;

@RestController
@RequestMapping("/")
public class DbServiceResource {

	private QuotesRepository quoteRepository;

	@Autowired
	public DbServiceResource(QuotesRepository quoteRepository) {
		this.quoteRepository = quoteRepository;
	}

	@GetMapping("/user/{username}")
	public String checkUser(@PathVariable("username") final String username) {
		List<Quote> quotes = quoteRepository.findByUserName(username);
		if (null == quotes || quotes.isEmpty()) {
			return "No user found.";
		}
		return "Hi " + username + "! Welcome to Stock Portal.";
	}

	@GetMapping("/{username}")
	public List<String> getQuotes(@PathVariable("username") final String username) {
		return getQuotesByUserName(username);
	}

	@PostMapping("/add")
	public List<String> add(@RequestBody final Quotes quotes) {
		quotes.getQuotes().stream().map(quote -> new Quote(quotes.getUserName(), quote))
				.forEach(quote -> quoteRepository.save(quote));
		return getQuotesByUserName(quotes.getUserName());
	}

	@PostMapping("/delete/{username}")
	public List<String> delete(@PathVariable("username") final String username) {
		List<Quote> quotes = quoteRepository.findByUserName(username);
		quoteRepository.delete(quotes);
		return getQuotesByUserName(username);
	}

	private List<String> getQuotesByUserName(@PathVariable("username") String username) {

		if (StringUtils.isEmpty(username)) {
			return quoteRepository.findAll().stream().map(Quote::getQuote).collect(Collectors.toList());
		}

		return quoteRepository.findByUserName(username).stream().map(Quote::getQuote).collect(Collectors.toList());
	}
}