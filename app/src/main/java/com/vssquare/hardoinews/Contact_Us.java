package com.vssquare.hardoinews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Contact_Us extends AppCompatActivity {

    WebView contact_webView;
    String contact_url = "https://vssquareweb.web.app/";
    RelativeLayout contact_us_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact__us);

        contact_us_layout = findViewById(R.id.developer_contact_layout);
        contact_webView = findViewById(R.id.contact_webView);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if(CheckInternetConnection.IsNetworkAvailable(this)) {
            contact_webView.loadUrl(contact_url);
            contact_webView.getSettings().setJavaScriptEnabled(true);
            contact_webView.setWebViewClient(new WebViewClient());
        }
        else {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            getLayoutInflater().inflate(R.layout.errormessage,contact_us_layout);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
