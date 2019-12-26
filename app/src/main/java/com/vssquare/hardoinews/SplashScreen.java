package com.vssquare.hardoinews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SplashScreen extends AppCompatActivity {
    private String url = Const.url;
    public static ArrayList<Data_Model> model_datas ;
    ImageView splash_logo;
    RelativeLayout splash_layout;
    ProgressBar splash_progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        splash_logo = findViewById(R.id.splash_logo);
        splash_progress = findViewById(R.id.splash_progress);
        splash_layout = findViewById(R.id.splash_layout);
        splash_logo.startAnimation(AnimationUtils.loadAnimation(SplashScreen.this,R.anim.slide_down));
        if (CheckInternetConnection.IsNetworkAvailable(this)){
            model_datas = new ArrayList<>();

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String s) {
                    try {
                        Gson gson = new Gson();
                        JSONArray ParentArray = new JSONArray(s);
                        for (int i= 0;i<ParentArray.length();i++){
                            JSONObject ParentObject = ParentArray.getJSONObject(i);
                            Data_Model data_model = gson.fromJson(ParentObject.toString(), Data_Model.class);
                            data_model.setId(ParentObject.getInt("id"));
                            data_model.setDate(ParentObject.getString("date").substring(0,10));
                            data_model.setLink(ParentObject.getString("link"));
                            data_model.setTitle_rendered(ParentObject.getJSONObject("title").getString("rendered"));
                            data_model.setFeatured_media_url(ParentObject.getString("featuredimage"));
                            data_model.setAuthor_name(ParentObject.getJSONObject("pwapp_author").getString("name"));
                            model_datas.add(data_model);
                        }
                    }catch (JSONException e) {
                        e.printStackTrace();
                    }
                    finally {
                        if (model_datas!=null) {
                            splash_progress.setVisibility(View.GONE);
                            Intent intent = new Intent(getApplicationContext(), PermissionTransferToHomeActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    volleyError.printStackTrace();
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.toast_network_error), Toast.LENGTH_LONG).show();
                }
            });
            SingletonVolley.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
        }
        else{
                splash_progress.setVisibility(View.GONE);
                Snackbar snackbar = Snackbar.make(splash_layout, "Ops!! Please check Internet.", Snackbar.LENGTH_INDEFINITE);
                snackbar.setAction("Retry", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        recreate();
                    }
                }).show();
            }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
            finish();
    }
}
