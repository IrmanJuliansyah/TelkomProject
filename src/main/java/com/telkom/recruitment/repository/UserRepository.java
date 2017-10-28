package com.telkom.recruitment.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.telkom.recruitment.domain.User;


public interface UserRepository extends MongoRepository<User, String> {
	public User findByUserId(String userId);
	public User findByEmail(String email);
}