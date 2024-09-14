package com.food.app.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.app.config.UserMapper;
import com.food.app.dto.UserDto;
import com.food.app.exception.UserExistException;
import com.food.app.model.User;
import com.food.app.model.UserRole;
import com.food.app.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService{
	
	Logger logger  = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDto createUser(UserDto userDto) {
		
		logger.info("UserServiceImpl class and createUser method");
		
		Optional<User> user = userRepository.findByEmail(userDto.getEmail());
		if(user.isPresent()) {
			throw new UserExistException("User is already enrolled with this mail!");
		}
		
		
		User userObj = UserMapper.INSTANCE.userDtoToUser(userDto);

		userObj.setUserRole(UserRole.CUSTOMER);
		userObj.setCreatedDate(LocalDateTime.now());
		
		User userRes =  userRepository.saveAndFlush(userObj);
		
		
		return UserMapper.INSTANCE.userToUserDto(userRes);
	}

}
