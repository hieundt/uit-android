//package com.example.dragonbreath.login.ui;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.lifecycle.MutableLiveData;
//import androidx.lifecycle.Observer;
//import androidx.lifecycle.SavedStateHandle;
//import androidx.lifecycle.ViewModelProvider;
//
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//
//import com.example.dragonbreath.LocalStorage;
//import com.example.dragonbreath.R;
//import com.example.dragonbreath.databinding.FragmentLoginBinding;
//import com.example.dragonbreath.login.data.AccessTokenResponse;
//import com.example.dragonbreath.login.data.AuthService;
//import com.example.dragonbreath.login.data.AuthServiceRepository;
//
//public class LoginFragment extends Fragment {
//    SharedPreferences sharedPreferences;
//    private LoginViewModel loginViewModel;
//    private FragmentLoginBinding binding;
//    private Button loginBtn;
//    private EditText usernameEditText,passwordEditText ;
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        Context context = getContext();
//        sharedPreferences = LocalStorage.getInstance(context);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//
//        binding = FragmentLoginBinding.inflate(inflater, container, false);
//
//        loginBtn = binding.login;
//        usernameEditText = binding.username;
//        passwordEditText = binding.password;
//
//        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
//
//        loginBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String username = usernameEditText.getText().toString();
//                String password = passwordEditText.getText().toString();
//
//                loginViewModel.login(username, password).observe(getViewLifecycleOwner(), new Observer<AccessTokenResponse>() {
//                    @Override
//                    public void onChanged(AccessTokenResponse response) {
//                        if (response != null) {
//                            editor.putString("access_token", "your_access_token_here");
//                            editor.apply();
//
//                        } else {
//                        }
//                    }
//                });
//            }
//        });
//        return binding.getRoot();
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//    }
//}