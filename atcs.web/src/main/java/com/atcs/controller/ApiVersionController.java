package com.atcs.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atcs.util.UrlPaths;

@RestController
public class ApiVersionController {
	
	@Value("${application.version}")
	private String currentApplicationVersion;

	@Value("${spring.profiles.active}")
	private String springActiveProfileInUse;

	 @RequestMapping (value=UrlPaths.API_VERSION,method = RequestMethod.GET)
	public synchronized ApiVersion getApiVersion() {
		return new ApiVersion(currentApplicationVersion, springActiveProfileInUse);
	}

	static public class ApiVersion {
		public final String apiVersion;
		public final String springActiveProfileInUse;

		public ApiVersion(String apiVersion, String springActiveProfileInUse) {
			this.apiVersion = apiVersion;
			this.springActiveProfileInUse = springActiveProfileInUse;
		}
	}
}