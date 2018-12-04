package com.example.premal2.rvceconnect;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;
import java.util.Set;

public class AdminMessages extends AppCompatActivity {

    FirebaseFirestore db;
    Button proceed;
    TextView notifnum;
    TextView emailid;
    TextView issue;
    ProgressBar loading;
    FirebaseAuth mAuth;
    @Override
    public void onBackPressed()
    {
        startActivity(new Intent(AdminMessages.this,AdminFrontEnd.class));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_messages);
        mAuth=FirebaseAuth.getInstance();
        proceed=(Button) findViewById(R.id.proceed);
        db=FirebaseFirestore.getInstance();
        notifnum=(TextView) findViewById(R.id.notifnum);
        emailid=(TextView) findViewById(R.id.emailid);
        issue=(TextView) findViewById(R.id.issue);
        loading=(ProgressBar) findViewById(R.id.loading);
        Button logout=(Button) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(AdminMessages.this,MainActivity.class));
            }
        });
        //Counting number of documents
        db.collection("admin_messages").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                int count=0;
                if (task.isSuccessful()) {

                    for (DocumentSnapshot document : task.getResult()) {
                        count++;

                        //Log.d(TAG, document.getId() + " => " + document.getData());
                    }
                    if(count==0)
                    {
                        Toast.makeText(AdminMessages.this,"No Messages to enquire!",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AdminMessages.this,AdminFrontEnd.class));
                    }
                    notifnum.setText(count+"");
                } else {
                    Log.w("e", "Error getting documents.", task.getException());
                }
            }
        });
        db.collection("admin_messages")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            final DocumentSnapshot t;

                            for (final DocumentSnapshot document : task.getResult()) {
                                Map<String,Object> x=document.getData();
                                Set< Map.Entry< String,Object> > st =x.entrySet();

                                for (Map.Entry< String,Object> me:st)
                                {
                                    Log.d("e",me.getKey()+":");
                                    if(me.getKey().equals("issue"))
                                    {
                                        issue.setText("Issue: "+me.getValue());
                                    }
                                    if(me.getKey().equals("emailid"))
                                    {
                                        emailid.setText("RVCE Mail ID: "+me.getValue());
                                    }
                                    Log.d("e",me.getValue()+"");
                                }

                                loading.setVisibility(View.INVISIBLE);
                                Log.d("e","next");
                             //   t=document;
                                proceed.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        db.collection("admin_messages").document(document.getId()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                  Log.d("e", document.getId() + " => " + document.getData());
                                                Log.d("e","Deleted");
                                                startActivity(new Intent(AdminMessages.this,AdminMessages.class));

                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.d("e","Not Deleted");
                                            }
                                        });
                                    }
                                });

                            }
                          //  Log.d("e", t.getId() + " => " + t.getData());

                        } else {
                            Log.w("e", "Error getting documents.", task.getException());
                        }
                    }
                });
    }
}
