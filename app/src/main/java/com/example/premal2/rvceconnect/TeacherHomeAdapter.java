package com.example.premal2.rvceconnect;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;



public class TeacherHomeAdapter extends BaseAdapter {

   Context c;
   ArrayList<SectionCardsForTeacherHome> sections;


   public TeacherHomeAdapter(Context c,ArrayList<SectionCardsForTeacherHome> sections)
   {
     this.c=c;
     this.sections=sections;
   }

    @Override
    public int getCount()
    {

        return sections.size();
    }

    @Override
    public Object getItem(int i)
    {
        return sections.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        if(view ==null)
        {
            view= LayoutInflater.from(c).inflate(R.layout.card_for_teacher_home,viewGroup,false);
        }

        final SectionCardsForTeacherHome s= (SectionCardsForTeacherHome) this.getItem(i);

        TextView nameTxt= (TextView) view.findViewById(R.id.SectionNameTxt);
        TextView propTxt= (TextView) view.findViewById(R.id.subjectnameTxt);

        //BIND
        nameTxt.setText(s.getSectionname());
        propTxt.setText(s.getSubjectname());


         view.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v)
            {
                Toast.makeText(c, s.getSubjectname(), Toast.LENGTH_SHORT).show();

                Intent i = new Intent(c,TeacherAttendanceUpdate.class);
                i.putExtra("studentnameslist",s.getStudentnames());
                c.startActivity(i);



            }
        });

        return view;


    }



}
