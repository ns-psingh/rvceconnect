package com.example.premal2.rvceconnect;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;

public class StudentFrontEnd extends AppCompatActivity
{

    private FirebaseAuth mAuth;
    int h;
    boolean attnShowingBack;
    boolean scoreShowingBack;
    boolean timetableShowingBack;
    int attncount=0;
    int scorecount=0;
    int timetablecount=0;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_front_end);
        mAuth=FirebaseAuth.getInstance();




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

        }




        /*find the id of the attendance card-view*/
        View attnbtnFlip = findViewById(R.id.attendancecontainer);

        /*set an on click listener to lister to any clicks on the card*/
        attnbtnFlip.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                attnflipCard();


            }
        });

        View scorebtnFlip = findViewById(R.id.testscorecontainer);

        /*set an on click listener to lister to any clicks on the card*/
        scorebtnFlip.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {


                scoreflipCard();


            }
        });

        View timetablebtnFlip = findViewById(R.id.timetablecontainer);

        /*set an on click listener to lister to any clicks on the card*/
        timetablebtnFlip.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {


                timetableflipCard();

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


    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if((keyCode==KeyEvent.KEYCODE_BACK))
        {
            attnShowingBack=false;
            scoreShowingBack=false;
            timetableShowingBack=false;
        }

        return super.onKeyDown(keyCode,event);
    }




}




