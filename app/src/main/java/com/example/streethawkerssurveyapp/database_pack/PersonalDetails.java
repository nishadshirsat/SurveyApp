package com.example.streethawkerssurveyapp.database_pack;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "personaldata")
public class PersonalDetails {


//    private String id;

    @PrimaryKey
    @NonNull
    private String survey_id;

    //Personal Details
    private String photo_of_vendor;

    private String photo_of_vending_site;

    private String barcode;

    private String latitude;

    private String longitude;

    private String name_of_vendor;

    private String aadhar_number;

    private String aadhar_details;

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

    private String aadhar_details_json;

    private String is_criminal_case;

    private String criminal_details_json;

    public PersonalDetails(@NonNull String survey_id,
                           String photo_of_vendor,
                           String photo_of_vending_site,
                           String barcode,
                           String latitude,
                           String longitude,
                           String name_of_vendor,
                           String aadhar_number,
                           String Sex,
                           String Age,
                           String date_of_birth,
                           String mobile_no,
                           String landline_no,
                           String education_status,
                           String name_of_father_husband,
                           String name_of_mother,
                           String name_of_spouse,
                           String wheather_widowed,
                           String category,
                           String residential_address,
                           String permenent_address,
                           String annual_income,
                           String aadhar_details_json,
                           String is_criminal_case,
                           String criminal_details_json) {

        this.survey_id = survey_id;
        this.photo_of_vendor = photo_of_vendor;
        this.photo_of_vending_site = photo_of_vending_site;
        this.barcode = barcode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name_of_vendor = name_of_vendor;
        this.aadhar_number = aadhar_number;
        this.Sex = Sex;
        this.Age = Age;
        this.date_of_birth = date_of_birth;
        this.mobile_no = mobile_no;
        this.landline_no = landline_no;
        this.education_status = education_status;
        this.name_of_father_husband = name_of_father_husband;
        this.name_of_mother = name_of_mother;
        this.name_of_spouse = name_of_spouse;
        this.wheather_widowed = wheather_widowed;
        this.category = category;
        this.residential_address = residential_address;
        this.permenent_address = permenent_address;
        this.annual_income = annual_income;
        this.aadhar_details_json = aadhar_details_json;
        this.is_criminal_case = is_criminal_case;
        this.criminal_details_json = criminal_details_json;


    }


    public String getAadhar_details() {
        return aadhar_details;
    }

    public void setAadhar_details(String aadhar_details) {
        this.aadhar_details = aadhar_details;
    }


    public String getPhoto_of_vendor() {
        return photo_of_vendor;
    }

    public void setPhoto_of_vendor(String photo_of_vendor) {
        this.photo_of_vendor = photo_of_vendor;
    }

    public String getPhoto_of_vending_site() {
        return photo_of_vending_site;
    }

    @NonNull
    public String getSurvey_id() {
        return survey_id;
    }

    public void setSurvey_id(@NonNull String survey_id) {
        this.survey_id = survey_id;
    }

    public void setPhoto_of_vending_site(String photo_of_vending_site) {
        this.photo_of_vending_site = photo_of_vending_site;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getName_of_vendor() {
        return name_of_vendor;
    }

    public void setName_of_vendor(String name_of_vendor) {
        this.name_of_vendor = name_of_vendor;
    }

    public String getAadhar_number() {
        return aadhar_number;
    }

    public void setAadhar_number(String aadhar_number) {
        this.aadhar_number = aadhar_number;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getLandline_no() {
        return landline_no;
    }

    public void setLandline_no(String landline_no) {
        this.landline_no = landline_no;
    }

    public String getEducation_status() {
        return education_status;
    }

    public void setEducation_status(String education_status) {
        this.education_status = education_status;
    }

    public String getName_of_father_husband() {
        return name_of_father_husband;
    }

    public void setName_of_father_husband(String name_of_father_husband) {
        this.name_of_father_husband = name_of_father_husband;
    }

    public String getName_of_mother() {
        return name_of_mother;
    }

    public void setName_of_mother(String name_of_mother) {
        this.name_of_mother = name_of_mother;
    }

    public String getName_of_spouse() {
        return name_of_spouse;
    }

    public void setName_of_spouse(String name_of_spouse) {
        this.name_of_spouse = name_of_spouse;
    }

    public String getWheather_widowed() {
        return wheather_widowed;
    }

    public void setWheather_widowed(String wheather_widowed) {
        this.wheather_widowed = wheather_widowed;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getResidential_address() {
        return residential_address;
    }

    public void setResidential_address(String residential_address) {
        this.residential_address = residential_address;
    }

    public String getPermenent_address() {
        return permenent_address;
    }

    public void setPermenent_address(String permenent_address) {
        this.permenent_address = permenent_address;
    }

    public String getAnnual_income() {
        return annual_income;
    }

    public void setAnnual_income(String annual_income) {
        this.annual_income = annual_income;
    }

    public String getAadhar_details_json() {
        return aadhar_details_json;
    }

    public void setAadhar_details_json(String aadhar_details_json) {
        this.aadhar_details_json = aadhar_details_json;
    }

    public String getIs_criminal_case() {
        return is_criminal_case;
    }

    public void setIs_criminal_case(String is_criminal_case) {
        this.is_criminal_case = is_criminal_case;
    }

    public String getCriminal_details_json() {
        return criminal_details_json;
    }

    public void setCriminal_details_json(String criminal_details_json) {
        this.criminal_details_json = criminal_details_json;
    }
}
