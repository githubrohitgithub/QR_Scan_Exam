package com.aislyn.appointment.doctor.ModelResponse;

public class DLoginResponse {

    Doctor user;
    String error,message;

    public DLoginResponse(Doctor user, String error, String message) {
        this.user = user;
        this.error = error;
        this.message = message;
    }


    public DLoginResponse(){

    }

    public Doctor getUser() {
        return user;
    }

    public void setUser(Doctor user) {
        this.user = user;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
