package com.example.premal2.rvceconnect;

import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

import org.jsoup.Jsoup;

import de.hdodenhof.circleimageview.CircleImageView;

public class StudentInfo extends AppCompatActivity {


    TextView name;
    TextView dob;
    TextView usn;
    TextView category;
    TextView bloodgroup;
    TextView phone1;
    TextView phone2;
    TextView address;
    TextView counsellor;
    TextView dept;
    TextView sem;
    TextView section;
    TextView scheme;
    TextView rvceid;
    CircleImageView profilepic;
    FirebaseAuth mAuth;
    ProgressBar prog;
    Button logout;
    Button pwd;
    Button edit;
    public class background extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected void onPreExecute()
        {
            prog.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }
        protected Void doInBackground(Void...voids)
        {
            String url="http://192.168.43.110/connect7.php?query=SELECT firstname,lastname,bdate,student_personal.usn,category,bloodgroup,phoneno,phoneno2,student_personal.scheme,student_personal.address,staff.name,dept,student_pro.sem,student_pro.section FROM student_personal,student_pro,staff WHERE student_pro.usn=student_personal.usn AND student_pro.rvcemailid='"+mAuth.getCurrentUser().getEmail()+"' AND staff.staff_id=cousellor";
            Log.d("e",url);
            try
            {
                org.jsoup.nodes.Document document= Jsoup.connect(url).get();
                name.setText(document.getElementById("firstname").text()+" "+document.getElementById("lastname").text());
                dob.setText("Date of Birth: "+document.getElementById("bdate").text());
                usn.setText("USN: "+document.getElementById("usn").text());
                if(document.getElementById("category").text().equals("CMD"))
                    category.setText("Category of Admission: COMEDK");
                if(document.getElementById("category").text().equals("CET"))
                    category.setText("Category of Admission: KCET");
                if(document.getElementById("category").text().equals("MQ"))
                    category.setText("Category of Admission: Management Quota");
                bloodgroup.setText("Blood Group: "+document.getElementById("bloodgroup").text()+"ve");
                phone1.setText("Mob:"+document.getElementById("phoneno").text());
                phone2.setText("Mob:"+document.getElementById("phoneno2").text());
                address.setText("Address: "+document.getElementById("address").text());
                counsellor.setText("Counsellor: Prof."+document.getElementById("name").text());
                dept.setText("Department: "+document.getElementById("dept").text());
                sem.setText("Semester: "+document.getElementById("sem").text());
                section.setText("Section: "+document.getElementById("section").text());
                rvceid.setText("RVCE Mail ID: "+mAuth.getCurrentUser().getEmail());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid)
        {
            prog.setVisibility(View.INVISIBLE);
            profilepic.setVisibility(View.VISIBLE);
            Glide.with(getApplicationContext())
                    .load(StudentFrontEnd.imageurl)
                    .into(profilepic);
            super.onPostExecute(aVoid);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);
        mAuth=FirebaseAuth.getInstance();
        profilepic=(CircleImageView) findViewById(R.id.profilepic);
        profilepic.setVisibility(View.INVISIBLE);
        name=(TextView) findViewById(R.id.name);
        dob=(TextView) findViewById(R.id.dob);
        usn=(TextView) findViewById(R.id.usn);
        category=(TextView) findViewById(R.id.category);
        bloodgroup=(TextView) findViewById(R.id.bloodgroup);
        phone1=(TextView) findViewById(R.id.phone1);
        phone2=(TextView) findViewById(R.id.phone2);
        address=(TextView) findViewById(R.id.address);
        counsellor=(TextView) findViewById(R.id.counsellor);
        dept=(TextView) findViewById(R.id.dept);
        sem=(TextView) findViewById(R.id.sem);
        section=(TextView) findViewById(R.id.section);
        scheme=(TextView) findViewById(R.id.scheme);
        rvceid=(TextView) findViewById(R.id.rvceid);
        prog=(ProgressBar) findViewById(R.id.loading_prog);
        prog.setVisibility(View.INVISIBLE);
        name.setText("");
        dob.setText("");
        usn.setText("");
        category.setText("");
        bloodgroup.setText("");
        phone1.setText("");
        phone2.setText("");
        address.setText("");
        counsellor.setText("");
        dept.setText("");
        sem.setText("");
        section.setText("");
        scheme.setText("");
        rvceid.setText("");
        logout=(Button) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();

                startActivity(new Intent(StudentInfo.this,MainActivity.class));
            }
        });
        pwd=(Button) findViewById(R.id.changepwd);
        pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.stat=1;
                startActivity(new Intent(StudentInfo.this,FacingIssues.class));
            }
        });
        edit=(Button) findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MainActivity.stat=2;
                startActivity(new Intent(StudentInfo.this,FacingIssues.class));
            }
        });
        new background().execute();

    }
}
