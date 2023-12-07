package com.example.airsense.view.dashboard;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.airsense.domain.model.AssetModel.WeatherAsset;
import com.example.airsense.view.home.WeatherAssetRepository;

import java.util.List;

public class DashboardViewModel extends ViewModel {
    private  LiveData<List<WeatherAsset>> weatherAssets;

    public DashboardViewModel(WeatherAssetRepository weatherAssetRepository) {
        weatherAssets = weatherAssetRepository.getAsset();
    }

    public LiveData<List<WeatherAsset>> getData() {
        return weatherAssets;
    }
}