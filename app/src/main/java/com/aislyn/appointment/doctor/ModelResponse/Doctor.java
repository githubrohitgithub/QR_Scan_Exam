package com.aislyn.appointment.doctor.ModelResponse;

public class Doctor {


    int id;
    String drname;
    String qualification;
    String specialist;
    String IMR_NO;
    String email;
    int fee;

    public Doctor(int id, String drname, String qualification, String specialist, String IMR_NO, String email, int fee) {
        this.id = id;
        this.drname = drname;
        this.qualification = qualification;
        this.specialist = specialist;
        this.IMR_NO = IMR_NO;
        this.email = email;
        this.fee = fee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDrname() {
        return drname;
    }

    public void setDrname(String drname) {
        this.drname = drname;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getSpecialist() {
        return specialist;
    }

    public void setSpecialist(String specialist) {
        this.specialist = specialist;
    }

    public String getIMR_NO() {
        return IMR_NO;
    }

    public void setIMR_NO(String IMR_NO) {
        this.IMR_NO = IMR_NO;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }
}
