package com.vssquare.hardoinews;

import android.content.Context;
import android.content.Intent;
import android.transition.Slide;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class Main_Post_Slider extends SliderViewAdapter<Main_Post_Slider.MainPostAdapter> {

    private Context context;
    private List<Data_Model> jsonData;
    public static final String POST_ID = "id";
    public static final String POST_TITLE = "title";
    public static final String POST_URL = "link";
    public static final String POST_IMAGE_URL = "featured";
    public static final String POST_DATE = "date";
    public static final String POST_AUTHOR = "author";
    public static final String POST_CATEGORY = "cat_name";

    public Main_Post_Slider(Context context,List<Data_Model> jsonData) {
        this.context = context;
        this.jsonData = jsonData;
    }

    @Override
    public MainPostAdapter onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_post_slider, null);
        return new MainPostAdapter(inflate);
    }

    @Override
    public void onBindViewHolder(final MainPostAdapter viewHolder, final int position) {
        final Intent slider_post_details = new Intent(context,WPPostDetails.class);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CheckInternetConnection.IsNetworkAvailable(context)) {
                    switch (position) {
                        case 0:
                            slider_post_details.putExtra(POST_ID, jsonData.get(0).getId());
                            slider_post_details.putExtra(POST_TITLE, jsonData.get(0).getTitle_rendered());
                            slider_post_details.putExtra(POST_URL, jsonData.get(0).getLink());
                            slider_post_details.putExtra(POST_IMAGE_URL, jsonData.get(0).getFeatured_media_url());
                            slider_post_details.putExtra(POST_DATE, jsonData.get(0).getDate());
                            slider_post_details.putExtra(POST_AUTHOR, jsonData.get(0).getAuthor_name());
                            slider_post_details.putExtra(POST_CATEGORY, jsonData.get(0).getCategory_name());
                            slider_post_details.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(slider_post_details);
                            break;
                        case 1:
                            slider_post_details.putExtra(POST_ID, jsonData.get(1).getId());
                            slider_post_details.putExtra(POST_TITLE, jsonData.get(1).getTitle_rendered());
                            slider_post_details.putExtra(POST_URL, jsonData.get(1).getLink());
                            slider_post_details.putExtra(POST_IMAGE_URL, jsonData.get(1).getFeatured_media_url());
                            slider_post_details.putExtra(POST_DATE, jsonData.get(1).getDate());
                            slider_post_details.putExtra(POST_AUTHOR, jsonData.get(1).getAuthor_name());
                            slider_post_details.putExtra(POST_CATEGORY, jsonData.get(1).getCategory_name());
                            slider_post_details.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(slider_post_details);
                            break;
                        case 2:
                            slider_post_details.putExtra(POST_ID, jsonData.get(2).getId());
                            slider_post_details.putExtra(POST_TITLE, jsonData.get(2).getTitle_rendered());
                            slider_post_details.putExtra(POST_URL, jsonData.get(2).getLink());
                            slider_post_details.putExtra(POST_IMAGE_URL, jsonData.get(2).getFeatured_media_url());
                            slider_post_details.putExtra(POST_DATE, jsonData.get(2).getDate());
                            slider_post_details.putExtra(POST_AUTHOR, jsonData.get(2).getAuthor_name());
                            slider_post_details.putExtra(POST_CATEGORY, jsonData.get(2).getCategory_name());
                            slider_post_details.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(slider_post_details);
                            break;
                        case 3:
                            slider_post_details.putExtra(POST_ID, jsonData.get(3).getId());
                            slider_post_details.putExtra(POST_TITLE, jsonData.get(3).getTitle_rendered());
                            slider_post_details.putExtra(POST_URL, jsonData.get(3).getLink());
                            slider_post_details.putExtra(POST_IMAGE_URL, jsonData.get(3).getFeatured_media_url());
                            slider_post_details.putExtra(POST_DATE, jsonData.get(3).getDate());
                            slider_post_details.putExtra(POST_AUTHOR, jsonData.get(3).getAuthor_name());
                            slider_post_details.putExtra(POST_CATEGORY, jsonData.get(3).getCategory_name());
                            slider_post_details.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(slider_post_details);
                            break;
                    }
                }else{
                    Toast.makeText(context, "No Internet Connection!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
            switch (position) {
                case 0:
                    Glide.with(viewHolder.itemView)
                            .load(jsonData.get(0).getFeatured_media_url())
                            .into(viewHolder.sliderViewImage);
                    viewHolder.slider_title.setText(jsonData.get(0).getTitle_rendered());
                    viewHolder.slider_category.setText(jsonData.get(0).getCategory_name());
                    viewHolder.slider_author.setText(jsonData.get(0).getAuthor_name());
                    viewHolder.slider_date.setText(" | "+jsonData.get(0).getDate());
                    break;
                case 1:
                    Glide.with(viewHolder.itemView)
                            .load(jsonData.get(1).getFeatured_media_url())
                            .into(viewHolder.sliderViewImage);
                    viewHolder.slider_title.setText(jsonData.get(1).getTitle_rendered());
                    viewHolder.slider_category.setText(jsonData.get(1).getCategory_name());
                    viewHolder.slider_author.setText(jsonData.get(1).getAuthor_name());
                    viewHolder.slider_date.setText(" | "+jsonData.get(1).getDate());
                    break;
                case 2:
                    Glide.with(viewHolder.itemView)
                            .load(jsonData.get(2).getFeatured_media_url())
                            .into(viewHolder.sliderViewImage);
                    viewHolder.slider_title.setText(jsonData.get(2).getTitle_rendered());
                    viewHolder.slider_category.setText(jsonData.get(2).getCategory_name());
                    viewHolder.slider_author.setText(jsonData.get(2).getAuthor_name());
                    viewHolder.slider_date.setText(" | "+jsonData.get(2).getDate());
                    break;
                case 3:
                    Glide.with(viewHolder.itemView)
                            .load(jsonData.get(3).getFeatured_media_url())
                            .into(viewHolder.sliderViewImage);
                    viewHolder.slider_title.setText(jsonData.get(3).getTitle_rendered());
                    viewHolder.slider_category.setText(jsonData.get(3).getCategory_name());
                    viewHolder.slider_author.setText(jsonData.get(3).getAuthor_name());
                    viewHolder.slider_date.setText(" | "+jsonData.get(3).getDate());
                    break;

            }


    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return 4;
    }

    class MainPostAdapter extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView sliderViewImage;
        TextView slider_title,slider_author,slider_date,slider_category;

        public MainPostAdapter(View itemView) {
            super(itemView);
            sliderViewImage = itemView.findViewById(R.id.iv_auto_image_slider);
            slider_title = itemView.findViewById(R.id.slider_post_title);
            slider_author = itemView.findViewById(R.id.slider_authorTxt);
            slider_date = itemView.findViewById(R.id.slider_dateTxt);
            slider_category = itemView.findViewById(R.id.slider_categoryTxt);
            this.itemView = itemView;
        }
    }
}
