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


public class MatchFragmentChat extends Fragment implements AdapterView.OnItemClickListener {

    private boolean isPhone = false;
    private FragmentChatListAdapter fragmentChatListAdapter;

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
        fragmentChatListAdapter.add(new Chat());
        return fragmentView;
    }

    public void setIsPhone(){
        isPhone = true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Chat chatItem = fragmentChatListAdapter.getItem(position);

        if(isPhone){
            Intent i = new Intent(getActivity(), ConversationActivity.class);
            i.putExtra("chat", chatItem);
            startActivity(i);
        }
    }
}
