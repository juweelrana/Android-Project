package com.example.abcmovies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ShowUserName extends Fragment {

    TextView nameShow;
    Button signOut;
    Context context;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("users");

    public ShowUserName() { }

    public ShowUserName(Context context, FirebaseAuth mAuth) {
        this.mAuth=mAuth;
        this.context=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_user_name, container, false);

        nameShow = view.findViewById(R.id.nameView);
        signOut = view.findViewById(R.id.signOutBtn);

        String uId = mAuth.getCurrentUser().getUid();
        Query query = myRef.orderByChild("uId").equalTo(uId);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {
                    String firstName = snapshot.child(uId).child("fname").getValue(String.class);
                    String lastName = snapshot.child(uId).child("lname").getValue(String.class);
                    nameShow.setText(firstName+" "+lastName);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



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