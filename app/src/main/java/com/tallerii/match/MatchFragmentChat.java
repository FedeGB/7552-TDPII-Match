package com.tallerii.match;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tallerii.match.core.Chat;
import com.tallerii.match.core.ServerData;
import com.tallerii.match.core.query.QueryListener;

import java.util.ArrayList;
import java.util.Iterator;


public class MatchFragmentChat extends Fragment implements AdapterView.OnItemClickListener, QueryListener {

    private boolean isPhone = false;
    private FragmentChatListAdapter fragmentChatListAdapter;
    MatchFragmentConversation matchFragmentConversation = null;

    public MatchFragmentChat() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_match_fragment_chat, container, false);

        ListView chat = (ListView) fragmentView.findViewById(R.id.fmfc_lv_chats);
        fragmentChatListAdapter = new FragmentChatListAdapter(getContext());
        chat.setOnItemClickListener(this);

        chat.setAdapter(fragmentChatListAdapter);

        //ServerData.getInstance().getChatList(this);

        return fragmentView;
    }

    public void setMatchFragmentConversation(MatchFragmentConversation matchFragmentConversation) {
        this.matchFragmentConversation = matchFragmentConversation;
    }

    public void setIsPhone(){
        isPhone = true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Chat chatItem = fragmentChatListAdapter.getItem(position);

        if(isPhone){
            Intent i = new Intent(getActivity(), ConversationActivity.class);
            i.putExtra("chat", position);
            startActivity(i);
        } else {
            selectChat(chatItem);
        }
    }

    private void selectChat(Chat chat){
        if(matchFragmentConversation != null){
            matchFragmentConversation.setChat(chat);
        }
    }

    /*
    @Override
    public void proccesRequest(Object returnedObject, String request) {
        ArrayList<Chat> chatArrayList = (ArrayList<Chat>) returnedObject;

        Iterator<Chat> chatIterator = chatArrayList.iterator();
        fragmentChatListAdapter.clear();

        while (chatIterator.hasNext()) {
            fragmentChatListAdapter.add(chatIterator.next());
        }
    }*/

    @Override
    public void onReturnedRequest(String request) {

    }

    @Override
    public void onFailRequest(String message, String request) {

    }

    @Override
    public void afterRequest(String request) {

    }
}
