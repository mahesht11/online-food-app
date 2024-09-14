package com.food.app.service;

import com.food.app.dto.Login;
import com.food.app.dto.UserDto;

public interface UserService {

	UserDto createUser(UserDto userDto);

    String userLogin(Login login);

    String deleteUser(String email);
}
