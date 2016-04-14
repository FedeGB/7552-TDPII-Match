package com.tallerii.match;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;


public class PerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        String jSonTest = "{ \"user\":{ \"name\":\"jorge\", \"sex\":\"Hombre\", \"alias\":\"jRop\", \"mail\":\"jor@gmail.com\", \"interests\":[{\"category\":\"music/band\", \"value\":\"aasds\"}, {\"category\":\"music/band\", \"value\":\"aasds\"}, {\"category\":\"outdoors\", \"value\":\"run\"}] } }";
        this.getContent(jSonTest);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void getContent(String jSon){

        LinearLayout llInterest = (LinearLayout) findViewById(R.id.PE_LL_interest);

        try {
            JSONObject consult = new JSONObject(jSon);
            JSONObject user = consult.getJSONObject("user");

            ((TextView)(findViewById(R.id.PE_TV_pSex))).setText(user.getString("sex"));
            ((TextView)(findViewById(R.id.PE_TV_alias))).setText(user.getString("alias"));
            ((TextView)(findViewById(R.id.PE_TV_email))).setText(user.getString("mail"));
            ((TextView)(findViewById(R.id.PE_TV_realName))).setText(user.getString("name"));

            JSONArray interests = user.getJSONArray("interests");
            Map<String,Vector<String>> interestMap = new HashMap<>();
            for(int i = 0; i < interests.length(); i++){
                JSONObject interest = interests.getJSONObject(i);

                String category = interest.getString("category");
                String value = interest.getString("value");

                if(interestMap.containsKey(category)){
                    interestMap.get(category).add(value);
                } else {
                    Vector<String> vCategory = new Vector<>();
                    vCategory.add(value);
                    interestMap.put(category, vCategory);
                }
            }

            Iterator<Map.Entry<String, Vector<String>>> mIterator = interestMap.entrySet().iterator();

            while(mIterator.hasNext()){
                Map.Entry<String, Vector<String>> mapEntry = mIterator.next();

                TextView tvCategory = new TextView(this);
                tvCategory.setText(mapEntry.getKey());
                tvCategory.setTextAppearance(R.style.NormalBoldTextView);
                llInterest.addView(tvCategory);

                Vector<String> values = mapEntry.getValue();
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

