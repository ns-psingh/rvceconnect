package com.example.premal2.rvceconnect;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class TeacherCounsel extends Fragment
{
    public static TeacherCounsel newInstance()
    {
        TeacherCounsel fragment = new TeacherCounsel();
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

        ArrayList<String> studentnames=new ArrayList<>();

        studentnames.add("SHUBHAM");
        studentnames.add("PREMAL");

        View view =inflater.inflate(R.layout.counsel_list_layout, container, false);


        ListView lv=view.findViewById(R.id.counsellingstudentlist);


        ArrayAdapter<String> attadapter=new ArrayAdapter<String>(getContext(),R.layout.card_for_teacher_counsel,R.id.studentnamedisplay,studentnames);


        lv.setAdapter(attadapter);






     lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

             Log.i("CLICKED","CLICK");
             startActivity(new Intent(getActivity().getApplicationContext(),TeacherStudentGaurdianContact.class));

             return;
         }
     });












        return view;

    }

}
