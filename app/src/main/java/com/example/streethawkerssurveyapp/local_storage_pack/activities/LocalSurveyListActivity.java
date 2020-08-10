package com.example.streethawkerssurveyapp.local_storage_pack.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.streethawkerssurveyapp.R;
import com.example.streethawkerssurveyapp.activities.PersonalDetailsActivity;
import com.example.streethawkerssurveyapp.activities.ScannedBarcodeActivity;
import com.example.streethawkerssurveyapp.local_storage_pack.activities.LocalSurveyListActivity;
import com.example.streethawkerssurveyapp.database_pack.PersonalDetails;
import com.example.streethawkerssurveyapp.database_pack.SurveyDao;
import com.example.streethawkerssurveyapp.database_pack.SurveyDatabase;
import com.example.streethawkerssurveyapp.local_storage_pack.adapters.ViewLocalSurveyAdapter;
import com.example.streethawkerssurveyapp.response_pack.SurveyResponse;
import com.example.streethawkerssurveyapp.services_pack.ApiInterface;
import com.example.streethawkerssurveyapp.services_pack.ApiService;
import com.example.streethawkerssurveyapp.services_pack.ApplicationConstant;
import com.example.streethawkerssurveyapp.services_pack.CustomProgressDialog;
import com.example.streethawkerssurveyapp.utils.PrefUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocalSurveyListActivity extends AppCompatActivity implements ViewLocalSurveyAdapter.UploadAndRefresh {

    private androidx.recyclerview.widget.RecyclerView mRecycler_view;

    private  ViewLocalSurveyAdapter viewSurveyAdapter;
    private SurveyDatabase surveyDatabase;
    private SurveyDao surveyDao;
    private List<PersonalDetails> allSurveys = new ArrayList<>();

    private ProgressDialog progressDialog;

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

    @Override
    public void uploadData(PersonalDetails surveyData) {




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

    private void GetSurveyUri() {

        progressDialog = CustomProgressDialog.getDialogue(LocalSurveyListActivity.this);
        progressDialog.show();

//        String UNiq_Id =  PrefUtils.getFromPrefs(LocalSurveyListActivity.this, ApplicationConstant.URI_NO_,"");

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + PrefUtils.getFromPrefs(LocalSurveyListActivity.this, ApplicationConstant.USERDETAILS.API_KEY, ""));

        ApiInterface apiservice = ApiService.getApiClient().create(ApiInterface.class);
        Call<SurveyResponse> call = apiservice.getSurveyUriNumber(headers,"");

        call.enqueue(new Callback<SurveyResponse>() {
            @Override
            public void onResponse(Call<SurveyResponse> call, Response<SurveyResponse> response) {

                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();

                if (response.body() != null) {

                    if (response.body().isStatus()) {
                        PrefUtils.saveToPrefs(LocalSurveyListActivity.this, ApplicationConstant.SURVEY_ID, response.body().getUriNumber());
                        ApplicationConstant.SurveyId = response.body().getUriNumber();
                        PrefUtils.saveToPrefs(LocalSurveyListActivity.this, ApplicationConstant.URI_NO_, response.body().getUriNumber());



                    } else {

                        ApplicationConstant.displayMessageDialog(LocalSurveyListActivity.this,
                                "Response",
                                String.valueOf(response.body().isStatus()));
                    }

                }else {

                    try {
                        ApplicationConstant.displayMessageDialog(LocalSurveyListActivity.this,
                                "Response",
                                response.errorBody().string());
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<SurveyResponse> call, Throwable t) {

                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();
                ApplicationConstant.displayMessageDialog(LocalSurveyListActivity.this, "Response", getString(R.string.net_speed_problem));

            }
        });
    }





}
