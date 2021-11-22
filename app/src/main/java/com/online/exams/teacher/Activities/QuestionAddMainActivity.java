package com.online.exams.teacher.Activities;

import static com.online.exams.teacher.Activities.THomeActivity.SET_CODE;
import static com.online.exams.teacher.Activities.THomeActivity.SUB_CODE;
import static com.online.exams.teacher.Activities.THomeActivity.TEACHER_ID;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.online.exams.R;
import com.online.exams.network.RetrofitClient;
import com.online.exams.teacher.ModelResponse.TRegisterResponse;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionAddMainActivity extends AppCompatActivity {


    EditText question,optionA,optionB,optionC,optionD,answer;
    CircularProgressButton add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_add_main);

        question=findViewById(R.id.question);
        optionA=findViewById(R.id.optionA);
        optionB=findViewById(R.id.optionB);
        optionC=findViewById(R.id.optionC);
        optionD=findViewById(R.id.optionD);
        answer=findViewById(R.id.answer);
        add=findViewById(R.id.add);


        add.setOnClickListener(view -> {

            add.startAnimation();

            String q=question.getText().toString();
            String a=optionA.getText().toString();
            String b=optionB.getText().toString();
            String c=optionC.getText().toString();
            String d=optionD.getText().toString();
            String answerr=answer.getText().toString();




            if(q.isEmpty()){
                question.requestFocus();
                question.setError("Please Enter question");
                add.revertAnimation();
                return;
            }

            if(a.isEmpty()){
                optionA.requestFocus();
                optionA.setError("Please Enter option A");
                add.revertAnimation();
                return;
            }

            if(b.isEmpty()){
                optionB.requestFocus();
                optionB.setError("Please Enter option B");
                add.revertAnimation();
                return;
            }

            if(c.isEmpty()){
                optionC.requestFocus();
                optionC.setError("Please Enter option C");
                add.revertAnimation();
                return;
            }

            if(d.isEmpty()){
                optionD.requestFocus();
                optionD.setError("Please Enter option D");
                add.revertAnimation();
                return;
            }



            add_question(q,a,b,c,d,answerr);



        });









    }

    private void add_question(String q,String a,String b,String c,String d,String answerr) {

        Call<TRegisterResponse> call= RetrofitClient.getInstance()
                .getApi()
                .questionadd(SUB_CODE,TEACHER_ID,SET_CODE,q,a,b,c,d,answerr);

        call.enqueue(new Callback<TRegisterResponse>() {
            @Override
            public void onResponse(Call<TRegisterResponse> call, Response<TRegisterResponse> response) {

                TRegisterResponse tRegisterResponse=response.body();


                if(response.isSuccessful()){


                    if(tRegisterResponse.getError().equals("000")){


                        question.setText("");
                        optionA.setText("");
                        optionB.setText("");
                        optionC.setText("");
                        optionD.setText("");
                        answer.setText("");
                        add.revertAnimation();

                    }
                    else if(tRegisterResponse.getError().equals("001")){
                        add.revertAnimation();
                        Toast.makeText(getApplicationContext(), tRegisterResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    } else if(tRegisterResponse.getError().equals("002")){
                        add.revertAnimation();
                        Toast.makeText(getApplicationContext(), tRegisterResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }else if(tRegisterResponse.getError().equals("007")){
                        add.revertAnimation();
                        Toast.makeText(getApplicationContext(), tRegisterResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }


                }
            }

            @Override
            public void onFailure(Call<TRegisterResponse> call, Throwable t) {

                add.revertAnimation();
                Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();

            }
        });
    }


}