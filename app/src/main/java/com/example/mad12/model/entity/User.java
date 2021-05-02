package com.example.mad12.model.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User {
    @PrimaryKey
    @NonNull
    private String UserID;
    private String FirstName;
    private String LastName;
    @NonNull
    private String UserName;
    @NonNull
    private String AuthType;
    private String Photo;

    public User() {
    }

    public User(@NonNull String userID, String firstName, String lastName, @NonNull String userName, @NonNull String authType, String photo) {
        UserID = userID;
        FirstName = firstName;
        LastName = lastName;
        UserName = userName;
        AuthType = authType;
        Photo = photo;
    }

    @NonNull
    public String getUserID() {
        return UserID;
    }

    public void setUserID(@NonNull String userID) {
        UserID = userID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    @NonNull
    public String getUserName() {
        return UserName;
    }

    public void setUserName(@NonNull String userName) {
        UserName = userName;
    }

    @NonNull
    public String getAuthType() {
        return AuthType;
    }

    public void setAuthType(@NonNull String authType) {
        AuthType = authType;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }
}
