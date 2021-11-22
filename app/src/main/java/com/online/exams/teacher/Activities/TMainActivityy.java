package com.online.exams.teacher.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.online.exams.R;
import com.online.exams.teacher.ModelResponse.TRegisterResponse;
import com.online.exams.network.RetrofitClient;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TMainActivityy extends AppCompatActivity {


    TextView back,loginlink;
    EditText etname,etemail,etpassword,etqual,etid;
    CircularProgressButton btnregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tactivityy_main);


        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        back=findViewById(R.id.back);
        loginlink=findViewById(R.id.loginlink);
        btnregister=findViewById(R.id.btnregister);
        etpassword=findViewById(R.id.etpassword);
        etemail=findViewById(R.id.etemail);
        etname=findViewById(R.id.etname);
        etqual=findViewById(R.id.etqual);
        etid=findViewById(R.id.etid);



        loginlink.setOnClickListener(view->{
            Intent intent=new Intent(getApplicationContext(), TLoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

        back.setOnClickListener(view->{

            Intent intent=new Intent(getApplicationContext(), TLoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        });

        btnregister.setOnClickListener(view -> {

            registerUser();
        });


    }



    private void registerUser() {

        btnregister.startAnimation();
        String teacherName=etname.getText().toString();
        String teacherEmail=etemail.getText().toString();
        String teacherPassword=etpassword.getText().toString();

        String teacheretqual=etqual.getText().toString();
        String teachereid=etid.getText().toString();




        if(teachereid.isEmpty()){
            etid.requestFocus();
            etid.setError("Please Enter ID No.");
            btnregister.revertAnimation();
            return;

        }

        if(teacheretqual.isEmpty()){
            etqual.requestFocus();
            etqual.setError("Please Enter qualification");
            btnregister.revertAnimation();
            return;

        }

        if(teacherName.isEmpty()){
            etname.requestFocus();
            etname.setError("Please Enter name");
            btnregister.revertAnimation();
            return;

        }

        if(teacherEmail.isEmpty()){
            etemail.requestFocus();
            etemail.setError("Please Enter email");
            btnregister.revertAnimation();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(teacherEmail).matches()){
            etemail.requestFocus();
            etemail.setError("Please Enter correct email");
            btnregister.revertAnimation();
            return;
        }

        if(teacherEmail.isEmpty()){
            etpassword.requestFocus();
            etpassword.setError("Please Enter password");
            btnregister.revertAnimation();
            return;
        }
        if(teacherPassword.length()<8){
            etpassword.requestFocus();
            etpassword.setError("Please Enter password 8 digit password");
            btnregister.revertAnimation();
            return;
        }


        Call<TRegisterResponse> call= RetrofitClient
                .getInstance()
                .getApi()
                .tregister(teacherName,teacheretqual,teachereid,teacherEmail,teacherPassword);

        call.enqueue(new Callback<TRegisterResponse>() {
            @Override
            public void onResponse(Call<TRegisterResponse> call, Response<TRegisterResponse> response) {

                TRegisterResponse TRegisterResponse =response.body();
                if(response.isSuccessful()){



                    Intent intent=new Intent(TMainActivityy.this, TLoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();

                    Toast.makeText(TMainActivityy.this, TRegisterResponse.getMessage(), Toast.LENGTH_SHORT).show();




                }else{
                    Toast.makeText(TMainActivityy.this, TRegisterResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<TRegisterResponse> call, Throwable t) {


                Toast.makeText(TMainActivityy.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }

    private void switchOnLogin() {

        Intent i=new Intent(TMainActivityy.this, TLoginActivity.class);
        startActivity(i);
    }
}