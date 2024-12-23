package com.example.demo.SecurityConfig;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Users;
import com.example.demo.Repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user=userRepository.findByEmail(username);
		if(user==null) {
			throw new UsernameNotFoundException(username);
			
		}
		
		List<GrantedAuthority> authList=new ArrayList<>();
		
		
		return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authList);
	}
}
