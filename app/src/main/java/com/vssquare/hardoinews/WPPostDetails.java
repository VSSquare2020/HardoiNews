package com.vssquare.hardoinews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class WPPostDetails extends AppCompatActivity {

    TextView details_post_title,details_post_author,details_post_date;
    WebView webView;
    ImageView details_post_image;
    ProgressBar details_post_progress;
    ProgressDialog progressDialog;
    RelativeLayout post_layout;
    public static final String POST_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wppost_details);
        post_layout = findViewById(R.id.postDetail_layout);
        webView = findViewById(R.id.postwebview);
        details_post_image = findViewById(R.id.detail_post_image);
        details_post_title = findViewById(R.id.details_post_title);
        details_post_author = findViewById(R.id.details_post_author);
        details_post_date = findViewById(R.id.details_post_date);
        details_post_progress = findViewById(R.id.details_post_progress);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent i = getIntent();
        int position = i.getExtras().getInt(POST_ID);
        final WebviewLoader webViewLoader = new WebviewLoader(webView);
        webViewLoader.setWebSettings();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
       // String post_url = MainActivity.mListPost.get(position).getLink();

        if (CheckInternetConnection.IsNetworkAvailable(this)) {
            String url = Const.get_content_by_id.replace("POST_ID",String.valueOf(position));
            progressDialog.show();

            StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String s) {
                    try {
                        JSONObject ParentObject = new JSONObject(s);
                        webViewLoader.setLoadDataWithBaseUrl(ParentObject.getJSONObject("content").getString("rendered"));
                        details_post_title.setText(ParentObject.getJSONObject("title").getString("rendered"));
                        details_post_author.setText(ParentObject.getJSONObject("pwapp_author").getString("name"));
                        details_post_date.setText(" | " + ParentObject.getString("date").substring(0,10));

                        String image_url = ParentObject.getString("featuredimage");

                        //Setting Image to the ImageView
                        ImageLoader.getInstance().displayImage(image_url, details_post_image, new ImageLoadingListener() {
                                    @Override
                                    public void onLoadingStarted(String imageUri, View view) {

                                    }

                                    @Override
                                    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

                                    }

                                    @Override
                                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                                        details_post_progress.setVisibility(View.GONE);
                                    }

                                    @Override
                                    public void onLoadingCancelled(String imageUri, View view) {

                                    }
                                });

                                progressDialog.dismiss();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    progressDialog.dismiss();
                    Toast.makeText(WPPostDetails.this,"Unable to Load", Toast.LENGTH_LONG).show();
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
