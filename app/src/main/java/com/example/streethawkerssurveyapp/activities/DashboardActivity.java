package com.example.streethawkerssurveyapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.streethawkerssurveyapp.R;
import com.example.streethawkerssurveyapp.services_pack.ApplicationConstant;

public class DashboardActivity extends AppCompatActivity {

    private LinearLayout mProfile_layout;
    private RelativeLayout mRelative1;
    private androidx.cardview.widget.CardView mCard_profile;
    private RelativeLayout mRelative2;
    private de.hdodenhof.circleimageview.CircleImageView mProfile_image;
    private TextView mProfile_name;
    private TextView mProfile_number;
    private TextView mAreaName;
    private TextView mTextUsertype;
    private TextView mTextUsername;
    private LinearLayout mLinear_newSurvey;
    private LinearLayout mLinear_pending;
    private ImageView mImage_dth;
    private LinearLayout mLinear_view;
    private ImageView mImage_postpaid;
    private LinearLayout mLinear_suspended;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        
        bindVieW();

        mLinear_newSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this,ScanActivity.class));
            }
        });

        mLinear_pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ApplicationConstant.DisplayMessageDialog(DashboardActivity.this,"","This Feature Coming Soon...");

            }
        });

        mLinear_suspended.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ApplicationConstant.DisplayMessageDialog(DashboardActivity.this,"","This Feature Coming Soon...");

            }
        });

        mLinear_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ApplicationConstant.DisplayMessageDialog(DashboardActivity.this,"","This Feature Coming Soon...");

            }
        });


    }

    private void bindVieW() {

        mProfile_layout = (LinearLayout) findViewById(R.id.profile_layout);
        mRelative1 = (RelativeLayout) findViewById(R.id.relative1);
        mCard_profile = (androidx.cardview.widget.CardView) findViewById(R.id.card_profile);
        mProfile_name = (TextView) findViewById(R.id.profile_name);
        mProfile_number = (TextView) findViewById(R.id.profile_number);
        mAreaName = (TextView) findViewById(R.id.AreaName);
        mTextUsertype = (TextView) findViewById(R.id.TextUsertype);
        mTextUsername = (TextView) findViewById(R.id.TextUsername);
        mLinear_newSurvey = (LinearLayout) findViewById(R.id.linear_newSurvey);
        mLinear_pending = (LinearLayout) findViewById(R.id.linear_pending);
        mImage_dth = (ImageView) findViewById(R.id.image_dth);
        mLinear_view = (LinearLayout) findViewById(R.id.linear_view);
        mImage_postpaid = (ImageView) findViewById(R.id.image_postpaid);
        mLinear_suspended = (LinearLayout) findViewById(R.id.linear_suspended);

    }
}
