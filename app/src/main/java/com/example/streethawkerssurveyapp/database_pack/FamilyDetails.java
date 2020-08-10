package com.example.streethawkerssurveyapp.database_pack;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "familydata")
public class FamilyDetails {

    @PrimaryKey
    @NonNull
    private String survey_id;

    //Family Details
    private String family_details_json;

    private String landassets_details_json;

    private String is_family_member_surveyed;

    public FamilyDetails(@NonNull String survey_id,
                         String family_details_json,
                         String landassets_details_json,
                         String is_family_member_surveyed,
                         String json_surveyFam
                         ) {
        this.survey_id = survey_id;
        this.family_details_json = family_details_json;
        this.landassets_details_json = landassets_details_json;
        this.is_family_member_surveyed = is_family_member_surveyed;
        this.json_surveyFam = json_surveyFam;

    }

    private String json_surveyFam;


//    private String family_member_relationship;
//
//    private String family_member_name;
//
//    private String family_member_adhaar;
//
//    private String family_member_age;


    @NonNull
    public String getSurvey_id() {
        return survey_id;
    }

    public void setSurvey_id(@NonNull String survey_id) {
        this.survey_id = survey_id;
    }

    public String getFamily_details_json() {
        return family_details_json;
    }

    public String getJson_surveyFam() {
        return json_surveyFam;
    }

    public void setJson_surveyFam(String json_surveyFam) {
        this.json_surveyFam = json_surveyFam;
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

}
