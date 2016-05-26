package com.tallerii.match;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.tallerii.match.core.InterestCategory;

import java.util.Iterator;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentEditPerfilInterestDetails extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    boolean isPhone = true;
    View fragmentView;
    ArrayAdapter<String> arrayAdapter;
    InterestCategory interestCategory;

    public FragmentEditPerfilInterestDetails() {
        // Required empty public constructor
    }

    public void setInterestCategory(InterestCategory interestCategory){
        isPhone = false;

        TextView title = (TextView) fragmentView.findViewById(R.id.EIA_TV_interest);
        title.setText(interestCategory.getName());

        arrayAdapter.clear();
        Iterator<String> detailsIterator = interestCategory.getDetails().iterator();

        while (detailsIterator.hasNext()){
            arrayAdapter.add(detailsIterator.next());
        }

        this.interestCategory = interestCategory;

        Button buttonAccept = (Button) fragmentView.findViewById(R.id.fefid_b_save);
        buttonAccept.setVisibility(View.GONE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_editperfil_fragment_interestdetails, container, false);

        ListView detailsListView = (ListView) fragmentView.findViewById(R.id.EIA_LV_details);
        arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1);
        detailsListView.setAdapter(arrayAdapter);
        detailsListView.setOnItemClickListener(this);

        Button buttonAccept = (Button) fragmentView.findViewById(R.id.fefid_b_save);
        buttonAccept.setOnClickListener(this);

        Button buttonAdd = (Button) fragmentView.findViewById(R.id.fefid_b_accept);
        buttonAdd.setOnClickListener(this);

        return fragmentView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        arrayAdapter.remove(interestCategory.getDetails().elementAt(position));
        interestCategory.getDetails().remove(position);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fefid_b_accept:
                addInterest();
                break;
            case R.id.fefid_b_save:
                save();
                break;
        }

    }

    private void addInterest(){
        EditText newInterest = (EditText) fragmentView.findViewById(R.id.EIA_ET_newInterest);
        String text = newInterest.getText().toString();

        if(text.length() >= 2) {
            arrayAdapter.add(text);
            interestCategory.addDetail(text);
            newInterest.setText("");
        }
    }

    private void save(){

    }

}
