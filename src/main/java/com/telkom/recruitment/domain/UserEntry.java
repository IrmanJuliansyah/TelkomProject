package com.telkom.recruitment.domain;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class UserEntry {

	@NotNull(message = "Nama harus di isi")
	@NotEmpty(message = "Nama tidak boleh kosong")
	private String fullName;

	@NotNull(message = "Email harus di isi")
	@NotEmpty(message = "Email tidak boleh kosong")
	@Email(message = "Harap masukkan alamat email")
	private String email;

	@NotNull(message = "password harus di isi")
	@NotEmpty(message = "password tidak boleh kosong")
	private String password;
	
	public UserEntry() {}
	
	public UserEntry(String fullName, String email, String password) {
		this.fullName = fullName;
		this.email = email;
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


}
