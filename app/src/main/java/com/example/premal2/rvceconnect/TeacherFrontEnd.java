package com.example.premal2.rvceconnect;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class TeacherFrontEnd extends AppCompatActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item)
        {
            android.support.v4.app.Fragment selectedFragment = null;


            switch (item.getItemId())
            {

                case R.id.navigation_home:
                    selectedFragment = TeacherHome.newInstance();
                    break;


                case R.id.navigation_performance_dashboard:
                    selectedFragment = TeacherPerformanceDashboard.newInstance();
                    break;


                case R.id.navigation_counsel:
                    selectedFragment = TeacherCounsel.newInstance();
                    break;


            }

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.teacherfrontendcontainer, selectedFragment);
            transaction.commit();

            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_front_end);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.teacherfrontendcontainer, TeacherHome.newInstance());
        transaction.commit();

    }

}
