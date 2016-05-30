package com.tallerii.match;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tallerii.match.core.Chat;
import com.tallerii.match.core.ServerData;

public class ConversationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);

        Bundle extras = getIntent().getExtras();

        if(extras != null) {
            int chat = extras.getInt("chat");
            Chat currentChat = ServerData.getInstance().getChatByIdWithoutQuery(chat);
            MatchFragmentConversation matchFragmentConversation = (MatchFragmentConversation)getSupportFragmentManager().findFragmentById(R.id.fragment4);
            if(matchFragmentConversation != null){
                matchFragmentConversation.setChat(currentChat);
            }

        }
    }
}
