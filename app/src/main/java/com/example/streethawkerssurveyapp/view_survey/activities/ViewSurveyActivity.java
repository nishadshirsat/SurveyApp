package com.example.streethawkerssurveyapp.view_survey.activities;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
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
import com.example.streethawkerssurveyapp.utils.PrefUtils;
import com.example.streethawkerssurveyapp.view_survey.adapters.ViewSurveyAdapter;
import com.example.streethawkerssurveyapp.view_survey.response_pojo.ViewSurveyData;
import com.example.streethawkerssurveyapp.view_survey.response_pojo.ViewSurveyResponse;
import com.example.streethawkerssurveyapp.view_survey.services.ViewSurveyInterface;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewSurveyActivity extends AppCompatActivity {

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
    private ViewSurveyAdapter viewSurveyAdapter;

    ProgressDialog progressDialog;
    private List<ViewSurveyData> AllSurveyList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_survey);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Completed Survey List");

        bindView();

        mRecycler_view.setLayoutManager(new LinearLayoutManager(this));
        viewSurveyAdapter = new ViewSurveyAdapter(this);
        mRecycler_view.setAdapter(viewSurveyAdapter);

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

        ViewAllSurvey();

        onClickListners();


    }

    private void onClickListners() {

        mbtn_SearchData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewPagewiseSurvey();
            }
        });


        mTextNextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!mTextPageNo.getText().toString().trim().equals(mTextTotalPages.getText().toString().trim())){
                    ViewPagewiseSurvey();
                }  else {
                    mTextNextPage.setVisibility(View.GONE);
                    mTextPrevPage.setVisibility(View.VISIBLE);
                }
            }
        });

        mTextPrevPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    ViewPagewiseSurvey();

            }
        });


        mImgDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(new ContextThemeWrapper(ViewSurveyActivity.this, R.style.DialogTheme),
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

                DatePickerDialog datePickerDialog = new DatePickerDialog(new ContextThemeWrapper(ViewSurveyActivity.this, R.style.DialogTheme),
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

                DatePickerDialog datePickerDialog = new DatePickerDialog(new ContextThemeWrapper(ViewSurveyActivity.this, R.style.DialogTheme),
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

        progressDialog = CustomProgressDialog.getDialogue(ViewSurveyActivity.this);
        progressDialog.show();

        String UNiq_Id =  PrefUtils.getFromPrefs(ViewSurveyActivity.this, ApplicationConstant.URI_NO_,"");

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + PrefUtils.getFromPrefs(ViewSurveyActivity.this, ApplicationConstant.USERDETAILS.API_KEY, ""));

        ViewSurveyInterface apiservice = ApiService.getApiClient().create(ViewSurveyInterface.class);
        Call<ViewSurveyResponse> call = apiservice.getViewSurveyData(headers,"1");

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

                        ApplicationConstant.displayMessageDialog(ViewSurveyActivity.this,
                                "Response",
                                "Failed to get Data");
                    }

                }else {

                    try {
                        ApplicationConstant.displayMessageDialog(ViewSurveyActivity.this,
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
                ApplicationConstant.displayMessageDialog(ViewSurveyActivity.this, "Response", getString(R.string.net_speed_problem));

            }
        });
    }

    private void ViewPagewiseSurvey() {

        progressDialog = CustomProgressDialog.getDialogue(ViewSurveyActivity.this);
        progressDialog.show();

        String UNiq_Id =  PrefUtils.getFromPrefs(ViewSurveyActivity.this, ApplicationConstant.URI_NO_,"");

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + PrefUtils.getFromPrefs(ViewSurveyActivity.this, ApplicationConstant.USERDETAILS.API_KEY, ""));

        ViewSurveyInterface apiservice = ApiService.getApiClient().create(ViewSurveyInterface.class);
        Call<ViewSurveyResponse> call = apiservice.viewPagewiseSurvey(headers,
                mTextPageNo.getText().toString().trim(),
                "1",
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

                      AllSurveyList  = response.body().getResponse().getData();

                        mTextPageNo.setText("Current Page : "+response.body().getResponse().getCurrentPage());
                        mTextTotalPages.setText("Total Pages : "+response.body().getResponse().getLastPage());

                        if (!mTextPageNo.getText().toString().trim().equals("1")){

                            mTextPrevPage.setVisibility(View.GONE);

                        }else {

                            mTextPrevPage.setVisibility(View.VISIBLE);

                        }

                       viewSurveyAdapter.setList(AllSurveyList);

                    } else {

                        ApplicationConstant.displayMessageDialog(ViewSurveyActivity.this,
                                "Response",
                                "Failed to get Data");
                    }

                }else {

                    try {
                        ApplicationConstant.displayMessageDialog(ViewSurveyActivity.this,
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
                ApplicationConstant.displayMessageDialog(ViewSurveyActivity.this, "Response", getString(R.string.net_speed_problem));

            }
        });
    }

}
