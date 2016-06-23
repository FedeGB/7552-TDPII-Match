package com.tallerii.match;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.tallerii.match.core.Chat;
import com.tallerii.match.core.ChatMessage;
import com.tallerii.match.core.ImageManager;
import com.tallerii.match.core.InterestCategory;
import com.tallerii.match.core.NullQueryListener;
import com.tallerii.match.core.ServerData;
import com.tallerii.match.core.SystemData;
import com.tallerii.match.core.UserProfile;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;


/**
 * A simple {@link Fragment} subclass.
 */
public class MatchFragmentConversation extends Fragment implements View.OnClickListener, Observer {

    private FragmentMessageListAdapter fragmentMessageListAdapter;
    private View fragmentView;
    Chat currentChat = null;
    ListView chat;

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

        chat = (ListView) fragmentView.findViewById(R.id.fmfc_lv_message_list);
        fragmentMessageListAdapter = new FragmentMessageListAdapter(getContext());
        chat.setAdapter(fragmentMessageListAdapter);

        return fragmentView;
    }

    public void setChat(Chat chat){
        fragmentMessageListAdapter.clear();
        ArrayList<ChatMessage> messageList = chat.getMessageList();
        Iterator<ChatMessage> chatMessageIterator = messageList.iterator();

        String userId = chat.getUserId();
        UserProfile userProfile = SystemData.getInstance().getUserManager().getUserProfile(userId);

        if(userProfile != null) {
            ImageView imageView = (ImageView) fragmentView.findViewById(R.id.fmfc_iv_userimage);
            Bitmap imageBitmap = ImageManager.decodeFromBase64(userProfile.getPhoto());
            imageView.setImageBitmap(imageBitmap);

            TextView listView = (TextView) fragmentView.findViewById(R.id.fmfc_tv_username);
            listView.setText(userProfile.getName());
        }

        currentChat = chat;
        chat.addObserver(this);

        while (chatMessageIterator.hasNext()){
            addMessage(chatMessageIterator.next());
        }
    }

    public void addMessage(ChatMessage chatMessage){
        fragmentMessageListAdapter.add(chatMessage);
        chat.setSelection(fragmentMessageListAdapter.getCount() - 1);
    }

    public void onSendMessageClick(){
        EditText contentEditText = (EditText) fragmentView.findViewById(R.id.fmfc_et_content);
        String content = contentEditText.getText().toString();

        if(currentChat != null){
            if(content.compareTo("") != 0) {
                contentEditText.setText("");
                ServerData.getInstance().sendMessage(currentChat.getUserId(), content, new NullQueryListener());
            }
        }

    }

    @Override
    public void onClick(View v) {
        onSendMessageClick();
    }

    @Override
    public void update(Observable observable, Object data) {
        ChatMessage newChatMessage = (ChatMessage) data;
        addMessage(newChatMessage);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        currentChat.deleteObserver(this);
    }
}
