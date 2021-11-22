package com.rohit.onlne_exams.teacher.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.rohit.onlne_exams.R;
import com.rohit.onlne_exams.teacher.ModelResponse.TLoginResponse;
import com.rohit.onlne_exams.network.RetrofitClient;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TLoginActivity extends AppCompatActivity  {

   EditText useremail,userpassword;
   CircularProgressButton userlogin;
   TextView gotoregalready,back;
   ImageView plusbutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tactivity_login);


        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        useremail=findViewById(R.id.useremail);
        back=findViewById(R.id.back);
        userpassword=findViewById(R.id.userpassword);
        userlogin=findViewById(R.id.userlogin);
        gotoregalready=findViewById(R.id.gotoregalready);
        plusbutton=findViewById(R.id.plusbutton);



        userlogin.setOnClickListener(view -> {
            userLogin();
        });


        plusbutton.setOnClickListener(view -> {
            switchOnRegister();
        });

        gotoregalready.setOnClickListener(view -> {
            switchOnRegister();
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               finishAffinity();
            }
        });



    }


    private void userLogin() {

        userlogin.startAnimation();
        String userEmail=useremail.getText().toString();
        String userpass=userpassword.getText().toString();


        if(userEmail.isEmpty()){
            useremail.requestFocus();
            useremail.setError("Please Enter email");
            userlogin.revertAnimation();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
            useremail.requestFocus();
            useremail.setError("Please Enter correct email");
            userlogin.revertAnimation();
            return;
        }

        if(userpass.isEmpty()){
            userpassword.requestFocus();
            userpassword.setError("Please Enter password");
            userlogin.revertAnimation();
            return;
        }
        if(userpassword.length()<8){
            userpassword.requestFocus();
            userpassword.setError("Please Enter password 8 digit password");
            userlogin.revertAnimation();
            return;
        }


       Call<TLoginResponse> call= RetrofitClient.getInstance()
               .getApi()
               .tlogin(userEmail,userpass);

        call.enqueue(new Callback<TLoginResponse>() {
            @Override
            public void onResponse(Call<TLoginResponse> call, Response<TLoginResponse> response) {

                TLoginResponse loginResponse=response.body();


                if(response.isSuccessful()){


                    if(loginResponse.getError().equals("200")){

                        Toast.makeText(TLoginActivity.this, "Hello "+response.body().getTeacher().getName(), Toast.LENGTH_SHORT).show();



                        Intent intent=new Intent(TLoginActivity.this, THomeActivity.class);
                        intent.putExtra("teacher_id",response.body().getTeacher().getTeacher_id());
                        startActivity(intent);
                        userlogin.revertAnimation();
                        Toast.makeText(TLoginActivity.this, "Hello "+response.body().getTeacher().getName(), Toast.LENGTH_SHORT).show();

                    }
                    else if(loginResponse.getError().equals("400")){
                        userlogin.revertAnimation();
                        Toast.makeText(getApplicationContext(), loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }


                }
            }

            @Override
            public void onFailure(Call<TLoginResponse> call, Throwable t) {

                userlogin.revertAnimation();
                Toast.makeText(TLoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void switchOnRegister() {
        startActivity(new Intent(TLoginActivity.this, TMainActivityy.class));
    }



}