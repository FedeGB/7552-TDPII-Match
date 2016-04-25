package com.tallerii.match;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class MatchFragmentMatchResults extends Fragment {

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

        adapter.add(new UserProfile());
        adapter.add(new UserProfile());

        return fragmentView;
    }
}
