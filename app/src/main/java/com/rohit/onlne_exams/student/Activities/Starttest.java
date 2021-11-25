package com.rohit.onlne_exams.student.Activities;

import static com.rohit.onlne_exams.student.Activities.SHomeActivity.Choosensub_code;
import static com.rohit.onlne_exams.student.Activities.SLoginActivity.student_name;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.rohit.onlne_exams.GLOBAL;
import com.rohit.onlne_exams.R;
import com.rohit.onlne_exams.network.RetrofitClient;
import com.rohit.onlne_exams.student.ModelResponse.QuestionResponse;

import java.util.ArrayList;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;

public class Starttest extends AppCompatActivity {


    QuestionResponse questionResponse;
    String Scannedurl;


    TextView tv;
    Button submitbutton, quitbutton;
    RadioGroup radio_g;
    RadioButton rb1,rb2,rb3,rb4;

    ArrayList<String> questions = new ArrayList<String>();
    ArrayList<String> answers = new ArrayList<String>();
    ArrayList<String> opt = new ArrayList<String>();

    int flag=0;
    public static int correct=0,wrong=0,TOTAL_QUESTION=0;
    public static String SUBJECT_CODE,SET_CODE;

    String YOUR_SET;


    TextView timeremaining;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starttest);



        timeremaining=findViewById(R.id.timeremaining);


        Bundle bn=getIntent().getExtras();
        assert bn != null;
        Scannedurl=bn.getString("US");

        Random r = new Random();
        String alphabet = GLOBAL.QUESTION_SET_CONSTANT;
        YOUR_SET = String.valueOf(alphabet.charAt(r.nextInt(alphabet.length())));


        fetch();



        new CountDownTimer(200000, 1000) {
            public void onTick(long millisUntilFinished) {
                timeremaining.setText("Seconds remaining: " + millisUntilFinished / 1000+" (20 min.)");
            }

            public void onFinish() {
                timeremaining.setText("finish!");
                Intent intent=new Intent(getApplicationContext(),ResultActivity.class);
                startActivity(intent);
            }
        }.start();




        if (student_name.trim().equals(""))
            setTitle("Hello");
        else
            setTitle("Hello " + student_name);


        submitbutton=(Button)findViewById(R.id.button3);
        quitbutton=(Button)findViewById(R.id.buttonquit);
        tv=(TextView) findViewById(R.id.tvque);

        radio_g=(RadioGroup)findViewById(R.id.answersgrp);
        rb1=(RadioButton)findViewById(R.id.radioButton);
        rb2=(RadioButton)findViewById(R.id.radioButton2);
        rb3=(RadioButton)findViewById(R.id.radioButton3);
        rb4=(RadioButton)findViewById(R.id.radioButton4);

        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int color = mBackgroundColor.getColor();
                //mLayout.setBackgroundColor(color);

                if(radio_g.getCheckedRadioButtonId()==-1)
                {
                    Toast.makeText(getApplicationContext(), "Please select one choice", Toast.LENGTH_SHORT).show();
                    return;
                }
                RadioButton uans = (RadioButton) findViewById(radio_g.getCheckedRadioButtonId());
                String ansText = uans.getText().toString();
//                Toast.makeText(getApplicationContext(), ansText, Toast.LENGTH_SHORT).show();
                if(ansText.equals(answers.get(flag))) {
                    correct++;
                    //Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
                }
                else {
                    wrong++;
                    //Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_SHORT).show();
                }

                flag++;


                if(flag<questions.size())
                {
                    tv.setText(questions.get(flag));
                    rb1.setText(opt.get(flag * 4));
                    rb2.setText(opt.get(flag * 4 + 1));
                    rb3.setText(opt.get(flag * 4 + 2));
                    rb4.setText(opt.get(flag * 4 + 3));
                }
                else
                {

                    Intent in = new Intent(getApplicationContext(),ResultActivity.class);
                    startActivity(in);
                }
                radio_g.clearCheck();
            }
        });

        quitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ResultActivity.class);
                startActivity(intent);
            }
        });
    }


    private void fetch() {

        callApi();


    }

    private void callApi() {

        Call<QuestionResponse> call= RetrofitClient.getInstance().getApi().squestions(Choosensub_code,YOUR_SET);

        call.enqueue(new Callback<QuestionResponse>() {
            @Override
            public void onResponse(Call<QuestionResponse> call, retrofit2.Response<QuestionResponse> response) {

                if(response.isSuccessful()){


                     questionResponse=response.body();


                    Log.e("Tag", questionResponse.getDataList().get(0).getQuestion());

                    SUBJECT_CODE=questionResponse.getDataList().get(0).getSub_code();
                    SET_CODE=questionResponse.getDataList().get(0).getSetcode();


                     for(int i=0;i<=questionResponse.getDataList().size()-1;i++){


                         questions.add(questionResponse.getDataList().get(i).getQuestion());
                         answers.add(questionResponse.getDataList().get(i).getAnswer());
                         TOTAL_QUESTION=questionResponse.getDataList().size();



                         opt.add(questionResponse.getDataList().get(i).getOptionA());
                         opt.add(questionResponse.getDataList().get(i).getOptionB());
                         opt.add(questionResponse.getDataList().get(i).getOptionC());
                         opt.add(questionResponse.getDataList().get(i).getOptionD());







                     }






                    tv.setText(questions.get(flag));
                    rb1.setText(opt.get(0));
                    rb2.setText(opt.get(1));
                    rb3.setText(opt.get(2));
                    rb4.setText(opt.get(3));














                }else{

                    Toast.makeText(getApplicationContext(), response.body().getError(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<QuestionResponse> call, Throwable t) {

                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}