package com.tallerii.match;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tallerii.match.core.InterestCategory;
import com.tallerii.match.core.SystemData;
import com.tallerii.match.core.UserProfile;

import java.util.Iterator;


public class FragmentEditperfilInterest extends Fragment implements AdapterView.OnItemClickListener {

    ArrayAdapter<InterestCategory> listAdapter;

    boolean isPhone = true;
    FragmentEditPerfilInterestDetails fragmentEditPerfilInterestDetails;

    public FragmentEditperfilInterest() {
        // Required empty public constructor
    }

    public void setFragmentEditPerfilInterestDetails(FragmentEditPerfilInterestDetails fragmentEditPerfilInterestDetails) {
        this.fragmentEditPerfilInterestDetails = fragmentEditPerfilInterestDetails;
        String myId = SystemData.getInstance().getUserId();
        UserProfile userProfile = SystemData.getInstance().getUserManager().getUserProfile(myId);

        if(userProfile != null) {
            InterestCategory interestCategory = userProfile.getInterestCategories().get("hobby");
            fragmentEditPerfilInterestDetails.setInterestCategory(interestCategory);
        }
        isPhone = false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_editperfil_fragment_interest, container, false);


        ListView interestListView = (ListView) fragmentView.findViewById(R.id.fefi_lv_interestList);
        listAdapter = new FragmentEditPerfilInterestListAdapter(getContext());
        interestListView.setOnItemClickListener(this);
        interestListView.setAdapter(listAdapter);

        buildInterestList();

        return fragmentView;
    }

    private void buildInterestList(){
        listAdapter.clear();

        String myId = SystemData.getInstance().getUserId();
        UserProfile userProfile = SystemData.getInstance().getUserManager().getUserProfile(myId);
        if(userProfile != null) {
            Iterator<InterestCategory> interestCategoryIterator = userProfile.getInterestCategories().values().iterator();

            while (interestCategoryIterator.hasNext()) {
                listAdapter.add(interestCategoryIterator.next());
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String categoryName = view.getTag().toString();

        if(isPhone) {
            Intent i = new Intent(getActivity(), EditInterestActivity.class);
            i.putExtra("interest", categoryName);
            startActivityForResult(i, position);
        } else {
            String myId = SystemData.getInstance().getUserId();
            UserProfile userProfile = SystemData.getInstance().getUserManager().getUserProfile(myId);
            InterestCategory interestCategory = userProfile.getInterestCategories().get(categoryName);
            fragmentEditPerfilInterestDetails.setInterestCategory(interestCategory);
        }
    }

    /*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK){
            InterestCategory interestCategory = (InterestCategory) data.getSerializableExtra("interest");
            Iterator<String> a = interestCategory.getDetails().iterator();
            userProfile.getInterestCategories().put(interestCategory.getName(), interestCategory);
            buildInterestList();
        }
    }
    */
}
