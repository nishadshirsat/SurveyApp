package com.example.streethawkerssurveyapp.local_storage_pack.activities;

import android.os.AsyncTask;
import android.os.Bundle;

import com.example.streethawkerssurveyapp.R;
import com.example.streethawkerssurveyapp.database_pack.PersonalDetails;
import com.example.streethawkerssurveyapp.database_pack.SurveyDao;
import com.example.streethawkerssurveyapp.database_pack.SurveyDatabase;
import com.example.streethawkerssurveyapp.local_storage_pack.adapters.ViewLocalSurveyAdapter;
import com.example.streethawkerssurveyapp.services_pack.ApplicationConstant;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

public class LocalSurveyListActivity extends AppCompatActivity {

    private androidx.recyclerview.widget.RecyclerView mRecycler_view;

    private  ViewLocalSurveyAdapter viewSurveyAdapter;
    private SurveyDatabase surveyDatabase;
    private SurveyDao surveyDao;
    private List<PersonalDetails> allSurveys = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_surveylist);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Local Survey List");

        surveyDatabase = SurveyDatabase.getDatabase(LocalSurveyListActivity.this);
        surveyDao = surveyDatabase.surveyDao();

        mRecycler_view = findViewById(R.id.recycler_view);


        mRecycler_view.setLayoutManager(new LinearLayoutManager(this));
        viewSurveyAdapter = new ViewLocalSurveyAdapter(this);
        mRecycler_view.setAdapter(viewSurveyAdapter);

        getAllRoomData();


    }

    public void getAllRoomData(){

       new InsertGetDataTask(surveyDao).execute();

    }

    private class InsertGetDataTask extends AsyncTask<Void, Void, List<PersonalDetails>> {
        SurveyDao surveyDao;

        public InsertGetDataTask(SurveyDao surveyDao) {
            this.surveyDao = surveyDao;
        }

        @Override
        protected List<PersonalDetails> doInBackground(Void... voids) {
            List<PersonalDetails> allData = surveyDao.getAllPersonalData();
            return allData;
        }

        @Override
        protected void onPostExecute(List<PersonalDetails> personalDetails) {
            viewSurveyAdapter.setList(personalDetails);

//            super.onPostExecute(personalDetails);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
