package com.example.airsense.domain.service;
import com.example.airsense.domain.model.AssetModel.LightAsset;
import com.example.airsense.domain.model.AssetModel.WeatherAsset;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WeatherAssetService {
    @POST("api/master/asset/query")
    Call<List<WeatherAsset>> getAsset(@Body WeatherAssetRequestBody types);

    @GET("api/master/asset/{assetID}")
    Call<WeatherAsset> getAssetById(@Path("assetID") String assetID);

    @GET("api/master/asset/{assetID}")
    Call<LightAsset> getLightAssetById(@Path("assetID") String assetID);
}
