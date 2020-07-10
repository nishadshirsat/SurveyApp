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

import com.example.streethawkerssurveyapp.R;

public class VendingDetailsActivity extends AppCompatActivity {

    private LinearLayout mLinearMain;
    private LinearLayout mLinearOne;
    private Spinner mSpinnerItems;
    private EditText mEditVendingSite;
    private EditText mEditFromTime;
    private EditText mEditToTime;
    private EditText mEditAge;
    private LinearLayout mLinearTwo;
    private EditText mEditAnnualIncome;
    private RadioButton mRadioY;
    private RadioButton mRadioN;
    private Spinner mSpinnerVehical;
    private EditText mEditDob;
    private ImageView mImgCalendar;
    private LinearLayout mLinearThree;
    private RadioButton mRadioDY;
    private RadioButton mRadioDN;
    private Spinner mSpinnerChoice;
    private Button mBtnNext;
    private Button mBtnPrevious;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vending_details);

        bindView();

        mBtnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VendingDetailsActivity.this,VendorsFamDetailsActivity.class));
            }
        });
        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VendingDetailsActivity.this,DocumentScanActivity.class));
            }
        });
    }

    private void bindView() {

        mLinearMain = (LinearLayout) findViewById(R.id.LinearMain);
        mLinearOne = (LinearLayout) findViewById(R.id.LinearOne);
        mSpinnerItems = (Spinner) findViewById(R.id.SpinnerItems);
        mEditVendingSite = (EditText) findViewById(R.id.EditVendingSite);
        mEditFromTime = (EditText) findViewById(R.id.EditFromTime);
        mEditToTime = (EditText) findViewById(R.id.EditToTime);
        mEditAge = (EditText) findViewById(R.id.EditAge);
        mLinearTwo = (LinearLayout) findViewById(R.id.LinearTwo);
        mEditAnnualIncome = (EditText) findViewById(R.id.EditAnnualIncome);
        mRadioY = (RadioButton) findViewById(R.id.RadioY);
        mRadioN = (RadioButton) findViewById(R.id.RadioN);
        mSpinnerVehical = (Spinner) findViewById(R.id.SpinnerVehical);
        mEditDob = (EditText) findViewById(R.id.EditDob);
        mImgCalendar = (ImageView) findViewById(R.id.ImgCalendar);
        mLinearThree = (LinearLayout) findViewById(R.id.LinearThree);
        mRadioDY = (RadioButton) findViewById(R.id.RadioDY);
        mRadioDN = (RadioButton) findViewById(R.id.RadioDN);
        mSpinnerChoice = (Spinner) findViewById(R.id.SpinnerChoice);
        mBtnNext = (Button) findViewById(R.id.BtnNext);
        mBtnPrevious = (Button) findViewById(R.id.BtnPrevious);

    }
}
