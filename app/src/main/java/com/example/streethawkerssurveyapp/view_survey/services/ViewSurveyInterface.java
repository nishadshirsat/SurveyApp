package com.example.streethawkerssurveyapp.view_survey.services;

import com.example.streethawkerssurveyapp.view_survey.response_pojo.SingleSurveyResponse;
import com.example.streethawkerssurveyapp.view_survey.response_pojo.ViewSurveyResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ViewSurveyInterface {


    @FormUrlEncoded
    @POST("search-survey")
    Call<ViewSurveyResponse> getViewSurveyData(
            @HeaderMap Map<String, String> token,
            @Field("type") String type);

    @FormUrlEncoded
    @POST("search-survey")
    Call<ViewSurveyResponse> viewPagewiseSurvey(
            @HeaderMap Map<String, String> token,
            @Query("page") String page,
            @Field("type") String type,
            @Field("date") String date,
            @Field("search_key") String search_key
    );

    @GET
    Call<SingleSurveyResponse> SingleSurveyDetails(
            @HeaderMap Map<String, String> token,
            @Url String CustomUrl);


}
