package com.example.streethawkerssurveyapp.suspended_survey.avtivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.streethawkerssurveyapp.R;
import com.example.streethawkerssurveyapp.services_pack.ApiService;
import com.example.streethawkerssurveyapp.services_pack.ApplicationConstant;
import com.example.streethawkerssurveyapp.services_pack.CustomProgressDialog;
import com.example.streethawkerssurveyapp.suspended_survey.adapters.SuspendedSurveyAdapter;
import com.example.streethawkerssurveyapp.utils.PrefUtils;
import com.example.streethawkerssurveyapp.view_survey.activities.ViewSurveyActivity;
import com.example.streethawkerssurveyapp.view_survey.response_pojo.ViewSurveyData;
import com.example.streethawkerssurveyapp.view_survey.response_pojo.ViewSurveyResponse;
import com.example.streethawkerssurveyapp.view_survey.services.ViewSurveyInterface;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SuspendedSurveyActivity extends AppCompatActivity {

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
    private androidx.recyclerview.widget.RecyclerView mRecycler_view;


    private Calendar myCalendar;
    private int mYear, mMonth, mDay;
    private SuspendedSurveyAdapter viewSurveyAdapter;

    ProgressDialog progressDialog;
    private List<ViewSurveyData> AllSurveyList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_survey);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Suspended Survey List");

        bindView();

        mRecycler_view.setLayoutManager(new LinearLayoutManager(this));
        viewSurveyAdapter = new SuspendedSurveyAdapter(this);
        mRecycler_view.setAdapter(viewSurveyAdapter);

        myCalendar = Calendar.getInstance();
        mYear = myCalendar.get(Calendar.YEAR);
        mMonth = myCalendar.get(Calendar.MONTH);
        mDay = myCalendar.get(Calendar.DAY_OF_MONTH);

//        CardSurvey.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(SuspendedSurveyActivity.this,ViewSurveyDetailsActivity.class));
//            }
//        });

        ViewAllSurvey();

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

                            ApplicationConstant.displayToastMessage(SuspendedSurveyActivity.this,"No more records found");

                        }
                    } catch (NumberFormatException e) {
                        e.printStackTrace();

                        ApplicationConstant.displayToastMessage(SuspendedSurveyActivity.this,"No more records found");

                    }
                }

            }
        });


        mbtn_SearchData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                int PageNo = 1;
