package com.example.airsense.view.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.airsense.R;
import com.example.airsense.databinding.FragmentRegisterBinding;
import com.example.airsense.view.login.LoginFragment;


public class RegisterFragment extends Fragment {
    private FragmentRegisterBinding binding;
    private LinearLayout loginOptionTxtBtn;
    private Button registerBtn;
    private EditText usernameEditText,emailEditText,passwordEditText,confirmPasswordEditText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        initiate(binding);

        loginOptionTxtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment loginFragment = new LoginFragment();
                replaceFragment(loginFragment);
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loading = new Intent(getActivity(), LoadingActivity.class);

                String username = usernameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();

                Bundle bundle = new Bundle();
                bundle.putString("username", username);
                bundle.putString("email", email);
                bundle.putString("password", password);
                bundle.putString("confirmPassword", confirmPassword);
                loading.putExtra("register", bundle);
                startActivity(loading);
            }
        });

        return binding.getRoot();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void initiate(FragmentRegisterBinding binding) {
        loginOptionTxtBtn = binding.txtBtnOptionLogin;
        registerBtn = binding.btnRegister;
        usernameEditText = binding.editRegisterUsername;
        emailEditText = binding.editRegisterEmail;
        passwordEditText =binding.editRegisterPassword;
        confirmPasswordEditText = binding.editRegisterConfirm;
    }

    public void replaceFragment(Fragment fragment) {
        getParentFragmentManager().beginTransaction()
                .replace(R.id.landing_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    public boolean validateRegistration(String username,String email,String password,String confirm) {
        return isEmailValid(email) &&
                isPasswordValid(password, confirm) &&
                isUsernameValid(username);
    }

    private boolean isEmailValid(String email) {
        return email != null && email.contains("@");
    }

    private boolean isPasswordValid(String password, String confirmPassword) {
        return password != null && confirmPassword != null &&
                password.equals(confirmPassword) &&
                password.length() >= 6;
    }

    private boolean isUsernameValid(String username) {
        return username != null;
    }
}

