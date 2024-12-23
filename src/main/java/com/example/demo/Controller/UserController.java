package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.UserDTO;
import com.example.demo.Entity.Users;
import com.example.demo.Service.UserService;

@RestController
@RequestMapping("/auth")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/user")
	public UserDTO getUserData(@RequestHeader("Authorization") String jwt) throws Exception {
		Users user=userService.findUserprofileByJwt(jwt);
		UserDTO userDto=new UserDTO();
		userDto.setFullName(user.getFullName());
		userDto.setEmail(user.getEmail());
		userDto.setMobileNumber(user.getMobileNumber());
		userDto.setAddress(user.getAddress());
		return userDto;
	}
	
	

}
