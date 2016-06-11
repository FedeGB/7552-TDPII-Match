package com.tallerii.match;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tallerii.match.core.InterestCategory;
import com.tallerii.match.core.UserProfile;

import org.json.*;

import java.util.Iterator;
import java.util.Map;
import java.util.Vector;


public class PerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
    }

    public void getContent(UserProfile userProfile){
        ((TextView)(findViewById(R.id.PE_TV_pSex))).setText(userProfile.getSex());
        ((TextView)(findViewById(R.id.PE_TV_alias))).setText(userProfile.getAlias());
        ((TextView)(findViewById(R.id.PE_TV_email))).setText(userProfile.getMail());
        ((TextView)(findViewById(R.id.PE_TV_realName))).setText(userProfile.getName());
    }
}

