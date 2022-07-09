package com.technostack.account.client;

import ma.baridmedia.imdae.account.domain.User;
import ma.baridmedia.imdae.account.dto.RequestAuth;
import ma.baridmedia.imdae.account.dto.ResponseAuth;
import ma.baridmedia.imdae.account.dto.UserInfo;
import org.apache.http.HttpHeaders;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@FeignClient(name = "service-auth")
public interface RegisterServiceClient {

	@RequestMapping(method = RequestMethod.POST, value = "/api/user/register")
	void register(
			@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
			User payload);

	@RequestMapping(method = RequestMethod.POST, value = "/api/user/info")
	UserInfo getUserInfo(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader);


	@PostMapping(value = "/api/user/auth",
			produces = APPLICATION_JSON_VALUE,
			consumes = APPLICATION_JSON_VALUE)
	ResponseAuth auth(RequestAuth payload);
}