package com.example.streethawkerssurveyapp.database_pack;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.sentry.core.protocol.User;

@Dao
public interface SurveyDao {

    //insert service
   @Insert(onConflict = OnConflictStrategy.REPLACE)
   void insertPersonalDetails(PersonalDetails personalDetails);

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   void insertFamilyDetails(FamilyDetails familyDetails);

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   void insertVendingDetails(VendingDetails vendingDetails);

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   void insertBankingDetails(BankingDetails bankingDetails);

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   void insertDocumentsDetails(DocumentsData documentsData);

//   @Query("SELECT * FROM personaldata WHERE survey_id= :survey_id")
//   List<PersonalDetails> getAllPersonalData(String survey_id);

   @Query("SELECT * FROM personaldata")
   List<PersonalDetails> getAllPersonalData();

   @Query("SELECT * FROM familydata WHERE survey_id= :survey_id")
   List<FamilyDetails> getAllFamilyData(String survey_id);

   @Query("SELECT * FROM vendingdata WHERE survey_id= :survey_id")
   List<VendingDetails> getAllVendingData(String survey_id);

   @Query("SELECT * FROM bankingdata WHERE survey_id= :survey_id")
   List<BankingDetails> getAllBankingData(String survey_id);

   @Query("SELECT * FROM documentsdata WHERE survey_id= :survey_id")
   List<DocumentsData> getAllDocumentsData(String survey_id);

   @Delete
   void deletePersonalDetailsRecord(PersonalDetails personalDetails);


//
//   @Query("SELECT * FROM pageinfo")
//   LiveData<PageDetail> getAllDetails();
}
