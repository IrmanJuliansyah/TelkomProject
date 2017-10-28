package com.telkom.recruitment.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.telkom.recruitment.domain.Role;


public interface RoleRepository extends MongoRepository<Role, String> {
	public Role findByRoleId(Long roleId);
	public Role findByIsDefault(Boolean isDefault);
}
