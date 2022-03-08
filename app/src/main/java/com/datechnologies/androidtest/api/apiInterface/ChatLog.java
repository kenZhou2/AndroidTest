package com.datechnologies.androidtest.api.apiInterface;


import retrofit2.Call;
import retrofit2.http.GET;
import com.datechnologies.androidtest.api.ChatLogList;

public interface ChatLog {

    @GET("chat_log.php")
    Call<ChatLogList> chatLog();
}
