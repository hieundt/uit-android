package com.example.dragonbreath;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.dragonbreath.databinding.FragmentLandingBinding;
import com.example.dragonbreath.databinding.FragmentLoginBinding;
import com.example.dragonbreath.login.data.AccessTokenResponse;
import com.example.dragonbreath.login.ui.LoginFragment;
import com.example.dragonbreath.login.ui.LoginViewModel;
import com.example.dragonbreath.register.ui.RegisterFragment;

public class LandingFragment  extends Fragment {
    private FragmentLandingBinding binding;
    private Button loginBtn;
    private TextView registerBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLandingBinding.inflate(inflater, container, false);
        initiate(binding);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment loginFragment = new RegisterFragment();
                replaceFragment(loginFragment);

            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment registerFragment = new RegisterFragment();
                replaceFragment(registerFragment);
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void initiate(FragmentLandingBinding binding) {
        loginBtn = binding.btnLandingLogin;
        registerBtn = binding.btnLandingRegister;
    }

    public void replaceFragment(Fragment fragment) {
        getParentFragmentManager().beginTransaction()
                .replace(R.id.landing_container, fragment)
                .addToBackStack(null)
                .commit();
    }
}
