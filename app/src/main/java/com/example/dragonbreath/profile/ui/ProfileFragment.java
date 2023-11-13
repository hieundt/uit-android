package com.example.dragonbreath.profile.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.dragonbreath.LandingActivity;
import com.example.dragonbreath.LocalStorage;
import com.example.dragonbreath.R;
import com.example.dragonbreath.databinding.FragmentProfileBinding;
import com.example.dragonbreath.login.ui.LoginFragment;

public class ProfileFragment extends Fragment {
    private SharedPreferences sharedPreferences;
    private ProfileViewModel profileViewModel;
    private  FragmentTransaction transaction;
    private FragmentProfileBinding binding;
    private TextView fakeProfile;
    private Button logoutBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater,container,false);
        fakeProfile = binding.fake;
        logoutBtn = binding.btnLogout;

        Intent intent = new Intent(getActivity(), LandingActivity.class);

        sharedPreferences = LocalStorage.getInstance(getContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String token = sharedPreferences.getString("access_token", null);

        fakeProfile.setText(token);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogoutDialog(getContext(),editor,intent);
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void showLogoutDialog(Context context,SharedPreferences.Editor editor,Intent intent) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context)
                .setTitle("Logout")
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        if (sharedPreferences.getString("access_token", null) != null) {
                            editor.remove("access_token");
                            editor.apply();
                            startActivity(intent);
                            //transaction.commit();
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                });
        alertDialog.show();
        alertDialog.setCancelable(false);
    }
}
