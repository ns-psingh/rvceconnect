package com.example.premal2.rvceconnect;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.TypedValue;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AttendanceFrontEnd extends AppCompatActivity
{
    /*this variable will be used to reference the parent relative layout */
    public RelativeLayout mRelativeLayout;

    /*this variable will hold the current application context */
    public  Context mcontext;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_front_end);

        /*get application context*/
        mcontext = getApplicationContext();
        /*get the RelativeLayoutView */
        mRelativeLayout =findViewById(R.id.relativelayoutmain);



    }

}

/*class that will create new progress bars for each subject*/
class subjectattendance
{


    CardView card;


   /*name of the subject */
    String name;

   /*total_no_classes_held_for_the_subject*/
     int total_classes;

   /*total_classes_attended */
     int total_classes_attended;

     /*percentage of classes attended */
     int percentage;

     /*initialise the object parameters namely name,total_clsses,total_classes_attended*/
     public void init(String name,int total_classes,int total_classes_attended)
     {

         this.name =name;
         this.total_classes=total_classes;
         this.total_classes_attended=total_classes_attended;

     }



     public void createCard(Context mContext)
     {

         card =new CardView(mContext);
         RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(

                 RelativeLayout.LayoutParams.MATCH_PARENT,
                 RelativeLayout.LayoutParams.WRAP_CONTENT
         );

         card.setLayoutParams(params);

         /* Set CardView corner radius */
         card.setRadius(9);

         /* Set cardView content padding */
         card.setContentPadding(15, 15, 15, 15);

         /* Set a background color for CardView */
         card.setCardBackgroundColor(Color.parseColor("#FFC6D6C3"));

         /* Set the CardView maximum elevation */
         card.setMaxCardElevation(15);

         /* Set CardView elevation */
         card.setCardElevation(9);

         /* Initialize a new TextView to put in CardView */
         TextView tv = new TextView(mContext);

         /*Set the parameters of the text view */
         tv.setLayoutParams(params);

         /*Adding the name of the subject to the text view */
         tv.setText(name+"\n");

        /*creating a large enough text view */
         tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 30);

         /*setting text color*/
         tv.setTextColor(Color.RED);


         /* Put the TextView in CardView */
         card.addView(tv);




         /*create a progress bar */
         ProgressBar progressBar=new ProgressBar(mContext);

         /*calculate attendance percentage */
         percentage = (total_classes_attended/total_classes)*100;

       /*set the max value of progress bar to 100*/
         progressBar.setMax(100);

        /*set the min value of the progress bar */
       // progressBar.setMin(0);

         /*set the progress of the progress bar*/
         progressBar.setProgress(percentage,true);

        card.addView(progressBar);










     }




}
