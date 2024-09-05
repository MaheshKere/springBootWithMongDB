package com.mangocrud.crud.pojo;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
@Document
public class User {
    @Id
    private String userId;
    private String name;
    private String rollNumber;

    public User(String name, String rollNumber) {
        super();
        this.name = name;
        this.rollNumber = rollNumber;
    }
    public String getUserId() {
        return userId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getRollNumber() {
        return rollNumber;
    }
    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }
}
