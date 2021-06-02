package com.aislyn.appointment;

public class FetchAllBooked {

    int appointment_id;
    String patient_name;
    String patient_id;
    String doctor_name;
    String doctor_id;
    String patient_phone;
    String expired;

    public FetchAllBooked(int appointment_id, String patient_name, String patient_id, String doctor_name, String doctor_id,String patient_phone,String expired) {
        this.appointment_id = appointment_id;
        this.patient_name = patient_name;
        this.patient_id = patient_id;
        this.doctor_name = doctor_name;
        this.doctor_id = doctor_id;
        this.patient_phone = patient_phone;
        this.expired = expired;

    }

    public String getExpired() {
        return expired;
    }

    public void setExpired(String expired) {
        this.expired = expired;
    }

    public String getPatient_phone() {
        return patient_phone;
    }

    public void setPatient_phone(String patient_phone) {
        this.patient_phone = patient_phone;
    }

    public int getAppointment_id() {
        return appointment_id;
    }

    public void setAppointment_id(int appointment_id) {
        this.appointment_id = appointment_id;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public String getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }
}
