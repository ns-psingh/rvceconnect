package com.example.premal2.rvceconnect;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TeacherPerformanceDashboard extends Fragment
{

    public static TeacherPerformanceDashboard newInstance()
    {
        TeacherPerformanceDashboard fragment = new TeacherPerformanceDashboard();
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
        return inflater.inflate(R.layout.teacher_performance_dashboard, container, false);
    }
}
