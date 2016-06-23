package com.tallerii.match;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tallerii.match.core.Chat;
import com.tallerii.match.core.ImageManager;
import com.tallerii.match.core.SystemData;
import com.tallerii.match.core.UserProfile;

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

        Chat currentChat = getItem(position);

        UserProfile userProfile = SystemData.getInstance().getUserManager().getUserProfile(currentChat.getUserId());

        if(userProfile != null) {
            ImageView imageView = (ImageView) customView.findViewById(R.id.fcci_iv_image);
            Bitmap photoBitmap = ImageManager.decodeFromBase64(userProfile.getPhoto());
            imageView.setImageBitmap(photoBitmap);

            TextView userNameTextView = (TextView) customView.findViewById(R.id.fcci_tv_name);
            userNameTextView.setText(userProfile.getName());

            TextView userMailTextView = (TextView) customView.findViewById(R.id.fcci_tv_mail);
            userMailTextView.setText(userProfile.getMail());
        }

        return customView;
    }
}
