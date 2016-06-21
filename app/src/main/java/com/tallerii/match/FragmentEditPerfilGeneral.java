package com.tallerii.match;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.tallerii.match.core.ServerData;
import com.tallerii.match.core.ImageManager;
import com.tallerii.match.core.query.QueryListener;
import com.tallerii.match.core.SystemData;
import com.tallerii.match.core.UserProfile;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentEditPerfilGeneral extends Fragment implements View.OnClickListener, QueryListener {

    View fragmentView;
    Button saveButton;
    Button changePhotoButton;

    public FragmentEditPerfilGeneral() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        fragmentView = inflater.inflate(R.layout.fragment_editperfil_fragment_general, container, false);

        updateProfilePhoto();

        changePhotoButton = (Button) fragmentView.findViewById(R.id.fefg_b_cphoto);
        changePhotoButton.setOnClickListener(this);

        saveButton = (Button) fragmentView.findViewById(R.id.fefg_b_save);
        saveButton.setOnClickListener(this);

        return fragmentView;
    }

    public void updateProfilePhoto(){
        String myId = SystemData.getInstance().getUserId();
        UserProfile userProfile = SystemData.getInstance().getUserManager().getUserProfile(myId);
        //userProfile.setPhoto("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");

        if(userProfile != null) {
            String photo = userProfile.getPhoto();
            if (photo.compareTo("") != 0) {
                ImageView photoImageView = (ImageView) fragmentView.findViewById(R.id.fefg_iv_profilephoto);
                Bitmap userPhoto = ImageManager.decodeFromBase64(photo);
                photoImageView.setImageBitmap(userPhoto);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fefg_b_cphoto:
                startActivityForResult(new Intent(getActivity(), SelectImageActivity.class), 23);
                break;
            case R.id.fefg_b_save:
                saveButton.setEnabled(false);
                changePhotoButton.setEnabled(false);
                ServerData.getInstance().updateUserProfile(this);
                break;
        }

    }

    @Override
    public void onReturnedRequest(String request) {

    }

    @Override
    public void onFailRequest(String message, String request) {

    }

    @Override
    public void afterRequest(String request) {
        saveButton.setEnabled(true);
        changePhotoButton.setEnabled(true);
        getActivity().finish();
    }
}
