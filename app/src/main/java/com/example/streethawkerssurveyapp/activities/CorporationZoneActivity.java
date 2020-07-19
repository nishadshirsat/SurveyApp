package com.example.streethawkerssurveyapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.example.streethawkerssurveyapp.R;
import com.example.streethawkerssurveyapp.services_pack.ApplicationConstant;

public class CorporationZoneActivity extends AppCompatActivity {

    private Spinner mSpinnerCorporations;
    private Spinner mSpinnerZones;
    private EditText mEditWard;
    private EditText mEditArea;
    private RelativeLayout mRelative_buttons;
    private Button mBtnNext;
    private Button mBtnPrevious;

    private String
            CORPORATION = "",
            ZONE = "",
            WARD = "",
            AREA = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corporation_zone);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Zone Details");

        bindView();

        mSpinnerCorporations.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                CORPORATION = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mSpinnerZones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ZONE = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    if (CORPORATION.contains("East Delhi Municipal Corporation")) {
                        CORPORATION = "ED";
                    } else if (CORPORATION.contains("South Delhi Municipal Corporation")) {
                        CORPORATION = "SD";
                    }
                    if (CORPORATION.contains("North Delhi Municipal Corporation")) {
                        CORPORATION = "ND";
                    }
                    if (CORPORATION.contains("New Delhi Municipal Council")) {
                        CORPORATION = "NC";
                    } else {
                        CORPORATION = "CN";
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                WARD = mEditWard.getText().toString().trim();
                AREA = mEditArea.getText().toString().trim();

                if (validate()) {

                    startActivity(new Intent(CorporationZoneActivity.this,PersonalDetailsActivity.class));

                }

            }
        });


    }

    private boolean validate() {
        if (!ApplicationConstant.isNetworkAvailable(CorporationZoneActivity.this)) {

            ApplicationConstant.displayMessageDialog(CorporationZoneActivity.this, "No Internet Connection", "Please enable internet connection first to proceed");

            return false;
        } else if (mSpinnerCorporations.getSelectedItem().toString().isEmpty()) {
            mSpinnerCorporations.requestFocus();

            return false;

        } else if (mSpinnerZones.getSelectedItem().toString().isEmpty()) {
            mSpinnerZones.requestFocus();
            return false;

        } else if (mEditWard.getText().toString().trim().isEmpty()) {
            mEditWard.setError("Enter Ward Name");
            mEditWard.requestFocus();
        } else if (mEditArea.getText().toString().trim().isEmpty()) {
            mEditArea.setError("Enter Area Name");
            mEditArea.requestFocus();
        }

        return true;

    }

    private void bindView() {
        mSpinnerCorporations = (Spinner) findViewById(R.id.SpinnerCorporations);
        mSpinnerZones = (Spinner) findViewById(R.id.SpinnerZones);
        mEditWard = (EditText) findViewById(R.id.EditWard);
        mEditArea = (EditText) findViewById(R.id.EditArea);
        mRelative_buttons = (RelativeLayout) findViewById(R.id.relative_buttons);
        mBtnNext = (Button) findViewById(R.id.BtnNext);
        mBtnPrevious = (Button) findViewById(R.id.BtnPrevious);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
