package com.tallerii.match;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.tallerii.match.core.Chat;
import com.tallerii.match.core.ChatMessage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;


/**
 * A simple {@link Fragment} subclass.
 */
public class MatchFragmentConversation extends Fragment implements View.OnClickListener {

    private FragmentMessageListAdapter fragmentMessageListAdapter;
    private View fragmentView;
    Chat currentChat = null;

    public MatchFragmentConversation() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_match_fragment_conversation, container, false);

        Button sendMessageButton = (Button) fragmentView.findViewById(R.id.fmfc_b_sendmessage);
        sendMessageButton.setOnClickListener(this);

        ListView chat = (ListView) fragmentView.findViewById(R.id.fmfc_lv_message_list);
        fragmentMessageListAdapter = new FragmentMessageListAdapter(getContext());
        chat.setAdapter(fragmentMessageListAdapter);
        return fragmentView;
    }

    public void setChat(Chat chat){
        fragmentMessageListAdapter.clear();
        ArrayList<ChatMessage> messageList = chat.getMessageList();
        Iterator<ChatMessage> chatMessageIterator = messageList.iterator();

        currentChat = chat;

        while (chatMessageIterator.hasNext()){
            addMessage(chatMessageIterator.next());
        }
    }

    public void addMessage(ChatMessage chatMessage){
        fragmentMessageListAdapter.add(chatMessage);
    }

    public void onSendMessageClick(){
        EditText contentEditText = (EditText) fragmentView.findViewById(R.id.fmfc_et_content);
        String content = contentEditText.getText().toString();


        Calendar calander = Calendar.getInstance();
        int cDay = calander.get(Calendar.DAY_OF_MONTH);
        int cMonth = calander.get(Calendar.MONTH) + 1;
        int cYear = calander.get(Calendar.YEAR);

        String date = Integer.toString(cDay) + "/" + Integer.toString(cMonth) + "/" + Integer.toString(cYear);

        ChatMessage newChatMessage = new ChatMessage(true, content, date);

        if(currentChat != null){
            if(content.compareTo("") != 0) {
                currentChat.addMessageToChat(newChatMessage);
                addMessage(newChatMessage);
                contentEditText.setText("");
            }
        }
        //TODO: QUITAR DE INTENT PORQUE ROMPE EL OBJETO "CHAT"
        //TODO: ARMAR CONSULTA A SERVIDOR PARA ENVIAR MENSAJE!
    }

    @Override
    public void onClick(View v) {
        onSendMessageClick();
    }
}
