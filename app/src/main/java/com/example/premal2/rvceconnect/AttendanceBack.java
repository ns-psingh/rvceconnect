package com.example.premal2.rvceconnect;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.math.*;
import java.util.Map;
import java.util.Set;

public class AttendanceBack extends Fragment
{

  static  int[] classes_attended=new int[]{0,0,0,0,0,0,0};
  static int[] classes_held=new int[]{1,1,1,1,1,1,1};
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

      /*color of progress bar*/
      int[] progress_color=new int[]{Color.BLUE,Color.MAGENTA,Color.YELLOW,Color.RED,Color.GREEN,Color.CYAN};


      /*temporary array that holds the list of subjects*/
      String[] subjects=new String[]{"IPRE","DBMS","MCES","SE","CCN","ANN","OT"};


       /*temporary array that holds the number of classes attended*/
      // final int[] classes_attended =new int[]{12,3,5,7,13,8,9};


       /*temporary array that holds the total number of classes held*/
     //  final int[] classes_held =new int[]{13,13,13,13,13,13,13};

      /*Arraylist that holds the list of subjects */
      final ArrayList<String> subjectnames = new ArrayList<>();
      subjectnames.addAll(Arrays.asList(subjects));



      /*number of subjects*/
      final int number_of_subjects= subjectnames.size();





      /*this hashmap will hold a mapping of subject names to percentage of attendance corressponding to it*/
      HashMap<String,Integer> subject_to_attendance_map =new HashMap<>();
      Log.d("e","number="+number_of_subjects);
      for(int i=0;i<number_of_subjects;i++)
      {
          //Log.d("e",classes_attended[i]+" "+classes_held[i]);
        /*populate the hashmap with values */
        int y=Math.round((classes_attended[i]*100/classes_held[i]));
       // Log.d("e",Math.round(y)+" ");
        subject_to_attendance_map.put(subjectnames.get(i),y);

       // Log.d("e",Math.round(y)+" ");

      }
      Set<Map.Entry<String,Integer>> hashset=subject_to_attendance_map.entrySet();
      for(Map.Entry entry:hashset)
        Log.d("e",entry.getValue()+"");
        /*Connect with frontend(attendance_back.xml)*/
      final View atnbackview=inflater.inflate(R.layout.attendance_back, container, false);





      /*create a new linear layout that will serve as parent of the other linear layouts */
      LinearLayout super_parent_layout=new LinearLayout(atnbackview.getContext());
      super_parent_layout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
      super_parent_layout.setOrientation(LinearLayout.VERTICAL);



