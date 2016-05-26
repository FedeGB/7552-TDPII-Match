package com.tallerii.match;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Demian on 25/05/2016.
 */
public class FragmentMessageListAdapter extends ArrayAdapter<ChatMessage>{
    public FragmentMessageListAdapter(Context context) {
        super(context, R.layout.fragment_conversation_message_item);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View customView = layoutInflater.inflate(R.layout.fragment_conversation_message_item, parent, false);

        ChatMessage currentChatMessage = getItem(position);

        TextView dateText = (TextView) customView.findViewById(R.id.txtInfo);
        TextView messageText = (TextView) customView.findViewById(R.id.txtMessage);

        if(!currentChatMessage.isSendByMe()) {
            LinearLayout messageContent = (LinearLayout) customView.findViewById(R.id.fcmi_ly_mcontent);
            LinearLayout layoutBackround = (LinearLayout) customView.findViewById(R.id.contentWithBackground);

            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) messageContent.getLayoutParams();
            lp.gravity = Gravity.LEFT;

            lp = (LinearLayout.LayoutParams) layoutBackround.getLayoutParams();
            lp.gravity = Gravity.LEFT;
            layoutBackround.setBackgroundResource(R.drawable.graybubble);
        }

        messageText.setText(currentChatMessage.getContent());
        dateText.setText(currentChatMessage.getDate());

        return customView;
    }
}
