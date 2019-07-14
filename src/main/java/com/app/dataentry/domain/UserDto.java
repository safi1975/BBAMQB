package com.app.dataentry.domain;

import org.springframework.util.StringUtils;

import com.app.dataentry.model.User;

public class UserDto implements BaseDto<User> {
	
    private Long id;
	private String name;
	private String password; 
	private String role;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public UserDto() {
		
	}
	
public UserDto(User entity) {
		mapFromEntity(entity);
	}

	@Override
	public BaseDto<User> mapFromEntity(User entity) {
		id = entity.getId();
		name = entity.getName(); 
		password = entity.getPassword();
		role = entity.getRole();
		return this;
	}

	@Override
	public User mapToEntity() {
		User entity = new User();
		if (!StringUtils.isEmpty(id)) {
			entity.setId(id);
        }
        entity.setName(name);
        entity.setPassword(password);
        entity.setRole(role);
		return entity;
	}

}
