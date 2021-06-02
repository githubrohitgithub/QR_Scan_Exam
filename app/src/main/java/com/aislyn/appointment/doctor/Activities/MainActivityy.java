package com.aislyn.appointment.doctor.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.aislyn.appointment.R;
import com.aislyn.appointment.doctor.ModelResponse.RegisterResponse;
import com.aislyn.appointment.doctor.DoctorRetrofitClient;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivityy extends AppCompatActivity {


    TextView back,loginlink;
    EditText etname,etemail,etpassword,etqual,etspec,etimr;
    CircularProgressButton btnregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dractivityy_main);


        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        back=findViewById(R.id.back);
        loginlink=findViewById(R.id.loginlink);
        btnregister=findViewById(R.id.btnregister);
        etpassword=findViewById(R.id.etpassword);
        etemail=findViewById(R.id.etemail);
        etname=findViewById(R.id.etname);
        etqual=findViewById(R.id.etqual);
        etspec=findViewById(R.id.etspec);
        etimr=findViewById(R.id.etimr);


        loginlink.setOnClickListener(view->{
            Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

        back.setOnClickListener(view->{

            Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        });

        btnregister.setOnClickListener(view -> {

            registerUser();
        });


    }



    private void registerUser() {

        btnregister.startAnimation();
        String userName=etname.getText().toString();
        String userEmail=etemail.getText().toString();
        String userpassword=etpassword.getText().toString();

        String dretqual=etqual.getText().toString();
        String dretspec=etspec.getText().toString();
        String dretimr=etimr.getText().toString();




        if(dretimr.isEmpty()){
            etimr.requestFocus();
            etimr.setError("Please Enter IMR No.");
            btnregister.revertAnimation();
            return;

        }
        if(dretspec.isEmpty()){
            etspec.requestFocus();
            etspec.setError("Please Enter specialization");
            btnregister.revertAnimation();
            return;

        }

        if(dretqual.isEmpty()){
            etqual.requestFocus();
            etqual.setError("Please Enter qualification");
            btnregister.revertAnimation();
            return;

        }

        if(userName.isEmpty()){
            etname.requestFocus();
            etname.setError("Please Enter name");
            btnregister.revertAnimation();
            return;

        }

        if(userEmail.isEmpty()){
            etemail.requestFocus();
            etemail.setError("Please Enter email");
            btnregister.revertAnimation();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
            etemail.requestFocus();
            etemail.setError("Please Enter correct email");
            btnregister.revertAnimation();
            return;
        }

        if(userpassword.isEmpty()){
            etpassword.requestFocus();
            etpassword.setError("Please Enter password");
            btnregister.revertAnimation();
            return;
        }
        if(userpassword.length()<8){
            etpassword.requestFocus();
            etpassword.setError("Please Enter password 8 digit password");
            btnregister.revertAnimation();
            return;
        }


        Call<RegisterResponse> call= DoctorRetrofitClient
                .getInstance()
                .getApi()
                .register(userName,dretqual,dretspec,dretimr,userEmail,userpassword);

        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                RegisterResponse registerResponse=response.body();
                if(response.isSuccessful()){



                    Intent intent=new Intent(MainActivityy.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();

                    Toast.makeText(MainActivityy.this, registerResponse.getMessage(), Toast.LENGTH_SHORT).show();




                }else{
                    Toast.makeText(MainActivityy.this, registerResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {

                Toast.makeText(MainActivityy.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }

    private void switchOnLogin() {

        Intent i=new Intent(MainActivityy.this, LoginActivity.class);
        startActivity(i);
    }
}