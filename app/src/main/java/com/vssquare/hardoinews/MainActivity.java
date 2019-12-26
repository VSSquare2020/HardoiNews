package com.vssquare.hardoinews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.FutureTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private RelativeLayout main_layout;
    private RecyclerView recyclerView;
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private ProgressBar progressBar;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<Model> list;
    static List<WPPost> mListPost;
    private RecyclerViewAdapter adapter;
    NavigationView navigationView;
    private  String baseURL = "https://newshardoi.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Navigation Bar
        main_layout = findViewById(R.id.main_layout);
        mDrawerlayout = findViewById(R.id.drawer);
        actionBarDrawerToggle =new ActionBarDrawerToggle(this,mDrawerlayout,R.string.open,R.string.close);
        mDrawerlayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        recyclerView = findViewById(R.id.recyclerview);
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);
        linearLayoutManager =new LinearLayoutManager(MainActivity.this,LinearLayoutManager.
                VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        list = new ArrayList<>();
        adapter= new RecyclerViewAdapter(list,MainActivity.this);
        recyclerView.setAdapter(adapter);
        if (CheckInternetConnection.IsNetworkAvailable(this)) {
            getRetrofit();
        }else{
            Snackbar.make(main_layout, "No Internet Connection!!", Snackbar.LENGTH_LONG).show();
            progressBar.setVisibility(View.GONE);
            getLayoutInflater().inflate(R.layout.errormessage,main_layout);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()){
            case R.id.action_settings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_exit:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void getRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitArrayApi service =retrofit.create(RetrofitArrayApi.class);
        Call<List<WPPost>> call= service.getPostInfo();

        call.enqueue(new Callback<List<WPPost>>() {
            @Override
            public void onResponse(Call<List<WPPost>> call, Response<List<WPPost>> response) {
                Log.e("mainactivity","response"+response.body());
                mListPost = response.body();

                progressBar.setVisibility(View.GONE);

                for (int i=0;i<response.body().size();i++){
                    String img = response.body().get(i).getLinks().getWpFeaturedmedia().get(0).getHref();
                    Log.e("main","title"+response.body().get(i).getTitle().getRendered() +""+
                            img);

                    String tempdetails = response.body().get(i).getExcerpt().getRendered();
                    tempdetails=tempdetails.replace("<p>","");
                    tempdetails=tempdetails.replace("</p>","");
                    tempdetails=tempdetails.replace("[&hellip","");


                    list.add(new Model(Model.IMAGE_TYPE, response.body().get(i).getTitle()
                            .getRendered(),tempdetails,response.body().get(i).getLinks()
                            .getWpFeaturedmedia().get(0).getHref()));

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<WPPost>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });


        }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id== R.id.desh){
            Intent intent = new Intent(MainActivity.this,Desh.class);
            startActivity(intent);
            mDrawerlayout.closeDrawer(GravityCompat.START);
        }
        else if (id == R.id.up){
            Intent intent = new Intent(MainActivity.this,Uttar_pradesh.class);
            startActivity(intent);
            mDrawerlayout.closeDrawer(GravityCompat.START);
        }
        else if (id == R.id.politics){
            Intent intent = new Intent(MainActivity.this,Politics.class);
            startActivity(intent);
            mDrawerlayout.closeDrawer(GravityCompat.START);
        }
        else if(id == R.id.education){
            Intent intent = new Intent(MainActivity.this,Education.class);
            startActivity(intent);
            mDrawerlayout.closeDrawer(GravityCompat.START);
        }
        else if(id == R.id.crime){
            Intent intent = new Intent(MainActivity.this,Crime.class);
            startActivity(intent);
            mDrawerlayout.closeDrawer(GravityCompat.START);
        }
        else if(id ==R.id.games){
            Intent intent = new Intent(MainActivity.this,Games.class);
            startActivity(intent);
            mDrawerlayout.closeDrawer(GravityCompat.START);
        }
        else if (id == R.id.health) {
            Intent intent = new Intent(MainActivity.this,Health.class);
            startActivity(intent);
            mDrawerlayout.closeDrawer(GravityCompat.START);
        }
        else if(id == R.id.share){
            try {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                String share_Message= "Download News Hardoi App,\n To get all the latest of Hardoi.\n\n";
                share_Message = share_Message + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID;
                shareIntent.putExtra(Intent.EXTRA_TEXT, share_Message);
                startActivity(Intent.createChooser(shareIntent, "Share With.."));
            } catch(Exception e) {
                //e.toString();
            }
            mDrawerlayout.closeDrawer(GravityCompat.START);
        }
        else {
            Intent intent = new Intent(MainActivity.this,Contact_Us.class);
            startActivity(intent);
            mDrawerlayout.closeDrawer(GravityCompat.START);
        }

        return false;
    }
}
