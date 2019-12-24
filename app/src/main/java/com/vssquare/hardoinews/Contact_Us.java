package com.vssquare.hardoinews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class Contact_Us extends AppCompatActivity {

    WebView contact_webView;
    String contact_url = "https://vssquareweb.web.app/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact__us);
        contact_webView = findViewById(R.id.contact_webView);
        if(CheckInternetConnection.IsNetworkAvailable(this)) {
            contact_webView.loadUrl(contact_url);
            contact_webView.getSettings().setJavaScriptEnabled(true);
            contact_webView.setWebViewClient(new WebViewClient());
        }
        else {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
