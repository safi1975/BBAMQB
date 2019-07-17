package com.app.dataentry.services;

import com.app.dataentry.domain.UserDto;
import com.app.dataentry.model.User;
import com.app.dataentry.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getUsers() {
        List<UserDto> users = new ArrayList<>();
        for (User u : userRepository.findAll()) {
            users.add(new UserDto(u));
        }
        return users;
    }

    @Override
    @Transactional
    public User saveUser(UserDto userDto) {
        User entity = userDto.mapToEntity();
        if (StringUtils.isEmpty(entity.getId())) {
            entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
        } else if (!StringUtils.isEmpty(entity.getPassword())) {
            entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
        } else {
            entity.setPassword((userRepository.findById(entity.getId()).get().getPassword()));
        }
        return userRepository.save(entity);
    }
    
    @Override
    @Transactional
    public User saveUser(User entity) {
        return userRepository.save(entity);
    }

    @Override
    @Transactional
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public UserDto getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        return new UserDto(user.orElse(new User()));
    }
    
    @Override
    public User getUserByName(String name) {
    	return userRepository.findByName(name);
    }
}
