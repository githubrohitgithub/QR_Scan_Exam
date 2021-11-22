package com.online.appointment.student.Activities;

import static com.online.appointment.student.Activities.SLoginActivity.STUDENT_ID;
import static com.online.appointment.student.Activities.Starttest.SET_CODE;
import static com.online.appointment.student.Activities.Starttest.SUBJECT_CODE;
import static com.online.appointment.student.Activities.Starttest.TOTAL_QUESTION;
import static com.online.appointment.student.Activities.Starttest.correct;
import static com.online.appointment.student.Activities.Starttest.wrong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.online.appointment.R;
import com.online.appointment.network.RetrofitClient;
import com.online.appointment.student.ModelResponse.SRegisterResponse;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultActivity extends AppCompatActivity {


    ProgressBar progressbar;
    TextView thankyou;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);



        progressbar=findViewById(R.id.progressbar);
        thankyou=findViewById(R.id.thankyou);




        result_update(SUBJECT_CODE,SET_CODE,STUDENT_ID,String.valueOf(correct),String.valueOf(wrong),String.valueOf(TOTAL_QUESTION),String.valueOf(correct+wrong));



    }

    private void result_update(String sub_code,String set_code,String student_id,String correct,String wrong,String total,String attemted) {

        Call<SRegisterResponse> call= RetrofitClient
                .getInstance()
                .getApi()
                .supdate_result(sub_code,set_code,student_id,correct,wrong,total,attemted);

        call.enqueue(new Callback<SRegisterResponse>() {
            @Override
            public void onResponse(Call<SRegisterResponse> call, Response<SRegisterResponse> response) {

                SRegisterResponse SRegisterResponse =response.body();
                if(response.isSuccessful()){


                    thankyou.setVisibility(View.VISIBLE);
                    progressbar.setVisibility(View.GONE);
                    Toast.makeText(ResultActivity.this, "Thank you..", Toast.LENGTH_SHORT).show();



                }else{
                    Toast.makeText(ResultActivity.this, SRegisterResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<SRegisterResponse> call, Throwable t) {


                Toast.makeText(ResultActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();


        finishAffinity();
    }
}