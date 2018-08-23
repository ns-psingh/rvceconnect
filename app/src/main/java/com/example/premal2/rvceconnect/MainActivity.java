package com.example.premal2.rvceconnect;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity
{

    String cred1;
    String cred2;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("e","works fine");
        mAuth=FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser()!=null)
        {

            finish();
            startActivity(new Intent(getApplicationContext(),StudentFrontEnd.class));
        }
        final EditText emailid = (EditText) findViewById(R.id.emailid);
        final EditText password = (EditText) findViewById(R.id.password);
        Button signin = (Button) findViewById(R.id.signin);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cred1=emailid.getText().toString();
                cred2=password.getText().toString();
                mAuth.signInWithEmailAndPassword(cred1,cred2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Log.d("e","success");
                            finish();
                            startActivity(new Intent(getApplicationContext(),StudentFrontEnd.class));
                        }
                        else
                        {
                            Log.d("e","failed");
                        }
                    }
                });
            }
        });
    }
}
