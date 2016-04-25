package com.tallerii.match;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


public class MatchFragmentChat extends Fragment {

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
        FragmentChatListAdapter adapter = new FragmentChatListAdapter(getContext());
        chat.setAdapter(adapter);

        adapter.add(new Chat());
        adapter.add(new Chat());

        return fragmentView;
    }
}
