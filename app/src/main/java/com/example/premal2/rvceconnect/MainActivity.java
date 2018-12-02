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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity
{

    String cred1; //Username String
    String cred2; //Password String
    private FirebaseAuth mAuth; //FirebaseAuth object for user instance
    private ProgressBar signin_progress;
    protected boolean isOnline()
    {
        ConnectivityManager cm=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo=cm.getActiveNetworkInfo();
        if(netInfo!=null && netInfo.isConnectedOrConnecting())
            return true;
        else
            return false;
    }
    ImageView offline;
    ImageView online;
    TextView status;
    public void seton()
    {

           online.setVisibility(View.VISIBLE);
           offline.setVisibility(View.INVISIBLE);
           status.setText("Connected");
    }

    public void setoff()
    {

           offline.setVisibility(View.VISIBLE);
           online.setVisibility(View.INVISIBLE);
           status.setText("Offline");
    }
    class onlinecheck implements Runnable
    {
        public void run()
        {
            while(true)
            {Log.d("e","entered inside");
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
            }}
        //    Intent mainActivity=new Intent(getApplicationContext(),MainActivity.class);
        //    startActivity(mainActivity);
/*
            while(true)
            {Log.d("e","entered thread");

            if(isOnline())
            {
             seton();
             //   online.setVisibility(View.VISIBLE);
             //   offline.setVisibility(View.INVISIBLE);
             //   status.setText("Connected");
                try {
                    Thread.sleep(3000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
            else {
                setoff();
                try {
                    Thread.sleep(3000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }}*/
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        offline=(ImageView) findViewById(R.id.offline);
        online=(ImageView) findViewById(R.id.online);
        offline.setVisibility(View.INVISIBLE);
        online.setVisibility(View.INVISIBLE);
        status=(TextView) findViewById(R.id.status);
        onlinecheck s=new onlinecheck();
        Thread t1=new Thread(s);
        t1.start();
        signin_progress=(ProgressBar) findViewById(R.id.signin_progress);
        signin_progress.setVisibility(View.INVISIBLE);
        mAuth=FirebaseAuth.getInstance(); //Assigned user
        final Vibrator vibe=(Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
        if(mAuth.getCurrentUser()!=null) //if user is logged in
        {

            finish();
            startActivity(new Intent(getApplicationContext(),StudentFrontEnd.class)); //Go to Student Front End Page
        }
        final EditText emailid = (EditText) findViewById(R.id.emailid); //EditText for Email-id
        final EditText password = (EditText) findViewById(R.id.password); //EditText for Password
        Button signin = (Button) findViewById(R.id.signin); //Button for signing in
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isOnline())
                {
                    Log.d("e","not connected");
                    vibe.vibrate(80);
                    Toast.makeText(MainActivity.this, "Please check your Internet Connection.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    signin_progress.setVisibility(View.VISIBLE);
                    cred1=emailid.getText().toString(); //String assigned edittext values
                    cred2=password.getText().toString(); //String assigned edittext values
                    if(!(cred1.equals("")||cred2.equals("")))
                    {mAuth.signInWithEmailAndPassword(cred1,cred2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                signin_progress.setVisibility(View.INVISIBLE);
                                //If login is successfull
                                Log.d("e","success");
                                finish();
                                startActivity(new Intent(getApplicationContext(),StudentFrontEnd.class));
                            }
                            else
                            {
                                signin_progress.setVisibility(View.INVISIBLE);
                                Toast.makeText(MainActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                                vibe.vibrate(80);
                                //If login is unsuccessfull
                                Log.d("e","failed");
                            }
                        }
                    });}
                    else {
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

                Intent i=new Intent(MainActivity.this,TeacherFrontEnd.class);

                startActivity(i);
            }
        });





    }
}
