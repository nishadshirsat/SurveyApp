package com.example.streethawkerssurveyapp.database_pack;


import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "landassetesdata")
public class LandDetails {

    @PrimaryKey
    @NonNull
    private String land_id;

    private String survey_id;

    private String area;

    private String house_size;

    private String kucchha;

    private String plot;

    private String rental_income;

}
