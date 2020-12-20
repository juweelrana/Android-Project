package com.example.abcmovies;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ShowMovies extends Fragment {

    private List<Structure> list;
    private RecyclerView recyclerView;
    private Context context;
    private String url = "https://gist.githubusercontent.com/BakiAdol/c4b4d6ce0f4db2b2c3fc3fa18a7f957d/raw/801dec1b5f3f75d97a13cb8783628735f365b247/movies.json";


    public ShowMovies(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_show_movies, container, false);

        list = new ArrayList<>();
        recyclerView = view.findViewById(R.id.reView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));



        parseData();

        return view;
    }

    private void parseData()
    {
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for(int i=0;i<response.length();i++)
                    {
                        JSONObject data = response.getJSONObject(i);
                        String title = data.getString("Title");
                        String cUrl = data.getString("Cover");
                        int year = data.getInt("Year");
                        String genre = data.getString("Genre");
                        double rating = data.getDouble("Rating");

                        list.add(new Structure(title,cUrl,genre,year,rating));
                    }
                    //Toast.makeText(context,list.size()+"",Toast.LENGTH_LONG).show();
                    recyclerView.setAdapter(new MyAdapter(list,context));

                }catch (Exception e){}
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"faild",Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(jsonArrayRequest);
    }
}