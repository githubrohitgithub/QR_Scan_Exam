package com.aislyn.appointment.users.Newfragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aislyn.appointment.users.ModelResponse.UpdatepasswordResponse;
import com.aislyn.appointment.R;
import com.aislyn.appointment.users.RetrofitClient;
import com.aislyn.appointment.users.SharedPrefmanager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends Fragment implements View.OnClickListener {


    EditText currentPass,newpass;
    int userId;
    Button updateUserPassword;
    String userEmailId;
    SharedPrefmanager sharedPrefmanager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_profile, container, false);



        currentPass=view.findViewById(R.id.currentpassword);
        newpass=view.findViewById(R.id.newpassword);
        updateUserPassword=view.findViewById(R.id.btnupdatepassword);


        sharedPrefmanager=new SharedPrefmanager(getActivity());
        userId=sharedPrefmanager.getuser().getId();
        userEmailId=sharedPrefmanager.getuser().getEmail();

        updateUserPassword.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View view) {


        if (view.getId() == R.id.btnupdatepassword) {
            updatePassword();
        }
    }

    private void updatePassword() {


        String usercurrentPass=currentPass.getText().toString().trim();
        String usernewpass=newpass.getText().toString().trim();


        if(usercurrentPass.isEmpty()){
            currentPass.setError("Enter current password");
            currentPass.requestFocus();
            return;
        }

        if(usercurrentPass.length()<8){
            currentPass.setError("Enter current password");
            currentPass.requestFocus();
            return;
        }


        if(usernewpass.isEmpty()){
            newpass.setError("Enter new password");
            newpass.requestFocus();
            return;
        }

        if(usernewpass.length()<8){
            newpass.setError("Enter new password");
            newpass.requestFocus();
            return;
        }


        Call<UpdatepasswordResponse> call= RetrofitClient.getInstance()
                .getApi()
                .updateUserPass(userEmailId,usercurrentPass,usernewpass);


        call.enqueue(new Callback<UpdatepasswordResponse>() {
            @Override
            public void onResponse(Call<UpdatepasswordResponse> call, Response<UpdatepasswordResponse> response) {


                UpdatepasswordResponse updatepasswordResponse=response.body();



                if(response.isSuccessful()){


                    if(updatepasswordResponse.getError().equals("200")){

                        Toast.makeText(getActivity(), updatepasswordResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }


                }else{

                    Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();

                }




            }

            @Override
            public void onFailure(Call<UpdatepasswordResponse> call, Throwable t) {

                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });




    }


}