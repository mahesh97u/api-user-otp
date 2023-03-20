package com.us.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.us.entity.Role;
import com.us.repository.RoleRepository;
import com.us.service.RoleService;


@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepo;

	@Override
	public List<Role> getAllRoles() throws Exception {
		return roleRepo.findAll();
	}
}
