package com.example.airsense.domain.service;

import com.example.airsense.domain.model.TokenResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface TokenService {
    @FormUrlEncoded
    @POST("auth/realms/master/protocol/openid-connect/token")
    Call<TokenResponse> getToken(
            @Field("client_id") String clientId,
            @Field("grant_type") String grantType,
            @Field("username") String username,
            @Field("password") String password
    );
}
