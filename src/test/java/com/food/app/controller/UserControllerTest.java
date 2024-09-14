package com.food.app.controller;

import com.food.app.dto.Login;
import com.food.app.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
 class UserControllerTest {

    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;

    @Test
    void testString() {
        String test = " its working!";
        String test1 = userController.testString();
        assertEquals(test, test1);

    }

@Test
 void testUserLogin() {

    Login login = new Login();
    login.setEmail("test@example.com");

    ResponseEntity<String> responseEntity = userController.userLogin(login);
    assertEquals(HttpStatus.FOUND, responseEntity.getStatusCode());
    assertEquals(userService.userLogin(login), responseEntity.getBody());
}
@Test
 void testDeleteUser() {

    String email = "test@example.com";
    ResponseEntity<String> responseEntity = userController.deleteUser(email);
    assertEquals(HttpStatus.FOUND, responseEntity.getStatusCode());
    assertEquals(userService.deleteUser(email), responseEntity.getBody());
}
}
