package com.example.streethawkerssurveyapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.streethawkerssurveyapp.R;
import com.example.streethawkerssurveyapp.local_storage_pack.activities.LocalSurveyListActivity;
import com.example.streethawkerssurveyapp.officer.activities.SupervisorListActivity;
import com.example.streethawkerssurveyapp.pending_survey.activities.PendingSurveyActivity;
import com.example.streethawkerssurveyapp.services_pack.ApplicationConstant;
import com.example.streethawkerssurveyapp.supervisor.activities.SurveyorListActivity;
import com.example.streethawkerssurveyapp.suspended_survey.avtivities.SuspendedSurveyActivity;
import com.example.streethawkerssurveyapp.utils.GetLocation;
import com.example.streethawkerssurveyapp.utils.PrefUtils;
import com.example.streethawkerssurveyapp.view_survey.activities.ViewSurveyActivity;

import java.util.Locale;

public class DashboardActivity extends MainActivity {

    private LinearLayout mProfile_layout;
    private RelativeLayout mRelative1;
    private androidx.cardview.widget.CardView mCard_profile;
    private RelativeLayout mRelative2;
    private de.hdodenhof.circleimageview.CircleImageView mProfile_image;
    private TextView mProfile_name;
    private TextView TextSurvey;
    private TextView mProfile_email;
    private TextView mAreaName;
    private TextView CorporationName,ZoneName,TvcName,WardName;
    private TextView mTextUsertype;
    private TextView mTextUsername;
    private LinearLayout mLinear_newSurvey;
    private LinearLayout mLinear_pending;
    private ImageView mImage_dth;
    private LinearLayout mLinear_view;
    private ImageView mImage_postpaid;
    private LinearLayout mLinear_suspended;
    private LinearLayout mLinear_local_data;
    public GetLocation getLocation;

    boolean doubleBackToExitPressedOnce = false;

    String USERTYPE = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        if (getLocation == null) {
            getLocation = new GetLocation(DashboardActivity.this);
        }

        bindVieW();

        setData();

        USERTYPE = PrefUtils.getFromPrefs(DashboardActivity.this, ApplicationConstant.USERDETAILS.UserType, "");

        if (USERTYPE.equals("Surveyor")) {
            TextSurvey.setText("New Survey");
            mLinear_newSurvey.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(DashboardActivity.this, StartSurveyModeActivity.class));
                }
            });
        } else if (USERTYPE.equals("Supervisor")) {
            TextSurvey.setText("Surveyor List");
            mLinear_newSurvey.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(DashboardActivity.this, SurveyorListActivity.class));
                }
            });
        } else {
            TextSurvey.setText("Supervisor List");
            mLinear_newSurvey.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(DashboardActivity.this, SupervisorListActivity.class));
                }
            });
        }


//        mLinear_newSurvey.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(DashboardActivity.this, StartSurveyModeActivity.class));
//            }
//        });

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
        CorporationName.setText("Corporation : "+CORP);
        ZoneName.setText("Zone : "+ZONE);
        WardName.setText("Ward : "+WARD);
        TvcName.setText("TVC : "+TVC);
        mTextUsername.setText(Role);


    }

    private void bindVieW() {

        mLinear_local_data = (LinearLayout) findViewById(R.id.linear_local_data);
        mProfile_layout = (LinearLayout) findViewById(R.id.profile_layout);
        mRelative1 = (RelativeLayout) findViewById(R.id.relative1);
        mCard_profile = (androidx.cardview.widget.CardView) findViewById(R.id.card_profile);
        mProfile_name = (TextView) findViewById(R.id.profile_name);
        mProfile_email = (TextView) findViewById(R.id.profile_email);
        mAreaName = (TextView) findViewById(R.id.AreaName);
        mTextUsertype = (TextView) findViewById(R.id.TextUsertype);
        mTextUsername = (TextView) findViewById(R.id.TextUsername);
        TextSurvey = (TextView) findViewById(R.id.TextSurvey);
        mAreaName = (TextView) findViewById(R.id.AreaName);
        CorporationName = (TextView) findViewById(R.id.CorporationName);
        TvcName = (TextView) findViewById(R.id.TvcName);
        ZoneName = (TextView) findViewById(R.id.ZoneName);
        WardName = (TextView) findViewById(R.id.WardName);
        mLinear_newSurvey = (LinearLayout) findViewById(R.id.linear_newSurvey);
        mLinear_pending = (LinearLayout) findViewById(R.id.linear_pending);
        mImage_dth = (ImageView) findViewById(R.id.image_dth);
        mLinear_view = (LinearLayout) findViewById(R.id.linear_view);
        mImage_postpaid = (ImageView) findViewById(R.id.image_postpaid);
        mLinear_suspended = (LinearLayout) findViewById(R.id.linear_suspended);

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

                View view1 = getLayoutInflater().inflate(R.layout.layout_select_language, null);
                RadioButton RadioEnglish=view1.findViewById(R.id.RadioEnglish);
                RadioButton RadioHindi=view1.findViewById(R.id.RadioHindi);
                Button BtnDone=view1.findViewById(R.id.BtnDone);

                AlertDialog.Builder builder = new AlertDialog.Builder(DashboardActivity.this);

                final AlertDialog alertDialog = builder.create();

                alertDialog.setView(view1);
                alertDialog.getWindow().setSoftInputMode(
                        WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

//                boolean checked = ((RadioButton) view1).isChecked();

                BtnDone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

//                        if(RadioHindi.isChecked()){
//                            setApplicationLocale("hi");
//                        }else{
//                            setApplicationLocale("en");
//                        }
                        alertDialog.dismiss();
                    }
                });


                alertDialog.show();


                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void setApplicationLocale(String hi) {

        Resources resources = getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLocale(new Locale(hi.toLowerCase()));
        } else {
            config.locale = new Locale(hi.toLowerCase());
        }
        resources.updateConfiguration(config, dm);

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
