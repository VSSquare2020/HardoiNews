package com.vssquare.hardoinews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
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
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, NavigationView.OnNavigationItemSelectedListener{
    private RelativeLayout main_layout;
    ListView listView;
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private List<Data_Model> jsonDataList = SplashScreen.model_datas;
    //static List<WPPost> mListPost;
    CommonAdapter adapter;
    public static final String POST_ID = "id";
    public static final String POST_TITLE = "post_title";
    public static final String POST_URL = "url";
    public static final String POST_IMAGE_URL = "post_image";
    public static final String POST_DATE = "date";
    public static final String POST_AUTHOR = "author";
    NavigationView navigationView;

    public MainActivity(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Navigation Bar

        listView = findViewById(R.id.listView);

        main_layout = findViewById(R.id.main_layout);
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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(getApplication(),WPPostDetails.class);
        intent.putExtra(POST_ID,jsonDataList.get(i).getId());
        intent.putExtra(POST_TITLE,jsonDataList.get(i).getTitle_rendered());
        intent.putExtra(POST_URL,jsonDataList.get(i).getLink());
        intent.putExtra(POST_IMAGE_URL,jsonDataList.get(i).getFeatured_media_url());
        intent.putExtra(POST_DATE,jsonDataList.get(i).getDate());
        intent.putExtra(POST_AUTHOR,jsonDataList.get(i).getAuthor_name());
        //intent.putExtra(POST_DATE,dateConverter.getDate(jsonDataList.get(position).getDate())+" "+ dateConverter.getMonth(jsonDataList.get(position).getDate()));
        startActivity(intent);
    }
}
