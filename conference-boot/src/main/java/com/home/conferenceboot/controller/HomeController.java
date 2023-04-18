package com.home.conferenceboot.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ManagedResource
@RestController
public class HomeController {
	
	@Value("${app.version}")
	private String appVersion;
	
	@ManagedOperation
	@GetMapping
	@RequestMapping("/")
	public Map<String, String> getStatus() {
		Map<String, String> map = new HashMap<>();
		map.put("app-version", appVersion);
		return map;
	}

}
