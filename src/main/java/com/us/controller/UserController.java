package com.us.controller;

import java.util.UUID;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.us.bo.Response;
import com.us.bo.UserBO;
import com.us.constants.ErrorConstants;
import com.us.entity.User;
import com.us.service.UserService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@CrossOrigin
	@PostMapping("/login")
	public ResponseEntity<Response> userLogin(@RequestBody UserBO userBO) throws Exception {
		Response response = new Response();
		response.setStatus(ErrorConstants.SUCCESS.toString());
		response.setMessage("Logged In Successfully");
		response.setResult(userService.userLogin(userBO));
		return new ResponseEntity<Response>(response, HttpStatus.CREATED);
	}
	
	@CrossOrigin
	@PostMapping("/{forgot-password}")
	public ResponseEntity<Response> forgotPassword(@RequestBody UserBO userBO) throws Exception {
		Response response = new Response();
		response.setStatus(ErrorConstants.SUCCESS.toString());
		response.setMessage("OTP sent on email");
		response.setResult(userService.forgotPassword(userBO));
		return new ResponseEntity<Response>(response, HttpStatus.CREATED);
	}
	
	@CrossOrigin
	@PostMapping("/reset-password")
	public ResponseEntity<Response> resetPassword(@RequestBody UserBO userBO) throws Exception {
		userService.resetPassword(userBO);
		Response response = new Response();
		response.setStatus(ErrorConstants.SUCCESS.toString());
		response.setMessage("Password Updated Successfully");
		return new ResponseEntity<Response>(response, HttpStatus.CREATED);
	}
	
	//@PreAuthorize("hasAnyAuthority('ADMIN')")
	@CrossOrigin
	@PostMapping
	public ResponseEntity<Response> addUser(@Valid @RequestBody User user) throws Exception {
		userService.addUser(user);
		Response response = new Response();
		response.setStatus(ErrorConstants.SUCCESS.toString());
		response.setMessage("User Added Successfully");
		return new ResponseEntity<Response>(response, HttpStatus.CREATED);
	}


	@CrossOrigin
	@GetMapping("/dropdown")          
	public ResponseEntity<Response> getUsersForDropdown() throws Exception {
		Response response = new Response();
		response.setResult(userService.getUsersForDropdown());
		response.setStatus(ErrorConstants.SUCCESS.toString());
		response.setMessage("User Details");
		return ResponseEntity.ok(response);
	}
	
	
	
	
}
