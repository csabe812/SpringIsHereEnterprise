package com.spring.is.here.service;

import com.spring.is.here.domain.User;

public interface UserService {
	
	public String registerUser(User user);

	public User findByEmail(String email);

	public String userActivation(String code);
}
