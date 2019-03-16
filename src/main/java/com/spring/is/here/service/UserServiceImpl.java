package com.spring.is.here.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.is.here.domain.Role;
import com.spring.is.here.domain.User;
import com.spring.is.here.repository.RoleRepository;
import com.spring.is.here.repository.UserRepository;

import jdk.internal.jline.internal.Log;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private UserRepository userRepository;
	private RoleRepository roleRepository;

	private final String USER_ROLE = "user";

	@Autowired
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("Not found");
		}
		return new UserDetailsImpl(user);
	}

	@Override
	public void registerUser(User user) {
		Role userRole = roleRepository.findByRole(USER_ROLE);
		if (userRole != null) {
			user.getRoles().add(userRole);
			log.info(userRole.toString());
		} else {
			user.addRoles(USER_ROLE);
		}
		User u = this.userRepository.save(user);
	}

}