      for(int i=0;i<number_of_subjects;i++)
      {
        /*each of the linear layouts will hold exactly one subject's attendance details */
        LinearLayout parentLayout =new LinearLayout(atnbackview.getContext());
        parentLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,50));
        parentLayout.setOrientation(LinearLayout.HORIZONTAL);



        /*the progress bar will indicate percentage attendance in each subject*/
        ProgressBar atnprogress = new ProgressBar(atnbackview.getContext(),null,android.R.attr.progressBarStyleHorizontal);
        atnprogress.setLayoutParams(new LinearLayout.LayoutParams(600,50));
        atnprogress.setIndeterminate(false);
        atnprogress.getProgressDrawable().setColorFilter(progress_color[i%5], PorterDuff.Mode.SRC_IN);
        atnprogress.setMax(100);

        /*TextView subjectname will hold the subject's name */
        TextView   subjectname =new TextView(atnbackview.getContext());

        /*TextView percent_attendance will hold the subjects attendance% */
        TextView   percent_attendance=new TextView(atnbackview.getContext());

        /*snippet to format the typeface and define the width and height of the TextView*/
        subjectname.setTypeface(null, Typeface.BOLD);
        subjectname.setLayoutParams(new LinearLayout.LayoutParams(220,50));

        percent_attendance.setTypeface(null,Typeface.BOLD);
        percent_attendance.setLayoutParams(new LinearLayout.LayoutParams(150,50));



        subjectname.setText(subjectnames.get(i));




        atnprogress.setProgress(subject_to_attendance_map.get(subjectnames.get(i)),true);

        /*If the percentage attendance in the subject is <75% the subject name will be colored red */
        if(atnprogress.getProgress()<75)
        {
          subjectname.setTextColor(Color.RED);
        }


        percent_attendance.setText("\t"+atnprogress.getProgress()+"%");


        /*Add all the views to the parent layout*/
        parentLayout.addView(subjectname);
        parentLayout.addView(atnprogress);
        parentLayout.addView(percent_attendance);


        ViewGroup.MarginLayoutParams params=new ViewGroup.MarginLayoutParams(parentLayout.getLayoutParams());
        params.setMargins(0,0,0,25);





        super_parent_layout.addView(parentLayout,params);
        super_parent_layout.setDividerPadding(10);



      }




     /*clicking on this button will open up a detailed list
       of number of classes attended and number of classes
       held so far in each subject
      */



      Button open_dialog_box =new Button(atnbackview.getContext());
      open_dialog_box.setGravity(Gravity.CENTER);
      open_dialog_box.setText("VIEW DETAILS");
      open_dialog_box.setTextColor(Color.WHITE);
      open_dialog_box.setBackgroundColor(Color.BLUE);



      open_dialog_box.setOnClickListener(new View.OnClickListener()
      {
        @Override
        public void onClick(View view)
        {


          View prompt =inflater.inflate(R.layout.alert_dialog_attendance,null);


          AlertDialog.Builder builder;

          if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.LOLLIPOP)
          {
            builder= new AlertDialog.Builder(atnbackview.getContext(),android.R.style.Theme_Material_Dialog_Alert);
          }
          else
          {
            builder = new AlertDialog.Builder(atnbackview.getContext());
          }


          builder.setView(prompt);


          builder.setTitle("ATTENDANCE DETAILS");

          TableLayout parent_table_layout =prompt.findViewById(R.id.TableLayoutAttendanceBack);

          parent_table_layout.setStretchAllColumns(true);

          TableRow   parent_row =new TableRow(prompt.getContext());
          parent_row.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));




           TextView ntv=new TextView(prompt.getContext());
           TextView ntv1 =new TextView(prompt.getContext());
           TextView ntv2=new TextView(prompt.getContext());

           ntv.setTextColor(Color.WHITE);
           ntv1.setTextColor(Color.WHITE);
           ntv2.setTextColor(Color.WHITE);


           ntv.setText(" Subject");
           ntv1.setText(" Classes Attended");
           ntv2.setText(" Classes Held");

           parent_row.addView(ntv);
           parent_row.addView(ntv1);
           parent_row.addView(ntv2);

           parent_row.setGravity(Gravity.CENTER_HORIZONTAL);


           parent_table_layout.addView(parent_row);


          for(int i=0;i<number_of_subjects;i++)
          {

            TableRow tr=new TableRow(prompt.getContext());
         /*   TableRow.LayoutParams lp=new TableRow.LayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

      */
            TextView tv=new TextView(prompt.getContext());
            TextView tv1=new TextView(prompt.getContext());
            TextView tv2=new TextView(prompt.getContext());


            tv1.setGravity(Gravity.CENTER_HORIZONTAL);
            tv2.setGravity(Gravity.CENTER_HORIZONTAL);

            tv.setTextColor(Color.WHITE);
            tv1.setTextColor(Color.WHITE);
            tv2.setTextColor(Color.WHITE);



            tv.setText(" "+subjectnames.get(i));
            tv1.setText(" \t"+classes_attended[i]);
            tv2.setText(" \t"+classes_held[i]);


            tr.addView(tv);
            tr.addView(tv1);
            tr.addView(tv2);
            tr.setGravity(Gravity.CENTER_HORIZONTAL);



            parent_table_layout.addView(tr);


          }


//           builder.setPositiveButton("NEXT",
//                   new DialogInterface.OnClickListener()
//                   {
//             @Override
//             public void onClick(DialogInterface dialogInterface, int i)
//             {
//
//             }
//           });

          builder.setNegativeButton("BACK",
                  new DialogInterface.OnClickListener()
                  {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                      dialogInterface.cancel();
                    }
                  });


        builder.show();

        }

      });








      super_parent_layout.addView(open_dialog_box);
      RelativeLayout rl = (RelativeLayout)atnbackview.findViewById(R.id.attendance_back_layout);
      rl.addView(super_parent_layout);
      return atnbackview;








    }








}