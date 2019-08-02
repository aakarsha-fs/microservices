package com.microservice.stock.dbservice.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.stock.dbservice.model.Quote;

public interface QuotesRepository extends JpaRepository<Quote, Integer> {
	List<Quote> findByUserName(String username);

}
