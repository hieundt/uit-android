package com.example.dragonbreath.login.ui;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.dragonbreath.clientconfig.ApiClient;
import com.example.dragonbreath.login.data.AccessTokenResponse;
import com.example.dragonbreath.login.data.AuthService;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {
    private AuthService authService;

    public LoginViewModel() {
        authService = ApiClient.getClient().create(AuthService.class);
    }

    public LiveData<AccessTokenResponse> login(String username, String password) {
        MutableLiveData<AccessTokenResponse> data = new MutableLiveData<>();

        Call<AccessTokenResponse> call = authService.login("openremote", "password", username, password);
        call.enqueue(new Callback<AccessTokenResponse>() {
            @Override
            public void onResponse(Call<AccessTokenResponse> call, Response<AccessTokenResponse> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    // Handle login failure
                }
            }

            @Override
            public void onFailure(Call<AccessTokenResponse> call, Throwable t) {
                // Handle network errors
            }
        });

        return data;
    }

}