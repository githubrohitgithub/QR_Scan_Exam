package com.online.exams.teacher.ModelResponse;

public class TLoginResponse {

    Teacher teacher;
    String error,message;

    public TLoginResponse(Teacher teacher, String error, String message) {
        this.teacher = teacher;
        this.error = error;
        this.message = message;
    }


    public TLoginResponse(){

    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
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
