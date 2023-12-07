package com.example.airsense.view.register;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.airsense.MainActivity;
import com.example.airsense.data.apiclient.ApiClient;
import com.example.airsense.databinding.ActivityLoadingBinding;
import com.example.airsense.domain.model.TokenResponse;
import com.example.airsense.domain.service.TokenService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadingActivity extends AppCompatActivity {
    private ActivityLoadingBinding binding;
    private TokenService tokenService;
    private WebView webView;
    private ProgressBar progressBar;
    private String username;
    private String email;
    private String password;
    private String confirmPassword;

    @SuppressLint({"MissingInflatedId", "SetJavaScriptEnabled"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoadingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initiate();

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (url.contains("openid-connect/auth"))
                {
                    String script = "javascript:window.open(document.querySelector('a').href);";
                    view.loadUrl(script);
                }

                if (url.contains("login-actions/registration"))
                {
                    String errorScript = "document.getElementsByClassName('helper-text')[0].getAttribute('data-error');";
                    view.evaluateJavascript(errorScript, err -> {
                        if (err.equals("null"))
                        {
                            String script = "javascript:document.getElementById('username').value = '" + username + "';" +
                                    "document.getElementById('email').value = '" + email + "';" +
                                    "document.getElementById('password').value = '" + password + "';" +
                                    "document.getElementById('password-confirm').value = '" + confirmPassword + "';" +
                                    "document.getElementById('kc-register-form').submit();";
                            view.evaluateJavascript(script,null);
                        }
                        else
                        {
                            Log.d("register error: ", err);
                        }
                    });
                }

                if (url.contains("manager"))
                {
                    navigateToMain();
                }
            }
        });

        webView.setWebChromeClient(new WebChromeClient()
        {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                progressBar.setProgress(newProgress);
            }
        });

        webView.loadUrl("https://uiot.ixxc.dev/");
    }

    private void initiate()
    {
        CookieManager.getInstance().removeAllCookies(null);
        tokenService = ApiClient.getClient(this).create(TokenService.class);
        webView = new WebView(getBaseContext());
        progressBar = binding.progressBar;

        Intent register = getIntent();
        Bundle bundle = register.getBundleExtra("register");
        username = bundle.getString("username");
        email = bundle.getString("email");
        password = bundle.getString("password");
        confirmPassword = bundle.getString("confirmPassword");
    }

    private void navigateToMain()
    {
        Call<TokenResponse> call = tokenService.getToken("openremote", "password", username, password);
        call.enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                if (response.isSuccessful()) {
                    Intent navToMain = new Intent(LoadingActivity.this, MainActivity.class);
                    startActivity(navToMain);
                } else {
                }
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                // Handle network errors
            }
        });
    }
}
