package com.aislyn.appointment.doctor;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DoctorRetrofitClient {

    private  static String BASE_URL="http://aislyn.in/DRappointment/DOCTOR/";
    private static DoctorRetrofitClient retrofitClient;
    private static Retrofit retrofit;



    private DoctorRetrofitClient(){
        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }



    public static synchronized DoctorRetrofitClient getInstance(){


        if(retrofitClient==null){
            retrofitClient=new DoctorRetrofitClient();

        }
        return  retrofitClient;
    }


   public Api getApi(){
        return retrofit.create(Api.class);
    }


}
