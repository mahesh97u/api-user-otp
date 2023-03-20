package com.us.repository;

import java.util.List;
import java.util.UUID;

import org.apache.catalina.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.us.dto.UserDTO;


public interface UserRepository extends JpaRepository<User, UUID> {

	Page<User> findByFirstNameContaining(String searchBy, Pageable pageable) throws Exception;

	@Query("SELECT u.userId AS userId, u.firstName AS firstName, u.lastName AS lastName, u.email AS email,"
			+ " u.mobile AS mobile, u.role.name AS role FROM User u ")
	List<UserDTO> getUsersForDropdown() throws Exception;

	User findByEmail(String email) throws UsernameNotFoundException;

	User findByMobile(String mobile) throws Exception;

}
