package com.example.streethawkerssurveyapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.streethawkerssurveyapp.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BankingDetailsActivity extends AppCompatActivity {

    private LinearLayout mLinearMain;
    private LinearLayout mLinearHead;
    private LinearLayout mLinearOne;
    private EditText mEditAccNo;
    private EditText mEditBankName;
    private EditText mEditBranchName;
    private EditText mEditIfscCode;
    private LinearLayout mLinearTwo;
    private RelativeLayout mRelative_buttons;
    private Button mBtnNext;
    private Button mBtnPrevious;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_details);
        bindViews();


        mBtnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mLinearOne.getVisibility() == View.VISIBLE) {

                    mLinearHead.setVisibility(View.VISIBLE);
                    mLinearOne.setVisibility(View.GONE);

                }else if (mLinearHead.getVisibility() == View.VISIBLE) {

                        onBackPressed();

                }

            }
        });


        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mLinearHead.getVisibility() == View.VISIBLE) {

                    mLinearHead.setVisibility(View.GONE);
                    mLinearOne.setVisibility(View.VISIBLE);

                }else   if (mLinearOne.getVisibility() == View.VISIBLE) {

//                    if (validate1()) {
//                        UploadIdentityProof();
//                    }


                }

            }
        });

    }

    private void bindViews() {

        mLinearMain = (LinearLayout) findViewById(R.id.LinearMain);
        mLinearHead = (LinearLayout) findViewById(R.id.LinearHead);
        mLinearOne = (LinearLayout) findViewById(R.id.LinearOne);
        mEditAccNo = (EditText) findViewById(R.id.EditAccNo);
        mEditBankName = (EditText) findViewById(R.id.EditBankName);
        mEditBranchName = (EditText) findViewById(R.id.EditBranchName);
        mEditIfscCode = (EditText) findViewById(R.id.EditIfscCode);
        mLinearTwo = (LinearLayout) findViewById(R.id.LinearTwo);
        mRelative_buttons = (RelativeLayout) findViewById(R.id.relative_buttons);
        mBtnNext = (Button) findViewById(R.id.BtnNext);
        mBtnPrevious = (Button) findViewById(R.id.BtnPrevious);
    }
}
