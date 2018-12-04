package com.example.premal2.rvceconnect;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.vansuita.materialabout.builder.AboutBuilder;
import com.vansuita.materialabout.views.AboutView;

import org.jsoup.Jsoup;

import java.net.URL;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

//import org.jsoup.Jsoup;

//import org.jsoup.Jsoup;

public class StudentFrontEnd extends AppCompatActivity
{
    FirebaseAuth mAuth; //For creating logged in user instance
    int h=0; //Will be assigned as 1 when data is fetched
    boolean attnShowingBack; //Flag for indicating card is flipped
    boolean scoreShowingBack; //Flag for indicating card is flipped
    boolean timetableShowingBack; //Flag for indicating card is flipped
    boolean transcriptsShowingBack; //Flag for indicating card is flipped
    boolean calenderannouncementsShowingBack; //Flag for indicating card is flipped
    int attncount=0;
    int scorecount=0;
    int timetablecount=0;
    int REQUEST_CODE=4;
    ProgressBar x;
    TextView t;
    Thread t3;
    fetchingtext s;
    public static ImageView profilepic;
    TextToSpeech ttobj;
    FirebaseFirestore db;

    public static String imageurl;

     /*An array that contains the list of permissions required by the app*/
    private String [] permissions = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.SEND_SMS};



    /*Code snippet to check request permissions result */
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED)
        {
        Log.i("Permission status","permission successful");

        }
    }

    private class fetchingtext implements Runnable //Thread class running in background while fetching thread is happening
    {
        private volatile boolean exit=false;
        private volatile boolean fail=false;
        public void run()
        {
            while(exit==false)
            {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                t.setText("FETCHING.");

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                t.setText("FETCHING..");

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                t.setText("FETCHING...");
            }
            if(fail==true)
            t.setText("FAILED!");
            else
            {

                    synchronized (this)
                    {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                t.setTextSize(20);
                                t.setText("Welcome "+mAuth.getCurrentUser().getEmail()+"!\nTap here to see personal information!");
                                t.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        startActivity(new Intent(StudentFrontEnd.this,StudentInfo.class));
                                    }
                                });

                            }
                        });
                    }



            }
        }
        public void stopfail()
        {
            fail=true;
            exit=true;
        }
        public void stop()
        {
            exit=true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) //Student activity starts here
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_front_end);
        mAuth=FirebaseAuth.getInstance(); //mAuth assigned current user
        Log.d("e",mAuth.getCurrentUser().getEmail());
        if(mAuth.getCurrentUser().getEmail().equals("sowmyaranicn@rvce.edu.in"))
        {
            mAuth.signOut();

            startActivity(new Intent(StudentFrontEnd.this,MainActivity.class));
        }
        Log.d("e","doesnt");
         db=FirebaseFirestore.getInstance();
        profilepic=(ImageView) findViewById(R.id.profilepic); //Profile pic image loaded
        profilepic.setVisibility(View.INVISIBLE); //Set invisible
        t=(TextView) findViewById(R.id.details);
        t.setTextSize(50);
         s=new fetchingtext();
        t3=new Thread(s);
        t3.start();
        x=(ProgressBar) findViewById(R.id.fetching);
        new tempval().execute(); //Fetching data from database
        new tempval2().execute(); //Fetching data from database
        ActivityCompat.requestPermissions(this,permissions, REQUEST_CODE); //request permissions to write to external storage
        if (savedInstanceState == null)
        {


            /*fill the attendance card view with the attendance front end*/
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.attendancecontainer, new AttendanceFront())
                    .commit();

            /*fill the test score card view with test score front end */
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.testscorecontainer, new TestScoreFront())
                    .commit();


          /*fill the time table card view with time table front end */
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.timetablecontainer, new TimetableFront())
                    .commit();

            /*fill the transcripts card view with front end */


        }




        /*find the id of the card-view*/
        View attnbtnFlip = findViewById(R.id.attendancecontainer);
        View scorebtnFlip = findViewById(R.id.testscorecontainer);
        View timetablebtnFlip = findViewById(R.id.timetablecontainer);

        /*set an on click listener to lister to any clicks on the card*/
        attnbtnFlip.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {


                attnflipCard();


            }
        });



        /*set an on click listener to lister to any clicks on the card*/
        scorebtnFlip.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {


                scoreflipCard();


            }
        });



        /*set an on click listener to lister to any clicks on the card*/
        timetablebtnFlip.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {


                timetableflipCard();

            }
        });

        /*set an on click listener to listen to any clicks on the card*/





       }





    private void attnflipCard()
    {
        if (!attnShowingBack && h==1)
        {


            attnShowingBack=true;



            getFragmentManager()
                    .beginTransaction()

                    /* Replace the default fragment animations with animator resources
                    // representing rotations when switching to the back of the card, as
                    // well as animator resources representing rotations when flipping
                    // back to the front (e.g. when the system Back button is pressed). */
                    .setCustomAnimations(
                            R.animator.card_flip_right_in,
                            R.animator.card_flip_right_out,
                            R.animator.card_flip_left_in,
                            R.animator.card_flip_left_out)

                    /* Replace any fragments currently in the container view with a
                     fragment representing the next page (indicated by the
                     just-incremented currentPage variable).*/
                    .replace(R.id.attendancecontainer, new AttendanceBack())


                    /* Add this transaction to the back stack, allowing users to press */
                    /* Back to get to the front of the card.*/
                    .addToBackStack(null)

                    /* Commit the transaction. */
                    .commit();


        }
    }




    private void scoreflipCard()
    {
        if (!scoreShowingBack && h==1)
        {

            scoreShowingBack=true;

            getFragmentManager()
                    .beginTransaction()

                    /* Replace the default fragment animations with animator resources
                    // representing rotations when switching to the back of the card, as
                    // well as animator resources representing rotations when flipping
                    // back to the front (e.g. when the system Back button is pressed). */
                    .setCustomAnimations(
                            R.animator.card_flip_right_in,
                            R.animator.card_flip_right_out,
                            R.animator.card_flip_left_in,
                            R.animator.card_flip_left_out)

                    /* Replace any fragments currently in the container view with a
                     fragment representing the next page (indicated by the
                     just-incremented currentPage variable).*/
                    .replace(R.id.testscorecontainer, new TestScoreBack())

                    /* Add this transaction to the back stack, allowing users to press */
                    /* Back to get to the front of the card.*/
                    .addToBackStack(null)

                    /* Commit the transaction. */
                    .commit();


        }


    }


    private void timetableflipCard()
    {
        if (!timetableShowingBack && h==1) {

        timetableShowingBack=true;

            getFragmentManager()
                    .beginTransaction()

                    /* Replace the default fragment animations with animator resources
                    // representing rotations when switching to the back of the card, as
                    // well as animator resources representing rotations when flipping
                    // back to the front (e.g. when the system Back button is pressed). */
                    .setCustomAnimations(
                            R.animator.card_flip_right_in,
                            R.animator.card_flip_right_out,
                            R.animator.card_flip_left_in,
                            R.animator.card_flip_left_out)

                    /* Replace any fragments currently in the container view with a
                     fragment representing the next page (indicated by the
                     just-incremented currentPage variable).*/
                    .replace(R.id.timetablecontainer, new TimetableBack())

                    /* Add this transaction to the back stack, allowing users to press */
                    /* Back to get to the front of the card.*/
                    .addToBackStack(null)

                    /* Commit the transaction. */
                    .commit();

             }
        }

