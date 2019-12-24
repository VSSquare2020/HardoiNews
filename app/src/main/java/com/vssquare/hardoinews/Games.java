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

public class Games extends AppCompatActivity {

    WebView games_webView;
    RelativeLayout games_layout;
    ProgressBar games_progress;
    String games_url = "https://newshardoi.com/खेलकूद/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);
        games_layout = findViewById(R.id.games_layout);
        games_progress = findViewById(R.id.games_progress);
        games_webView  = findViewById(R.id.games_webView);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        games_progress.setVisibility(View.VISIBLE);
        if (CheckInternetConnection.IsNetworkAvailable(this)) {
            startWebView(games_url);

        }
        else {
            Snackbar.make(games_layout, "No Internet Connection", Snackbar.LENGTH_LONG).show();
            games_progress.setVisibility(View.GONE);
            getLayoutInflater().inflate(R.layout.errormessage,games_layout);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void startWebView(String url) {

        WebSettings settings = games_webView.getSettings();
        settings.setJavaScriptEnabled(true);
        games_progress.setVisibility(View.VISIBLE);
        games_webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                games_progress.setVisibility(View.VISIBLE);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                games_progress.setVisibility(View.GONE);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(Games.this, "Error:" + description, Toast.LENGTH_SHORT).show();
                games_progress.setVisibility(View.GONE);

            }
        });
        games_webView.loadUrl(url);
    }
    @Override
    public void onBackPressed() {
        if (games_webView.canGoBack()){
            this.games_webView.goBack();
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && this.games_webView.canGoBack()) {
            this.games_webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