//
//                if (mTextPageNo.getText().toString().trim().contains(":")){
//                    PageNo = Integer.parseInt(mTextPageNo.getText().toString().trim().split(":")[1].trim()) + 1;
//                }
                ViewSearchSurvey(0);
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

                DatePickerDialog datePickerDialog = new DatePickerDialog(new ContextThemeWrapper(SuspendedSurveyActivity.this, R.style.DialogTheme),
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

                DatePickerDialog datePickerDialog = new DatePickerDialog(new ContextThemeWrapper(SuspendedSurveyActivity.this, R.style.DialogTheme),
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

                DatePickerDialog datePickerDialog = new DatePickerDialog(new ContextThemeWrapper(SuspendedSurveyActivity.this, R.style.DialogTheme),
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
        mRecycler_view = (androidx.recyclerview.widget.RecyclerView) findViewById(R.id.recycler_view);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void ViewAllSurvey() {

        progressDialog = CustomProgressDialog.getDialogue(SuspendedSurveyActivity.this);
        progressDialog.show();

        String UNiq_Id =  PrefUtils.getFromPrefs(SuspendedSurveyActivity.this, ApplicationConstant.URI_NO_,"");

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + PrefUtils.getFromPrefs(SuspendedSurveyActivity.this, ApplicationConstant.USERDETAILS.API_KEY, ""));

        ViewSurveyInterface apiservice = ApiService.getApiClient().create(ViewSurveyInterface.class);
        Call<ViewSurveyResponse> call = apiservice.getViewSurveyData(headers,"-1");

        call.enqueue(new Callback<ViewSurveyResponse>() {
            @Override
            public void onResponse(Call<ViewSurveyResponse> call, Response<ViewSurveyResponse> response) {

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

                        ApplicationConstant.displayMessageDialog(SuspendedSurveyActivity.this,
                                "Response",
                                "Failed to get Data");
                    }

                }else {

                    try {
                        ApplicationConstant.displayMessageDialog(SuspendedSurveyActivity.this,
                                "Response",
                                response.errorBody().string());
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<ViewSurveyResponse> call, Throwable t) {

                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();
                ApplicationConstant.displayMessageDialog(SuspendedSurveyActivity.this, "Response", getString(R.string.net_speed_problem));

            }
        });
    }

    private void ViewPagewiseSurvey(int pageNo) {
        String NO_PAGE = "";

        progressDialog = CustomProgressDialog.getDialogue(SuspendedSurveyActivity.this);
        progressDialog.show();

        String UNiq_Id =  PrefUtils.getFromPrefs(SuspendedSurveyActivity.this, ApplicationConstant.URI_NO_,"");

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + PrefUtils.getFromPrefs(SuspendedSurveyActivity.this, ApplicationConstant.USERDETAILS.API_KEY, ""));

        if (pageNo == 0){
            NO_PAGE ="";
        }else {
            NO_PAGE = ""+pageNo;
        }

        ViewSurveyInterface apiservice = ApiService.getApiClient().create(ViewSurveyInterface.class);
        Call<ViewSurveyResponse> call = apiservice.viewPagewiseSurvey(headers,
                NO_PAGE,
                "-1",
                mEditDate.getText().toString().trim(),
                mEditAdharNo.getText().toString().trim()
        );

        call.enqueue(new Callback<ViewSurveyResponse>() {
            @Override
            public void onResponse(Call<ViewSurveyResponse> call, Response<ViewSurveyResponse> response) {

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

                        ApplicationConstant.displayMessageDialog(SuspendedSurveyActivity.this,
                                "Response",
                                "Failed to get Data");
                    }

                }else {

                    try {
                        ApplicationConstant.displayMessageDialog(SuspendedSurveyActivity.this,
                                "Response",
                                response.errorBody().string());
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<ViewSurveyResponse> call, Throwable t) {

                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();
                ApplicationConstant.displayMessageDialog(SuspendedSurveyActivity.this, "Response", getString(R.string.net_speed_problem));

            }
        });
    }


    private void ViewSearchSurvey(int pageNo) {
        String NO_PAGE = "";

        progressDialog = CustomProgressDialog.getDialogue(SuspendedSurveyActivity.this);
        progressDialog.show();

        String UNiq_Id =  PrefUtils.getFromPrefs(SuspendedSurveyActivity.this, ApplicationConstant.URI_NO_,"");

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + PrefUtils.getFromPrefs(SuspendedSurveyActivity.this, ApplicationConstant.USERDETAILS.API_KEY, ""));

        if (pageNo == 0){
            NO_PAGE ="";
        }else {
            NO_PAGE = ""+pageNo;
        }

        ViewSurveyInterface apiservice = ApiService.getApiClient().create(ViewSurveyInterface.class);
        Call<ViewSurveyResponse> call = apiservice.viewPagewiseSurvey(headers,
                NO_PAGE,
                "-1",
                mEditDate.getText().toString().trim(),
                mEditAdharNo.getText().toString().trim()
        );

        call.enqueue(new Callback<ViewSurveyResponse>() {
            @Override
            public void onResponse(Call<ViewSurveyResponse> call, Response<ViewSurveyResponse> response) {

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

                        ApplicationConstant.displayMessageDialog(SuspendedSurveyActivity.this,
                                "Response",
                                "Failed to get Data");
                    }

                }else {

                    try {
                        ApplicationConstant.displayMessageDialog(SuspendedSurveyActivity.this,
                                "Response",
                                response.errorBody().string());
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<ViewSurveyResponse> call, Throwable t) {

                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();
                ApplicationConstant.displayMessageDialog(SuspendedSurveyActivity.this, "Response", getString(R.string.net_speed_problem));

            }
        });
    }


    private void filter(String s) {

        List<ViewSurveyData> listupdated_user = new ArrayList<>();

        for (ViewSurveyData viewSurveyData: AllSurveyList){

            try {
                if (viewSurveyData.getUriNumber().toLowerCase().contains(s.toLowerCase())
                        || viewSurveyData.getNameOfTheStreetVendor().toLowerCase().contains(s.toLowerCase())
                        || viewSurveyData.getCorporation().toLowerCase().contains(s.toLowerCase())
                        || viewSurveyData.getArea().toLowerCase().contains(s.toLowerCase())
                        || viewSurveyData.getWard().toLowerCase().contains(s.toLowerCase())
                        || viewSurveyData.getZone().toLowerCase().contains(s.toLowerCase())
                ){
                    listupdated_user.add(viewSurveyData);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        viewSurveyAdapter.setList(listupdated_user);

    }

}
