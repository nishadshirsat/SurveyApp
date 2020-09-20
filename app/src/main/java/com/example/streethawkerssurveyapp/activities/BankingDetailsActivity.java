package com.example.streethawkerssurveyapp.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.streethawkerssurveyapp.R;
import com.example.streethawkerssurveyapp.database_pack.BankingDetails;
import com.example.streethawkerssurveyapp.database_pack.SurveyDao;
import com.example.streethawkerssurveyapp.database_pack.SurveyDatabase;
import com.example.streethawkerssurveyapp.database_pack.VendingDetails;
import com.example.streethawkerssurveyapp.response_pack.UpdateSurveyResponse;
import com.example.streethawkerssurveyapp.services_pack.ApiInterface;
import com.example.streethawkerssurveyapp.services_pack.ApiService;
import com.example.streethawkerssurveyapp.services_pack.ApplicationConstant;
import com.example.streethawkerssurveyapp.services_pack.CustomProgressDialog;
import com.example.streethawkerssurveyapp.utils.PrefUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BankingDetailsActivity extends MainActivity {

    private LinearLayout mLinearMain;
    private LinearLayout mLinearHead;
    private LinearLayout mLinearOne;
    private EditText mEditAccNo;
    private EditText mEditBankName;
    private EditText mEditBranchName;
    private EditText mEditIfscCode;
    private LinearLayout mLinearTwo;
    private RelativeLayout mRelative_buttons;
    private Button mBtnNext;
    private Button mBtnPrevious;

    private String   BANKACC_NO = "",
            BANKNAME = "",
            BRANCH_NAME = "",
            IFSC = "";

    private SurveyDatabase surveyDatabase;
    private SurveyDao surveyDao;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_details);
        bindViews();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        if (!ApplicationConstant.ISLOCALDB) {
            setTitle(R.string.URI+ApplicationConstant.SurveyId);

        }

        surveyDatabase = SurveyDatabase.getDatabase(BankingDetailsActivity.this);
        surveyDao = surveyDatabase.surveyDao();

        mBtnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             onBackPressed();

            }
        });


        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mLinearHead.getVisibility() == View.VISIBLE) {

                    mLinearHead.setVisibility(View.GONE);
                    mLinearOne.setVisibility(View.VISIBLE);

                    mBtnNext.setText("Submit");

                }else   if (mLinearOne.getVisibility() == View.VISIBLE) {

                    BRANCH_NAME = mEditBranchName.getText().toString().trim();
                    BANKACC_NO = mEditAccNo.getText().toString().trim();
                    BANKNAME = mEditBankName.getText().toString().trim();
                    IFSC = mEditIfscCode.getText().toString().trim();

                    if (validate()){

                        if (ApplicationConstant.ISLOCALDB) {

                            insertBankingDetails();

                        } else if (!ApplicationConstant.isNetworkAvailable(BankingDetailsActivity.this)) {

                            ApplicationConstant.displayMessageDialog(BankingDetailsActivity.this, "No Internet Connection", "Please enable internet connection first to proceed");

                        } else {
                            UploadBankDetailsSurvey();

                        }

                    }

                }
            }
        });

    }

    private boolean validate() {

//        if (mEditAccNo.getText().toString().trim().isEmpty()) {
//            mEditAccNo.setError("Enter Account No");
//            mEditAccNo.requestFocus();
//            return false;
//        } else if (mEditBankName.getText().toString().trim().isEmpty()) {
//            mEditBankName.setError("Enter Bank Name");
//            mEditBankName.requestFocus();
//            return false;
//        } else if (mEditBranchName.getText().toString().trim().isEmpty()) {
//            mEditBranchName.setError("Enter Branch Name");
//            mEditBranchName.requestFocus();
//            return false;
//        } else if (mEditIfscCode.getText().toString().trim().isEmpty()) {
//            mEditIfscCode.setError("Enter IFSC Code");
//            mEditIfscCode.requestFocus();
//            return false;
//        }

        return true;
    }

    private void bindViews() {

        mLinearMain = (LinearLayout) findViewById(R.id.LinearMain);
        mLinearHead = (LinearLayout) findViewById(R.id.LinearHead);
        mLinearOne = (LinearLayout) findViewById(R.id.LinearOne);
        mEditAccNo = (EditText) findViewById(R.id.EditAccNo);
        mEditBankName = (EditText) findViewById(R.id.EditBankName);
        mEditBranchName = (EditText) findViewById(R.id.EditBranchName);
        mEditIfscCode = (EditText) findViewById(R.id.EditIfscCode);
        mLinearTwo = (LinearLayout) findViewById(R.id.LinearTwo);
        mRelative_buttons = (RelativeLayout) findViewById(R.id.relative_buttons);
        mBtnNext = (Button) findViewById(R.id.BtnNext);
        mBtnPrevious = (Button) findViewById(R.id.BtnPrevious);
    }


    private void UploadBankDetailsSurvey() {

      ProgressDialog progressDialog = CustomProgressDialog.getDialogue(BankingDetailsActivity.this);
        progressDialog.show();

        String UNiq_Id = "";

        UNiq_Id = PrefUtils.getFromPrefs(BankingDetailsActivity.this, ApplicationConstant.URI_NO_, "");

        String CORPORATION =   PrefUtils.getFromPrefs(BankingDetailsActivity.this,ApplicationConstant.CORPORATION,"");
        String ZONE =  PrefUtils.getFromPrefs(BankingDetailsActivity.this,ApplicationConstant.ZONE,"");
        String WARD =  PrefUtils.getFromPrefs(BankingDetailsActivity.this,ApplicationConstant.WARD,"");

        RequestBody CORPORATION_ = RequestBody.create(MediaType.parse("multipart/form-data"), CORPORATION);
        RequestBody ZONE_ = RequestBody.create(MediaType.parse("multipart/form-data"), ZONE);
        RequestBody WARD_ = RequestBody.create(MediaType.parse("multipart/form-data"), WARD);




        RequestBody SURVEY_ID_ = RequestBody.create(MediaType.parse("multipart/form-data"), UNiq_Id);

        RequestBody BANKACC_NO_ = RequestBody.create(MediaType.parse("multipart/form-data"), BANKACC_NO);
        RequestBody BANKNAME_ = RequestBody.create(MediaType.parse("multipart/form-data"), BANKNAME);
        RequestBody BRANCH_NAME_ = RequestBody.create(MediaType.parse("multipart/form-data"), BRANCH_NAME);
        RequestBody IFSC_ = RequestBody.create(MediaType.parse("multipart/form-data"), IFSC);

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + PrefUtils.getFromPrefs(BankingDetailsActivity.this, ApplicationConstant.USERDETAILS.API_KEY, ""));

        ApiInterface apiservice = ApiService.getApiClient().create(ApiInterface.class);
        Call<UpdateSurveyResponse> call = apiservice.BankDetailsSurvey(headers,
                SURVEY_ID_,
                CORPORATION_,
                ZONE_,
                WARD_,
                BANKACC_NO_,
                BANKNAME_,
                BRANCH_NAME_,
                IFSC_
        );

        call.enqueue(new Callback<UpdateSurveyResponse>() {
            @Override
            public void onResponse(Call<UpdateSurveyResponse> call, Response<UpdateSurveyResponse> response) {

                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();

                if (response.body() != null) {

                    if (response.body().isStatus()) {


                        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(BankingDetailsActivity.this);
                        builder.setTitle(R.string.banking);
                        builder.setMessage(R.string.saved);
                        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                startActivity(new Intent(BankingDetailsActivity.this, DocumentsScanActivity.class));

                            }
                        });

                        AlertDialog alertDialog = builder.create();
                        alertDialog.setCancelable(false);
                        alertDialog.setCanceledOnTouchOutside(false);
                        alertDialog.show();


//                        ApplicationConstant.displayToastMessage(BankingDetailsActivity.this,
//                                "Personal Details saved successfully");


                    } else {

                        ApplicationConstant.displayErrorMessage(BankingDetailsActivity.this,
                                response.body().getErrorCode().trim());
                    }

                } else {

                    try {
                        ApplicationConstant.displayMessageDialog(BankingDetailsActivity.this,
                                "Response",
                                response.errorBody().string());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<UpdateSurveyResponse> call, Throwable t) {

                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();
                ApplicationConstant.displayMessageDialog(BankingDetailsActivity.this, "Response", getString(R.string.net_speed_problem));

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {

        if (mLinearOne.getVisibility() == View.VISIBLE) {

            mLinearHead.setVisibility(View.VISIBLE);
            mLinearOne.setVisibility(View.GONE);
            mBtnNext.setText("Next");


        }else if (mLinearHead.getVisibility() == View.VISIBLE) {

            super.onBackPressed();

        }
    }



    public void insertBankingDetails() {


        String LocalId  = PrefUtils.getFromPrefs(BankingDetailsActivity.this,ApplicationConstant.LOCAL_SURVEYID,"");

        BankingDetails bankingDetails = new BankingDetails(
                LocalId,
                BANKACC_NO,
                BANKNAME,
                BRANCH_NAME,
                IFSC);

        new InsertAsyncTask(surveyDao).execute(bankingDetails);

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(BankingDetailsActivity.this);
        builder.setTitle("Banking Details");
        builder.setMessage("Survey saved locally");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

                startActivity(new Intent(BankingDetailsActivity.this, DocumentsScanActivity.class));

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }

    private class InsertAsyncTask extends AsyncTask<BankingDetails, Void, Void> {
        SurveyDao surveyDao;

        public InsertAsyncTask(SurveyDao surveyDao) {
            this.surveyDao = surveyDao;
        }

        @Override
        protected Void doInBackground(BankingDetails... bankingDetails) {
            surveyDao.insertBankingDetails(bankingDetails[0]);
            return null;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.second, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.home_menu:

                AlertDialog.Builder builder =   builder = new AlertDialog.Builder(this);
                builder.setMessage("Do you want to exit this survey ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                                startActivity(new Intent(BankingDetailsActivity.this, DashboardActivity.class));
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                            }
                        });
                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
