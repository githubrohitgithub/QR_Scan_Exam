package com.aislyn.appointment.users.ModelResponse;

import com.aislyn.appointment.doctor.ModelResponse.Doctor;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllDoctorsFetchResponse {

    @SerializedName("doctors")
    List<Doctor> doctorsList;
    String error;

    public AllDoctorsFetchResponse(List<Doctor> doctorsList, String error) {
        this.doctorsList = doctorsList;
        this.error = error;
    }

    public List<Doctor> getDoctorsList() {
        return doctorsList;
    }

    public void setDoctorsList(List<Doctor> doctorsList) {
        this.doctorsList = doctorsList;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
