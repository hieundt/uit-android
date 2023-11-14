package com.example.dragonbreath.login.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.dragonbreath.LocalStorage;
import com.example.dragonbreath.MainActivity;
import com.example.dragonbreath.R;
import com.example.dragonbreath.databinding.FragmentLoginBinding;
import com.example.dragonbreath.login.data.AccessTokenResponse;
import com.example.dragonbreath.register.ui.RegisterFragment;

public class LoginFragment extends Fragment {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Intent navToMain;
    private FragmentLoginBinding binding;
    private LoginViewModel loginViewModel;
    private Button loginBtn;
    private LinearLayout registerOption;
    private EditText usernameEditText, passwordEditText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        initiate(binding);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                loginViewModel.login(username, password).observe(getViewLifecycleOwner(), new Observer<AccessTokenResponse>() {
                    @Override
                    public void onChanged(AccessTokenResponse response) {
                        if (response != null) {
                            editor.putString("access_token", response.accessToken);
                            editor.apply();
                            startActivity(navToMain);
                        } else {
                        }
                    }
                });
            }
        });

        registerOption.setOnClickListener(new View.OnClickListener() {
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

    private void initiate(FragmentLoginBinding binding) {
        sharedPreferences = LocalStorage.getInstance(getContext());
        editor = sharedPreferences.edit();
        navToMain = new Intent(getActivity(), MainActivity.class);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        loginBtn = binding.btnLogin;
        registerOption = binding.registerOption;
        usernameEditText = binding.username;
        passwordEditText = binding.password;
    }

    public void replaceFragment(Fragment fragment) {
        getParentFragmentManager().beginTransaction()
                .replace(R.id.landing_container, fragment)
                .addToBackStack(null)
                .commit();
    }
}