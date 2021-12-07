package com.rohit.onlne_exams.teacher.Activities;

import static com.rohit.onlne_exams.student.Activities.SLoginActivity.STUDENT_ID;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.rohit.onlne_exams.R;
import com.rohit.onlne_exams.network.RetrofitClient;
import com.rohit.onlne_exams.student.Activities.SHomeActivity;
import com.rohit.onlne_exams.student.Activities.Starttest;
import com.rohit.onlne_exams.student.ModelResponse.SRegisterResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class THomeActivity extends AppCompatActivity  {


    EditText sub_code;
    Button add,previus_result;
    public static String TEACHER_ID,SUB_CODE,SET_CODE;
    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tactivity_home);

        context=this;

        TEACHER_ID=getIntent().getStringExtra("teacher_id");


        sub_code=findViewById(R.id.sub_code);
        radioSexGroup=(RadioGroup)findViewById(R.id.radioGroup);
        add=findViewById(R.id.add);
        previus_result=findViewById(R.id.previus_result);

        Dexter.withContext(context)
                .withPermissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                ).withListener(new MultiplePermissionsListener() {
            @Override public void onPermissionsChecked(MultiplePermissionsReport report) {



                if(!report.areAllPermissionsGranted()) {


                    Toast.makeText(context, "Not all permissions granted.", Toast.LENGTH_SHORT).show();

                }




            }
            @Override public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {

                token.continuePermissionRequest();


            }
        }).check();



        previus_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getApplicationContext(),StudentResultActivity.class);
                startActivity(intent);

            }
        });

        add.setOnClickListener(view -> {

            String code=sub_code.getText().toString();
            int selectedId=radioSexGroup.getCheckedRadioButtonId();
            radioSexButton= findViewById(selectedId);

            if(code.isEmpty()){
                sub_code.requestFocus();
                sub_code.setError("Please Enter Subject Code");
                return;
            }



            SUB_CODE=code;
            SET_CODE=radioSexButton.getText().toString();



            Call<SRegisterResponse> call= RetrofitClient
                    .getInstance()
                    .getApi()
                    .checksubcode(SUB_CODE,SET_CODE);

            call.enqueue(new Callback<SRegisterResponse>() {
                @Override
                public void onResponse(Call<SRegisterResponse> call, Response<SRegisterResponse> response) {


                    if(response.isSuccessful()){



                        if(response.body().getError().equals("200")){


                            Intent intent=new Intent(getApplicationContext(),QuestionAddMainActivity.class);
                            startActivity(intent);
                            finish();


                        }else{

                            Toast.makeText(getApplicationContext(), "Already exist.", Toast.LENGTH_SHORT).show();
                        }



                    }else{
                        Toast.makeText(getApplicationContext(), "Already exist.", Toast.LENGTH_SHORT).show();
                    }


                }

                @Override
                public void onFailure(Call<SRegisterResponse> call, Throwable t) {

                    Toast.makeText(getApplicationContext(), "Already exist.", Toast.LENGTH_SHORT).show();
                }
            });









        });




    }











}