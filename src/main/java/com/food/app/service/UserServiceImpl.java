package com.food.app.service;

import java.time.LocalDateTime;
import java.util.Optional;

import com.food.app.dto.Login;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@NoArgsConstructor
@AllArgsConstructor
public class UserServiceImpl implements UserService{
	
	Logger logger  = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private UserRepository userRepository;

	@Override
	public UserDto createUser(UserDto userDto) {
		
		logger.info("UserServiceImpl class and createUser method");
		
		Optional<User> user = userRepository.findByEmail(userDto.getEmail());
		if(user.isPresent()) {
			logger.info("user is already exist with this email id {} :", user.get().getEmail());
			throw new UserExistException("User is already enrolled with this mail!");
		}
		//convert user to userDto using mapper
		User userObj = UserMapper.INSTANCE.userDtoToUser(userDto);

		userObj.setUserRole(UserRole.CUSTOMER);
		userObj.setCreatedDate(LocalDateTime.now());
		
		User userRes =  userRepository.saveAndFlush(userObj);
		return UserMapper.INSTANCE.userToUserDto(userRes);
	}

	@Override
	public String userLogin(Login login) {
		logger.info("UserServiceImpl class and userLogin method {} :", login.getEmail());
		User user = userRepository.findByEmailAndPassword(login.getEmail(), login.getPassword());
		if(user==null){
			throw new UserExistException("Invalid credentials, Please try again!");
		}
		return "Welcome to FoodApp {} : "+user.getUserName();
	}

	@Override
	public String deleteUser(String email) {
		logger.info("UserServiceImpl class and deleteUser method {} :", email);
		Optional<User> user = userRepository.findByEmail(email);
		if(user.isEmpty()) {
			logger.info("user is not exist with this email id {} :", user.get().getEmail());
			throw new UserExistException("User is not exist with this mail!");
		}
		userRepository.delete(user.get());
		return "Successfully deleted the user with this email {} :"+user.get().getEmail();
	}

}
