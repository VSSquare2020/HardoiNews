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

public class Crime extends AppCompatActivity {

    WebView crime_wv;
    RelativeLayout crime_layout;
    ProgressBar crime_progress;
    String crime_url = "https://newshardoi.com/%e0%a4%95%e0%a5%8d%e0%a4%b0%e0%a4%be%e0%a4%87%e0%a4%ae/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime);
        crime_progress = findViewById(R.id.crime_progress);
        crime_layout = findViewById(R.id.crime_layout);
        crime_wv = findViewById(R.id.crime_webview);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        crime_progress.setVisibility(View.VISIBLE);
        if (CheckInternetConnection.IsNetworkAvailable(this)) {
            startWebView(crime_url);

        }
        else {
            Snackbar.make(crime_layout, "No Internet Connection", Snackbar.LENGTH_LONG).show();
            crime_progress.setVisibility(View.GONE);
            getLayoutInflater().inflate(R.layout.errormessage,crime_layout);
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

        WebSettings settings = crime_wv.getSettings();
        settings.setJavaScriptEnabled(true);
        crime_progress.setVisibility(View.VISIBLE);
        crime_wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                crime_progress.setVisibility(View.VISIBLE);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                crime_progress.setVisibility(View.GONE);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(Crime.this, "Error:" + description, Toast.LENGTH_SHORT).show();
                crime_progress.setVisibility(View.GONE);

            }
        });
        crime_wv.loadUrl(url);
    }
    @Override
    public void onBackPressed() {
        if (crime_wv.canGoBack()){
            this.crime_wv.goBack();
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && this.crime_wv.canGoBack()) {
            this.crime_wv.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
