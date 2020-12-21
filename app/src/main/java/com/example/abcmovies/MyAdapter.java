package com.example.abcmovies;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Structure> list;
    private Context context;

    public MyAdapter(){}
    public MyAdapter(List<Structure> list, Context context){

        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_design,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        Structure structure = list.get(position);
        holder.Title.setText(structure.getTitle());
        holder.Year.setText(structure.getYear()+"");
        holder.Genre.setText(structure.getGenre());
        holder.Rating.setText(structure.getRating()+"");
        new DownloadImageTask(holder.Cover).execute(structure.getUrl());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView Title, Genre, Year, Rating;
        ImageView Cover;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Title = itemView.findViewById(R.id.title);
            Cover = itemView.findViewById(R.id.cover);
            Genre = itemView.findViewById(R.id.genre);
            Year = itemView.findViewById(R.id.year);
            Rating = itemView.findViewById(R.id.rating);

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
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
