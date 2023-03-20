package com.us.service;

import java.util.List;
import java.util.UUID;

import com.us.bo.UserBO;
import com.us.dto.UserDTO;
import com.us.entity.User;



public interface UserService {

	void addUser(User user) throws Exception;

	List<UserDTO> getUsersForDropdown() throws Exception;

	User userLogin(UserBO userBO) throws Exception;

	UUID forgotPassword(UserBO userBO) throws Exception;

	void resetPassword(UserBO userBO) throws Exception;

}
