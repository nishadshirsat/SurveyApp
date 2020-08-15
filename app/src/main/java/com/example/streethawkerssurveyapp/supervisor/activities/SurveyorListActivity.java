package com.example.streethawkerssurveyapp.supervisor.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.streethawkerssurveyapp.R;
import com.example.streethawkerssurveyapp.services_pack.ApiService;
import com.example.streethawkerssurveyapp.services_pack.ApplicationConstant;
import com.example.streethawkerssurveyapp.services_pack.CustomProgressDialog;
import com.example.streethawkerssurveyapp.supervisor.adapter.SurveyorListAdapter;
import com.example.streethawkerssurveyapp.supervisor.response_pojo.SurveyorListData;
import com.example.streethawkerssurveyapp.supervisor.response_pojo.SurveyorListResponse;
import com.example.streethawkerssurveyapp.supervisor.services.SupervisorInterface;
import com.example.streethawkerssurveyapp.utils.PrefUtils;
import com.example.streethawkerssurveyapp.supervisor.activities.SurveyorListActivity;
import com.example.streethawkerssurveyapp.view_survey.response_pojo.ViewSurveyData;
import com.example.streethawkerssurveyapp.view_survey.response_pojo.ViewSurveyResponse;
import com.example.streethawkerssurveyapp.view_survey.services.ViewSurveyInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SurveyorListActivity extends AppCompatActivity {



    private EditText mText_search;
    private androidx.recyclerview.widget.RecyclerView mRecycleSurveyor;
    private SurveyorListAdapter surveyorListAdapter;

    private ProgressDialog progressDialog;
    private List<SurveyorListData> AllSurveyorList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surveyor_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Surveyor List");

        bindView();

        ViewSurveyorList();


        mText_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.toString().length() == 0){
                    surveyorListAdapter.setList(AllSurveyorList);

                }else {
                    filter(s.toString());
                }
            }
        });

    }

    private void bindView() {

        mText_search = (EditText) findViewById(R.id.text_search);
        mRecycleSurveyor = (androidx.recyclerview.widget.RecyclerView) findViewById(R.id.RecycleSurveyor);
        mRecycleSurveyor.setLayoutManager(new LinearLayoutManager(SurveyorListActivity.this));

        surveyorListAdapter = new SurveyorListAdapter(SurveyorListActivity.this);
        mRecycleSurveyor.setAdapter(surveyorListAdapter);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void ViewSurveyorList() {

        progressDialog = CustomProgressDialog.getDialogue(SurveyorListActivity.this);
        progressDialog.show();

        String UNiq_Id =  PrefUtils.getFromPrefs(SurveyorListActivity.this, ApplicationConstant.URI_NO_,"");

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + PrefUtils.getFromPrefs(SurveyorListActivity.this, ApplicationConstant.USERDETAILS.API_KEY, ""));

        SupervisorInterface apiservice = ApiService.getApiClient().create(SupervisorInterface.class);
        Call<SurveyorListResponse> call = apiservice.getSurveyorList(headers);

        call.enqueue(new Callback<SurveyorListResponse>() {
            @Override
            public void onResponse(Call<SurveyorListResponse> call, Response<SurveyorListResponse> response) {

                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();

                if (response.body() != null) {

                    if (response.body().isStatus()) {

                        AllSurveyorList  = response.body().getData();
                        surveyorListAdapter.setList(AllSurveyorList);

//                        mTextPageNo.setText("Current Page : "+response.body().getResponse().getCurrentPage());
//                        mTextTotalPages.setText("Total Pages : "+response.body().getResponse().getLastPage());
//
//                        mTextPrevPage.setVisibility(View.GONE);
//
//                        viewSurveyAdapter.setList(AllSurveyList);


                    } else {

                        ApplicationConstant.displayMessageDialog(SurveyorListActivity.this,
                                "Response",
                                "Failed to get Data");
                    }

                }else {

                    try {
                        ApplicationConstant.displayMessageDialog(SurveyorListActivity.this,
                                "Response",
                                response.errorBody().string());
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<SurveyorListResponse> call, Throwable t) {

                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();
                ApplicationConstant.displayMessageDialog(SurveyorListActivity.this, "Response", getString(R.string.net_speed_problem));

            }
        });
    }

    private void filter(String s) {

        List<SurveyorListData> listupdated_user = new ArrayList<>();

        for (SurveyorListData viewSurveyData: AllSurveyorList){

            if (viewSurveyData.getName().toLowerCase().contains(s.toLowerCase())
                    || viewSurveyData.getPhone().toLowerCase().contains(s.toLowerCase())
                    || viewSurveyData.getAllotment().getCorporationName().toLowerCase().contains(s.toLowerCase())
                    || viewSurveyData.getAllotment().getZoneName().toLowerCase().contains(s.toLowerCase())
                    || viewSurveyData.getAllotment().getWardName().toLowerCase().contains(s.toLowerCase())
                    || viewSurveyData.getAllotment().getAreaName().toLowerCase().contains(s.toLowerCase())
                    || viewSurveyData.getAllotment().getTvcName().toLowerCase().contains(s.toLowerCase())
            ){
                listupdated_user.add(viewSurveyData);

            }
        }

        surveyorListAdapter.setList(listupdated_user);

    }


}
