package com.tallerii.match;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentEditPerfilGeneral extends Fragment implements View.OnClickListener {


    public FragmentEditPerfilGeneral() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View fragmentView = inflater.inflate(R.layout.fragment_editperfil_fragment_general, container, false);

        Button changePhotoButton = (Button) fragmentView.findViewById(R.id.fefg_b_cphoto);
        changePhotoButton.setOnClickListener(this);

        return fragmentView;
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(getActivity(), SelectImageActivity.class));
    }
}
