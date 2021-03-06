package com.rohit.onlne_exams.student.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rohit.onlne_exams.network.RetrofitClient;
import com.rohit.onlne_exams.student.ModelResponse.SLoginResponse;
import com.rohit.onlne_exams.R;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SLoginActivity extends AppCompatActivity  {

   EditText useremail,userpassword;
   CircularProgressButton userlogin;
   TextView gotoregalready,back;
   ImageView plusbutton;

   public static String student_name;
   public static String STUDENT_ID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sactivity_login);


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


       Call<SLoginResponse> call= RetrofitClient.getInstance()
               .getApi()
               .slogin(userEmail,userpass);

        call.enqueue(new Callback<SLoginResponse>() {
            @Override
            public void onResponse(Call<SLoginResponse> call, Response<SLoginResponse> response) {

                SLoginResponse loginResponse=response.body();


                if(response.isSuccessful()){

                    if(loginResponse.getError().equals("200")){



                        student_name=loginResponse.getUser().getName();
                        STUDENT_ID=loginResponse.getUser().getRegno();

                        Intent intent=new Intent(SLoginActivity.this, SHomeActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        userlogin.revertAnimation();
                        Toast.makeText(SLoginActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    else if(loginResponse.getError().equals("400")){
                        userlogin.revertAnimation();
                        Toast.makeText(SLoginActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }


                }
            }

            @Override
            public void onFailure(Call<SLoginResponse> call, Throwable t) {

                userlogin.revertAnimation();
                Toast.makeText(SLoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void switchOnRegister() {
        startActivity(new Intent(SLoginActivity.this, SMainActivityy.class));
    }



}