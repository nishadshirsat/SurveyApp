package com.example.streethawkerssurveyapp.activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.streethawkerssurveyapp.R;
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

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VendingDetailsActivity extends AppCompatActivity {

    private LinearLayout mLinearMain;
    private LinearLayout mLinearOne;
    private Spinner mSpinnerItems;
    private EditText mEditVendingSite;
//    private EditText mEditFromTime;
//    private EditText mEditToTime;
    private EditText mEditLFromTime;
    private EditText mEditLToTime;
    private EditText mEditEFromTime;
    private EditText mEditEToTime;
    private EditText mEditAge;
    private LinearLayout mLinearTwo;
//    private EditText mEditAnnualIncome;
    //    private RadioButton mRadioY;
//    private RadioButton mRadioN;
    private Spinner mSpinnerVehical;
    private EditText mEditDob;
    private ImageView mImgCalendar;
    private LinearLayout mLinearThree, LinearVehical;
    //    private RadioButton mRadioDY;
//    private RadioButton mRadioDN;
    private Spinner mSpinnerChoice;
    private Button mBtnNext;
    private Button mBtnPrevious;
    private Button btn_all_days;
    private CheckBox mCheckM;
    private CheckBox mCheckT;
    private CheckBox mCheckW;
    private CheckBox mCheckTh;
    private CheckBox mCheckF;
    private CheckBox mCheckS;
    private CheckBox mCheckSu;
    RadioGroup RGDocument, RGVendor;
    TextView TextYes;
    private ProgressDialog progressDialog;

    private String
            TYPE_OF_VENDING = "",
            VENDING_SITE = "",
    //            VENDING_FROM = "",
//            VENDING_TO = "",
    VENDING_LFROM = "",
            VENDING_LTO = "",
            VENDING_EFROM = "",
            VENDING_ETO = "",
            YRS_OF_VENDING = "",

//            ANNUAL_INCOME = "",

            IS_RECOGNIZED_STREET_VENDOR = "",
            TYPE_OF_STRUCTURE = "",
            STARTING_DATE_VENDING = "",
            TEHABZARI_AVAILABLE = "",
            VENDING_AREA_CHOCE = "",
            M = "",
            T = "",
            W = "",
            Th = "",
            F = "",
            S = "",
            Su = "";

    StringBuilder NO_DAYS_ACTIVE;

    static final int TIME_DIALOG_ID = 1111;

    private Calendar myCalendar;
    private int mYear, mMonth, mDay;
    private int hour;
    private int minute;
    private int sec;
    TimePickerDialog picker;
    String format = "";

    private Spinner spinner_to_vending, spinner_from_vending;
    private String FromVending = "";
    private String ToVending = "";

    private LinearLayout mLinearHead;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vending_details);

        bindView();

        NO_DAYS_ACTIVE = new StringBuilder();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("URI NO: "+ApplicationConstant.SurveyId);

        myCalendar = Calendar.getInstance();
        mYear = myCalendar.get(Calendar.YEAR);
        mMonth = myCalendar.get(Calendar.MONTH);
        mDay = myCalendar.get(Calendar.DAY_OF_MONTH);

        hour = myCalendar.get(Calendar.HOUR_OF_DAY);
        minute = myCalendar.get(Calendar.MINUTE);
        sec = myCalendar.get(Calendar.SECOND);


