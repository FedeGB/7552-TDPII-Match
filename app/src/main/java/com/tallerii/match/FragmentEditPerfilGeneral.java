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

        changePhotoButton = (Button) fragmentView.findViewById(R.id.fefg_b_cphoto);
        changePhotoButton.setOnClickListener(this);

        saveButton = (Button) fragmentView.findViewById(R.id.fefg_b_save);
        saveButton.setOnClickListener(this);

        return fragmentView;
    }

    public void updateProfilePhoto(){
        UserProfile userProfile = SystemData.getInstance().getUserProfile();
        String photo = userProfile.getPhoto();
        if(photo.compareTo("") != 0) {
            ImageView photoImageView = (ImageView) fragmentView.findViewById(R.id.fefg_iv_profilephoto);
            Bitmap userPhoto = ImageManager.decodeFromBase64(photo);
            photoImageView.setImageBitmap(userPhoto);
            System.out.println("update");
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
                ServerData.getInstance().saveUserProfile(this);
                break;
        }

    }

    @Override
    public void proccesRequest(Object returnedObject, String request) {
        saveButton.setEnabled(true);
        changePhotoButton.setEnabled(true);
        getActivity().finish();
    }
}
