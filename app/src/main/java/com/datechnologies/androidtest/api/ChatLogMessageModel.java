package com.datechnologies.androidtest.api;

import com.google.gson.annotations.SerializedName;

/**
 * A data model that represents a chat log message fetched from the D & A Technologies Web Server.
 */

public class ChatLogMessageModel
{
    @SerializedName("user_id")
    private int userId;
    @SerializedName("name")
    private String username;
    @SerializedName("avatar_url")
    private String avatarUrl;
    @SerializedName("message")
    private String message;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.username = name;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return username;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return message;
    }
}
