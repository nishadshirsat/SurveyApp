package com.example.streethawkerssurveyapp.services_pack;

import com.example.streethawkerssurveyapp.response_pack.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("login-user")
    Call<LoginResponse> getLoginResponse(@Field("email") String email,@Field("password") String password);
}
