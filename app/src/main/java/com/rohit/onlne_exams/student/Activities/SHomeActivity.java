package com.rohit.onlne_exams.student.Activities;

import static android.widget.Toast.LENGTH_LONG;

import static com.rohit.onlne_exams.student.Activities.SLoginActivity.STUDENT_ID;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.rohit.onlne_exams.GLOBAL;
import com.rohit.onlne_exams.R;
import com.rohit.onlne_exams.network.RetrofitClient;
import com.rohit.onlne_exams.student.ModelResponse.SRegisterResponse;

import java.util.ArrayList;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SHomeActivity extends AppCompatActivity  {


    Button scan;
    CircularProgressButton done;


    AutoCompleteTextView  actv;
    public static String Choosensub_code;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sactivity_home);



        done=findViewById(R.id.done);
        scan=findViewById(R.id.scan);
        actv =findViewById(R.id.multiAutoCompleteTextView1);

        actv= findViewById(R.id.multiAutoCompleteTextView1);

        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("Android");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("Java");
        GLOBAL.SUBJECT_CODE_ARRAYLIST.add("MAT11");


        ArrayAdapter adapter = new
                ArrayAdapter(this,android.R.layout.simple_list_item_1, GLOBAL.SUBJECT_CODE_ARRAYLIST);

        actv.setAdapter(adapter);
        actv.setAdapter(adapter);


        scan.setOnClickListener(view -> {

            Choosensub_code=actv.getText().toString();

            if(Choosensub_code.isEmpty()){
                actv.requestFocus();
                actv.setError("Please choose subject code");
                done.revertAnimation();
                return;
            }

            if(GLOBAL.SUBJECT_CODE_ARRAYLIST.contains(Choosensub_code)){

                scanqr();
            }else{

                actv.requestFocus();
                actv.setError("Please choose subject code");
                done.revertAnimation();

            }






        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                checkstudent();



            }
        });


    }

    public void checkstudent() {

        Call<SRegisterResponse> call= RetrofitClient
                .getInstance()
                .getApi()
                .checkstudent(STUDENT_ID,Choosensub_code);

        call.enqueue(new Callback<SRegisterResponse>() {
            @Override
            public void onResponse(Call<SRegisterResponse> call, Response<SRegisterResponse> response) {

                SRegisterResponse registerResponse=response.body();
                if(response.isSuccessful()){



                    if(response.body().getError().equals("400")){


                        if(url==null){

                            Toast.makeText(getApplicationContext(), "Scan QR", Toast.LENGTH_SHORT).show();

                        }else{

                            Intent intent=new Intent(SHomeActivity.this,Starttest.class);
                            intent.putExtra("US",url);
                            startActivity(intent);
                            finish();
                        }


                    }else{

                        Toast.makeText(getApplicationContext(), "You already given exam.", Toast.LENGTH_SHORT).show();
                    }



                }else{
                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<SRegisterResponse> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
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

            url=result.getContents();
            scan.setText("Click to start button below");


            // Toast.makeText(Scan.this,url,Toast.LENGTH_LONG).show();

        }else{

            Toast.makeText(SHomeActivity.this,"Please scan properly",LENGTH_LONG).show();

        }
        super.onActivityResult(requestCode, resultCode, data);
    }





}