package com.online.exams.teacher.ModelResponse;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultResponse {

    @SerializedName("result")
    List<Result> resultList;
    String message;
    String error;

    public ResultResponse(List<Result> resultList, String message, String error) {
        this.resultList = resultList;
        this.message = message;
        this.error = error;
    }

    public List<Result> getResultList() {
        return resultList;
    }

    public void setResultList(List<Result> resultList) {
        this.resultList = resultList;
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
