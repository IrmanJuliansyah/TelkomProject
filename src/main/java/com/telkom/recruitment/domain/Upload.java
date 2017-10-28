package com.telkom.recruitment.domain;

import com.telkom.recruitment.domain.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@Document(collection = "tb_upload")
public class Upload implements Serializable {

    public Upload() {
    }

    public Upload(String nameUpload, String fileUpload) {
        this.nameUpload = nameUpload;
        this.fileUpload = fileUpload;
    }


    public Upload(String idUpload, String nameUpload, String fileUpload) {
        this.idUpload = idUpload;
        this.nameUpload = nameUpload;
        this.fileUpload = fileUpload;
    }

    @Id
    @Field(value = "_id")
    private String idUpload;


    @Field(value = "name_upload")
    private String nameUpload;


    @Field(value = "file_upload")
    private String fileUpload;

    @DBRef(lazy = true)
    private User user;

    public String getIdUpload() {
        return idUpload;
    }

    public void setIdUpload(String idUpload) {
        this.idUpload = idUpload;
    }

    public String getNameUpload() {
        return nameUpload;
    }

    public void setNameUpload(String nameUpload) {
        this.nameUpload = nameUpload;
    }

    public String getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(String fileUpload) {
        this.fileUpload = fileUpload;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
