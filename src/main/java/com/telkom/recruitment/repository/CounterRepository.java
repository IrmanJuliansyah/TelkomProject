package com.telkom.recruitment.repository;

import com.telkom.recruitment.domain.Counter;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CounterRepository extends MongoRepository<Counter,String>{
}
