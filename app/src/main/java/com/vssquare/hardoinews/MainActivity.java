package com.vssquare.hardoinews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, NavigationView.OnNavigationItemSelectedListener{
    ListView listView;
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private List<Data_Model> jsonDataList = SplashScreen.model_datas;
    CommonAdapter adapter;
    public static final String POST_ID = "id";
    public static final String POST_TITLE = "title";
    public static final String POST_URL = "link";
    public static final String POST_IMAGE_URL = "featured";
    public static final String POST_DATE = "date";
    public static final String POST_AUTHOR = "author";
    public static final String POST_CATEGORY = "cat_name";
    NavigationView navigationView;

    SliderView sliderView;
    Main_Post_Slider main_post_slider;

    public MainActivity(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Navigation Bar

        listView = findViewById(R.id.listView);
        sliderView = findViewById(R.id.post_slider);
        mDrawerlayout = findViewById(R.id.drawer);
        actionBarDrawerToggle =new ActionBarDrawerToggle(this,mDrawerlayout,R.string.open,R.string.close);
        mDrawerlayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .build();
        ImageLoader.getInstance().init(config);

        main_post_slider = new Main_Post_Slider(getApplicationContext(),jsonDataList);
        sliderView.setSliderAdapter(main_post_slider);


        listView.setOnItemClickListener(this);
        adapter = new CommonAdapter(getApplication(),jsonDataList);
        listView.setAdapter(adapter);
//        if (CheckInternetConnection.IsNetworkAvailable(this)) {
//            getRetrofit();
//        }else{
//            Snackbar.make(main_layout, "No Internet Connection!!", Snackbar.LENGTH_LONG).show();
//            progressBar.setVisibility(View.GONE);
//            getLayoutInflater().inflate(R.layout.errormessage,main_layout);
//        }
        sliderView.setIndicatorAnimation(IndicatorAnimations.SLIDE); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.startAutoCycle();

        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                sliderView.setCurrentPagePosition(position);
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
        else if(id == R.id.exit_app){
            finish();
        }
        else {
            Intent intent = new Intent(MainActivity.this,Contact_Us.class);
            startActivity(intent);
            mDrawerlayout.closeDrawer(GravityCompat.START);
        }

        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        i = i + 4;
        Intent intent = new Intent(getApplication(),WPPostDetails.class);
        intent.putExtra(POST_ID,jsonDataList.get(i).getId());
        intent.putExtra(POST_TITLE,jsonDataList.get(i).getTitle_rendered());
        intent.putExtra(POST_URL,jsonDataList.get(i).getLink());
        intent.putExtra(POST_IMAGE_URL,jsonDataList.get(i).getFeatured_media_url());
        intent.putExtra(POST_DATE,jsonDataList.get(i).getDate());
        intent.putExtra(POST_AUTHOR,jsonDataList.get(i).getAuthor_name());
        intent.putExtra(POST_CATEGORY,jsonDataList.get(i).getCategory_name());
        startActivity(intent);
    }

}
