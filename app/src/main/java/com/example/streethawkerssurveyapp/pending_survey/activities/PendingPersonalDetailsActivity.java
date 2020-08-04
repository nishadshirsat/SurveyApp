package com.example.streethawkerssurveyapp.pending_survey.activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.streethawkerssurveyapp.BuildConfig;
import com.example.streethawkerssurveyapp.R;
import com.example.streethawkerssurveyapp.activities.MainActivity;
import com.example.streethawkerssurveyapp.activities.VendorsFamDetailsActivity;
import com.example.streethawkerssurveyapp.view_survey.adapters.ViewCriminalCasesAdpater;
import com.example.streethawkerssurveyapp.pojo_class.CriminalCases;
import com.example.streethawkerssurveyapp.response_pack.UpdateSurveyResponse;
import com.example.streethawkerssurveyapp.response_pack.aadhar_response.AadharData;
import com.example.streethawkerssurveyapp.response_pack.aadhar_response.AadharOtpData;
import com.example.streethawkerssurveyapp.response_pack.aadhar_response.AadharOtpResponse;
import com.example.streethawkerssurveyapp.response_pack.aadhar_response.AadharValidResponse;
import com.example.streethawkerssurveyapp.services_pack.ApiInterface;
import com.example.streethawkerssurveyapp.services_pack.ApiService;
import com.example.streethawkerssurveyapp.services_pack.ApplicationConstant;
import com.example.streethawkerssurveyapp.services_pack.CustomProgressDialog;
import com.example.streethawkerssurveyapp.utils.GetLocation;
import com.example.streethawkerssurveyapp.utils.PrefUtils;
import com.example.streethawkerssurveyapp.utils.SurveyAppFileProvider;
import com.example.streethawkerssurveyapp.pending_survey.activities.PendingPersonalDetailsActivity;
import com.example.streethawkerssurveyapp.view_survey.response_pojo.CriminalCaseDetailsItem;
import com.example.streethawkerssurveyapp.view_survey.response_pojo.SingleSurveyDetails;
import com.example.streethawkerssurveyapp.view_survey.response_pojo.SingleSurveyResponse;
import com.example.streethawkerssurveyapp.view_survey.services.ViewSurveyInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PendingPersonalDetailsActivity extends MainActivity {

    private LinearLayout mLinearOne;
    private ImageView mImgVendorPhoto;
    private ImageView mImgVendorSite;
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
    private EditText mEditAnnualIncome;

    private Button mBtnAddharCapture;
    private TextView BtnAddharVerified;

//    private EditText mEditAccNo;
//    private EditText mEditBankName;
//    private EditText mEditBranchName;
//    private EditText mEditIfscCode;

    private RadioButton mRadioCY;
    private RadioButton mRadioCN;
    private LinearLayout mLinearFive;
    private LinearLayout mLinearHead;

//    private EditText mEditSNo;
//    private EditText mEditDate;
//    private EditText mEditFir;
//    private EditText mEditNamePolice;
//    private EditText mEditStatusCase;

    private Button mBtnNext, mBtnPrevious;
    private String photoPath = "";
    private String VindingPhotoPath = "";
    RadioGroup RGSex;
    RadioGroup RGWidow;
    RadioGroup RGCriminal;
    int radioId;


    Uri photoURI;

    ProgressDialog progressDialog;

    //Street Vendor Info

    private
    String

            NAME_VENDOR = "",
            SEX = "",
            AGE = "",
            DOB = "",
            CONTACT_NO = "",
            LANDLINE_NO = "",
            EDUCATION_STATUS = "",
            NAME_OFFATHER_HUSBAND = "",
            NAME_MOTHER = "",
            NAME_SPOUSE = "",
            WHETHER_WIDOWED = "",
            CATEGORY = "",
            RESIDENTIAL_ADDRESS = "",
            PERMENENT_ADDRESS = "",
            AADHAR_NO = "",
            ANNUAL_INCOME = "",


//            BANKACC_NO = "",
//            BANKNAME = "",
//            BRANCH_NAME = "",
//            IFSC = "",

            IS_CRIMINALCASE = "",
            CRIMINALCASE_NO = "";
//            CRIMINALCASE_DATE = "",
//            CRIMINALCASE_FIRNO = "",
//            CRIMINALCASE_POLICA_NAME = "",
//            CRIMINALCASE_STATUS = "";


    private Calendar myCalendar;
    private int mYear, mMonth, mDay;

    public GetLocation getLocation;

    public double Latitude = 0.0;
    public double Longitude = 0.0;

    private LinearLayout linear_cases;

    private RecyclerView view_Criminal_Case;
    private TextView TextAddCases;
    private List<CriminalCaseDetailsItem> listCriminalCases = new ArrayList<>();

    private TextView btn_same_resident;
    private String AADHAR_DETAILS="";

    public static int orientation;
    private SingleSurveyDetails SingleSurveyData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindView();

        ApplicationConstant.SurveyId = PrefUtils.getFromPrefs(PendingPersonalDetailsActivity.this, ApplicationConstant.SURVEY_ID, "");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("URI NO: "+ApplicationConstant.SurveyId);

        if (getLocation == null) {
            getLocation = new GetLocation(PendingPersonalDetailsActivity.this);
        }

        myCalendar = Calendar.getInstance();
        mYear = myCalendar.get(Calendar.YEAR);
        mMonth = myCalendar.get(Calendar.MONTH);
        mDay = myCalendar.get(Calendar.DAY_OF_MONTH);


//        if (ApplicationConstant.SurveyId.trim().isEmpty()) {
//            ApplicationConstant.SurveyId = "1.0";
//            PrefUtils.saveToPrefs(PersonalDetailsActivity.this, ApplicationConstant.SURVEY_ID, "1.0");
//        } else {
//            double count = Double.parseDouble(ApplicationConstant.SurveyId);
//            count = count + 1.0;
//            PrefUtils.saveToPrefs(PersonalDetailsActivity.this, ApplicationConstant.SURVEY_ID, "" + count);
//        }



        onCLickListners();

        SingleSurveyDetails("survey/"+ApplicationConstant.SurveyId);


    }

    private void onCLickListners() {

        mBtnAddharCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEditAadhar.getText().toString().trim().isEmpty()){
                    mEditAadhar.setError("enter aadhar to verify");
                    mEditAadhar.requestFocus();
                }else  if (mEditAadhar.getText().toString().trim().length() !=12){
                    mEditAadhar.setError("enter correct aadhar to verify");
                    mEditAadhar.requestFocus();
                }else {

                    AADHAR_NO = mEditAadhar.getText().toString().trim();


                    View viewAdd = LayoutInflater.from(PendingPersonalDetailsActivity.this).inflate(R.layout.layout_select_type, null);
                    CardView cCardOTP = (CardView)viewAdd. findViewById(R.id.CardOTP);
                    CardView cCardBiometric = (CardView) viewAdd.findViewById(R.id.CardBiometric);


                    AlertDialog.Builder builder = new AlertDialog.Builder(PendingPersonalDetailsActivity.this);
                    builder.setView(viewAdd);
                    final AlertDialog alertDialog = builder.create();
                    alertDialog.setCanceledOnTouchOutside(false);
                    alertDialog.setCancelable(true);

                    cCardBiometric.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            alertDialog.dismiss();

                            ApplicationConstant.displayMessageDialog(PendingPersonalDetailsActivity.this,"","Credentials not found.");
                        }
                    });

                    cCardOTP.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            alertDialog.dismiss();
                            SendOtpForAadhar();
                        }
                    });

                    alertDialog.show();

                }

            }
        });

        btn_same_resident.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               mEditPArea.setText(mEditArea.getText().toString().trim());
                mEditPHouseNo.setText(mEditHouseNo.getText().toString().trim());
                mEditPRoad.setText(mEditRoad.getText().toString().trim());
                mEditPCity.setText(mEditCity.getText().toString().trim());
                mEditPPincode.setText(mEditPincode.getText().toString().trim());

            }
        });

        TextAddCases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                View viewAdd = LayoutInflater.from(PendingPersonalDetailsActivity.this).inflate(R.layout.layout_add_criminal_cases, null);
                ImageView cImage_cancel = (ImageView) viewAdd.findViewById(R.id.image_cancel);
                final EditText cEditSNo = (EditText) viewAdd.findViewById(R.id.EditSNo);
                final EditText cEditDate = (EditText)viewAdd. findViewById(R.id.EditDate);
                final EditText cEditFir = (EditText) viewAdd.findViewById(R.id.EditFir);
                final EditText cEditNamePolice = (EditText) viewAdd.findViewById(R.id.EditNamePolice);
                final EditText cEditStatusCase = (EditText)viewAdd. findViewById(R.id.EditStatusCase);
                TextView cTextAddCases = (TextView) viewAdd.findViewById(R.id.TextAddCases);


                AlertDialog.Builder builder = new AlertDialog.Builder(PendingPersonalDetailsActivity.this);

                builder.setView(viewAdd);
                final AlertDialog alertDialog = builder.create();

                cImage_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });

                cEditDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        DatePickerDialog datePickerDialog = new DatePickerDialog(new ContextThemeWrapper(PendingPersonalDetailsActivity.this, R.style.DialogTheme),
                                new DatePickerDialog.OnDateSetListener() {

                                    @Override
                                    public void onDateSet(DatePicker view, int year,
                                                          int monthOfYear, int dayOfMonth) {

                                        cEditDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);


                                    }
                                }, mYear, mMonth, mDay);
                        datePickerDialog.show();

                    }
                });

                cTextAddCases.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (cEditSNo.getText().toString().trim().isEmpty()){
                            cEditSNo.setError("enter case no");
                            cEditSNo.requestFocus();
                        }else  if (cEditDate.getText().toString().trim().isEmpty()){
                            cEditDate.setError("enter case date");
                            cEditDate.requestFocus();
                        }else if (cEditFir.getText().toString().trim().isEmpty()){
                            cEditFir.setError("enter fir no");
                            cEditFir.requestFocus();
                        }else if (cEditNamePolice.getText().toString().trim().isEmpty()){
                            cEditNamePolice.setError("enter police name");
                            cEditNamePolice.requestFocus();
                        }else if (cEditStatusCase.getText().toString().trim().isEmpty()){
                            cEditStatusCase.setError("enter case status");
                            cEditStatusCase.requestFocus();
                        }else {

                            CriminalCaseDetailsItem criminalCases = new CriminalCaseDetailsItem(
                                    cEditSNo.getText().toString().trim(),
                                    cEditFir.getText().toString().trim(),
                                    cEditNamePolice.getText().toString().trim(),
                                    cEditDate.getText().toString().trim(),
                                    cEditStatusCase.getText().toString().trim()
                            );

                            listCriminalCases.add(criminalCases);

                            ViewCriminalCasesAdpater criminalCasesAdpater = new ViewCriminalCasesAdpater(PendingPersonalDetailsActivity.this);
                            criminalCasesAdpater.setDetails(listCriminalCases);

                            view_Criminal_Case.setAdapter(criminalCasesAdpater);
                            alertDialog.dismiss();

                        }

                    }
                });


                alertDialog.show();

            }
        });


        mImgCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(new ContextThemeWrapper(PendingPersonalDetailsActivity.this, R.style.DialogTheme),
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

        RGCriminal.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioSexButton = (RadioButton) findViewById(checkedId);
                IS_CRIMINALCASE = radioSexButton.getText().toString().trim();
                if (IS_CRIMINALCASE.contains("Yes")) {
                    linear_cases.setVisibility(View.VISIBLE);
                    IS_CRIMINALCASE ="1";

                } else {
                    linear_cases.setVisibility(View.GONE);
                }
            }
        });

        mImgVendorPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//          startActivityForResult(takePictureIntent, 1);

                // Ensure that there's a camera activity to handle the intent
                if (takePictureIntent.resolveActivity(PendingPersonalDetailsActivity.this.getPackageManager()) != null) {
                    // Create the File where the photo should go
                    File photoFile = null;
                    try {
                        photoPath = ApplicationConstant.createImageFile(ApplicationConstant.SurveyId + "_profile.jpeg", "Profile", PendingPersonalDetailsActivity.this);
                        photoFile = new File(photoPath);
                    } catch (IOException ex) {
                        // Error occurred while creating the File
                    }
                    // Continue only if the File was successfully created
                    if (photoFile != null) {
                        photoURI = SurveyAppFileProvider.getUriForFile(PendingPersonalDetailsActivity.this,
                                BuildConfig.APPLICATION_ID + ".android.fileprovider",
                                photoFile);
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                        startActivityForResult(takePictureIntent, 1);

                    }
                }
            }
        });

        mImgVendorSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//          startActivityForResult(takePictureIntent, 1);

                // Ensure that there's a camera activity to handle the intent
                if (takePictureIntent.resolveActivity(PendingPersonalDetailsActivity.this.getPackageManager()) != null) {
                    // Create the File where the photo should go
                    File photoFile = null;
                    try {
                        VindingPhotoPath = ApplicationConstant.createImageFile(ApplicationConstant.SurveyId + "_vendingsite.jpeg", "Profile", PendingPersonalDetailsActivity.this);
                        photoFile = new File(VindingPhotoPath);
                    } catch (IOException ex) {
                        // Error occurred while creating the File
                    }
                    // Continue only if the File was successfully created
                    if (photoFile != null) {
                        photoURI = SurveyAppFileProvider.getUriForFile(PendingPersonalDetailsActivity.this,
                                BuildConfig.APPLICATION_ID + ".android.fileprovider",
                                photoFile);
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                        startActivityForResult(takePictureIntent, 2);

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

                } else if (mLinearFour.getVisibility() == View.VISIBLE) {

                    mLinearFour.setVisibility(View.GONE);
                    mLinearOne.setVisibility(View.GONE);
                    mLinearThree.setVisibility(View.VISIBLE);
                    mLinearTwo.setVisibility(View.GONE);
                    mLinearFive.setVisibility(View.GONE);

                } else if (mLinearThree.getVisibility() == View.VISIBLE) {

                    mLinearFour.setVisibility(View.GONE);
                    mLinearOne.setVisibility(View.GONE);
                    mLinearThree.setVisibility(View.GONE);
                    mLinearTwo.setVisibility(View.VISIBLE);
                    mLinearFive.setVisibility(View.GONE);

                } else if (mLinearTwo.getVisibility() == View.VISIBLE) {

                    mLinearFour.setVisibility(View.GONE);
                    mLinearOne.setVisibility(View.VISIBLE);
                    mLinearThree.setVisibility(View.GONE);
                    mLinearTwo.setVisibility(View.GONE);
                    mLinearFive.setVisibility(View.GONE);

//                    mBtnPrevious.setVisibility(View.GONE);

                }else if (mLinearOne.getVisibility() == View.VISIBLE) {

                    mLinearHead.setVisibility(View.VISIBLE);
                    mLinearFour.setVisibility(View.GONE);
                    mLinearOne.setVisibility(View.GONE);
                    mLinearThree.setVisibility(View.GONE);
                    mLinearTwo.setVisibility(View.GONE);
                    mLinearFive.setVisibility(View.GONE);

                    mBtnPrevious.setVisibility(View.GONE);

                } else {

                    onBackPressed();

                }

            }
        });

        mSpinnerEducation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                EDUCATION_STATUS = parent.getItemAtPosition(position).toString().trim()
                        .split("\\.")[1].trim();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mSpinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                CATEGORY = parent.getItemAtPosition(position).toString().trim();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mLinearHead.getVisibility() == View.VISIBLE) {

                    mLinearHead.setVisibility(View.GONE);
                    mLinearOne.setVisibility(View.VISIBLE);
                    mLinearTwo.setVisibility(View.GONE);
                    mLinearThree.setVisibility(View.GONE);
                    mLinearFour.setVisibility(View.GONE);
                    mLinearFive.setVisibility(View.GONE);

                    mBtnPrevious.setVisibility(View.VISIBLE);

                }else if (mLinearOne.getVisibility() == View.VISIBLE) {

                    try {
                        int selectedId = RGSex.getCheckedRadioButtonId();
                        RadioButton radioSexButton = (RadioButton) findViewById(selectedId);

                        if ( radioSexButton.getText().toString().trim().equals("a. Male")){
                            SEX = "M";
                        } else if ( radioSexButton.getText().toString().trim().equals("b. Female")){
                            SEX = "F";
                        } else {
                            SEX = "O";
                        }

//                    SEX = radioSexButton.getText().toString().trim();

//                  Toast.makeText(PersonalDetailsActivity.this, SEX, Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }



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

                    ANNUAL_INCOME = mEditAnnualIncome.getText().toString().trim();

                    try {
                        int checkedId = RGWidow.getCheckedRadioButtonId();
                        RadioButton radioSexButton = (RadioButton) findViewById(checkedId);
                        WHETHER_WIDOWED = radioSexButton.getText().toString().trim();
//                    Toast.makeText(PersonalDetailsActivity.this, SEX, Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    if (validate3()) {
                        mLinearThree.setVisibility(View.GONE);
                        mLinearOne.setVisibility(View.GONE);
                        mLinearTwo.setVisibility(View.GONE);
                        mLinearFour.setVisibility(View.VISIBLE);
                        mLinearFive.setVisibility(View.GONE);
                    }

                }
                else if (mLinearFour.getVisibility() == View.VISIBLE) {

                    if (validate4()) {
                        mLinearFour.setVisibility(View.GONE);
                        mLinearOne.setVisibility(View.GONE);
                        mLinearThree.setVisibility(View.GONE);
                        mLinearTwo.setVisibility(View.GONE);
                        mLinearFive.setVisibility(View.VISIBLE);

//                        mBtnNext.setText("Submit");
                    }
                }
                else {

//                    mLinearFive.setVisibility(View.VISIBLE);
//                    mLinearOne.setVisibility(View.GONE);
//                    mLinearThree.setVisibility(View.GONE);
//                    mLinearTwo.setVisibility(View.GONE);
//                    mLinearFour.setVisibility(View.GONE);

                    mBtnNext.setText("Submit");

                    NAME_VENDOR = mEditFName.getText().toString().trim() + " "
                            + mEditMName.getText().toString().trim() + " "
                            + mEditLName.getText().toString().trim();
                    AGE = mEditAge.getText().toString().trim();
                    DOB = mEditDob.getText().toString().trim();
                    CONTACT_NO = mEditMobile.getText().toString().trim();
                    LANDLINE_NO = mEditLandline.getText().toString().trim();
//                    BRANCH_NAME = mEditBranchName.getText().toString().trim();



                    NAME_OFFATHER_HUSBAND = mEditFatherName.getText().toString().trim() + " "
                            + mEditFatherMName.getText().toString().trim() + " "
                            + mEditFatherLName.getText().toString().trim();

                    NAME_MOTHER = mEditMotherFName.getText().toString().trim() + " "
                            + mEditMotherMName.getText().toString().trim() + " "
                            + mEditMotherLName.getText().toString().trim();

                    NAME_SPOUSE = mEditSpouceFName.getText().toString().trim() + " "
                            + mEditSpouceMName.getText().toString().trim() + " "
                            + mEditSpouceLName.getText().toString().trim();


                    RESIDENTIAL_ADDRESS = mEditArea.getText().toString().trim() + ", "
                            + mEditHouseNo.getText().toString().trim() + ", "
                            + mEditRoad.getText().toString().trim()+ ", "
                            + mEditCity.getText().toString().trim()+ ", "
                            + mEditPincode.getText().toString().trim();

                    PERMENENT_ADDRESS = mEditPArea.getText().toString().trim() + ", "
                            + mEditPHouseNo.getText().toString().trim() + ", "
                            + mEditPRoad.getText().toString().trim()+ ", "
                            + mEditPCity.getText().toString().trim()+ ", "
                            + mEditPPincode.getText().toString().trim();

                    AADHAR_NO = mEditAadhar.getText().toString().trim();
//                    BANKACC_NO = mEditAccNo.getText().toString().trim();
//                    BANKNAME = mEditBankName.getText().toString().trim();
//                    IFSC = mEditIfscCode.getText().toString().trim();


//                    IS_CRIMINALCASE = String.valueOf(RGCriminal.getCheckedRadioButtonId());
//                    IS_CRIMINALCASE = "1";
//                    CRIMINALCASE_NO = mEditSNo.getText().toString().trim();
//                    CRIMINALCASE_DATE = mEditDate.getText().toString().trim();
//                    CRIMINALCASE_FIRNO = mEditFir.getText().toString().trim();
//                    CRIMINALCASE_POLICA_NAME = mEditNamePolice.getText().toString().trim();
//                    CRIMINALCASE_STATUS = mEditStatusCase.getText().toString().trim();


                    if (validate()) {

                            AddSurvey();

                    }
//                    startActivity(new Intent(PersonalDetailsActivity.this, VendorsFamDetailsActivity.class));

                }


            }
        });


    }

    private boolean validate4() {
        if (!ApplicationConstant.isNetworkAvailable(PendingPersonalDetailsActivity.this)) {

            ApplicationConstant.displayMessageDialog(PendingPersonalDetailsActivity.this, "No Internet Connection", "Please enable internet connection first to proceed");

            return false;
        }

//        else if (mEditPArea.getText().toString().trim().isEmpty()) {
//            mEditPArea.setError("Enter Permanant Area");
//            mEditPArea.requestFocus();
//            return false;
//        } else if (mEditPHouseNo.getText().toString().trim().isEmpty()) {
//            mEditPHouseNo.setError("Enter Permanant House No");
//            mEditPHouseNo.requestFocus();
//            return false;
//        } else if (mEditPRoad.getText().toString().trim().isEmpty()) {
//            mEditPRoad.setError("Enter Permanant Road");
//            mEditPRoad.requestFocus();
//            return false;
//        } else if (mEditPCity.getText().toString().trim().isEmpty()) {
//            mEditPCity.setError("Enter Permanant City");
//            mEditPCity.requestFocus();
//            return false;
//        } else if (mEditPPincode.getText().toString().trim().isEmpty()) {
//            mEditPPincode.setError("Enter Permanant Pincode");
//            mEditPPincode.requestFocus();
//            return false;
//        }
        else if (mEditAadhar.getText().toString().trim().isEmpty()) {
            mEditAadhar.setError("Enter Aadhar Number");
            mEditAadhar.requestFocus();
            return false;
        } else if (mEditAadhar.getText().toString().trim().length()<12) {
            mEditAadhar.setError("Enter Correct Aadhar Number");
            mEditAadhar.requestFocus();
            return false;
        }




//        else if (!getLocation.isGPSEnabled) {
//            this.getLocation.showSettingsAlert();
//            return false;
//        }

        return true;
    }

    private boolean validate3() {
        if (!ApplicationConstant.isNetworkAvailable(PendingPersonalDetailsActivity.this)) {

            ApplicationConstant.displayMessageDialog(PendingPersonalDetailsActivity.this, "No Internet Connection", "Please enable internet connection first to proceed");

            return false;
        } else if (WHETHER_WIDOWED.trim().isEmpty()) {
            ApplicationConstant.displayMessageDialog(PendingPersonalDetailsActivity.this, "", "Select Options");
//            mEditAge.requestFocus();
            return false;
        } else if (mSpinnerCategory.getSelectedItem().toString().trim().isEmpty()) {
            mEditArea.setError("Select Category");
            mSpinnerCategory.requestFocus();
            return false;
        }  else if (mEditAnnualIncome.getText().toString().trim().isEmpty()) {
            mEditAnnualIncome.setError("Enter Vending Site");
            mEditAnnualIncome.requestFocus();
            return false;
        }  else if (mEditArea.getText().toString().trim().isEmpty()) {
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

        if (!ApplicationConstant.isNetworkAvailable(PendingPersonalDetailsActivity.this)) {

            ApplicationConstant.displayMessageDialog(PendingPersonalDetailsActivity.this, "No Internet Connection", "Please enable internet connection first to proceed");

            return false;
        } else if (mEditMobile.getText().toString().trim().isEmpty()) {
            mEditMobile.setError("Enter Mobile Number");
            mEditMobile.requestFocus();
            return false;
        } else if (mEditMobile.getText().toString().trim().length() < 10) {
            mEditMobile.setError("Enter Correct Mobile Number");
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
        }
//        else if (mEditFatherMName.getText().toString().trim().isEmpty()) {
//            mEditFatherMName.setError("Enter Father Middle Name");
//            mEditFatherMName.requestFocus();
//            return false;
//        }
        else if (mEditFatherLName.getText().toString().trim().isEmpty()) {
            mEditFatherLName.setError("Enter Father Last Name");
            mEditFatherLName.requestFocus();
            return false;
        } else if (mEditMotherFName.getText().toString().trim().isEmpty()) {
            mEditMotherFName.setError("Enter Mother First Name");
            mEditMotherFName.requestFocus();
            return false;
        }
//        else if (mEditMotherMName.getText().toString().trim().isEmpty()) {
//            mEditMotherMName.setError("Enter Mother Middle Name");
//            mEditMotherMName.requestFocus();
//            return false;
//        }
        else if (mEditMotherLName.getText().toString().trim().isEmpty()) {
            mEditMotherLName.setError("Enter Mother Last Name");
            mEditMotherLName.requestFocus();
            return false;
        }
        return true;

    }

    private boolean validate1() {

        if (!ApplicationConstant.isNetworkAvailable(PendingPersonalDetailsActivity.this)) {

            ApplicationConstant.displayMessageDialog(PendingPersonalDetailsActivity.this, "No Internet Connection", "Please enable internet connection first to proceed");

            return false;
        } else if (mImgVendorPhoto.getDrawable() == null) {
            ApplicationConstant.displayMessageDialog(PendingPersonalDetailsActivity.this, "", "Capture profile photo");

            return false;
        }  else if (mImgVendorSite.getDrawable() == null) {
            ApplicationConstant.displayMessageDialog(PendingPersonalDetailsActivity.this, "", "Capture Vending Place photo");

            return false;
        } else if (mEditFName.getText().toString().trim().isEmpty()) {
            mEditFName.setError("Enter First Name");
            mEditFName.requestFocus();
            return false;
        }
//        else if (mEditMName.getText().toString().trim().isEmpty()) {
//            mEditMName.setError("Enter Middle Name");
//            mEditMName.requestFocus();
//            return false;
//        }
        else if (mEditLName.getText().toString().trim().isEmpty()) {
            mEditLName.setError("Enter Last Name");
            mEditLName.requestFocus();
            return false;
        } else if (SEX.trim().isEmpty()) {
            ApplicationConstant.displayMessageDialog(PendingPersonalDetailsActivity.this, "", "Select Gender");
            return false;
        } else if (mEditAge.getText().toString().trim().isEmpty()) {
            mEditAge.setError("Enter Age");
            mEditAge.requestFocus();
            return false;
        } else if (mEditAge.getText().toString().trim().length() > 3) {
            mEditAge.setError("Enter Correct Age");
            mEditAge.requestFocus();
            return false;
        } else if (mEditDob.getText().toString().trim().isEmpty()) {
            mEditDob.setError("Enter Date Of Birth");
            mEditDob.requestFocus();
            return false;
        } else  if (getLocation.getLatitude() > 0.0D && getLocation.getLongitude() > 0.0D) {
            return true;

        } else {
            EnableGPSAutoMatically();

        }

        return true;
    }

    private boolean validate() {
        if (!ApplicationConstant.isNetworkAvailable(PendingPersonalDetailsActivity.this)) {

            ApplicationConstant.displayMessageDialog(PendingPersonalDetailsActivity.this, "No Internet Connection", "Please enable internet connection first to proceed");

            return false;
        } else

        if (IS_CRIMINALCASE.trim().isEmpty()) {
            ApplicationConstant.displayMessageDialog(PendingPersonalDetailsActivity.this, "", "Select Options");
//            mEditAge.requestFocus();
            return false;
        }else if (linear_cases.getVisibility() == View.VISIBLE) {

            if (listCriminalCases.isEmpty()){
                ApplicationConstant.DisplayMessageDialog(PendingPersonalDetailsActivity.this,"","Add Criminal Cases");
                return false;
            }

        }


//        else if (!getLocation.isGPSEnabled) {
//            this.getLocation.showSettingsAlert();
//            return false;
//        }


        return true;

    }

    private void bindView() {

        mEditAnnualIncome = (EditText) findViewById(R.id.EditAnnualIncome);

        btn_same_resident = (TextView) findViewById(R.id.btn_same_resident);
        linear_cases = (LinearLayout) findViewById(R.id.linear_cases);
        mLinearHead = (LinearLayout) findViewById(R.id.LinearHead);
        mLinearOne = (LinearLayout) findViewById(R.id.LinearOne);
        RGSex = (RadioGroup) findViewById(R.id.RGSex);
        RGWidow = (RadioGroup) findViewById(R.id.RGWidow);
        RGCriminal = (RadioGroup) findViewById(R.id.RGCriminal);
        mImgVendorPhoto = (ImageView) findViewById(R.id.ImgVendorPhoto);
        mImgVendorSite = (ImageView) findViewById(R.id.ImgVendorSite);
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
        BtnAddharVerified = (TextView) findViewById(R.id.BtnAddharVerified);

        mRadioCY = (RadioButton) findViewById(R.id.RadioCY);
        mRadioCN = (RadioButton) findViewById(R.id.RadioCN);
        mLinearFive = (LinearLayout) findViewById(R.id.LinearFive);

//        mEditSNo = (EditText) findViewById(R.id.EditSNo);
//        mEditDate = (EditText) findViewById(R.id.EditDate);
//        mEditFir = (EditText) findViewById(R.id.EditFir);
//        mEditNamePolice = (EditText) findViewById(R.id.EditNamePolice);
//        mEditStatusCase = (EditText) findViewById(R.id.EditStatusCase);

        mBtnNext = (Button) findViewById(R.id.BtnNext);
        mBtnPrevious = (Button) findViewById(R.id.BtnPrevious);

        TextAddCases = (TextView) findViewById(R.id.TextAddCases);

        view_Criminal_Case = (RecyclerView) findViewById(R.id.view_Criminal_Case);
        view_Criminal_Case.setLayoutManager(new LinearLayoutManager(PendingPersonalDetailsActivity.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {

                Bitmap bitmap = ApplicationConstant.CompressedBitmap(new File(photoPath));


                Glide.with(PendingPersonalDetailsActivity.this).load(photoURI)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                        .into(mImgVendorPhoto);


                UploadVendorPhoto();

            }else   if (requestCode == 2) {

                                        Glide.with(PendingPersonalDetailsActivity.this).load(photoURI)
                                .diskCacheStrategy(DiskCacheStrategy.NONE)
                                .skipMemoryCache(true)
                                .into(mImgVendorSite);

                Bitmap bitmap = ApplicationConstant.CompressedBitmap(new File(VindingPhotoPath));

                UploadVendingSitePhoto();

            }
        }

    }

    private void AddSurvey() {

//        if (AADHAR_DETAILS.trim().isEmpty()){
//
//                HashMap<String,String> body = new HashMap<>();
//                body.put("aadhaar_number",AADHAR_NO);
//
//                String json_Aadhar = new Gson().toJson(body);
//
//                AADHAR_DETAILS = json_Aadhar;
//
//        }

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        CRIMINALCASE_NO = new Gson().toJson(listCriminalCases);

        progressDialog = CustomProgressDialog.getDialogue(PendingPersonalDetailsActivity.this);
        progressDialog.show();

        String username = PrefUtils.getFromPrefs(PendingPersonalDetailsActivity.this, ApplicationConstant.USERDETAILS.API_KEY, "");

        if (getLocation.getLatitude() > 0.0D && getLocation.getLongitude() > 0.0D) {
            Latitude = getLocation.getLatitude();
            Longitude = getLocation.getLongitude();
        }

        File file1 = new File(photoPath);

        RequestBody request_photo =
                RequestBody.create(MediaType.parse("image/png"), file1);

        String UNiq_Id = "";

//        if (ApplicationConstant.SurveyId.trim().isEmpty()) {
//            UNiq_Id = PrefUtils.getFromPrefs(PersonalDetailsActivity.this, ApplicationConstant.URI_NO_, "");
//
//        } else {
//            UNiq_Id = ApplicationConstant.SurveyId;
//        }

        UNiq_Id = PrefUtils.getFromPrefs(PendingPersonalDetailsActivity.this, ApplicationConstant.URI_NO_, "");

     String CORPORATION =   PrefUtils.getFromPrefs(PendingPersonalDetailsActivity.this,ApplicationConstant.CORPORATION,"");
        String ZONE =  PrefUtils.getFromPrefs(PendingPersonalDetailsActivity.this,ApplicationConstant.ZONE,"");
        String WARD =  PrefUtils.getFromPrefs(PendingPersonalDetailsActivity.this,ApplicationConstant.WARD,"");

        RequestBody CORPORATION_ = RequestBody.create(MediaType.parse("multipart/form-data"), CORPORATION);
        RequestBody ZONE_ = RequestBody.create(MediaType.parse("multipart/form-data"), ZONE);
        RequestBody WARD_ = RequestBody.create(MediaType.parse("multipart/form-data"), WARD);

// MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body_fhoto =
                MultipartBody.Part.createFormData("photo_of_the_street_vendor", file1.getName(), request_photo);


        RequestBody NAME_VENDOR_ = RequestBody.create(MediaType.parse("multipart/form-data"), NAME_VENDOR);
        RequestBody SURVEY_ID_ = RequestBody.create(MediaType.parse("multipart/form-data"), UNiq_Id);
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
        RequestBody AADHAR_DETAILS_ = RequestBody.create(MediaType.parse("multipart/form-data"), AADHAR_DETAILS);
        RequestBody AADHAR_NO_ = RequestBody.create(MediaType.parse("multipart/form-data"), AADHAR_NO);
        RequestBody IS_CRIMINALCASE_ = RequestBody.create(MediaType.parse("multipart/form-data"), IS_CRIMINALCASE);
        RequestBody CRIMINALCASE_NO_ = RequestBody.create(MediaType.parse("multipart/form-data"), CRIMINALCASE_NO);
        RequestBody ANNUAL_INCOME_ = RequestBody.create(MediaType.parse("multipart/form-data"), ANNUAL_INCOME);

//        RequestBody CRIMINALCASE_DATE_ = RequestBody.create(MediaType.parse("multipart/form-data"), CRIMINALCASE_DATE);
//        RequestBody CRIMINALCASE_FIRNO_ = RequestBody.create(MediaType.parse("multipart/form-data"), CRIMINALCASE_FIRNO);
//        RequestBody CRIMINALCASE_POLICA_NAME_ = RequestBody.create(MediaType.parse("multipart/form-data"), CRIMINALCASE_POLICA_NAME);
//        RequestBody CRIMINALCASE_STATUS_ = RequestBody.create(MediaType.parse("multipart/form-data"), CRIMINALCASE_STATUS);

        RequestBody LATITUDE = RequestBody.create(MediaType.parse("multipart/form-data"), "" + Latitude);
        RequestBody LONGITUDE = RequestBody.create(MediaType.parse("multipart/form-data"), "" + Longitude);


        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + PrefUtils.getFromPrefs(PendingPersonalDetailsActivity.this, ApplicationConstant.USERDETAILS.API_KEY, ""));

        ApiInterface apiservice = ApiService.getApiClient().create(ApiInterface.class);
        Call<UpdateSurveyResponse> call = apiservice.getAddSurvey(headers,
                SURVEY_ID_,
                CORPORATION_,
                ZONE_,
                WARD_,
                NAME_VENDOR_, SEX_, AGE_,
                DOB_,
                CONTACT_NO_,
                LANDLINE_NO_,
                EDUCATION_STATUS_,
                NAME_OFFATHER_HUSBAND_,
                NAME_MOTHER_,
                NAME_SPOUSE_,
                WHETHER_WIDOWED_,
                CATEGORY_,
                ANNUAL_INCOME_,
                RESIDENTIAL_ADDRESS_,
                PERMENENT_ADDRESS_,
                AADHAR_DETAILS_,
                AADHAR_NO_,
                IS_CRIMINALCASE_,
                CRIMINALCASE_NO_,
                LATITUDE,
                LONGITUDE
//                CRIMINALCASE_STATUS_
        );

        call.enqueue(new Callback<UpdateSurveyResponse>() {
            @Override
            public void onResponse(Call<UpdateSurveyResponse> call, Response<UpdateSurveyResponse> response) {

                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();

                if (response.body() != null) {

                    if (response.body().isStatus()) {

                        PrefUtils.saveToPrefs(PendingPersonalDetailsActivity.this, ApplicationConstant.CONTACT, CONTACT_NO);

                        AlertDialog.Builder builder = new AlertDialog.Builder(PendingPersonalDetailsActivity.this);
                        builder.setTitle("Personal Details");
                        builder.setMessage("Saved successfully");
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                startActivity(new Intent(PendingPersonalDetailsActivity.this, VendorsFamDetailsActivity.class));

                            }
                        });

                        AlertDialog alertDialog = builder.create();
                        alertDialog.setCancelable(false);
                        alertDialog.setCanceledOnTouchOutside(false);
                        alertDialog.show();


//                        ApplicationConstant.displayToastMessage(PersonalDetailsActivity.this,
//                                "Personal Details saved successfully");


                    } else {

                        ApplicationConstant.displayMessageDialog(PendingPersonalDetailsActivity.this,
                                "Response",
                                response.body().getMessage());
                    }

                } else {

                    try {
                        ApplicationConstant.displayMessageDialog(PendingPersonalDetailsActivity.this,
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
                ApplicationConstant.displayMessageDialog(PendingPersonalDetailsActivity.this, "Response", getString(R.string.net_speed_problem));

            }
        });
    }


    private void UploadVendorPhoto() {

        progressDialog = CustomProgressDialog.getDialogue(PendingPersonalDetailsActivity.this);
        progressDialog.show();

        File file1 = new File(photoPath);

        RequestBody request_photo =
                RequestBody.create(MediaType.parse("image/png"), file1);

        String UNiq_Id = "";


        UNiq_Id = PrefUtils.getFromPrefs(PendingPersonalDetailsActivity.this, ApplicationConstant.URI_NO_, "");

        String CORPORATION =   PrefUtils.getFromPrefs(PendingPersonalDetailsActivity.this,ApplicationConstant.CORPORATION,"");
        String ZONE =  PrefUtils.getFromPrefs(PendingPersonalDetailsActivity.this,ApplicationConstant.ZONE,"");
        String WARD =  PrefUtils.getFromPrefs(PendingPersonalDetailsActivity.this,ApplicationConstant.WARD,"");

        RequestBody CORPORATION_ = RequestBody.create(MediaType.parse("multipart/form-data"), CORPORATION);
        RequestBody ZONE_ = RequestBody.create(MediaType.parse("multipart/form-data"), ZONE);
        RequestBody WARD_ = RequestBody.create(MediaType.parse("multipart/form-data"), WARD);

// MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body_fhoto =
                MultipartBody.Part.createFormData("photo_of_the_street_vendor", file1.getName(), request_photo);

        RequestBody SURVEY_ID_ = RequestBody.create(MediaType.parse("multipart/form-data"), UNiq_Id);


        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + PrefUtils.getFromPrefs(PendingPersonalDetailsActivity.this, ApplicationConstant.USERDETAILS.API_KEY, ""));

        ApiInterface apiservice = ApiService.getApiClient().create(ApiInterface.class);
        Call<UpdateSurveyResponse> call = apiservice.UploadVendorPhoto(headers,
                SURVEY_ID_,
                CORPORATION_,
                ZONE_,
                WARD_,
                body_fhoto
        );

        call.enqueue(new Callback<UpdateSurveyResponse>() {
            @Override
            public void onResponse(Call<UpdateSurveyResponse> call, Response<UpdateSurveyResponse> response) {

                if (response.body() != null) {

                    if (response.body().isStatus()) {



                        if (progressDialog != null && progressDialog.isShowing())
                            progressDialog.dismiss();

                        ApplicationConstant.displayToastMessage(PendingPersonalDetailsActivity.this,
                                "Photo saved successfully");


                    } else {
                        if (progressDialog != null && progressDialog.isShowing())
                            progressDialog.dismiss();

                        ApplicationConstant.displayMessageDialog(PendingPersonalDetailsActivity.this,
                                "Response",
                                response.body().getMessage());
                    }

                } else {
                    if (progressDialog != null && progressDialog.isShowing())
                        progressDialog.dismiss();

                    try {
                        ApplicationConstant.displayMessageDialog(PendingPersonalDetailsActivity.this,
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
                ApplicationConstant.displayMessageDialog(PendingPersonalDetailsActivity.this, "Response", getString(R.string.net_speed_problem));

            }
        });
    }



    public static String getRealPathFromUri(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void UploadVendingSitePhoto() {

        progressDialog = CustomProgressDialog.getDialogue(PendingPersonalDetailsActivity.this);
        progressDialog.show();

        File file1 = new File(VindingPhotoPath);

        RequestBody request_photo =
                RequestBody.create(MediaType.parse("image/png"), file1);

        String UNiq_Id = "";


        UNiq_Id = PrefUtils.getFromPrefs(PendingPersonalDetailsActivity.this, ApplicationConstant.URI_NO_, "");

        String CORPORATION =   PrefUtils.getFromPrefs(PendingPersonalDetailsActivity.this,ApplicationConstant.CORPORATION,"");
        String ZONE =  PrefUtils.getFromPrefs(PendingPersonalDetailsActivity.this,ApplicationConstant.ZONE,"");
        String WARD =  PrefUtils.getFromPrefs(PendingPersonalDetailsActivity.this,ApplicationConstant.WARD,"");

        RequestBody CORPORATION_ = RequestBody.create(MediaType.parse("multipart/form-data"), CORPORATION);
        RequestBody ZONE_ = RequestBody.create(MediaType.parse("multipart/form-data"), ZONE);
        RequestBody WARD_ = RequestBody.create(MediaType.parse("multipart/form-data"), WARD);

// MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body_fhoto =
                MultipartBody.Part.createFormData("photo_of_vendor_site", file1.getName(), request_photo);

        RequestBody SURVEY_ID_ = RequestBody.create(MediaType.parse("multipart/form-data"), UNiq_Id);


        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + PrefUtils.getFromPrefs(PendingPersonalDetailsActivity.this, ApplicationConstant.USERDETAILS.API_KEY, ""));

        ApiInterface apiservice = ApiService.getApiClient().create(ApiInterface.class);
        Call<UpdateSurveyResponse> call = apiservice.UploadVendorPlacePhoto(headers,
                SURVEY_ID_,
                CORPORATION_,
                ZONE_,
                WARD_,
                body_fhoto
        );

        call.enqueue(new Callback<UpdateSurveyResponse>() {
            @Override
            public void onResponse(Call<UpdateSurveyResponse> call, Response<UpdateSurveyResponse> response) {



                if (response.body() != null) {

                    if (response.body().isStatus()) {





                        if (progressDialog != null && progressDialog.isShowing())
                            progressDialog.dismiss();

                        ApplicationConstant.displayToastMessage(PendingPersonalDetailsActivity.this,
                                "Vending Site Photo saved successfully");


                    } else {
                        if (progressDialog != null && progressDialog.isShowing())
                            progressDialog.dismiss();

                        ApplicationConstant.displayMessageDialog(PendingPersonalDetailsActivity.this,
                                "Response",
                                response.body().getMessage());
                    }

                } else {
                    if (progressDialog != null && progressDialog.isShowing())
                        progressDialog.dismiss();

                    try {
                        ApplicationConstant.displayMessageDialog(PendingPersonalDetailsActivity.this,
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
                ApplicationConstant.displayMessageDialog(PendingPersonalDetailsActivity.this, "Response", getString(R.string.net_speed_problem));

            }
        });
    }

    private void SendOtpForAadhar() {

        progressDialog = CustomProgressDialog.getDialogue(PendingPersonalDetailsActivity.this);
        progressDialog.show();


        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + ApplicationConstant.AADHAR_TOKEN);

        HashMap<String,String> body = new HashMap<>();
        body.put("id_number",AADHAR_NO);

        ApiInterface apiservice = ApiService.getApiClient2().create(ApiInterface.class);
        Call<AadharOtpResponse> call = apiservice.generateOtpAadhar(headers,body);

        call.enqueue(new Callback<AadharOtpResponse>() {
            @Override
            public void onResponse(Call<AadharOtpResponse> call, Response<AadharOtpResponse> response) {

                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();

                if (response.body() != null) {

                    if (response.body().isSuccess()) {


                        ApplicationConstant.displayToastMessage(PendingPersonalDetailsActivity.this,
                                ""+response.body().getMessage());

                        AadharOtpData aadhar_data = response.body().getData();

                        if (aadhar_data.isIfNumber()){
                            if (aadhar_data.isValidAadhaar()){

                                String Client_Id = aadhar_data.getClientId().trim();
                                View viewAdd = LayoutInflater.from(PendingPersonalDetailsActivity.this).inflate(R.layout.layout_otp_screen, null);
                                ImageView aImage_cancel = (ImageView)viewAdd. findViewById(R.id.image_cancel);
                                EditText aEditOtp = (EditText)viewAdd. findViewById(R.id.EditOtp);
                                TextView  aText_resend = (TextView)viewAdd. findViewById(R.id.text_resend);
                                TextView  aTextSubmitOtp = (TextView)viewAdd. findViewById(R.id.TextSubmitOtp);

                                AlertDialog.Builder builder = new AlertDialog.Builder(PendingPersonalDetailsActivity.this);
                                builder.setView(viewAdd);
                                final AlertDialog alertDialog = builder.create();
                                alertDialog.setCanceledOnTouchOutside(false);
                                alertDialog.setCancelable(false);

                                resendOtpTimer(aText_resend);

                                aImage_cancel.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        alertDialog.dismiss();
                                    }
                                });

                                aText_resend.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if (aText_resend.getText().toString().equals("RESEND OTP")) {
                                            ResendOtpForAadhar();
                                        }
                                        }
                                });

                                aTextSubmitOtp.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        if (aEditOtp.getText().toString().trim().isEmpty()){
                                            aEditOtp.setError("enter otp to verify");
                                            aEditOtp.requestFocus();
                                        }else   if (aEditOtp.getText().toString().trim().length() != 6){
                                            aEditOtp.setError("enter correct otp to verify");
                                            aEditOtp.requestFocus();
                                        }else {
                                            SubmitOtpForAadhar(aEditOtp.getText().toString().trim(),Client_Id,alertDialog);
                                        }
                                    }
                                });


                                alertDialog.show();



                            }else {
                                ApplicationConstant.displayMessageDialog(PendingPersonalDetailsActivity.this,"Response","Enter correct aadhar no");

                            }

                        }else {
                            ApplicationConstant.displayMessageDialog(PendingPersonalDetailsActivity.this,"Response","Mobile no not linked");
                        }


                    } else {

                        ApplicationConstant.displayMessageDialog(PendingPersonalDetailsActivity.this,
                                "Response",
                                response.body().getMessage());
                    }

                } else {

                    try {
                        ApplicationConstant.displayMessageDialog(PendingPersonalDetailsActivity.this,
                                "Response",
                                "incorrect Aadhar or server problem");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<AadharOtpResponse> call, Throwable t) {

                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();
                ApplicationConstant.displayMessageDialog(PendingPersonalDetailsActivity.this, "Response", getString(R.string.net_speed_problem));

            }
        });
    }

    private void SubmitOtpForAadhar(String otp,String clientId,AlertDialog alertDialog) {

        progressDialog = CustomProgressDialog.getDialogue(PendingPersonalDetailsActivity.this);
        progressDialog.show();


        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + ApplicationConstant.AADHAR_TOKEN);

        HashMap<String,String> body = new HashMap<>();
        body.put("client_id",clientId);
        body.put("otp",otp);

        ApiInterface apiservice = ApiService.getApiClient2().create(ApiInterface.class);
        Call<AadharValidResponse> call = apiservice.SubmitOtpForAadhar(headers,body);

        call.enqueue(new Callback<AadharValidResponse>() {
            @Override
            public void onResponse(Call<AadharValidResponse> call, Response<AadharValidResponse> response) {

                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();

                if (response.body() != null) {

                    if (response.body().isSuccess()) {


//                        ApplicationConstant.displayToastMessage(PersonalDetailsActivity.this,
//                                ""+response.body().getMessage());


                        if (response.body().getData()!=null){
                            AadharData aadhar_data = response.body().getData();
                            alertDialog.dismiss();

                            BtnAddharVerified.setVisibility(View.VISIBLE);
                            mBtnAddharCapture.setVisibility(View.GONE);
                            mEditAadhar.setEnabled(false);

                            GsonBuilder gsonBuilder = new GsonBuilder();
                            Gson gson = gsonBuilder.create();
                            String json_Aadhar = new Gson().toJson(aadhar_data);

                            AADHAR_DETAILS = json_Aadhar;

//                            ApplicationConstant.displayMessageDialog(PersonalDetailsActivity.this,"Response",json_Aadhar);


                        }else {
                            ApplicationConstant.displayMessageDialog(PendingPersonalDetailsActivity.this,"Response","Enter correct mobile no");
                        }


                    } else {

                        ApplicationConstant.displayMessageDialog(PendingPersonalDetailsActivity.this,
                                "Response",
                                response.body().getMessage());
                    }

                } else {

                    try {
                        ApplicationConstant.displayMessageDialog(PendingPersonalDetailsActivity.this,
                                "Response",
                                "Server Error Occurred! try again");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<AadharValidResponse> call, Throwable t) {

                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();
                ApplicationConstant.displayMessageDialog(PendingPersonalDetailsActivity.this, "Response", getString(R.string.net_speed_problem));

            }
        });
    }

    private void ResendOtpForAadhar() {

        progressDialog = CustomProgressDialog.getDialogue(PendingPersonalDetailsActivity.this);
        progressDialog.show();


        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + ApplicationConstant.AADHAR_TOKEN);

        HashMap<String,String> body = new HashMap<>();
        body.put("id_number",AADHAR_NO);

        ApiInterface apiservice = ApiService.getApiClient2().create(ApiInterface.class);
        Call<AadharOtpResponse> call = apiservice.generateOtpAadhar(headers,body);

        call.enqueue(new Callback<AadharOtpResponse>() {
            @Override
            public void onResponse(Call<AadharOtpResponse> call, Response<AadharOtpResponse> response) {

                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();

                if (response.body() != null) {

                    if (response.body().isSuccess()) {


                        ApplicationConstant.displayToastMessage(PendingPersonalDetailsActivity.this,
                                ""+response.body().getMessage());

                        AadharOtpData aadhar_data = response.body().getData();

                        if (aadhar_data.isIfNumber()){
                            if (aadhar_data.isValidAadhaar()){


                            }else {
                                ApplicationConstant.displayMessageDialog(PendingPersonalDetailsActivity.this,"Response","Enter correct aadhar no");

                            }

                        }else {
                            ApplicationConstant.displayMessageDialog(PendingPersonalDetailsActivity.this,"Response","Mobile no not linked");
                        }


                    } else {

                        ApplicationConstant.displayMessageDialog(PendingPersonalDetailsActivity.this,
                                "Response",
                                response.body().getMessage());
                    }

                } else {

                    try {
                        ApplicationConstant.displayMessageDialog(PendingPersonalDetailsActivity.this,
                                "Response",
                                "incorrect Aadhar or server problem");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<AadharOtpResponse> call, Throwable t) {

                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();
                ApplicationConstant.displayMessageDialog(PendingPersonalDetailsActivity.this, "Response", getString(R.string.net_speed_problem));

            }
        });
    }

    private void resendOtpTimer(TextView text_resend) {

        new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                text_resend.setText("seconds: " + millisUntilFinished / 1000);
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                text_resend.setText("RESEND OTP");
            }

        }.start();
    }


    private void SingleSurveyDetails(String CustomUrl) {

        progressDialog = CustomProgressDialog.getDialogue(PendingPersonalDetailsActivity.this);
        progressDialog.show();

        String UNiq_Id =  PrefUtils.getFromPrefs(PendingPersonalDetailsActivity.this, ApplicationConstant.URI_NO_,"");

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + PrefUtils.getFromPrefs(PendingPersonalDetailsActivity.this, ApplicationConstant.USERDETAILS.API_KEY, ""));

        ViewSurveyInterface apiservice = ApiService.getApiClient().create(ViewSurveyInterface.class);
        Call<SingleSurveyResponse> call = apiservice.SingleSurveyDetails(headers, CustomUrl);

        call.enqueue(new Callback<SingleSurveyResponse>() {
            @Override
            public void onResponse(Call<SingleSurveyResponse> call, Response<SingleSurveyResponse> response) {

                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();

                if (response.body() != null) {

                    if (response.body().isStatus()) {

                        SingleSurveyData  = response.body().getResponse();

                        setPersonalData();

                    } else {

                        ApplicationConstant.displayMessageDialog(PendingPersonalDetailsActivity.this,
                                "Response",
                                "Failed to get Data");
                    }

                }else {
                    try {
                        ApplicationConstant.displayMessageDialog(PendingPersonalDetailsActivity.this,
                                "Response",
                                response.errorBody().string());
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<SingleSurveyResponse> call, Throwable t) {

                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();
                ApplicationConstant.displayMessageDialog(PendingPersonalDetailsActivity.this, "Response", getString(R.string.net_speed_problem));

            }
        });
    }

    private void setPersonalData() {

        Glide.with(this).load(SingleSurveyData.getPhotoOfTheStreetVendor())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(mImgVendorPhoto);

        Glide.with(this).load(SingleSurveyData.getPhotoOfVendorSite())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(mImgVendorSite);

        String[] splited = SingleSurveyData.getNameOfTheStreetVendor().split("\\s+");

        if (splited.length == 3){
            mEditFName.setText(splited[0]);
            mEditMName.setText(splited[1]);
            mEditLName.setText(splited[2]);

        }else {
            mEditFName.setText(splited[0]);
            mEditLName.setText(splited[1]);
        }

        if (SingleSurveyData.getSex().trim().equals("M")){
            mRadioM.setChecked(true);
        }else if (SingleSurveyData.getSex().trim().equals("F")){
            mRadioF.setChecked(true);
        }else {
            mRadioO.setChecked(true);
        }

        mEditAge.setText(SingleSurveyData.getAge());
        mEditDob.setText(SingleSurveyData.getDateOfBirth());

        mEditMobile.setText(SingleSurveyData.getContactNumber());
        mEditLandline.setText(SingleSurveyData.getLandlineNumber());

        String[] splitedHus = SingleSurveyData.getNameOfFatherHusband().split("\\s+");

        if (splitedHus.length == 3){
            mEditFatherName.setText(splitedHus[0]);
            mEditFatherMName.setText(splitedHus[1]);
            mEditFatherLName.setText(splitedHus[2]);

        }else {
            mEditFatherName.setText(splitedHus[0]);
            mEditFatherLName.setText(splitedHus[1]);
        }


        String[] splitedMother = SingleSurveyData.getNameOfMother().split("\\s+");

        if (splitedMother.length == 3){
            mEditMotherFName.setText(splitedMother[0]);
            mEditMotherMName.setText(splitedMother[1]);
            mEditMotherLName.setText(splitedMother[2]);

        }else {
            mEditMotherFName.setText(splitedMother[0]);
            mEditMotherLName.setText(splitedMother[1]);
        }

        if (mSpinnerEducation.getItemAtPosition(0).toString().trim().contains(SingleSurveyData.getEducationStatus().trim())){
            mSpinnerEducation.setSelection(0);
        }else if (mSpinnerEducation.getItemAtPosition(1).toString().trim().contains(SingleSurveyData.getEducationStatus().trim())){
            mSpinnerEducation.setSelection(1);
        }else if (mSpinnerEducation.getItemAtPosition(2).toString().trim().contains(SingleSurveyData.getEducationStatus().trim())){
            mSpinnerEducation.setSelection(2);
        }else if (mSpinnerEducation.getItemAtPosition(3).toString().trim().contains(SingleSurveyData.getEducationStatus().trim())){
            mSpinnerEducation.setSelection(3);
        }else if (mSpinnerEducation.getItemAtPosition(4).toString().trim().contains(SingleSurveyData.getEducationStatus().trim())){
            mSpinnerEducation.setSelection(4);
        }else if (mSpinnerEducation.getItemAtPosition(5).toString().trim().contains(SingleSurveyData.getEducationStatus().trim())){
            mSpinnerEducation.setSelection(5);
        }

        if (SingleSurveyData.getSpouseName() != null ){
            try {
                String[] splitedSpouse = SingleSurveyData.getSpouseName().split("\\s+");

                if (splitedSpouse.length == 3){
                    mEditSpouceFName.setText(splitedSpouse[0]);
                    mEditSpouceMName.setText(splitedSpouse[1]);
                    mEditSpouceLName.setText(splitedSpouse[2]);

                }else {
                    mEditSpouceFName.setText(splitedSpouse[0]);
                    mEditSpouceLName.setText(splitedSpouse[1]);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (SingleSurveyData.getWhetherWidowedWidower() != null ){
            if (SingleSurveyData.getWhetherWidowedWidower().trim().equals("No")){
                mRadioN.setChecked(true);
            }else {
                mRadioY.setChecked(true);
            }
        }

        if (mSpinnerCategory.getItemAtPosition(0).toString().trim().contains(SingleSurveyData.getCategory().trim())){
            mSpinnerCategory.setSelection(0);
        }else if (mSpinnerCategory.getItemAtPosition(1).toString().trim().contains(SingleSurveyData.getCategory().trim())){
            mSpinnerCategory.setSelection(1);
        }else if (mSpinnerCategory.getItemAtPosition(2).toString().trim().contains(SingleSurveyData.getCategory().trim())){
            mSpinnerCategory.setSelection(2);
        }else if (mSpinnerCategory.getItemAtPosition(3).toString().trim().contains(SingleSurveyData.getCategory().trim())){
            mSpinnerCategory.setSelection(3);
        }else if (mSpinnerCategory.getItemAtPosition(4).toString().trim().contains(SingleSurveyData.getCategory().trim())){
            mSpinnerCategory.setSelection(4);
        }else if (mSpinnerCategory.getItemAtPosition(5).toString().trim().contains(SingleSurveyData.getCategory().trim())){
            mSpinnerCategory.setSelection(5);
        }
        else if (mSpinnerCategory.getItemAtPosition(6).toString().trim().contains(SingleSurveyData.getCategory().trim())){
            mSpinnerCategory.setSelection(6);
        }
        else if (mSpinnerCategory.getItemAtPosition(7).toString().trim().contains(SingleSurveyData.getCategory().trim())){
            mSpinnerCategory.setSelection(7);
        }
        else if (mSpinnerCategory.getItemAtPosition(8).toString().trim().contains(SingleSurveyData.getCategory().trim())){
            mSpinnerCategory.setSelection(8);
        }
        else if (mSpinnerCategory.getItemAtPosition(9).toString().trim().contains(SingleSurveyData.getCategory().trim())){
            mSpinnerCategory.setSelection(9);
        }
        else if (mSpinnerCategory.getItemAtPosition(10).toString().trim().contains(SingleSurveyData.getCategory().trim())){
            mSpinnerCategory.setSelection(10);
        }
        else if (mSpinnerCategory.getItemAtPosition(11).toString().trim().contains(SingleSurveyData.getCategory().trim())){
            mSpinnerCategory.setSelection(11);
        }


        mEditAnnualIncome.setText(SingleSurveyData.getAnnualIncome());

        if (SingleSurveyData.getResidentialCorrespondenceAddress() != null ){
            try {
                String[] splitedRes = SingleSurveyData.getResidentialCorrespondenceAddress().split(",");

                if (splitedRes.length == 5){
                    mEditArea.setText(splitedRes[0]);
                    mEditHouseNo.setText(splitedRes[1]);
                    mEditRoad.setText(splitedRes[2]);
                    mEditCity.setText(splitedRes[3]);
                    mEditPincode.setText(splitedRes[4]);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (SingleSurveyData.getPermanentAddress() != null ){
            try {
                String[] splitedRes = SingleSurveyData.getPermanentAddress().split(",");

                if (splitedRes.length == 5){
                    mEditPArea.setText(splitedRes[0]);
                    mEditPHouseNo.setText(splitedRes[1]);
                    mEditPRoad.setText(splitedRes[2]);
                    mEditPCity.setText(splitedRes[3]);
                    mEditPPincode.setText(splitedRes[4]);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        mEditAadhar.setText(SingleSurveyData.getAadhaarNumber());

        if (SingleSurveyData.getCriminalCasePending() != null ){
            if (SingleSurveyData.getCriminalCasePending().trim().equals("No")){
                mRadioCN.setChecked(true);
            }else {
                mRadioCY.setChecked(true);
            }
        }

        if (SingleSurveyData.getCriminalCaseDetails() != null)

        listCriminalCases.addAll(SingleSurveyData.getCriminalCaseDetails());

        ViewCriminalCasesAdpater criminalCasesAdpater = new ViewCriminalCasesAdpater(PendingPersonalDetailsActivity.this);
        criminalCasesAdpater.setDetails(listCriminalCases);

    }
}