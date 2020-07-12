package com.example.streethawkerssurveyapp.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
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
import android.widget.TextView;
import android.widget.Toast;

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
import com.example.streethawkerssurveyapp.utils.GetLocation;
import com.example.streethawkerssurveyapp.utils.PrefUtils;
import com.example.streethawkerssurveyapp.utils.SurveyAppFileProvider;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class PersonalDetailsActivity extends MainActivity {

    private LinearLayout mLinearOne;
    private ImageView mImgVendorPhoto;
    private EditText mEditFName;
    private EditText mEditMName;
    private EditText mEditLName;
//    private RadioButton mRadioM;
//    private RadioButton mRadioF;
//    private RadioButton mRadioO;
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
//    private RadioButton mRadioY;
//    private RadioButton mRadioN;
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
//    private RadioButton mRadioCY;
//    private RadioButton mRadioCN;
    private LinearLayout mLinearFive;
    private EditText mEditSNo;
    private EditText mEditDate;
    private EditText mEditFir;
    private EditText mEditNamePolice;
    private EditText mEditStatusCase;
    private Button mBtnNext,mBtnPrevious;
    private String photoPath = "";
    RadioGroup RGSex;
    RadioGroup RGWidow;
    RadioGroup RGCriminal;
    boolean doubleBackToExitPressedOnce = false;


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


    private Calendar myCalendar;
    private int mYear, mMonth, mDay;

    public GetLocation getLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindView();

        if (getLocation == null) {
            getLocation = new GetLocation(PersonalDetailsActivity.this);
        }

        myCalendar = Calendar.getInstance();
        mYear = myCalendar.get(Calendar.YEAR);
        mMonth = myCalendar.get(Calendar.MONTH);
        mDay = myCalendar.get(Calendar.DAY_OF_MONTH);

        onCLickListners();
        ApplicationConstant.SurveyId = PrefUtils.getFromPrefs(PersonalDetailsActivity.this, ApplicationConstant.SURVEY_ID, "");

        if (ApplicationConstant.SurveyId.trim().isEmpty()) {
            ApplicationConstant.SurveyId = "1.0";
            PrefUtils.saveToPrefs(PersonalDetailsActivity.this, ApplicationConstant.SURVEY_ID, "1.0");
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
                        photoPath = ApplicationConstant.createImageFile(ApplicationConstant.SurveyId+"_profile.png", "Profile", PersonalDetailsActivity.this);
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

        mBtnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mLinearFive.getVisibility() == View.VISIBLE) {

                        mLinearFour.setVisibility(View.VISIBLE);
                        mLinearOne.setVisibility(View.GONE);
                        mLinearThree.setVisibility(View.GONE);
                        mLinearTwo.setVisibility(View.GONE);
                        mLinearFive.setVisibility(View.GONE);

                } else
                if (mLinearFour.getVisibility() == View.VISIBLE) {

                    mLinearFour.setVisibility(View.GONE);
                    mLinearOne.setVisibility(View.GONE);
                    mLinearThree.setVisibility(View.VISIBLE);
                    mLinearTwo.setVisibility(View.GONE);
                    mLinearFive.setVisibility(View.GONE);

                } else
                if (mLinearThree.getVisibility() == View.VISIBLE) {

                    mLinearFour.setVisibility(View.GONE);
                    mLinearOne.setVisibility(View.GONE);
                    mLinearThree.setVisibility(View.GONE);
                    mLinearTwo.setVisibility(View.VISIBLE);
                    mLinearFive.setVisibility(View.GONE);

                } else
                if (mLinearTwo.getVisibility() == View.VISIBLE) {

                    mLinearFour.setVisibility(View.GONE);
                    mLinearOne.setVisibility(View.VISIBLE);
                    mLinearThree.setVisibility(View.GONE);
                    mLinearTwo.setVisibility(View.GONE);
                    mLinearFive.setVisibility(View.GONE);

                    mBtnPrevious.setVisibility(View.GONE);

                } else {

                   onBackPressed();

                }

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
                        mLinearFour.setVisibility(View.GONE);
                        mLinearFive.setVisibility(View.GONE);

                        mBtnPrevious.setVisibility(View.VISIBLE);

                    }

                } else if (mLinearTwo.getVisibility() == View.VISIBLE) {

                    if (validate2()) {
                        mLinearTwo.setVisibility(View.GONE);
                        mLinearOne.setVisibility(View.GONE);
                        mLinearThree.setVisibility(View.VISIBLE);
                        mLinearFour.setVisibility(View.GONE);
                        mLinearFive.setVisibility(View.GONE);
                    }

                } else if (mLinearThree.getVisibility() == View.VISIBLE) {

                    if (validate3()) {
                        mLinearThree.setVisibility(View.GONE);
                        mLinearOne.setVisibility(View.GONE);
                        mLinearTwo.setVisibility(View.GONE);
                        mLinearFour.setVisibility(View.VISIBLE);
                        mLinearFive.setVisibility(View.GONE);
                    }

                } else if (mLinearFour.getVisibility() == View.VISIBLE) {

                    if (validate4()) {
                        mLinearFour.setVisibility(View.GONE);
                        mLinearOne.setVisibility(View.GONE);
                        mLinearThree.setVisibility(View.GONE);
                        mLinearTwo.setVisibility(View.GONE);
                        mLinearFive.setVisibility(View.VISIBLE);

                        mBtnNext.setText("Submit");
                    }
                } else {

//                    mLinearFive.setVisibility(View.VISIBLE);
//                    mLinearOne.setVisibility(View.GONE);
//                    mLinearThree.setVisibility(View.GONE);
//                    mLinearTwo.setVisibility(View.GONE);
//                    mLinearFour.setVisibility(View.GONE);


                    NAME_VENDOR = mEditFName.getText().toString().trim() + " "
                            + mEditMName.getText().toString().trim() + " "
                            + mEditLName.getText().toString().trim();
//                    SEX = String.valueOf(RGSex.getCheckedRadioButtonId());
                    SEX = "M";
                    AGE = mEditAge.getText().toString().trim();
//                    DOB = mEditDob.getText().toString().trim();
                    CONTACT_NO = mEditMobile.getText().toString().trim();
                    LANDLINE_NO = mEditLandline.getText().toString().trim();
                    mSpinnerEducation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            EDUCATION_STATUS = mSpinnerEducation.getItemAtPosition(position).toString();

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                    NAME_OFFATHER_HUSBAND = mEditFatherName.getText().toString().trim() + " "
                            + mEditFatherMName.getText().toString().trim() + " "
                            + mEditFatherLName.getText().toString().trim();

                    NAME_MOTHER = mEditMotherFName.getText().toString().trim() + " "
                            + mEditMotherMName.getText().toString().trim() + " "
                            + mEditMotherLName.getText().toString().trim();

                    NAME_SPOUSE = mEditSpouceFName.getText().toString().trim() + " "
                            + mEditSpouceMName.getText().toString().trim() + " "
                            + mEditSpouceLName.getText().toString().trim();

                    WHETHER_WIDOWED = String.valueOf(RGWidow.getCheckedRadioButtonId());
                    mSpinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                            CATEGORY = mSpinnerCategory.getItemAtPosition(position).toString();

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                    RESIDENTIAL_ADDRESS = mEditArea.getText().toString().trim() + " "
                            + mEditHouseNo.getText().toString().trim() + " "
                            + mEditRoad.getText().toString().trim()
                            + mEditCity.getText().toString().trim()
                            + mEditPincode.getText().toString().trim();

                    PERMENENT_ADDRESS = mEditPArea.getText().toString().trim() + " "
                            + mEditPHouseNo.getText().toString().trim() + " "
                            + mEditPRoad.getText().toString().trim()
                            + mEditPCity.getText().toString().trim()
                            + mEditPPincode.getText().toString().trim();

                    AADHAR_NO = mEditAadhar.getText().toString().trim();
                    BANKACC_NO = mEditAccNo.getText().toString().trim();
                    BANKNAME = mEditBankName.getText().toString().trim();
                    IFSC = mEditIfscCode.getText().toString().trim();

//                    IS_CRIMINALCASE = String.valueOf(RGCriminal.getCheckedRadioButtonId());
                    IS_CRIMINALCASE = "1";
                    CRIMINALCASE_NO = mEditSNo.getText().toString().trim();
//                    CRIMINALCASE_DATE = mEditDate.getText().toString().trim();
                    CRIMINALCASE_FIRNO = mEditFir.getText().toString().trim();
                    CRIMINALCASE_POLICA_NAME = mEditNamePolice.getText().toString().trim();
                    CRIMINALCASE_STATUS = mEditStatusCase.getText().toString().trim();


                    if (validate()) {

                        if (getLocation.getLatitude() > 0.0D && getLocation.getLongitude() > 0.0D) {
                            AddSurvey();

                        }else {
                            ApplicationConstant.displayMessageDialog(PersonalDetailsActivity.this,"","Unable to get Location please check GPS enabled and permissions check");
                        }
                    }
//                    startActivity(new Intent(PersonalDetailsActivity.this, VendorsFamDetailsActivity.class));

                }


            }
        });

        mImgCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(new ContextThemeWrapper(PersonalDetailsActivity.this, R.style.DialogTheme),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                mEditDob.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);


                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });

        mEditDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(new ContextThemeWrapper(PersonalDetailsActivity.this, R.style.DialogTheme),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                mEditDate.setText((monthOfYear + 1) + "/" + dayOfMonth + "/" + year);


                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });

    }

    private boolean validate4() {
        if (!ApplicationConstant.isNetworkAvailable(PersonalDetailsActivity.this)) {

            ApplicationConstant.displayMessageDialog(PersonalDetailsActivity.this, "No Internet Connection", "Please enable internet connection first to proceed");

            return false;
        } else if (mEditPArea.getText().toString().trim().isEmpty()) {
            mEditPArea.setError("Enter Permanant Area");
            mEditPArea.requestFocus();
            return false;
        } else if (mEditPHouseNo.getText().toString().trim().isEmpty()) {
            mEditPHouseNo.setError("Enter Permanant House No");
            mEditPHouseNo.requestFocus();
            return false;
        } else if (mEditPRoad.getText().toString().trim().isEmpty()) {
            mEditPRoad.setError("Enter Permanant Road");
            mEditPRoad.requestFocus();
            return false;
        } else if (mEditPCity.getText().toString().trim().isEmpty()) {
            mEditPCity.setError("Enter Permanant City");
            mEditPCity.requestFocus();
            return false;
        } else if (mEditPPincode.getText().toString().trim().isEmpty()) {
            mEditPPincode.setError("Enter Permanant Pincode");
            mEditPPincode.requestFocus();
            return false;
        } else if (mEditAadhar.getText().toString().trim().isEmpty()) {
            mEditAadhar.setError("Enter Aadhar Number");
            mEditAadhar.requestFocus();
            return false;
        } else if (mEditAccNo.getText().toString().trim().isEmpty()) {
            mEditAccNo.setError("Enter Account No");
            mEditAccNo.requestFocus();
            return false;
        } else if (mEditBankName.getText().toString().trim().isEmpty()) {
            mEditBankName.setError("Enter Bank Name");
            mEditBankName.requestFocus();
            return false;
        } else if (mEditBranchName.getText().toString().trim().isEmpty()) {
            mEditBranchName.setError("Enter Branch Name");
            mEditBranchName.requestFocus();
            return false;
        } else if (mEditIfscCode.getText().toString().trim().isEmpty()) {
            mEditIfscCode.setError("Enter IFSC Code");
            mEditIfscCode.requestFocus();
            return false;
        }

        return true;
    }

    private boolean validate3() {
        if (!ApplicationConstant.isNetworkAvailable(PersonalDetailsActivity.this)) {

            ApplicationConstant.displayMessageDialog(PersonalDetailsActivity.this, "No Internet Connection", "Please enable internet connection first to proceed");

            return false;
        } else if (mSpinnerCategory.getSelectedItem().toString().trim().isEmpty()) {
            mEditArea.setError("Select Category");
            mSpinnerCategory.requestFocus();
            return false;
        } else if (mEditArea.getText().toString().trim().isEmpty()) {
            mEditArea.setError("Enter Area");
            mEditArea.requestFocus();
            return false;
        } else if (mEditHouseNo.getText().toString().trim().isEmpty()) {
            mEditHouseNo.setError("Enter House No");
            mEditHouseNo.requestFocus();
            return false;
        } else if (mEditRoad.getText().toString().trim().isEmpty()) {
            mEditRoad.setError("Enter Road");
            mEditRoad.requestFocus();
            return false;
        } else if (mEditCity.getText().toString().trim().isEmpty()) {
            mEditCity.setError("Enter City");
            mEditCity.requestFocus();
            return false;
        } else if (mEditPincode.getText().toString().trim().isEmpty()) {
            mEditPincode.setError("Enter Pincode");
            mEditPincode.requestFocus();
            return false;
        }

        return true;
    }

    private boolean validate2() {

        if (!ApplicationConstant.isNetworkAvailable(PersonalDetailsActivity.this)) {

            ApplicationConstant.displayMessageDialog(PersonalDetailsActivity.this, "No Internet Connection", "Please enable internet connection first to proceed");

            return false;
        } else if (mEditMobile.getText().toString().trim().isEmpty()) {
            mEditMobile.setError("Enter Mobile Number");
            mEditMobile.requestFocus();
            return false;
        } else if (mSpinnerEducation.getSelectedItem().toString().trim().isEmpty()) {
            mEditFatherName.setError("Select Education");
            mSpinnerEducation.requestFocus();
            return false;
        } else if (mEditFatherName.getText().toString().trim().isEmpty()) {
            mEditFatherName.setError("Enter Father First Name");
            mEditFatherName.requestFocus();
            return false;
        } else if (mEditFatherMName.getText().toString().trim().isEmpty()) {
            mEditFatherMName.setError("Enter Father Middle Name");
            mEditFatherMName.requestFocus();
            return false;
        } else if (mEditFatherLName.getText().toString().trim().isEmpty()) {
            mEditFatherLName.setError("Enter Father Last Name");
            mEditFatherLName.requestFocus();
            return false;
        } else if (mEditMotherFName.getText().toString().trim().isEmpty()) {
            mEditMotherFName.setError("Enter Mother First Name");
            mEditMotherFName.requestFocus();
            return false;
        } else if (mEditMotherMName.getText().toString().trim().isEmpty()) {
            mEditMotherMName.setError("Enter Mother Middle Name");
            mEditMotherMName.requestFocus();
            return false;
        } else if (mEditMotherLName.getText().toString().trim().isEmpty()) {
            mEditMotherLName.setError("Enter Mother Last Name");
            mEditMotherLName.requestFocus();
            return false;
        }

        return true;

    }

    private boolean validate1() {

        if (!ApplicationConstant.isNetworkAvailable(PersonalDetailsActivity.this)) {

            ApplicationConstant.displayMessageDialog(PersonalDetailsActivity.this, "No Internet Connection", "Please enable internet connection first to proceed");

            return false;
        }   else if (photoPath.isEmpty()) {
            ApplicationConstant.displayMessageDialog(PersonalDetailsActivity.this, "", "Capture profile photo");

            return false;
        }

        else if (mEditFName.getText().toString().trim().isEmpty()) {
            mEditFName.setError("Enter First Name");
            mEditFName.requestFocus();
            return false;
        } else if (mEditMName.getText().toString().trim().isEmpty()) {
            mEditMName.setError("Enter Middle Name");
            mEditMName.requestFocus();
            return false;
        } else if (mEditLName.getText().toString().trim().isEmpty()) {
            mEditLName.setError("Enter Last Name");
            mEditLName.requestFocus();
            return false;
        } else if (mEditAge.getText().toString().trim().isEmpty()) {
            mEditAge.setError("Enter Age");
            mEditAge.requestFocus();
            return false;
        } else if (mEditDob.getText().toString().trim().isEmpty()) {
            mEditDob.setError("Enter Date Of Birth");
            mEditDob.requestFocus();
            return false;
        }

        return true;
    }

    private boolean validate() {
        if (!ApplicationConstant.isNetworkAvailable(PersonalDetailsActivity.this)) {

            ApplicationConstant.displayMessageDialog(PersonalDetailsActivity.this, "No Internet Connection", "Please enable internet connection first to proceed");

            return false;
        } else if (mEditSNo.getText().toString().trim().isEmpty()) {
            mEditSNo.setError("Enter S No");
            mEditSNo.requestFocus();
            return false;
        } else if (mEditDate.getText().toString().trim().isEmpty()) {
            mEditDate.setError("Enter Date");
            mEditDate.requestFocus();
            return false;
        } else if (mEditFir.getText().toString().trim().isEmpty()) {
            mEditFir.setError("Enter FIR");
            mEditFir.requestFocus();
            return false;
        } else if (mEditNamePolice.getText().toString().trim().isEmpty()) {
            mEditNamePolice.setError("Enter Police Name");
            mEditNamePolice.requestFocus();
            return false;
        } else if (mEditStatusCase.getText().toString().trim().isEmpty()) {
            mEditStatusCase.setError("Enter Status Case");
            mEditStatusCase.requestFocus();
            return false;
        }else if (!getLocation.isGPSEnabled) {
        this.getLocation.showSettingsAlert();
        return false;
    }



        return true;

    }

    private void bindView() {

        mLinearOne = (LinearLayout) findViewById(R.id.LinearOne);
        RGSex = (RadioGroup) findViewById(R.id.RGSex);
        RGWidow = (RadioGroup) findViewById(R.id.RGWidow);
        RGCriminal = (RadioGroup) findViewById(R.id.RGCriminal);
        mImgVendorPhoto = (ImageView) findViewById(R.id.ImgVendorPhoto);
        mEditFName = (EditText) findViewById(R.id.EditFName);
        mEditMName = (EditText) findViewById(R.id.EditMName);
        mEditLName = (EditText) findViewById(R.id.EditLName);
//        mRadioM = (RadioButton) findViewById(Integer.parseInt(SEX));
//        mRadioF = (RadioButton) findViewById(Integer.parseInt(SEX));
//        mRadioO = (RadioButton) findViewById(Integer.parseInt(SEX));
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
//        mRadioY = (RadioButton) findViewById(Integer.parseInt(WHETHER_WIDOWED));
//        mRadioN = (RadioButton) findViewById(Integer.parseInt(WHETHER_WIDOWED));
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
//        mRadioCY = (RadioButton) findViewById(Integer.parseInt(IS_CRIMINALCASE));
//        mRadioCN = (RadioButton) findViewById(Integer.parseInt(IS_CRIMINALCASE));
        mLinearFive = (LinearLayout) findViewById(R.id.LinearFive);
        mEditSNo = (EditText) findViewById(R.id.EditSNo);
        mEditDate = (EditText) findViewById(R.id.EditDate);
        mEditFir = (EditText) findViewById(R.id.EditFir);
        mEditNamePolice = (EditText) findViewById(R.id.EditNamePolice);
        mEditStatusCase = (EditText) findViewById(R.id.EditStatusCase);
        mBtnNext = (Button) findViewById(R.id.BtnNext);
        mBtnPrevious = (Button) findViewById(R.id.BtnPrevious);
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

        RequestBody LATITUDE = RequestBody.create(MediaType.parse("multipart/form-data"), ""+getLocation.getLatitude());
        RequestBody LONGITUDE = RequestBody.create(MediaType.parse("multipart/form-data"), ""+getLocation.getLongitude());


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
                CRIMINALCASE_FIRNO_,CRIMINALCASE_POLICA_NAME_,LATITUDE,LONGITUDE,CRIMINALCASE_STATUS_
                );

        call.enqueue(new Callback<SurveyResponse>() {
            @Override
            public void onResponse(Call<SurveyResponse> call, Response<SurveyResponse> response) {

                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();

                if (response.body() != null) {

                    if (response.body().isStatus()) {

                        ApplicationConstant.URI_NO = response.body().getUriNumber();

                        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(PersonalDetailsActivity.this);
                        builder.setTitle("Personal Details");
                        builder.setMessage("Saved successfully");
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                startActivity(new Intent(PersonalDetailsActivity.this,VendorsFamDetailsActivity.class));

                            }
                        });

                        AlertDialog alertDialog = builder.create();
                        alertDialog.setCancelable(false);
                        alertDialog.setCanceledOnTouchOutside(false);
                        alertDialog.show();



//                        ApplicationConstant.displayToastMessage(PersonalDetailsActivity.this,
//                                "Personal Details saved successfully");



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

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
