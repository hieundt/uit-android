package com.example.airsense.domain.model.AssetModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherAsset {
    @SerializedName("id")
    public String id;
    @SerializedName("version")
    public float version;
    @SerializedName("createdOn")
    public long createdOn;
    @SerializedName("name")
    public String name;
    @SerializedName("accessPublicRead")
    public boolean accessPublicRead;
    @SerializedName("parentId")
    public String parentId;
    @SerializedName("realm")
    public String realm;
    @SerializedName("type")
    public String type;
    @SerializedName("path")
    public List<String> path;
    @SerializedName("attributes")
    public Attributes attributes;

    public static class Attributes {
        @SerializedName("sunIrradiance")
        public Measurement sunIrradiance;
        @SerializedName("rainfall")
        public Measurement rainfall;
        @SerializedName("notes")
        public Measurement notes;
        @SerializedName("uVIndex")
        public Measurement uVIndex;
        @SerializedName("sunAzimuth")
        public Measurement sunAzimuth;
        @SerializedName("sunZenith")
        public Measurement sunZenith;
        @SerializedName("tags")
        public Measurement tags;
        @SerializedName("manufacturer")
        public Measurement manufacturer;
        @SerializedName("temperature")
        public Measurement temperature;
        @SerializedName("humidity")
        public Measurement humidity;
        @SerializedName("location")
        public Location location;
        @SerializedName("place")
        public Measurement place;
        @SerializedName("windDirection")
        public Measurement windDirection;
        @SerializedName("windSpeed")
        public Measurement windSpeed;
        @SerializedName("sunAltitude")
        public Measurement sunAltitude;
        @SerializedName("PM25")
        public Measurement pm25;
        @SerializedName("CO2")
        public Measurement co2;
        @SerializedName("AQI_Predict")
        public Measurement aqiPredict;
        @SerializedName("AQI")
        public Measurement aqi;

        public static class Measurement {
            @SerializedName("type")
            public String type;
            @SerializedName("value")
            public Object value;
            @SerializedName("name")
            public String name;
            @SerializedName("timestamp")
            public long timestamp;
        }

    }

    public static class Location {
        @SerializedName("type")
        public String type;
        @SerializedName("value")
        public GeoJsonPoint value;
        @SerializedName("name")
        public String name;
        @SerializedName("timestamp")
        public long timestamp;
    }

    public static class GeoJsonPoint {
        @SerializedName("coordinates")
        public List<Double> coordinates;
        @SerializedName("type")
        public String type;
    }
}

