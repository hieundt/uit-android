package com.example.dragonbreath.register.ui;

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
import androidx.fragment.app.FragmentManager;

import com.example.dragonbreath.R;
import com.example.dragonbreath.databinding.FragmentRegisterBinding;
import com.example.dragonbreath.login.ui.LoginFragment;


public class RegisterFragment extends Fragment {
    private FragmentRegisterBinding binding;
    private LinearLayout loginOption;
    private Button registerBtn;
    private EditText usernameEditText,emailEditText,passwordEditText,confirmPasswordEditText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        initiate(binding);

        loginOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment loginFragment = new LoginFragment();
                replaceFragment(loginFragment);
//                FragmentManager fragmentManager = getParentFragmentManager();
//                fragmentManager.popBackStack("register_fragment_tag", FragmentManager.POP_BACK_STACK_INCLUSIVE);
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
//                if (validateRegistration(username,email,password,confirmPassword)) {
//                    Bundle bundle = new Bundle();
//                    bundle.putString("username", username);
//                    bundle.putString("email", email);
//                    bundle.putString("password", password);
//                    bundle.putString("confirmPassword", confirmPassword);
//
//                    loading.putExtra("register", bundle);
//                    startActivity(loading);
//                } else {
//
//                }

            }
        });

        return binding.getRoot();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void initiate(FragmentRegisterBinding binding) {
        loginOption = binding.loginOption;
        registerBtn = binding.btnRegister;
        usernameEditText = binding.username;
        emailEditText = binding.email;
        passwordEditText =binding.password;
        confirmPasswordEditText = binding.confirmPassword;
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

