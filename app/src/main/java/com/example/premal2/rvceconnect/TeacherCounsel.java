package com.example.premal2.rvceconnect;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

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


    /*query to retrieve USN, name and phone number of the student */

        final HashMap<String,String> name_phone =new HashMap<>();
        studentnames.add("SHUBHAM");
        studentnames.add("PREMAL");
        studentnames.add("SAI SHOURIE");

        name_phone.put("SHUBHAM","8830800912");
        name_phone.put("PREMAL","8982625174");
        name_phone.put("SAI SHOURIE","8830800912");


        View view =inflater.inflate(R.layout.counsel_list_layout, container, false);


      final  ListView lv=view.findViewById(R.id.counsellingstudentlist);

      Button smsmessage=view.findViewById(R.id.instasms);



        ArrayAdapter<String> attadapter=new ArrayAdapter<String>(getContext(),R.layout.card_for_teacher_counsel,R.id.studentnamedisplay,studentnames);


        lv.setAdapter(attadapter);






     lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
     {
         @Override
         public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
         {

             Log.i("ITEM NAME",lv.getItemAtPosition(i).toString());

             Toast.makeText(getContext(),""+lv.getItemAtPosition(i),Toast.LENGTH_SHORT).show();

   //          startActivity(new Intent(getActivity().getApplicationContext(),TeacherStudentGaurdianContact.class));



             Intent intent = new Intent(Intent.ACTION_DIAL);
             intent.setData(Uri.parse("tel:"+name_phone.get(lv.getItemAtPosition(i))));
             startActivity(intent);


             return;
         }
     });


   smsmessage.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view)
       {






       }
   });









        return view;

    }

}
