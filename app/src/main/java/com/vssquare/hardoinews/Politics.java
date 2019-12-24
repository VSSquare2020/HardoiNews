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

public class Politics extends AppCompatActivity {
    RelativeLayout politics_layout;
    ProgressBar politics_progress;
    WebView politics_webView;
    String politics_url = "https://newshardoi.com/राजनीति/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_politics);
        politics_layout = findViewById(R.id.politics_layout);
        politics_progress = findViewById(R.id.politics_progress);
        politics_webView = findViewById(R.id.politics_webView);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        politics_progress.setVisibility(View.VISIBLE);
        if (CheckInternetConnection.IsNetworkAvailable(this)) {
            startWebView(politics_url);

        }
        else {
            Snackbar.make(politics_layout, "No Internet Connection", Snackbar.LENGTH_LONG).show();
            politics_progress.setVisibility(View.GONE);
            getLayoutInflater().inflate(R.layout.errormessage,politics_layout);
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

        WebSettings settings = politics_webView.getSettings();
        settings.setJavaScriptEnabled(true);
        politics_progress.setVisibility(View.VISIBLE);
        politics_webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                politics_progress.setVisibility(View.VISIBLE);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                politics_progress.setVisibility(View.GONE);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(Politics.this, "Error:" + description, Toast.LENGTH_SHORT).show();
                politics_progress.setVisibility(View.GONE);

            }
        });
        politics_webView.loadUrl(url);
    }
    @Override
    public void onBackPressed() {
        if (politics_webView.canGoBack()){
            this.politics_webView.goBack();
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && this.politics_webView.canGoBack()) {
            this.politics_webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
