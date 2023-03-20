package com.us.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.us.entity.Role;



public interface RoleRepository extends JpaRepository<Role, UUID> {

	Role findByName(String string) throws Exception;

}
