package com.example.streethawkerssurveyapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.streethawkerssurveyapp.Helper.LocaleHelper;
import com.example.streethawkerssurveyapp.R;
import com.example.streethawkerssurveyapp.local_storage_pack.activities.LocalSurveyListActivity;
import com.example.streethawkerssurveyapp.officer.activities.SupervisorListActivity;
import com.example.streethawkerssurveyapp.pending_survey.activities.PendingSurveyActivity;
import com.example.streethawkerssurveyapp.services_pack.ApplicationConstant;
import com.example.streethawkerssurveyapp.supervisor.activities.SurveyorListActivity;
import com.example.streethawkerssurveyapp.supervisor.activities.ViewSurveySupervisorActivity;
import com.example.streethawkerssurveyapp.suspended_survey.avtivities.SuspendedSurveyActivity;
import com.example.streethawkerssurveyapp.utils.GetLocation;
import com.example.streethawkerssurveyapp.utils.PrefUtils;
import com.example.streethawkerssurveyapp.view_survey.activities.ViewSurveyActivity;

import java.util.Locale;

import io.paperdb.Paper;

public class DashboardActivity extends MainActivity {

    private LinearLayout mProfile_layout;
    private RelativeLayout mRelative1;
    private androidx.cardview.widget.CardView mCard_profile;
    private LinearLayout mRelative2;
    private TextView mProfile_name;
    private TextView mProfile_email;
    private TextView mCorporationName;
    private TextView mZoneName;
    private TextView mTvcName;
    private TextView mAreaName;
    private TextView mWardName;
    private TextView mTextUsertype;
    private TextView mTextUsername;
    private LinearLayout mLinear_Surveyor;
    private LinearLayout mLinear_newSurvey;
    private TextView mTextSurvey,TextPending,TextView,Textsuspended,TextLocal;
    private LinearLayout mLinear_pending;
    private ImageView mImage_dth;
    private LinearLayout mLinear_view;
    private ImageView mImage_postpaid;
    private LinearLayout mLinear_suspended;
    private LinearLayout mLinear_local_data;
    private LinearLayout mLinear_Supervisor;
    private LinearLayout mLinear_surveyorList;
    private LinearLayout mLinear_view_Supervisor;
    Context context;

    public GetLocation getLocation;

    boolean doubleBackToExitPressedOnce = false;

    String USERTYPE = "";
    boolean lang_selected;
    Resources resources;
    private String mLanguageCode = "hi";
    private String mLanguageCode1 = "en";


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase,"hi"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
//        setAppLocale("hi");

        if (getLocation == null) {
            getLocation = new GetLocation(DashboardActivity.this);
        }

        bindVieW();

//        Paper.init(this);
//
//        String language = Paper.book().read("language");
//        if(language==null)
//        Paper.book().write("language","en");
//
//        updateView((String)Paper.book().read(language));

        setData();

        USERTYPE = PrefUtils.getFromPrefs(DashboardActivity.this, ApplicationConstant.USERDETAILS.UserType, "");

        if (USERTYPE.equals("Surveyor")) {

            mLinear_Surveyor.setVisibility(View.VISIBLE);
            mLinear_Supervisor.setVisibility(View.GONE);

        } else if (USERTYPE.equals("Supervisor")) {

            mLinear_Surveyor.setVisibility(View.GONE);
            mLinear_Supervisor.setVisibility(View.VISIBLE);
        }


        mLinear_newSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, StartSurveyModeActivity.class));
            }
        });

        mLinear_view_Supervisor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, ViewSurveySupervisorActivity.class));
            }
        });

        mLinear_surveyorList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, SurveyorListActivity.class));
            }
        });

        mLinear_pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(DashboardActivity.this, PendingSurveyActivity.class));

            }
        });

        mLinear_local_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(DashboardActivity.this, LocalSurveyListActivity.class));

            }
        });

        mLinear_suspended.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                ApplicationConstant.DisplayMessageDialog(DashboardActivity.this,"","This Feature Coming Soon...");
                startActivity(new Intent(DashboardActivity.this, SuspendedSurveyActivity.class));

            }
        });

        mLinear_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                ApplicationConstant.DisplayMessageDialog(DashboardActivity.this,"","This Feature Coming Soon...");

                startActivity(new Intent(DashboardActivity.this, ViewSurveyActivity.class));

            }
        });


    }

