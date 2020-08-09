package com.example.streethawkerssurveyapp.database_pack;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "familydata")
public class FamilyDetails {

    @PrimaryKey
    @NonNull
    private String family_id;

    private String survey_id;

    private String family_member_relationship;

    private String family_member_name;

    private String family_member_adhaar;

    private String family_member_age;

}
