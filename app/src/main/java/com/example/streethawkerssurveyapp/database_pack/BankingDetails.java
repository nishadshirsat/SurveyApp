package com.example.streethawkerssurveyapp.database_pack;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "bankingdata")
public class BankingDetails {


    @PrimaryKey
    @NonNull
    private String survey_id;

    //BankDetails
    private String bank_account_number;

    private String bank_name;

    private String branch_name;

    private String ifsc_code;

    public BankingDetails(@NonNull String survey_id,
                          String bank_account_number,
                          String bank_name,
                          String branch_name,
                          String ifsc_code) {
        this.survey_id = survey_id;
        this.bank_account_number = bank_account_number;
        this.bank_name = bank_name;
        this.branch_name = branch_name;
        this.ifsc_code = ifsc_code;
    }

    @NonNull
    public String getSurvey_id() {
        return survey_id;
    }

    public void setSurvey_id(@NonNull String survey_id) {
        this.survey_id = survey_id;
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
}
