package com.datechnologies.androidtest.chat;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.datechnologies.androidtest.MainActivity;
import com.datechnologies.androidtest.R;
import com.datechnologies.androidtest.api.apiInterface.ChatLog;
import com.datechnologies.androidtest.api.ChatLogList;
import com.datechnologies.androidtest.api.ChatLogMessageModel;
import com.datechnologies.androidtest.api.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Screen that displays a list of chats from a chat log.
 */
public class ChatActivity extends AppCompatActivity {

    //==============================================================================================
    // Class Properties
    //==============================================================================================

    private RecyclerView recyclerView;
    private ChatAdapter chatAdapter;

    //==============================================================================================
    // Static Class Methods
    //==============================================================================================

    public static void start(Context context)
    {
        Intent starter = new Intent(context, ChatActivity.class);
        context.startActivity(starter);
    }

    //==============================================================================================
    // Lifecycle Methods
    //==============================================================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        ActionBar actionBar = getSupportActionBar();

        assert actionBar != null;
        actionBar.setTitle("Chat");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        chatAdapter = new ChatAdapter();

        recyclerView.setAdapter(chatAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL,
                false));

        //Retrieve the chat data from http://dev.rapptrlabs.com/Tests/scripts/chat_log.php
        //Parse this chat data from JSON into and display it.
        ChatLog chatLog = RetrofitClient.getRetrofit().create(ChatLog.class);
        Call<ChatLogList> _chat = chatLog.chatLog();
        _chat.enqueue(new Callback<ChatLogList>() {
            @Override
            public void onResponse(Call<ChatLogList> call, Response<ChatLogList> response) {
                if(response.isSuccessful()){
                    assert response.body() != null;
                    List<ChatLogMessageModel> test = response.body().getChatLog();
                    chatAdapter.setChatLogMessageModelList(test);
                }
            }

            @Override
            public void onFailure(Call<ChatLogList> call, Throwable throwable) {
                Toast.makeText(ChatActivity.this, "fail", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
