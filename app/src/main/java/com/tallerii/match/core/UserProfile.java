package com.tallerii.match.core;

import com.tallerii.match.JsonSerializable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Demian on 24/04/2016.
 */
public class UserProfile implements JsonSerializable, Serializable{
    private Map<String, InterestCategory> interestCategories;
    private String name;
    private String alias;
    private String mail;
    private String sex;
    private String photo;

    public String getPhoto() {
        return photo;
    }

    public String getName() {
        return name;
    }

    public String getAlias() {
        return alias;
    }

    public String getMail() {
        return mail;
    }

    public String getSex() {
        return sex;
    }

    public UserProfile() {
        setDefaultSettings();
    }

    public Map<String, InterestCategory> getInterestCategories() {
        return interestCategories;
    }

    private void setDefaultSettings(){
        interestCategories = new HashMap<>();
        setDefaultInterestCategories();
        name = "Unknow";
        alias = "Unknow";
        mail = "Unknow@Unknow.com";
        sex = "Unknow";
    }

    private void setDefaultInterestCategories(){
        this.addInterestCategory("music");
        this.addInterestCategory("music/band");
        this.addInterestCategory("sex");
        this.addInterestCategory("food");
        this.addInterestCategory("travel");
        this.addInterestCategory("hobby");
    }

    private void addInterestCategory(String category){
        interestCategories.put(category, new InterestCategory(category));
    }

    @Override
    public JSONObject jsonSerialize() {
        return null;
    }

    @Override
    public void jsonDeserialize(JSONObject jsonObject) {
        try {
            JSONObject user = jsonObject.getJSONObject("user");
            sex = user.getString("sex");
            alias = user.getString("alias");
            name = user.getString("name");
            mail = user.getString("mail");

            JSONArray interests = user.getJSONArray("interests");

            for(int i = 0; i < interests.length(); i++){
                JSONObject interest = interests.getJSONObject(i);

                String category = interest.getString("category");
                String value = interest.getString("value");

                if(interestCategories.containsKey(category)){
                    interestCategories.get(category).addDetail(value);
                } else {
                    InterestCategory newCategory = new InterestCategory(category);
                    newCategory.addDetail(value);
                    interestCategories.put(category, newCategory);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
