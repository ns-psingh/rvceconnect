package com.example.premal2.rvceconnect;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class TeacherAttendanceUpdate extends AppCompatActivity
{

    ArrayList<String> studentnamelist;
    ListView lv;
    boolean select=true;
    String allpresent ="EVERY ONE IS PRESENT";
    Button selectordeselect;
    Context appcontext;
    Button submitbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_attendance_update);
        appcontext=getApplicationContext();


        studentnamelist=(ArrayList<String>)getIntent().getSerializableExtra("studentnameslist");





        lv=(ListView)findViewById(R.id.studentnamelistattupdate);
        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lv.setItemsCanFocus(false);

        selectordeselect=(Button)findViewById(R.id.selectordeselect);
        submitbutton=(Button)findViewById(R.id.submitbutton);



        ArrayAdapter<String> attadapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,studentnamelist);

       lv.setAdapter(attadapter);


        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l)
            {
                if(lv.isItemChecked(position))
                {
                    lv.setItemChecked(position,false);
                }
                else
                {
                    lv.setItemChecked(position,true);

                    Log.i("CHECKED",""+position);
                }

                return true;
            }
        });








      selectordeselect.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view)
          {

              if(select)
              {

                  for(int i=0;i<lv.getChildCount();i++)
                  {

                   lv.setItemChecked(i,true);

                   selectordeselect.setText("DESELECT ALL");

                   select =false;

                  }


              }
              else
              {

                  for(int i=0;i<lv.getChildCount();i++)
                  {

                      lv.setItemChecked(i,false);

                  }

                  selectordeselect.setText("SELECT ALL");

                  select=true;

               }

              }
      });


      submitbutton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view)
          {

             ArrayList<String> presentstudents=new ArrayList<>();
             ArrayList<String> absentstudents=new ArrayList<>();


             for(int i=0;i<lv.getChildCount();i++)
             {

                 if(lv.isItemChecked(i))
                 {
                       presentstudents.add(lv.getItemAtPosition(i).toString());
                 }
                 else
                 {
                     absentstudents.add(lv.getItemAtPosition(i).toString());
                 }

             }

             if(absentstudents.size()==0)
             {
                 absentstudents.add(allpresent);
             }
             else
             {
                 absentstudents.remove(allpresent);
             }




            Log.i("List of absentees",absentstudents.toString());




             LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);


             View builderview =inflater.inflate(R.layout.teacher_attendance_update_alert,null);

             AlertDialog.Builder alertbuilder;

              if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.LOLLIPOP)
              {
                  alertbuilder= new AlertDialog.Builder(TeacherAttendanceUpdate.this,android.R.style.Theme_Material_Dialog_Alert);
              }
              else
              {
                  alertbuilder= new AlertDialog.Builder(TeacherAttendanceUpdate.this);
              }


            ListView absenteeslist=builderview.findViewById(R.id.alertAbsentees);



              ArrayAdapter<String> attadapter=new ArrayAdapter<String>(TeacherAttendanceUpdate.this,R.layout.custom_list_to_display_absentees,R.id.absenteenames,absentstudents);










              absenteeslist.setAdapter(attadapter);







              alertbuilder.setView(builderview);



            int number_of_students_present;

            if(absentstudents.contains(allpresent))
            {
                number_of_students_present=0;
            }
            else
            {
                number_of_students_present=absentstudents.size();
            }

              alertbuilder.setTitle("ABSENTEE'S("+number_of_students_present+")");



              alertbuilder.setPositiveButton("CONFIRM SUBMISSION",
                   new DialogInterface.OnClickListener()
                   {
             @Override
             public void onClick(DialogInterface dialogInterface, int i)
             {


             }
                  });

                      alertbuilder.setNegativeButton("BACK",
                              new DialogInterface.OnClickListener()
                              {
                                  @Override
                                  public void onClick(DialogInterface dialogInterface, int i)
                                  {
                                      dialogInterface.cancel(); }
                              });




              alertbuilder.show();


          }
      });







    }




}
