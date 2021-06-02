package com.aislyn.appointment.users.Newfragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aislyn.appointment.doctor.ModelResponse.Doctor;
import com.aislyn.appointment.users.ModelResponse.AllDoctorsFetchResponse;
import com.aislyn.appointment.R;
import com.aislyn.appointment.users.RetrofitClient;
import com.aislyn.appointment.users.DoctorsAdapter;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DoctorsFragment extends Fragment  {


    RecyclerView recyclerView;
    List<Doctor> doctorsListList;
    ShimmerFrameLayout layout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doctors, container, false);




    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        recyclerView=view.findViewById(R.id.recycle);


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        layout=view.findViewById(R.id.shimmer);

        //retrofit


       Call<AllDoctorsFetchResponse>call= RetrofitClient.getInstance().getApi().fetchAllDoctors();

       call.enqueue(new Callback<AllDoctorsFetchResponse>() {
           @Override
           public void onResponse(Call<AllDoctorsFetchResponse> call, Response<AllDoctorsFetchResponse> response) {

               if(response.isSuccessful()){

                   doctorsListList=response.body().getDoctorsList();



                   recyclerView.setAdapter(new DoctorsAdapter(getActivity(),doctorsListList));
                   layout.stopShimmer();
                   layout.hideShimmer();
                   layout.setVisibility(View.GONE);







               }else{

                   Toast.makeText(getActivity(), response.body().getError(), Toast.LENGTH_SHORT).show();

               }
           }

           @Override
           public void onFailure(Call<AllDoctorsFetchResponse> call, Throwable t) {

               Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
           }
       });




    


    }


}