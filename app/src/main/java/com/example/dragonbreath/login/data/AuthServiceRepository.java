package com.example.dragonbreath.login.data;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.dragonbreath.clientconfig.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthServiceRepository {
    private AuthService authService;

    public AuthServiceRepository() {
        authService = ApiClient.getClient().create(AuthService.class);
    }

    public MutableLiveData<AccessTokenResponse> login(String username, String password) {
        MutableLiveData<AccessTokenResponse> data = new MutableLiveData<>();
        authService.login("openremote", "password", username, password)
                .enqueue(new Callback<AccessTokenResponse>() {
            @Override
            public void onResponse(Call<AccessTokenResponse> call, Response<AccessTokenResponse> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    Log.e("API CALL", "Request failed with code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<AccessTokenResponse> call, Throwable t) {
                Log.e("API CALL", "Request failed with error: " + t.getMessage());
            }
        });
        return data;
    }
}
