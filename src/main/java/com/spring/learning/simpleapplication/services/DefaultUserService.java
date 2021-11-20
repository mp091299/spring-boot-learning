package com.spring.learning.simpleapplication.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.learning.simpleapplication.dto.UserDto;
import com.spring.learning.simpleapplication.exceptions.UserAlreadyExistException;
import com.spring.learning.simpleapplication.models.UserEntity;
import com.spring.learning.simpleapplication.repositories.UserRepository;


@Service("userService")
public class DefaultUserService implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void register(UserDto user) throws UserAlreadyExistException {
		if(checkIfUserExist(user.getEmail())) {
			throw new UserAlreadyExistException("User already exist with this email");
		}
		UserEntity entity = new UserEntity();
		BeanUtils.copyProperties(user, entity);
		encodePassword(user, entity);
		userRepository.save(entity);
	}

	private void encodePassword(UserDto user, UserEntity entity) {
		entity.setPassword(passwordEncoder.encode(user.getPassword()));
		
	}

	@Override
	public boolean checkIfUserExist(String email) {
		
		return userRepository.findByEmail(email) != null?true:false;
	}

}
