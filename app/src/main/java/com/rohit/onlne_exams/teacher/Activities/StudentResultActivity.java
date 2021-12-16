package com.rohit.onlne_exams.teacher.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.rohit.onlne_exams.GLOBAL;
import com.rohit.onlne_exams.R;
import com.rohit.onlne_exams.adapers.RecyclerViewAdapter;
import com.rohit.onlne_exams.adapers.ResultData;
import com.rohit.onlne_exams.adapers.SwipeToDeleteCallback;
import com.rohit.onlne_exams.network.RetrofitClient;
import com.rohit.onlne_exams.teacher.ModelResponse.Result;
import com.rohit.onlne_exams.teacher.ModelResponse.ResultResponse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class StudentResultActivity extends AppCompatActivity {




    List<Result> locationList;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    Button pdf;
    CoordinatorLayout coordinatorLayout;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentresult);


        recyclerView = findViewById(R.id.recyclerView);
        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        pdf = findViewById(R.id.pdf);
        adapter = new RecyclerViewAdapter(GLOBAL.resultData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        enableSwipeToDeleteAndUndo();


        pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent intent = new Intent(view.getContext(), Pdf_CreaterAll.class);
                startActivity(intent);


            }
        });


        setTitle("Student Result");

        callApi();
    }


    private void enableSwipeToDeleteAndUndo() {
        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(this) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


                final int position = viewHolder.getAdapterPosition();
                final ResultData item = adapter.getData().get(position);

                adapter.removeItem(position);
                adapter.notifyDataSetChanged();


                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "Item was removed from the list.", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

//                        adapter.restoreItem(item, position);
                        recyclerView.scrollToPosition(position);
                    }
                });

                snackbar.setActionTextColor(Color.YELLOW);
                snackbar.show();

            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(recyclerView);
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



                        GLOBAL.resultData.add(new ResultData(data.getId(),data.getSub_code(),data.getSet_code(),data.getSreg_no(),data.getCorrect(),data.getWrong(),data.getTotal(),data.getAttempted(),data.getResult()));

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