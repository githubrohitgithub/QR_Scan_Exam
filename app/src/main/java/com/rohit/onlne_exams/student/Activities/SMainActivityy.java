package com.rohit.onlne_exams.student.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;


import com.rohit.onlne_exams.databinding.SactivityyMainBinding;
import com.rohit.onlne_exams.network.RetrofitClient;
import com.rohit.onlne_exams.student.ModelResponse.SRegisterResponse;
import com.rohit.onlne_exams.R;



import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SMainActivityy extends AppCompatActivity implements View.OnClickListener {


    SactivityyMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding=SactivityyMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding.loginlink.setOnClickListener(this);
        binding.btnregister.setOnClickListener(this);

        binding.back.setOnClickListener(view->{

            Intent intent=new Intent(getApplicationContext(), SLoginActivity.class);
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

        String studentName=binding.etname.getText().toString();
        String studentEmail=binding.etemail.getText().toString();
        String studentpassword=binding.etpassword.getText().toString();
        String studentrno=binding.etrno.getText().toString();


        if(studentName.isEmpty()){
            binding.etname.requestFocus();
            binding.etname.setError("Please Enter name");
            return;

        }

        if(studentEmail.isEmpty()){
            binding.etemail.requestFocus();
            binding.etemail.setError("Please Enter email");
            return;
        }


        if(studentrno.isEmpty()){
            binding.etrno.requestFocus();
            binding.etrno.setError("Please Enter Register No.");
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(studentEmail).matches()){
            binding.etemail.requestFocus();
            binding.etemail.setError("Please Enter correct email");
            return;
        }

        if(studentpassword.isEmpty()){
            binding.etpassword.requestFocus();
            binding.etpassword.setError("Please Enter password");
            return;
        }
        if(studentpassword.length()<8){
            binding.etpassword.requestFocus();
            binding.etpassword.setError("Please Enter password 8 digit password");
            return;
        }


        Call<SRegisterResponse> call= RetrofitClient
                .getInstance()
                .getApi()
                .sregister(studentrno,studentName,studentEmail,studentpassword);

        call.enqueue(new Callback<SRegisterResponse>() {
            @Override
            public void onResponse(Call<SRegisterResponse> call, Response<SRegisterResponse> response) {

                SRegisterResponse registerResponse=response.body();
                if(response.isSuccessful()){



                    Intent intent=new Intent(SMainActivityy.this, SLoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();

                    Toast.makeText(SMainActivityy.this, registerResponse.getMessage(), Toast.LENGTH_SHORT).show();




                }else{
                    Toast.makeText(SMainActivityy.this, registerResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<SRegisterResponse> call, Throwable t) {

                Toast.makeText(SMainActivityy.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }

    private void switchOnLogin() {

        Intent i=new Intent(SMainActivityy.this, SLoginActivity.class);
        startActivity(i);
    }
}