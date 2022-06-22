package com.app.dataentry.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "app_user")
public class User implements BaseEntity {

	@Id
	@SequenceGenerator(sequenceName = "app_user_seq", name = "app_user_seq_id", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "app_user_seq_id")
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "password")
	private String password;

	@Column(name = "role")
	private String role;

	@Column(name = "mobile_no")
	private String mobileNo;

	@Column(name = "code")
	private String code;

	@Column(name = "is_logged_in")
	private Boolean is_logged_in;

	@Column(name = "last_logged_in_at")
	private LocalDateTime last_logged_in_at;

	public void setIsLoggedIn(boolean is_logged_in) {
		this.is_logged_in = is_logged_in;
	}

	public Boolean getIsLoggedIn() {
		return is_logged_in;
	}

	public void setLastLoggedInAt(LocalDateTime last_logged_in_at) {
		this.last_logged_in_at = last_logged_in_at;
	}

	public LocalDateTime getLastLoggedInAt() {
		return last_logged_in_at;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

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
}
