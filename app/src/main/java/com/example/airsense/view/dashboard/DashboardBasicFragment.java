package com.example.airsense.view.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.airsense.databinding.FragmentDashboardBasicBinding;
import com.example.airsense.domain.model.AssetModel.WeatherAsset;
import com.example.airsense.view.home.WeatherAssetRepository;

import java.util.List;

public class DashboardBasicFragment extends Fragment {
    private FragmentDashboardBasicBinding binding;
    private WeatherAssetRepository weatherAssetRepository;
    private DashboardBasicViewModel dashboardViewModel;
    private DashboardBasicRecViewAdapter dashboardRecViewAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDashboardBasicBinding.inflate(inflater, container, false);
        initiate(binding);
        setupRecyclerView(binding);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void initiate(FragmentDashboardBasicBinding binding) {
        weatherAssetRepository = new  WeatherAssetRepository(getContext());
        dashboardViewModel = new DashboardBasicViewModel(weatherAssetRepository);
        dashboardRecViewAdapter = new DashboardBasicRecViewAdapter(getContext());
        dashboardViewModel.getData().observe(getViewLifecycleOwner(), new Observer<List<WeatherAsset>>() {
            @Override
            public void onChanged(List<WeatherAsset> weatherAssets) {
                if(weatherAssets != null) {
                    dashboardRecViewAdapter.setWeatherAssets(weatherAssets);
                }
            }
        });
    }

    private void setupRecyclerView(FragmentDashboardBasicBinding binding) {
        RecyclerView recyclerView = binding.dashboardBasicRecView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        recyclerView.setAdapter(dashboardRecViewAdapter);

        recyclerView.setHasFixedSize(true);
    }
}