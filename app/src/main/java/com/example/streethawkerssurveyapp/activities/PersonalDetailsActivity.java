package com.example.streethawkerssurveyapp.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.FileProvider;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import fr.arnaudguyon.xmltojsonlib.XmlToJson;
import io.sentry.core.protocol.User;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.streethawkerssurveyapp.BuildConfig;
import com.example.streethawkerssurveyapp.R;
import com.example.streethawkerssurveyapp.adapter.CriminalCasesAdpater;
import com.example.streethawkerssurveyapp.adapter.LandAssetsAdpater;
import com.example.streethawkerssurveyapp.database_pack.PersonalDetails;
import com.example.streethawkerssurveyapp.database_pack.SurveyDao;
import com.example.streethawkerssurveyapp.database_pack.SurveyDatabase;
import com.example.streethawkerssurveyapp.pending_survey.activities.PendingPersonalDetailsActivity;
import com.example.streethawkerssurveyapp.pojo_class.CriminalCases;
import com.example.streethawkerssurveyapp.pojo_class.LandAssets;
import com.example.streethawkerssurveyapp.response_pack.SurveyResponse;
import com.example.streethawkerssurveyapp.response_pack.UpdateSurveyResponse;
import com.example.streethawkerssurveyapp.response_pack.aadhar_response.AadharData;
import com.example.streethawkerssurveyapp.response_pack.aadhar_response.AadharOtpData;
import com.example.streethawkerssurveyapp.response_pack.aadhar_response.AadharOtpResponse;
import com.example.streethawkerssurveyapp.response_pack.aadhar_response.AadharValidResponse;
import com.example.streethawkerssurveyapp.response_pack.aadhar_response.Address;
import com.example.streethawkerssurveyapp.services.AudioRecordService;
import com.example.streethawkerssurveyapp.services_pack.ApiInterface;
import com.example.streethawkerssurveyapp.services_pack.ApiService;
import com.example.streethawkerssurveyapp.services_pack.ApplicationConstant;
import com.example.streethawkerssurveyapp.services_pack.CustomProgressDialog;
import com.example.streethawkerssurveyapp.utils.GetLocation;
import com.example.streethawkerssurveyapp.utils.PrefUtils;
import com.example.streethawkerssurveyapp.utils.SurveyAppFileProvider;
import com.google.ads.afma.nano.Google3NanoAdshieldEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nitgen.SDK.AndroidBSP.NBioBSPJNI;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class PersonalDetailsActivity extends MainActivity {


    //Biometric init
    private NBioBSPJNI bsp;
    private NBioBSPJNI.Export exportEngine;
    private NBioBSPJNI.IndexSearch indexSearch;
    public static final int QUALITY_LIMIT = 60;
    private byte[] byTemplate1;
    private byte[] byTemplate2;

    private byte[] byCapturedRaw1;
    private int nCapturedRawWidth1;
    private int nCapturedRawHeight1;

    private byte[] byCapturedRaw2;
    private int nCapturedRawWidth2;
    private int nCapturedRawHeight2;


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
    private List<CriminalCases> listCriminalCases = new ArrayList<>();

    private TextView btn_same_resident;
    private String AADHAR_DETAILS = "";

    CardView BtnOpenDevice;

    public static int orientation;

    private AadharData aadharData = null;

    private CardView CaptureBiometric;
    private TextView TextBioCaptured;
    private ImageView image_bio;

    private String SCANRESULT = "";

    private SurveyDatabase surveyDatabase;
    private SurveyDao surveyDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindView();
        initData();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("URI NO: " + ApplicationConstant.SurveyId);

        try {
            SCANRESULT = getIntent().getExtras().getString("SCANRESULT");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(PersonalDetailsActivity.this, AudioRecordService.class);
        intent.putExtra("FILE", ApplicationConstant.SurveyId);
        startService(intent);

        if (getLocation == null) {
            getLocation = new GetLocation(PersonalDetailsActivity.this);
        }

        surveyDatabase = SurveyDatabase.getDatabase(PersonalDetailsActivity.this);
        surveyDao = surveyDatabase.surveyDao();


        myCalendar = Calendar.getInstance();
        mYear = myCalendar.get(Calendar.YEAR);
        mMonth = myCalendar.get(Calendar.MONTH);
        mDay = myCalendar.get(Calendar.DAY_OF_MONTH);

        ApplicationConstant.SurveyId = PrefUtils.getFromPrefs(PersonalDetailsActivity.this, ApplicationConstant.SURVEY_ID, "");

//        if (ApplicationConstant.SurveyId.trim().isEmpty()) {
//            ApplicationConstant.SurveyId = "1.0";
//            PrefUtils.saveToPrefs(PersonalDetailsActivity.this, ApplicationConstant.SURVEY_ID, "1.0");
//        } else {
//            double count = Double.parseDouble(ApplicationConstant.SurveyId);
//            count = count + 1.0;
//            PrefUtils.saveToPrefs(PersonalDetailsActivity.this, ApplicationConstant.SURVEY_ID, "" + count);
//        }


        onCLickListners();

    }

    private void onCLickListners() {

        CaptureBiometric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new Thread(new Runnable() {

                    public void run() {

                        OnCapture1(10000);

                    }
                }).start();

            }
        });

        BtnOpenDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                progressDialog = CustomProgressDialog.getDialogue(PersonalDetailsActivity.this);
