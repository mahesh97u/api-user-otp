package com.us.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;

@Entity
@Table(name = "user")
public class User implements Serializable, UserDetails {

	@Id

	@GeneratedValue
	@Column(name = "user_id", length = 50)
	private UUID userId;

	@Column(name = "first_name", length = 50, nullable = false)
	private String firstName;

	@Column(name = "last_name", length = 50, nullable = false)
	private String lastName;

	@Column(name = "email", length = 50, nullable = false, unique = true)
	private String email;

	@Column(name = "mobile", length = 12, nullable = false, unique = true)
	private String mobile;

	@Column(name = "enable_login", columnDefinition = "BOOLEAN DEFAULT TRUE")
	private boolean enableLogin;

	@Column(name = "status", nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
	private boolean status;

	@JsonIgnore
	@Column(name = "password", length = 255, nullable = true)
	private String password;

	@ManyToOne(targetEntity = Role.class)
	@JoinColumn(name = "role_id", nullable = true)
	private Role role;

	@Column(name = "otp", length = 6, nullable = true)
	private String otp;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "otp_time")
	private Date otpTime;

	@Column(name = "otp_verify_status", columnDefinition = "BOOLEAN DEFAULT FALSE")
	private boolean otpVerifyStatus;

	@Transient
	private String sessionToken;

	public User() {
	}

	public User(UUID userId, String firstName, String lastName, String email, String mobile, Role role) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobile = mobile;
		this.role = role;
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean getEnableLogin() {
		return enableLogin;
	}

	public void setEnableLogin(boolean enableLogin) {
		this.enableLogin = enableLogin;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSessionToken() {
		return sessionToken;
	}

	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public Date getOtpTime() {
		return otpTime;
	}

	public void setOtpTime(Date otpTime) {
		this.otpTime = otpTime;
	}

	public boolean getOtpVerifyStatus() {
		return otpVerifyStatus;
	}

	public void setOtpVerifyStatus(boolean otpVerifyStatus) {
		this.otpVerifyStatus = otpVerifyStatus;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role.getName());
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(simpleGrantedAuthority);
		return authorities;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
