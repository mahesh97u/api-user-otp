package com.us.config;

import java.util.TimeZone;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

@Configuration
public class TimeZoneConfig {

	@Value("${default.timezone}")
	private String defaultTimeZone;
	
	@PostConstruct
	void started() {
	  TimeZone.setDefault(TimeZone.getTimeZone(defaultTimeZone));
	}
}
