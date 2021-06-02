package com.aislyn.appointment.users.ModelResponse;

public class Hospital {


    int id;
    String name;
    String address;
    String phone;
    String hospital_id;

    public Hospital(int id, String name, String address, String phone,String hospital_id) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.hospital_id = hospital_id;
    }

    public String getHospital_id() {
        return hospital_id;
    }

    public void setHospital_id(String hospital_id) {
        this.hospital_id = hospital_id;
    }

    public int getId() {
        return id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
