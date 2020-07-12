package com.example.streethawkerssurveyapp.services_pack;

import com.example.streethawkerssurveyapp.pojo_class.FamilyMembers;
import com.example.streethawkerssurveyapp.pojo_class.LandAssets;
import com.example.streethawkerssurveyapp.response_pack.LoginResponse;
import com.example.streethawkerssurveyapp.response_pack.SurveyResponse;
import com.example.streethawkerssurveyapp.response_pack.UpdateSurveyResponse;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
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
            @Part("latitude") RequestBody latitude,
            @Part("longitude") RequestBody longitude,
            @Part("criminal_case_status") RequestBody criminal_case_status);


    @FormUrlEncoded
    @POST("update-survey")
    Call<UpdateSurveyResponse> getUpdateSurvey(
            @HeaderMap Map<String, String> token,
            @Field("uri_number") String uri_number,
            @Field("type_of_vending") String type_of_vending,
            @Field("name_of_vending_site") String name_of_vending_site,
            @Field("timing_of_vending_from") String timing_of_vending_from,
            @Field("timing_of_vending_to") String timing_of_vending_to,
            @Field("number_of_yrs_of_vending") String number_of_yrs_of_vending,
            @Field("annual_income") String annual_income,
            @Field("applicant_recognized_as_a_street_vendor") String applicant_recognized_as_a_street_vendor,
            @Field("type_of_structure") String type_of_structure,
            @Field("date_of_start_of_vending_activity") String date_of_start_of_vending_activity,
            @Field("tehbazari_available") String tehbazari_available,
            @Field("choice_of_vending_area") String choice_of_vending_area
    );

//    @FormUrlEncoded
//    @POST("update-survey")
//    Call<UpdateSurveyResponse> getUpdateFamilySurvey(
//            @HeaderMap Map<String, String> token,
//            @Field("uri_number") String uri_number,
//            @Field("family_members") String family_members,
//            @Field("family_member_details") String family_member_details,
////            @Field("family_member_details") List<FamilyMembers> family_member_details,
//            @Field("land_fixed_assets") String land_fixed_assets,
//            @Field("family_members_been_surveyed") String family_members_been_surveyed,
//            @Field("family_member_survey_details") String family_member_survey_details
//    );

    @Multipart
    @POST("update-survey")
    Call<UpdateSurveyResponse> getUpdateFamilySurvey(
            @HeaderMap Map<String, String> token,
            @Part("uri_number") RequestBody uri_number,
            @Part("family_members") RequestBody family_members,
            @Part("family_member_details") RequestBody family_member_details,
//            @Field("family_member_details") List<FamilyMembers> family_member_details,
            @Part("land_fixed_assets") RequestBody land_fixed_assets,
            @Part("family_members_been_surveyed") RequestBody family_members_been_surveyed,
            @Part("family_member_survey_details") RequestBody family_member_survey_details
    );

    @Multipart
    @POST("update-survey")
    Call<UpdateSurveyResponse> getUpdateDocuments(
            @HeaderMap Map<String, String> token,
            @Part("uri_number") RequestBody uri_number,
            @Part MultipartBody.Part identity_proof_documents,
            @Part MultipartBody.Part vending_history_proof_documents,
            @Part MultipartBody.Part allotment_of_tehbazari_document,
//            @Part MultipartBody.Part recording,
            @Part MultipartBody.Part undertaking_by_the_applicant
    );

    @Multipart
    @POST("update-survey")
    Call<UpdateSurveyResponse> getUpdateRecording(
            @HeaderMap Map<String, String> token,
            @Part("uri_number") RequestBody uri_number,
            @Part MultipartBody.Part recording
    );


}
