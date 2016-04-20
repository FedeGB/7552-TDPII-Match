package com.tallerii.match;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.SynchronousQueue;

public class EditPerfilActivity extends AppCompatActivity {

    enum InterestCodeEnum {
        MUSIC(1, "music"), MUSICBAND(2, "music/band"), SPORT(3, "sport"), SEX(4, "sex"), OUTDOORS(5, "outdoors"), TRAVEL(6, "travel"), FOOD(7, "food");

        private List<String> interestList;
        private int numVal;
        private String name;

        InterestCodeEnum(int numVal, String name){
            this.numVal = numVal;
            this.name = name;
            this.interestList = new ArrayList<String>();
        }

        public int getNumVal(){
            return numVal;
        }

        public String getName(){
            return name;
        }

        public void addToList(String name){
            interestList.add(name);

            Iterator<String> i = interestList.iterator();
            while (i.hasNext()){
                System.out.println(i.next());
            }
        }

        static public InterestCodeEnum getFromValue(int numVal){
            for(InterestCodeEnum iC: InterestCodeEnum.values()){
                if(iC.getNumVal() == numVal){
                    return iC;
                }
            }

            return null;
        }

        static public InterestCodeEnum getFromName(String name){
            for(InterestCodeEnum iC: InterestCodeEnum.values()){
                if(iC.getName().compareTo(name) == 0){
                    return iC;
                }
            }

            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_perfil);
    }

    public void OnAddInterest(View view){
        String interestName = view.getTag().toString();
        int interestCode = InterestCodeEnum.getFromName(interestName).getNumVal();

        Intent i = new Intent(this, EditTextActivity.class);
        i.putExtra("Title", "Add interest: " + interestName);
        startActivityForResult(i, interestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode == Activity.RESULT_CANCELED){
            return;
        }

        String resultText = data.getStringExtra("result");

        addToInterest(requestCode, resultText);
    }

    private void addToInterest(int interestCode, String interest){
        InterestCodeEnum.getFromValue(interestCode).addToList(interest);
        buildInterestView();
    }

    private void buildInterestView(){
  
    }

}
