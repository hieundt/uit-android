package com.example.airsense.data.apiclient;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.airsense.LocalStorage;
import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class TokenManager {
    private static TokenManager instance;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String accessToken;
    private String refreshToken;
    private long expiresIn;
    private long refreshExpiresIn;

    public static TokenManager getInstance( Context context) {
        if(instance == null) {
            instance  = new TokenManager(context);
        }
        return instance;
    }
    private TokenManager(Context context) {
        sharedPreferences = LocalStorage.getInstance(context);
        editor = sharedPreferences.edit();

        accessToken = sharedPreferences.getString("access_token", null);
        refreshToken = sharedPreferences.getString("refresh_token", null);
        expiresIn = sharedPreferences.getLong("expires_in", 0);
        refreshExpiresIn = sharedPreferences.getLong("refresh_expires_in", 0);
    }

    public String getAccessToken() {
        return accessToken;
    }
    public String getRefreshToken() {
        return refreshToken;
    }
    public long getExpiresIn() { return expiresIn;}
    public long getRefreshExpiresIn() {
        return refreshExpiresIn;
    }

    public void login(String accessToken, String refreshToken, long expiresIn, long refreshExpiresIn) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiresIn = System.currentTimeMillis() / 1000 + expiresIn;
        this.refreshExpiresIn = refreshExpiresIn;

        editor.putString("access_token", accessToken);
        editor.putString("refresh_token", refreshToken);
        editor.putLong("expires_in", expiresIn);
        editor.putLong("refresh_expires_in", refreshExpiresIn);
        editor.apply();
    }

    public void logout() {
        if (sharedPreferences.getString("access_token", null) != null) {
            editor.remove("access_token");
            editor.remove("refresh_token");
            editor.remove("expires_in");
            editor.remove("refresh_expires_in");
            editor.apply();
        }
    }

    public boolean isAccessTokenValid() {
        return accessToken != null && (System.currentTimeMillis() > expiresIn);
    }
}
