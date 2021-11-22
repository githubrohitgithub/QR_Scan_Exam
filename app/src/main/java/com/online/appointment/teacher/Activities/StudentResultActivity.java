package com.online.appointment.teacher.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.online.appointment.R;
import com.online.appointment.network.RetrofitClient;
import com.online.appointment.teacher.ModelResponse.ResultResponse;

import retrofit2.Call;
import retrofit2.Callback;

public class StudentResultActivity extends AppCompatActivity {


    ResultResponse resultResponse;

    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentresult);


        result=findViewById(R.id.result);

        setTitle("Student Result");

        callApi();
    }


    private void callApi() {

        Call<ResultResponse> call= RetrofitClient.getInstance().getApi().tresult();

        call.enqueue(new Callback<ResultResponse>() {
            @Override
            public void onResponse(Call<ResultResponse> call, retrofit2.Response<ResultResponse> response) {

                if(response.isSuccessful()){


                    resultResponse=response.body();


                    result.setText("");

                    for(int i=0;i<=resultResponse.getResultList().size()-1;i++){


                        result.append("ID "+resultResponse.getResultList().get(i).getId()+"\n"+
                                "SUBJECT CODE  "+resultResponse.getResultList().get(i).getSub_code()+"\n"+
                                "SET CODE "+resultResponse.getResultList().get(i).getSet_code()+"\n"+
                                "REG NO. "+resultResponse.getResultList().get(i).getSreg_no()+"\n"+
                                "CORRECT "+resultResponse.getResultList().get(i).getCorrect()+"\n"+
                                "WRONG "+resultResponse.getResultList().get(i).getWrong()+"\n"+
                                "ATTEMPTED "+resultResponse.getResultList().get(i).getAttempted()+"\n"+
                                "TOTAL "+resultResponse.getResultList().get(i).getTotal()+"\n"+
                                "RESULT "+resultResponse.getResultList().get(i).getResult()+"\n\n");



                    }





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