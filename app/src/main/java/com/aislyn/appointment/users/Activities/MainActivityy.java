package com.aislyn.appointment.users.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;


import com.aislyn.appointment.users.ModelResponse.RegisterResponse;
import com.aislyn.appointment.R;
import com.aislyn.appointment.users.RetrofitClient;
import com.aislyn.appointment.databinding.ActivityyMainBinding;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivityy extends AppCompatActivity implements View.OnClickListener {


    ActivityyMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding=ActivityyMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding.loginlink.setOnClickListener(this);
        binding.btnregister.setOnClickListener(this);

        binding.back.setOnClickListener(view->{

            Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        });


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnregister:

                registerUser();

                break;
            case R.id.loginlink:

               switchOnLogin();
                break;
        }

    }

    private void registerUser() {

        String userName=binding.etname.getText().toString();
        String userEmail=binding.etemail.getText().toString();
        String userpassword=binding.etpassword.getText().toString();


        if(userName.isEmpty()){
            binding.etname.requestFocus();
            binding.etname.setError("Please Enter name");
            return;

        }

        if(userEmail.isEmpty()){
            binding.etemail.requestFocus();
            binding.etemail.setError("Please Enter email");
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
            binding.etemail.requestFocus();
            binding.etemail.setError("Please Enter correct email");
            return;
        }

        if(userpassword.isEmpty()){
            binding.etpassword.requestFocus();
            binding.etpassword.setError("Please Enter password");
            return;
        }
        if(userpassword.length()<8){
            binding.etpassword.requestFocus();
            binding.etpassword.setError("Please Enter password 8 digit password");
            return;
        }


        Call<RegisterResponse> call= RetrofitClient
                .getInstance()
                .getApi()
                .register(userName,userEmail,userpassword);

        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                RegisterResponse registerResponse=response.body();
                if(response.isSuccessful()){



                    Intent intent=new Intent(MainActivityy.this,LoginActivity.class);
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