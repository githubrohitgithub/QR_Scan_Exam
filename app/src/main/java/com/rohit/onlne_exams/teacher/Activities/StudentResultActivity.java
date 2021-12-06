package com.rohit.onlne_exams.teacher.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
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



    ArrayList<ResultData> resultData =new ArrayList<ResultData>();
    List<Result> locationList;
    RecyclerView recyclerView;
    ResultDataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentresult);


        recyclerView = findViewById(R.id.recyclerview);
        adapter = new ResultDataAdapter(StudentResultActivity.this, resultData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



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

                    Toast.makeText(getApplicationContext(), locationList.get(0).getResult(), Toast.LENGTH_SHORT).show();


                    for(Result data:locationList){



                        resultData.add(new ResultData(data.getId(),data.getSub_code(),data.getSet_code(),data.getSreg_no(),data.getCorrect(),data.getWrong(),data.getTotal(),data.getAttempted(),data.getResult()));

                    }

                    recyclerView.setAdapter(adapter);





                }else{

                    Toast.makeText(getApplicationContext(), response.body().getError(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ResultResponse> call, Throwable t) {

                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}