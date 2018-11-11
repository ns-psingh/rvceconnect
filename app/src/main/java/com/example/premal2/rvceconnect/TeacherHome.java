package com.example.premal2.rvceconnect;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

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

    private ArrayList getData()
    {
        ArrayList<SectionCardsForTeacherHome> sections=new ArrayList<>();

        SectionCardsForTeacherHome s=new SectionCardsForTeacherHome();

        /*retrieve all the sections that a teacher teaches */
        String[] name_of_section=new String[]{"5C","7A","4B","3D"};

       /*retrieve the name of the subjects  of sections that the teacher teaches */
        String[] name_of_subject=new String[]{"IPR","BIG DATA","ALGORITHM","DATA STRUCTURES"};

       /*retrive the names of the students in each section and add it in the same order to the list */
        String[] studentnames=new String[]{"Sai praveen","Premal Singh","Shubham Phal","Sai Shourie","Anjan Kumar"};

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
