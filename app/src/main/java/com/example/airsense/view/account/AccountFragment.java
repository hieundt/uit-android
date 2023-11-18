package com.example.airsense.view.account;

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

import com.example.airsense.LandingActivity;
import com.example.airsense.LocalStorage;
import com.example.airsense.databinding.FragmentAccountBinding;


public class AccountFragment extends Fragment {
    private SharedPreferences sharedPreferences;
    private AccountViewModel accountViewModel;
    private  FragmentTransaction transaction;
    private FragmentAccountBinding binding;
    private TextView fakeProfile;
    private Button logoutBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAccountBinding.inflate(inflater,container,false);
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
