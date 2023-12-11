package com.example.airsense.view.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.airsense.MainActivity;
import com.example.airsense.R;
import com.example.airsense.data.apiclient.TokenManager;
import com.example.airsense.databinding.FragmentLoginBinding;
import com.example.airsense.domain.model.TokenResponse;
import com.example.airsense.view.register.RegisterFragment;

public class LoginFragment extends Fragment {
    private TokenManager tokenManager;
    private Intent navToMain;
    private FragmentLoginBinding binding;
    private LoginRepository loginRepository;
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
                loginRepository.login(username, password).observe(getViewLifecycleOwner(), new Observer<TokenResponse>() {
                    @Override
                    public void onChanged(TokenResponse response) {
                        if (response != null) {
                            tokenManager.login(
                                    response.accessToken,
                                    response.refreshToken,
                                    response.expiresIn,
                                    response.refreshExpiresIn
                            );
                            startActivity(navToMain);
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
        tokenManager = TokenManager.getInstance(getContext());
        navToMain = new Intent(getActivity(), MainActivity.class);
        loginRepository = new LoginRepository(getContext());
        loginBtn = binding.btnLogin;
        registerOption = binding.txtBtnOptionRegister;
        usernameEditText = binding.editLoginUsername;
        passwordEditText = binding.editLoginPassword;
    }

    public void replaceFragment(Fragment fragment) {
        getParentFragmentManager().beginTransaction()
                .replace(R.id.landing_container, fragment)
                .addToBackStack(null)
                .commit();
    }
}