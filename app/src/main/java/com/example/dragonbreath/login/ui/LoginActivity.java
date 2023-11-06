package com.example.dragonbreath.login.ui;

import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dragonbreath.LocalStorage;
import com.example.dragonbreath.MainActivity;
import com.example.dragonbreath.R;
import com.example.dragonbreath.databinding.ActivityLoginBinding;
import com.example.dragonbreath.databinding.ActivityMainBinding;
import com.example.dragonbreath.login.data.AccessTokenResponse;
import com.example.dragonbreath.login.data.AuthService;
import com.example.dragonbreath.login.data.AuthServiceRepository;

public class LoginActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    private ActivityLoginBinding binding;
    private LoginViewModel loginViewModel;
    private Button loginBtn;
    private EditText usernameEditText,passwordEditText ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = LocalStorage.getInstance(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loginBtn = binding.login;
        usernameEditText = binding.username;
        passwordEditText = binding.password;

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                loginViewModel.login(username, password).observe(LoginActivity.this, new Observer<AccessTokenResponse>() {
                    @Override
                    public void onChanged(AccessTokenResponse response) {
                        if (response != null) {
                            editor.putString("access_token", response.accessToken);
                            editor.apply();
                            startActivity(intent);
                        } else {
                        }
                    }
                });
            }
        });
    }
}