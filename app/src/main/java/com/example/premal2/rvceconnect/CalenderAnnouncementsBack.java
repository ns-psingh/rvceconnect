package com.example.premal2.rvceconnect;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Calendar;

public class CalenderAnnouncementsBack extends Fragment
{



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        Calendar calendarEvent = Calendar.getInstance();
        Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra("beginTime", calendarEvent.getTimeInMillis());
        intent.putExtra("endTime", calendarEvent.getTimeInMillis() + 60 * 60 * 1000);
        intent.putExtra("title", "Sample Event");
        intent.putExtra("allDay", true);
        intent.putExtra("rule", "FREQ=YEARLY");
        startActivity(intent);




        return inflater.inflate(R.layout.calender_announcements_back, container, false);
    }

}
