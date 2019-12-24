package com.vssquare.hardoinews;

import androidx.appcompat.app.ActionBar;
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

public class Desh extends AppCompatActivity {

    WebView desh_webView;
    ProgressBar desh_progress;
    RelativeLayout deshLayout;
    String url ="https://newshardoi.com/देश/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desh);
        desh_progress = findViewById(R.id.desh_progress);
        desh_webView = findViewById(R.id.deshWeb);
        deshLayout = findViewById(R.id.deshLayout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        desh_progress.setVisibility(View.VISIBLE);
        if (CheckInternetConnection.IsNetworkAvailable(this)) {
            startWebView(url);

            }
            else {
            Snackbar.make(deshLayout, "No Internet Connection", Snackbar.LENGTH_LONG).show();
            desh_progress.setVisibility(View.GONE);
            getLayoutInflater().inflate(R.layout.errormessage,deshLayout);
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

        WebSettings settings = desh_webView.getSettings();
        settings.setJavaScriptEnabled(true);
        desh_progress.setVisibility(View.VISIBLE);
        desh_webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                desh_progress.setVisibility(View.VISIBLE);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                desh_progress.setVisibility(View.GONE);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(Desh.this, "Error:" + description, Toast.LENGTH_SHORT).show();
                desh_progress.setVisibility(View.GONE);

            }
        });
        desh_webView.loadUrl(url);
    }
            @Override
    public void onBackPressed() {
        if (desh_webView.canGoBack()){
            this.desh_webView.goBack();
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && this.desh_webView.canGoBack()) {
            this.desh_webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
