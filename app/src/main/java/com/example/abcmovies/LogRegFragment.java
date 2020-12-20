package com.example.abcmovies;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class LogRegFragment extends Fragment {

    Context context;
    Button signIn,signUp;

    public LogRegFragment() { }
    public LogRegFragment(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_log_reg, container, false);

        signIn = view.findViewById(R.id.signInBtn);
        signUp = view.findViewById(R.id.signUpBtn);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.getInstance().addSIgnUpInRight();
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.getInstance().addSIgnInInRight();
            }
        });

        return view;
    }
}