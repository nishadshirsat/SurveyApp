package com.example.streethawkerssurveyapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.example.streethawkerssurveyapp.R;

public class VendorsFamDetailsActivity extends AppCompatActivity {

    private LinearLayout mLinearMain;
    private EditText mEditFamName;
    private EditText mEditFamRelation;
    private EditText mEditFamAge;
    private EditText mEditFamAadhar;
    private EditText mEditPlot;
    private EditText mEditHouseSize;
    private EditText mEditArea;
    private EditText mEditKuccha;
    private EditText mEditRent;
    private RadioButton mRadioY;
    private RadioButton mRadioN;
    private EditText mEditName;
    private EditText mEditRelation;
    private EditText mEditAge;
    private EditText mEditAadhar;
    private Button mBtnNext;
    private Button mBtnPrevious;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendors_fam_details);

        bindView();

        mBtnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VendorsFamDetailsActivity.this,PersonalDetailsActivity.class));
            }
        });
        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VendorsFamDetailsActivity.this,VendingDetailsActivity.class));
            }
        });
    }

    private void bindView() {
        mLinearMain = (LinearLayout) findViewById(R.id.LinearMain);
        mEditFamName = (EditText) findViewById(R.id.EditFamName);
        mEditFamRelation = (EditText) findViewById(R.id.EditFamRelation);
        mEditFamAge = (EditText) findViewById(R.id.EditFamAge);
        mEditFamAadhar = (EditText) findViewById(R.id.EditFamAadhar);
        mEditPlot = (EditText) findViewById(R.id.EditPlot);
        mEditHouseSize = (EditText) findViewById(R.id.EditHouseSize);
        mEditArea = (EditText) findViewById(R.id.EditArea);
        mEditKuccha = (EditText) findViewById(R.id.EditKuccha);
        mEditRent = (EditText) findViewById(R.id.EditRent);
        mRadioY = (RadioButton) findViewById(R.id.RadioY);
        mRadioN = (RadioButton) findViewById(R.id.RadioN);
        mEditName = (EditText) findViewById(R.id.EditName);
        mEditRelation = (EditText) findViewById(R.id.EditRelation);
        mEditAge = (EditText) findViewById(R.id.EditAge);
        mEditAadhar = (EditText) findViewById(R.id.EditAadhar);
        mBtnNext = (Button) findViewById(R.id.BtnNext);
        mBtnPrevious = (Button) findViewById(R.id.BtnPrevious);
    }
}
