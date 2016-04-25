package com.tallerii.match;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Demian on 24/04/2016.
 */
public class FragmentMatchResultsListAdapter extends ArrayAdapter<UserProfile> {

    public FragmentMatchResultsListAdapter(Context context) {
        super(context, R.layout.fragment_match_person_item);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View customView = layoutInflater.inflate(R.layout.fragment_match_person_item, parent, false);

        UserProfile userProfile = getItem(position);

        ((TextView)customView.findViewById(R.id.fmpi_tv_nombre)).setText(userProfile.getName());
        ((TextView)customView.findViewById(R.id.fmpi_tv_mail)).setText(userProfile.getMail());
        ((TextView)customView.findViewById(R.id.fmpi_tv_sex)).setText(userProfile.getSex());
        ((TextView)customView.findViewById(R.id.fmpi_tv_alias)).setText(userProfile.getAlias());

        return customView;
    }
}
