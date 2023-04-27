package com.example.login_page;


import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

public class PoliceIdSession {

    void storePoliceId(Context context, String policeId) {
        SharedPreferences preferences = context.getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("police_id", policeId);
        editor.apply();
    }

    String getPoliceId(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("MyPrefs", MODE_PRIVATE);
        return preferences.getString("police_id", null);
    }



}