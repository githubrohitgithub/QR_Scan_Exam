package com.rohit.onlne_exams.network;

import com.rohit.onlne_exams.student.ModelResponse.QuestionResponse;
import com.rohit.onlne_exams.teacher.ModelResponse.ResultResponse;
import com.rohit.onlne_exams.teacher.ModelResponse.ResultSub_Set;
import com.rohit.onlne_exams.teacher.ModelResponse.TLoginResponse;
import com.rohit.onlne_exams.teacher.ModelResponse.TRegisterResponse;

import com.rohit.onlne_exams.student.ModelResponse.SLoginResponse;
import com.rohit.onlne_exams.student.ModelResponse.SRegisterResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {


    @FormUrlEncoded
    @POST("questionsadd.php")
    Call<TRegisterResponse> questionadd(


            @Field("sub_code") String sub_code,
            @Field("teacher_id") String teacher_id,
            @Field("setcode") String setcode,
            @Field("question") String question,
            @Field("optionA") String optionA,
            @Field("optionB") String optionB,
            @Field("optionC") String optionC,
            @Field("optionD") String optionD,
            @Field("answer") String answer

    );

    @FormUrlEncoded
    @POST("tregister.php")
    Call<TRegisterResponse> tregister(

            @Field("name") String tname,
            @Field("quali") String tqualification,
            @Field("teacher_id") String teacher_id,
            @Field("email") String temail,
            @Field("password") String tpassowrd

    );

    @FormUrlEncoded
    @POST("tlogin.php")
    Call<TLoginResponse> tlogin(


            @Field("email") String email,
            @Field("password") String passowrd

    );

    @FormUrlEncoded
    @POST("sregister.php")
    Call<SRegisterResponse> sregister(


            @Field("sreg") String sreg,
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String passowrd

    );


    @FormUrlEncoded
    @POST("checkstudent.php")
    Call<SRegisterResponse> checkstudent(


            @Field("sreg") String sreg,
            @Field("sub_code") String subcode

    );


    @FormUrlEncoded
    @POST("update_result.php")
    Call<SRegisterResponse> supdate_result(


            @Field("sub_code") String sub_code,
            @Field("set_code") String set_code,
            @Field("sreg_no") String sreg_no,
            @Field("correct") String correct,
            @Field("wrong") String wrong,
            @Field("total") String total,
            @Field("attemted") String attemted

    );






    @FormUrlEncoded
    @POST("slogin.php")
    Call<SLoginResponse> slogin(


            @Field("email") String email,
            @Field("password") String passowrd

    );


    @FormUrlEncoded
    @POST("update_questionset.php")
    Call<TRegisterResponse> setupdate(


            @Field("sub_code") String sub_code,
            @Field("set_code") String set_code

    );



    @FormUrlEncoded
    @POST("scan.php")
    Call<QuestionResponse> squestions(

            @Field("sub_code") String sub_code,
            @Field("set_code") String set_code


    );


    @GET("result.php")
    Call<ResultResponse> tresult(


    );

    @GET("subcode_subset.php")
    Call<ResultSub_Set> subsetresult(


    );







}
