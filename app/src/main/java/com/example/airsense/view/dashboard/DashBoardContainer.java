package com.example.airsense.view.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.airsense.R;
import com.example.airsense.databinding.ActivityLandingBinding;
import com.example.airsense.databinding.FragmentDashboardContainerBinding;

public class DashBoardContainer extends Fragment {
    private FragmentDashboardContainerBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentDashboardContainerBinding.inflate(getLayoutInflater());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDashboardContainerBinding.inflate(inflater, container, false);

        NavHostFragment bla =  (NavHostFragment) getChildFragmentManager().findFragmentById(R.id.dashboard_nav_host_fragment);
        NavController nestedNavController = bla.getNavController();


        initiate(binding);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void initiate(FragmentDashboardContainerBinding binding) {

    }

}
