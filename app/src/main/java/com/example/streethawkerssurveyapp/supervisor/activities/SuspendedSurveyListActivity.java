package com.example.streethawkerssurveyapp.supervisor.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;

import com.example.streethawkerssurveyapp.R;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

public class SuspendedSurveyListActivity extends AppCompatActivity {

    private EditText mText_search;
    private SearchableSpinner SpinnerType;
    private RecyclerView RecyclerSuspended;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suspended_surveyor_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Suspended Survey List");

        bindView();

    }

    private void bindView() {

        mText_search=(EditText)findViewById(R.id.text_search);
        SpinnerType=(SearchableSpinner) findViewById(R.id.SpinnerType);
        RecyclerSuspended=(RecyclerView) findViewById(R.id.RecyclerSuspended);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}