package com.example.streethawkerssurveyapp.database_pack;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.sentry.core.protocol.User;

@Dao
public interface SurveyDao {

//   @Insert(onConflict = OnConflictStrategy.REPLACE)
//   void insert(User user);

//   @Insert(onConflict = OnConflictStrategy.REPLACE)
//   void insertPageDetails(PageDetail pageDetail);
//
//   @Query("SELECT * FROM users WHERE page_no= :page_no")
//   LiveData<List<User>> getAllUsers(String page_no);
//
//   @Query("SELECT * FROM pageinfo")
//   LiveData<PageDetail> getAllDetails();
}
