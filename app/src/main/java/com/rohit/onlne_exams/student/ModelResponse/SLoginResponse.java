package com.rohit.onlne_exams.student.ModelResponse;

public class SLoginResponse {

    student student;
    String error,message;

    public SLoginResponse(student student, String error, String message) {
        this.student = student;
        this.error = error;
        this.message = message;
    }


    public SLoginResponse(){

    }

    public student getUser() {
        return student;
    }

    public void setUser(student student) {
        this.student = student;
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
