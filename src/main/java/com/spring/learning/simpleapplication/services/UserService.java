package com.spring.learning.simpleapplication.services;

import com.spring.learning.simpleapplication.dto.UserDto;
import com.spring.learning.simpleapplication.exceptions.UserAlreadyExistException;

public interface UserService {

	public void register(UserDto user) throws UserAlreadyExistException;
	
	public boolean checkIfUserExist(String email);
}
