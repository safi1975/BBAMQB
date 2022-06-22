package com.app.dataentry.services;

import com.app.dataentry.domain.UserDto;
import com.app.dataentry.model.User;

import java.util.List;

public interface UserService {

    List<UserDto> getUsers();

    List<UserDto> getLoggedInOperators();

    User saveUser(UserDto userDto);

    boolean deleteUser(Long id);

    UserDto getUser(Long id);

    User getUserByName(String name);

    User saveUser(User entity);

}
