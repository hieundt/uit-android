package com.example.dragonbreath.basic_info;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.dragonbreath.databinding.FragmentBasicInfoBinding;

public class BasicInfoFragment extends Fragment {
    private FragmentBasicInfoBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        BasicInfoViewModel basicInfoViewModel = new ViewModelProvider(this).get(BasicInfoViewModel.class);
        binding = FragmentBasicInfoBinding.inflate(inflater,container,false);
        View root = binding.getRoot();

        binding.tvPlaceInfo.setText("SAI GON");


        return root;
    }
}
