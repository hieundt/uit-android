package com.example.dragonbreath.login.data;

import com.google.gson.annotations.SerializedName;

public class AccessTokenResponse {
    @SerializedName("access_token")
    public String accessToken;
    @SerializedName("refresh_token")
    public String refreshToken;
    @SerializedName("expires_in")
    public long  expiresIn;
    @SerializedName("refresh_expires_in")
    public long  refreshExpiresIn;
    @SerializedName("token_type")
    public String tokenType;
    @SerializedName("not-before-policy")
    public Long notBeforePolicy;
    @SerializedName("session_state")
    public String sessionState;
    @SerializedName("scope")
    public String scope;

//    public AccessTokenResponse(String accessToken, String refreshToken, int expiresIn, int refreshExpiresIn) {
//        this.accessToken = accessToken;
//        this.refreshToken = refreshToken;
//        this.expiresIn = expiresIn;
//        this.refreshExpiresIn = refreshExpiresIn;
//    }
//    public String getAccessToken() {
//        return accessToken;
//    }
//    public void setAccessToken(String accessToken) {
//        this.accessToken = accessToken;
//    }
//    public String getRefreshToken() {
//        return refreshToken;
//    }
//    public void setRefreshToken(String refreshToken) {
//        this.refreshToken = refreshToken;
//    }
//    public int getExpiresIn() {
//        return expiresIn;
//    }
//    public void setExpiresIn(int expiresIn) {
//        this.expiresIn = expiresIn;
//    }
//    public int getRefreshExpiresIn() {
//        return refreshExpiresIn;
//    }
//    public void setRefreshExpiresIn(int refreshExpiresIn) {
//        this.refreshExpiresIn = refreshExpiresIn;
//    }
}
