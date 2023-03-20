package com.us.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.us.bo.UserBO;
import com.us.constants.ErrorConstants;
import com.us.dto.UserDTO;
import com.us.entity.User;
import com.us.exception.ResourceNotFoundException;
import com.us.exception.UsException;
import com.us.repository.UserRepository;
import com.us.security.JwtTokenHelper;
import com.us.service.UserService;
import com.us.utility.FileUtility;
import com.us.utility.Utils;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private com.us.utility.MailUtility mailUtility;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	
	
	@Autowired
	private FileUtility fileUtility;
	
	@Override
	public User userLogin(UserBO userBO) throws Exception {
		User checkUser = (User) userRepo.findByEmail(userBO.getEmail());
		if (checkUser == null) {
			throw new ResourceNotFoundException(ErrorConstants.NOT_FOUND.toString(), "Please enter valid username and password");
		}
		if (!passwordEncoder.matches(userBO.getPassword(), checkUser.getPassword())) {
			throw new UsException(ErrorConstants.INVALID.toString(), "Username or Password incorrect");
		}
		
		if(!checkUser.getEnableLogin()) {
			throw new UsException(ErrorConstants.INVALID.toString(), "Login is not enabled for this account");
		}
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				userBO.getEmail(), userBO.getPassword());
		authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		UserDetails userDetails = userDetailsService.loadUserByUsername(checkUser.getEmail());
		String generateToken = jwtTokenHelper.generateToken(userDetails);
		checkUser.setSessionToken(generateToken);
		checkUser.setPassword(null);
		return checkUser;
	}
	
	@Override
	public void addUser(User user) throws Exception {
		
		User checkEmail = (User) userRepo.findByEmail(user.getEmail());
		if(null != checkEmail) {
			throw new UsException(ErrorConstants.UNIQUE_KEY_EXISTS.toString(), "Official email already exists");
		}
		
		User checkMobile = (User) userRepo.findByMobile(user.getMobile());
		if(null != checkMobile) {
			throw new UsException(ErrorConstants.UNIQUE_KEY_EXISTS.toString(), "Mobile number already exists");
		}
		
		if(user.getEnableLogin()) {
			String password = Utils.randomAlphaNumeric(10);
			user.setPassword(passwordEncoder.encode(password));
			String message = "Dear User, \n Please user password " + password + " to login into system";
			//mailUtility.sendSimpleMessage(user.getEmail(), "Account Created", message);
		
		}
		user.setStatus(true);
		//userRepo.save(user);
	}

	


	@Override
	public List<UserDTO> getUsersForDropdown() throws Exception {
		return userRepo.getUsersForDropdown();
	}



	
	public UUID forgotPassword(UserBO userBO) throws Exception {
		User user = (User) userRepo.findByEmail(userBO.getEmail());
		if(null == user) {
			throw new UsException(ErrorConstants.INVALID.toString(), "Invalid email id");
		}
		String otp = Utils.generateRandomNumber(6);
		user.setOtp(otp);
		user.setOtpVerifyStatus(false);
		user.setOtpTime(new Date());
		//userRepo.save(user);
		mailUtility.sendSimpleMessage(userBO.getEmail(), "Password reset OTP",
				"Please use otp: " + otp + " to reset your account password");
		return user.getUserId();
	}

	@Override
	public void resetPassword(UserBO userBO) throws Exception {
		User user = getUserById(userBO.getUserId());
		if(!user.getOtp().equals(userBO.getOtp())) {
			throw new UsException(ErrorConstants.INVALID.toString(), "Invalid OTP");
		}
		if((new Date().getTime() - user.getOtpTime().getTime()) > 120000) {
			throw new UsException(ErrorConstants.INVALID.toString(), "OTP Expired");
		}
		if(user.getOtpVerifyStatus()) {
			throw new UsException(ErrorConstants.INVALID.toString(), "OTP already used");
		}
		user.setOtpVerifyStatus(true);
		user.setPassword(passwordEncoder.encode(userBO.getPassword()));
		//userRepo.save(user);
	}

	private User getUserById(UUID userId) throws ResourceNotFoundException {
		return (User) userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException(ErrorConstants.NOT_FOUND.toString(), "User Not Found"));
	}

	

}
