package com.us.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.us.entity.User;

;

public class AuditorAwareImpl implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		try {
			if(null != auth && null != auth.getPrincipal()) {
				User user = (User) auth.getPrincipal();
				return Optional.of(user.getUserId().toString());
			}
		} catch(Exception e) {
			return null;
		}
		return null;
	}
}
