package com.aislyn.appointment.doctor.Newfragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aislyn.appointment.R;
import com.aislyn.appointment.doctor.ModelResponse.Doctor;
import com.aislyn.appointment.users.DoctorsAdapter;
import com.aislyn.appointment.users.HospitalAdapter;
import com.aislyn.appointment.users.ModelResponse.AllDoctorsFetchResponse;
import com.aislyn.appointment.users.ModelResponse.AllHospitalsFetchResponse;
import com.aislyn.appointment.users.ModelResponse.Hospital;
import com.aislyn.appointment.users.RetrofitClient;
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
    CircularProgressButton add;

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

            Toast.makeText(getContext(), "add", Toast.LENGTH_SHORT).show();
        });

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
}