/*when the back key is pressed the card flip booleans are set to false so as to re-enable the flip */
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if((keyCode==KeyEvent.KEYCODE_BACK))
        {
            attnShowingBack=false;
            scoreShowingBack=false;
            timetableShowingBack=false;
            transcriptsShowingBack=false;
            calenderannouncementsShowingBack=false;

        }

        return super.onKeyDown(keyCode,event);
    }



    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        switch (id){
            case R.id.item1:

          Intent i=new Intent(StudentFrontEnd.this,AboutActivity.class);
          startActivity(i);


                return true;


            case R.id.item2:
                  mAuth.signOut();

                  startActivity(new Intent(StudentFrontEnd.this,MainActivity.class));

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public class tempval2 extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }
        @Override
        protected Void doInBackground(Void...voids)
        {
            String url="http://192.168.43.110/connect6.php";
            Log.d("e","workks");
            try {
                org.jsoup.nodes.Document document = Jsoup.connect(url).get();
                Log.d("e", "workkks finne");
                Log.d("e", document.getElementById("sgpa1").text());
                TestScoreBack.studentsgpa[0]=Double.valueOf(document.getElementById("sgpa1").text());
                TestScoreBack.studentsgpa[1]=Double.valueOf(document.getElementById("sgpa2").text());
                TestScoreBack.studentsgpa[2]=Double.valueOf(document.getElementById("sgpa3").text());
                TestScoreBack.studentsgpa[3]=Double.valueOf(document.getElementById("sgpa4").text());
                TestScoreBack.toppersgpa[0]=Double.valueOf(document.getElementById("tgpa1").text());
                TestScoreBack.toppersgpa[1]=Double.valueOf(document.getElementById("tgpa2").text());
                TestScoreBack.toppersgpa[2]=Double.valueOf(document.getElementById("tgpa3").text());
                TestScoreBack.toppersgpa[3]=Double.valueOf(document.getElementById("tgpa4").text());
                TestScoreBack.averagesgpa[0]=Double.valueOf(document.getElementById("agpa1").text());
                TestScoreBack.averagesgpa[1]=Double.valueOf(document.getElementById("agpa2").text());
                TestScoreBack.averagesgpa[2]=Double.valueOf(document.getElementById("agpa3").text());
                TestScoreBack.averagesgpa[3]=Double.valueOf(document.getElementById("agpa4").text());
                Log.d("e",TestScoreBack.averagesgpa[0]+"");
            }
            catch (Exception e)
            {
                e.printStackTrace();

            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid)
        {
            db.collection("pics").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {

                        x.setVisibility(View.INVISIBLE);
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
                                    imageurl=me.getValue()+"";
                                    profilepic.setVisibility(View.VISIBLE);
                                    Glide.with(getApplicationContext())
                                            .load(me.getValue())
                                            .into(profilepic);
                                }
                            }
                        }
                    } else {
                        Log.w("e", "Error getting documents.", task.getException());
                    }
                }
            });


            //  x.setVisibility(View.INVISIBLE);
            super.onPostExecute(aVoid);
        }
    }
    public class tempval extends AsyncTask<Void,Void,Void>
    {
        public int z;
        @Override
        protected void onPreExecute()
        {
        //    x.setVisibility(View.VISIBLE);
            z=1;
            super.onPreExecute();
        }
        @Override
        protected Void doInBackground(Void... voids)
        {
            //            String url="https://rvconnect.000webhostapp.com/connect.php?query=SELECT coursewise.course,coursewise.classatd,coursewise.totalclass,coursewise.quiz1,coursewise.test1,coursewise.quiz2,coursewise.test2,coursewise.quiz3,coursewise.test3 FROM student_pro INNER JOIN coursewise ON student_pro.p_usn=coursewise.usn WHERE student_pro.rvcemailid='premalsingh.cs16@rvce.edu.in'";
            String url="http://192.168.43.110/connect.php?query=SELECT%20course_cgpa.courseid,course_cgpa.classatd,course_cgpa.totalclass,course_cgpa.quiz1,course_cgpa.test1,course_cgpa.quiz2,course_cgpa.test2,course_cgpa.quiz3,course_cgpa.test3%20FROM%20student_pro%20INNER%20JOIN%20course_cgpa%20ON%20student_pro.usn=course_cgpa.usn%20WHERE%20student_pro.rvcemailid=%27"+mAuth.getCurrentUser().getEmail()+"%27";

            Log.d("e","works fine");
            try {
                org.jsoup.nodes.Document document = Jsoup.connect(url).get();
                int i;
                for(i=0;i<7;i++)
                    AttendanceBack.classes_attended[i]=Integer.parseInt(document.getElementById("s"+String.valueOf(i+1)+"a").text());
                for(i=0;i<7;i++)
                    AttendanceBack.classes_held[i]=Integer.parseInt(document.getElementById("s"+String.valueOf(i+1)+"t").text());
                for(i=0;i<7;i++)
                    TestScoreBack.quiz1_score[i]=Integer.parseInt(document.getElementById("s"+String.valueOf(i+1)+"q1").text());
                for(i=0;i<7;i++)
                    TestScoreBack.test1_score[i]=Integer.parseInt(document.getElementById("s"+String.valueOf(i+1)+"t1").text());
                for(i=0;i<7;i++)
                    TestScoreBack.quiz2_score[i]=Integer.parseInt(document.getElementById("s"+String.valueOf(i+1)+"q2").text());
                for(i=0;i<7;i++)
                    TestScoreBack.test2_score[i]=Integer.parseInt(document.getElementById("s"+String.valueOf(i+1)+"t2").text());
                for(i=0;i<7;i++)
                    TestScoreBack.quiz3_score[i]=Integer.parseInt(document.getElementById("s"+String.valueOf(i+1)+"q3").text());
                for(i=0;i<7;i++)
                    TestScoreBack.test3_score[i]=Integer.parseInt(document.getElementById("s"+String.valueOf(i+1)+"t3").text());
            }
            catch (Exception e)
            {
                z=2;
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid)
        {
            if(z==2) {
                s.stopfail();
                x.setVisibility(View.INVISIBLE);
                profilepic.setVisibility(View.VISIBLE);
                AlertDialog.Builder builder = new AlertDialog.Builder(StudentFrontEnd.this);
                builder.setTitle("Connection Failed");
                builder.setMessage("Connection to 192.168.43.110 (Application Datacenter) failed! Please make sure you are connected to STUDENT WiFi.");
                builder.setCancelable(false);
                builder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
                AlertDialog alert1 = builder.create();
                alert1.show();
            }
            else {
                s.stop();
                h=1;
            }
          //  x.setVisibility(View.INVISIBLE);
            super.onPostExecute(aVoid);
        }
    }








}




