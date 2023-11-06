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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.dragonbreath.LocalStorage;
import com.example.dragonbreath.R;
import com.example.dragonbreath.databinding.FragmentProfileBinding;
import com.example.dragonbreath.login.ui.LoginActivity;
import com.example.dragonbreath.login.ui.LoginViewModel;

public class ProfileFragment extends Fragment {
    SharedPreferences sharedPreferences;
    private ProfileViewModel profileViewModel;
    private FragmentProfileBinding binding;
    private TextView fakeProfile;
    private Button logoutBtn;

//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater,container,false);

        Intent intent = new Intent(getActivity(), LoginActivity.class);

        sharedPreferences = LocalStorage.getInstance(getContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String token = sharedPreferences.getString("access_token", null);

        fakeProfile = binding.fake;
        logoutBtn = binding.btnLogout;

        fakeProfile.setText(token);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogoutDialog(getContext(),editor,intent);
               // if (sharedPreferences.getString("access_token", null) != null) {
//                    editor.remove("access_token");
//                    editor.apply();
                    //startActivity(intent);
               // }
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // Don't forget to set 'binding' to null
    }

    private void showLogoutDialog(Context context,SharedPreferences.Editor editor,Intent intent ) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context)
                .setTitle("Logout")
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        //do stuff onclick of POSITIVE
                       // Toast.makeText(getContext(), "You clicked on POSITIVE", Toast.LENGTH_LONG).show();
                        //processingAlert = false;
                        if (sharedPreferences.getString("access_token", null) != null) {
                            editor.remove("access_token");
                            editor.apply();
                            startActivity(intent);
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        //do stuff onclick of NEGATIVE
                        //Toast.makeText(getContext(), "You clicked on NEGATIVE", Toast.LENGTH_LONG).show();
                        //processingAlert = false;
                    }
                });

        alertDialog.show();
        alertDialog.setCancelable(false);

    }
}
