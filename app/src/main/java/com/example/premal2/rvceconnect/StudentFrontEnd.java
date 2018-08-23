package com.example.premal2.rvceconnect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

public class StudentFrontEnd extends AppCompatActivity {

    private FirebaseAuth mAuth;
    int h;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_front_end);
        mAuth=FirebaseAuth.getInstance();
       // mAuth.signOut();
      //  finish();
      //  startActivity(new Intent(this,MainActivity.class));
    }
}
