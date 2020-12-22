package com.example.abcmovies;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Favourites extends Fragment {

    ListView listView;
    Context context;
    private List<FavStructure> favList;
    private List<Structure> list;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("favbs");

    public Favourites() {
    }
    public Favourites(Context context, FirebaseAuth mAuth)
    {
        this.context = context;
        this.mAuth=mAuth;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.favourites, container, false);

        listView = view.findViewById(R.id.list_View);

        favList = new ArrayList<>();

        Query query = myRef.orderByChild("uId").equalTo(mAuth.getCurrentUser().getUid());

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {
                    for (DataSnapshot data:snapshot.getChildren())
                    {
                        FavStructure favStructure = data.getValue(FavStructure.class);
                        favList.add(favStructure);
                    }
                    CustomAdapter customAdapter = new CustomAdapter();
                    listView.setAdapter(customAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //Toast.makeText(context,favList.size()+" = ",Toast.LENGTH_LONG).show();

        return view;
    }

    private class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return favList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.fav_row_design,null);

            TextView Title, Genre, Year,Time;
            ImageView Cover;

            list = MainActivity.getInstance().getList();

            Title = view.findViewById(R.id.title);
            Cover = view.findViewById(R.id.cover);
            Genre = view.findViewById(R.id.genre);
            Year = view.findViewById(R.id.year);
            Time = view.findViewById(R.id.time);

            Time.setText(favList.get(position).getcTime());
            int pos = Integer.parseInt(favList.get(position).getFilmId());
            Title.setText(list.get(pos).getTitle());
            Year.setText(list.get(pos).getYear()+"");
            Genre.setText(list.get(pos).getGenre());
            new MyAdapter.DownloadImageTask(Cover).execute(list.get(pos).getUrl());
            return view;
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
