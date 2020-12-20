package com.example.abcmovies;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SIgnIn extends Fragment {

    public SIgnIn() {

    }
    public SIgnIn(Context context) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_s_ign_in, container, false);

        return view;
    }
}