package com.vssquare.hardoinews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class Education extends AppCompatActivity {
    WebView education_webView;
    RelativeLayout education_layout;
    ProgressBar education_progress;
    String education_url = "https://newshardoi.com/शिक्षा/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);
        education_layout = findViewById(R.id.education_layout);
        education_progress = findViewById(R.id.education_progress);
        education_webView = findViewById(R.id.education_webView);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        education_progress.setVisibility(View.VISIBLE);
        if (CheckInternetConnection.IsNetworkAvailable(this)) {
            startWebView(education_url);

        }
        else {
            Snackbar.make(education_layout, "No Internet Connection", Snackbar.LENGTH_LONG).show();
            education_progress.setVisibility(View.GONE);
            getLayoutInflater().inflate(R.layout.errormessage,education_layout);
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()== android.R.id.home){
            onBackPressed();
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void startWebView(String url) {

        WebSettings settings = education_webView.getSettings();
        settings.setJavaScriptEnabled(true);
        education_progress.setVisibility(View.VISIBLE);
        education_webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                education_progress.setVisibility(View.VISIBLE);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                education_progress.setVisibility(View.GONE);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(Education.this, "Error:" + description, Toast.LENGTH_SHORT).show();
                education_progress.setVisibility(View.GONE);

            }
        });
        education_webView.loadUrl(url);
    }
    @Override
    public void onBackPressed() {
        if (education_webView.canGoBack()){
            this.education_webView.goBack();
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && this.education_webView.canGoBack()) {
            this.education_webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
