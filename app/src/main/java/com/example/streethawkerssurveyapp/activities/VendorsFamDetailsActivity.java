package com.example.streethawkerssurveyapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.streethawkerssurveyapp.R;
import com.example.streethawkerssurveyapp.services_pack.ApplicationConstant;

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
    private TextView mTextAdd;

    private int FamCount = 0;
    private int FamInc = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendors_fam_details);

        bindView();

        mTextAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FamCount < FamInc ){
//                    viewAddMembers("Members");
//                    passengerItemAdapter1.notifyDataSetChanged();
                } else {
                    ApplicationConstant.DisplayMessageDialog(VendorsFamDetailsActivity.this,"","Added "+FamCount+" Family Members Already");
                }
            }
        });

        mBtnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VendorsFamDetailsActivity.this,PersonalDetailsActivity.class));
            }
        });
        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validate()){
                    startActivity(new Intent(VendorsFamDetailsActivity.this,VendingDetailsActivity.class));
                }

            }
        });
    }

    private boolean validate() {

        if (!ApplicationConstant.isNetworkAvailable(VendorsFamDetailsActivity.this)) {

            ApplicationConstant.displayMessageDialog(VendorsFamDetailsActivity.this, "No Internet Connection", "Please enable internet connection first to proceed");

            return false;
        } else if (mEditFamName.getText().toString().trim().isEmpty()) {
            mEditFamName.setError("Enter Family Name");
            mEditFamName.requestFocus();
            return false;
        }else if (mEditFamRelation.getText().toString().trim().isEmpty()) {
            mEditFamRelation.setError("Enter Family Relation");
            mEditFamRelation.requestFocus();
            return false;
        }else if (mEditFamAge.getText().toString().trim().isEmpty()) {
            mEditFamAge.setError("Enter Family Age");
            mEditFamAge.requestFocus();
            return false;
        }else if (mEditFamAadhar.getText().toString().trim().isEmpty()) {
            mEditFamAadhar.setError("Enter Family Aadhar No");
            mEditFamAadhar.requestFocus();
            return false;
        }else if (mEditPlot.getText().toString().trim().isEmpty()) {
            mEditPlot.setError("Enter Plot");
            mEditPlot.requestFocus();
            return false;
        }else if (mEditHouseSize.getText().toString().trim().isEmpty()) {
            mEditHouseSize.setError("Enter House Size");
            mEditHouseSize.requestFocus();
            return false;
        }else if (mEditArea.getText().toString().trim().isEmpty()) {
            mEditArea.setError("Enter Area");
            mEditArea.requestFocus();
            return false;
        }else if (mEditKuccha.getText().toString().trim().isEmpty()) {
            mEditKuccha.setError("Enter Kuccha");
            mEditKuccha.requestFocus();
            return false;
        }else if (mEditRent.getText().toString().trim().isEmpty()) {
            mEditRent.setError("Enter Rent");
            mEditRent.requestFocus();
            return false;
        }else if (mEditName.getText().toString().trim().isEmpty()) {
            mEditName.setError("Enter Name");
            mEditName.requestFocus();
            return false;
        }else if (mEditRelation.getText().toString().trim().isEmpty()) {
            mEditRelation.setError("Enter Relation");
            mEditRelation.requestFocus();
            return false;
        }
        else if (mEditAge.getText().toString().trim().isEmpty()) {
            mEditAge.setError("Enter Age");
            mEditAge.requestFocus();
            return false;
        }
        else if (mEditAadhar.getText().toString().trim().isEmpty()) {
            mEditAadhar.setError("Enter Aadhar Number");
            mEditAadhar.requestFocus();
            return false;
        }


        return true;
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
        mTextAdd = (TextView) findViewById(R.id.TextAdd);
    }
}
