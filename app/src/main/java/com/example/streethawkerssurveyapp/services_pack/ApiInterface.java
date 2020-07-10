package com.example.streethawkerssurveyapp.services_pack;

import com.example.streethawkerssurveyapp.response_pack.LoginResponse;
import com.example.streethawkerssurveyapp.response_pack.SurveyResponse;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("login-user")
    Call<LoginResponse> getLoginResponse(@Field("email") String email, @Field("password") String password);


    @FormUrlEncoded
    @POST("survey")
    Call<LoginResponse> getLoginResponse(@Header("Authorization") String value_header, @Field("email") String email, @Field("password") String password);


    @Multipart
    @POST("survey")
    Call<SurveyResponse> getAddSurvey(
            @HeaderMap Map<String, String> token,
            @Part MultipartBody.Part photo_of_the_street_vendor,
            @Part("survey_id") RequestBody survey_id,
            @Part("name_of_the_street_vendor") RequestBody name_of_the_street_vendor,
            @Part("sex") RequestBody sex,
            @Part("age") RequestBody age,
            @Part("date_of_birth") RequestBody date_of_birth,
            @Part("contact_number") RequestBody contact_number,
            @Part("landline_number") RequestBody landline_number,
            @Part("education_status") RequestBody education_status,
            @Part("name_of_father_husband") RequestBody name_of_father_husband,
            @Part("name_of_mother") RequestBody name_of_mother,
            @Part("spouse_name") RequestBody spouse_name,
            @Part("whether_widowed_widower") RequestBody whether_widowed_widower,
            @Part("category") RequestBody category,
            @Part("residential_correspondence_address") RequestBody residential_correspondence_address,
            @Part("permanent_address") RequestBody permanent_address,
            @Part("aadhar_card_details") RequestBody aadhar_card_details,
            @Part("bank_account_number") RequestBody bank_account_number,
            @Part("bank_name") RequestBody bank_name,
            @Part("bank_branch_name") RequestBody bank_branch_name,
            @Part("bank_ifsc") RequestBody bank_ifsc,
            @Part("criminal_case_pending") RequestBody criminal_case_pending,
            @Part("criminal_case_number") RequestBody criminal_case_number,
            @Part("criminal_case_date") RequestBody criminal_case_date,
            @Part("criminal_case_fir_number") RequestBody criminal_case_fir_number,
            @Part("criminal_case_name_of_police") RequestBody criminal_case_name_of_police,
            @Part("criminal_case_status") RequestBody criminal_case_status);

//    @GET("/posts")
//    Observable<JsonElement> getDataFromService(
//            @Header("Authorization")token: String = "Bearer " + PreferenceUtils.getToken(),
//    @QueryMap
//    HashMap<String, Object> queryParams);


}
