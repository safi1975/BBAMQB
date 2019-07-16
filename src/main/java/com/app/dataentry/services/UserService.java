package com.app.dataentry.services;

import com.app.dataentry.domain.UserDto;
import com.app.dataentry.model.User;

import java.util.List;

public interface UserService {

    List<UserDto> getUsers();

    User saveUser(UserDto userDto);

    boolean deleteClient(Long id);

    UserDto getUser(Long id);

}
