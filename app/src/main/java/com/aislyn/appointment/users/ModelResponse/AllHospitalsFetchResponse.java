package com.aislyn.appointment.users.ModelResponse;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllHospitalsFetchResponse {

    @SerializedName("hospitals")
    List<Hospital> hospitalList;
    String error;

    public AllHospitalsFetchResponse(List<Hospital> doctorsList, String error) {
        this.hospitalList = doctorsList;
        this.error = error;
    }

    public List<Hospital> getDoctorsList() {
        return hospitalList;
    }

    public void setDoctorsList(List<Hospital> doctorsList) {
        this.hospitalList = doctorsList;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}

