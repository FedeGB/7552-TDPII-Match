package com.tallerii.match;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.*;

import java.util.Iterator;
import java.util.Map;
import java.util.Vector;


public class PerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        String jSonTest = "{ \"user\":{ \"name\":\"jorge\", \"sex\":\"Hombre\", \"alias\":\"jRop\", \"mail\":\"jor@gmail.com\", \"interests\":[{\"category\":\"music/band\", \"value\":\"aasds\"}, {\"category\":\"music/band\", \"value\":\"aasds\"}, {\"category\":\"outdoors\", \"value\":\"run\"}] } }";

        UserProfile userProfile = (UserProfile) getIntent().getSerializableExtra("profile");
        this.getContent(userProfile);
    }

    public void getContent(UserProfile userProfile){
        ((TextView)(findViewById(R.id.PE_TV_pSex))).setText(userProfile.getSex());
        ((TextView)(findViewById(R.id.PE_TV_alias))).setText(userProfile.getAlias());
        ((TextView)(findViewById(R.id.PE_TV_email))).setText(userProfile.getMail());
        ((TextView)(findViewById(R.id.PE_TV_realName))).setText(userProfile.getName());
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void getContent(String jSon){

        LinearLayout llInterest = (LinearLayout) findViewById(R.id.PE_LL_interest);

        try {
            UserProfile userProfile = new UserProfile();
            userProfile.jsonDeserialize(new JSONObject(jSon));

            ((TextView)(findViewById(R.id.PE_TV_pSex))).setText(userProfile.getSex());
            ((TextView)(findViewById(R.id.PE_TV_alias))).setText(userProfile.getAlias());
            ((TextView)(findViewById(R.id.PE_TV_email))).setText(userProfile.getMail());
            ((TextView)(findViewById(R.id.PE_TV_realName))).setText(userProfile.getName());


            Map<String, InterestCategory> interestCategories = userProfile.getInterestCategories();

            Iterator<InterestCategory> interestCategoryIterator = interestCategories.values().iterator();

            while(interestCategoryIterator.hasNext()){
                InterestCategory interestCategory = interestCategoryIterator.next();

                TextView tvCategory = new TextView(this);
                tvCategory.setText(interestCategory.getName());
                tvCategory.setTextAppearance(R.style.NormalBoldTextView);
                llInterest.addView(tvCategory);

                Vector<String> values = interestCategory.getDetails();
                Iterator<String> vIterator = values.iterator();

                while (vIterator.hasNext()){
                    TextView tvValue = new TextView(this);
                    tvValue.setText(vIterator.next());
                    tvValue.setTextAppearance(R.style.NormalTextView);
                    llInterest.addView(tvValue);
                }
            }

            //String photoProfile = user.getString("photo_profile");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

