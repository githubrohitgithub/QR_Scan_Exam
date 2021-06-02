package com.aislyn.appointment.users.ModelResponse;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllUserFetchResponse {

    @SerializedName("users")
    List<User> userList;
    String error;

    public AllUserFetchResponse(List<User> userList, String error) {
        this.userList = userList;
        this.error = error;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
