package com.example.dragonbreath.home;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.dragonbreath.LocalStorage;
import com.example.dragonbreath.MainActivity;
import com.example.dragonbreath.R;
import com.example.dragonbreath.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment {
    SharedPreferences sharedPreferences;
    private FragmentHomeBinding binding;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        Context context = getContext();
//        sharedPreferences = LocalStorage.getInstance(context);
//        String accessToken = sharedPreferences.getString("access_token", null);
//
//        NavController navController = Navigation.findNavController(view);
//        if (accessToken != null) {
//            Toast.makeText(context, "Welcome", Toast.LENGTH_SHORT).show();
//        } else {
//            navController.navigate(R.id.navigation_login);
//        }
//    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}