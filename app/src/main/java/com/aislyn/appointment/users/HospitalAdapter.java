package com.aislyn.appointment.users;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aislyn.appointment.Mysingleton;
import com.aislyn.appointment.R;
import com.aislyn.appointment.doctor.Newfragments.AddHospitalsFragment;
import com.aislyn.appointment.doctor.SharedPrefmanager;
import com.aislyn.appointment.users.ModelResponse.Hospital;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;


import java.util.List;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.Viewholder> {


    Context context;
    List<Hospital> hospitalList;
    SharedPrefmanager drSharedPrefmanager;
    SharedPrefmanager sharedPrefmanager;


    public HospitalAdapter(Context context, List<Hospital> hospitalList) {
        this.context = context;
        this.hospitalList = hospitalList;

        drSharedPrefmanager=new SharedPrefmanager(context);
        sharedPrefmanager=new SharedPrefmanager(context);

    }

    @NonNull
    @Override
    public HospitalAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.hospital_list,parent,false);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HospitalAdapter.Viewholder holder, int position) {

        holder.id.setText("Hospital id:- "+ hospitalList.get(position).getHospital_id());
        holder.name.setText("Hospital Name :- "+ hospitalList.get(position).getName());
        holder.address.setText("Hospital Address :- "+ hospitalList.get(position).getAddress());
        holder.phone.setText("Hospital Phone. :- "+ hospitalList.get(position).getPhone());



        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                new delete().execute("http://aislyn.in/DRappointment/DOCTOR/deletehosp.php?id="+hospitalList.get(position).getId());
            }
        });



    }

    @Override
    public int getItemCount() {
        return hospitalList.size();
    }


    public class Viewholder extends RecyclerView.ViewHolder  {


        TextView id,name,address,phone;
        CircularProgressButton delete;


        public Viewholder(@NonNull View itemView) {
            super(itemView);


            id=itemView.findViewById(R.id.id);
            name=itemView.findViewById(R.id.name);
            address=itemView.findViewById(R.id.address);
            phone=itemView.findViewById(R.id.phone);
            delete=itemView.findViewById(R.id.delete);


            if(drSharedPrefmanager.getuser().getId()==9){
                delete.setVisibility(View.VISIBLE);
            }




        }




    }

    private class delete extends AsyncTask<String, Void, Void> {



        @Override
        protected Void doInBackground(String... strings) {


            String url = strings[0];

            StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
                @Override
                public void onResponse(String response) {


                    if (response.contains("1")) {


                        Toast.makeText(context, "Delete..", Toast.LENGTH_SHORT).show();


                    } else {


                        Toast.makeText(context, "Something went wrong" + response, Toast.LENGTH_SHORT).show();

                    }


                }
            }, new com.android.volley.Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {


                    Toast.makeText(context, "No Response from server.Try after some time and Check internet", Toast.LENGTH_SHORT).show();
                }
            }

            ) {

            };

            Mysingleton.getInstance(context).addToRequestque(request);


            return null;
        }


    }



}
