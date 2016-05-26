package com.tallerii.match;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.tallerii.match.core.Chat;

/**
 * Created by Demian on 24/04/2016.
 */
public class FragmentChatListAdapter extends ArrayAdapter<Chat> {
    public FragmentChatListAdapter(Context context) {
        super(context, R.layout.fragment_chat_conver_item);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View customView = layoutInflater.inflate(R.layout.fragment_chat_conver_item, parent, false);

        return customView;
    }
}
