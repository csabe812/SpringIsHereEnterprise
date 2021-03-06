package com.spring.is.here.service;

import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.is.here.domain.Role;
import com.spring.is.here.domain.User;
import com.spring.is.here.repository.RoleRepository;
import com.spring.is.here.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	private final String USER_ROLE = "ADMIN";
	private final String SHOP_OWNER_ROLE = "SHOP_OWNER";

	@Autowired
	public UserServiceImpl(UserRepository userRepository,
			RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}

		return new UserDetailsImpl(user);
	}

	@Override
	public String registerUser(User userToRegister) {
		User userCheck = userRepository.findByEmail(userToRegister.getEmail());

		if (userCheck != null)
			return "alreadyExists";

		Role userRole = roleRepository.findByRole(USER_ROLE);
		if (userRole != null) {
			userToRegister.getRoles().add(userRole);
		} else {
			userToRegister.addRoles(USER_ROLE);
		}

		userToRegister.setEnabled(false);
		userToRegister.setActivation(generateKey());

		userToRegister.setPassword(bCryptPasswordEncoder.encode(userToRegister.getPassword()));
        log.info("NEW USER: " + userToRegister.toString());
		userRepository.save(userToRegister);

		return "ok";
	}

	public String generateKey() {
		String key = "";
		Random random = new Random();
		char[] word = new char[16];
		for (int j = 0; j < word.length; j++) {
			word[j] = (char) ('a' + random.nextInt(26));
		}
		String toReturn = new String(word);
		log.info("random code: " + toReturn);
		return new String(word);
	}

	@Override
	public String userActivation(String code) {
		User user = userRepository.findByActivation(code);
		if (user == null)
			return "noresult";

		user.setEnabled(true);
		user.setActivation("");
		userRepository.save(user);
		return "ok";
	}

	public void registerShopOwner(User user) {
		Role userRole = roleRepository.findByRole(SHOP_OWNER_ROLE);
		if (userRole != null) {
			user.getRoles().add(userRole);
			log.info(userRole.toString());
		} else {
			user.addRoles(SHOP_OWNER_ROLE);
		}
		
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        log.info("NEW USER: " + user.toString());
		this.userRepository.save(user);
	}
	
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

}
