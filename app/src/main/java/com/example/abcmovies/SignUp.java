package com.example.abcmovies;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends Fragment {

    EditText FirstName,LastName,Email,Password;
    Button Register;
    Context context;

    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;

    public SignUp() { }
    public SignUp(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        mAuth = FirebaseAuth.getInstance();

        FirstName = view.findViewById(R.id.firstName);
        LastName = view.findViewById(R.id.lastName);
        Email = view.findViewById(R.id.email);
        Password = view.findViewById(R.id.password);
        Register = view.findViewById(R.id.register);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userSignUp(v);
            }
        });

        return view;
    }

    void userSignUp(View v)
    {
        String uEmail = Email.getText().toString();
        String uPass = Password.getText().toString();

        mAuth.createUserWithEmailAndPassword(uEmail,uPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    insertData(v);
                    MainActivity.getInstance().addFavouritesInRight();
                }
                else Toast.makeText(context,"Failed",Toast.LENGTH_LONG).show();
            }
        });

    }

    private void insertData(View v)
    {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users");

        String uFname = FirstName.getText().toString();
        String uLname = LastName.getText().toString();
        String uId = mAuth.getCurrentUser().getUid();

        nameStructure userInfo = new nameStructure(uId,uFname,uLname);

        myRef.child(uId).setValue(userInfo);
        MainActivity.getInstance().setLogStatus(true);
        MainActivity.getInstance().setUserName(mAuth);
    }

    public class nameStructure
    {
        String uId,fname,lname;
        nameStructure(){}

        public nameStructure(String uId, String fname, String lname) {
            this.uId = uId;
            this.fname = fname;
            this.lname = lname;
        }

        public String getuId() {
            return uId;
        }

        public void setuId(String uId) {
            this.uId = uId;
        }

        public String getFname() {
            return fname;
        }

        public void setFname(String fname) {
            this.fname = fname;
        }

        public String getLname() {
            return lname;
        }

        public void setLname(String lname) {
            this.lname = lname;
        }
    }
}