package com.rohit.onlne_exams.teacher.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.rohit.onlne_exams.R;
import com.rohit.onlne_exams.network.RetrofitClient;
import com.rohit.onlne_exams.teacher.ModelResponse.ResultSub_Set;
import com.rohit.onlne_exams.teacher.ModelResponse.TRegisterResponse;

import java.util.ArrayList;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Question_set_change extends AppCompatActivity {





    ArrayList<String> arrayList = new ArrayList<>();


    TextView textView3;

    CircularProgressButton done;

    String Choosensub_code;


    String Choosenset_code;
    Spinner spinner;
    TextView setted;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_set_change);


        done=findViewById(R.id.done);
        textView3=findViewById(R.id.textView3);
        setted=findViewById(R.id.setted);

        setTitle("Set Question paper");


        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                done.startAnimation();
                change();


            }
        });



         spinner = (Spinner) findViewById(R.id.spinner);



        callApi();

    }

    private void change() {



        Call<TRegisterResponse> call= RetrofitClient
                .getInstance()
                .getApi()
                .setupdate(Choosensub_code,Choosenset_code);

        call.enqueue(new Callback<TRegisterResponse>() {
            @Override
            public void onResponse(Call<TRegisterResponse> call, Response<TRegisterResponse> response) {

                TRegisterResponse tRegisterResponse =response.body();
                if(response.isSuccessful()){


                    done.revertAnimation();
                    setted.setText(Choosenset_code+" of "+Choosensub_code+" has set");
                    setted.setVisibility(View.VISIBLE);




                }else{
                    Toast.makeText(Question_set_change.this, tRegisterResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<TRegisterResponse> call, Throwable t) {


                Toast.makeText(Question_set_change.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void callApi() {

        Call<ResultSub_Set> call= RetrofitClient.getInstance().getApi().subsetresult();

        call.enqueue(new Callback<ResultSub_Set>() {
            @Override
            public void onResponse(Call<ResultSub_Set> call, retrofit2.Response<ResultSub_Set> response) {

                if(response.isSuccessful()){


                    ResultSub_Set resultSub_set=response.body();



                    for(int i=0;i<=resultSub_set.getSub_sets().size()-1;i++){

                        arrayList.add(resultSub_set.getSub_sets().get(i).getSub_code()+" set "+resultSub_set.getSub_sets().get(i).getSetcode());
                    }


                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, arrayList);
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(arrayAdapter);
                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String tutorialsName = parent.getItemAtPosition(position).toString();
                            String[] arrOfStr = tutorialsName.split(" set ", 2);



                            Choosensub_code=arrOfStr[0];
                            Choosenset_code=arrOfStr[1];






                        }
                        @Override
                        public void onNothingSelected(AdapterView <?> parent) {
                        }
                    });









                }else{

                    Toast.makeText(getApplicationContext(), response.body().getError(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ResultSub_Set> call, Throwable t) {

                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }









}