package com.example.streethawkerssurveyapp.database_pack;


import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import retrofit2.http.Field;

@Entity(tableName = "surveydata")
public class SurveyDetails {

    @PrimaryKey
    @NonNull
    private String survey_id;

    //Personal Details
    private String photo_of_vendor;

    private String photo_of_vending_site;

    private String name_of_vendor;

    private String aadhar_number;

    private String Sex;

    private String Age;

    private String date_of_birth;

    private String mobile_no;

    private String landline_no;

    private String education_status;

    private String name_of_father_husband;

    private String name_of_mother;

    private String name_of_spouse;

    private String wheather_widowed;

    private String category;

    private String residential_address;

    private String permenent_address;

    private String annual_income;

    private String is_criminal_case;

    private String criminal_details_json;

    //Family Details
    private String family_details_json;

    private String landassets_details_json;


    //Family Details
    private String is_family_member_surveyed;

    private String family_member_relationship;

    private String family_member_name;

    private String family_member_adhaar;

    private String family_member_age;


    //BankDetails
    private String bank_account_number;

    private String bank_name;

    private String branch_name;

    private String ifsc_code;

    //Vending Details
    private String type_of_vending;

    private String name_of_vending;

    private String timing_of_vending_from;

    private String timing_of_vending_to;

    private String timing_of_vending_from_1;

    private String timing_of_vending_to_1;


}
