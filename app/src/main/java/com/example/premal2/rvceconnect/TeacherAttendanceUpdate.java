package com.example.premal2.rvceconnect;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
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

import org.jsoup.Jsoup;

import java.util.ArrayList;
import java.util.ListIterator;

public class TeacherAttendanceUpdate extends AppCompatActivity
{

    ArrayList<String> studentnamelist;

    ArrayList<String> presentstudents=new ArrayList<>();
    public int p[]=new int[6];
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
                 //int p[]=new int[6];
                 int i2=0;
                 int j=0;
                 Log.d("e","entered");
                 for(String s: presentstudents)
                 {

                     while(!s.equals(TeacherHome.studentnames[i2]))
                     {Log.d("e",s);
                     i2++;
                     }
                     Log.d("e",s+" "+TeacherHome.studentnames[i2]);
                     p[j]=i2;
                     //Log.d("e",p[j]+" ");
                     i2++;
                     j++;
                 }
                 for(j=0;j<6;j++)
                     Log.d("e",p[j]+"");
                 Log.d("e","students num="+presentstudents.size());

                 new tempval().execute();
                 /*update the attendance of the students present */
                 /*use the studentspresntlist for this purpose */

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
    public class tempval extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }
        @Override
        protected Void doInBackground(Void... voids)
        {
            Log.d("e","came here too");
            int i;
            String url;
            String url2;
            for(i=0;i<presentstudents.size();i++) {
                url = "http://192.168.43.110/connect5.php?query=UPDATE%20course_cgpa%20SET%20classatd=classatd%2B1%20WHERE%20usn=%27" + TeacherHome.studentusn[p[i]] + "%27%20AND%20courseid=%2716HSI51%27";
                url2 = "http://192.168.43.110/connect5.php?query=UPDATE%20course_cgpa%20SET%20totalclass=totalclass%2B1%20WHERE%20usn=%27" + TeacherHome.studentusn[p[i]] + "%27%20AND%20courseid=%2716HSI51%27";
                //Log.d("e", url);

                //  String url2="http://192.168.43.110/connect4.php?query=SELECT%20student_personal.firstname,student_personal.lastname%20FROM%20student_pro,student_personal%20WHERE%20student_pro.usn=student_personal.usn%20AND%20student_pro.sem=%275C%27";
                try {
                  //  Log.d("e", "works fne1");
                    org.jsoup.nodes.Document document = Jsoup.connect(url).get();
                    org.jsoup.nodes.Document document1=Jsoup.connect(url2).get();
                    //  org.jsoup.nodes.Document document1=Jsoup.connect(url2).get();
                    Log.d("e", url+"and"+url2);
                    //   Log.d("e",document.getElementById("s1").text());
                    //   Log.d("e",document1.getElementById("s1").text());
                    //   name_of_section[0]=document.getElementById("s1").text();
                    //
                } catch (Exception e) {

                    Log.d("e", "works fne");
                    e.printStackTrace();
                    Log.d("e", "problem");
                }
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);
        }
    }




}
