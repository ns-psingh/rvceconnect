package com.example.premal2.rvceconnect;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
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
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.vansuita.materialabout.builder.AboutBuilder;
import com.vansuita.materialabout.views.AboutView;

//import org.jsoup.Jsoup;

public class StudentFrontEnd extends AppCompatActivity
{

    private FirebaseAuth mAuth;
    int h;
    boolean attnShowingBack;
    boolean scoreShowingBack;
    boolean timetableShowingBack;
    boolean transcriptsShowingBack;
    boolean calenderannouncementsShowingBack;
    int attncount=0;
    int scorecount=0;
    int timetablecount=0;
    int REQUEST_CODE=4;


    /*An array that contains the list of permissions required by the app*/
    private String [] permissions = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE};



    /*Code snippet to check request permissions result */
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED)
        {
        Log.i("Permission status","permission successful");

        }
    }




    ProgressBar x;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_front_end);
//        x=(ProgressBar) findViewById(R.id.fetching);
        new tempval().execute();
     //   AboutView view =new AboutBuilder(this);
        mAuth=FirebaseAuth.getInstance();

        /*request permissions to write to external storage*/
        ActivityCompat.requestPermissions(this,permissions, REQUEST_CODE);




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
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.transcriptscontainer, new TranscriptsFrontEnd())
                    .commit();

            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.announcements, new CalenderAnnouncementsFront())
                    .commit();



        }




        /*find the id of the card-view*/
        View attnbtnFlip = findViewById(R.id.attendancecontainer);
        View scorebtnFlip = findViewById(R.id.testscorecontainer);
        View timetablebtnFlip = findViewById(R.id.timetablecontainer);
        final View transcriptsFlip =findViewById(R.id.transcriptscontainer);
        View announcementsFlip =findViewById(R.id.announcements);


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
        transcriptsFlip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                transflipCard();
            }
        });

        announcementsFlip.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                CalAnnouncementsflipCard();
            }
        });








       }





    private void attnflipCard()
    {
        if (!attnShowingBack)
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
        if (!scoreShowingBack)
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
        if (!timetableShowingBack) {

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


    private void transflipCard()
    {
        if (!transcriptsShowingBack)
        {

            transcriptsShowingBack=true;



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
                    .replace(R.id.transcriptscontainer, new TranscriptsBackEnd())


                    /* Add this transaction to the back stack, allowing users to press */
                    /* Back to get to the front of the card.*/
                    .addToBackStack(null)

                    /* Commit the transaction. */
                    .commit();


        }
    }

    private void CalAnnouncementsflipCard()
    {
        if (!calenderannouncementsShowingBack)
        {

            calenderannouncementsShowingBack=true;



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
                    .replace(R.id.announcements, new CalenderAnnouncementsBack())


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
    public class tempval extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected void onPreExecute()
        {
            x.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }
        @Override
        protected Void doInBackground(Void... voids)
        {
    /*            String url="https://rvconnect.000webhostapp.com/connect.php?query=SELECT coursewise.course,coursewise.classatd,coursewise.totalclass,coursewise.quiz1,coursewise.test1,coursewise.quiz2,coursewise.test2,coursewise.quiz3,coursewise.test3 FROM student_pro INNER JOIN coursewise ON student_pro.p_usn=coursewise.usn WHERE student_pro.rvcemailid='premalsingh.cs16@rvce.edu.in'";
            Log.d("e","works fine");
            try {
                org.jsoup.nodes.Document document = Jsoup.connect(url).get();
                Log.d("e","works fine");
                Log.d("r",document.getElementById("s1a").text());
                Log.d("e","works fine");
                AttendanceBack.classes_attended[0]=Integer.parseInt(document.getElementById("s1a").text());
                Log.d("r",AttendanceBack.classes_attended[0]+" ");
                AttendanceBack.classes_attended[1]=Integer.parseInt(document.getElementById("s2a").text());
                AttendanceBack.classes_attended[2]=Integer.parseInt(document.getElementById("s3a").text());
                AttendanceBack.classes_attended[3]=Integer.parseInt(document.getElementById("s4a").text());
                AttendanceBack.classes_attended[4]=Integer.parseInt(document.getElementById("s5a").text());
                AttendanceBack.classes_attended[5]=Integer.parseInt(document.getElementById("s6a").text());
                AttendanceBack.classes_attended[6]=Integer.parseInt(document.getElementById("s7a").text());
                AttendanceBack.classes_held[0]=Integer.parseInt(document.getElementById("s1t").text());
                AttendanceBack.classes_held[1]=Integer.parseInt(document.getElementById("s2t").text());
                AttendanceBack.classes_held[2]=Integer.parseInt(document.getElementById("s3t").text());
                AttendanceBack.classes_held[3]=Integer.parseInt(document.getElementById("s4t").text());
                AttendanceBack.classes_held[4]=Integer.parseInt(document.getElementById("s5t").text());
                AttendanceBack.classes_held[5]=Integer.parseInt(document.getElementById("s6t").text());
                AttendanceBack.classes_held[6]=Integer.parseInt(document.getElementById("s7t").text());
                TestScoreBack.quiz1_score[0]=Integer.parseInt(document.getElementById("s1q1").text());
                TestScoreBack.quiz1_score[1]=Integer.parseInt(document.getElementById("s2q1").text());
                TestScoreBack.quiz1_score[2]=Integer.parseInt(document.getElementById("s3q1").text());
                TestScoreBack.quiz1_score[3]=Integer.parseInt(document.getElementById("s4q1").text());
                TestScoreBack.quiz1_score[4]=Integer.parseInt(document.getElementById("s5q1").text());
                TestScoreBack.quiz1_score[5]=Integer.parseInt(document.getElementById("s6q1").text());
                TestScoreBack.quiz1_score[6]=Integer.parseInt(document.getElementById("s7q1").text());

                TestScoreBack.test1_score[0]=Integer.parseInt(document.getElementById("s1t1").text());
                TestScoreBack.test1_score[1]=Integer.parseInt(document.getElementById("s2t1").text());
                TestScoreBack.test1_score[2]=Integer.parseInt(document.getElementById("s3t1").text());
                TestScoreBack.test1_score[3]=Integer.parseInt(document.getElementById("s4t1").text());
                TestScoreBack.test1_score[4]=Integer.parseInt(document.getElementById("s5t1").text());
                TestScoreBack.test1_score[5]=Integer.parseInt(document.getElementById("s6t1").text());
                TestScoreBack.test1_score[6]=Integer.parseInt(document.getElementById("s7t1").text());
                TestScoreBack.quiz2_score[0]=Integer.parseInt(document.getElementById("s1q2").text());
                TestScoreBack.quiz2_score[1]=Integer.parseInt(document.getElementById("s2q2").text());
                TestScoreBack.quiz2_score[2]=Integer.parseInt(document.getElementById("s3q2").text());
                TestScoreBack.quiz2_score[3]=Integer.parseInt(document.getElementById("s4q2").text());
                TestScoreBack.quiz2_score[4]=Integer.parseInt(document.getElementById("s5q2").text());
                TestScoreBack.quiz2_score[5]=Integer.parseInt(document.getElementById("s6q2").text());
                TestScoreBack.quiz2_score[6]=Integer.parseInt(document.getElementById("s7q2").text());

                TestScoreBack.test2_score[0]=Integer.parseInt(document.getElementById("s1t2").text());
                TestScoreBack.test2_score[1]=Integer.parseInt(document.getElementById("s2t2").text());
                TestScoreBack.test2_score[2]=Integer.parseInt(document.getElementById("s3t2").text());
                TestScoreBack.test2_score[3]=Integer.parseInt(document.getElementById("s4t2").text());
                TestScoreBack.test2_score[4]=Integer.parseInt(document.getElementById("s5t2").text());
                TestScoreBack.test2_score[5]=Integer.parseInt(document.getElementById("s6t2").text());
                TestScoreBack.test2_score[6]=Integer.parseInt(document.getElementById("s7t2").text());
                TestScoreBack.quiz3_score[0]=Integer.parseInt(document.getElementById("s1q3").text());
                TestScoreBack.quiz3_score[1]=Integer.parseInt(document.getElementById("s2q3").text());
                TestScoreBack.quiz3_score[2]=Integer.parseInt(document.getElementById("s3q3").text());
                TestScoreBack.quiz3_score[3]=Integer.parseInt(document.getElementById("s4q3").text());
                TestScoreBack.quiz3_score[4]=Integer.parseInt(document.getElementById("s5q3").text());
                TestScoreBack.quiz3_score[5]=Integer.parseInt(document.getElementById("s6q3").text());
                TestScoreBack.quiz3_score[6]=Integer.parseInt(document.getElementById("s7q3").text());

                TestScoreBack.test3_score[0]=Integer.parseInt(document.getElementById("s1t3").text());
                TestScoreBack.test3_score[1]=Integer.parseInt(document.getElementById("s2t3").text());
                TestScoreBack.test3_score[2]=Integer.parseInt(document.getElementById("s3t3").text());
                TestScoreBack.test3_score[3]=Integer.parseInt(document.getElementById("s4t3").text());
                TestScoreBack.test3_score[4]=Integer.parseInt(document.getElementById("s5t3").text());
                TestScoreBack.test3_score[5]=Integer.parseInt(document.getElementById("s6t3").text());
                TestScoreBack.test3_score[6]=Integer.parseInt(document.getElementById("s7t3").text());

            }
            catch (Exception e)
            {
                e.printStackTrace();
                Log.d("e","problem");
            }*/
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid)
        {
            x.setVisibility(View.INVISIBLE);
            super.onPostExecute(aVoid);
        }
    }








}




