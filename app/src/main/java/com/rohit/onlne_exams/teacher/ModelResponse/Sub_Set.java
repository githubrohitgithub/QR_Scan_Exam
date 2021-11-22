package com.rohit.onlne_exams.teacher.ModelResponse;

public class Sub_Set {

    String sub_code;
    String setcode;

    public Sub_Set(String sub_code, String setcode) {
        this.sub_code = sub_code;
        this.setcode = setcode;
    }

    public String getSub_code() {
        return sub_code;
    }

    public void setSub_code(String sub_code) {
        this.sub_code = sub_code;
    }

    public String getSetcode() {
        return setcode;
    }

    public void setSetcode(String setcode) {
        this.setcode = setcode;
    }
}
