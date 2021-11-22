package com.rohit.onlne_exams.teacher.ModelResponse;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultSub_Set {

    @SerializedName("sub_set")
    List<Sub_Set> sub_sets;
    String message;
    String error;


    public ResultSub_Set(List<Sub_Set> sub_sets, String message, String error) {
        this.sub_sets = sub_sets;
        this.message = message;
        this.error = error;
    }

    public List<Sub_Set> getSub_sets() {
        return sub_sets;
    }

    public void setSub_sets(List<Sub_Set> sub_sets) {
        this.sub_sets = sub_sets;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}

