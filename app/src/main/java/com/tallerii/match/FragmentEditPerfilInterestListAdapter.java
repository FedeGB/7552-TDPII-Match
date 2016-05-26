package com.tallerii.match;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tallerii.match.core.InterestCategory;

import java.util.Iterator;

/**
 * Created by Demian on 24/04/2016.
 */
public class FragmentEditPerfilInterestListAdapter extends ArrayAdapter<InterestCategory> {

    public FragmentEditPerfilInterestListAdapter(Context context) {
        super(context, R.layout.edit_perfil_interest_item);
    }

    public FragmentEditPerfilInterestListAdapter(Context context, InterestCategory[] interestList) {
        super(context, R.layout.edit_perfil_interest_item, interestList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View customView = layoutInflater.inflate(R.layout.edit_perfil_interest_item, parent, false);

        InterestCategory currentInterest = getItem(position);
        TextView textViewInterestTitle = (TextView) customView.findViewById(R.id.EPI_TV_interestTitle);

        textViewInterestTitle.setText(currentInterest.getName());
        LinearLayout ll = (LinearLayout) customView.findViewById(R.id.EPI_LL_interestList);

        Iterator<String> detailsIt = currentInterest.getDetails().iterator();

        while (detailsIt.hasNext()){
            TextView tvValue = new TextView(getContext());
            tvValue.setText(detailsIt.next());

            //tvValue.setTextAppearance(R.style.NormalTextView);
            ll.addView(tvValue);
        }

        customView.setTag(currentInterest.getName());

        return customView;
    }
}
