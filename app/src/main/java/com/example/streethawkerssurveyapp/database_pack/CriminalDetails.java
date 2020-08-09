package com.example.streethawkerssurveyapp.database_pack;


import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "criminaldata")
public class CriminalDetails {

    @PrimaryKey
    @NonNull
    private String criminal_id;

    private String survey_id;

    private String criminal_case_number;

    private String criminal_case_fir_number;

    private String criminal_case_police_name;

    private String criminal_case_date;

    private String criminal_case_status;


}
