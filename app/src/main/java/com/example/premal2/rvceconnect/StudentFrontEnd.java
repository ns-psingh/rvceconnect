package com.example.premal2.rvceconnect;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.pm.PackageManager;
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

import com.google.firebase.auth.FirebaseAuth;
import com.vansuita.materialabout.builder.AboutBuilder;
import com.vansuita.materialabout.views.AboutView;

public class StudentFrontEnd extends AppCompatActivity
{

    private FirebaseAuth mAuth;
    int h;
    boolean attnShowingBack;
    boolean scoreShowingBack;
    boolean timetableShowingBack;
    boolean transcriptsShowingBack;
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






    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_front_end);

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



        }




        /*find the id of the card-view*/
        View attnbtnFlip = findViewById(R.id.attendancecontainer);
        View scorebtnFlip = findViewById(R.id.testscorecontainer);
        View timetablebtnFlip = findViewById(R.id.timetablecontainer);
        final View transcriptsFlip =findViewById(R.id.transcriptscontainer);


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




/*when the back key is pressed the card flip booleans are set to false so as to re-enable the flip */
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if((keyCode==KeyEvent.KEYCODE_BACK))
        {
            attnShowingBack=false;
            scoreShowingBack=false;
            timetableShowingBack=false;
            transcriptsShowingBack=false;

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

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }









}




