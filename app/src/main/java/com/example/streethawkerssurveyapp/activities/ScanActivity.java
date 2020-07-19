package com.example.streethawkerssurveyapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.streethawkerssurveyapp.R;

public class ScanActivity extends AppCompatActivity {

    private androidx.cardview.widget.CardView mCardStartSurvey;
    private androidx.cardview.widget.CardView mCardScanCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Start Survey");

        bindViews();

        mCardStartSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ScanActivity.this,CorporationZoneActivity.class));

            }
        });

        mCardScanCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


    }

    private void bindViews() {

        mCardStartSurvey = (androidx.cardview.widget.CardView) findViewById(R.id.CardStartSurvey);
        mCardScanCode = (androidx.cardview.widget.CardView) findViewById(R.id.CardScanCode);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
