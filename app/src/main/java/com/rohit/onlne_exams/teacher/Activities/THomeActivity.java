package com.rohit.onlne_exams.teacher.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.rohit.onlne_exams.R;

public class THomeActivity extends AppCompatActivity  {


    EditText sub_code;
    Button add,previus_result;
    public static String TEACHER_ID,SUB_CODE,SET_CODE;
    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tactivity_home);


        TEACHER_ID=getIntent().getStringExtra("teacher_id");


        sub_code=findViewById(R.id.sub_code);
        radioSexGroup=(RadioGroup)findViewById(R.id.radioGroup);
        add=findViewById(R.id.add);
        previus_result=findViewById(R.id.previus_result);



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



            Intent intent=new Intent(this,QuestionAddMainActivity.class);
            startActivity(intent);
            finish();







        });




    }











}