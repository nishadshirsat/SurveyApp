package com.example.streethawkerssurveyapp.response_pack;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("data")
    private LoginData data;

    @SerializedName("status")
    private boolean status;

    @SerializedName("msg")
    private String msg;

    public void setData(LoginData data) {
        this.data = data;
    }

    public LoginData getData() {
        return data;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return
                "LoginResponse{" +
                        "data = '" + data + '\'' +
                        "msg = '" + msg + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}