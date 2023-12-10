package com.example.airsense.view.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.airsense.domain.model.AssetModel.WeatherAsset;
import com.example.airsense.view.home.WeatherAssetRepository;

public class DashBoardDetailViewModel extends ViewModel {
    private String assetId;
    private LiveData<WeatherAsset> weatherAsset;

    public DashBoardDetailViewModel(String assetId, WeatherAssetRepository weatherAssetRepository) {
        this.assetId = assetId;
        weatherAsset = weatherAssetRepository.getAssetById(this.assetId);
    }

    public LiveData<WeatherAsset> getData() {
        return weatherAsset;
    }
}
