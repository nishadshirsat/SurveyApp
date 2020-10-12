package com.example.streethawkerssurveyapp.supervisor.activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.streethawkerssurveyapp.R;
import com.example.streethawkerssurveyapp.activities.DashboardActivity;
import com.example.streethawkerssurveyapp.activities.MainActivity;
import com.example.streethawkerssurveyapp.activities.PersonalDetailsActivity;
import com.example.streethawkerssurveyapp.activities.VendorsFamDetailsActivity;
import com.example.streethawkerssurveyapp.pending_survey.activities.PendingSurveyActivity;
import com.example.streethawkerssurveyapp.response_pack.UpdateSurveyResponse;
import com.example.streethawkerssurveyapp.services_pack.ApiInterface;
import com.example.streethawkerssurveyapp.services_pack.ApiService;
import com.example.streethawkerssurveyapp.services_pack.ApplicationConstant;
import com.example.streethawkerssurveyapp.services_pack.CustomProgressDialog;
import com.example.streethawkerssurveyapp.supervisor.adapter.ViewSurveySupervisorAdapter;
import com.example.streethawkerssurveyapp.supervisor.response_pojo.SupervisorViewSurveyData;
import com.example.streethawkerssurveyapp.supervisor.response_pojo.SupervisorViewSurveyResponse;
import com.example.streethawkerssurveyapp.supervisor.response_pojo.SurveyorListData;
import com.example.streethawkerssurveyapp.supervisor.response_pojo.SurveyorListResponse;
import com.example.streethawkerssurveyapp.supervisor.services.SupervisorInterface;
import com.example.streethawkerssurveyapp.utils.PrefUtils;
import com.example.streethawkerssurveyapp.view_survey.response_pojo.ViewSurveyData;
import com.example.streethawkerssurveyapp.view_survey.response_pojo.ViewSurveyResponse;
import com.example.streethawkerssurveyapp.view_survey.services.ViewSurveyInterface;
import com.labters.documentscanner.base.DocumentScanActivity;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewSurveySupervisorActivity extends MainActivity implements ViewSurveySupervisorAdapter.RefreshlistListner{

    private EditText mEditAdharNo;
    private LinearLayout mLinearDate;
    private ImageView mImgDate;
    private EditText mEditDate;
    private EditText mText_search;
    private TextView mTextPageNo;
    private TextView mTextTotalPages;
    private TextView mTextPrevPage;
    private TextView mTextNextPage;
    private Button mbtn_SearchData;
    private RecyclerView mRecycler_view;


    private Calendar myCalendar;
    private int mYear, mMonth, mDay;
    private ViewSurveySupervisorAdapter viewSurveyAdapter;
    private SearchableSpinner mSpinnerType;

    ProgressDialog progressDialog;
    private List<SupervisorViewSurveyData> AllSurveyList = new ArrayList<>();
    private List<SurveyorListData> AllSurveyorList = new ArrayList<>();
    private String ID="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_survey_supervisor);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle(R.string.surveyor_list);

        bindView();

        mRecycler_view.setLayoutManager(new LinearLayoutManager(this));
        viewSurveyAdapter = new ViewSurveySupervisorAdapter(this);
        mRecycler_view.setAdapter(viewSurveyAdapter);
        viewSurveyAdapter.setListner(ViewSurveySupervisorActivity.this);

        myCalendar = Calendar.getInstance();
        mYear = myCalendar.get(Calendar.YEAR);
        mMonth = myCalendar.get(Calendar.MONTH);
        mDay = myCalendar.get(Calendar.DAY_OF_MONTH);

//        CardSurvey.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(ViewSurveyActivity.this,ViewSurveyDetailsActivity.class));
//            }
//        });


        ViewSurveyorList();

        mSpinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                ID = AllSurveyorList.get(i).getId().toString().trim();

                ViewAllSurvey(ID);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        onClickListners();

    }

    private void onClickListners() {

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
                    viewSurveyAdapter.setList(AllSurveyList);

                }else {
                    filter(s.toString());
                }
            }
        });

        mRecycler_view.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();


                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == AllSurveyList.size() - 1) {
                        //bottom of list!
                        try {
                            if (!mTextPageNo.getText().toString().trim().split(":")[1].trim().equals(mTextTotalPages.getText().toString().trim().split(":")[1].trim())){
                                int PageNo = 1;

                                if (mTextPageNo.getText().toString().trim().contains(":")){
                                    PageNo = Integer.parseInt(mTextPageNo.getText().toString().trim().split(":")[1].trim()) + 1;
                                }
                                ViewPagewiseSurvey(PageNo);
                            }  else {
                                mTextNextPage.setVisibility(View.GONE);
                                mTextPrevPage.setVisibility(View.VISIBLE);

                                ApplicationConstant.displayToastMessage(ViewSurveySupervisorActivity.this,"No more records found");

                            }
                        } catch (NumberFormatException e) {
                            e.printStackTrace();

                            ApplicationConstant.displayToastMessage(ViewSurveySupervisorActivity.this,"No more records found");

                        }
                    }

            }
        });


        mbtn_SearchData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                int PageNo = 1;
