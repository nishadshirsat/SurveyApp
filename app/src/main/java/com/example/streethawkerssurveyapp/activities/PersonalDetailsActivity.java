package com.example.streethawkerssurveyapp.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.streethawkerssurveyapp.BuildConfig;
import com.example.streethawkerssurveyapp.R;
import com.example.streethawkerssurveyapp.response_pack.SurveyResponse;
import com.example.streethawkerssurveyapp.services.AudioRecordService;
import com.example.streethawkerssurveyapp.services_pack.ApiInterface;
import com.example.streethawkerssurveyapp.services_pack.ApiService;
import com.example.streethawkerssurveyapp.services_pack.ApplicationConstant;
import com.example.streethawkerssurveyapp.services_pack.CustomProgressDialog;
import com.example.streethawkerssurveyapp.utils.PrefUtils;
import com.example.streethawkerssurveyapp.utils.SurveyAppFileProvider;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PersonalDetailsActivity extends MainActivity {

    private LinearLayout mLinearOne;
    private ImageView mImgVendorPhoto;
    private EditText mEditFName;
    private EditText mEditMName;
    private EditText mEditLName;
    private RadioButton mRadioM;
    private RadioButton mRadioF;
    private RadioButton mRadioO;
    private EditText mEditAge;
    private EditText mEditDob;
    private ImageView mImgCalendar;
    private LinearLayout mLinearTwo;
    private EditText mEditMobile;
    private EditText mEditLandline;
    private Spinner mSpinnerEducation;
    private EditText mEditFatherName;
    private EditText mEditFatherMName;
    private EditText mEditFatherLName;
    private EditText mEditMotherFName;
    private EditText mEditMotherMName;
    private EditText mEditMotherLName;
    private LinearLayout mLinearThree;
    private EditText mEditSpouceFName;
    private EditText mEditSpouceMName;
    private EditText mEditSpouceLName;
    private RadioButton mRadioY;
    private RadioButton mRadioN;
    private Spinner mSpinnerCategory;
    private EditText mEditArea;
    private EditText mEditHouseNo;
    private EditText mEditRoad;
    private EditText mEditCity;
    private EditText mEditPincode;
    private LinearLayout mLinearFour;
    private EditText mEditPArea;
    private EditText mEditPHouseNo;
    private EditText mEditPRoad;
    private EditText mEditPCity;
    private EditText mEditPPincode;
    private EditText mEditAadhar;
    private Button mBtnAddharCapture;
    private EditText mEditAccNo;
    private EditText mEditBankName;
    private EditText mEditBranchName;
    private EditText mEditIfscCode;
    private RadioButton mRadioCY;
    private RadioButton mRadioCN;
    private LinearLayout mLinearFive;
    private EditText mEditSNo;
    private EditText mEditDate;
    private EditText mEditFir;
    private EditText mEditNamePolice;
    private EditText mEditStatusCase;
    private Button mBtnNext;
    private String photoPath ="";

    Uri photoURI;

    ProgressDialog progressDialog;

    //Street Vendor Info

    private
    String

     NAME_VENDOR="",
     SEX="",
     AGE="",
     DOB="",
     CONTACT_NO="",
     LANDLINE_NO="",
     EDUCATION_STATUS="",
     NAME_OFFATHER_HUSBAND="",
     NAME_MOTHER="",
     NAME_SPOUSE="",
     WHETHER_WIDOWED="",
     CATEGORY="",
     RESIDENTIAL_ADDRESS="",
     PERMENENT_ADDRESS="",
     AADHAR_NO="",
     BANKACC_NO="",
     BANKNAME="",
     BRANCH_NAME="",
     IFSC="",
     IS_CRIMINALCASE="",
     CRIMINALCASE_NO="",
     CRIMINALCASE_DATE="",
     CRIMINALCASE_FIRNO="",
     CRIMINALCASE_POLICA_NAME="",
     CRIMINALCASE_STATUS="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindView();

       ApplicationConstant.SurveyId = PrefUtils.getFromPrefs(PersonalDetailsActivity.this,ApplicationConstant.SURVEY_ID,"");

       if (ApplicationConstant.SurveyId.trim().isEmpty()){
           ApplicationConstant.SurveyId = "1";
           PrefUtils.saveToPrefs(PersonalDetailsActivity.this,ApplicationConstant.SURVEY_ID,"1");
       }

       Intent intent = new Intent(PersonalDetailsActivity.this, AudioRecordService.class);
       intent.putExtra("FILE",ApplicationConstant.SurveyId);
       startService(intent);

       onCLickListners();

    }

    private void onCLickListners() {

        mImgVendorPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//          startActivityForResult(takePictureIntent, 1);

                // Ensure that there's a camera activity to handle the intent
                if (takePictureIntent.resolveActivity(PersonalDetailsActivity.this.getPackageManager()) != null) {
                    // Create the File where the photo should go
                    File photoFile = null;
                    try {
                        photoPath = ApplicationConstant.createImageFile("survey_profile.png", "Profile", PersonalDetailsActivity.this);
                        photoFile = new File(photoPath);
                    } catch (IOException ex) {
                        // Error occurred while creating the File
                    }
                    // Continue only if the File was successfully created
                    if (photoFile != null) {
                        photoURI = SurveyAppFileProvider.getUriForFile(PersonalDetailsActivity.this,
                                BuildConfig.APPLICATION_ID+".android.fileprovider",
                                photoFile);
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                        startActivityForResult(takePictureIntent, 1);

                    }
                }

            }
        });


        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mLinearOne.getVisibility() == View.VISIBLE) {
                    mLinearOne.setVisibility(View.GONE);
                    mLinearTwo.setVisibility(View.VISIBLE);
                    mLinearThree.setVisibility(View.GONE);
                    mLinearFour.setVisibility(View.GONE);
                    mLinearFive.setVisibility(View.GONE);
                } else if (mLinearTwo.getVisibility() == View.VISIBLE) {

                    mLinearTwo.setVisibility(View.GONE);
                    mLinearOne.setVisibility(View.GONE);
                    mLinearThree.setVisibility(View.VISIBLE);
                    mLinearFour.setVisibility(View.GONE);
                    mLinearFive.setVisibility(View.GONE);

                } else if (mLinearThree.getVisibility() == View.VISIBLE) {

                    mLinearThree.setVisibility(View.GONE);
                    mLinearOne.setVisibility(View.GONE);
                    mLinearTwo.setVisibility(View.GONE);
                    mLinearFour.setVisibility(View.VISIBLE);
                    mLinearFive.setVisibility(View.GONE);

                } else if (mLinearFour.getVisibility() == View.VISIBLE) {

                    mLinearFour.setVisibility(View.GONE);
                    mLinearOne.setVisibility(View.GONE);
                    mLinearThree.setVisibility(View.GONE);
                    mLinearTwo.setVisibility(View.GONE);
                    mLinearFive.setVisibility(View.VISIBLE);

                } else {

//                    mLinearFive.setVisibility(View.VISIBLE);
//                    mLinearOne.setVisibility(View.GONE);
//                    mLinearThree.setVisibility(View.GONE);
//                    mLinearTwo.setVisibility(View.GONE);
//                    mLinearFour.setVisibility(View.GONE);


//                    NAME_VENDOR = mEditFName.getText().toString().trim()+" "
//                            + mEditMName.getText().toString().trim()+" "
//                            +  mEditLName.getText().toString().trim();
//
                    AddSurvey();


                }


            }
        });

    }

    private void bindView() {

        mLinearOne = (LinearLayout) findViewById(R.id.LinearOne);
        mImgVendorPhoto = (ImageView) findViewById(R.id.ImgVendorPhoto);
        mEditFName = (EditText) findViewById(R.id.EditFName);
        mEditMName = (EditText) findViewById(R.id.EditMName);
        mEditLName = (EditText) findViewById(R.id.EditLName);
        mRadioM = (RadioButton) findViewById(R.id.RadioM);
        mRadioF = (RadioButton) findViewById(R.id.RadioF);
        mRadioO = (RadioButton) findViewById(R.id.RadioO);
        mEditAge = (EditText) findViewById(R.id.EditAge);
        mEditDob = (EditText) findViewById(R.id.EditDob);
        mImgCalendar = (ImageView) findViewById(R.id.ImgCalendar);
        mLinearTwo = (LinearLayout) findViewById(R.id.LinearTwo);
        mEditMobile = (EditText) findViewById(R.id.EditMobile);
        mEditLandline = (EditText) findViewById(R.id.EditLandline);
        mSpinnerEducation = (Spinner) findViewById(R.id.SpinnerEducation);
        mEditFatherName = (EditText) findViewById(R.id.EditFatherName);
        mEditFatherMName = (EditText) findViewById(R.id.EditFatherMName);
        mEditFatherLName = (EditText) findViewById(R.id.EditFatherLName);
        mEditMotherFName = (EditText) findViewById(R.id.EditMotherFName);
        mEditMotherMName = (EditText) findViewById(R.id.EditMotherMName);
        mEditMotherLName = (EditText) findViewById(R.id.EditMotherLName);
        mLinearThree = (LinearLayout) findViewById(R.id.LinearThree);
        mEditSpouceFName = (EditText) findViewById(R.id.EditSpouceFName);
        mEditSpouceMName = (EditText) findViewById(R.id.EditSpouceMName);
        mEditSpouceLName = (EditText) findViewById(R.id.EditSpouceLName);
        mRadioY = (RadioButton) findViewById(R.id.RadioY);
        mRadioN = (RadioButton) findViewById(R.id.RadioN);
        mSpinnerCategory = (Spinner) findViewById(R.id.SpinnerCategory);
        mEditArea = (EditText) findViewById(R.id.EditArea);
        mEditHouseNo = (EditText) findViewById(R.id.EditHouseNo);
        mEditRoad = (EditText) findViewById(R.id.EditRoad);
        mEditCity = (EditText) findViewById(R.id.EditCity);
        mEditPincode = (EditText) findViewById(R.id.EditPincode);
        mLinearFour = (LinearLayout) findViewById(R.id.LinearFour);
        mEditPArea = (EditText) findViewById(R.id.EditPArea);
        mEditPHouseNo = (EditText) findViewById(R.id.EditPHouseNo);
        mEditPRoad = (EditText) findViewById(R.id.EditPRoad);
        mEditPCity = (EditText) findViewById(R.id.EditPCity);
        mEditPPincode = (EditText) findViewById(R.id.EditPPincode);
        mEditAadhar = (EditText) findViewById(R.id.EditAadhar);
        mBtnAddharCapture = (Button) findViewById(R.id.BtnAddharCapture);
        mEditAccNo = (EditText) findViewById(R.id.EditAccNo);
        mEditBankName = (EditText) findViewById(R.id.EditBankName);
        mEditBranchName = (EditText) findViewById(R.id.EditBranchName);
        mEditIfscCode = (EditText) findViewById(R.id.EditIfscCode);
        mRadioCY = (RadioButton) findViewById(R.id.RadioCY);
        mRadioCN = (RadioButton) findViewById(R.id.RadioCN);
        mLinearFive = (LinearLayout) findViewById(R.id.LinearFive);
        mEditSNo = (EditText) findViewById(R.id.EditSNo);
        mEditDate = (EditText) findViewById(R.id.EditDate);
        mEditFir = (EditText) findViewById(R.id.EditFir);
        mEditNamePolice = (EditText) findViewById(R.id.EditNamePolice);
        mEditStatusCase = (EditText) findViewById(R.id.EditStatusCase);
        mBtnNext = (Button) findViewById(R.id.BtnNext);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            if (requestCode == 1) {

                Glide.with(PersonalDetailsActivity.this).load(photoURI)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                        .into(mImgVendorPhoto);

            }
        }

    }

    private void AddSurvey() {

        progressDialog = CustomProgressDialog.getDialogue(PersonalDetailsActivity.this);
        progressDialog.show();

        String username = PrefUtils.getFromPrefs(PersonalDetailsActivity.this, ApplicationConstant.USERDETAILS.API_KEY, "");


        File file1 = new File(photoPath);

        RequestBody request_photo =
                RequestBody.create(MediaType.parse("image/png"), file1);


// MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body_fhoto =
                MultipartBody.Part.createFormData("photo_of_the_street_vendor", file1.getName(), request_photo);

        RequestBody NAME_VENDOR_ = RequestBody.create(MediaType.parse("multipart/form-data"), NAME_VENDOR);
        RequestBody SURVEY_ID_ = RequestBody.create(MediaType.parse("multipart/form-data"), ApplicationConstant.SurveyId);
        RequestBody SEX_ = RequestBody.create(MediaType.parse("multipart/form-data"), SEX);
        RequestBody AGE_ = RequestBody.create(MediaType.parse("multipart/form-data"), AGE);
        RequestBody DOB_ = RequestBody.create(MediaType.parse("multipart/form-data"), DOB);
        RequestBody CONTACT_NO_ = RequestBody.create(MediaType.parse("multipart/form-data"), CONTACT_NO);
        RequestBody LANDLINE_NO_ = RequestBody.create(MediaType.parse("multipart/form-data"), LANDLINE_NO);
        RequestBody EDUCATION_STATUS_ = RequestBody.create(MediaType.parse("multipart/form-data"), EDUCATION_STATUS);
        RequestBody NAME_OFFATHER_HUSBAND_ = RequestBody.create(MediaType.parse("multipart/form-data"), NAME_OFFATHER_HUSBAND);
        RequestBody NAME_MOTHER_ = RequestBody.create(MediaType.parse("multipart/form-data"), NAME_MOTHER);
        RequestBody NAME_SPOUSE_ = RequestBody.create(MediaType.parse("multipart/form-data"), NAME_SPOUSE);
        RequestBody WHETHER_WIDOWED_ = RequestBody.create(MediaType.parse("multipart/form-data"), WHETHER_WIDOWED);
        RequestBody CATEGORY_ = RequestBody.create(MediaType.parse("multipart/form-data"), CATEGORY);
        RequestBody RESIDENTIAL_ADDRESS_ = RequestBody.create(MediaType.parse("multipart/form-data"), RESIDENTIAL_ADDRESS);
        RequestBody PERMENENT_ADDRESS_ = RequestBody.create(MediaType.parse("multipart/form-data"), PERMENENT_ADDRESS);
        RequestBody AADHAR_NO_ = RequestBody.create(MediaType.parse("multipart/form-data"), AADHAR_NO);
        RequestBody BANKACC_NO_ = RequestBody.create(MediaType.parse("multipart/form-data"), BANKACC_NO);
        RequestBody BANKNAME_ = RequestBody.create(MediaType.parse("multipart/form-data"), BANKNAME);
        RequestBody BRANCH_NAME_ = RequestBody.create(MediaType.parse("multipart/form-data"), BRANCH_NAME);
        RequestBody IFSC_ = RequestBody.create(MediaType.parse("multipart/form-data"), IFSC);
        RequestBody IS_CRIMINALCASE_ = RequestBody.create(MediaType.parse("multipart/form-data"), IS_CRIMINALCASE);
        RequestBody CRIMINALCASE_NO_ = RequestBody.create(MediaType.parse("multipart/form-data"), CRIMINALCASE_NO);
        RequestBody CRIMINALCASE_DATE_ = RequestBody.create(MediaType.parse("multipart/form-data"), CRIMINALCASE_DATE);
        RequestBody CRIMINALCASE_FIRNO_ = RequestBody.create(MediaType.parse("multipart/form-data"), CRIMINALCASE_FIRNO);
        RequestBody CRIMINALCASE_POLICA_NAME_ = RequestBody.create(MediaType.parse("multipart/form-data"), CRIMINALCASE_POLICA_NAME);
        RequestBody CRIMINALCASE_STATUS_ = RequestBody.create(MediaType.parse("multipart/form-data"), CRIMINALCASE_STATUS);


        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer "+PrefUtils.getFromPrefs(PersonalDetailsActivity.this,ApplicationConstant.USERDETAILS.API_KEY,""));

        ApiInterface apiservice = ApiService.getApiClient().create(ApiInterface.class);
        Call<SurveyResponse> call = apiservice.getAddSurvey(headers,
                body_fhoto,SURVEY_ID_
                ,NAME_VENDOR_,SEX_,AGE_
                ,DOB_,CONTACT_NO_,LANDLINE_NO_,EDUCATION_STATUS_,
                NAME_OFFATHER_HUSBAND_,NAME_MOTHER_,
                NAME_SPOUSE_,WHETHER_WIDOWED_,CATEGORY_,
                RESIDENTIAL_ADDRESS_,PERMENENT_ADDRESS_,AADHAR_NO_,
                BANKACC_NO_,BANKNAME_,BRANCH_NAME_,
                IFSC_,IS_CRIMINALCASE_,CRIMINALCASE_NO_,CRIMINALCASE_DATE_,
                CRIMINALCASE_FIRNO_,CRIMINALCASE_POLICA_NAME_,CRIMINALCASE_STATUS_
                );

        call.enqueue(new Callback<SurveyResponse>() {
            @Override
            public void onResponse(Call<SurveyResponse> call, Response<SurveyResponse> response) {

                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();

                if (response.body() != null) {

                    if (response.body().isStatus()) {

                        ApplicationConstant.displayToastMessage(PersonalDetailsActivity.this,
                                "Personal Details saved successfully");

                        ApplicationConstant.URI_NO = response.body().getUriNumber();
                        startActivity(new Intent(PersonalDetailsActivity.this, VendorsFamDetailsActivity.class));


                    } else {

                        ApplicationConstant.displayMessageDialog(PersonalDetailsActivity.this,
                                "Response",
                                String.valueOf(response.body().isStatus()));
                    }

                }else {

                    try {
                        ApplicationConstant.displayMessageDialog(PersonalDetailsActivity.this,
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
                ApplicationConstant.displayMessageDialog(PersonalDetailsActivity.this, "Response", t.getMessage().toString());

            }
        });
    }
}
