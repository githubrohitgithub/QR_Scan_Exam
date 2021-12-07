package com.rohit.onlne_exams.adapers;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.rohit.onlne_exams.R;
import com.rohit.onlne_exams.teacher.Activities.Pdf_Creater;
import com.rohit.onlne_exams.teacher.Activities.StudentResultActivity;

import java.util.ArrayList;


public class ResultDataAdapter extends RecyclerView.Adapter<ResultDataAdapter.ViewHolder>{

    ArrayList<ResultData> listdata;
    StudentResultActivity mapActivity;

    // RecyclerView recyclerView;
    public ResultDataAdapter(StudentResultActivity mapActivity, ArrayList<ResultData> listdata) {
        this.mapActivity = mapActivity;
        this.listdata = listdata;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final ResultData resultData = listdata.get(position);

        holder.id_.setText("ID "+resultData.getId());
        holder.subcode_.setText("Subject code "+resultData.getSub_code());
        holder.setcode_.setText("Subject code "+resultData.getSet_code());
        holder.sregno_.setText("Reg no. "+resultData.getSreg_no());
        holder.correct_.setText("Correct "+resultData.getCorrect());
        holder.wrong_.setText("Wrong "+resultData.getWrong());
        holder.total_.setText("Total "+resultData.getTotal());
        holder.attempted_.setText("Attempted "+resultData.getAttempted());
        holder.result_.setText("Result "+resultData.getResult());


        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent=new Intent(mapActivity, Pdf_Creater.class);
                intent.putExtra("sub_code",resultData.getSub_code());
                intent.putExtra("set_code",resultData.getSet_code());
                intent.putExtra("regno",resultData.getSreg_no());
                intent.putExtra("correct",resultData.getCorrect());
                intent.putExtra("wrong",resultData.getWrong());
                intent.putExtra("total",resultData.getTotal());
                intent.putExtra("attempted",resultData.getAttempted());
                intent.putExtra("result",resultData.getResult());
                mapActivity.startActivity(intent);


            }
        });
    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView id_;
        public TextView subcode_;
        public TextView setcode_;
        public TextView sregno_;
        public TextView correct_;
        public TextView wrong_;
        public TextView total_;
        public TextView attempted_;
        public TextView result_;
        public LinearLayout constraintLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            this.id_ = itemView.findViewById(R.id.id_);
            this.subcode_ = itemView.findViewById(R.id.subcode_);
            this.setcode_ = itemView.findViewById(R.id.setcode_);
            this.sregno_ = itemView.findViewById(R.id.sregno_);
            this.correct_ = itemView.findViewById(R.id.correct_);
            this.wrong_ = itemView.findViewById(R.id.wrong_);
            this.total_ = itemView.findViewById(R.id.total_);
            this.attempted_ = itemView.findViewById(R.id.attempted_);
            this.result_ = itemView.findViewById(R.id.result_);
            constraintLayout = itemView.findViewById(R.id.relativeLayout);
        }
    }
}