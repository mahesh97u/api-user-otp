package com.us.entity;

import java.io.Serializable;
import java.util.UUID;


import org.hibernate.annotations.Type;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "role")
public class Role  implements Serializable {

	

	@Id
	
	@GeneratedValue
	@Column(name = "role_id", length = 50)
	private UUID roleId;
	
	@Column(name = "name", length = 50, nullable = false)
	private String name;
	
	public UUID getRoleId() {
		return roleId;
	}

	public void setRoleId(UUID roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
