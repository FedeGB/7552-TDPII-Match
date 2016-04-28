package com.tallerii.match;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

public class MatchFragmentMatchResults extends Fragment implements AdapterView.OnItemClickListener {



    public MatchFragmentMatchResults() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_match_fragment_match_results, container, false);

        ListView matchResults = (ListView) fragmentView.findViewById(R.id.fmr_lv_match_results);
        FragmentMatchResultsListAdapter adapter = new FragmentMatchResultsListAdapter(getContext());
        matchResults.setAdapter(adapter);

        for(int i = 0; i < 10; i++) {
            adapter.add(new UserProfile());
            adapter.add(new UserProfile());
        }

        matchResults.setOnItemClickListener(this);
        return fragmentView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        UserProfile userProfile = (UserProfile) parent.getItemAtPosition(position);
        Intent i = new Intent(getActivity(), PerfilActivity.class);
        i.putExtra("profile", userProfile);
        startActivity(i);
    }
}
