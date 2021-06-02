package com.aislyn.appointment.users;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aislyn.appointment.PaymentActivity;
import com.aislyn.appointment.R;
import com.aislyn.appointment.doctor.ModelResponse.Doctor;


import java.util.List;

public class DoctorsAdapter extends RecyclerView.Adapter<DoctorsAdapter.Viewholder> {


    Context context;
    List<Doctor> doctorsList;
    SharedPrefmanager sharedPrefmanager;

    public DoctorsAdapter(Context context, List<Doctor> userList) {
        this.context = context;
        this.doctorsList = userList;

    }

    @NonNull
    @Override
    public DoctorsAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.doctors_item,parent,false);
        sharedPrefmanager=new SharedPrefmanager(context);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorsAdapter.Viewholder holder, int position) {

        holder.doctor_name.setText("Doctor Name:- "+ doctorsList.get(position).getDrname());
        holder.doctor_qualification.setText("Qualification :- "+ doctorsList.get(position).getQualification());
        holder.doctor_specialist.setText("Specialist :- "+ doctorsList.get(position).getSpecialist());
        holder.doctor_imrno.setText("IMR NO. :- "+ doctorsList.get(position).getIMR_NO());
        holder.doctor_email.setText("Email :- "+ doctorsList.get(position).getEmail());
       holder.book.setText("Appointment Fee :- ₹"+ doctorsList.get(position).getFee());

        holder.book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(context, PaymentActivity.class);
                i.putExtra("fee", doctorsList.get(position).getFee());
                i.putExtra("doctor_name", doctorsList.get(position).getDrname());
                i.putExtra("doctor_id", String.valueOf(doctorsList.get(position).getId()));

                i.putExtra("patient_id", String.valueOf(sharedPrefmanager.getuser().getId()));
                i.putExtra("patient_name", sharedPrefmanager.getuser().getUsername());
                i.putExtra("patient_phone", sharedPrefmanager.getuser().getPhone());
                context.startActivity(i);



            }
        });

    }

    @Override
    public int getItemCount() {
        return doctorsList.size();
    }


    public class Viewholder extends RecyclerView.ViewHolder  {


        TextView doctor_name,doctor_qualification,doctor_specialist,doctor_imrno,doctor_email;

        Button book;

        public Viewholder(@NonNull View itemView) {
            super(itemView);


            doctor_name=itemView.findViewById(R.id.doctor_name);
            doctor_qualification=itemView.findViewById(R.id.doctor_qualification);
            doctor_specialist=itemView.findViewById(R.id.doctor_specialist);
            doctor_imrno=itemView.findViewById(R.id.doctor_imrno);
            doctor_email=itemView.findViewById(R.id.doctor_email);
            book=itemView.findViewById(R.id.book_apointment);




        }


    }


}
