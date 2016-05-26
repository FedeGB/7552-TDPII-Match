package com.tallerii.match;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ConversationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);

        Bundle extras = getIntent().getExtras();

        if(extras != null) {
            Chat currentChat = (Chat) extras.getSerializable("chat");
            MatchFragmentConversation matchFragmentConversation = (MatchFragmentConversation)getSupportFragmentManager().findFragmentById(R.id.fragment4);
            if(matchFragmentConversation != null){
                matchFragmentConversation.setChat(currentChat);
            }

        }
    }
}
