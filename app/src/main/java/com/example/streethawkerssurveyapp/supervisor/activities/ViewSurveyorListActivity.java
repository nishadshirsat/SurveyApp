package com.example.streethawkerssurveyapp.supervisor.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.example.streethawkerssurveyapp.R;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

public class ViewSurveyorListActivity extends AppCompatActivity {

    private EditText mText_search;
    private SearchableSpinner SpinnerType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_surveyor_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("View Surveyor List");

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
