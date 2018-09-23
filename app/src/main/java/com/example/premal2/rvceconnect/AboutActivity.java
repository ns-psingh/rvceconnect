package com.example.premal2.rvceconnect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.anychart.core.annotations.Line;
import com.vansuita.materialabout.builder.AboutBuilder;
import com.vansuita.materialabout.views.AboutView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_about);

        AboutView view = AboutBuilder.with(this)
                .setPhoto(R.drawable.rvce_logo)
                .setCover(R.mipmap.profile_cover)
                .setName("RVCE")
                .setSubTitle("Educational Institute Affiliated with VTU")
                .setBrief("This app was built by SHUBHAM PHAL and PREMAL SINGH")
                .setAppIcon(R.mipmap.ic_launcher)
                .setAppName(R.string.app_name)
                .addGooglePlayStoreLink("")
                .addGitHubLink("shubhamphal/rvceconnect")
                .addFacebookLink("")
                .addLinkedInLink("")
                .addFiveStarsAction()
                .setVersionNameAsAppSubTitle()
                .addShareAction(R.string.app_name)
                .addEmailLink("")
                .setWrapScrollView(true)
                .setLinksAnimated(true)
                .setShowAsCard(true)
                .build();


        LinearLayout aboutparent=(LinearLayout)this.findViewById(R.id.aboutLinearLayout);
        aboutparent.addView(view);



    }
}
