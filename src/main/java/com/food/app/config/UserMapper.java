package com.food.app.config;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.food.app.dto.UserDto;
import com.food.app.model.User;

@Mapper
public interface UserMapper {
	
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	UserDto userToUserDto(User user);
	
	@Mapping(target="createdDate", ignore=true)
	@Mapping(target="userId", ignore=true)
	@Mapping(target="userRole", ignore=true)
	User userDtoToUser(UserDto userDto);

}
