package com.app.dataentry.domain;

import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

import com.app.dataentry.model.User;

public class UserDto implements BaseDto<User> {
	
    private Long id;
	private String name;
	private String password; 
	private String role;
	private String mobileNo;
	private Boolean isLoggedIn;
	private LocalDateTime lastLoggedInAt;

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

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public void setIsLoggedIn(Boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public Boolean getIsLoggedIn() {
		return isLoggedIn;
	}

	public void setLastLoggedInAt(LocalDateTime lastLoggedInAt) {
		this.lastLoggedInAt = lastLoggedInAt;
	}

	public LocalDateTime getLastLoggedInAt() {
		return lastLoggedInAt;
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
		mobileNo = entity.getMobileNo();
		isLoggedIn = entity.getIsLoggedIn();
		lastLoggedInAt = entity.getLastLoggedInAt();
		return this;
	}

	@Override
	public User mapToEntity() {
		User entity = new User();
		if (!StringUtils.isEmpty(id)) {
			entity.setId(id);
        }
        entity.setName(name.toLowerCase());
		if (!StringUtils.isEmpty(password)) {
			entity.setPassword(password);
		}
        entity.setRole(role);
        entity.setMobileNo(mobileNo);
		return entity;
	}

}
