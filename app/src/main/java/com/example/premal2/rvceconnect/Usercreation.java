package com.example.premal2.rvceconnect;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jsoup.Jsoup;

public class Usercreation extends AppCompatActivity {

    EditText firstname;
    EditText lastname;
    EditText emailid;
    EditText password;
    EditText password2;
    EditText bdate;
    EditText categ;
    EditText blooggroup;
    EditText phoneno;
    EditText phoneno2;
    EditText address;
    EditText dept;
    EditText counsellor;
    EditText scheme;
    EditText usn;
    EditText sem;
    EditText section;
    ProgressBar process;
    FirebaseAuth mAuth;
    public class temp extends AsyncTask<Void,Void,Void>
    {
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();

        }
        @Override
        protected Void doInBackground(Void... voids)
        {
            String url="http://192.168.43.110/connect8.php?query=INSERT INTO `student_personal` (`usn`, `firstname`, `midname`, `lastname`, `bdate`, `category`, `bloodgroup`, `phoneno`, `phoneno2`, `address`, `dept`, `cousellor`, `scheme`) VALUES ('"+usn.getText().toString()+"', '"+firstname.getText().toString()+"', '', '"+lastname.getText().toString()+"', '"+bdate.getText().toString()+"', '"+categ.getText().toString()+"', '"+blooggroup.getText().toString()+"', '"+phoneno.getText().toString()+"', '"+phoneno2.getText().toString()+"', '"+address.getText().toString()+"', '"+dept.getText().toString()+"', '"+counsellor.getText().toString()+"', '"+scheme.getText().toString()+"')";
            String url2="http://192.168.43.110/connect8.php?query=INSERT INTO `student_pro` (`usn`, `sem`, `rvcemailid`, `section`, `backlogs`) VALUES ('"+usn.getText().toString()+"', '"+sem.getText().toString()+"', '"+emailid.getText().toString()+"', '"+sem.getText().toString()+"', '0')";
            String url3="http://192.168.43.110/connect8.php?query=INSERT INTO `course_cgpa` (`usn`, `courseid`, `gp`, `classatd`, `totalclass`, `quiz1`, `test1`, `quiz2`, `test2`, `quiz3`, `test3`) VALUES ('"+usn.getText().toString()+"', '16HSI51', '9', '1', '1', '0', '0', '0', '0', '0', '0'),('"+usn.getText().toString()+"', '16CS52', '9', '1', '1', '0', '0', '0', '0', '0', '0'),('"+usn.getText().toString()+"', '16CS53', '9', '1', '1', '0', '0', '0', '0', '0', '0'), ('"+usn.getText().toString()+"', '16CS54', '9', '1', '1', '0', '0', '0', '0', '0', '0'), ('"+usn.getText().toString()+"', '16CS55', '9', '1', '1', '0', '0', '0', '0', '0', '0'), ('"+usn.getText().toString()+"', '16CS5A', '9', '1', '1', '0', '0', '0', '0', '0', '0'), ('"+usn.getText().toString()+"', '16CS5B', '9', '1', '1', '0', '0', '0', '0', '0', '0') ";
            try
            {
                org.jsoup.nodes.Document document= Jsoup.connect(url).get();
                org.jsoup.nodes.Document document1=Jsoup.connect(url2).get();
                org.jsoup.nodes.Document document2=Jsoup.connect(url3).get();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(Void aVoid)
        {
            process.setVisibility(View.INVISIBLE);
            super.onPostExecute(aVoid);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usercreation);
        mAuth=FirebaseAuth.getInstance();
        Button logout=(Button) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(Usercreation.this,MainActivity.class));
            }
        });

        firstname=(EditText) findViewById(R.id.firstname);
        lastname=(EditText) findViewById(R.id.lastname);
        emailid=(EditText) findViewById(R.id.emailid);
        password=(EditText) findViewById(R.id.password);
        password2=(EditText) findViewById(R.id.password2);
        bdate=(EditText) findViewById(R.id.bdate);
        categ=(EditText) findViewById(R.id.categ);
        blooggroup=(EditText) findViewById(R.id.bloodgroup);
        phoneno=(EditText) findViewById(R.id.phoneno1);
        phoneno2=(EditText) findViewById(R.id.phoneno2);
        address=(EditText) findViewById(R.id.address);
        dept=(EditText) findViewById(R.id.dept);
        counsellor=(EditText) findViewById(R.id.counsellor);
        scheme=(EditText) findViewById(R.id.scheme);
        usn=(EditText) findViewById(R.id.usn);
        sem=(EditText) findViewById(R.id.sem);
        section=(EditText) findViewById(R.id.section);
        Button create=(Button) findViewById(R.id.create_account);
        process=(ProgressBar) findViewById(R.id.process);
        process.setVisibility(View.INVISIBLE);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(firstname.getText().toString().equals("")||lastname.getText().toString().equals("")||emailid.getText().toString().equals("")||password.getText().toString().equals("")||password2.getText().toString().equals("")||bdate.getText().toString().equals("")||categ.getText().toString().equals("")||blooggroup.getText().toString().equals("")||phoneno.getText().toString().equals("")||phoneno2.getText().toString().equals("")||address.getText().toString().equals("")||dept.getText().toString().equals("")||counsellor.getText().toString().equals("")||scheme.getText().toString().equals("")||usn.getText().toString().equals("")||sem.getText().toString().equals("")||section.getText().toString().equals(""))
                    Toast.makeText(Usercreation.this,"Please Enter all fields!",Toast.LENGTH_SHORT).show();
                else
                {
                    if(password.getText().toString().equals(password2.getText().toString()))
                    {
                        process.setVisibility(View.VISIBLE);
                        mAuth.signOut();
                        mAuth.createUserWithEmailAndPassword(emailid.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("e", "createUserWithEmail:success");
                                    new temp().execute();
                                    Toast.makeText(Usercreation.this,"Account Created!",Toast.LENGTH_SHORT).show();

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(Usercreation.this,"Username is not availbale!",Toast.LENGTH_SHORT).show();
                                    Log.w("e", "createUserWithEmail:failure", task.getException());
                                    process.setVisibility(View.INVISIBLE);

                                }
                            }
                        });
                        mAuth.signInWithEmailAndPassword(MainActivity.cred1,MainActivity.cred2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                            }
                        });
                    }
                    else
                    {
                        Toast.makeText(Usercreation.this,"Passwords dont match!",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
