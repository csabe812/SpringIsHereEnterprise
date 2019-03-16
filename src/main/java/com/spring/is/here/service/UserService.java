package com.spring.is.here.service;

import com.spring.is.here.domain.User;

public interface UserService {

	public User findByEmail(String email);

	public void registerUser(User user);
}
