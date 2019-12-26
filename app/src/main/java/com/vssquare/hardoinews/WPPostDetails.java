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

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

public class WPPostDetails extends AppCompatActivity {

    TextView textView;
    WebView webView;
    ProgressDialog progressDialog;
    RelativeLayout post_layout;
    public static final String POST_ID = "id";
    public static final String POST_TITLE = "post_title";
    public static final String POST_URL = "url";
    public static final String POST_IMAGE_URL = "post_image";
    public static final String POST_DATE = "date";
    public static final String POST_AUTHOR = "author";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wppost_details);
        post_layout = findViewById(R.id.postDetail_layout);
        webView = findViewById(R.id.postwebview);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent i = getIntent();
        int position = i.getExtras().getInt(POST_ID);

        final WebviewLoader webViewLoader = new WebviewLoader(webView);
        webViewLoader.setWebSettings();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        String post_url = MainActivity.mListPost.get(position).getLink();

        if (CheckInternetConnection.IsNetworkAvailable(this)) {
            String url = Const.get_content_by_id.replace("POST_ID",String.valueOf(position));
            progressDialog.show();
            StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String s) {
                    try {
                        JSONObject ParentObject = new JSONObject(s);
                        webViewLoader.setLoadDataWithBaseUrl(ParentObject.getJSONObject("content").getString("rendered"));
                        progressDialog.dismiss();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    progressDialog.dismiss();
                    Toast.makeText(WPPostDetails.this,"Unable to Load Data", Toast.LENGTH_LONG).show();
                }
            });
            SingletonVolley.getInstance(getApplicationContext()).addToRequestQueue(request);


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


    @Override
    public void onBackPressed() {
        if (this.webView.canGoBack()) {
            this.webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
