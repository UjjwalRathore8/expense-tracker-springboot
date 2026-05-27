package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.LoginRequestDto;
import com.example.demo.dto.LoginResponseDto;
import com.example.demo.entity.User;
import com.example.demo.exception.InvalidCredentialsException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtUtil;

@Service
public class UserService {
	@Autowired
	UserRepository userrepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtUtil jwtUtil;
	
	public User addUser (User user)
	{
		user.setPassword(passwordEncoder.encode(user.getPassword())); 
		return userrepository.save(user);
	}

	
	public User getUserById(long id)
	{
		User s = userrepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found"));
		return s;
	}
	public List<User> getAllUsers()
	{
		List<User> s = userrepository.findAll();
		return s;
	}
	public User updateUser(User user , long id)
	{
		User existingUser = userrepository.findById(id).orElseThrow(()-> new 
				ResourceNotFoundException("User not found"));
		existingUser.setEmail(user.getEmail());
		existingUser.setName(user.getName());
		 if (user.getPassword() != null && !user.getPassword().isBlank()) {
		        existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
		    }
        return userrepository.save(existingUser);	
}
	public String deleteUser(long id)
	{
		userrepository.findById(id).orElseThrow(()-> new 
				ResourceNotFoundException ("User not found")); 
		userrepository.deleteById(id);
		return "Deleted Successfully";
	}
	public List<User> getAll()
	{
		List<User> allUser = userrepository.findAll();
		return allUser;
	}
	
	public User userById(Long id)
	{
		User user = userrepository.findById(id).orElseThrow(()-> new 
				ResourceNotFoundException("User not found"));
		return user;
	}
	
	public LoginResponseDto loginUser(LoginRequestDto loginDto)
	{
	    User user = userrepository.findByEmail(loginDto.getEmail())
	            .orElseThrow(() ->
	                    new ResourceNotFoundException("User not found"));

	    boolean isPasswordMatch = passwordEncoder.matches(
	            loginDto.getPassword(),
	            user.getPassword()
	    );

	    if(!isPasswordMatch)
	    {
	        throw new InvalidCredentialsException("Invalid password");
	    }

	    String token = jwtUtil.generateToken(user.getEmail(), user.getId());

	    return new LoginResponseDto(
	            "Login successful",
	            user.getId(),
	            user.getEmail(),
	            token
	    );
	}
	
}

