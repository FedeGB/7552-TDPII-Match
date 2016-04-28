package com.tallerii.match;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by fedegb on 27/04/16.
 */

public class PreferencesManager {
    private SharedPreferences sharedPref ;

    public PreferencesManager(Activity activity) {
        this.sharedPref = activity.getApplicationContext().getSharedPreferences(
                activity.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
    }

    public void saveString(String key, String value) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getString(String key) {
        return sharedPref.getString(key, "");
    }

    public void deleteKey(String key) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove(key);
        editor.apply();
    }

    public void saveInt(String key, Integer value) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public Integer getInt(String key) {
        return sharedPref.getInt(key, 0);
    }

}
