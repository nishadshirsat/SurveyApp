package com.example.streethawkerssurveyapp.pending_survey.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.streethawkerssurveyapp.R;
import com.example.streethawkerssurveyapp.activities.DashboardActivity;
import com.example.streethawkerssurveyapp.activities.DocumentsScanActivity;
import com.example.streethawkerssurveyapp.activities.PersonalDetailsActivity;
import com.example.streethawkerssurveyapp.response_pack.UpdateSurveyResponse;
import com.example.streethawkerssurveyapp.services_pack.ApiInterface;
import com.example.streethawkerssurveyapp.services_pack.ApiService;
import com.example.streethawkerssurveyapp.services_pack.ApplicationConstant;
import com.example.streethawkerssurveyapp.services_pack.CustomProgressDialog;
import com.example.streethawkerssurveyapp.utils.PrefUtils;
import com.example.streethawkerssurveyapp.view_survey.response_pojo.SingleSurveyDetails;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PendingBankingDetailsActivity extends AppCompatActivity {

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

    private SingleSurveyDetails SingleSurveyData;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_details);
        bindViews();

        mBtnNext.setVisibility(View.GONE);


        SingleSurveyData = (SingleSurveyDetails) getIntent().getSerializableExtra("SurveyData");

        ApplicationConstant.SurveyId = PrefUtils.getFromPrefs(PendingBankingDetailsActivity.this, ApplicationConstant.SURVEY_ID, "");


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("URI NO: "+ApplicationConstant.SurveyId);


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

                }else   if (mLinearOne.getVisibility() == View.VISIBLE) {

                    BRANCH_NAME = mEditBranchName.getText().toString().trim();
                    BANKACC_NO = mEditAccNo.getText().toString().trim();
                    BANKNAME = mEditBankName.getText().toString().trim();
                    IFSC = mEditIfscCode.getText().toString().trim();

                    if (validate()){
                        UploadBankDetailsSurvey();
                    }

                }
            }
        });

        setBankingData();

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

      ProgressDialog progressDialog = CustomProgressDialog.getDialogue(PendingBankingDetailsActivity.this);
        progressDialog.show();

        String UNiq_Id = "";

        UNiq_Id = PrefUtils.getFromPrefs(PendingBankingDetailsActivity.this, ApplicationConstant.URI_NO_, "");

        String CORPORATION =   PrefUtils.getFromPrefs(PendingBankingDetailsActivity.this,ApplicationConstant.CORPORATION,"");
        String ZONE =  PrefUtils.getFromPrefs(PendingBankingDetailsActivity.this,ApplicationConstant.ZONE,"");
        String WARD =  PrefUtils.getFromPrefs(PendingBankingDetailsActivity.this,ApplicationConstant.WARD,"");

        RequestBody CORPORATION_ = RequestBody.create(MediaType.parse("multipart/form-data"), CORPORATION);
        RequestBody ZONE_ = RequestBody.create(MediaType.parse("multipart/form-data"), ZONE);
        RequestBody WARD_ = RequestBody.create(MediaType.parse("multipart/form-data"), WARD);




        RequestBody SURVEY_ID_ = RequestBody.create(MediaType.parse("multipart/form-data"), UNiq_Id);

        RequestBody BANKACC_NO_ = RequestBody.create(MediaType.parse("multipart/form-data"), BANKACC_NO);
        RequestBody BANKNAME_ = RequestBody.create(MediaType.parse("multipart/form-data"), BANKNAME);
        RequestBody BRANCH_NAME_ = RequestBody.create(MediaType.parse("multipart/form-data"), BRANCH_NAME);
        RequestBody IFSC_ = RequestBody.create(MediaType.parse("multipart/form-data"), IFSC);

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + PrefUtils.getFromPrefs(PendingBankingDetailsActivity.this, ApplicationConstant.USERDETAILS.API_KEY, ""));

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


                        AlertDialog.Builder builder = new AlertDialog.Builder(PendingBankingDetailsActivity.this);
                        builder.setTitle("Banking Details");
                        builder.setMessage("Saved successfully");
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();

                                Intent intent = new Intent(PendingBankingDetailsActivity.this, PendingDocumentsScanActivity.class);
                                intent.putExtra("SurveyData",SingleSurveyData);
                                startActivity(intent);
                            }
                        });

                        AlertDialog alertDialog = builder.create();
                        alertDialog.setCancelable(false);
                        alertDialog.setCanceledOnTouchOutside(false);
                        alertDialog.show();


//                        ApplicationConstant.displayToastMessage(BankingDetailsActivity.this,
//                                "Personal Details saved successfully");


                    } else {

                        ApplicationConstant.displayErrorMessage(PendingBankingDetailsActivity.this,
                                response.body().getErrorCode().trim());
                    }

                } else {

                    try {
                        ApplicationConstant.displayMessageDialog(PendingBankingDetailsActivity.this,
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
                ApplicationConstant.displayMessageDialog(PendingBankingDetailsActivity.this, "Response", getString(R.string.net_speed_problem));

            }
        });
    }

    private void setBankingData() {

        mEditBankName.setText(SingleSurveyData.getBankName());
        mEditBranchName.setText(SingleSurveyData.getBankBranchName());
        mEditAccNo.setText(SingleSurveyData.getBankAccountNumber());
        mEditIfscCode.setText(SingleSurveyData.getBankIfsc());

        mBtnNext.setVisibility(View.VISIBLE);

    }

    @Override
    public void onBackPressed() {

        if (mLinearOne.getVisibility() == View.VISIBLE) {

            mLinearHead.setVisibility(View.VISIBLE);
            mLinearOne.setVisibility(View.GONE);

        }else if (mLinearHead.getVisibility() == View.VISIBLE) {

            super.onBackPressed();

        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
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
                                startActivity(new Intent(PendingBankingDetailsActivity.this, DashboardActivity.class));
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
