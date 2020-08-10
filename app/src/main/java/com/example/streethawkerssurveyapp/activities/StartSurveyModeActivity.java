package com.example.streethawkerssurveyapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.streethawkerssurveyapp.R;
import com.example.streethawkerssurveyapp.response_pack.SurveyDetailsResponse;
import com.example.streethawkerssurveyapp.response_pack.SurveyResponse;
import com.example.streethawkerssurveyapp.response_pack.UpdateSurveyResponse;
import com.example.streethawkerssurveyapp.services_pack.ApiInterface;
import com.example.streethawkerssurveyapp.services_pack.ApiService;
import com.example.streethawkerssurveyapp.services_pack.ApplicationConstant;
import com.example.streethawkerssurveyapp.services_pack.CustomProgressDialog;
import com.example.streethawkerssurveyapp.utils.PrefUtils;

import java.util.HashMap;
import java.util.Map;

public class StartSurveyModeActivity extends AppCompatActivity {

    private androidx.cardview.widget.CardView mCardStartSurvey;
    private androidx.cardview.widget.CardView mCardScanCode;


    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Start Survey");

        bindViews();

        mCardStartSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!ApplicationConstant.isNetworkAvailable(StartSurveyModeActivity.this)) {

                    ApplicationConstant.ISLOCALDB = true;

                    ApplicationConstant.displayToastMessage(StartSurveyModeActivity.this,  "No Internet Connection! Storing survey in local Database now.");

                    startActivity(new Intent(StartSurveyModeActivity.this,PersonalDetailsActivity.class));


                }else {
                    GetSurveyUri("START");

                }
            }
        });

        mCardScanCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!ApplicationConstant.isNetworkAvailable(StartSurveyModeActivity.this)) {

                    ApplicationConstant.ISLOCALDB = true;

                    ApplicationConstant.displayToastMessage(StartSurveyModeActivity.this,  "No Internet Connection! Storing survey in local Database now.");

                    startActivity(new Intent(StartSurveyModeActivity.this,ScannedBarcodeActivity.class));


                }else {
                    GetSurveyUri("SCAN");

                }

            }
        });


    }

    private void bindViews() {

        mCardStartSurvey = (androidx.cardview.widget.CardView) findViewById(R.id.CardStartSurvey);
        mCardScanCode = (androidx.cardview.widget.CardView) findViewById(R.id.CardScanCode);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

//    private void UpdateSurvey() {
//
//        progressDialog = CustomProgressDialog.getDialogue(StartSurveyModeActivity.this);
//        progressDialog.show();
//
//        String UNiq_Id =  PrefUtils.getFromPrefs(StartSurveyModeActivity.this, ApplicationConstant.URI_NO_,"");
//
//        Map<String, String> headers = new HashMap<>();
//        headers.put("Authorization", "Bearer " + PrefUtils.getFromPrefs(StartSurveyModeActivity.this, ApplicationConstant.USERDETAILS.API_KEY, ""));
//
//        ApiInterface apiservice = ApiService.getApiClient().create(ApiInterface.class);
//        Call<SurveyDetailsResponse> call = apiservice.getSurveyDetails(headers);
//
//        call.enqueue(new Callback<SurveyDetailsResponse>() {
//            @Override
//            public void onResponse(Call<SurveyDetailsResponse> call, Response<SurveyDetailsResponse> response) {
//
//                if (progressDialog != null && progressDialog.isShowing())
//                    progressDialog.dismiss();
//
//                if (response.body() != null) {
//
//                    if (response.body().isStatus()) {
//                        PrefUtils.saveToPrefs(StartSurveyModeActivity.this, ApplicationConstant.SURVEY_ID, response.body().getData().get(0).getSurveyId());
//
//                    } else {
//
//                        ApplicationConstant.displayMessageDialog(StartSurveyModeActivity.this,
//                                "Response",
//                                String.valueOf(response.body().isStatus()));
//                    }
//
//                }else {
//
//                    try {
//                        ApplicationConstant.displayMessageDialog(StartSurveyModeActivity.this,
//                                "Response",
//                                response.errorBody().string());
//                    }catch (Exception e){
//                        e.printStackTrace();
//                    }
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<SurveyDetailsResponse> call, Throwable t) {
//
//                if (progressDialog != null && progressDialog.isShowing())
//                    progressDialog.dismiss();
//                ApplicationConstant.displayMessageDialog(StartSurveyModeActivity.this, "Response", t.getMessage().toString());
//
//            }
//        });
//    }


    private void GetSurveyUri(String Control) {

        progressDialog = CustomProgressDialog.getDialogue(StartSurveyModeActivity.this);
        progressDialog.show();

//        String UNiq_Id =  PrefUtils.getFromPrefs(StartSurveyModeActivity.this, ApplicationConstant.URI_NO_,"");

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + PrefUtils.getFromPrefs(StartSurveyModeActivity.this, ApplicationConstant.USERDETAILS.API_KEY, ""));

        ApiInterface apiservice = ApiService.getApiClient().create(ApiInterface.class);
        Call<SurveyResponse> call = apiservice.getSurveyUriNumber(headers,"");

        call.enqueue(new Callback<SurveyResponse>() {
            @Override
            public void onResponse(Call<SurveyResponse> call, Response<SurveyResponse> response) {

                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();

                if (response.body() != null) {

                    if (response.body().isStatus()) {
                        PrefUtils.saveToPrefs(StartSurveyModeActivity.this, ApplicationConstant.SURVEY_ID, response.body().getUriNumber());
                        ApplicationConstant.SurveyId = response.body().getUriNumber();
                        PrefUtils.saveToPrefs(StartSurveyModeActivity.this, ApplicationConstant.URI_NO_, response.body().getUriNumber());

                        if (Control.trim().equals("START")){
                            startActivity(new Intent(StartSurveyModeActivity.this,PersonalDetailsActivity.class));

                        }else {
                            startActivity(new Intent(StartSurveyModeActivity.this,ScannedBarcodeActivity.class));

                        }



                    } else {

                        ApplicationConstant.displayMessageDialog(StartSurveyModeActivity.this,
                                "Response",
                                String.valueOf(response.body().isStatus()));
                    }

                }else {

                    try {
                        ApplicationConstant.displayMessageDialog(StartSurveyModeActivity.this,
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
                ApplicationConstant.displayMessageDialog(StartSurveyModeActivity.this, "Response", getString(R.string.net_speed_problem));

            }
        });
    }


}