//
                if (ID.trim().isEmpty()){

                    ApplicationConstant.displayMessageDialog(ViewSurveySupervisorActivity.this,"","Select Surveyor to display data");
                }else {
                    ViewSearchSurvey(0);

                }

            }
        });


        mTextNextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    if (!mTextPageNo.getText().toString().trim().split(":")[1].trim().equals(mTextTotalPages.getText().toString().trim().split(":")[1].trim())){
                        int PageNo = 1;

                        if (mTextPageNo.getText().toString().trim().contains(":")){
                            PageNo = Integer.parseInt(mTextPageNo.getText().toString().trim().split(":")[1].trim()) + 1;
                        }
                        ViewPagewiseSurvey(PageNo);
                    }  else {
                        mTextNextPage.setVisibility(View.GONE);
                        mTextPrevPage.setVisibility(View.VISIBLE);
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        });

        mTextPrevPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    if (Integer.parseInt(mTextPageNo.getText().toString().trim().split(":")[1].trim()) > 1){
                        int PageNo = 1;

                        if (mTextPageNo.getText().toString().trim().contains(":")){
                            PageNo = Integer.parseInt(mTextPageNo.getText().toString().trim().split(":")[1].trim()) - 1;
                        }
                        ViewPagewiseSurvey(PageNo);
                    }else {
                        mTextNextPage.setVisibility(View.VISIBLE);
                        mTextPrevPage.setVisibility(View.GONE);
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

            }
        });


        mImgDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(new ContextThemeWrapper(ViewSurveySupervisorActivity.this, R.style.DialogTheme),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                mEditDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);


                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });

        mLinearDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(new ContextThemeWrapper(ViewSurveySupervisorActivity.this, R.style.DialogTheme),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                mEditDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);


                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });

        mEditDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(new ContextThemeWrapper(ViewSurveySupervisorActivity.this, R.style.DialogTheme),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                mEditDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);


                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });

    }

    private void bindView() {
        mSpinnerType = (SearchableSpinner) findViewById(R.id.SpinnerType);
        mbtn_SearchData = (Button) findViewById(R.id.btn_SearchData);
        mEditAdharNo = (EditText) findViewById(R.id.EditAdharNo);
        mLinearDate = (LinearLayout) findViewById(R.id.LinearDate);
        mImgDate = (ImageView) findViewById(R.id.ImgDate);
        mEditDate = (EditText) findViewById(R.id.EditDate);
        mText_search = (EditText) findViewById(R.id.text_search);
        mTextPageNo = (TextView) findViewById(R.id.TextPageNo);
        mTextTotalPages = (TextView) findViewById(R.id.TextTotalPages);
        mTextPrevPage = (TextView) findViewById(R.id.TextPrevPage);
        mTextNextPage = (TextView) findViewById(R.id.TextNextPage);
        mRecycler_view = (RecyclerView) findViewById(R.id.recycler_view);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void ViewAllSurvey(String ID) {

        progressDialog = CustomProgressDialog.getDialogue(ViewSurveySupervisorActivity.this);
        progressDialog.show();

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + PrefUtils.getFromPrefs(ViewSurveySupervisorActivity.this, ApplicationConstant.USERDETAILS.API_KEY, ""));

        SupervisorInterface apiservice = ApiService.getApiClient().create(SupervisorInterface.class);
        Call<SupervisorViewSurveyResponse> call = apiservice.getSurveyorSurveys(headers,"1","1",
                ID,
                mEditDate.getText().toString().trim(),
                mEditAdharNo.getText().toString().trim());

        call.enqueue(new Callback<SupervisorViewSurveyResponse>() {
            @Override
            public void onResponse(Call<SupervisorViewSurveyResponse> call, Response<SupervisorViewSurveyResponse> response) {

                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();

                if (response.body() != null) {

                    if (response.body().isStatus()) {

                      AllSurveyList  = response.body().getResponse().getData();

                      mTextPageNo.setText("Current Page : "+response.body().getResponse().getCurrentPage());
                      mTextTotalPages.setText("Total Pages : "+response.body().getResponse().getLastPage());

                      mTextPrevPage.setVisibility(View.GONE);

                      viewSurveyAdapter.setList(AllSurveyList);


                    } else {

                        ApplicationConstant.displayMessageDialog(ViewSurveySupervisorActivity.this,
                                "Response",
                                "Failed to get Data");
                    }

                }else {

                    try {
                        ApplicationConstant.displayMessageDialog(ViewSurveySupervisorActivity.this,
                                "Response",
                                response.errorBody().string());
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<SupervisorViewSurveyResponse> call, Throwable t) {

                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();
                ApplicationConstant.displayMessageDialog(ViewSurveySupervisorActivity.this, "Response", t.getMessage().toString().trim());

            }
        });
    }

    private void ViewPagewiseSurvey(int pageNo) {
        String NO_PAGE = "";

        progressDialog = CustomProgressDialog.getDialogue(ViewSurveySupervisorActivity.this);
        progressDialog.show();


        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + PrefUtils.getFromPrefs(ViewSurveySupervisorActivity.this, ApplicationConstant.USERDETAILS.API_KEY, ""));

        if (pageNo == 0){
            NO_PAGE ="";
        }else {
            NO_PAGE = ""+pageNo;
        }

        SupervisorInterface apiservice = ApiService.getApiClient().create(SupervisorInterface.class);
        Call<SupervisorViewSurveyResponse> call = apiservice.getSurveyorSurveys(headers,
                NO_PAGE,
                "1",
                ID,
                mEditDate.getText().toString().trim(),
                mEditAdharNo.getText().toString().trim()
                );

        call.enqueue(new Callback<SupervisorViewSurveyResponse>() {
            @Override
            public void onResponse(Call<SupervisorViewSurveyResponse> call, Response<SupervisorViewSurveyResponse> response) {

                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();

                if (response.body() != null) {

                    if (response.body().isStatus()) {

                        AllSurveyList.addAll(response.body().getResponse().getData());

                        mTextPageNo.setText("Current Page : "+response.body().getResponse().getCurrentPage());
                        mTextTotalPages.setText("Total Pages : "+response.body().getResponse().getLastPage());

                        if (!mTextPageNo.getText().toString().trim().equals("1")){

                            mTextPrevPage.setVisibility(View.VISIBLE);
                            mTextNextPage.setVisibility(View.VISIBLE);

                        }
//                        else   if (!mTextPageNo.getText().toString().trim().equals("1")){
//
//                            mTextPrevPage.setVisibility(View.VISIBLE);
//
//                        }
                        else {

                            mTextPrevPage.setVisibility(View.GONE);
                            mTextNextPage.setVisibility(View.VISIBLE);

                        }

                       viewSurveyAdapter.setList(AllSurveyList);

                    } else {

                        ApplicationConstant.displayMessageDialog(ViewSurveySupervisorActivity.this,
                                "Response",
                                "Failed to get Data");
                    }

                }else {

                    try {
                        ApplicationConstant.displayMessageDialog(ViewSurveySupervisorActivity.this,
                                "Response",
                                response.errorBody().string());
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<SupervisorViewSurveyResponse> call, Throwable t) {

                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();
                ApplicationConstant.displayMessageDialog(ViewSurveySupervisorActivity.this, "Response", t.getMessage().toString().trim());

            }
        });
    }


    private void ViewSearchSurvey(int pageNo) {
        String NO_PAGE = "";

        progressDialog = CustomProgressDialog.getDialogue(ViewSurveySupervisorActivity.this);
        progressDialog.show();

        String UNiq_Id =  PrefUtils.getFromPrefs(ViewSurveySupervisorActivity.this, ApplicationConstant.URI_NO_,"");

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + PrefUtils.getFromPrefs(ViewSurveySupervisorActivity.this, ApplicationConstant.USERDETAILS.API_KEY, ""));

        if (pageNo == 0){
            NO_PAGE ="";
        }else {
            NO_PAGE = ""+pageNo;
        }

        SupervisorInterface apiservice = ApiService.getApiClient().create(SupervisorInterface.class);
        Call<SupervisorViewSurveyResponse> call = apiservice.getSurveyorSurveys(headers,
                NO_PAGE,
                "1",
                ID,
                mEditDate.getText().toString().trim(),
                mEditAdharNo.getText().toString().trim()
                );

        call.enqueue(new Callback<SupervisorViewSurveyResponse>() {
            @Override
            public void onResponse(Call<SupervisorViewSurveyResponse> call, Response<SupervisorViewSurveyResponse> response) {

                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();

                if (response.body() != null) {

                    if (response.body().isStatus()) {

                        AllSurveyList.clear();

                        AllSurveyList.addAll(response.body().getResponse().getData());

                        mTextPageNo.setText("Current Page : "+response.body().getResponse().getCurrentPage());
                        mTextTotalPages.setText("Total Pages : "+response.body().getResponse().getLastPage());

                        if (!mTextPageNo.getText().toString().trim().equals("1")){

                            mTextPrevPage.setVisibility(View.VISIBLE);
                            mTextNextPage.setVisibility(View.VISIBLE);

                        }
//                        else   if (!mTextPageNo.getText().toString().trim().equals("1")){
//
//                            mTextPrevPage.setVisibility(View.VISIBLE);
//
//                        }
                        else {

                            mTextPrevPage.setVisibility(View.GONE);
                            mTextNextPage.setVisibility(View.VISIBLE);

                        }

                       viewSurveyAdapter.setList(AllSurveyList);

                    } else {

                        ApplicationConstant.displayMessageDialog(ViewSurveySupervisorActivity.this,
                                "Response",
                                "Failed to get Data");
                    }

                }else {

                    try {
                        ApplicationConstant.displayMessageDialog(ViewSurveySupervisorActivity.this,
                                "Response",
                                response.errorBody().string());
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<SupervisorViewSurveyResponse> call, Throwable t) {

                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();
                ApplicationConstant.displayMessageDialog(ViewSurveySupervisorActivity.this, "Response", t.getMessage().toString().trim());

            }
        });
    }


    private void filter(String s) {

        List<SupervisorViewSurveyData> listupdated_user = new ArrayList<>();

        for (SupervisorViewSurveyData viewSurveyData: AllSurveyList){

            if (viewSurveyData.getUriNumber().toLowerCase().contains(s.toLowerCase())
                    || viewSurveyData.getNameOfTheStreetVendor().toLowerCase().contains(s.toLowerCase())
                    || viewSurveyData.getCorporation().toLowerCase().contains(s.toLowerCase())
                    || viewSurveyData.getCategory().toLowerCase().contains(s.toLowerCase())
                    || viewSurveyData.getArea().toLowerCase().contains(s.toLowerCase())
                    || viewSurveyData.getWard().toLowerCase().contains(s.toLowerCase())
                    || viewSurveyData.getSurveyStatus().toLowerCase().contains(s.toLowerCase())
                    || viewSurveyData.getZone().toLowerCase().contains(s.toLowerCase())
            ){
                listupdated_user.add(viewSurveyData);

            }
        }

        viewSurveyAdapter.setList(listupdated_user);

    }

    private void ViewSurveyorList() {

        progressDialog = CustomProgressDialog.getDialogue(ViewSurveySupervisorActivity.this);
        progressDialog.show();


        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + PrefUtils.getFromPrefs(ViewSurveySupervisorActivity.this, ApplicationConstant.USERDETAILS.API_KEY, ""));

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

                        ArrayAdapter<SurveyorListData> listDataArrayAdapter = new ArrayAdapter<>(ViewSurveySupervisorActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,AllSurveyorList);
                        mSpinnerType.setTitle("Select Surveyor");
                        mSpinnerType.setAdapter(listDataArrayAdapter);


                    } else {

                        ApplicationConstant.displayMessageDialog(ViewSurveySupervisorActivity.this,
                                "Response",
                                "Failed to get Data");
                    }

                }else {

                    try {
                        ApplicationConstant.displayMessageDialog(ViewSurveySupervisorActivity.this,
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
                ApplicationConstant.displayMessageDialog(ViewSurveySupervisorActivity.this, "Response", t.getMessage().toString().trim());

            }
        });
    }

    @Override
    public void refrehListwithAction(String URI) {

        CheckSurveyRemark(URI);

    }

    @Override
    public void refrehListwithPending(String URI) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(ViewSurveySupervisorActivity.this);
                        builder.setTitle("Check Pending Survey");
                        builder.setMessage("Set this survey as pending survey ?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();

                                CheckPendingRemark(URI);

                            }
                        });

                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();

                            }
                        });

                        AlertDialog alertDialog = builder.create();
                        alertDialog.setCancelable(false);
                        alertDialog.setCanceledOnTouchOutside(false);
                        alertDialog.show();

    }


    private void CheckSurveyRemark(String URI) {

        progressDialog = CustomProgressDialog.getDialogue(ViewSurveySupervisorActivity.this);
        progressDialog.show();


        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + PrefUtils.getFromPrefs(ViewSurveySupervisorActivity.this, ApplicationConstant.USERDETAILS.API_KEY, ""));

        SupervisorInterface apiservice = ApiService.getApiClient().create(SupervisorInterface.class);
        Call<UpdateSurveyResponse> call = apiservice.SendCheckStatus(headers,URI,"1");

        call.enqueue(new Callback<UpdateSurveyResponse>() {
            @Override
            public void onResponse(Call<UpdateSurveyResponse> call, Response<UpdateSurveyResponse> response) {

                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();

                if (response.body() != null) {

                    if (response.body().isStatus()) {

//                        ApplicationConstant.displayToastMessage(ViewSurveySupervisorActivity.this,
//                                response.body().getMessage());

                        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(ViewSurveySupervisorActivity.this);
                        builder.setTitle("Response");
                        builder.setMessage(response.body().getMessage());
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();

                                ViewAllSurvey(ID);

                            }
                        });

                        AlertDialog alertDialog = builder.create();
                        alertDialog.setCancelable(false);
                        alertDialog.setCanceledOnTouchOutside(false);
                        alertDialog.show();


                    } else {

                        ApplicationConstant.displayMessageDialog(ViewSurveySupervisorActivity.this,
                                "Response",
                                response.body().getMessage());
                    }

                }else {

                    try {
                        ApplicationConstant.displayMessageDialog(ViewSurveySupervisorActivity.this,
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
                ApplicationConstant.displayMessageDialog(ViewSurveySupervisorActivity.this, "Response", t.getMessage().toString().trim());

            }
        });
    }


    private void CheckPendingRemark(String URI) {

        progressDialog = CustomProgressDialog.getDialogue(ViewSurveySupervisorActivity.this);
        progressDialog.show();


        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + PrefUtils.getFromPrefs(ViewSurveySupervisorActivity.this, ApplicationConstant.USERDETAILS.API_KEY, ""));

        SupervisorInterface apiservice = ApiService.getApiClient().create(SupervisorInterface.class);
        Call<UpdateSurveyResponse> call = apiservice.SendPendingStatus(headers,URI,"0");

        call.enqueue(new Callback<UpdateSurveyResponse>() {
            @Override
            public void onResponse(Call<UpdateSurveyResponse> call, Response<UpdateSurveyResponse> response) {

                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();

                if (response.body() != null) {

                    if (response.body().isStatus()) {

                        ApplicationConstant.displayToastMessage(ViewSurveySupervisorActivity.this,
                                response.body().getMessage());

                        ViewAllSurvey(ID);


                    } else {

                        ApplicationConstant.displayMessageDialog(ViewSurveySupervisorActivity.this,
                                "Response",
                                response.body().getMessage());
                    }

                }else {

                    try {
                        ApplicationConstant.displayMessageDialog(ViewSurveySupervisorActivity.this,
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
                ApplicationConstant.displayMessageDialog(ViewSurveySupervisorActivity.this, "Response", t.getMessage().toString().trim());

            }
        });
    }


}
