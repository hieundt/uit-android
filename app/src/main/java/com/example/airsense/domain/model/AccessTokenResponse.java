package com.example.airsense.domain.model;

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
}
