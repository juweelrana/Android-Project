package com.example.abcmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Structure> list;
    private Context context;

    public MyAdapter(){}
    public MyAdapter(List<Structure> list, Context context){

        this.list = list;
        this.context = context;

        //Toast.makeText(context,"Come",Toast.LENGTH_LONG).show();
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
        holder.Vgenre.setText(structure.getGenre());
        holder.Vbudget.setText(Integer.toString(structure.getBudget()));
        holder.Vyear.setText(Integer.toString(structure.getYear()));
        holder.Vfilm.setText(structure.getFilm());
        holder.Vgross.setText(Integer.toString(structure.getGross()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView Vfilm, Vyear, Vgenre, Vbudget, Vgross;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Vfilm = itemView.findViewById(R.id.film);
            Vyear = itemView.findViewById(R.id.year);
            Vgenre = itemView.findViewById(R.id.genre);
            Vbudget = itemView.findViewById(R.id.budget);
            Vgross = itemView.findViewById(R.id.gross);
        }
    }
}
