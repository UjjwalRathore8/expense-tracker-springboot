package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginRequestDto;
import com.example.demo.dto.LoginResponseDto;
import com.example.demo.dto.UserRequestDto;
import com.example.demo.dto.UserResponseDto;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired 
	UserService userservice;
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<String> deleteById(@PathVariable long id)
	{
		String response = userservice.deleteUser(id);
		return ResponseEntity.ok(response);
	}
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> loginUser(
	        @Valid @RequestBody LoginRequestDto loginDto)
	{
	    LoginResponseDto response = userservice.loginUser(loginDto);
	    return ResponseEntity.ok(response);
	}
	
	@PostMapping("/users")
	public ResponseEntity<?> registerUser(
	        @Valid @RequestBody UserRequestDto userDto)
	{
	    User user = new User();
	    user.setName(userDto.getName());
	    user.setEmail(userDto.getEmail());
	    user.setPassword(userDto.getPassword());

	    User savedUser = userservice.addUser(user);

	    UserResponseDto responseDto =
	            new UserResponseDto(
	                    savedUser.getId(),
	                    savedUser.getName(),
	                    savedUser.getEmail()
	            );

	    return ResponseEntity.ok(responseDto);
	}
	@GetMapping("/users/{id}")
	public ResponseEntity<UserResponseDto> getUserById(
	        @PathVariable Long id)
	{
	    User user = userservice.userById(id);

	    UserResponseDto responseDto =
	            new UserResponseDto(
	                    user.getId(),
	                    user.getName(),
	                    user.getEmail()
	            );

	    return ResponseEntity.ok(responseDto);
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<UserResponseDto>> getAllUser()
	{
	    List<User> users = userservice.getAllUsers();

	    List<UserResponseDto> response = users.stream()
	            .map(user -> new UserResponseDto(
	                    user.getId(),
	                    user.getName(),
	                    user.getEmail()
	            ))
	            .toList();

	    return ResponseEntity.ok(response);
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<UserResponseDto> updateById(
	        @Valid @RequestBody UserRequestDto userDto,
	        @PathVariable Long id)
	{
	    User user = new User();

	    user.setName(userDto.getName());
	    user.setEmail(userDto.getEmail());
	    user.setPassword(userDto.getPassword());

	    User updatedUser = userservice.updateUser(user, id);

	    UserResponseDto responseDto =
	            new UserResponseDto(
	                    updatedUser.getId(),
	                    updatedUser.getName(),
	                    updatedUser.getEmail()
	            );

	    return ResponseEntity.ok(responseDto);
	}
}
