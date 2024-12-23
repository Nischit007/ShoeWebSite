package com.example.demo.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Users;
import com.example.demo.Repository.UserRepository;
import com.example.demo.SecurityConfig.JwtProvider;




@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public Users findUserprofileByJwt(String jwt) throws Exception {
		String email=JwtProvider.getEmailFromToken(jwt);
		Users authUser= userRepository.findByEmail(email);
		if(authUser==null) throw new Exception("User not found");
		return authUser;
	}

}