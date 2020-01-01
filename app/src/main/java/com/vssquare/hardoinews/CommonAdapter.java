package com.vssquare.hardoinews;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.List;

public class CommonAdapter extends BaseAdapter {

    private Context context;
    private List<Data_Model> jsonData;

    public CommonAdapter(Context context, List<Data_Model> jsonData) {
        this.context = context;
        this.jsonData = jsonData;
    }

    @Override
    public int getCount() {
        return jsonData.size()-4;
    }

    @Override
    public Object getItem(int position) {
        return jsonData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.postdetails, null);

            holder.post_image = convertView.findViewById(R.id.post_image);
            holder.post_title = convertView.findViewById(R.id.post_title);
            holder.post_date = convertView.findViewById(R.id.post_date);
            holder.post_author = convertView.findViewById(R.id.post_author);
            holder.category_txt = convertView.findViewById(R.id.category_Txt);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        position = position + 4;
        holder.post_title.setText(jsonData.get(position).getTitle_rendered());
        holder.post_author.setText(jsonData.get(position).getAuthor_name());
        holder.post_date.setText(jsonData.get(position).getDate());
        holder.category_txt.setText(jsonData.get(position).getCategory_name());

        final ViewHolder finalHolder = holder;
        ImageLoader.getInstance().displayImage(jsonData.get(position).getFeatured_media_url(), holder.post_image, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String s, View view) {
                //finalHolder.main_activity_progress.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {
                //finalHolder.main_activity_progress.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingCancelled(String s, View view) {

            }
        });
        return convertView;
    }
    private class ViewHolder{
        private ImageView post_image;
        private TextView post_date;
        private TextView post_title;
        private TextView post_author;
        private TextView category_txt;
    }
}
