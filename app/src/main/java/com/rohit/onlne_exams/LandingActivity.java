package com.rohit.onlne_exams;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

import com.rohit.onlne_exams.student.Activities.SLoginActivity;
import com.rohit.onlne_exams.teacher.Activities.TLoginActivity;

public class LandingActivity extends AppCompatActivity {

    Button patient,doctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        patient=findViewById(R.id.patient);
        doctor=findViewById(R.id.doctor);
        


        patient.setOnClickListener(view -> {

            Intent intent=new Intent(getApplicationContext(), SLoginActivity.class);
            startActivity(intent);
            finish();
        });

        doctor.setOnClickListener(view -> {

            Intent intent=new Intent(getApplicationContext(), TLoginActivity.class);
            startActivity(intent);
            finish();
        });

    }


}