//        mEditFromTime.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                picker = new TimePickerDialog(VendingDetailsActivity.this, new TimePickerDialog.OnTimeSetListener() {
//
//                    @Override
//                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
//                        if (hourOfDay >= 12) {
//                            format = "PM";
//                        } else {
//                            format = "AM";
//                        }
//                        mEditFromTime.setText(String.format("%02d:%02d", hourOfDay, minutes));
//                    }
//                }, hour, minute, false);
//
//                picker.show();
//
//            }
//        });
//
//        mEditToTime.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                picker = new TimePickerDialog(VendingDetailsActivity.this, new TimePickerDialog.OnTimeSetListener() {
//
//                    @Override
//                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
//                        if (hourOfDay >= 12) {
//                            format = "PM";
//                        } else {
//                            format = "AM";
//                        }
//                        mEditToTime.setText(String.format("%02d:%02d", hourOfDay, minutes));
//                    }
//                }, hour, minute, false);
//
//                picker.show();
//
//            }
//        });


        btn_all_days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCheckM.setChecked(true);
                mCheckT.setChecked(true);
                mCheckW.setChecked(true);
                mCheckTh.setChecked(true);
                mCheckF.setChecked(true);
                mCheckS.setChecked(true);
                mCheckSu.setChecked(true);

            }
        });

        mEditLFromTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                picker = new TimePickerDialog(VendingDetailsActivity.this, new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay >= 12) {
                            format = "PM";
                        } else {
                            format = "AM";
                        }
                        mEditLFromTime.setText(String.format("%02d:%02d", hourOfDay, minutes));
                    }
                }, hour, minute, false);

                picker.show();

            }
        });

        mEditLToTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                picker = new TimePickerDialog(VendingDetailsActivity.this, new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay >= 12) {
                            format = "PM";
                        } else {
                            format = "AM";
                        }
                        mEditLToTime.setText(String.format("%02d:%02d", hourOfDay, minutes));
                    }
                }, hour, minute, false);

                picker.show();

            }
        });

        mEditEFromTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                picker = new TimePickerDialog(VendingDetailsActivity.this, new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay >= 12) {
                            format = "PM";
                        } else {
                            format = "AM";
                        }
                        mEditEFromTime.setText(String.format("%02d:%02d", hourOfDay, minutes));
                    }
                }, hour, minute, false);

                picker.show();

            }
        });

        mEditEToTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                picker = new TimePickerDialog(VendingDetailsActivity.this, new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if (hourOfDay >= 12) {
                            format = "PM";
                        } else {
                            format = "AM";
                        }
                        mEditEToTime.setText(String.format("%02d:%02d", hourOfDay, minutes));
                    }
                }, hour, minute, false);

                picker.show();

            }
        });

        mImgCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(new ContextThemeWrapper(VendingDetailsActivity.this, R.style.DialogTheme),
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

        RGVendor.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioSexButton = (RadioButton) findViewById(checkedId);
                IS_RECOGNIZED_STREET_VENDOR = radioSexButton.getText().toString().trim();
                if (IS_RECOGNIZED_STREET_VENDOR.contains("Yes")) {
                    TextYes.setVisibility(View.VISIBLE);
                    LinearVehical.setVisibility(View.VISIBLE);

                } else {
                    TextYes.setVisibility(View.GONE);
                    LinearVehical.setVisibility(View.GONE);
                }

            }
        });

        RGDocument.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioSexButton = (RadioButton) findViewById(checkedId);
                TEHABZARI_AVAILABLE = radioSexButton.getText().toString().trim();

                if (TEHABZARI_AVAILABLE.contains("Yes")) {

                } else {

                }

            }
        });

        mBtnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (mLinearThree.getVisibility() == View.VISIBLE) {

                    mLinearOne.setVisibility(View.GONE);
                    mLinearThree.setVisibility(View.GONE);
                    mLinearTwo.setVisibility(View.VISIBLE);

                } else if (mLinearTwo.getVisibility() == View.VISIBLE) {

                    mLinearOne.setVisibility(View.VISIBLE);
                    mLinearThree.setVisibility(View.GONE);
                    mLinearTwo.setVisibility(View.GONE);


                }else if (mLinearOne.getVisibility() == View.VISIBLE) {

                    mLinearOne.setVisibility(View.GONE);
                    mLinearThree.setVisibility(View.GONE);
                    mLinearTwo.setVisibility(View.GONE);

                    mLinearHead.setVisibility(View.VISIBLE);


                } else {

                    onBackPressed();

                }

            }
        });

        mSpinnerItems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                TYPE_OF_VENDING = parent.getItemAtPosition(position).toString()
                        .split("\\.")[0].toUpperCase().trim();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mCheckM.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (mCheckM.isChecked()) {
                    M = mCheckM.getText().toString().trim();
                }else {
                    M ="";
                }
            }
        });

        mCheckT.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (mCheckT.isChecked()) {
                    T = mCheckT.getText().toString().trim();
                }else {
                    T ="";
                }
            }
        });

        mCheckW.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (mCheckW.isChecked()) {
                    W = mCheckW.getText().toString().trim();
                }else {
                    W ="";
                }
            }
        });

        mCheckTh.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (mCheckTh.isChecked()) {
                    Th = mCheckTh.getText().toString().trim();
                }else {
                    Th ="";
                }
            }
        });

        mCheckF.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (mCheckF.isChecked()) {
                    F = mCheckF.getText().toString().trim();
                }else {
                    F ="";
                }
            }
        });

        mCheckS.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (mCheckS.isChecked()) {
                    S = mCheckS.getText().toString().trim();
                }else {
                    S ="";
                }
            }
        });

        mCheckSu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (mCheckSu.isChecked()) {
                    Su = mCheckSu.getText().toString().trim();
                }else {
                    Su ="";
                }
            }
        });


        mSpinnerVehical.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                TYPE_OF_STRUCTURE = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mSpinnerChoice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                VENDING_AREA_CHOCE = parent.getItemAtPosition(position).toString();

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


                }else   if (mLinearOne.getVisibility() == View.VISIBLE) {

                    setActiveWeeks();

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

                    mBtnNext.setText("Submit");

                    }
                } else {

                    TextYes.setVisibility(View.GONE);
                    LinearVehical.setVisibility(View.GONE);




//                    NO_DAYS_ACTIVE=M+","+T+","+W+","+Th+","+F+","+S+","+Su;

                    VENDING_SITE = mEditVendingSite.getText().toString().trim();
//                    VENDING_FROM = mEditFromTime.getText().toString().trim();
//                    VENDING_TO = mEditToTime.getText().toString().trim();
                    VENDING_LFROM = mEditLFromTime.getText().toString().trim();
                    VENDING_LTO = mEditLToTime.getText().toString().trim();
                    VENDING_EFROM = mEditEFromTime.getText().toString().trim();
                    VENDING_ETO = mEditEToTime.getText().toString().trim();
                    YRS_OF_VENDING = mEditAge.getText().toString().trim();

                    VENDING_SITE = mEditVendingSite.getText().toString().trim();
//                    VENDING_FROM=mEditFromTime.getText().toString().trim();
//                    VENDING_TO=mEditToTime.getText().toString().trim();
                    YRS_OF_VENDING = mEditAge.getText().toString().trim();

                    STARTING_DATE_VENDING = mEditDob.getText().toString().trim();

                    if (validate3()) {
                        UpdateSurvey();
                    }
                }
            }
        });
    }

    private void setActiveWeeks() {
        if (!M.trim().isEmpty()) {
            if (NO_DAYS_ACTIVE.toString().isEmpty()) {
                NO_DAYS_ACTIVE.append(M);

            } else {
                NO_DAYS_ACTIVE.append("," + M);

            }
        }
        if (!T.trim().isEmpty()) {

            if (NO_DAYS_ACTIVE.toString().isEmpty()) {
                NO_DAYS_ACTIVE.append(T);

            } else {
                NO_DAYS_ACTIVE.append("," + T);

            }
        }

        if (!W.trim().isEmpty()) {

            if (NO_DAYS_ACTIVE.toString().isEmpty()) {
                NO_DAYS_ACTIVE.append(W);

            } else {
                NO_DAYS_ACTIVE.append("," + W);

            }

        }

        if (!Th.trim().isEmpty()) {

            if (NO_DAYS_ACTIVE.toString().isEmpty()) {
                NO_DAYS_ACTIVE.append(Th);

            } else {
                NO_DAYS_ACTIVE.append("," + Th);

            }

        }

        if (!F.trim().isEmpty()) {

            if (NO_DAYS_ACTIVE.toString().isEmpty()) {
                NO_DAYS_ACTIVE.append(F);

            } else {
                NO_DAYS_ACTIVE.append("," + F);

            }

        }

        if (!S.trim().isEmpty()) {

            if (NO_DAYS_ACTIVE.toString().isEmpty()) {
                NO_DAYS_ACTIVE.append(S);

            } else {
                NO_DAYS_ACTIVE.append("," + S);

            }

        }


        if (!Su.trim().isEmpty()) {

            if (NO_DAYS_ACTIVE.toString().isEmpty()) {
                NO_DAYS_ACTIVE.append(Su);

            } else {
                NO_DAYS_ACTIVE.append("," + Su);

            }

        }
    }

    private boolean validate3() {
        if (!ApplicationConstant.isNetworkAvailable(VendingDetailsActivity.this)) {

            ApplicationConstant.displayMessageDialog(VendingDetailsActivity.this, "No Internet Connection", "Please enable internet connection first to proceed");

            return false;
        }
        else if (mEditAge.getText().toString().trim().isEmpty()) {
            mEditAge.setError("Enter No of years");
            mEditAge.requestFocus();
            return false;
        }
        else if (mEditDob.getText().toString().trim().isEmpty()) {
            mEditDob.setError("Enter Date");
            mEditDob.requestFocus();
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
        }
        else if (mEditAge.getText().toString().trim().isEmpty()) {
            mEditAge.setError("Enter No of Years");
            mEditAge.requestFocus();
            return false;
        }

        else if (mSpinnerVehical.getSelectedItem().toString().isEmpty()) {
            ApplicationConstant.displayMessageDialog(VendingDetailsActivity.this, "", "Select Vehicals");
            mSpinnerVehical.requestFocus();
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
        } else if (mEditLFromTime.getText().toString().trim().isEmpty()) {
            mEditLFromTime.setError("Enter From Time");
            mEditLFromTime.requestFocus();
            return false;
        } else if (mEditLToTime.getText().toString().trim().isEmpty()) {
            mEditLToTime.setError("Enter To Time");
            mEditLToTime.requestFocus();
            return false;
        }else if (NO_DAYS_ACTIVE.toString().trim().isEmpty()) {
           ApplicationConstant.displayMessageDialog(VendingDetailsActivity.this,"","Select No of Active Days");
            return false;
        }


        return true;
    }

    private void bindView() {

        btn_all_days = (Button) findViewById(R.id.btn_all_days);
        mLinearMain = (LinearLayout) findViewById(R.id.LinearMain);
        LinearVehical = (LinearLayout) findViewById(R.id.LinearVehical);
        mLinearHead = (LinearLayout) findViewById(R.id.LinearHead);
        mLinearOne = (LinearLayout) findViewById(R.id.LinearOne);
        RGDocument = (RadioGroup) findViewById(R.id.RGDocument);
        RGVendor = (RadioGroup) findViewById(R.id.RGVendor);
        mSpinnerItems = (Spinner) findViewById(R.id.SpinnerItems);
        mEditVendingSite = (EditText) findViewById(R.id.EditVendingSite);
//        mEditFromTime = (EditText) findViewById(R.id.EditFromTime);
//        mEditToTime = (EditText) findViewById(R.id.EditToTime);
        mEditLFromTime = (EditText) findViewById(R.id.EditLFromTime);
        mEditLToTime = (EditText) findViewById(R.id.EditLToTime);
        mEditEFromTime = (EditText) findViewById(R.id.EditEFromTime);
        mEditEToTime = (EditText) findViewById(R.id.EditEToTime);
        mEditAge = (EditText) findViewById(R.id.EditAge);
        mLinearTwo = (LinearLayout) findViewById(R.id.LinearTwo);
//        mRadioY = (RadioButton) findViewById(Integer.parseInt(IS_RECOGNIZED_STREET_VENDOR));
//        mRadioN = (RadioButton) findViewById(Integer.parseInt(IS_RECOGNIZED_STREET_VENDOR));
        mSpinnerVehical = (Spinner) findViewById(R.id.SpinnerVehical);
        mEditDob = (EditText) findViewById(R.id.EditDob);
        TextYes = (TextView) findViewById(R.id.TextYes);
        mImgCalendar = (ImageView) findViewById(R.id.ImgCalendar);
        mLinearThree = (LinearLayout) findViewById(R.id.LinearThree);
//        mRadioDY = (RadioButton) findViewById(Integer.parseInt(TEHABZARI_AVAILABLE));
//        mRadioDN = (RadioButton) findViewById(Integer.parseInt(TEHABZARI_AVAILABLE));
        mSpinnerChoice = (Spinner) findViewById(R.id.SpinnerChoice);
        mBtnNext = (Button) findViewById(R.id.BtnNext);
        mBtnPrevious = (Button) findViewById(R.id.BtnPrevious);

        mCheckM = (CheckBox) findViewById(R.id.CheckM);
        mCheckT = (CheckBox) findViewById(R.id.CheckT);
        mCheckW = (CheckBox) findViewById(R.id.CheckW);
        mCheckTh = (CheckBox) findViewById(R.id.CheckTh);
        mCheckF = (CheckBox) findViewById(R.id.CheckF);
        mCheckS = (CheckBox) findViewById(R.id.CheckS);
        mCheckSu = (CheckBox) findViewById(R.id.CheckSu);

    }

    private void UpdateSurvey() {

        progressDialog = CustomProgressDialog.getDialogue(VendingDetailsActivity.this);
        progressDialog.show();

        String UNiq_Id = PrefUtils.getFromPrefs(VendingDetailsActivity.this, ApplicationConstant.URI_NO_, "");

        String CORPORATION =   PrefUtils.getFromPrefs(VendingDetailsActivity.this,ApplicationConstant.CORPORATION,"");
        String ZONE =  PrefUtils.getFromPrefs(VendingDetailsActivity.this,ApplicationConstant.ZONE,"");
        String WARD =  PrefUtils.getFromPrefs(VendingDetailsActivity.this,ApplicationConstant.WARD,"");




        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + PrefUtils.getFromPrefs(VendingDetailsActivity.this, ApplicationConstant.USERDETAILS.API_KEY, ""));

        ApiInterface apiservice = ApiService.getApiClient().create(ApiInterface.class);
        Call<UpdateSurveyResponse> call = apiservice.getUpdateSurvey(headers,
                UNiq_Id,
                CORPORATION,
                ZONE,
                WARD,
                TYPE_OF_VENDING,
                VENDING_SITE,
                VENDING_LFROM,
                VENDING_LTO,
                VENDING_EFROM,
                VENDING_ETO,
                NO_DAYS_ACTIVE.toString().trim(),
                YRS_OF_VENDING,
                IS_RECOGNIZED_STREET_VENDOR,
                TYPE_OF_STRUCTURE,
                STARTING_DATE_VENDING,
                TEHABZARI_AVAILABLE,
                VENDING_AREA_CHOCE

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
                                startActivity(new Intent(VendingDetailsActivity.this, BankingDetailsActivity.class));

                            }
                        });

                        AlertDialog alertDialog = builder.create();
                        alertDialog.setCancelable(false);
                        alertDialog.setCanceledOnTouchOutside(false);
                        alertDialog.show();

//                        ApplicationConstant.displayToastMessage(VendingDetailsActivity.this,
//                                "Vending Details saved successfully");


                    } else {

                        ApplicationConstant.displayMessageDialog(VendingDetailsActivity.this,
                                "Response",
                                response.body().getMessage());
                    }

                } else {

                    try {
                        ApplicationConstant.displayMessageDialog(VendingDetailsActivity.this,
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
                ApplicationConstant.displayMessageDialog(VendingDetailsActivity.this, "Response", getString(R.string.net_speed_problem));

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
