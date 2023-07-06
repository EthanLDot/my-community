package com.example.mycommunity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    List<News> mData;
    Context context;

    public RecyclerViewAdapter(Context context, List<News> mData) {
        this.mData = mData;
        this.context = context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.news_card,parent,false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        News newsList = mData.get(position);
        holder.ntitle.setText(newsList.getTitle());
        holder.nsubtitle.setText(newsList.getSubtitle());
        Glide
                .with(context)
                .load(newsList.getImage())
                .centerCrop()
                .into(holder.nimage);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView ntitle, nsubtitle;
        ImageView nimage;

        public MyViewHolder(View itemView) {
            super(itemView);
            ntitle = itemView.findViewById(R.id.newsTitle);
            nsubtitle = itemView.findViewById(R.id.newsDescription);
            nimage = itemView.findViewById(R.id.newsImage);
        }
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}