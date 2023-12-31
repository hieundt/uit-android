package com.example.airsense.view.home;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.airsense.data.apiclient.ApiClient;
import com.example.airsense.domain.model.AssetModel.LightAsset;
import com.example.airsense.domain.model.AssetModel.WeatherAsset;
import com.example.airsense.domain.model.TokenResponse;
import com.example.airsense.domain.service.WeatherAssetRequestBody;
import com.example.airsense.domain.service.WeatherAssetService;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherAssetRepository {
    private WeatherAssetService assetService;
    public WeatherAssetRepository(Context context) {
        assetService = ApiClient.getClient(context).create(WeatherAssetService.class);
    }

    //List of Weather Asset
    String[] types = {"WeatherAsset"};
    WeatherAssetRequestBody requestBody = new WeatherAssetRequestBody(types);
    public LiveData<List<WeatherAsset>> getAsset() {
        MutableLiveData<List<WeatherAsset>> data = new MutableLiveData<>();
        Call<List<WeatherAsset>> call = assetService.getAsset(requestBody);
        call.enqueue(new Callback<List<WeatherAsset>>() {
            @Override
            public void onResponse(Call<List<WeatherAsset>> call, Response<List<WeatherAsset>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else  {
                    Log.e("API CALL", "Request failed with code: " + response.code());
                }
            }
            @Override
            public void onFailure(Call<List<WeatherAsset>> call, Throwable t) {
                // Handle network error
                Log.e("API CALL", "Request failed with code: " +  t.getMessage());
            }
        });
        return data;
    }

    //Weather Asset by Id
    public LiveData<WeatherAsset> getAssetById(String assetId) {
        MutableLiveData<WeatherAsset> data = new MutableLiveData<>();
        Call<WeatherAsset> call = assetService.getAssetById(assetId);
        call.enqueue(new Callback<WeatherAsset>() {
            @Override
            public void onResponse(Call<WeatherAsset> call, Response<WeatherAsset> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else  {
                    Log.e("API CALL", "Request failed with code: " + response.code());
                }
            }
            @Override
            public void onFailure(Call<WeatherAsset> call, Throwable t) {
                // Handle network error
                Log.e("API CALL", "Request failed with code: " +  t.getMessage());
            }
        });
        return data;
    }

    //Light Asset by Id
    public LiveData<LightAsset> getLightAssetById(String assetId) {
        MutableLiveData<LightAsset> data = new MutableLiveData<>();
        Call<LightAsset> call = assetService.getLightAssetById(assetId);
        call.enqueue(new Callback<LightAsset>() {
            @Override
            public void onResponse(Call<LightAsset> call, Response<LightAsset> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else  {
                    Log.e("API CALL", "Request failed with code: " + response.code());
                }
            }
            @Override
            public void onFailure(Call<LightAsset> call, Throwable t) {
                // Handle network error
                Log.e("API CALL", "Request failed with code: " +  t.getMessage());
            }
        });
        return data;
    }
}
