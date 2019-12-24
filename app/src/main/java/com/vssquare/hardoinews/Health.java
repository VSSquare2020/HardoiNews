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

public class Health extends AppCompatActivity {

    WebView health_webView;
    RelativeLayout health_layout;
    ProgressBar health_progress;
    String health_url = "https://newshardoi.com/%e0%a4%b8%e0%a5%8d%e0%a4%b5%e0%a4%be%e0%a4%b8%e0%a5%8d%e0%a4%a5%e0%a5%8d%e0%a4%af/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);
        health_layout = findViewById(R.id.health_layout);
        health_progress = findViewById(R.id.health_progress);
        health_webView = findViewById(R.id.health_webView);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        health_progress.setVisibility(View.VISIBLE);

        if (CheckInternetConnection.IsNetworkAvailable(this)) {
            startWebView(health_url);

        }
        else {
            Snackbar.make(health_layout, "No Internet Connection", Snackbar.LENGTH_LONG).show();
            health_progress.setVisibility(View.GONE);
            getLayoutInflater().inflate(R.layout.errormessage,health_layout);
        }


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()== android.R.id.home){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void startWebView(String url) {

        WebSettings settings = health_webView.getSettings();
        settings.setJavaScriptEnabled(true);
        health_progress.setVisibility(View.VISIBLE);
        health_webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                health_progress.setVisibility(View.VISIBLE);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                health_progress.setVisibility(View.GONE);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(Health.this, "Error:" + description, Toast.LENGTH_SHORT).show();
                health_progress.setVisibility(View.GONE);

            }
        });
        health_webView.loadUrl(url);
    }
    @Override
    public void onBackPressed() {
        if (health_webView.canGoBack()){
            this.health_webView.goBack();
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && this.health_webView.canGoBack()) {
            this.health_webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
