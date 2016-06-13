package com.tallerii.match.core;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Demian on 24/04/2016.
 */
public class UserProfile implements Serializable{
    private Map<String, InterestCategory> interestCategories;
    private String name;
    private String alias;
    private String mail;
    private String sex;
    private String photo = "";
    private int age = 18;

    private int latitude;
    private int longitude;

    private String id = "";

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

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

    public UserProfile(String id) {
        this.id = id;
        setDefaultSettings();
    }

    public Map<String, InterestCategory> getInterestCategories() {
        return interestCategories;
    }

    public void addOnInterestCategory(String category, String interest){
        interestCategories.get(category).addDetail(interest);
    }

    private void setDefaultSettings(){
        interestCategories = new HashMap<>();
        setDefaultInterestCategories();
        name = "Unknow";
        alias = "Unknow";
        mail = "Unknow@Unknow.com";
        sex = "M";
        latitude = 0;
        longitude = 0;
    }

    private void setDefaultInterestCategories(){
        this.addInterestCategory("music");
        this.addInterestCategory("music/band");
        this.addInterestCategory("sex");
        this.addInterestCategory("food");
        this.addInterestCategory("travel");
        this.addInterestCategory("hobby");
        this.addInterestCategory("outdoors");
    }

    private void addInterestCategory(String category){
        interestCategories.put(category, new InterestCategory(category));
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public static UserProfile buildDefaultProfile() {
        UserProfile userProfile = new UserProfile("default");
        userProfile.setMail("default@default.d");
        userProfile.setAlias("default");
        userProfile.addOnInterestCategory("travel", "asia");
        userProfile.setLatitude(23);
        userProfile.setLongitude(43);
        userProfile.setSex("M");

        return userProfile;
    }
}
