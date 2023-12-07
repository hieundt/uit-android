package com.example.airsense.view.login;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.airsense.data.apiclient.ApiClient;
import com.example.airsense.domain.model.TokenResponse;
import com.example.airsense.domain.service.TokenService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {
    private TokenService tokenService;
    public LoginRepository(Context context) {
        tokenService = ApiClient.getClient(context).create(TokenService.class);
    }

    public LiveData<TokenResponse> login(String username, String password) {
        MutableLiveData<TokenResponse> data = new MutableLiveData<>();

        Call<TokenResponse> call = tokenService.getToken("openremote", "password", username, password);
        call.enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    // Handle login failure
                }
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                // Handle network errors
            }
        });
        return data;
    }
}