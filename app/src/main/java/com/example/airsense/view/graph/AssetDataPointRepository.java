package com.example.airsense.view.graph;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.airsense.data.apiclient.ApiClient;
import com.example.airsense.domain.model.AsserDataPointModel.AssetDataPoint;
import com.example.airsense.domain.service.AssetDataPointRequestBody;
import com.example.airsense.domain.service.AssetDataPointService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssetDataPointRepository {
    private AssetDataPointService service;

    public AssetDataPointRepository(Context context) {
        this.service = ApiClient.getClient(context).create(AssetDataPointService.class);
    }

    public LiveData<List<AssetDataPoint>> getListDataPoint(
            String assetID,
            String assetAttribute,
            AssetDataPointRequestBody body
    ) {
        MutableLiveData<List<AssetDataPoint>> data = new MutableLiveData<>();
        Call<List<AssetDataPoint>> call = service.getListDataPoint(assetID, assetAttribute, body);
        call.enqueue(new Callback<List<AssetDataPoint>>() {
            @Override
            public void onResponse(Call<List<AssetDataPoint>> call, Response<List<AssetDataPoint>> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else  {
                    Log.e("API CALL", "Request failed with code: " + response.code());
                }
            }
            @Override
            public void onFailure(Call<List<AssetDataPoint>> call, Throwable t) {
                // Handle network error
                Log.e("API CALL", "Request failed with code: " +  t.getMessage());
            }
        });
        return data;
    }
}
