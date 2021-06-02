package com.aislyn.appointment.doctor;

import com.aislyn.appointment.doctor.ModelResponse.DLoginResponse;
import com.aislyn.appointment.doctor.ModelResponse.DeleteAccountResponse;
import com.aislyn.appointment.doctor.ModelResponse.RegisterResponse;

import com.aislyn.appointment.doctor.ModelResponse.UpdatepasswordResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("register.php")
    Call<RegisterResponse> register(

            @Field("drname") String drname,
            @Field("qualification") String qualification,
            @Field("specialist") String specialist,
            @Field("IMR_NO") String IMR_NO,
            @Field("email") String email,
            @Field("password") String passowrd

    );


    @FormUrlEncoded
    @POST("login.php")
    Call<DLoginResponse> login(


            @Field("email") String email,
            @Field("password") String passowrd

    );



    @FormUrlEncoded
    @POST("updatepassword.php")
    Call<UpdatepasswordResponse> updateUserPass(


            @Field("email") String email,
            @Field("current") String currentPassword,
            @Field("new") String newpassword

    );


    @FormUrlEncoded
    @POST("deleteaccount.php")
    Call<DeleteAccountResponse> deleteaccount(

            @Field("id") int id


    );





}
