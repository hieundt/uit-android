package com.example.airsense.domain.service;

import com.example.airsense.domain.model.AsserDataPointModel.AssetDataPoint;
import com.example.airsense.domain.model.AssetModel.WeatherAsset;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AssetDataPointService {
    @POST("api/master/asset/datapoint/{assetID}/attribute/{assetAttribute}")
    Call<List<AssetDataPoint>> getListDataPoint(
            @Path("assetID") String assetID,
            @Path("assetAttribute") String assetAttribute,
            @Body AssetDataPointRequestBody body);
}
