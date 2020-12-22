package com.example.abcmovies;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Structure> list;
    private Context context;
    private DatabaseReference myRef;
    int serialId=0;

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

        holder.favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.getInstance().getLogStatus()==false)
                {
                    Toast.makeText(context,"Log In First",Toast.LENGTH_LONG).show();
                }
                else
                {
                    myRef = FirebaseDatabase.getInstance().getReference("favbs");

                    String movieId = position+"";
                    String userId = MainActivity.getInstance().getmAuth().getCurrentUser().getUid();
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss, dd.MM.yy");
                    String currentDateandTime = sdf.format(new Date());

                    myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            serialId=(int) snapshot.getChildrenCount();
                            serialId = serialId+1;
                            FavStructure favStructure = new FavStructure(userId,currentDateandTime,movieId,serialId+"");
                            myRef.child(serialId+"").setValue(favStructure);
                            MainActivity.getInstance().addFavouritesInRight();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                    //Toast.makeText(context,movieId+" "+userId+" "+currentDateandTime,Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView Title, Genre, Year, Rating;
        ImageView Cover,favButton;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Title = itemView.findViewById(R.id.title);
            Cover = itemView.findViewById(R.id.cover);
            Genre = itemView.findViewById(R.id.genre);
            Year = itemView.findViewById(R.id.year);
            Rating = itemView.findViewById(R.id.rating);
            favButton = itemView.findViewById(R.id.fav);
        }
    }

    static class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
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
