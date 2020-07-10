package com.example.streethawkerssurveyapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.streethawkerssurveyapp.R;
import com.example.streethawkerssurveyapp.services_pack.ApplicationConstant;
import com.scanlibrary.ScanActivity;
import com.scanlibrary.ScanConstants;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class DocumentScanActivity extends AppCompatActivity {

    private LinearLayout mLinearMain;
    private LinearLayout mLinearOne;
    private TextView mTextAddhar;
    private ImageView mImgAadharScan;
    private TextView mTextDL;
    private ImageView mImgDLScan;
    private TextView mTextVoterId;
    private ImageView mImgVoterID;
    private TextView mTextBankPassbook;
    private ImageView mImgBankPassbook;
    private LinearLayout mLinearTwo;
    private TextView mTextFestivalReceipts;
    private ImageView mImgFestivalReceipts;
    private TextView mTextTokens;
    private ImageView mImgTokens;
    private TextView mTextChallan;
    private ImageView mImgChallan;
    private TextView mTextTRChallan;
    private ImageView mImgTRChallan;
    private LinearLayout mLinearThree;
    private TextView mTextPoliceChalan;
    private ImageView mImgPoliceChalan;
    private TextView mTextReceipt;
    private ImageView mImgReceipt;
    private TextView mTextCertificate;
    private ImageView mImgCertificate;
    private TextView mTextAttestedDoc;
    private ImageView mAttestedDoc;
    private LinearLayout mLinearFour;
    private ImageView mImgTehbaziDoc;
    private ImageView mImgSignature;
    private Button mBtnNext;
    private Button mBtnPrevious;

    private String CONTROL="";

    private static final int REQUEST_CODE = 99;
    private String AADHAR_PATH ="";
    private String DRIVING_LICENCE_PATH="";
    private String VOTER_ID_PATH="";
    private String BANK_PASSBOOK_PATH="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_scan);

        bindView();

        mBtnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DocumentScanActivity.this,VendingDetailsActivity.class));
            }
        });

        mImgAadharScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CONTROL = ApplicationConstant.SurveyId+"_"+"Aadhar";
                startScan();
            }
        });



        mImgDLScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CONTROL = ApplicationConstant.SurveyId+"_"+"DrivingLicence";
                startScan();
            }
        });

        mImgVoterID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CONTROL = ApplicationConstant.SurveyId+"_"+"VoterId";
                startScan();
            }
        });

        mImgBankPassbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CONTROL = ApplicationConstant.SurveyId+"_"+"BankPassbook";
                startScan();
            }
        });

        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mLinearOne.getVisibility() == View.VISIBLE) {
                    mLinearOne.setVisibility(View.GONE);
                    mLinearTwo.setVisibility(View.VISIBLE);
                    mLinearThree.setVisibility(View.GONE);
                    mLinearFour.setVisibility(View.GONE);
                } else if (mLinearTwo.getVisibility() == View.VISIBLE) {

                    mLinearTwo.setVisibility(View.GONE);
                    mLinearOne.setVisibility(View.GONE);
                    mLinearThree.setVisibility(View.VISIBLE);
                    mLinearFour.setVisibility(View.GONE);

                } else if (mLinearThree.getVisibility() == View.VISIBLE) {

                    mLinearThree.setVisibility(View.GONE);
                    mLinearOne.setVisibility(View.GONE);
                    mLinearTwo.setVisibility(View.GONE);
                    mLinearFour.setVisibility(View.VISIBLE);

                }
