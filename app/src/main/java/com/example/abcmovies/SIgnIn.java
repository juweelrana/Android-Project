package com.example.abcmovies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SIgnIn extends Fragment {

    EditText Email,Password;
    Button SignIn;
    Context context;

    private FirebaseAuth mAuth;

    public SIgnIn() {

    }
    public SIgnIn(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_s_ign_in, container, false);

        mAuth = FirebaseAuth.getInstance();

        Email = view.findViewById(R.id.email);
        Password = view.findViewById(R.id.password);
        SignIn = view.findViewById(R.id.logIn);

        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userSignIn(v);
            }
        });


        return view;
    }

    private void userSignIn(View v)
    {
        String uEmail = Email.getText().toString();
        String uPassword = Password.getText().toString();

        mAuth.signInWithEmailAndPassword(uEmail, uPassword)
                .addOnCompleteListener(MainActivity.getInstance(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            MainActivity.getInstance().addFavouritesInRight();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(context,"Faild!",Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }
}