package com.example.premal2.rvceconnect;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FacingIssues extends AppCompatActivity {

    EditText emailid;
    EditText issue;
    CheckBox checkBox;
    Button submit;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facing_issues);
        db=FirebaseFirestore.getInstance();
        emailid=(EditText) findViewById(R.id.emailid);
        issue=(EditText) findViewById(R.id.issue);
        checkBox=(CheckBox) findViewById(R.id.checkBox);
        final Map<String,Object> message=new HashMap<>();
        submit=(Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkBox.isChecked())
                {
                    if(emailid.getText().toString().equals("")||issue.getText().toString().equals(""))
                        Toast.makeText(FacingIssues.this,"Please Enter All Fields!",Toast.LENGTH_SHORT).show();
                    else
                    {
                        message.put("emailid",emailid.getText().toString());
                        message.put("issue",issue.getText().toString());
                        db.collection("admin_messages").add(message).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {

                                Toast.makeText(FacingIssues.this,"Your issue was delivered to app admin. Admin will get in touch with you!",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(FacingIssues.this,MainActivity.class));

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(FacingIssues.this,"Unable to submit! Please Re-try!",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }
        });
    }
}
