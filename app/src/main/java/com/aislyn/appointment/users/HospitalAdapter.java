package com.aislyn.appointment.users;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aislyn.appointment.R;
import com.aislyn.appointment.users.ModelResponse.Hospital;


import java.util.List;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.Viewholder> {


    Context context;
    List<Hospital> hospitalList;


    public HospitalAdapter(Context context, List<Hospital> hospitalList) {
        this.context = context;
        this.hospitalList = hospitalList;

    }

    @NonNull
    @Override
    public HospitalAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.hospital_list,parent,false);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HospitalAdapter.Viewholder holder, int position) {

        holder.id.setText("Hospital id:- "+ hospitalList.get(position).getId());
        holder.name.setText("Hospital Name :- "+ hospitalList.get(position).getName());
        holder.address.setText("Hospital Address :- "+ hospitalList.get(position).getAddress());
        holder.phone.setText("Hospital Phone. :- "+ hospitalList.get(position).getPhone());



    }

    @Override
    public int getItemCount() {
        return hospitalList.size();
    }


    public class Viewholder extends RecyclerView.ViewHolder  {


        TextView id,name,address,phone;


        public Viewholder(@NonNull View itemView) {
            super(itemView);


            id=itemView.findViewById(R.id.id);
            name=itemView.findViewById(R.id.name);
            address=itemView.findViewById(R.id.address);
            phone=itemView.findViewById(R.id.phone);





        }


    }


}
