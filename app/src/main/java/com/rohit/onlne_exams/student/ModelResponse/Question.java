package com.rohit.onlne_exams.student.ModelResponse;

public class Question {

    int id;
    String sub_code;
    String teacher_id;
    String setcode;
    String question;
    String optionA;
    String optionB;
    String optionC;
    String optionD;
    String answer;

    public Question(int id, String sub_code, String teacher_id, String setcode, String question, String optionA, String optionB, String optionC, String optionD, String answer) {
        this.id = id;
        this.sub_code = sub_code;
        this.teacher_id = teacher_id;
        this.setcode = setcode;
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSub_code() {
        return sub_code;
    }

    public void setSub_code(String sub_code) {
        this.sub_code = sub_code;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getSetcode() {
        return setcode;
    }

    public void setSetcode(String setcode) {
        this.setcode = setcode;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
