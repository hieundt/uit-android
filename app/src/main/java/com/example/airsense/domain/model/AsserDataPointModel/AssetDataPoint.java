package com.example.airsense.domain.model.AsserDataPointModel;

import com.google.gson.annotations.SerializedName;

public class AssetDataPoint {
    @SerializedName("x")
    public long x;

    @SerializedName("y")
    public long y;
}
