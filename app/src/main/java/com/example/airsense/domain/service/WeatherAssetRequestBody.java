package com.example.airsense.domain.service;

import com.google.gson.annotations.SerializedName;

public class WeatherAssetRequestBody {
    @SerializedName("types")
    private String[] types;

    public WeatherAssetRequestBody(String[] types) {
        this.types = types;
    }
}
