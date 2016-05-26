package com.tallerii.match;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.tallerii.match.core.DataFacade;
import com.tallerii.match.core.ImageManager;
import com.tallerii.match.core.RequesterListener;
import com.tallerii.match.core.UserProfile;

public class MatchFragmentMatchResults extends Fragment implements AdapterView.OnItemClickListener, RequesterListener {

    UserProfile currentMatchProfile = null;
    View fragmentView;

    public MatchFragmentMatchResults() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getNextMatch();

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_match_fragment_match_results, container, false);

        return fragmentView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        UserProfile userProfile = (UserProfile) parent.getItemAtPosition(position);
        Intent i = new Intent(getActivity(), PerfilActivity.class);
        i.putExtra("profile", userProfile);
        startActivity(i);
    }

    @Override
    public void proccesRequest(Object returnedObject, String request) {
        if(request.compareTo("MATCH")== 0){
            String match = (String) returnedObject;
            DataFacade.getInstance().getUserProfile(match, this);
            return;
        }

        if(request.compareTo("PROFILE")== 0){
           setUserOnMatch((UserProfile) returnedObject);
            return;
        }
    }
}
