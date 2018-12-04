package com.example.premal2.rvceconnect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdminFrontEnd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_front_end);
        CircleImageView notif=(CircleImageView) findViewById(R.id.notif);
        CircleImageView create=(CircleImageView) findViewById(R.id.reg_user);
        CircleImageView dev=(CircleImageView) findViewById(R.id.contactdev);
        dev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminFrontEnd.this,DeveloperInfo.class));
            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminFrontEnd.this,Usercreation.class));
            }
        });
        notif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminFrontEnd.this,AdminMessages.class));
            }
        });
        Button logout=(Button) findViewById(R.id.logout);
        final FirebaseAuth mAuth=FirebaseAuth.getInstance();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(AdminFrontEnd.this,MainActivity.class));
            }
        });
    }
}
