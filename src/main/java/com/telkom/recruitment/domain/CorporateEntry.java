package com.telkom.recruitment.domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class CorporateEntry {

    @NotNull(message = "Nama harus di isi")
    @NotEmpty(message = "Nama tidak boleh kosong")
    private String fullName;

    @NotNull(message = "Email harus di isi")
    @NotEmpty(message = "Email tidak boleh kosong")
    @Email(message = "Harap masukkan alamat email")
    private String email;

    @NotNull(message = "NamaCorp harus di isi")
    @NotEmpty(message = "NamaCorp tidak boleh kosong")
    private String nameCorp;

    private String deskripsiCorp;

    @NotNull(message = "password harus di isi")
    @NotEmpty(message = "password tidak boleh kosong")
    private String password;


    public CorporateEntry() {
    }

    public CorporateEntry(String fullName, String email, String nameCorp, String deskripsiCorp, String password) {
        this.fullName = fullName;
        this.email = email;
        this.nameCorp = nameCorp;
        this.deskripsiCorp = deskripsiCorp;
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
}

