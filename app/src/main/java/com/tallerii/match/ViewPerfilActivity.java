package com.tallerii.match;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.tallerii.match.core.ImageManager;
import com.tallerii.match.core.InterestCategory;
import com.tallerii.match.core.SystemData;
import com.tallerii.match.core.UserProfile;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ViewPerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_perfil);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        UserProfile curretProfile = SystemData.getInstance().getShowingProfile();
        if(curretProfile != null) {
            buildActivtyContent(curretProfile);
            toolbar.setTitle(curretProfile.getName());
        }

        setSupportActionBar(toolbar);
    }

    private void buildActivtyContent(UserProfile userProfile) {
        String photo = userProfile.getPhoto();
        if(photo.length() > 4) {
            ImageView imageView = (ImageView) findViewById(R.id.avp_iv_profilePhoto);
            Bitmap profileBitmap = ImageManager.decodeFromBase64(userProfile.getPhoto());
            imageView.setImageBitmap(profileBitmap);
        }

        TextView ageTextView = (TextView) findViewById(R.id.cvp_tv_age);
        TextView aliasTextView = (TextView) findViewById(R.id.cvp_tv_alias);
        TextView mailTextView = (TextView) findViewById(R.id.cvp_tv_mail);

        ageTextView.setText(userProfile.getAge() + " anios");
        aliasTextView.setText("Alias: " + userProfile.getAlias());
        mailTextView.setText("Email: " + userProfile.getMail());


        Iterator<InterestCategory> interestIterator = userProfile.getInterestCategories().values().iterator();

        LinearLayout interestLayout = (LinearLayout) findViewById(R.id.cvp_ll_interest);

        while (interestIterator.hasNext()) {
            InterestCategory category = interestIterator.next();
            if(!category.getDetails().isEmpty()) {
                buildCategory(category, interestLayout);
            }
        }
    }

    private void buildCategory(InterestCategory category, LinearLayout ll) {


        TextView cName = new TextView(this);
        cName.setText(category.getName());

        if(Build.VERSION.SDK_INT < 23) {
            cName.setTextAppearance(this, R.style.InterestCategory);
        } else {
            cName.setTextAppearance(R.style.InterestCategory);
        }

        ll.addView(cName);


        Iterator<String> details = category.getDetails().iterator();
        while (details.hasNext()) {
            TextView tvValue = new TextView(this);
            tvValue.setText(details.next());

            if(Build.VERSION.SDK_INT < 23) {
                tvValue.setTextAppearance(this, R.style.InterestDetail);
            } else {
                tvValue.setTextAppearance(R.style.InterestDetail);
            }
            //
            ll.addView(tvValue);
        }
    }
}
