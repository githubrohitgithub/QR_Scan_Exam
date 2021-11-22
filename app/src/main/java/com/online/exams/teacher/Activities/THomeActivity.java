package com.online.exams.teacher.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


import com.online.exams.R;

public class THomeActivity extends AppCompatActivity  {


    EditText sub_code,set_code;
    Button add,previus_result,setchange;
   public static String TEACHER_ID,SUB_CODE,SET_CODE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tactivity_home);


        TEACHER_ID=getIntent().getStringExtra("teacher_id");


        sub_code=findViewById(R.id.sub_code);
        set_code=findViewById(R.id.set_code);
        add=findViewById(R.id.add);
        previus_result=findViewById(R.id.previus_result);
        setchange=findViewById(R.id.setchange);


        setchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getApplicationContext(),Question_set_change.class);
                startActivity(intent);

            }
        });

        previus_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getApplicationContext(),StudentResultActivity.class);
                startActivity(intent);

            }
        });

        add.setOnClickListener(view -> {

            String code=sub_code.getText().toString();
            String setcode=set_code.getText().toString();

            if(code.isEmpty()){
                sub_code.requestFocus();
                sub_code.setError("Please Enter Subject Code");
                return;
            }

            if(setcode.isEmpty()){
                set_code.requestFocus();
                set_code.setError("Please Enter Set Code");
                return;
            }

            SUB_CODE=code;
            SET_CODE=setcode;


            Intent intent=new Intent(this,QuestionAddMainActivity.class);
            startActivity(intent);
            finish();







        });




    }











}