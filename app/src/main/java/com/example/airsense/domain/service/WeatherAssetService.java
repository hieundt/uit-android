package com.example.airsense.domain.service;
import com.example.airsense.domain.model.AssetModel.WeatherAsset;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface WeatherAssetService {
    @POST("api/master/asset/query")
    Call<List<WeatherAsset>> getAsset(@Body WeatherAssetRequestBody types);
}
