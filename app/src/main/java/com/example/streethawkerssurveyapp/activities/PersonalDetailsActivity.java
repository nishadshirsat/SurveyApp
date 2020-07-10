package com.example.streethawkerssurveyapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.streethawkerssurveyapp.R;

public class PersonalDetailsActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindView();

        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if (mLinearOne.getVisibility() == View.GONE) {
//                    mLinearOne.setVisibility(View.VISIBLE);
//                    mLinearTwo.setVisibility(View.GONE);
//                    mLinearThree.setVisibility(View.GONE);
//                    mLinearFour.setVisibility(View.GONE);
//                    mLinearFive.setVisibility(View.GONE);
//                } else if (mLinearTwo.getVisibility() == View.GONE) {
//
//                    mLinearTwo.setVisibility(View.VISIBLE);
//                    mLinearOne.setVisibility(View.GONE);
//                    mLinearThree.setVisibility(View.GONE);
//                    mLinearFour.setVisibility(View.GONE);
//                    mLinearFive.setVisibility(View.GONE);
//
//                } else if (mLinearThree.getVisibility() == View.GONE) {
//
//                    mLinearThree.setVisibility(View.VISIBLE);
//                    mLinearOne.setVisibility(View.GONE);
//                    mLinearTwo.setVisibility(View.GONE);
//                    mLinearFour.setVisibility(View.GONE);
//                    mLinearFive.setVisibility(View.GONE);
//
//                } else if (mLinearFour.getVisibility() == View.GONE) {
//
//                    mLinearFour.setVisibility(View.VISIBLE);
//                    mLinearOne.setVisibility(View.GONE);
//                    mLinearThree.setVisibility(View.GONE);
//                    mLinearTwo.setVisibility(View.GONE);
//                    mLinearFive.setVisibility(View.GONE);
//
//                } else {
//
//                    mLinearFive.setVisibility(View.VISIBLE);
//                    mLinearOne.setVisibility(View.GONE);
//                    mLinearThree.setVisibility(View.GONE);
//                    mLinearTwo.setVisibility(View.GONE);
//                    mLinearFour.setVisibility(View.GONE);
//
//                    startActivity(new Intent(PersonalDetailsActivity.this, VendorsFamDetailsActivity.class));
//
//                }

                startActivity(new Intent(PersonalDetailsActivity.this, VendorsFamDetailsActivity.class));


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
}