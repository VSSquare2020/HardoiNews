package com.vssquare.hardoinews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class WPPostDetails extends AppCompatActivity {

    TextView textView;
    WebView webView;
    ProgressDialog progressDialog;
    RelativeLayout post_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wppost_details);
        post_layout = findViewById(R.id.postDetail_layout);
        webView = findViewById(R.id.postwebview);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent i = getIntent();
        int position = i.getExtras().getInt("itemPosition");

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        String post_url = MainActivity.mListPost.get(position).getLink();

        if (CheckInternetConnection.IsNetworkAvailable(this)) {
            startWebView(post_url);
            progressDialog.show();

        }
        else {
            Snackbar.make(post_layout, "No Internet Connection", Snackbar.LENGTH_LONG).show();
            progressDialog.dismiss();
            getLayoutInflater().inflate(R.layout.errormessage,post_layout);
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
        progressDialog.show();
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                progressDialog.show();
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progressDialog.dismiss();
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(WPPostDetails.this, "Error:" + description, Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });
        webView.loadUrl(url);
    }

    @Override
    public void onBackPressed() {
        if (this.webView.canGoBack()) {
            this.webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && this.webView.canGoBack()) {
            this.webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
