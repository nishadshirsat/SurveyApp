package com.example.streethawkerssurveyapp.database_pack;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "vendingdata")
public class VendingDetails {

    @PrimaryKey
    @NonNull
    private String survey_id;

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

    public VendingDetails(@NonNull String survey_id,
                          String type_of_vending,
                          String name_of_vending,
                          String timing_of_vending_from,
                          String timing_of_vending_to,
                          String timing_of_vending_from_1,
                          String timing_of_vending_to_1,
                          String years_of_vending,
                          String is_previously_street_vendor,
                          String type_of_structure,
                          String no_of_days_Active,
                          String start_date_of_vending,
                          String is_tehbajari_document,
                          String choice_of_vending_area_market) {
        this.survey_id = survey_id;
        this.type_of_vending = type_of_vending;
        this.name_of_vending = name_of_vending;
        this.timing_of_vending_from = timing_of_vending_from;
        this.timing_of_vending_to = timing_of_vending_to;
        this.timing_of_vending_from_1 = timing_of_vending_from_1;
        this.timing_of_vending_to_1 = timing_of_vending_to_1;
        this.years_of_vending = years_of_vending;
        this.is_previously_street_vendor = is_previously_street_vendor;
        this.type_of_structure = type_of_structure;
        this.no_of_days_Active = no_of_days_Active;
        this.start_date_of_vending = start_date_of_vending;
        this.is_tehbajari_document = is_tehbajari_document;
        this.choice_of_vending_area_market = choice_of_vending_area_market;
    }

    @NonNull
    public String getSurvey_id() {
        return survey_id;
    }

    public void setSurvey_id(@NonNull String survey_id) {
        this.survey_id = survey_id;
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
}
