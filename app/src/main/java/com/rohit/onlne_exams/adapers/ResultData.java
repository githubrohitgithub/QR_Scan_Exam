package com.rohit.onlne_exams.adapers;

public class ResultData {


    private String id;
    private String sub_code;
    private String set_code;
    private String sreg_no;
    private String correct;
    private String wrong;
    private String total;
    private String attempted;
    private String result;

    public ResultData(String id, String sub_code, String set_code, String sreg_no, String correct, String wrong, String total, String attempted, String result) {
        this.id = id;
        this.sub_code = sub_code;
        this.set_code = set_code;
        this.sreg_no = sreg_no;
        this.correct = correct;
        this.wrong = wrong;
        this.total = total;
        this.attempted = attempted;
        this.result = result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSub_code() {
        return sub_code;
    }

    public void setSub_code(String sub_code) {
        this.sub_code = sub_code;
    }

    public String getSet_code() {
        return set_code;
    }

    public void setSet_code(String set_code) {
        this.set_code = set_code;
    }

    public String getSreg_no() {
        return sreg_no;
    }

    public void setSreg_no(String sreg_no) {
        this.sreg_no = sreg_no;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public String getWrong() {
        return wrong;
    }

    public void setWrong(String wrong) {
        this.wrong = wrong;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getAttempted() {
        return attempted;
    }

    public void setAttempted(String attempted) {
        this.attempted = attempted;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
