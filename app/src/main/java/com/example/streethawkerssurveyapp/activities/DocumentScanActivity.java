package com.example.streethawkerssurveyapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.streethawkerssurveyapp.R;

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
}
