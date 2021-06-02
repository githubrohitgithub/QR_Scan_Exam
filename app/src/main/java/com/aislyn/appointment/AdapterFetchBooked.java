package com.aislyn.appointment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AdapterFetchBooked extends ArrayAdapter<FetchAllBooked> {

    Context context;
    List<FetchAllBooked> fetchAllBookeds;

    public AdapterFetchBooked(@NonNull Context context, List<FetchAllBooked> arrayListChits) {
        super(context, R.layout.booked_layout,arrayListChits);

        this.context=context;
        this.fetchAllBookeds =arrayListChits;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.booked_layout,null,true);

        TextView appointment_id=view.findViewById(R.id.appointment_id);
        TextView patient_name=view.findViewById(R.id.patient_name);
        TextView patient_id=view.findViewById(R.id.patient_id);
        TextView doctor_name=view.findViewById(R.id.doctor_name);
        TextView doctor_id=view.findViewById(R.id.doctor_id);
        ImageView image=view.findViewById(R.id.image);

        appointment_id.setText("Appointment id :- "+fetchAllBookeds.get(position).getAppointment_id());
        patient_name.setText("Patient Name :- "+fetchAllBookeds.get(position).getPatient_name());
        patient_id.setText("Patient id :- "+fetchAllBookeds.get(position).getPatient_id());
        doctor_name.setText("Doctor name :- "+fetchAllBookeds.get(position).getDoctor_name());
        doctor_id.setText("Doctor id :- "+fetchAllBookeds.get(position).getDoctor_id());


        if(fetchAllBookeds.get(position).getExpired().equals("1")){
            image.setImageResource(R.drawable.ic_yes);
        }




        return view;
    }
}

