package com.example.streethawkerssurveyapp.officer.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.example.streethawkerssurveyapp.R;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

public class SuspendedSupervisorListActivity extends AppCompatActivity {

    private EditText mText_search;
    private SearchableSpinner SpinnerType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suspended_supervisor_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Suspended Supervisor List");

        bindView();

    }

    private void bindView() {

        mText_search=(EditText)findViewById(R.id.text_search);
        SpinnerType=(SearchableSpinner) findViewById(R.id.SpinnerType);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
