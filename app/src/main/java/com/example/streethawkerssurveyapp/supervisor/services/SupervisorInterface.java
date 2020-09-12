package com.example.streethawkerssurveyapp.supervisor.services;

import com.example.streethawkerssurveyapp.response_pack.SurveyResponse;
import com.example.streethawkerssurveyapp.response_pack.UpdateSurveyResponse;
import com.example.streethawkerssurveyapp.supervisor.response_pojo.SupervisorViewSurveyResponse;
import com.example.streethawkerssurveyapp.supervisor.response_pojo.SurveyorListResponse;
import com.example.streethawkerssurveyapp.view_survey.response_pojo.ViewSurveyResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SupervisorInterface {

    @GET("surveyor-list")
    Call<SurveyorListResponse> getSurveyorList(
            @HeaderMap Map<String, String> token
    );

    @FormUrlEncoded
    @POST("surveyor-surveys")
    Call<SupervisorViewSurveyResponse> getSurveyorSurveys(
            @HeaderMap Map<String, String> token,
            @Query("page") String page,
            @Field("type") String type,
            @Field("surveyor_id") String surveyor_id,
            @Field("date") String date,
            @Field("search_key") String search_key
    );

    @FormUrlEncoded
    @POST("update-survey")
    Call<UpdateSurveyResponse> SendCheckStatus(
            @HeaderMap Map<String, String> token,
            @Field("uri_number") String uri_number,
            @Field("supervisor_check") String supervisor_check
    );

    @FormUrlEncoded
    @POST("update-survey")
    Call<UpdateSurveyResponse> SendPendingStatus(
            @HeaderMap Map<String, String> token,
            @Field("uri_number") String uri_number,
            @Field("survey_status") String survey_status
    );

}
