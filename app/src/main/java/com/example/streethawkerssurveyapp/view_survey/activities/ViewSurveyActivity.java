package com.example.streethawkerssurveyapp.view_survey.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.streethawkerssurveyapp.R;

import java.util.Calendar;

public class ViewSurveyActivity extends AppCompatActivity {

    private LinearLayout mLinearDate;
    private ImageView mImgDate;
    private EditText mEditDate;
    private EditText mEditAdharNo;
    private CardView CardSurvey;

    private Calendar myCalendar;
    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_survey);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Completed Survey List");

        bindView();

        myCalendar = Calendar.getInstance();
        mYear = myCalendar.get(Calendar.YEAR);
        mMonth = myCalendar.get(Calendar.MONTH);
        mDay = myCalendar.get(Calendar.DAY_OF_MONTH);

        mImgDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(new ContextThemeWrapper(ViewSurveyActivity.this, R.style.DialogTheme),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                mEditDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);


                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });

        mLinearDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(new ContextThemeWrapper(ViewSurveyActivity.this, R.style.DialogTheme),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                mEditDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);


                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });

        mEditDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(new ContextThemeWrapper(ViewSurveyActivity.this, R.style.DialogTheme),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                mEditDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);


                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });

        CardSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewSurveyActivity.this,ViewSurveyDetailsActivity.class));
            }
        });


    }

    private void bindView() {
        mLinearDate = (LinearLayout) findViewById(R.id.LinearDate);
        mImgDate = (ImageView) findViewById(R.id.ImgDate);
        mEditDate = (EditText) findViewById(R.id.EditDate);
        mEditAdharNo = (EditText) findViewById(R.id.EditAdharNo);
        CardSurvey = (CardView) findViewById(R.id.CardSurvey);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
