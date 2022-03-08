package com.datechnologies.androidtest.api;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChatLogList {
    @SerializedName("data")
    private List<ChatLogMessageModel> data;

    public List<ChatLogMessageModel> getChatLog() {
        return data;
    }
}
