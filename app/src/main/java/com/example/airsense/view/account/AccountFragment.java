package com.example.airsense.view.account;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.airsense.LandingActivity;
import com.example.airsense.R;
import com.example.airsense.data.apiclient.TokenManager;
import com.example.airsense.databinding.FragmentAccountBinding;


public class AccountFragment extends Fragment {

    private TokenManager tokenManager;
    private AccountViewModel accountViewModel;
    private FragmentAccountBinding binding;
    private RelativeLayout logoutBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAccountBinding.inflate(inflater,container,false);
        initiate(binding);
        logoutBtn = binding.btnLogout;

        Intent intent = new Intent(getActivity(), LandingActivity.class);

        String token = tokenManager.getAccessToken();

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showLogoutDialog(getContext(),intent);
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void initiate(FragmentAccountBinding binding) {
        tokenManager = TokenManager.getInstance(getContext());

    }

    private void showLogoutDialog(Context context,Intent intent) {
        View dialogView = LayoutInflater.from(context).inflate(R.layout.custom_dialog, null);

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context)
                .setTitle("Logout")
                .setView(dialogView) // Set the custom layout
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        tokenManager.logout();
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                });

        AlertDialog dialog = alertDialog.create();

        dialog.getWindow().setBackgroundDrawableResource(R.drawable.custom_flat_shape);
        dialog.show();
        dialog.setCancelable(false);
    }


}
