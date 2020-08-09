package com.example.streethawkerssurveyapp.supervisor.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.streethawkerssurveyapp.R;

public class SurveyorListActivity extends AppCompatActivity {



    private EditText mText_search;
    private androidx.recyclerview.widget.RecyclerView mRecycleSurveyor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surveyor_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Surveyor List");

        bindView();




    }

    private void bindView() {

        mText_search = (EditText) findViewById(R.id.text_search);
        mRecycleSurveyor = (androidx.recyclerview.widget.RecyclerView) findViewById(R.id.RecycleSurveyor);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