//                progressDialog.show();

                bsp.OpenDevice();

            }
        });


        mBtnAddharCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEditAadhar.getText().toString().trim().isEmpty()) {
                    mEditAadhar.setError("enter aadhar to verify");
                    mEditAadhar.requestFocus();
                } else if (mEditAadhar.getText().toString().trim().length() != 12) {
                    mEditAadhar.setError("enter correct aadhar to verify");
                    mEditAadhar.requestFocus();
                } else {

                    AADHAR_NO = mEditAadhar.getText().toString().trim();

                    View viewAdd = LayoutInflater.from(PersonalDetailsActivity.this).inflate(R.layout.layout_select_type, null);
                    CardView cCardOTP = (androidx.cardview.widget.CardView) viewAdd.findViewById(R.id.CardOTP);
                    CardView cCardBiometric = (androidx.cardview.widget.CardView) viewAdd.findViewById(R.id.CardBiometric);
                    CardView cCardScanQR = (androidx.cardview.widget.CardView) viewAdd.findViewById(R.id.CardScanQR);


                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(PersonalDetailsActivity.this);
                    builder.setView(viewAdd);
                    final android.app.AlertDialog alertDialog = builder.create();
                    alertDialog.setCanceledOnTouchOutside(false);
                    alertDialog.setCancelable(true);

                    cCardBiometric.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            alertDialog.dismiss();
                            ApplicationConstant.displayMessageDialog(PersonalDetailsActivity.this, "", "Credentials not found.");
                        }
                    });

                    cCardOTP.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            alertDialog.dismiss();
                            SendOtpForAadhar();
                        }
                    });

                    cCardScanQR.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            alertDialog.dismiss();
                            Intent intent = new Intent(PersonalDetailsActivity.this, ScanQrForAadharActivity.class);
                            startActivityForResult(intent, 12345);
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

                View viewAdd = LayoutInflater.from(PersonalDetailsActivity.this).inflate(R.layout.layout_add_criminal_cases, null);
                ImageView cImage_cancel = (ImageView) viewAdd.findViewById(R.id.image_cancel);
                final EditText cEditSNo = (EditText) viewAdd.findViewById(R.id.EditSNo);
                final EditText cEditDate = (EditText) viewAdd.findViewById(R.id.EditDate);
                final EditText cEditFir = (EditText) viewAdd.findViewById(R.id.EditFir);
                final EditText cEditNamePolice = (EditText) viewAdd.findViewById(R.id.EditNamePolice);
                final EditText cEditStatusCase = (EditText) viewAdd.findViewById(R.id.EditStatusCase);
                TextView cTextAddCases = (TextView) viewAdd.findViewById(R.id.TextAddCases);


                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(PersonalDetailsActivity.this);

                builder.setView(viewAdd);
                final android.app.AlertDialog alertDialog = builder.create();

                cImage_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });

                cEditDate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        DatePickerDialog datePickerDialog = new DatePickerDialog(new ContextThemeWrapper(PersonalDetailsActivity.this, R.style.DialogTheme),
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

                        if (cEditSNo.getText().toString().trim().isEmpty()) {
                            cEditSNo.setError("enter case no");
                            cEditSNo.requestFocus();
                        } else if (cEditDate.getText().toString().trim().isEmpty()) {
                            cEditDate.setError("enter case date");
                            cEditDate.requestFocus();
                        } else if (cEditFir.getText().toString().trim().isEmpty()) {
                            cEditFir.setError("enter fir no");
                            cEditFir.requestFocus();
                        } else if (cEditNamePolice.getText().toString().trim().isEmpty()) {
                            cEditNamePolice.setError("enter police name");
                            cEditNamePolice.requestFocus();
                        } else if (cEditStatusCase.getText().toString().trim().isEmpty()) {
                            cEditStatusCase.setError("enter case status");
                            cEditStatusCase.requestFocus();
                        } else {


                            CriminalCases criminalCases = new CriminalCases(cEditSNo.getText().toString().trim(),
                                    cEditDate.getText().toString().trim(),
                                    cEditFir.getText().toString().trim(),
                                    cEditNamePolice.getText().toString().trim(),
                                    cEditStatusCase.getText().toString().trim()
                            );
                            listCriminalCases.add(criminalCases);

                            CriminalCasesAdpater criminalCasesAdpater = new CriminalCasesAdpater(PersonalDetailsActivity.this);
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

        RGCriminal.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioSexButton = (RadioButton) findViewById(checkedId);
                IS_CRIMINALCASE = radioSexButton.getText().toString().trim();
                if (IS_CRIMINALCASE.contains("Yes")) {
                    linear_cases.setVisibility(View.VISIBLE);
                    IS_CRIMINALCASE = "1";

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
                if (takePictureIntent.resolveActivity(PersonalDetailsActivity.this.getPackageManager()) != null) {
                    // Create the File where the photo should go
                    File photoFile = null;
                    try {
                        photoPath = ApplicationConstant.createImageFile(ApplicationConstant.SurveyId + "_profile.jpeg", "Profile", PersonalDetailsActivity.this);
                        photoFile = new File(photoPath);
                    } catch (IOException ex) {
                        // Error occurred while creating the File
                    }
                    // Continue only if the File was successfully created
                    if (photoFile != null) {
                        photoURI = SurveyAppFileProvider.getUriForFile(PersonalDetailsActivity.this,
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
                if (takePictureIntent.resolveActivity(PersonalDetailsActivity.this.getPackageManager()) != null) {
                    // Create the File where the photo should go
                    File photoFile = null;
                    try {
                        VindingPhotoPath = ApplicationConstant.createImageFile(ApplicationConstant.SurveyId + "_vendingsite.jpeg", "Profile", PersonalDetailsActivity.this);
                        photoFile = new File(VindingPhotoPath);
                    } catch (IOException ex) {
                        // Error occurred while creating the File
                    }
                    // Continue only if the File was successfully created
                    if (photoFile != null) {
                        photoURI = SurveyAppFileProvider.getUriForFile(PersonalDetailsActivity.this,
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

                onBackPressed();

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

                } else if (mLinearOne.getVisibility() == View.VISIBLE) {

                    try {
                        int selectedId = RGSex.getCheckedRadioButtonId();
                        RadioButton radioSexButton = (RadioButton) findViewById(selectedId);

                        if (radioSexButton.getText().toString().trim().equals("a. Male")) {
                            SEX = "M";
                        } else if (radioSexButton.getText().toString().trim().equals("b. Female")) {
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

                } else if (mLinearFour.getVisibility() == View.VISIBLE) {

                    if (validate4()) {
                        mLinearFour.setVisibility(View.GONE);
                        mLinearOne.setVisibility(View.GONE);
                        mLinearThree.setVisibility(View.GONE);
                        mLinearTwo.setVisibility(View.GONE);
                        mLinearFive.setVisibility(View.VISIBLE);

//                        mBtnNext.setText("Submit");
                    }
                } else {

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
                            + mEditRoad.getText().toString().trim() + ", "
                            + mEditCity.getText().toString().trim() + ", "
                            + mEditPincode.getText().toString().trim();

                    PERMENENT_ADDRESS = mEditPArea.getText().toString().trim() + ", "
                            + mEditPHouseNo.getText().toString().trim() + ", "
                            + mEditPRoad.getText().toString().trim() + ", "
                            + mEditPCity.getText().toString().trim() + ", "
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

                        if (ApplicationConstant.ISLOCALDB) {


                            insertPersonalDetails();

                        } else if (!ApplicationConstant.isNetworkAvailable(PersonalDetailsActivity.this)) {

                            ApplicationConstant.displayMessageDialog(PersonalDetailsActivity.this, "No Internet Connection", "Please enable internet connection first to proceed");

                        } else {
                            AddSurvey();

                        }

                    }
//                    startActivity(new Intent(PersonalDetailsActivity.this, VendorsFamDetailsActivity.class));

                }


            }
        });


    }

    private boolean validate4() {
//        if (!ApplicationConstant.isNetworkAvailable(PersonalDetailsActivity.this)) {
//
//            ApplicationConstant.displayMessageDialog(PersonalDetailsActivity.this, "No Internet Connection", "Please enable internet connection first to proceed");
//
//            return false;
//        }

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
//        else
            if (mEditAadhar.getText().toString().trim().isEmpty()) {
            mEditAadhar.setError("Enter Aadhar Number");
            mEditAadhar.requestFocus();
            return false;
        } else if (mEditAadhar.getText().toString().trim().length() < 12) {
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
//        if (!ApplicationConstant.isNetworkAvailable(PersonalDetailsActivity.this)) {
//
//            ApplicationConstant.displayMessageDialog(PersonalDetailsActivity.this, "No Internet Connection", "Please enable internet connection first to proceed");
//
//            return false;
//        } else

            if (WHETHER_WIDOWED.trim().isEmpty()) {
            ApplicationConstant.displayMessageDialog(PersonalDetailsActivity.this, "", "Select Options");
//            mEditAge.requestFocus();
            return false;
        } else if (mSpinnerCategory.getSelectedItem().toString().trim().isEmpty()) {
            mEditArea.setError("Select Category");
            mSpinnerCategory.requestFocus();
            return false;
        } else if (mEditAnnualIncome.getText().toString().trim().isEmpty()) {
            mEditAnnualIncome.setError("Enter Vending Site");
            mEditAnnualIncome.requestFocus();
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

//        if (!ApplicationConstant.isNetworkAvailable(PersonalDetailsActivity.this)) {
//
//            ApplicationConstant.displayMessageDialog(PersonalDetailsActivity.this, "No Internet Connection", "Please enable internet connection first to proceed");
//
//            return false;
//        } else

            if (mEditMobile.getText().toString().trim().isEmpty()) {
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

//        if (!ApplicationConstant.isNetworkAvailable(PersonalDetailsActivity.this)) {
//
//            ApplicationConstant.displayMessageDialog(PersonalDetailsActivity.this, "No Internet Connection", "Please enable internet connection first to proceed");
//
//            return false;
//        } else

            if (photoPath.isEmpty()) {
            ApplicationConstant.displayMessageDialog(PersonalDetailsActivity.this, "", "Capture profile photo");

            return false;
        } else if (VindingPhotoPath.isEmpty()) {
            ApplicationConstant.displayMessageDialog(PersonalDetailsActivity.this, "", "Capture Vending Place photo");

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
            ApplicationConstant.displayMessageDialog(PersonalDetailsActivity.this, "", "Select Gender");
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
        } else if (getLocation.getLatitude() > 0.0D && getLocation.getLongitude() > 0.0D) {
            return true;

        } else {
            EnableGPSAutoMatically();

        }

        return true;
    }

    private boolean validate() {
//        if (!ApplicationConstant.isNetworkAvailable(PersonalDetailsActivity.this)) {
//
//            ApplicationConstant.displayMessageDialog(PersonalDetailsActivity.this, "No Internet Connection", "Please enable internet connection first to proceed");
//
//            return false;
//        } else

        if (IS_CRIMINALCASE.trim().isEmpty()) {
            ApplicationConstant.displayMessageDialog(PersonalDetailsActivity.this, "", "Select Options");
//            mEditAge.requestFocus();
            return false;
        } else if (linear_cases.getVisibility() == View.VISIBLE) {

            if (listCriminalCases.isEmpty()) {
                ApplicationConstant.DisplayMessageDialog(PersonalDetailsActivity.this, "", "Add Criminal Cases");
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

        image_bio = (ImageView) findViewById(R.id.image_bio);
        CaptureBiometric = (CardView) findViewById(R.id.CaptureBiometric);
        TextBioCaptured = (TextView) findViewById(R.id.TextBioCaptured);
        mEditAnnualIncome = (EditText) findViewById(R.id.EditAnnualIncome);

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
        BtnOpenDevice = (CardView) findViewById(R.id.BtnOpenDevice);


        TextAddCases = (TextView) findViewById(R.id.TextAddCases);

        view_Criminal_Case = (RecyclerView) findViewById(R.id.view_Criminal_Case);
        view_Criminal_Case.setLayoutManager(new LinearLayoutManager(PersonalDetailsActivity.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            if (requestCode == 12345) {

                if (data != null) {
                    String xmlData = data.getExtras().getString("SCANDATA");

                    XmlToJson xmlToJson = new XmlToJson.Builder(xmlData).build();

                    JSONObject jsonObject = xmlToJson.toJson();

                    String Name = "";

                    Address address = new Address();

                    Iterator<String> keys=jsonObject.keys();

                    while (keys.hasNext())
                    {
                        String keyValue = (String)keys.next();

                    try {
//                        JSONObject jsonAadhar = jsonObject.getJSONObject("PrintLetterBarcodeData");
                        JSONObject jsonAadhar = jsonObject.getJSONObject(keyValue);
                        address.setLoc(jsonAadhar.getString("loc"));
//                        address.setLandmark(jsonAadhar.getString("lm"));
                        address.setLandmark(null);
                        address.setSubdist(jsonAadhar.getString("subdist"));
                        address.setVtc(jsonAadhar.getString("vtc"));
                        address.setDist(jsonAadhar.getString("dist"));
                        address.setHouse(jsonAadhar.getString("house"));
                        address.setPo(jsonAadhar.getString("po"));
                        address.setState(jsonAadhar.getString("state"));
                        address.setStreet(jsonAadhar.getString("street"));
                        address.setCountry("India");

                        aadharData = new AadharData();
                        aadharData.setAddress(address);
                        aadharData.setGender(jsonAadhar.getString("gender"));

                        try {
                            String[] datearray = jsonAadhar.getString("dob").trim().split("\\/");
                            aadharData.setDob(datearray[2] + "-" + datearray[1] + "-" + datearray[0]);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            aadharData.setDob("1995-05-30");

                        }


//                        aadharData.setDob(jsonAadhar.getString("dob"));
                        aadharData.setFullName(jsonAadhar.getString("name"));
                        aadharData.setAadhaarNumber(jsonAadhar.getString("uid"));
                        aadharData.setRawXml(xmlData);
                        aadharData.setZip(jsonAadhar.getString("pc"));
                        aadharData.setZipData("zipdata");
                        aadharData.setCareOf("careoff");
                        aadharData.setFaceStatus(false);
                        aadharData.setFaceScore(-1);
                        aadharData.setHasImage(false);
                        aadharData.setClientId("clientid123");
                        aadharData.setShareCode("0");
                        aadharData.setProfileImage("sgdvsgsd");

                        ApplicationConstant.displayMessageDialog(PersonalDetailsActivity.this, "Aadhar Details Scanned Successfully", aadharData.getAadhaarNumber() + "\n" + aadharData.getFullName());


                        GsonBuilder gsonBuilder = new GsonBuilder();
                        Gson gson = gsonBuilder.create();
                        String json_Aadhar = new Gson().toJson(aadharData);

                        AADHAR_DETAILS = json_Aadhar;

                    } catch (JSONException e) {
                        e.printStackTrace();

                        ApplicationConstant.displayMessageDialog(PersonalDetailsActivity.this,"","Invalid QR");

                    }

                    }

                }


            } else if (requestCode == 1) {

                Bitmap bitmap = ApplicationConstant.CompressedBitmap(new File(photoPath));


                Glide.with(PersonalDetailsActivity.this).load(photoURI)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                        .into(mImgVendorPhoto);

                if (ApplicationConstant.ISLOCALDB) {

                } else {
                    UploadVendorPhoto();

                }

            } else if (requestCode == 2) {

                Glide.with(PersonalDetailsActivity.this).load(photoURI)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                        .into(mImgVendorSite);

                Bitmap bitmap = ApplicationConstant.CompressedBitmap(new File(VindingPhotoPath));

                if (ApplicationConstant.ISLOCALDB) {

                } else {
                    UploadVendingSitePhoto();

                }

            }
        }

    }

    private void AddSurvey() {


        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        CRIMINALCASE_NO = new Gson().toJson(listCriminalCases);

        progressDialog = CustomProgressDialog.getDialogue(PersonalDetailsActivity.this);
        progressDialog.show();

        String username = PrefUtils.getFromPrefs(PersonalDetailsActivity.this, ApplicationConstant.USERDETAILS.API_KEY, "");

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

        UNiq_Id = PrefUtils.getFromPrefs(PersonalDetailsActivity.this, ApplicationConstant.URI_NO_, "");

        String CORPORATION = PrefUtils.getFromPrefs(PersonalDetailsActivity.this, ApplicationConstant.CORPORATION, "");
        String ZONE = PrefUtils.getFromPrefs(PersonalDetailsActivity.this, ApplicationConstant.ZONE, "");
        String WARD = PrefUtils.getFromPrefs(PersonalDetailsActivity.this, ApplicationConstant.WARD, "");

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

        RequestBody LATITUDE = RequestBody.create(MediaType.parse("multipart/form-data"), "" + Latitude);
        RequestBody LONGITUDE = RequestBody.create(MediaType.parse("multipart/form-data"), "" + Longitude);
        RequestBody SCANRESULT_ = RequestBody.create(MediaType.parse("multipart/form-data"), "" + SCANRESULT);


        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + PrefUtils.getFromPrefs(PersonalDetailsActivity.this, ApplicationConstant.USERDETAILS.API_KEY, ""));

        ApiInterface apiservice = ApiService.getApiClient().create(ApiInterface.class);
        Call<UpdateSurveyResponse> call = apiservice.getAddSurvey(headers,
                SURVEY_ID_,
                CORPORATION_,
                ZONE_,
                WARD_,
                SCANRESULT_,
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

                        PrefUtils.saveToPrefs(PersonalDetailsActivity.this, ApplicationConstant.CONTACT, CONTACT_NO);

                        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(PersonalDetailsActivity.this);
                        builder.setTitle("Personal Details");
                        builder.setMessage("Saved successfully");
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                startActivity(new Intent(PersonalDetailsActivity.this, VendorsFamDetailsActivity.class));

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
                                response.body().getMessage());
                    }

                } else {

                    try {
                        ApplicationConstant.displayMessageDialog(PersonalDetailsActivity.this,
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
                ApplicationConstant.displayMessageDialog(PersonalDetailsActivity.this, "Response", getString(R.string.net_speed_problem));

            }
        });
    }


    private void UploadVendorPhoto() {

        progressDialog = CustomProgressDialog.getDialogue(PersonalDetailsActivity.this);
        progressDialog.show();

        File file1 = new File(photoPath);

        RequestBody request_photo =
                RequestBody.create(MediaType.parse("image/png"), file1);

        String UNiq_Id = "";


        UNiq_Id = PrefUtils.getFromPrefs(PersonalDetailsActivity.this, ApplicationConstant.URI_NO_, "");

        String CORPORATION = PrefUtils.getFromPrefs(PersonalDetailsActivity.this, ApplicationConstant.CORPORATION, "");
        String ZONE = PrefUtils.getFromPrefs(PersonalDetailsActivity.this, ApplicationConstant.ZONE, "");
        String WARD = PrefUtils.getFromPrefs(PersonalDetailsActivity.this, ApplicationConstant.WARD, "");

        RequestBody CORPORATION_ = RequestBody.create(MediaType.parse("multipart/form-data"), CORPORATION);
        RequestBody ZONE_ = RequestBody.create(MediaType.parse("multipart/form-data"), ZONE);
        RequestBody WARD_ = RequestBody.create(MediaType.parse("multipart/form-data"), WARD);

// MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body_fhoto =
                MultipartBody.Part.createFormData("photo_of_the_street_vendor", file1.getName(), request_photo);

        RequestBody SURVEY_ID_ = RequestBody.create(MediaType.parse("multipart/form-data"), UNiq_Id);


        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + PrefUtils.getFromPrefs(PersonalDetailsActivity.this, ApplicationConstant.USERDETAILS.API_KEY, ""));

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

                        ApplicationConstant.displayToastMessage(PersonalDetailsActivity.this,
                                "Photo saved successfully");


                    } else {
                        if (progressDialog != null && progressDialog.isShowing())
                            progressDialog.dismiss();

                        ApplicationConstant.displayMessageDialog(PersonalDetailsActivity.this,
                                "Response",
                                response.body().getMessage());
                    }

                } else {
                    if (progressDialog != null && progressDialog.isShowing())
                        progressDialog.dismiss();

                    try {
                        ApplicationConstant.displayMessageDialog(PersonalDetailsActivity.this,
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
                ApplicationConstant.displayMessageDialog(PersonalDetailsActivity.this, "Response", getString(R.string.net_speed_problem));

            }
        });
    }


    public static String getRealPathFromUri(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
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

        progressDialog = CustomProgressDialog.getDialogue(PersonalDetailsActivity.this);
        progressDialog.show();

        File file1 = new File(VindingPhotoPath);

        RequestBody request_photo =
                RequestBody.create(MediaType.parse("image/png"), file1);

        String UNiq_Id = "";


        UNiq_Id = PrefUtils.getFromPrefs(PersonalDetailsActivity.this, ApplicationConstant.URI_NO_, "");

        String CORPORATION = PrefUtils.getFromPrefs(PersonalDetailsActivity.this, ApplicationConstant.CORPORATION, "");
        String ZONE = PrefUtils.getFromPrefs(PersonalDetailsActivity.this, ApplicationConstant.ZONE, "");
        String WARD = PrefUtils.getFromPrefs(PersonalDetailsActivity.this, ApplicationConstant.WARD, "");

        RequestBody CORPORATION_ = RequestBody.create(MediaType.parse("multipart/form-data"), CORPORATION);
        RequestBody ZONE_ = RequestBody.create(MediaType.parse("multipart/form-data"), ZONE);
        RequestBody WARD_ = RequestBody.create(MediaType.parse("multipart/form-data"), WARD);

// MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body_fhoto =
                MultipartBody.Part.createFormData("photo_of_vendor_site", file1.getName(), request_photo);

        RequestBody SURVEY_ID_ = RequestBody.create(MediaType.parse("multipart/form-data"), UNiq_Id);


        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + PrefUtils.getFromPrefs(PersonalDetailsActivity.this, ApplicationConstant.USERDETAILS.API_KEY, ""));

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

                        ApplicationConstant.displayToastMessage(PersonalDetailsActivity.this,
                                "Vending Site Photo saved successfully");


                    } else {
                        if (progressDialog != null && progressDialog.isShowing())
                            progressDialog.dismiss();

                        ApplicationConstant.displayMessageDialog(PersonalDetailsActivity.this,
                                "Response",
                                response.body().getMessage());
                    }

                } else {
                    if (progressDialog != null && progressDialog.isShowing())
                        progressDialog.dismiss();

                    try {
                        ApplicationConstant.displayMessageDialog(PersonalDetailsActivity.this,
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
                ApplicationConstant.displayMessageDialog(PersonalDetailsActivity.this, "Response", getString(R.string.net_speed_problem));

            }
        });
    }

    private void SendOtpForAadhar() {

        progressDialog = CustomProgressDialog.getDialogue(PersonalDetailsActivity.this);
        progressDialog.show();


        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + ApplicationConstant.AADHAR_TOKEN);

        HashMap<String, String> body = new HashMap<>();
        body.put("id_number", AADHAR_NO);

        ApiInterface apiservice = ApiService.getApiClient2().create(ApiInterface.class);
        Call<AadharOtpResponse> call = apiservice.generateOtpAadhar(headers, body);

        call.enqueue(new Callback<AadharOtpResponse>() {
            @Override
            public void onResponse(Call<AadharOtpResponse> call, Response<AadharOtpResponse> response) {

                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();

                if (response.body() != null) {

                    if (response.body().isSuccess()) {


                        ApplicationConstant.displayToastMessage(PersonalDetailsActivity.this,
                                "" + response.body().getMessage());

                        AadharOtpData aadhar_data = response.body().getData();

                        if (aadhar_data.isIfNumber()) {
                            if (aadhar_data.isValidAadhaar()) {

                                String Client_Id = aadhar_data.getClientId().trim();
                                View viewAdd = LayoutInflater.from(PersonalDetailsActivity.this).inflate(R.layout.layout_otp_screen, null);
                                ImageView aImage_cancel = (ImageView) viewAdd.findViewById(R.id.image_cancel);
                                EditText aEditOtp = (EditText) viewAdd.findViewById(R.id.EditOtp);
                                TextView aText_resend = (TextView) viewAdd.findViewById(R.id.text_resend);
                                TextView aTextSubmitOtp = (TextView) viewAdd.findViewById(R.id.TextSubmitOtp);

                                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(PersonalDetailsActivity.this);
                                builder.setView(viewAdd);
                                final android.app.AlertDialog alertDialog = builder.create();
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
                                        if (aEditOtp.getText().toString().trim().isEmpty()) {
                                            aEditOtp.setError("enter otp to verify");
                                            aEditOtp.requestFocus();
                                        } else if (aEditOtp.getText().toString().trim().length() != 6) {
                                            aEditOtp.setError("enter correct otp to verify");
                                            aEditOtp.requestFocus();
                                        } else {
                                            SubmitOtpForAadhar(aEditOtp.getText().toString().trim(), Client_Id, alertDialog);
                                        }
                                    }
                                });


                                alertDialog.show();


                            } else {
                                ApplicationConstant.displayMessageDialog(PersonalDetailsActivity.this, "Response", "Enter correct aadhar no");

                            }

                        } else {
                            ApplicationConstant.displayMessageDialog(PersonalDetailsActivity.this, "Response", "Mobile no not linked");
                        }


                    } else {

                        ApplicationConstant.displayMessageDialog(PersonalDetailsActivity.this,
                                "Response",
                                response.body().getMessage());
                    }

                } else {

                    try {
                        ApplicationConstant.displayMessageDialog(PersonalDetailsActivity.this,
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
                ApplicationConstant.displayMessageDialog(PersonalDetailsActivity.this, "Response", getString(R.string.net_speed_problem));

            }
        });
    }

    private void SubmitOtpForAadhar(String otp, String clientId, AlertDialog alertDialog) {

        progressDialog = CustomProgressDialog.getDialogue(PersonalDetailsActivity.this);
        progressDialog.show();


        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + ApplicationConstant.AADHAR_TOKEN);

        HashMap<String, String> body = new HashMap<>();
        body.put("client_id", clientId);
        body.put("otp", otp);

        ApiInterface apiservice = ApiService.getApiClient2().create(ApiInterface.class);
        Call<AadharValidResponse> call = apiservice.SubmitOtpForAadhar(headers, body);

        call.enqueue(new Callback<AadharValidResponse>() {
            @Override
            public void onResponse(Call<AadharValidResponse> call, Response<AadharValidResponse> response) {

                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();

                if (response.body() != null) {

                    if (response.body().isSuccess()) {


//                        ApplicationConstant.displayToastMessage(PersonalDetailsActivity.this,
//                                ""+response.body().getMessage());


                        if (response.body().getData() != null) {
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


                        } else {
                            ApplicationConstant.displayMessageDialog(PersonalDetailsActivity.this, "Response", "Enter correct mobile no");
                        }


                    } else {

                        ApplicationConstant.displayMessageDialog(PersonalDetailsActivity.this,
                                "Response",
                                response.body().getMessage());
                    }

                } else {

                    try {
                        ApplicationConstant.displayMessageDialog(PersonalDetailsActivity.this,
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
                ApplicationConstant.displayMessageDialog(PersonalDetailsActivity.this, "Response", getString(R.string.net_speed_problem));

            }
        });
    }

    private void ResendOtpForAadhar() {

        progressDialog = CustomProgressDialog.getDialogue(PersonalDetailsActivity.this);
        progressDialog.show();


        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + ApplicationConstant.AADHAR_TOKEN);

        HashMap<String, String> body = new HashMap<>();
        body.put("id_number", AADHAR_NO);

        ApiInterface apiservice = ApiService.getApiClient2().create(ApiInterface.class);
        Call<AadharOtpResponse> call = apiservice.generateOtpAadhar(headers, body);

        call.enqueue(new Callback<AadharOtpResponse>() {
            @Override
            public void onResponse(Call<AadharOtpResponse> call, Response<AadharOtpResponse> response) {

                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();

                if (response.body() != null) {

                    if (response.body().isSuccess()) {


                        ApplicationConstant.displayToastMessage(PersonalDetailsActivity.this,
                                "" + response.body().getMessage());

                        AadharOtpData aadhar_data = response.body().getData();

                        if (aadhar_data.isIfNumber()) {
                            if (aadhar_data.isValidAadhaar()) {


                            } else {
                                ApplicationConstant.displayMessageDialog(PersonalDetailsActivity.this, "Response", "Enter correct aadhar no");

                            }

                        } else {
                            ApplicationConstant.displayMessageDialog(PersonalDetailsActivity.this, "Response", "Mobile no not linked");
                        }


                    } else {

                        ApplicationConstant.displayMessageDialog(PersonalDetailsActivity.this,
                                "Response",
                                response.body().getMessage());
                    }

                } else {

                    try {
                        ApplicationConstant.displayMessageDialog(PersonalDetailsActivity.this,
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
                ApplicationConstant.displayMessageDialog(PersonalDetailsActivity.this, "Response", getString(R.string.net_speed_problem));

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

    public static Bitmap rotateImageIfRequired(Bitmap img) throws IOException {

        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                return rotateImage(img, 90);
            case ExifInterface.ORIENTATION_ROTATE_180:
                return rotateImage(img, 180);
            case ExifInterface.ORIENTATION_ROTATE_270:
                return rotateImage(img, 270);
            default:
                return img;
        }


    }

    public static Bitmap rotateImage(Bitmap img, int degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        Bitmap rotatedImg = Bitmap.createBitmap(img, 0, 0, img.getWidth(), img.getHeight(), matrix, true);
        img.recycle();
        return rotatedImg;
    }

    @Override
    public void onBackPressed() {

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

        } else if (mLinearOne.getVisibility() == View.VISIBLE) {

            mLinearHead.setVisibility(View.VISIBLE);
            mLinearFour.setVisibility(View.GONE);
            mLinearOne.setVisibility(View.GONE);
            mLinearThree.setVisibility(View.GONE);
            mLinearTwo.setVisibility(View.GONE);
            mLinearFive.setVisibility(View.GONE);

            mBtnPrevious.setVisibility(View.GONE);

        } else {

            super.onBackPressed();

        }

    }


    public void initData() {

        NBioBSPJNI.CURRENT_PRODUCT_ID = 0;
        if (bsp == null) {
            bsp = new NBioBSPJNI("010701-613E5C7F4CC7C4B0-72E340B47E034015", this, mCallback);
//    		bsp = new NBioBSPJNI("010101-B4AB5DD87959F205-5515E7924B626FE0", this,  mCallback);
            String msg = null;
            if (bsp.IsErrorOccured())
                msg = "NBioBSP Error: " + bsp.GetErrorCode();
            else {
                msg = "SDK Version: " + bsp.GetVersion();
                exportEngine = bsp.new Export();
                indexSearch = bsp.new IndexSearch();
            }
            ApplicationConstant.displayToastMessage(PersonalDetailsActivity.this, msg);
        }

//        userDialog = new UserDialog();

    }


    @Override
    public void onDestroy() {

        if (bsp != null) {
            bsp.dispose();
            bsp = null;
        }
        super.onDestroy();
    }

    NBioBSPJNI.CAPTURE_CALLBACK mCallback = new NBioBSPJNI.CAPTURE_CALLBACK() {

        public void OnDisConnected() {
            NBioBSPJNI.CURRENT_PRODUCT_ID = 0;


            String message = "NBioBSP Disconnected: " + bsp.GetErrorCode();

            ApplicationConstant.displayToastMessage(PersonalDetailsActivity.this, message);


        }

        public void OnConnected() {

            String message = "Device Open Success : " + bsp.GetErrorCode();
            ApplicationConstant.displayToastMessage(PersonalDetailsActivity.this, message);

            if (CaptureBiometric.getVisibility() == View.GONE) {
                BtnOpenDevice.setVisibility(View.GONE);
                CaptureBiometric.setVisibility(View.VISIBLE);
            } else {
                BtnOpenDevice.setVisibility(View.VISIBLE);
                CaptureBiometric.setVisibility(View.GONE);
            }


        }


        public int OnCaptured(NBioBSPJNI.CAPTURED_DATA capturedData) {
            ApplicationConstant.displayToastMessage(PersonalDetailsActivity.this, "IMAGE Quality: " + capturedData.getImageQuality());


            if (capturedData.getImage() != null) {
                image_bio.setImageBitmap(capturedData.getImage());
            }

            // quality : 40~100
            if (capturedData.getImageQuality() >= QUALITY_LIMIT) {
//                hideLoading();
                return NBioBSPJNI.ERROR.NBioAPIERROR_USER_CANCEL;
            } else if (capturedData.getDeviceError() != NBioBSPJNI.ERROR.NBioAPIERROR_NONE) {
//                hideLoading();
                return capturedData.getDeviceError();
            } else {
                return NBioBSPJNI.ERROR.NBioAPIERROR_NONE;
            }
        }

    };

    int nFIQ = 0;
    String msg = "";

    public synchronized void OnCapture1(int timeout) {

        NBioBSPJNI.FIR_HANDLE hCapturedFIR, hAuditFIR;
        NBioBSPJNI.CAPTURED_DATA capturedData;

        hCapturedFIR = bsp.new FIR_HANDLE();
        hAuditFIR = bsp.new FIR_HANDLE();
        capturedData = bsp.new CAPTURED_DATA();

//        bCapturedFirst = true;

//		bsp.Capture(NBioBSPJNI.FIR_PURPOSE.ENROLL,hCapturedFIR,timeout, hAuditFIR, capturedData,  mCallback,0, null);
        bsp.Capture(NBioBSPJNI.FIR_PURPOSE.ENROLL, hCapturedFIR, timeout, hAuditFIR, capturedData);

//        if(sampleDialogFragment!=null && "DIALOG_TYPE_PROGRESS".equals(sampleDialogFragment.getTag()))
//            sampleDialogFragment.dismiss();
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();


        if (bsp.IsErrorOccured()) {
            msg = "NBioBSP Capture Error: " + bsp.GetErrorCode();
        } else {
            NBioBSPJNI.INPUT_FIR inputFIR;

            inputFIR = bsp.new INPUT_FIR();

            // Make ISO 19794-2 data
            {
                NBioBSPJNI.Export.DATA exportData;

                inputFIR.SetFIRHandle(hCapturedFIR);

                exportData = exportEngine.new DATA();

                exportEngine.ExportFIR(inputFIR, exportData, NBioBSPJNI.EXPORT_MINCONV_TYPE.OLD_FDA);

                if (bsp.IsErrorOccured()) {
                    runOnUiThread(new Runnable() {

                        public void run() {
                            msg = "NBioBSP ExportFIR Error: " + bsp.GetErrorCode();
                            Toast.makeText(PersonalDetailsActivity.this, msg, Toast.LENGTH_SHORT).show();
                        }
                    });
                    return;
                }

                if (byTemplate1 != null)
                    byTemplate1 = null;

                byTemplate1 = new byte[exportData.FingerData[0].Template[0].Data.length];
                byTemplate1 = exportData.FingerData[0].Template[0].Data;
            }

            // Make Raw Image data
            {
                NBioBSPJNI.Export.AUDIT exportAudit;

                inputFIR.SetFIRHandle(hAuditFIR);

                exportAudit = exportEngine.new AUDIT();

                exportEngine.ExportAudit(inputFIR, exportAudit);

                if (bsp.IsErrorOccured()) {

                    runOnUiThread(new Runnable() {

                        public void run() {
                            msg = "NBioBSP ExportAudit Error: " + bsp.GetErrorCode();
//                            tvInfo.setText(msg);
                            Toast.makeText(PersonalDetailsActivity.this, msg, Toast.LENGTH_SHORT).show();
                        }
                    });

                    return;
                }

                if (byCapturedRaw1 != null)
                    byCapturedRaw1 = null;

                byCapturedRaw1 = new byte[exportAudit.FingerData[0].Template[0].Data.length];
                byCapturedRaw1 = exportAudit.FingerData[0].Template[0].Data;

                nCapturedRawWidth1 = exportAudit.ImageWidth;
                nCapturedRawHeight1 = exportAudit.ImageHeight;

                msg = "First Capture Success";

                NBioBSPJNI.NFIQInfo info = bsp.new NFIQInfo();
                info.pRawImage = byCapturedRaw1;
                info.nImgWidth = nCapturedRawWidth1;
                info.nImgHeight = nCapturedRawHeight1;

                bsp.getNFIQInfoFromRaw(info);

                if (bsp.IsErrorOccured()) {
                    runOnUiThread(new Runnable() {

                        public void run() {
                            msg = "NBioBSP getNFIQInfoFromRaw Error: " + bsp.GetErrorCode();
                            Toast.makeText(PersonalDetailsActivity.this, msg, Toast.LENGTH_SHORT).show();
                        }
                    });

                    return;
                }

                nFIQ = info.pNFIQ;


                // ISO 19794-4
                {
                    NBioBSPJNI.ISOBUFFER ISOBuffer = bsp.new ISOBUFFER();
                    bsp.ExportRawToISOV1(exportAudit, ISOBuffer, false, NBioBSPJNI.COMPRESS_MODE.NONE);
//					bsp.ExportRawToISOV1(exportAudit, ISOBuffer, false, NBioBSPJNI.COMPRESS_MODE.WSQ);

                    if (bsp.IsErrorOccured()) {
                        runOnUiThread(new Runnable() {

                            public void run() {
                                msg = "NBioBSP ExportRawToISOV1 Error: " + bsp.GetErrorCode();
                                Toast.makeText(PersonalDetailsActivity.this, msg, Toast.LENGTH_SHORT).show();
                            }
                        });

                        return;
                    }

                    NBioBSPJNI.NIMPORTRAWSET rawSet = bsp.new NIMPORTRAWSET();
                    bsp.ImportISOToRaw(ISOBuffer, rawSet);

                    if (bsp.IsErrorOccured()) {
                        runOnUiThread(new Runnable() {

                            public void run() {
                                msg = "NBioBSP ImportISOToRaw Error: " + bsp.GetErrorCode();
                                Toast.makeText(PersonalDetailsActivity.this, msg, Toast.LENGTH_SHORT).show();
                            }
                        });

                        return;
                    }

                    for (int i = 0; i < rawSet.Count; i++) {
                        msg += "  DataLen: " + rawSet.RawData[i].Data.length + "  " +
                                "FingerID: " + rawSet.RawData[i].FingerID + "  " +
                                "Width: " + rawSet.RawData[i].ImgWidth + "  " +
                                "Height: " + rawSet.RawData[i].ImgHeight + "  ";
                    }

                    if (byCapturedRaw1 != null)
                        byCapturedRaw1 = null;

                    byCapturedRaw1 = new byte[rawSet.RawData[0].Data.length];
                    byCapturedRaw1 = rawSet.RawData[0].Data;

                    nCapturedRawWidth1 = rawSet.RawData[0].ImgWidth;
                    nCapturedRawHeight1 = rawSet.RawData[0].ImgHeight;

                    runOnUiThread(new Runnable() {

                        public void run() {
                            Toast.makeText(PersonalDetailsActivity.this, msg, Toast.LENGTH_SHORT).show();
                        }
                    });

                }

            }


//			// wsq convert example
//			{
//                NBioBSPJNI.Export.TEMPLATE_DATA data = exportEngine.new TEMPLATE_DATA();
//                exportEngine.ConvertRawToWsq(byCapturedRaw1, nCapturedRawWidth1, nCapturedRawHeight1, data, 4);
//
//                if (bsp.IsErrorOccured())  {
//                    runOnUiThread(new Runnable() {
//
//                        public void run() {
//                            msg = "NBioBSP ConvertRawToWsq Error: " + bsp.GetErrorCode();
//                            tvInfo.setText(msg);
//                        }
//                    });
//
//                    return ;
//                }
//
//                NBioBSPJNI.Export.AUDIT exportAudit = exportEngine.new AUDIT();
//                exportEngine.ConvertWsqToRaw(data.Data, data.Data.length, exportAudit);
//
//                if (bsp.IsErrorOccured())  {
//                    runOnUiThread(new Runnable() {
//
//                        public void run() {
//                            msg = "NBioBSP ConvertWsqToRaw Error: " + bsp.GetErrorCode();
//                            tvInfo.setText(msg);
//                        }
//                    });
//
//                    return ;
//                }
//
//                byCapturedRaw1 = exportAudit.FingerData[0].Template[0].Data;
//			}
        }

        runOnUiThread(new Runnable() {

            public void run() {
//                tvInfo.setText(msg+",NFIQ:"+nFIQ);

                Toast.makeText(PersonalDetailsActivity.this, msg + ",NFIQ:" + nFIQ, Toast.LENGTH_SHORT).show();


//                if (byTemplate1 != null && byTemplate2 != null)  {
//                    btnVerifyTemplate.setEnabled(true);
//                }else{
//                    btnVerifyTemplate.setEnabled(false);
//                }
//
//                if (byCapturedRaw1 != null && byCapturedRaw2 != null)  {
//                    btnVerifyRaw.setEnabled(true);
//                }else{
//                    btnVerifyRaw.setEnabled(false);
//                }

            }
        });

    }

    public void insertPersonalDetails() {

        CRIMINALCASE_NO = new Gson().toJson(listCriminalCases);

        if (getLocation.getLatitude() > 0.0D && getLocation.getLongitude() > 0.0D) {
            Latitude = getLocation.getLatitude();
            Longitude = getLocation.getLongitude();
        }

        String LocalId = generateId();

        PrefUtils.saveToPrefs(PersonalDetailsActivity.this,ApplicationConstant.LOCAL_SURVEYID,LocalId);

        PersonalDetails personalDetails = new PersonalDetails(
                LocalId,
                photoPath,
                VindingPhotoPath,
                SCANRESULT,
                "" + Latitude,
                "" + Longitude,
                NAME_VENDOR,
                AADHAR_NO,
                SEX,
                AGE,
                DOB,
                CONTACT_NO,
                LANDLINE_NO,
                EDUCATION_STATUS,
                NAME_OFFATHER_HUSBAND,
                NAME_MOTHER,
                NAME_SPOUSE,
                WHETHER_WIDOWED,
                CATEGORY,
                RESIDENTIAL_ADDRESS,
                PERMENENT_ADDRESS,
                ANNUAL_INCOME,
                AADHAR_DETAILS,
                IS_CRIMINALCASE,
                CRIMINALCASE_NO);

        new InsertAsyncTask(surveyDao).execute(personalDetails);

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(PersonalDetailsActivity.this);
        builder.setTitle("Personal Details");
        builder.setMessage("Saved successfully in local db");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();


                startActivity(new Intent(PersonalDetailsActivity.this, VendorsFamDetailsActivity.class));

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }

    private class InsertAsyncTask extends AsyncTask<PersonalDetails, Void, Void> {
        SurveyDao surveyDao;

        public InsertAsyncTask(SurveyDao surveyDao) {
            this.surveyDao = surveyDao;
        }

        @Override
        protected Void doInBackground(PersonalDetails... personalDetails) {
            surveyDao.insertPersonalDetails(personalDetails[0]);
            return null;
        }
    }

    private String generateId() {


        String SurveyId = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSS");
            Date date = new Date();
            String tranID = sdf.format(date);
            int n = 6;
            Random randGen = new Random();
            int startNum = (int) Math.pow(10, n - 1);
            int range = (int) (Math.pow(10, n) - startNum);
            int randomNum = randGen.nextInt(range) + startNum;
            String ran = String.valueOf(randomNum);
            SurveyId = tranID + ran;
        } catch (Throwable e) {

        }
        return SurveyId;
    }


}
