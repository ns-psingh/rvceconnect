package com.example.premal2.rvceconnect;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.jsoup.Jsoup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class TeacherCounsel extends Fragment
{
    public static TeacherCounsel newInstance()
    {
        TeacherCounsel fragment = new TeacherCounsel();
        return fragment;
    }
    public HashMap<String,String> name_phone =new HashMap<>();
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
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
            String url="http://192.168.43.110/connect2.php?query=SELECT%20firstname,midname,lastname,phoneno%20FROM%20`student_personal`%20WHERE%20cousellor=%27CSE_AZR%27";
            try
            {
                Log.d("e","works fne1");
                org.jsoup.nodes.Document document= Jsoup.connect(url).get();

                Log.d("e","works fne2");
                Log.d("e",document.getElementById("f5").text());
                name_phone.put(document.getElementById("f1").text()+" "+document.getElementById("m1").text()+" "+document.getElementById("l1").text(),document.getElementById("ph1").text());
                name_phone.put(document.getElementById("f2").text()+" "+document.getElementById("m2").text()+" "+document.getElementById("l2").text(),document.getElementById("ph2").text());
                name_phone.put(document.getElementById("f3").text()+" "+document.getElementById("m3").text()+" "+document.getElementById("l3").text(),document.getElementById("ph3").text());
                name_phone.put(document.getElementById("f4").text()+" "+document.getElementById("m4").text()+" "+document.getElementById("l4").text(),document.getElementById("ph4").text());
                name_phone.put(document.getElementById("f5").text()+" "+document.getElementById("m5").text()+" "+document.getElementById("l5").text(),document.getElementById("ph5").text());
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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        Log.d("e","came here");
        new tempval().execute();

    /*query to retrieve name and phone number of the students under the teachers counsel */

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("e","crossed");
     //   name_phone.put("SHUBHAM","8830800912");
       // name_phone.put("PREMAL","8982625174");
       // name_phone.put("SAI SHOURIE","8830800912");

     /*modify only the above section */









        View view =inflater.inflate(R.layout.counsel_list_layout, container, false);


      final  ListView lv=view.findViewById(R.id.counsellingstudentlist);

      final Button smsmessage=view.findViewById(R.id.instasms);


        Set<String> studentnames=name_phone.keySet();

        ArrayList<String> student_list_for_view=new ArrayList<>();
        student_list_for_view.addAll(studentnames);

        ArrayAdapter<String> attadapter=new ArrayAdapter<String>(getContext(),R.layout.card_for_teacher_counsel,R.id.studentnamedisplay,student_list_for_view);


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


   smsmessage.setOnClickListener(new View.OnClickListener()
   {
       @Override
       public void onClick(View view)
       {

           AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
           LayoutInflater inflater = getActivity().getLayoutInflater();
           final View dialogView = inflater.inflate(R.layout.custom_notification_dialog, null);
           dialogBuilder.setView(dialogView);



           dialogBuilder.setTitle("TYPE IN YOUR MESSAGE");

           final EditText textmessage=(EditText)dialogView.findViewById(R.id.message);


           dialogBuilder.setPositiveButton("CONFIRM", new DialogInterface.OnClickListener() {

               public void onClick(DialogInterface dialog, int whichButton)
               {


                   Set<String> keyset=name_phone.keySet();

                   String message = textmessage.getText().toString();

                   for(String key:keyset)
                   {
                       sendSms(name_phone.get(key),message);
                   }



                   //do something with edt.getText().toString();
               }
           });
           dialogBuilder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int whichButton)
               {
                   //pass
               }
           });
           AlertDialog b = dialogBuilder.create();
           b.show();










       }
   });









        return view;

    }

    private void sendSms(String phonenumber,String message)
    {

        SmsManager manager = SmsManager.getDefault();
        manager.sendTextMessage(phonenumber, null, message, null, null);


    }














}
