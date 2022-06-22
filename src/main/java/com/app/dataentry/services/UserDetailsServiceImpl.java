package com.app.dataentry.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.dataentry.repositories.UserRepository;
import org.springframework.util.StringUtils;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// hak to have username:code
		String[] split = username.split(":");
		String name = "";
		String code = "";
		if (split.length == 1) {
			name = split[0];
		} else if (split.length == 2) {
			name = split[0];
			code = split[1];
		}
		com.app.dataentry.model.User userEntity = userRepository.findByName(name.toLowerCase());
		if (userEntity == null || StringUtils.isEmpty(code) || !code.equals(code)) {
			throw new UsernameNotFoundException("Username or password incorrect");
		}
		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority(userEntity.getRole()));
		User user = new User(userEntity.getName(), userEntity.getPassword(), roles);
		return user;
	}

}
