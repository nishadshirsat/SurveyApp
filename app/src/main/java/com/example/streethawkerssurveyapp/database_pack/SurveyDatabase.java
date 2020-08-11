package com.example.streethawkerssurveyapp.database_pack;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import io.sentry.core.protocol.User;

@Database(entities = {PersonalDetails.class,
        FamilyDetails.class,
        VendingDetails.class,
        BankingDetails.class,
        DocumentsData.class},version = 1)
public abstract class SurveyDatabase extends RoomDatabase {

    public abstract SurveyDao surveyDao();

    private static SurveyDatabase surveyDatabase;

   public static SurveyDatabase getDatabase(final Context context){
        if (surveyDatabase == null){
            surveyDatabase = Room.databaseBuilder(context.getApplicationContext(),
                    SurveyDatabase.class,"survey_database")
                    .build();
        }

        return surveyDatabase;
    }
}
