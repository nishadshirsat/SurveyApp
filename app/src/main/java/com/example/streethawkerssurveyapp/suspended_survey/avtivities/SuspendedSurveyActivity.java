package com.example.streethawkerssurveyapp.suspended_survey.avtivities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.streethawkerssurveyapp.R;
import com.example.streethawkerssurveyapp.suspended_survey.avtivities.SuspendedSurveyActivity;

import java.util.Calendar;

public class SuspendedSurveyActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_suspended_survey);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Suspended Survey List");

        bindView();

        myCalendar = Calendar.getInstance();
        mYear = myCalendar.get(Calendar.YEAR);
        mMonth = myCalendar.get(Calendar.MONTH);
        mDay = myCalendar.get(Calendar.DAY_OF_MONTH);
        mImgDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(new ContextThemeWrapper(SuspendedSurveyActivity.this, R.style.DialogTheme),
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

                DatePickerDialog datePickerDialog = new DatePickerDialog(new ContextThemeWrapper(SuspendedSurveyActivity.this, R.style.DialogTheme),
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

                DatePickerDialog datePickerDialog = new DatePickerDialog(new ContextThemeWrapper(SuspendedSurveyActivity.this, R.style.DialogTheme),
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

                View viewV = getLayoutInflater().inflate(R.layout.layout_suspend_msg, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(SuspendedSurveyActivity.this);
                final AlertDialog alertDialog = builder.create();
                ImageView image_cancel = (ImageView) viewV.findViewById(R.id.image_cancel);
                TextView TextMsg = (TextView) viewV.findViewById(R.id.TextMsg);
                Button button_kyc_proceed = (Button) viewV.findViewById(R.id.button_kyc_proceed);

                alertDialog.setView(viewV);
                alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                alertDialog.setCanceledOnTouchOutside(false);

                button_kyc_proceed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        alertDialog.dismiss();

                    }
                });
                alertDialog.show();
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
