package com.tallerii.match;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tallerii.match.core.Chat;
import com.tallerii.match.core.ServerData;
import com.tallerii.match.core.SystemData;

public class ConversationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);

        Bundle extras = getIntent().getExtras();

        if(extras != null) {
            String idUSerChat = extras.getString("chat");

            Chat currentChat = SystemData.getInstance().getChatManager().getChat(idUSerChat);
            MatchFragmentConversation matchFragmentConversation = (MatchFragmentConversation)getSupportFragmentManager().findFragmentById(R.id.fragment4);
            if(matchFragmentConversation != null){
                matchFragmentConversation.setChat(currentChat);
            }
        }
    }
}
