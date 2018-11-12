package com.example.premal2.rvceconnect;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import org.jsoup.Jsoup;

import java.util.ArrayList;
import java.util.Arrays;

public class TeacherHome extends Fragment
{
    GridView gv;
    TeacherHomeAdapter adapter;


    public static TeacherHome newInstance()
    {
        TeacherHome fragment = new TeacherHome();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.teacher_home, container, false);

        gv =(GridView) view.findViewById(R.id.gridviewhome);

        adapter=new TeacherHomeAdapter(view.getContext(),getData());


        gv.setAdapter(adapter);


        return view;

    }

    /*retrieve all the sections that a teacher teaches */
    public String[] name_of_section=new String[]{"1C","1A","1B","1D"};

    /*retrieve the name of the subjects  of sections that the teacher teaches */
    public String[] name_of_subject=new String[]{"s1","s2","s3","s4"};
    static String[] studentnames=new String[]{"name1","name2","name3","name4","name5","name6"};
    static String[] studentusn=new String[] {"usn1","usn2","usn3","usn4","usn5","usn6"};
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
            String url="http://192.168.43.110/connect3.php?query=SELECT%20staff_section_course.section,course_table.coursename%20FROM%20staff_section_course,course_table%20WHERE%20staff_section_course.courseid=course_table.courseid%20AND%20staff_section_course.staffid=%27CSE_SOM%27";
            String url2="http://192.168.43.110/connect4.php?query=SELECT student_personal.firstname,student_personal.lastname,student_personal.usn FROM student_pro,student_personal WHERE student_pro.usn=student_personal.usn AND student_pro.sem='5C'";
            try
            {
                Log.d("e","works fne1");
                org.jsoup.nodes.Document document= Jsoup.connect(url).get();
                org.jsoup.nodes.Document document1=Jsoup.connect(url2).get();
                Log.d("e","works fne2");
                Log.d("e",document.getElementById("s1").text());
                Log.d("e",document1.getElementById("s1").text());
                name_of_section[0]=document.getElementById("s1").text();
                name_of_section[1]=document.getElementById("s2").text();
                name_of_section[2]=document.getElementById("s3").text();
                name_of_section[3]=document.getElementById("s4").text();
                name_of_subject[0]=document.getElementById("c1").text();
                name_of_subject[1]=document.getElementById("c2").text();
                name_of_subject[2]=document.getElementById("c3").text();
                name_of_subject[3]=document.getElementById("c4").text();
                studentnames[0]=document1.getElementById("s1").text()+" "+document1.getElementById("c1").text();
                studentnames[1]=document1.getElementById("s2").text()+" "+document1.getElementById("c2").text();
                studentnames[2]=document1.getElementById("s3").text()+" "+document1.getElementById("c3").text();
                studentnames[3]=document1.getElementById("s4").text()+" "+document1.getElementById("c4").text();
                studentnames[4]=document1.getElementById("s5").text()+" "+document1.getElementById("c5").text();
                studentnames[5]=document1.getElementById("s6").text()+" "+document1.getElementById("c6").text();
                studentusn[0]=document1.getElementById("u1").text();
                studentusn[1]=document1.getElementById("u2").text();
                studentusn[2]=document1.getElementById("u3").text();
                studentusn[3]=document1.getElementById("u4").text();
                studentusn[4]=document1.getElementById("u5").text();
                studentusn[5]=document1.getElementById("u6").text();
                Log.d("e",studentusn[1]);
                Log.d("e","changed");
            }
            catch (Exception e)
            {

                Log.d("e","works fne");
                e.printStackTrace();
                Log.d("e","problem");
            }

            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);
        }
    }
    private ArrayList getData()
    {
        ArrayList<SectionCardsForTeacherHome> sections=new ArrayList<>();

        SectionCardsForTeacherHome s=new SectionCardsForTeacherHome();
        new tempval().execute();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /*retrive the names of the students in each section and add it in the same order to the list */


        ArrayList<ArrayList<String>> studentnameslist=new ArrayList<>();

        for(int i=0;i<name_of_section.length;i++)
        {
            studentnameslist.add(new ArrayList<String>(Arrays.asList(studentnames)));
        }




        int number_of_subjects_handled=name_of_section.length;

        for(int i=0;i<number_of_subjects_handled;i++)
        {
            s=new SectionCardsForTeacherHome();
            s.setSectionname(name_of_section[i]);
            s.setSubjectname(name_of_subject[i]);
            s.setStudentnames(studentnameslist.get(i));
            sections.add(s);
        }




        return sections;
    }




}
