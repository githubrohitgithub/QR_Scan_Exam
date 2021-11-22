package com.rohit.onlne_exams.teacher.ModelResponse;

public class Teacher {


    int id;
    String name;
    String teacher_id;
    String quali;
    String email;
    String password;

    public Teacher(int id, String name,String teacher_id, String quali, String email, String password) {
        this.id = id;
        this.name = name;
        this.teacher_id = teacher_id;
        this.quali = quali;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuali() {
        return quali;
    }

    public void setQuali(String quali) {
        this.quali = quali;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
