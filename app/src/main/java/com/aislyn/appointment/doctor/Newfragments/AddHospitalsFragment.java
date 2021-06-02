package com.aislyn.appointment.doctor.Newfragments;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aislyn.appointment.Mysingleton;
import com.aislyn.appointment.R;
import com.aislyn.appointment.users.HospitalAdapter;
import com.aislyn.appointment.users.ModelResponse.AllHospitalsFetchResponse;
import com.aislyn.appointment.users.ModelResponse.Hospital;
import com.aislyn.appointment.users.RetrofitClient;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddHospitalsFragment extends Fragment {

    RecyclerView recyclerView;
    List<Hospital> hospitalList;
    ShimmerFrameLayout layout;
    Button add;
    CircularProgressButton addbtn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.drfragment_hospitals, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        recyclerView=view.findViewById(R.id.recycle);
        add=view.findViewById(R.id.add);


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        layout=view.findViewById(R.id.shimmer);


        add.setOnClickListener(v->{


            AddHospital();
        });


        fetchhospitals();



    }

    private void fetchhospitals(){
        Call<AllHospitalsFetchResponse> call= RetrofitClient.getInstance().getApi().fetchAllHospitals();

        call.enqueue(new Callback<AllHospitalsFetchResponse>() {
            @Override
            public void onResponse(Call<AllHospitalsFetchResponse> call, Response<AllHospitalsFetchResponse> response) {

                if(response.isSuccessful()){

                    hospitalList=response.body().getDoctorsList();



                    recyclerView.setAdapter(new HospitalAdapter(getContext(),hospitalList));
                    layout.stopShimmer();
                    layout.hideShimmer();
                    layout.setVisibility(View.GONE);
                    add.setVisibility(View.VISIBLE);







                }else{

                    Toast.makeText(getContext(), response.body().getError(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<AllHospitalsFetchResponse> call, Throwable t) {

                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void AddHospital() {

        final AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setTitle("Add Hospital");

        EditText hospid, hospname, hospaddress,hospphone;
        LayoutInflater inflater = LayoutInflater.from(getContext());
        final View editchit_layout = inflater.inflate(R.layout.hosp_add_layout, null);


        hospname = editchit_layout.findViewById(R.id.hospname);
        hospid = editchit_layout.findViewById(R.id.hospid);
        hospaddress = editchit_layout.findViewById(R.id.hospaddress);
        hospphone = editchit_layout.findViewById(R.id.hospphone);
        addbtn = editchit_layout.findViewById(R.id.addbtn);

        dialog.setView(editchit_layout);


        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (TextUtils.isEmpty(hospid.getText())) {

                    hospid.setError("Enter hospital id");
                    hospid.requestFocus();
                    addbtn.revertAnimation();
                } else if (TextUtils.isEmpty(hospname.getText())) {

                    hospname.setError("Enter hospital name");
                    hospname.requestFocus();
                    addbtn.revertAnimation();

                } else if (TextUtils.isEmpty(hospaddress.getText())) {

                    hospaddress.setError("Enter hospital address");
                    hospaddress.requestFocus();
                    addbtn.revertAnimation();
                }else if (TextUtils.isEmpty(hospphone.getText())) {

                    hospphone.setError("Enter hospital phone");
                    hospphone.requestFocus();
                    addbtn.revertAnimation();

                } else {

                    addbtn.startAnimation();
                    new AddHosp().execute("http://aislyn.in/DRappointment/DOCTOR/addhospital.php?hospital_id="+hospid.getText().toString()+"&name="+hospname.getText().toString()+"&address="+hospaddress.getText().toString()+"&phone="+hospphone.getText().toString());
                }
            }
        });


        dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();
            }
        });

        dialog.show();


    }

    private class AddHosp extends AsyncTask<String, Void, Void> {



        @Override
        protected Void doInBackground(String... strings) {


            String url = strings[0];

            StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
                @Override
                public void onResponse(String response) {


                    if (response.contains("1")) {


                        fetchhospitals();
                        addbtn.revertAnimation();
                        Toast.makeText(getContext(), "Added..", Toast.LENGTH_SHORT).show();


                    } else {


                        Toast.makeText(getContext(), "Something went wrong" + response, Toast.LENGTH_SHORT).show();
                        addbtn.revertAnimation();
                    }


                }
            }, new com.android.volley.Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {

                    addbtn.revertAnimation();
                    Toast.makeText(getContext(), "No Response from server.Try after some time and Check internet", Toast.LENGTH_SHORT).show();
                }
            }

            ) {

            };

            Mysingleton.getInstance(getContext()).addToRequestque(request);


            return null;
        }


    }
}