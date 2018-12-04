package com.example.premal2.rvceconnect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

import de.hdodenhof.circleimageview.CircleImageView;

public class DeveloperInfo extends AppCompatActivity {

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_info);
        Button logout=(Button) findViewById(R.id.logout);
        mAuth=FirebaseAuth.getInstance();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(DeveloperInfo.this,MainActivity.class));
            }
        });
        CircleImageView premal=(CircleImageView) findViewById(R.id.notif);
        CircleImageView shubham=(CircleImageView) findViewById(R.id.shubham);
        Glide.with(getApplicationContext())
                .load("https://firebasestorage.googleapis.com/v0/b/rvceconnect.appspot.com/o/premal.jpg?alt=media&token=77236419-4d5d-4dd1-b2e7-52f9b5dabf65")
                .into(premal);
        Glide.with(getApplicationContext())
                .load("https://firebasestorage.googleapis.com/v0/b/rvceconnect.appspot.com/o/shubham.jpg?alt=media&token=3fc44251-92ca-4e83-8db3-2181700e44c0")
                .into(shubham);
    }
}