//    private void updateView(String lang) {
//        Context context=LocaleHelper.setLocale(this,lang);
//        Resources resources=context.getResources();
//    }


    private void setData() {

        String Name = PrefUtils.getFromPrefs(DashboardActivity.this, ApplicationConstant.USERDETAILS.Name, "");
        String Email = PrefUtils.getFromPrefs(DashboardActivity.this, ApplicationConstant.USERDETAILS.Email, "");
        String AREA = PrefUtils.getFromPrefs(DashboardActivity.this, ApplicationConstant.AREA, "");
        String CORP = PrefUtils.getFromPrefs(DashboardActivity.this, ApplicationConstant.CORPORATION, "");
        String ZONE = PrefUtils.getFromPrefs(DashboardActivity.this, ApplicationConstant.ZONE, "");
        String WARD = PrefUtils.getFromPrefs(DashboardActivity.this, ApplicationConstant.WARD, "");
        String TVC = PrefUtils.getFromPrefs(DashboardActivity.this, ApplicationConstant.TVC, "");
        String Role = PrefUtils.getFromPrefs(DashboardActivity.this, ApplicationConstant.USERDETAILS.UserType, "");

        mProfile_name.setText(Name);
        mAreaName.setText("AREA : "+AREA);
        mCorporationName.setText("Corporation : "+CORP);
        mZoneName.setText("Zone : "+ZONE);
        mWardName.setText("Ward : "+WARD);
        mTvcName.setText("TVC : "+TVC);
        mTextUsername.setText(Role);


    }

    private void bindVieW() {

        mProfile_layout = (LinearLayout) findViewById(R.id.profile_layout);
        mRelative1 = (RelativeLayout) findViewById(R.id.relative1);
        mCard_profile = (androidx.cardview.widget.CardView) findViewById(R.id.card_profile);
        mRelative2 = (LinearLayout) findViewById(R.id.relative2);
        mProfile_name = (TextView) findViewById(R.id.profile_name);
        mProfile_email = (TextView) findViewById(R.id.profile_email);
        mCorporationName = (TextView) findViewById(R.id.CorporationName);
        mZoneName = (TextView) findViewById(R.id.ZoneName);
        mTvcName = (TextView) findViewById(R.id.TvcName);
        mAreaName = (TextView) findViewById(R.id.AreaName);
        mWardName = (TextView) findViewById(R.id.WardName);
        mTextUsertype = (TextView) findViewById(R.id.TextUsertype);
        mTextUsername = (TextView) findViewById(R.id.TextUsername);
        mLinear_Surveyor = (LinearLayout) findViewById(R.id.Linear_Surveyor);
        mLinear_newSurvey = (LinearLayout) findViewById(R.id.linear_newSurvey);
        mTextSurvey = (TextView) findViewById(R.id.TextSurvey);
        TextPending = (TextView) findViewById(R.id.TextPending);
        TextView = (TextView) findViewById(R.id.TextView);
        TextLocal = (TextView) findViewById(R.id.TextLocal);
        Textsuspended = (TextView) findViewById(R.id.TextSuspended);
        mLinear_pending = (LinearLayout) findViewById(R.id.linear_pending);
        mImage_dth = (ImageView) findViewById(R.id.image_dth);
        mLinear_view = (LinearLayout) findViewById(R.id.linear_view);
        mImage_postpaid = (ImageView) findViewById(R.id.image_postpaid);
        mLinear_suspended = (LinearLayout) findViewById(R.id.linear_suspended);
        mLinear_local_data = (LinearLayout) findViewById(R.id.linear_local_data);
        mLinear_Supervisor = (LinearLayout) findViewById(R.id.Linear_Supervisor);
        mLinear_surveyorList = (LinearLayout) findViewById(R.id.linear_surveyorList);
        mLinear_view_Supervisor = (LinearLayout) findViewById(R.id.linear_view_Supervisor);


    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startMain);
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.bookmark_menu:
                PrefUtils.saveToPrefs(DashboardActivity.this, ApplicationConstant.USERDETAILS.FlagRemember, "");
                PrefUtils.saveToPrefs(DashboardActivity.this, ApplicationConstant.USERDETAILS.UserName, "");
                PrefUtils.saveToPrefs(DashboardActivity.this, ApplicationConstant.USERDETAILS.UserPassword, "");
                startActivity(new Intent(DashboardActivity.this, LoginActivity.class));
                finish();
                return true;

            case R.id.language_menu:

