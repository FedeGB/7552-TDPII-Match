package com.tallerii.match;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.tallerii.match.core.DataFacade;
import com.tallerii.match.core.ImageManager;
import com.tallerii.match.core.RequesterListener;
import com.tallerii.match.core.UserProfile;

public class MatchFragmentMatchResults extends Fragment implements RequesterListener, OnClickListener {

    UserProfile currentMatchProfile = null;
    View fragmentView;
    Button likeButton;
    Button unlikeButton;

    public MatchFragmentMatchResults() {
        // Required empty public constructor
    }

    public void getNextMatch(){
        DataFacade.getInstance().getNextMatch(this);
    }

    private void setUserOnMatch(UserProfile user){
        if(user == null){
            return;
        }
        this.currentMatchProfile = user;
        ImageView imageView = (ImageView) fragmentView.findViewById(R.id.fmfmr_iv_uphoto);
        Bitmap imagebitmap = ImageManager.decodeFromBase64(user.getPhoto());
        imageView.setImageBitmap(imagebitmap);

        TextView userName = (TextView) fragmentView.findViewById(R.id.fmfmr_tv_uname);
        userName.setText(user.getName());

        TextView userMail = (TextView) fragmentView.findViewById(R.id.fmfmr_tv_umail);
        userMail.setText(user.getMail());

        TextView userDistance = (TextView) fragmentView.findViewById(R.id.fmfmr_tv_udistance);
        userDistance.setText("0");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getNextMatch();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_match_fragment_match_results, container, false);

        likeButton = (Button) fragmentView.findViewById(R.id.fmfmr_b_like);
        likeButton.setOnClickListener(this);

        unlikeButton = (Button) fragmentView.findViewById(R.id.fmfmr_b_unlike);
        unlikeButton.setOnClickListener(this);

        return fragmentView;
    }

    @Override
    public void proccesRequest(Object returnedObject, String request) {
        if(request.compareTo("MATCH")== 0){
            String match = (String) returnedObject;
            DataFacade.getInstance().getUserProfile(match, this);
            likeButton.setEnabled(true);
            unlikeButton.setEnabled(true);
            return;
        }

        if(request.compareTo("PROFILE")== 0){
           setUserOnMatch((UserProfile) returnedObject);
            return;
        }

        if(request.compareTo("LIKE")== 0){
            getNextMatch();
        }
    }

    public void likeUser(boolean like){
        if(currentMatchProfile != null) {
            DataFacade.getInstance().likeUser(currentMatchProfile.getId(), this, like);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fmfmr_b_like:
                likeUser(true);
                break;
            case R.id.fmfmr_b_unlike:
                likeUser(false);
                break;
        }

        likeButton.setEnabled(false);
        unlikeButton.setEnabled(false);
    }
}
