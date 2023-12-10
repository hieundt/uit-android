package com.example.airsense.view.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.airsense.domain.model.AssetModel.WeatherAsset;
import com.example.airsense.view.home.WeatherAssetRepository;

import java.util.List;

public class DashboardBasicViewModel extends ViewModel {
    private  LiveData<List<WeatherAsset>> weatherAssets;

    public DashboardBasicViewModel(WeatherAssetRepository weatherAssetRepository) {
        weatherAssets = weatherAssetRepository.getAsset();
    }

    public LiveData<List<WeatherAsset>> getData() {
        return weatherAssets;
    }
}