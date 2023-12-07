package com.example.airsense.view.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.airsense.databinding.FragmentDashboardBinding;
import com.example.airsense.domain.model.AssetModel.WeatherAsset;
import com.example.airsense.view.home.WeatherAssetRepository;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {
    private FragmentDashboardBinding binding;
    private WeatherAssetRepository weatherAssetRepository;
    private DashboardViewModel dashboardViewModel;
    private DashboardRecViewAdapter dashboardRecViewAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        initiate(binding);
        setupRecyclerView(binding);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void initiate(FragmentDashboardBinding binding) {
       weatherAssetRepository = new  WeatherAssetRepository(getContext());
        dashboardViewModel = new DashboardViewModel(weatherAssetRepository);
        dashboardRecViewAdapter = new DashboardRecViewAdapter(getContext());
        dashboardViewModel.getData().observe(getViewLifecycleOwner(), new Observer<List<WeatherAsset>>() {
            @Override
            public void onChanged(List<WeatherAsset> weatherAssets) {
                if(weatherAssets != null) {
                    dashboardRecViewAdapter.setWeatherAssets(weatherAssets);
                }
            }
        });
    }

    private void setupRecyclerView(FragmentDashboardBinding binding) {
        RecyclerView recyclerView = binding.dashboardRecView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        recyclerView.setAdapter(dashboardRecViewAdapter);

        recyclerView.setHasFixedSize(true);
    }
}