package com.example.premal2.rvceconnect;

import java.util.ArrayList;

public class SectionCardsForTeacherHome
{

    String sectionname,subjectname;
    ArrayList<String> studentnames;


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

    public void setStudentnames(ArrayList<String> studentnames)
    {
        this.studentnames = studentnames;
    }

    public ArrayList<String> getStudentnames()
    {
        return studentnames;
    }
}
