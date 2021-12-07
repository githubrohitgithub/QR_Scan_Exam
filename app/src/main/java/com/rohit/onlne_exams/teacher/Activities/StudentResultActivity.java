package com.rohit.onlne_exams.teacher.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.rohit.onlne_exams.R;
import com.rohit.onlne_exams.adapers.ResultDataAdapter;
import com.rohit.onlne_exams.adapers.ResultData;
import com.rohit.onlne_exams.network.RetrofitClient;
import com.rohit.onlne_exams.teacher.ModelResponse.Result;
import com.rohit.onlne_exams.teacher.ModelResponse.ResultResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class StudentResultActivity extends AppCompatActivity {



   public static ArrayList<ResultData> resultData =new ArrayList<ResultData>();
    List<Result> locationList;
    RecyclerView recyclerView;
    ResultDataAdapter adapter;
    Button pdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentresult);


        recyclerView = findViewById(R.id.recyclerview);
        pdf = findViewById(R.id.pdf);
        adapter = new ResultDataAdapter(StudentResultActivity.this, resultData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Pdf_Creater.class));
            }
        });


        setTitle("Student Result");

        callApi();
    }


    private void callApi() {

        Call<ResultResponse> call= RetrofitClient.getInstance().getApi().tresult();

        call.enqueue(new Callback<ResultResponse>() {
            @Override
            public void onResponse(Call<ResultResponse> call, retrofit2.Response<ResultResponse> response) {

                if(response.isSuccessful()){


                    locationList=response.body().getResultList();


                    pdf.setVisibility(View.VISIBLE);


                    for(Result data:locationList){



                        resultData.add(new ResultData(data.getId(),data.getSub_code(),data.getSet_code(),data.getSreg_no(),data.getCorrect(),data.getWrong(),data.getTotal(),data.getAttempted(),data.getResult()));

                    }

                    recyclerView.setAdapter(adapter);





                }else{

                    Toast.makeText(getApplicationContext(), "No data found", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ResultResponse> call, Throwable t) {

                Toast.makeText(getApplicationContext(), "No data found", Toast.LENGTH_SHORT).show();
            }
        });

    }
}