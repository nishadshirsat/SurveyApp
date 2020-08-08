package com.example.streethawkerssurveyapp.officer.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.example.streethawkerssurveyapp.R;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

public class SupervisorListActivity extends AppCompatActivity {

    private SearchableSpinner SpinnerSupervisor,SpinnerSurveyor;
    private androidx.recyclerview.widget.RecyclerView mRecycleSurveyor;
    private EditText mText_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisor_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Supervisor List");

        bindView();

    }

    private void bindView() {

        mText_search = (EditText) findViewById(R.id.text_search);
        SpinnerSupervisor = (SearchableSpinner) findViewById(R.id.SpinnerSupervisor);
        SpinnerSurveyor = (SearchableSpinner) findViewById(R.id.SpinnerSurveyor);
        mRecycleSurveyor = (androidx.recyclerview.widget.RecyclerView) findViewById(R.id.RecycleSurveyor);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
