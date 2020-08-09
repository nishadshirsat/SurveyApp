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

    private String latitude;

    private String longitude;

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

    private String aadhar_details_json;

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

    private String years_of_vending;

    private String is_previously_street_vendor;

    private String type_of_structure;

    private String no_of_days_Active;

    private String start_date_of_vending;

    private String is_tehbajari_document;

    private String choice_of_vending_area_market;

    //Documents
    private String identity_proof_type;

    private String identity_proof_front;

    private String identity_proof_back;

    private String vending_history_proof_type;

    private String vending_history_proof_front;

    private String vending_history_proof_back;

    private String allotment_tehzabari_document;

    private String undertaking_doc;

    private String acknowledgement_doc;

    private String other_doc_type;

    private String other_document_url;

    private String comments;

    private String recording;


    @NonNull
    public String getSurvey_id() {
        return survey_id;
    }

    public void setSurvey_id(@NonNull String survey_id) {
        this.survey_id = survey_id;
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

    public void setPhoto_of_vending_site(String photo_of_vending_site) {
        this.photo_of_vending_site = photo_of_vending_site;
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

    public String getFamily_details_json() {
        return family_details_json;
    }

    public void setFamily_details_json(String family_details_json) {
        this.family_details_json = family_details_json;
    }

    public String getLandassets_details_json() {
        return landassets_details_json;
    }

    public void setLandassets_details_json(String landassets_details_json) {
        this.landassets_details_json = landassets_details_json;
    }

    public String getIs_family_member_surveyed() {
        return is_family_member_surveyed;
    }

    public void setIs_family_member_surveyed(String is_family_member_surveyed) {
        this.is_family_member_surveyed = is_family_member_surveyed;
    }

    public String getFamily_member_relationship() {
        return family_member_relationship;
    }

    public void setFamily_member_relationship(String family_member_relationship) {
        this.family_member_relationship = family_member_relationship;
    }

    public String getFamily_member_name() {
        return family_member_name;
    }

    public void setFamily_member_name(String family_member_name) {
        this.family_member_name = family_member_name;
    }

    public String getFamily_member_adhaar() {
        return family_member_adhaar;
    }

    public void setFamily_member_adhaar(String family_member_adhaar) {
        this.family_member_adhaar = family_member_adhaar;
    }

    public String getFamily_member_age() {
        return family_member_age;
    }

    public void setFamily_member_age(String family_member_age) {
        this.family_member_age = family_member_age;
    }

    public String getBank_account_number() {
        return bank_account_number;
    }

    public void setBank_account_number(String bank_account_number) {
        this.bank_account_number = bank_account_number;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getBranch_name() {
        return branch_name;
    }

    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }

    public String getIfsc_code() {
        return ifsc_code;
    }

    public void setIfsc_code(String ifsc_code) {
        this.ifsc_code = ifsc_code;
    }

    public String getType_of_vending() {
        return type_of_vending;
    }

    public void setType_of_vending(String type_of_vending) {
        this.type_of_vending = type_of_vending;
    }

    public String getName_of_vending() {
        return name_of_vending;
    }

    public void setName_of_vending(String name_of_vending) {
        this.name_of_vending = name_of_vending;
    }

    public String getTiming_of_vending_from() {
        return timing_of_vending_from;
    }

    public void setTiming_of_vending_from(String timing_of_vending_from) {
        this.timing_of_vending_from = timing_of_vending_from;
    }

    public String getTiming_of_vending_to() {
        return timing_of_vending_to;
    }

    public void setTiming_of_vending_to(String timing_of_vending_to) {
        this.timing_of_vending_to = timing_of_vending_to;
    }

    public String getTiming_of_vending_from_1() {
        return timing_of_vending_from_1;
    }

    public void setTiming_of_vending_from_1(String timing_of_vending_from_1) {
        this.timing_of_vending_from_1 = timing_of_vending_from_1;
    }

    public String getTiming_of_vending_to_1() {
        return timing_of_vending_to_1;
    }

    public void setTiming_of_vending_to_1(String timing_of_vending_to_1) {
        this.timing_of_vending_to_1 = timing_of_vending_to_1;
    }

    public String getYears_of_vending() {
        return years_of_vending;
    }

    public void setYears_of_vending(String years_of_vending) {
        this.years_of_vending = years_of_vending;
    }

    public String getIs_previously_street_vendor() {
        return is_previously_street_vendor;
    }

    public void setIs_previously_street_vendor(String is_previously_street_vendor) {
        this.is_previously_street_vendor = is_previously_street_vendor;
    }

    public String getType_of_structure() {
        return type_of_structure;
    }

    public void setType_of_structure(String type_of_structure) {
        this.type_of_structure = type_of_structure;
    }

    public String getNo_of_days_Active() {
        return no_of_days_Active;
    }

    public void setNo_of_days_Active(String no_of_days_Active) {
        this.no_of_days_Active = no_of_days_Active;
    }

    public String getStart_date_of_vending() {
        return start_date_of_vending;
    }

    public void setStart_date_of_vending(String start_date_of_vending) {
        this.start_date_of_vending = start_date_of_vending;
    }

    public String getIs_tehbajari_document() {
        return is_tehbajari_document;
    }

    public void setIs_tehbajari_document(String is_tehbajari_document) {
        this.is_tehbajari_document = is_tehbajari_document;
    }

    public String getChoice_of_vending_area_market() {
        return choice_of_vending_area_market;
    }

    public void setChoice_of_vending_area_market(String choice_of_vending_area_market) {
        this.choice_of_vending_area_market = choice_of_vending_area_market;
    }

    public String getIdentity_proof_type() {
        return identity_proof_type;
    }

    public void setIdentity_proof_type(String identity_proof_type) {
        this.identity_proof_type = identity_proof_type;
    }

    public String getIdentity_proof_front() {
        return identity_proof_front;
    }

    public void setIdentity_proof_front(String identity_proof_front) {
        this.identity_proof_front = identity_proof_front;
    }

    public String getIdentity_proof_back() {
        return identity_proof_back;
    }

    public void setIdentity_proof_back(String identity_proof_back) {
        this.identity_proof_back = identity_proof_back;
    }

    public String getVending_history_proof_type() {
        return vending_history_proof_type;
    }

    public void setVending_history_proof_type(String vending_history_proof_type) {
        this.vending_history_proof_type = vending_history_proof_type;
    }

    public String getVending_history_proof_front() {
        return vending_history_proof_front;
    }

    public void setVending_history_proof_front(String vending_history_proof_front) {
        this.vending_history_proof_front = vending_history_proof_front;
    }

    public String getVending_history_proof_back() {
        return vending_history_proof_back;
    }

    public void setVending_history_proof_back(String vending_history_proof_back) {
        this.vending_history_proof_back = vending_history_proof_back;
    }

    public String getAllotment_tehzabari_document() {
        return allotment_tehzabari_document;
    }

    public void setAllotment_tehzabari_document(String allotment_tehzabari_document) {
        this.allotment_tehzabari_document = allotment_tehzabari_document;
    }

    public String getUndertaking_doc() {
        return undertaking_doc;
    }

    public void setUndertaking_doc(String undertaking_doc) {
        this.undertaking_doc = undertaking_doc;
    }

    public String getAcknowledgement_doc() {
        return acknowledgement_doc;
    }

    public void setAcknowledgement_doc(String acknowledgement_doc) {
        this.acknowledgement_doc = acknowledgement_doc;
    }

    public String getOther_doc_type() {
        return other_doc_type;
    }

    public void setOther_doc_type(String other_doc_type) {
        this.other_doc_type = other_doc_type;
    }

    public String getOther_document_url() {
        return other_document_url;
    }

    public void setOther_document_url(String other_document_url) {
        this.other_document_url = other_document_url;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getRecording() {
        return recording;
    }

    public void setRecording(String recording) {
        this.recording = recording;
    }
}
