package com.example.abcmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static MainActivity instance;
    private String name;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance = this;


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fLayout,new ShowMovies(getApplicationContext())).addToBackStack(null).commit();

        addLogRegInRight();
    }

    public static MainActivity getInstance() {
        return instance;
    }

    public void setUserName(String name,FirebaseAuth mAuth)
    {
        this.name=name;
        this.mAuth=mAuth;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.logReg,new ShowUserName(getApplicationContext(),name,mAuth)).addToBackStack(null).commit();

    }

    public void addLogRegInRight()
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ftLogReg = fragmentManager.beginTransaction();
        ftLogReg.replace(R.id.logReg,new LogRegFragment(getApplicationContext())).addToBackStack(null).commit();
    }

    public void addFavouritesInRight()
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fLayout2,new Favourites(getApplicationContext())).addToBackStack(null).commit();

    }

    public void addSIgnUpInRight()
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fLayout2,new SignUp(getApplicationContext())).addToBackStack(null).commit();

    }

    public void addSIgnInInRight()
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fLayout2,new SIgnIn(getApplicationContext())).addToBackStack(null).commit();

    }
}