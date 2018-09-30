package com.example.premal2.rvceconnect;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TeacherAnnouncements extends Fragment
{

    public static TeacherAnnouncements newInstance()
    {
        TeacherAnnouncements fragment = new TeacherAnnouncements();
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
        return inflater.inflate(R.layout.teacher_announcements, container, false);
    }


}