//                setAppLocale();



                View view1 = getLayoutInflater().inflate(R.layout.layout_select_language, null);
                RadioGroup RadioLanguages=view1.findViewById(R.id.RadioLanguages);
                RadioButton RadioEnglish=view1.findViewById(R.id.RadioEnglish);
                RadioButton RadioHindi=view1.findViewById(R.id.RadioHindi);
                Button BtnDone=view1.findViewById(R.id.BtnDone);

                AlertDialog.Builder builder = new AlertDialog.Builder(DashboardActivity.this);

                final AlertDialog alertDialog = builder.create();

                alertDialog.setView(view1);
                alertDialog.getWindow().setSoftInputMode(
                        WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));



                RadioLanguages.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if(checkedId==R.id.RadioEnglish){
                            LocaleHelper.setLocale(DashboardActivity.this, mLanguageCode1);

                            //It is required to recreate the activity to reflect the change in UI.
                            recreate();
                            alertDialog.dismiss();
                        }else{
                            LocaleHelper.setLocale(DashboardActivity.this, mLanguageCode);

                            //It is required to recreate the activity to reflect the change in UI.
                            recreate();
                            alertDialog.dismiss();
                        }
                    }
                });

                BtnDone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });


                alertDialog.show();


                return true;
//
//                final String[] Language = {"ENGLISH", "हिन्दी"};
//                final int checkedItem;
//                if(lang_selected)
//                {
//                    checkedItem=1;
//                }else
//                {
//                    checkedItem=0;
//                }
//                final AlertDialog.Builder builder = new AlertDialog.Builder(DashboardActivity.this);
//                builder.setTitle("Select a Language...")
//                        .setSingleChoiceItems(Language, checkedItem, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                Toast.makeText(DashboardActivity.this,""+which,Toast.LENGTH_SHORT).show();
////                                language_dialog.setText(Language[which]);
//                                lang_selected= Language[which].equals("ENGLISH");
//                                //if user select prefered language as English then
//                                if(Language[which].equals("ENGLISH"))
//                                {
////
//                                    LocaleHelper.setLocale(DashboardActivity.this, mLanguageCode1);
//
//                                    //It is required to recreate the activity to reflect the change in UI.
//                                    recreate();
//                                }
//                                //if user select prefered language as Hindi then
//                                if(Language[which].equals("हिन्दी"))
//                                {
////
//                                    LocaleHelper.setLocale(DashboardActivity.this, mLanguageCode);
//
//                                    //It is required to recreate the activity to reflect the change in UI.
//                                    recreate();
//                                }
//                            }
//                        })
//                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//                            }
//                        });
//                builder.create().show();

        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onResume() {
        super.onResume();

        if (!ApplicationConstant.isNetworkAvailable(DashboardActivity.this)) {

            ApplicationConstant.ISLOCALDB = true;

            ApplicationConstant.displayToastMessage(DashboardActivity.this,  "No Internet Connection! Storing survey in local Database now.");


        }else {

            ApplicationConstant.ISLOCALDB = false;


        }
    }
}
