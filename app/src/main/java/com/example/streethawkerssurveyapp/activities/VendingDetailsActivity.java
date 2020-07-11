package com.example.streethawkerssurveyapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.DatePickerDialog;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.streethawkerssurveyapp.R;
import com.example.streethawkerssurveyapp.response_pack.SurveyResponse;
import com.example.streethawkerssurveyapp.response_pack.UpdateSurveyResponse;
import com.example.streethawkerssurveyapp.services_pack.ApiInterface;
import com.example.streethawkerssurveyapp.services_pack.ApiService;
import com.example.streethawkerssurveyapp.services_pack.ApplicationConstant;
import com.example.streethawkerssurveyapp.services_pack.CustomProgressDialog;
import com.example.streethawkerssurveyapp.utils.PrefUtils;

import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class VendingDetailsActivity extends AppCompatActivity {

    private LinearLayout mLinearMain;
    private LinearLayout mLinearOne;
    private Spinner mSpinnerItems;
    private EditText mEditVendingSite;
    private EditText mEditFromTime;
    private EditText mEditToTime;
    private EditText mEditAge;
    private LinearLayout mLinearTwo;
    private EditText mEditAnnualIncome;
    private RadioButton mRadioY;
    private RadioButton mRadioN;
    private Spinner mSpinnerVehical;
    private EditText mEditDob;
    private ImageView mImgCalendar;
    private LinearLayout mLinearThree;
    private RadioButton mRadioDY;
    private RadioButton mRadioDN;
    private Spinner mSpinnerChoice;
    private Button mBtnNext;
    private Button mBtnPrevious;
    RadioGroup RGDocument, RGVendor;

    private ProgressDialog progressDialog;

    private String
            URI_NO = "",
            TYPE_OF_VENDING = "",
            VENDING_SITE = "",
            VENDING_FROM = "",
            VENDING_TO = "",
            YRS_OF_VENDING = "",
            ANNUAL_INCOME = "",
            IS_RECOGNIZED_STREET_VENDOR = "",
            TYPE_OF_STRUCTURE = "",
            STARTING_DATE_VENDING = "",
            TEHABZARI_AVAILABLE = "",
            VENDING_AREA_CHOCE = "";

    private Calendar myCalendar;
    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vending_details);

        bindView();

        myCalendar = Calendar.getInstance();
        mYear = myCalendar.get(Calendar.YEAR);
        mMonth = myCalendar.get(Calendar.MONTH);
        mDay = myCalendar.get(Calendar.DAY_OF_MONTH);

        mImgCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(new ContextThemeWrapper(VendingDetailsActivity.this, R.style.DialogTheme),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                mEditDob.setText((monthOfYear + 1) + "/" + dayOfMonth + "/" + year);


                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });

        mBtnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VendingDetailsActivity.this,VendorsFamDetailsActivity.class));
            }
        });
        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mLinearOne.getVisibility() == View.VISIBLE) {
                    if (validate1()) {
                        mLinearOne.setVisibility(View.GONE);
                        mLinearTwo.setVisibility(View.VISIBLE);
                        mLinearThree.setVisibility(View.GONE);
                    }
                } else if (mLinearTwo.getVisibility() == View.VISIBLE) {
                    if (validate2()) {
                        mLinearTwo.setVisibility(View.GONE);
                        mLinearOne.setVisibility(View.GONE);
                        mLinearThree.setVisibility(View.VISIBLE);
                    }
                } else {

                    URI_NO = ApplicationConstant.URI_NO;
                    mSpinnerItems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            TYPE_OF_VENDING = mSpinnerItems.getItemAtPosition(position).toString();

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                    VENDING_SITE=mEditVendingSite.getText().toString().trim();
                    VENDING_FROM=mEditFromTime.getText().toString().trim();
                    VENDING_TO=mEditToTime.getText().toString().trim();
                    YRS_OF_VENDING=mEditAge.getText().toString().trim();
                    ANNUAL_INCOME=mEditAnnualIncome.getText().toString().trim();
                    ANNUAL_INCOME=mEditAnnualIncome.getText().toString().trim();
                    IS_RECOGNIZED_STREET_VENDOR = String.valueOf(RGVendor.getCheckedRadioButtonId());
                    mSpinnerVehical.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            TYPE_OF_STRUCTURE = mSpinnerVehical.getItemAtPosition(position).toString();

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                    STARTING_DATE_VENDING=mEditDob.getText().toString().trim();
                    TEHABZARI_AVAILABLE= String.valueOf(RGDocument.getCheckedRadioButtonId());
                    mSpinnerChoice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            VENDING_AREA_CHOCE = mSpinnerChoice.getItemAtPosition(position).toString();

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });


                    if (validate3()) {
                        UpdateSurvey();
                    }


                }


            }
        });
    }

    private boolean validate3() {
        if (!ApplicationConstant.isNetworkAvailable(VendingDetailsActivity.this)) {

            ApplicationConstant.displayMessageDialog(VendingDetailsActivity.this, "No Internet Connection", "Please enable internet connection first to proceed");

            return false;
        } else if (mSpinnerChoice.getSelectedItem().toString().isEmpty()) {
            mSpinnerChoice.requestFocus();
            return false;
        }
        return true;
    }

    private boolean validate2() {
        if (!ApplicationConstant.isNetworkAvailable(VendingDetailsActivity.this)) {

            ApplicationConstant.displayMessageDialog(VendingDetailsActivity.this, "No Internet Connection", "Please enable internet connection first to proceed");

            return false;
        } else if (mEditAnnualIncome.getText().toString().trim().isEmpty()) {
            mEditAnnualIncome.setError("Enter Vending Site");
            mEditAnnualIncome.requestFocus();
            return false;
        } else if (mSpinnerVehical.getSelectedItem().toString().isEmpty()) {
            mEditAnnualIncome.setError("Select Vehicals");
            mSpinnerVehical.requestFocus();
            return false;
        } else if (mEditDob.getText().toString().trim().isEmpty()) {
            mEditDob.setError("Enter Date");
            mEditDob.requestFocus();
            return false;
        }
        return true;
    }

    private boolean validate1() {

        if (!ApplicationConstant.isNetworkAvailable(VendingDetailsActivity.this)) {

            ApplicationConstant.displayMessageDialog(VendingDetailsActivity.this, "No Internet Connection", "Please enable internet connection first to proceed");

            return false;
        } else if (mSpinnerItems.getSelectedItem().toString().isEmpty()) {
            mEditVendingSite.setError("Select Items");
            mSpinnerItems.requestFocus();
            return false;
        } else if (mEditVendingSite.getText().toString().trim().isEmpty()) {
            mEditVendingSite.setError("Enter Vending Site");
            mEditVendingSite.requestFocus();
            return false;
        } else if (mEditFromTime.getText().toString().trim().isEmpty()) {
            mEditFromTime.setError("Enter From Time");
            mEditFromTime.requestFocus();
            return false;
        } else if (mEditToTime.getText().toString().trim().isEmpty()) {
            mEditToTime.setError("Enter To Time");
            mEditToTime.requestFocus();
            return false;
        } else if (mEditAge.getText().toString().trim().isEmpty()) {
            mEditAge.setError("Enter Age");
            mEditAge.requestFocus();
            return false;
        }

        return true;
    }

    private void bindView() {

        mLinearMain = (LinearLayout) findViewById(R.id.LinearMain);
        mLinearOne = (LinearLayout) findViewById(R.id.LinearOne);
        RGDocument = (RadioGroup) findViewById(R.id.RGDocument);
        RGVendor = (RadioGroup) findViewById(R.id.RGVendor);
        mSpinnerItems = (Spinner) findViewById(R.id.SpinnerItems);
        mEditVendingSite = (EditText) findViewById(R.id.EditVendingSite);
        mEditFromTime = (EditText) findViewById(R.id.EditFromTime);
        mEditToTime = (EditText) findViewById(R.id.EditToTime);
        mEditAge = (EditText) findViewById(R.id.EditAge);
        mLinearTwo = (LinearLayout) findViewById(R.id.LinearTwo);
        mEditAnnualIncome = (EditText) findViewById(R.id.EditAnnualIncome);
        mRadioY = (RadioButton) findViewById(Integer.parseInt(IS_RECOGNIZED_STREET_VENDOR));
        mRadioN = (RadioButton) findViewById(Integer.parseInt(IS_RECOGNIZED_STREET_VENDOR));
        mSpinnerVehical = (Spinner) findViewById(R.id.SpinnerVehical);
        mEditDob = (EditText) findViewById(R.id.EditDob);
        mImgCalendar = (ImageView) findViewById(R.id.ImgCalendar);
        mLinearThree = (LinearLayout) findViewById(R.id.LinearThree);
        mRadioDY = (RadioButton) findViewById(Integer.parseInt(TEHABZARI_AVAILABLE));
        mRadioDN = (RadioButton) findViewById(Integer.parseInt(TEHABZARI_AVAILABLE));
        mSpinnerChoice = (Spinner) findViewById(R.id.SpinnerChoice);
        mBtnNext = (Button) findViewById(R.id.BtnNext);
        mBtnPrevious = (Button) findViewById(R.id.BtnPrevious);

    }

    private void UpdateSurvey() {

        progressDialog = CustomProgressDialog.getDialogue(VendingDetailsActivity.this);
        progressDialog.show();

        String username = PrefUtils.getFromPrefs(VendingDetailsActivity.this, ApplicationConstant.USERDETAILS.API_KEY, "");

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + PrefUtils.getFromPrefs(VendingDetailsActivity.this, ApplicationConstant.USERDETAILS.API_KEY, ""));

        ApiInterface apiservice = ApiService.getApiClient().create(ApiInterface.class);
        Call<UpdateSurveyResponse> call = apiservice.getUpdateSurvey(headers,
                URI_NO, TYPE_OF_VENDING, VENDING_SITE, VENDING_FROM, VENDING_TO, YRS_OF_VENDING,
                ANNUAL_INCOME, IS_RECOGNIZED_STREET_VENDOR, TYPE_OF_STRUCTURE, STARTING_DATE_VENDING,
                TEHABZARI_AVAILABLE, VENDING_AREA_CHOCE

        );

        call.enqueue(new Callback<UpdateSurveyResponse>() {
            @Override
            public void onResponse(Call<UpdateSurveyResponse> call, Response<UpdateSurveyResponse> response) {

                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();

                if (response.body() != null) {

                    if (response.body().isStatus()) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(VendingDetailsActivity.this);
                        builder.setTitle("Vending Details");
                        builder.setMessage("Saved successfully");
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                startActivity(new Intent(VendingDetailsActivity.this, DocumentScanActivity.class));

                            }
                        });

                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();

//                        ApplicationConstant.displayToastMessage(VendingDetailsActivity.this,
//                                "Vending Details saved successfully");


                    } else {

                        ApplicationConstant.displayMessageDialog(VendingDetailsActivity.this,
                                "Response",
                                String.valueOf(response.body().isStatus()));
                    }

                }else {

                    try {
                        ApplicationConstant.displayMessageDialog(VendingDetailsActivity.this,
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
                ApplicationConstant.displayMessageDialog(VendingDetailsActivity.this, "Response", t.getMessage().toString());

            }
        });
    }


}
