package com.example.premal2.rvceconnect;

import java.util.ArrayList;

public class SectionCardsForTeacherPerformance
{

    String sectionname,subjectname;
    ArrayList<Integer> studentmarks;


    public String getSectionname()
    {
        return sectionname;
    }

    public void setSectionname(String name)
    {
        this.sectionname = name;
    }

    public String getSubjectname()
    {
        return subjectname;
    }

    public void setSubjectname(String name)
    {
        this.subjectname = name;
    }

    public void setStudentmarks(ArrayList<Integer> studentnames)
    {
        this.studentmarks = studentnames;
    }

    public ArrayList<Integer> getStudentmarks()
    {
        return studentmarks;
    }






}


