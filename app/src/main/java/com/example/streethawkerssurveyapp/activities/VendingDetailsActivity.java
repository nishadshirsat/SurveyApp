package com.example.streethawkerssurveyapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
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

    private ProgressDialog progressDialog;

    private String
    URI_NO="",
    TYPE_OF_VENDING="",
    VENDING_SITE="",
    VENDING_FROM="",
    VENDING_TO="",
    YRS_OF_VENDING="",
    ANNUAL_INCOME="",
    IS_RECOGNIZED_STREET_VENDOR="",
    TYPE_OF_STRUCTURE="",
    STARTING_DATE_VENDING="",
    TEHABZARI_AVAILABLE="",
    VENDING_AREA_CHOCE="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vending_details);

        bindView();

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
                    mLinearOne.setVisibility(View.GONE);
                    mLinearTwo.setVisibility(View.VISIBLE);
                    mLinearThree.setVisibility(View.GONE);
                } else if (mLinearTwo.getVisibility() == View.VISIBLE) {

                    mLinearTwo.setVisibility(View.GONE);
                    mLinearOne.setVisibility(View.GONE);
                    mLinearThree.setVisibility(View.VISIBLE);

                }
                else {

                   URI_NO = ApplicationConstant.URI_NO;

                    UpdateSurvey();


                }


            }
        });
    }

    private void bindView() {

        mLinearMain = (LinearLayout) findViewById(R.id.LinearMain);
        mLinearOne = (LinearLayout) findViewById(R.id.LinearOne);
        mSpinnerItems = (Spinner) findViewById(R.id.SpinnerItems);
        mEditVendingSite = (EditText) findViewById(R.id.EditVendingSite);
        mEditFromTime = (EditText) findViewById(R.id.EditFromTime);
        mEditToTime = (EditText) findViewById(R.id.EditToTime);
        mEditAge = (EditText) findViewById(R.id.EditAge);
        mLinearTwo = (LinearLayout) findViewById(R.id.LinearTwo);
        mEditAnnualIncome = (EditText) findViewById(R.id.EditAnnualIncome);
        mRadioY = (RadioButton) findViewById(R.id.RadioY);
        mRadioN = (RadioButton) findViewById(R.id.RadioN);
        mSpinnerVehical = (Spinner) findViewById(R.id.SpinnerVehical);
        mEditDob = (EditText) findViewById(R.id.EditDob);
        mImgCalendar = (ImageView) findViewById(R.id.ImgCalendar);
        mLinearThree = (LinearLayout) findViewById(R.id.LinearThree);
        mRadioDY = (RadioButton) findViewById(R.id.RadioDY);
        mRadioDN = (RadioButton) findViewById(R.id.RadioDN);
        mSpinnerChoice = (Spinner) findViewById(R.id.SpinnerChoice);
        mBtnNext = (Button) findViewById(R.id.BtnNext);
        mBtnPrevious = (Button) findViewById(R.id.BtnPrevious);

    }

    private void UpdateSurvey() {

        progressDialog = CustomProgressDialog.getDialogue(VendingDetailsActivity.this);
        progressDialog.show();

        String username = PrefUtils.getFromPrefs(VendingDetailsActivity.this, ApplicationConstant.USERDETAILS.API_KEY, "");

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer "+PrefUtils.getFromPrefs(VendingDetailsActivity.this,ApplicationConstant.USERDETAILS.API_KEY,""));

        ApiInterface apiservice = ApiService.getApiClient().create(ApiInterface.class);
        Call<UpdateSurveyResponse> call = apiservice.getUpdateSurvey(headers,
                URI_NO,TYPE_OF_VENDING,VENDING_SITE,VENDING_FROM,VENDING_TO,YRS_OF_VENDING,
                ANNUAL_INCOME,IS_RECOGNIZED_STREET_VENDOR,TYPE_OF_STRUCTURE,STARTING_DATE_VENDING,
                TEHABZARI_AVAILABLE,VENDING_AREA_CHOCE

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
