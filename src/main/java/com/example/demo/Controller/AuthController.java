package com.example.demo.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Users;
import com.example.demo.Enum.Role;
import com.example.demo.Repository.UserRepository;
import com.example.demo.SecurityConfig.CustomUserDetailsService;
import com.example.demo.SecurityConfig.JwtProvider;

@RestController
@RequestMapping("/auth")
public class AuthController {
	


	@Autowired
	private UserRepository userRepository;
	

	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	

	
	@PostMapping("/register")
	public ResponseEntity<Map<String, String>> register(@RequestBody Users user) throws Exception {

	   
	    Users isEmailExist = userRepository.findByEmail(user.getEmail());

	    if (isEmailExist != null) {
	        throw new Exception("Email is already used with another account");
	    }

	  
	    Users savedUser = userRepository.save(user);

	
	    Authentication auth = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
	    SecurityContextHolder.getContext().setAuthentication(auth);

	   
	    String jwt = JwtProvider.generateToken(auth);

	  
	    Map<String, String> response = new HashMap<>();
	    response.put("jwt", jwt);

	    return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> login(@RequestBody Users user) throws Exception {
	    System.out.println("loginn.......");
	    String userName = user.getEmail();
	    String password = user.getPassword();

	    Authentication auth = authenticate(userName, password);
	    SecurityContextHolder.getContext().setAuthentication(auth);

	    String jwt = JwtProvider.generateToken(auth);

	    Map<String, String> response = new HashMap<>();
	    response.put("jwt", jwt);

	    return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	
	@PostMapping("/login/admin")
	public ResponseEntity<Map<String, String>> adminLogin(@RequestBody Users user) throws Exception {
	    System.out.println("loginn.......");
	    String userName = user.getEmail();
	    String password = user.getPassword();

	    Authentication auth = authenticate(userName, password);
	    Users authUser=userRepository.findByEmail(userName);
	    if(!authUser.getRole().equals(Role.ADMIN)) throw new Exception("admin can only access this route");
	    SecurityContextHolder.getContext().setAuthentication(auth);

	    String jwt = JwtProvider.generateToken(auth);

	    Map<String, String> response = new HashMap<>();
	    response.put("jwt", jwt);

	    return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	
	


	private Authentication authenticate(String userName, String password) {
		UserDetails userDetails =customUserDetailsService.loadUserByUsername(userName);
		if(userDetails==null) {
			throw new BadCredentialsException("invalid username");
		}
		if(!password.equals(userDetails.getPassword())) throw new BadCredentialsException("invalid password"); 
		
		return new UsernamePasswordAuthenticationToken(userDetails,password, userDetails.getAuthorities());
	}	
}
