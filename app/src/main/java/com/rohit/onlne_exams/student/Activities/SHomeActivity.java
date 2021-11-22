package com.rohit.onlne_exams.student.Activities;

import static android.widget.Toast.LENGTH_LONG;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.rohit.onlne_exams.R;
import com.rohit.onlne_exams.databinding.SactivityHomeBinding;


public class SHomeActivity extends AppCompatActivity  {


    Button scan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sactivity_home);



        scan=findViewById(R.id.scan);


        scan.setOnClickListener(view -> {


            scanqr();

        });


    }

    private void scanqr() {

        IntentIntegrator intentIntegrator=new IntentIntegrator(SHomeActivity.this);
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        intentIntegrator.setCameraId(0);
        intentIntegrator.setOrientationLocked(true);
        intentIntegrator.setPrompt("Scanning");
        intentIntegrator.setBeepEnabled(true);
        intentIntegrator.setBarcodeImageEnabled(true);
        intentIntegrator.initiateScan();


    }


    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {


        IntentResult result= IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result!=null &&result.getContents()!=null){

            //Toast.makeText(login.this,result.getContents(),Toast.LENGTH_LONG).show();

            String url=result.getContents();
            Intent intent=new Intent(SHomeActivity.this,Starttest.class);
            intent.putExtra("US",url);
            startActivity(intent);
            finish();

            // Toast.makeText(Scan.this,url,Toast.LENGTH_LONG).show();

        }else{

            Toast.makeText(SHomeActivity.this,"Please scan properly",LENGTH_LONG).show();

        }
        super.onActivityResult(requestCode, resultCode, data);
    }





}