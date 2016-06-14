package com.tallerii.match;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.tallerii.match.core.ServerData;
import com.tallerii.match.core.ImageManager;
import com.tallerii.match.core.SystemData;
import com.tallerii.match.core.query.CandidatesQuery;
import com.tallerii.match.core.query.LikeUserQuery;
import com.tallerii.match.core.query.QueryListener;
import com.tallerii.match.core.UserProfile;

public class MatchFragmentMatchResults extends Fragment implements QueryListener, OnClickListener {

    UserProfile currentMatchProfile = null;
    View fragmentView;
    ImageButton likeButton;
    ImageButton unlikeButton;
    ImageButton mediumButton;

    public MatchFragmentMatchResults() {
        // Required empty public constructor
    }

    public void getNextMatch() {
        SystemData systemData = SystemData.getInstance();

        if (systemData.getCandidatesManager().hasCandidate()) {
            setUserOnMatch(systemData.getCandidatesManager().getNextCandidate());
        } else {
            ServerData.getInstance().fetchCandidates(this);
        }
    }

    private void setUserOnMatch(UserProfile user){
        if(user == null){
            return;
        }

        ImageView imageView = (ImageView) fragmentView.findViewById(R.id.fmfmr_iv_uphoto);
        this.currentMatchProfile = user;
        Bitmap imagebitmap = ImageManager.decodeFromBase64(user.getPhoto());
        imageView.setImageBitmap(imagebitmap);

        TextView userName = (TextView) fragmentView.findViewById(R.id.fmfmr_tv_uname);
        userName.setText(user.getName());

        TextView userMail = (TextView) fragmentView.findViewById(R.id.fmfmr_tv_umail);
        userMail.setText(user.getMail());

        TextView userDistance = (TextView) fragmentView.findViewById(R.id.fmfmr_tv_udistance);
        userDistance.setText("0");

        likeButton.setEnabled(true);
        unlikeButton.setEnabled(true);
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

        likeButton = (ImageButton) fragmentView.findViewById(R.id.fmfmr_b_like);
        likeButton.setOnClickListener(this);

        unlikeButton = (ImageButton) fragmentView.findViewById(R.id.fmfmr_b_unlike);
        unlikeButton.setOnClickListener(this);

        mediumButton = (ImageButton) fragmentView.findViewById(R.id.fmfmr_b_profile);
        mediumButton.setOnClickListener(this);


        this.setUserOnMatch(UserProfile.buildDefaultProfile());

        ImageView imageView = (ImageView) fragmentView.findViewById(R.id.fmfmr_iv_uphoto);
        Bitmap icon = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.im_no_profile_image);
        imageView.setImageBitmap(icon);

        return fragmentView;
    }

    public void likeUser(boolean like){
        if(currentMatchProfile != null) {
            ServerData.getInstance().likeUser(currentMatchProfile.getId(), this, like);
        }
    }

    public void openUserProfile(){
        getActivity().startActivity(new Intent(getActivity(), ViewPerfilActivity.class));
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.fmfmr_b_profile) {
            SystemData.getInstance().setShowingProfile(this.currentMatchProfile);
            openUserProfile();
            return;
        }

        if (currentMatchProfile.getId().compareTo("default") == 0){
            return;
        }

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

    @Override
    public void onReturnedRequest(String request) {
        if(request.compareTo(CandidatesQuery.QUERY_TAG) == 0){
            getNextMatch();
        }
    }

    @Override
    public void onFailRequest(String message, String request) {
        System.out.println(message);
    }

    @Override
    public void afterRequest(String request) {
        if(request.compareTo(LikeUserQuery.QUERY_TAG)== 0){
            getNextMatch();
        }
    }
}