//                else if (mLinearFour.getVisibility() == View.VISIBLE) {
//
//                    mLinearFour.setVisibility(View.GONE);
//                    mLinearOne.setVisibility(View.GONE);
//                    mLinearThree.setVisibility(View.GONE);
//                    mLinearTwo.setVisibility(View.GONE);
//                    mLinearFive.setVisibility(View.VISIBLE);
//
//                }

                else {

//                    mLinearFive.setVisibility(View.VISIBLE);
//                    mLinearOne.setVisibility(View.GONE);
//                    mLinearThree.setVisibility(View.GONE);
//                    mLinearTwo.setVisibility(View.GONE);
//                    mLinearFour.setVisibility(View.GONE);


//                    NAME_VENDOR = mEditFName.getText().toString().trim()+" "
//                            + mEditMName.getText().toString().trim()+" "
//                            +  mEditLName.getText().toString().trim();
//
//                    AddSurvey();

//                    startActivity(new Intent(PersonalDetailsActivity.this, VendorsFamDetailsActivity.class));

                }


            }
        });

    }

    private void bindView() {

        mLinearMain = (LinearLayout) findViewById(R.id.LinearMain);
        mLinearOne = (LinearLayout) findViewById(R.id.LinearOne);
        mTextAddhar = (TextView) findViewById(R.id.TextAddhar);
        mImgAadharScan = (ImageView) findViewById(R.id.ImgAadharScan);
        mTextDL = (TextView) findViewById(R.id.TextDL);
        mImgDLScan = (ImageView) findViewById(R.id.ImgDLScan);
        mTextVoterId = (TextView) findViewById(R.id.TextVoterId);
        mImgVoterID = (ImageView) findViewById(R.id.ImgVoterID);
        mTextBankPassbook = (TextView) findViewById(R.id.TextBankPassbook);
        mImgBankPassbook = (ImageView) findViewById(R.id.ImgBankPassbook);
        mLinearTwo = (LinearLayout) findViewById(R.id.LinearTwo);
        mTextFestivalReceipts = (TextView) findViewById(R.id.TextFestivalReceipts);
        mImgFestivalReceipts = (ImageView) findViewById(R.id.ImgFestivalReceipts);
        mTextTokens = (TextView) findViewById(R.id.TextTokens);
        mImgTokens = (ImageView) findViewById(R.id.ImgTokens);
        mTextChallan = (TextView) findViewById(R.id.TextChallan);
        mImgChallan = (ImageView) findViewById(R.id.ImgChallan);
        mTextTRChallan = (TextView) findViewById(R.id.TextTRChallan);
        mImgTRChallan = (ImageView) findViewById(R.id.ImgTRChallan);
        mLinearThree = (LinearLayout) findViewById(R.id.LinearThree);
        mTextPoliceChalan = (TextView) findViewById(R.id.TextPoliceChalan);
        mImgPoliceChalan = (ImageView) findViewById(R.id.ImgPoliceChalan);
        mTextReceipt = (TextView) findViewById(R.id.TextReceipt);
        mImgReceipt = (ImageView) findViewById(R.id.ImgReceipt);
        mTextCertificate = (TextView) findViewById(R.id.TextCertificate);
        mImgCertificate = (ImageView) findViewById(R.id.ImgCertificate);
        mTextAttestedDoc = (TextView) findViewById(R.id.TextAttestedDoc);
        mAttestedDoc = (ImageView) findViewById(R.id.AttestedDoc);
        mLinearFour = (LinearLayout) findViewById(R.id.LinearFour);
        mImgTehbaziDoc = (ImageView) findViewById(R.id.ImgTehbaziDoc);
        mImgSignature = (ImageView) findViewById(R.id.ImgSignature);
        mBtnNext = (Button) findViewById(R.id.BtnNext);
        mBtnPrevious = (Button) findViewById(R.id.BtnPrevious);
    }

    protected void startScan() {
        Intent intent = new Intent(this, ScanActivity.class);
        intent.putExtra(ScanConstants.OPEN_INTENT_PREFERENCE, ScanConstants.OPEN_CAMERA);
        startActivityForResult(intent, REQUEST_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri uri_image = data.getExtras().getParcelable(ScanConstants.SCANNED_RESULT);

            if (CONTROL.trim().equals(ApplicationConstant.SurveyId+"_"+"Aadhar")){


                File photoFile = null;
                try {
                   String photoPath = ApplicationConstant.createImageFile(ApplicationConstant.SurveyId+"_"+"Aadhar.png", "Documents", DocumentScanActivity.this);
                    photoFile = new File(photoPath);
                    AADHAR_PATH = photoPath;

                    setBitmap(mImgAadharScan,uri_image,photoFile);

                } catch (IOException ex) {
                    // Error occurred while creating the File
                }


            }else if (CONTROL.trim().equals(ApplicationConstant.SurveyId+"_"+"DrivingLicence")){

                File photoFile = null;
                try {
                    String photoPath = ApplicationConstant.createImageFile(ApplicationConstant.SurveyId+"_"+"DrivingLicence.png", "Documents", DocumentScanActivity.this);
                    photoFile = new File(photoPath);
                    DRIVING_LICENCE_PATH = photoPath;

                    setBitmap(mImgDLScan,uri_image,photoFile);

                } catch (IOException ex) {
                    // Error occurred while creating the File
                }

            }else if (CONTROL.trim().equals(ApplicationConstant.SurveyId+"_"+"VoterId")){

                File photoFile = null;
                try {
                    String photoPath = ApplicationConstant.createImageFile(ApplicationConstant.SurveyId+"_"+"VoterId.png", "Documents", DocumentScanActivity.this);
                    photoFile = new File(photoPath);
                    VOTER_ID_PATH = photoPath;

                    setBitmap(mImgVoterID,uri_image,photoFile);

                } catch (IOException ex) {
                    // Error occurred while creating the File
                }

            }else if (CONTROL.trim().equals(ApplicationConstant.SurveyId+"_"+"BankPassbook")){
                File photoFile = null;
                try {
                    String photoPath = ApplicationConstant.createImageFile(ApplicationConstant.SurveyId+"_"+"BankPassbook.png", "Documents", DocumentScanActivity.this);
                    photoFile = new File(photoPath);
                    BANK_PASSBOOK_PATH = photoPath;

                    setBitmap(mImgBankPassbook,uri_image,photoFile);

                } catch (IOException ex) {
                    // Error occurred while creating the File
                }
            }

        }
    }

    public void setBitmap(ImageView imageDisplay, Uri uri, File photoFile){
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            getContentResolver().delete(uri, null, null);
            imageDisplay.setImageBitmap(bitmap);

            writeBitmap(bitmap,photoFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeBitmap(Bitmap bitmap, File filename){
        try (FileOutputStream out = new FileOutputStream(filename)) {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out); // bmp is your Bitmap instance
//            out.close();
            // PNG is a lossless format, the compression factor (100) is ignored
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
