package com.aislyn.appointment.users;

import com.aislyn.appointment.users.ModelResponse.AllDoctorsFetchResponse;
import com.aislyn.appointment.users.ModelResponse.AllHospitalsFetchResponse;
import com.aislyn.appointment.users.ModelResponse.AllUserFetchResponse;
import com.aislyn.appointment.users.ModelResponse.DeleteAccountResponse;
import com.aislyn.appointment.users.ModelResponse.LoginResponse;
import com.aislyn.appointment.users.ModelResponse.RegisterResponse;
import com.aislyn.appointment.users.ModelResponse.UpdatepasswordResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("register.php")
    Call<RegisterResponse> register(

            @Field("username") String username,
            @Field("email") String email,
            @Field("password") String passowrd

    );


    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResponse> login(


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



    @GET("fetchusers.php")
    Call<AllUserFetchResponse> fetchAllUsers();

    @GET("fetchdoctors.php")
    Call<AllDoctorsFetchResponse> fetchAllDoctors();


    @GET("fetchhospitals.php")
    Call<AllHospitalsFetchResponse> fetchAllHospitals();




}
