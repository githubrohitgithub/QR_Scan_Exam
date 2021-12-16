package com.rohit.onlne_exams.adapers;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.rohit.onlne_exams.R;
import com.rohit.onlne_exams.teacher.Activities.Pdf_Creater;


import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    ArrayList<ResultData> listdata;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView id_;
        public TextView subcode_;
        public TextView setcode_;
        public TextView sregno_;
        public TextView correct_;
        public TextView wrong_;
        public TextView total_;
        public TextView attempted_;
        public TextView result_;


        public MyViewHolder(View itemView) {
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


        }
    }

    public RecyclerViewAdapter(ArrayList<ResultData> listdata) {
        this.listdata = listdata;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.id_.setText("ID "+listdata.get(position).getId());
        holder.subcode_.setText("Subject code "+listdata.get(position).getSub_code());
        holder.setcode_.setText("Subject code "+listdata.get(position).getSet_code());
        holder.sregno_.setText("Reg no. "+listdata.get(position).getSreg_no());
        holder.correct_.setText("Correct "+listdata.get(position).getCorrect());
        holder.wrong_.setText("Wrong "+listdata.get(position).getWrong());
        holder.total_.setText("Total "+listdata.get(position).getTotal());
        holder.attempted_.setText("Attempted "+listdata.get(position).getAttempted());
        holder.result_.setText("Result "+listdata.get(position).getResult());



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(view.getContext(), Pdf_Creater.class);
                intent.putExtra("sub_code",listdata.get(position).getSub_code());
                intent.putExtra("set_code",listdata.get(position).getSet_code());
                intent.putExtra("regno",listdata.get(position).getSreg_no());
                intent.putExtra("correct",listdata.get(position).getCorrect());
                intent.putExtra("wrong",listdata.get(position).getWrong());
                intent.putExtra("total",listdata.get(position).getTotal());
                intent.putExtra("attempted",listdata.get(position).getAttempted());
                intent.putExtra("result",listdata.get(position).getResult());
                view.getContext().startActivity(intent);
            }
        });


    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }


    public void removeItem(int position) {
        listdata.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(ResultData item, int position) {
        listdata.add(position, item);
        notifyItemInserted(position);
    }

    public ArrayList<ResultData> getData() {
        return listdata;
    }
}


