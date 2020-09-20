package com.example.streethawkerssurveyapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.example.streethawkerssurveyapp.R;
import com.example.streethawkerssurveyapp.response_pack.SurveyResponse;
import com.example.streethawkerssurveyapp.response_pack.UpdateSurveyResponse;
import com.example.streethawkerssurveyapp.services.AudioRecordService;
import com.example.streethawkerssurveyapp.services_pack.ApiInterface;
import com.example.streethawkerssurveyapp.services_pack.ApiService;
import com.example.streethawkerssurveyapp.services_pack.ApplicationConstant;
import com.example.streethawkerssurveyapp.services_pack.CustomProgressDialog;
import com.example.streethawkerssurveyapp.utils.PrefUtils;

import java.util.HashMap;
import java.util.Map;

public class CorporationZoneActivity extends MainActivity {

    private Spinner mSpinnerCorporations;
    private Spinner mSpinnerZones;
    private EditText mEditWard;
    private EditText mEditArea;
    private RelativeLayout mRelative_buttons;
    private Button mBtnNext;
    private Button mBtnPrevious;

    private String
            CORPORATION = "",
            ZONE = "",
            WARD = "",
            AREA = "";

    private String SCANRESULT="";
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corporation_zone);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle(R.string.URI+ApplicationConstant.SurveyId);

        bindView();

        try {
            SCANRESULT =  getIntent().getExtras().getString("SCANRESULT");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(CorporationZoneActivity.this, AudioRecordService.class);
        intent.putExtra("FILE", ApplicationConstant.SurveyId);
        startService(intent);

        mSpinnerCorporations.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                CORPORATION = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mSpinnerZones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ZONE = parent.getItemAtPosition(position).toString().trim().split("-")[0].trim();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    if (CORPORATION.trim().contains("ED-East Delhi Municipal Corporation")) {
                        CORPORATION = "ED";
                    } else if (CORPORATION.trim().contains("SD-South Delhi Municipal Corporation")) {
                        CORPORATION = "SD";
                    }else
                    if (CORPORATION.trim().contains("ND-North Delhi Municipal Corporation")) {
                        CORPORATION = "ND";
                    }else
                    if (CORPORATION.trim().contains("NC-New Delhi Municipal Council")) {
                        CORPORATION = "NC";
                    } else {
                        CORPORATION = "CN";
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                WARD = mEditWard.getText().toString().trim();
                AREA = mEditArea.getText().toString().trim();

                if (validate()) {

                    SendCorporationDetails();

                }

            }
        });


    }

    private boolean validate() {
        if (!ApplicationConstant.isNetworkAvailable(CorporationZoneActivity.this)) {

            ApplicationConstant.displayMessageDialog(CorporationZoneActivity.this, "No Internet Connection", "Please enable internet connection first to proceed");

            return false;
        } else if (mSpinnerCorporations.getSelectedItem().toString().isEmpty()) {
            mSpinnerCorporations.requestFocus();

            return false;

        } else if (mSpinnerZones.getSelectedItem().toString().isEmpty()) {
            mSpinnerZones.requestFocus();
            return false;

        } else if (mEditWard.getText().toString().trim().isEmpty()) {
            mEditWard.setError("Enter Ward Name");
            mEditWard.requestFocus();
            return false;

        } else if (mEditArea.getText().toString().trim().isEmpty()) {
            mEditArea.setError("Enter Area Name");
            mEditArea.requestFocus();
            return false;

        }

        return true;

    }

    private void bindView() {
        mSpinnerCorporations = (Spinner) findViewById(R.id.SpinnerCorporations);
        mSpinnerZones = (Spinner) findViewById(R.id.SpinnerZones);
        mEditWard = (EditText) findViewById(R.id.EditWard);
        mEditArea = (EditText) findViewById(R.id.EditArea);
        mRelative_buttons = (RelativeLayout) findViewById(R.id.relative_buttons);
        mBtnNext = (Button) findViewById(R.id.BtnNext);
        mBtnPrevious = (Button) findViewById(R.id.BtnPrevious);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    private void SendCorporationDetails() {

        progressDialog = CustomProgressDialog.getDialogue(CorporationZoneActivity.this);
        progressDialog.show();

        String UNiq_Id =  PrefUtils.getFromPrefs(CorporationZoneActivity.this, ApplicationConstant.URI_NO_,"");

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + PrefUtils.getFromPrefs(CorporationZoneActivity.this, ApplicationConstant.USERDETAILS.API_KEY, ""));

        ApiInterface apiservice = ApiService.getApiClient().create(ApiInterface.class);
        Call<UpdateSurveyResponse> call = apiservice.SendCorporationDetails(headers,UNiq_Id,CORPORATION,ZONE,WARD,AREA,SCANRESULT);

        call.enqueue(new Callback<UpdateSurveyResponse>() {
            @Override
            public void onResponse(Call<UpdateSurveyResponse> call, Response<UpdateSurveyResponse> response) {

                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();

                if (response.body() != null) {

                    if (response.body().isStatus()) {

                        PrefUtils.saveToPrefs(CorporationZoneActivity.this,ApplicationConstant.CORPORATION,CORPORATION);
                        PrefUtils.saveToPrefs(CorporationZoneActivity.this,ApplicationConstant.ZONE,ZONE);
                        PrefUtils.saveToPrefs(CorporationZoneActivity.this,ApplicationConstant.WARD,WARD);

                        startActivity(new Intent(CorporationZoneActivity.this,PersonalDetailsActivity.class));

                    } else {

                        ApplicationConstant.displayMessageDialog(CorporationZoneActivity.this,
                                "Response",
                                response.body().getMessage());
                    }

                }else {

                    try {
                        ApplicationConstant.displayMessageDialog(CorporationZoneActivity.this,
                                "Response",
                                response.errorBody().string());
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<UpdateSurveyResponse> call, Throwable t) {

                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();
                ApplicationConstant.displayMessageDialog(CorporationZoneActivity.this, "Response", getString(R.string.net_speed_problem));

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        stopService(new Intent(CorporationZoneActivity.this, AudioRecordService.class));

    }
}
