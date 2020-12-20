package com.example.abcmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static MainActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance = this;


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fLayout,new ShowMovies(getApplicationContext())).addToBackStack(null).commit();

        FragmentTransaction rightArea = fragmentManager.beginTransaction();
        rightArea.replace(R.id.fLayout2,new Favourites(getApplicationContext())).addToBackStack(null).commit();

        FragmentTransaction ftLogReg = fragmentManager.beginTransaction();
        ftLogReg.replace(R.id.logReg,new LogRegFragment(getApplicationContext())).addToBackStack(null).commit();



    }

    public static MainActivity getInstance() {
        return instance;
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