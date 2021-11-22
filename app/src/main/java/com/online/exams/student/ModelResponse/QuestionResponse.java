package com.online.exams.student.ModelResponse;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuestionResponse {

    @SerializedName("question")
    List<Question> questionList;
    String error;

    public QuestionResponse(List<Question> questionList, String error) {
        this.questionList = questionList;
        this.error = error;
    }

    public List<Question> getDataList() {
        return questionList;
    }

    public void setDataList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
