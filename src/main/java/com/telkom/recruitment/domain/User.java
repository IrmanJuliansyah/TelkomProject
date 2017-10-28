package com.telkom.recruitment.domain;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotNull;


@Document(collection = "tb_user")
public class User extends BaseEntity {
	
	@Indexed
	private String userId;


	private String fullName;

	@Indexed(name="email", unique=true)
	@NotNull(message = "Email harus di isi")
	@NotEmpty(message = "Email tidak boleh kosong")
	@Email(message = "Harap masukkan alamat email")
	private String email;

	private String nameCorp;

	private String deskripsiCorp;

	private String password;

	private Long roleId;
	
	public User() {}
	
	public User(UserEntry entry) {
		super();
		this.fullName = entry.getFullName();
		this.email = entry.getEmail();
		this.password = entry.getPassword();

	}

	public User(CorporateEntry corporateEntry) {
		super();
		this.fullName = corporateEntry.getFullName();
		this.email = corporateEntry.getEmail();
		this.nameCorp = corporateEntry.getNameCorp();
		this.deskripsiCorp = corporateEntry.getDeskripsiCorp();
		this.password = corporateEntry.getPassword();
	}

	public User(String id, String userId, String fullName, String email, String nameCorp, String deskripsiCorp
				, String password, Long roleId) {
		super();
		this.setId(id);
		this.userId = userId;
		this.fullName = fullName;
		this.email = email;
		this.nameCorp = nameCorp;
		this.deskripsiCorp = deskripsiCorp;
		this.password = password;
		this.roleId = roleId;
	}

	public User(String userId, String fullName, String email, String nameCorp, String deskripsiCorp,
			String password,Long roleId) {
		super();
		this.userId = userId;
		this.fullName = fullName;
		this.email = email;
		this.nameCorp = nameCorp;
		this.deskripsiCorp =deskripsiCorp;
		this.password = password;
		this.roleId = roleId;
	}

	public User(User user) {
		super();
		this.userId = user.userId;
		this.fullName = user.fullName;
		this.email = user.email;
		this.nameCorp = user.nameCorp;
		this.deskripsiCorp = user.deskripsiCorp;
		this.password = user.password;
		this.roleId = user.roleId;
		this.setId(user.getId());
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getNameCorp() {
		return nameCorp;
	}

	public void setNameCorp(String nameCorp) {
		this.nameCorp = nameCorp;
	}

	public String getDeskripsiCorp() {
		return deskripsiCorp;
	}

	public void setDeskripsiCorp(String deskripsiCorp) {
		this.deskripsiCorp = deskripsiCorp;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}
