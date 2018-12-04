package com.example.premal2.rvceconnect;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity
{
    public static String cred1; //Username String
    public static String cred2; //Password String
    private ImageView offline; //View for red dot
    private ImageView online; //View for green dot
    private TextView status; //Text representing offline/online
    private FirebaseAuth mAuth; //FirebaseAuth object for user instance
    private ProgressBar signin_progress;
    private boolean isOnline() //Function to check whether Internet is enabled
    {
        ConnectivityManager cm=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo=cm.getActiveNetworkInfo();
        if(netInfo!=null && netInfo.isConnectedOrConnecting())
            return true;
        else
            return false;
    }
    private void seton() //Function to show on UI that device is connected
    {
           online.setVisibility(View.VISIBLE);
           offline.setVisibility(View.INVISIBLE);
           status.setText("Connected");
    }
    private void setoff()  //Function to show on UI that device is not connected
    {
           offline.setVisibility(View.VISIBLE);
           online.setVisibility(View.INVISIBLE);
           status.setText("Offline");
    }
    private class onlinecheck implements Runnable //Thread class running in background to check online/offline every 0.5s
    {
        public void run()
        {
            while(true)
            {
            try
            {
                synchronized (this)
                {
                    wait(500);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(isOnline())
                                seton();
                            else
                                setoff();
                        }
                    });
                }
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            }
        }
    }
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) //Called when activity starts
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        offline=(ImageView) findViewById(R.id.offline); //Instantiated with XML equivalent
        online=(ImageView) findViewById(R.id.online); //Instantiated with XML equivalent
        offline.setVisibility(View.INVISIBLE); //Instantiated with XML equivalent
        online.setVisibility(View.INVISIBLE); //Instantiated with XML equivalent
        status=(TextView) findViewById(R.id.status); //Instantiated with XML equivalent
        onlinecheck s=new onlinecheck();
        Thread t1=new Thread(s);
        t1.start(); //Executed in Background
        signin_progress=(ProgressBar) findViewById(R.id.signin_progress); //Instantiated with XML equivalent
        signin_progress.setVisibility(View.INVISIBLE); //Set as hidden
        mAuth=FirebaseAuth.getInstance(); //Assigned user
        final Vibrator vibe=(Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE); //Used for vibrating device
        db=FirebaseFirestore.getInstance();
        if(mAuth.getCurrentUser()!=null) //if user is logged in
        {

            db.collection("user_category").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {

                        for (DocumentSnapshot document : task.getResult()) {
                           // Log.d("e", document.getId() + " => " + document.getData());

                          //  Log.d("e",document.getId());
                        //    Log.d("e",mAuth.getCurrentUser().getEmail());
                            if(document.getId().equals(mAuth.getCurrentUser().getEmail()))
                            {
                                Log.d("e","here come");
                                Map<String,Object> x=document.getData();
                                Set< Map.Entry< String,Object> > st =x.entrySet();

                                for (Map.Entry< String,Object> me:st)
                                {
                                    Log.d("e",me.getValue()+"fine");
                                    if((me.getValue()+"").equals("student"))
                                    {
                                        Log.d("e","all is well");
                                        signin_progress.setVisibility(View.INVISIBLE);
                                        finish();
                                        startActivity(new Intent(getApplicationContext(),StudentFrontEnd.class));
                                    }
                                    if((me.getValue()+"").equals("admin"))
                                    {
                                        Log.d("e","admin");
                                        signin_progress.setVisibility(View.INVISIBLE);
                                        finish();
                                        startActivity(new Intent(getApplicationContext(),AdminFrontEnd.class));
                                    }
                                    if((me.getValue()+"").equals("teacher"))
                                    {
                                        Log.d("e","teacher");
                                        signin_progress.setVisibility(View.INVISIBLE);
                                        finish();
                                        startActivity(new Intent(getApplicationContext(),TeacherFrontEnd.class));
                                    }

                                }
                            }
                        }
                    } else {
                        Log.w("e", "Error getting documents.", task.getException());
                    }
                }
            });



          //  finish();
         //   startActivity(new Intent(getApplicationContext(),StudentFrontEnd.class)); //Go to Student Front End Page
        }

        final EditText emailid = (EditText) findViewById(R.id.emailid); //EditText for Email-id
        final EditText password = (EditText) findViewById(R.id.password); //EditText for Password
        Button signin = (Button) findViewById(R.id.signin); //Button for signing in
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isOnline()) //If device isnt connected to the Internet
                {
                    vibe.vibrate(80);
                    Toast.makeText(MainActivity.this, "Please check your Internet Connection.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    signin_progress.setVisibility(View.VISIBLE); //Progress Bar set visible, indicating connection with database
                    cred1=emailid.getText().toString(); //String assigned edittext values
                    cred2=password.getText().toString(); //String assigned edittext values
                    if(!(cred1.equals("")||cred2.equals("")))
                    {mAuth.signInWithEmailAndPassword(cred1,cred2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) //If Sign-in Successfull
                            {
                                db.collection("user_category").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        if (task.isSuccessful()) {

                                            for (DocumentSnapshot document : task.getResult()) {
                                                Log.d("e", document.getId() + " => " + document.getData());

                                                Log.d("e",document.getId());
                                                Log.d("e",mAuth.getCurrentUser().getEmail());
                                                if(document.getId().equals(mAuth.getCurrentUser().getEmail()))
                                                {
                                                    Log.d("e","here come");
                                                    Map<String,Object> x=document.getData();
                                                    Set< Map.Entry< String,Object> > st =x.entrySet();

                                                    for (Map.Entry< String,Object> me:st)
                                                    {
                                                        Log.d("e",me.getKey()+":");
                                                        Log.d("e",me.getValue()+"");
                                                        if((me.getValue()+"").equals("student"))
                                                        {
                                                            Log.d("e","all is well");
                                                            signin_progress.setVisibility(View.INVISIBLE);
                                                            finish();
                                                            startActivity(new Intent(getApplicationContext(),StudentFrontEnd.class));
                                                        }
                                                        if((me.getValue()+"").equals("admin"))
                                                        {
                                                            signin_progress.setVisibility(View.INVISIBLE);
                                                            finish();
                                                            startActivity(new Intent(getApplicationContext(),AdminFrontEnd.class));
                                                        }
                                                        if((me.getValue()+"").equals("teacher"))
                                                        {
                                                            signin_progress.setVisibility(View.INVISIBLE);
                                                            finish();
                                                            startActivity(new Intent(getApplicationContext(),TeacherFrontEnd.class));
                                                        }

                                                    }
                                                }
                                            }
                                        } else {
                                            Log.w("e", "Error getting documents.", task.getException());
                                        }
                                    }
                                });

                            }
                            else //If Wrong credentials added
                            {
                                signin_progress.setVisibility(View.INVISIBLE);
                                Toast.makeText(MainActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                                vibe.vibrate(80);
                            }
                        }
                    });
                    }
                    else { //If either or both credentials not filled
                        signin_progress.setVisibility(View.INVISIBLE);
                        vibe.vibrate(80);
                        Toast.makeText(MainActivity.this, "Please fill Credentials to log in", Toast.LENGTH_SHORT).show();
                        if(cred1.equals(""))
                            emailid.setError("Please Enter Username");
                        if(cred2.equals(""))
                            password.setError("Please Enter Password");
                    }
                }
            }
        });
        TextView forgot=(TextView)findViewById(R.id.forgot);
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent i=new Intent(MainActivity.this,FacingIssues.class);
                startActivity(i);
            }
        });
    }
}
