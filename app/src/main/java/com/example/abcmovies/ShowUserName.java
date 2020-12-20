package com.example.abcmovies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class ShowUserName extends Fragment {

    String name;
    TextView nameShow;
    Button signOut;
    private FirebaseAuth mAuth;

    public ShowUserName() {

    }

    public ShowUserName(Context context, String name, FirebaseAuth mAuth) {
        this.name = name;
        this.mAuth=mAuth;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_user_name, container, false);

        nameShow = view.findViewById(R.id.nameView);
        signOut = view.findViewById(R.id.signOutBtn);
        nameShow.setText(name);

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.getInstance().signOut();
                MainActivity.getInstance().addLogRegInRight();
            }
        });

        return view;
    }
}