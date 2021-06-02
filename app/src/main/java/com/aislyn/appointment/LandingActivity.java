package com.aislyn.appointment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

import com.aislyn.appointment.doctor.Activities.DRHomeActivity;
import com.aislyn.appointment.doctor.SharedPrefmanager;
import com.aislyn.appointment.users.Activities.HomeActivity;
import com.aislyn.appointment.users.Activities.LoginActivity;

public class LandingActivity extends AppCompatActivity {

    Button patient,doctor;
    SharedPrefmanager drsharedPrefmanager;
    SharedPrefmanager sharedPrefmanager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        patient=findViewById(R.id.patient);
        doctor=findViewById(R.id.doctor);

        drsharedPrefmanager =new SharedPrefmanager(getApplicationContext());
        sharedPrefmanager=new SharedPrefmanager(getApplicationContext());


        patient.setOnClickListener(view -> {

            Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        });

        doctor.setOnClickListener(view -> {

            Intent intent=new Intent(getApplicationContext(), com.aislyn.appointment.doctor.Activities.LoginActivity.class);
            startActivity(intent);
            finish();
        });

    }


}