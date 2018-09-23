package com.example.premal2.rvceconnect;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import static android.app.Activity.RESULT_OK;

public class TimetableBack extends Fragment
{




    static  int RESULT_LOAD_IMG=1;
    String imgDecodableString;
    View timetablebackview;
    TouchImageView timetableimage;
    SharedPreferences sharedPreferences;
    String MY_PREF="TIMETABLE-URL";
    String pathtag="filepath";
    boolean updatebuttonvisibility=false;
    FloatingActionButton fab;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {


       timetablebackview= inflater.inflate(R.layout.timetable_back, container, false);
       timetableimage = (TouchImageView)timetablebackview.findViewById(R.id.timetableimage);
       fab=(FloatingActionButton)timetablebackview.findViewById(R.id.updateTimeTableButton);

       sharedPreferences=timetablebackview.getContext().getSharedPreferences(MY_PREF, Context.MODE_PRIVATE);
       String restoredText = sharedPreferences.getString(pathtag,null);

       fab.setVisibility(View.GONE);
       fab.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
       fab.setAlpha((float) 0.6);



       Log.i("Text","Text Restored");
      if(restoredText!=null)
      {

          timetableimage.setImageBitmap(BitmapFactory.decodeFile(restoredText));
          timetableimage.setScaleType(ImageView.ScaleType.FIT_XY);
          fab.setVisibility(View.VISIBLE);

      }
        else
          {
          timetableimage.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view)
              {

                  Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);// Start the Intent

                  startActivityForResult(galleryIntent, RESULT_LOAD_IMG);


              }
          });



          }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);// Start the Intent

                startActivityForResult(galleryIntent, RESULT_LOAD_IMG);


            }
        });



       return  timetablebackview;
    }





    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        super.onActivityResult(requestCode, resultCode, data);

        fab.setVisibility(View.VISIBLE);
        if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK && null != data)
        {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor =timetablebackview.getContext().getContentResolver().query(selectedImage,filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            Log.i("PICTURE PATH",""+picturePath);
            SharedPreferences.Editor editor = timetablebackview.getContext().getSharedPreferences(MY_PREF, Context.MODE_PRIVATE).edit();
            editor.putString(pathtag, picturePath);
            editor.apply();


            timetableimage.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            timetableimage.setScaleType(ImageView.ScaleType.FIT_XY);

        }


    }



}
