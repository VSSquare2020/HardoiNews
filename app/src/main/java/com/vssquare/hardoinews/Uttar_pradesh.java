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

public class Uttar_pradesh extends AppCompatActivity {

    WebView uttar_Pradesh_wv;
    ProgressBar up_progress;
    RelativeLayout up_layout;
    String up_url = "https://newshardoi.com/उत्तर-प्रदेश/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uttar_pradesh);
        up_progress = findViewById(R.id.up_progress);
        up_layout = findViewById(R.id.up_layout);
        uttar_Pradesh_wv = findViewById(R.id.uttar_pradesh_webView);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        uttar_Pradesh_wv.setVisibility(View.VISIBLE);
        if (CheckInternetConnection.IsNetworkAvailable(this)) {
            startWebView(up_url);

        }
        else {
            Snackbar.make(up_layout, "No Internet Connection", Snackbar.LENGTH_LONG).show();
            uttar_Pradesh_wv.setVisibility(View.GONE);
            getLayoutInflater().inflate(R.layout.errormessage,up_layout);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    private void startWebView(String url) {

        WebSettings settings = uttar_Pradesh_wv.getSettings();
        settings.setJavaScriptEnabled(true);
        up_progress.setVisibility(View.VISIBLE);
        uttar_Pradesh_wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                up_progress.setVisibility(View.VISIBLE);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                up_progress.setVisibility(View.GONE);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(Uttar_pradesh.this, "Error:" + description, Toast.LENGTH_SHORT).show();
                up_progress.setVisibility(View.GONE);

            }
        });
        uttar_Pradesh_wv.loadUrl(url);
    }
    @Override
    public void onBackPressed() {
        if (uttar_Pradesh_wv.canGoBack()){
            this.uttar_Pradesh_wv.goBack();
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && this.uttar_Pradesh_wv.canGoBack()) {
            this.uttar_Pradesh_wv.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

