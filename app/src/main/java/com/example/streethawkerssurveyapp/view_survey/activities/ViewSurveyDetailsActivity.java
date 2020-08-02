package com.example.streethawkerssurveyapp.view_survey.activities;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.streethawkerssurveyapp.R;
import com.example.streethawkerssurveyapp.services_pack.ApiService;
import com.example.streethawkerssurveyapp.services_pack.ApplicationConstant;
import com.example.streethawkerssurveyapp.services_pack.CustomProgressDialog;
import com.example.streethawkerssurveyapp.utils.PrefUtils;
import com.example.streethawkerssurveyapp.view_survey.response_pojo.SingleSurveyDetails;
import com.example.streethawkerssurveyapp.view_survey.response_pojo.SingleSurveyResponse;
import com.example.streethawkerssurveyapp.view_survey.services.ViewSurveyInterface;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class ViewSurveyDetailsActivity extends AppCompatActivity {

    private androidx.cardview.widget.CardView mCardPersonal;
    private ImageView mImgPersonal;
    private TextView mTextPersonal;
    private androidx.cardview.widget.CardView mCardFamily;
    private ImageView mImgFamily;
    private TextView mTextFamily;
    private androidx.cardview.widget.CardView mCardBank;
    private ImageView mImgBank;
    private TextView mTextBank;
    private androidx.cardview.widget.CardView mCardVendor;
    private ImageView mImgVending;
    private TextView mTextVending;
    private androidx.cardview.widget.CardView mCardDocuments;
    private ImageView mImgDoc;
    private TextView mTextDoc;
    private androidx.cardview.widget.CardView mCardAadhar;
    private ImageView mImgAadhar;
    private TextView mTextAadhar;
    private TextView mTextUriNo;
    private LinearLayout mCardPersonalDetails;
    private TextView mTextBarcodeApplicationNo;
    private TextView mTextCorporation;
    private TextView mTextZone;
    private TextView mTextWard;
    private TextView mTextArea;
    private TextView mTextLocation;
    private ImageView mImgVendorPhoto;
    private ImageView mImgVendorSite;
    private TextView mTextVendorName;
    private TextView mTextAadharNo;
    private TextView mTextGender;
    private TextView mTextAge;
    private TextView mTextDOB;
    private TextView mTextMobile;
    private TextView mTextLandline;
    private TextView mTextEducation;
    private TextView mTextFatherName;
    private TextView mTextMotherName;
    private TextView mTextSpouseName;
    private TextView mTextWidowed;
    private TextView mTextCategory;
    private TextView mTextResidentAddress;
    private TextView mTextPermanantAddress;
    private TextView mTextAnnualImcome;
    private TextView mTextCriminal;
    private LinearLayout mLinearCriminalCase;
    private TextView mTextCriminalSNo;
    private TextView mTextCriminalDate;
    private TextView mTextCriminalFIR;
    private TextView mTextPoliceName;
    private TextView mTextCaseStatus;
    private LinearLayout mCardFamilyDetails;
    private LinearLayout mLinearFamMember;
    private TextView mTextMember1;
    private TextView mTextMemberRelation;
    private TextView mTextMemberAge;
    private TextView mTextMemberAdhaar;
    private LinearLayout mLinearLandDetails;
    private TextView mTextLandPlot;
    private TextView mTextHouseSize;
    private TextView mTextLandArea;
    private TextView mTextLandKuccha;
    private TextView mTextRentalIncome;
    private TextView mTextFamSurveyed;
    private LinearLayout mLinearFamSurveyed;
    private TextView mTextMember2;
    private TextView mTextMemberRelation2;
    private TextView mTextMemberAge2;
    private TextView mTextMemberAdhaar2;
    private LinearLayout mCardBankDetails;
    private TextView mTextBankNo;
    private TextView mTextBankName;
    private TextView mTextBranch;
    private TextView mTextIfsc;
    private LinearLayout mCardVendingDetails;
    private TextView mTextVendingType;
    private TextView mTextNameVending;
    private TextView mTextTime;
    private TextView mTextVendingYrs;
    private TextView mTextPreviousRecognized;
    private TextView mTextTypeVehical;
    private TextView mTextActiveDays;
    private TextView mTextStartVendingDate;
    private TextView mTextTehrabaziDoc;
    private TextView mTextVendingChoiceArea;
    private LinearLayout mCardDocumentsDetails;
    private TextView mTextIdFront;
    private TextView mTextIdBack;
    private TextView mTextHistoryFront;
    private TextView mTextHistoryBack;
    private TextView mTextViewTeharabazi;
    private TextView mTextUndertaking;
    private TextView mTextAcknowledge;
    private TextView mTextOtherDocument;
    private TextView mTextComments;
    private TextView mTextRecording;
    private LinearLayout mCardAdhaarDetails;
    private TextView mTextUidNo;
    private ImageView mImgProfile;
    private TextView mTextFullName;
    private TextView mTextSex;
    private TextView mTextBirthDate;
    private TextView mTextHouse;
    private TextView mTextZipcode;
    private TextView mTextPostOffice;
    private TextView mTextState;
    private TextView mTextVTC;
    private TextView mTextDistrict;
    private TextView mTextSubDistrict;
    private TextView mTextLandmark;
    private TextView mTextStreet;
    private TextView mTextLocationAadhar;
    private TextView mTextCountry;
    private Button mBtnDone;

    private ProgressDialog progressDialog;
    private SingleSurveyDetails SingleSurveyData;
    private String URI_NO="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_survey_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Survey Details");

        bindView();

        URI_NO  = getIntent().getExtras().getString("URI");

        onClickListners();

        SingleSurveyDetails("survey/"+URI_NO);

    }

    private void onClickListners() {

        mCardPersonal.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                mImgPersonal.setColorFilter(getApplicationContext().getResources().getColor(R.color.colorPrimary));
                mTextPersonal.setTextColor(getApplicationContext().getResources().getColor(R.color.colorPrimary));

                mImgFamily.setColorFilter(getApplicationContext().getResources().getColor(R.color.colorAccent));
                mTextFamily.setTextColor(getApplicationContext().getResources().getColor(R.color.colorAccent));

                mImgBank.setColorFilter(getApplicationContext().getResources().getColor(R.color.colorAccent));
                mTextBank.setTextColor(getApplicationContext().getResources().getColor(R.color.colorAccent));

                mImgVending.setColorFilter(getApplicationContext().getResources().getColor(R.color.colorAccent));
                mTextVending.setTextColor(getApplicationContext().getResources().getColor(R.color.colorAccent));

                mImgDoc.setColorFilter(getApplicationContext().getResources().getColor(R.color.colorAccent));
                mTextDoc.setTextColor(getApplicationContext().getResources().getColor(R.color.colorAccent));

                mImgAadhar.setColorFilter(getApplicationContext().getResources().getColor(R.color.colorAccent));
                mTextAadhar.setTextColor(getApplicationContext().getResources().getColor(R.color.colorAccent));

                if(mCardPersonalDetails.getVisibility()==View.GONE){
                    mCardPersonalDetails.setVisibility(View.VISIBLE);
                    mCardFamilyDetails.setVisibility(View.GONE);
                    mCardBankDetails.setVisibility(View.GONE);
                    mCardVendingDetails.setVisibility(View.GONE);
                    mCardDocumentsDetails.setVisibility(View.GONE);
                    mCardAdhaarDetails.setVisibility(View.GONE);
                }

                setPersonalData();

            }
        });

        mCardFamily.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                mImgPersonal.setColorFilter(getApplicationContext().getResources().getColor(R.color.colorAccent));
                mTextPersonal.setTextColor(getApplicationContext().getResources().getColor(R.color.colorAccent));

                mImgFamily.setColorFilter(getApplicationContext().getResources().getColor(R.color.colorPrimary));
                mTextFamily.setTextColor(getApplicationContext().getResources().getColor(R.color.colorPrimary));

                mImgBank.setColorFilter(getApplicationContext().getResources().getColor(R.color.colorAccent));
                mTextBank.setTextColor(getApplicationContext().getResources().getColor(R.color.colorAccent));

                mImgVending.setColorFilter(getApplicationContext().getResources().getColor(R.color.colorAccent));
                mTextVending.setTextColor(getApplicationContext().getResources().getColor(R.color.colorAccent));

                mImgDoc.setColorFilter(getApplicationContext().getResources().getColor(R.color.colorAccent));
                mTextDoc.setTextColor(getApplicationContext().getResources().getColor(R.color.colorAccent));

                mImgAadhar.setColorFilter(getApplicationContext().getResources().getColor(R.color.colorAccent));
                mTextAadhar.setTextColor(getApplicationContext().getResources().getColor(R.color.colorAccent));

                if(mCardFamilyDetails.getVisibility()==View.GONE){
                    mCardFamilyDetails.setVisibility(View.VISIBLE);
                    mCardPersonalDetails.setVisibility(View.GONE);
                    mCardBankDetails.setVisibility(View.GONE);
                    mCardVendingDetails.setVisibility(View.GONE);
                    mCardDocumentsDetails.setVisibility(View.GONE);
                    mCardAdhaarDetails.setVisibility(View.GONE);
                }

            }
        });

        mCardBank.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                mImgPersonal.setColorFilter(getApplicationContext().getResources().getColor(R.color.colorAccent));
                mTextPersonal.setTextColor(getApplicationContext().getResources().getColor(R.color.colorAccent));

                mImgFamily.setColorFilter(getApplicationContext().getResources().getColor(R.color.colorAccent));
                mTextFamily.setTextColor(getApplicationContext().getResources().getColor(R.color.colorAccent));

                mImgBank.setColorFilter(getApplicationContext().getResources().getColor(R.color.colorPrimary));
                mTextBank.setTextColor(getApplicationContext().getResources().getColor(R.color.colorPrimary));

                mImgVending.setColorFilter(getApplicationContext().getResources().getColor(R.color.colorAccent));
                mTextVending.setTextColor(getApplicationContext().getResources().getColor(R.color.colorAccent));

                mImgDoc.setColorFilter(getApplicationContext().getResources().getColor(R.color.colorAccent));
                mTextDoc.setTextColor(getApplicationContext().getResources().getColor(R.color.colorAccent));

                mImgAadhar.setColorFilter(getApplicationContext().getResources().getColor(R.color.colorAccent));
                mTextAadhar.setTextColor(getApplicationContext().getResources().getColor(R.color.colorAccent));

                if(mCardBankDetails.getVisibility()==View.GONE){
                    mCardBankDetails.setVisibility(View.VISIBLE);
                    mCardPersonalDetails.setVisibility(View.GONE);
                    mCardFamilyDetails.setVisibility(View.GONE);
                    mCardVendingDetails.setVisibility(View.GONE);
                    mCardDocumentsDetails.setVisibility(View.GONE);
                    mCardAdhaarDetails.setVisibility(View.GONE);
                }


            }
        });

        mCardVendor.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                mImgPersonal.setColorFilter(getApplicationContext().getResources().getColor(R.color.colorAccent));
                mTextPersonal.setTextColor(getApplicationContext().getResources().getColor(R.color.colorAccent));

                mImgFamily.setColorFilter(getApplicationContext().getResources().getColor(R.color.colorAccent));
                mTextFamily.setTextColor(getApplicationContext().getResources().getColor(R.color.colorAccent));

                mImgBank.setColorFilter(getApplicationContext().getResources().getColor(R.color.colorAccent));
                mTextBank.setTextColor(getApplicationContext().getResources().getColor(R.color.colorAccent));

                mImgVending.setColorFilter(getApplicationContext().getResources().getColor(R.color.colorPrimary));
                mTextVending.setTextColor(getApplicationContext().getResources().getColor(R.color.colorPrimary));

                mImgDoc.setColorFilter(getApplicationContext().getResources().getColor(R.color.colorAccent));
                mTextDoc.setTextColor(getApplicationContext().getResources().getColor(R.color.colorAccent));

                mImgAadhar.setColorFilter(getApplicationContext().getResources().getColor(R.color.colorAccent));
                mTextAadhar.setTextColor(getApplicationContext().getResources().getColor(R.color.colorAccent));

                if(mCardVendingDetails.getVisibility()==View.GONE){
                    mCardVendingDetails.setVisibility(View.VISIBLE);
                    mCardPersonalDetails.setVisibility(View.GONE);
                    mCardFamilyDetails.setVisibility(View.GONE);
                    mCardBankDetails.setVisibility(View.GONE);
                    mCardDocumentsDetails.setVisibility(View.GONE);
                    mCardAdhaarDetails.setVisibility(View.GONE);
                }


            }
        });

        mCardDocuments.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                mImgPersonal.setColorFilter(getApplicationContext().getResources().getColor(R.color.colorAccent));
                mTextPersonal.setTextColor(getApplicationContext().getResources().getColor(R.color.colorAccent));

                mImgFamily.setColorFilter(getApplicationContext().getResources().getColor(R.color.colorAccent));
                mTextFamily.setTextColor(getApplicationContext().getResources().getColor(R.color.colorAccent));

                mImgBank.setColorFilter(getApplicationContext().getResources().getColor(R.color.colorAccent));
                mTextBank.setTextColor(getApplicationContext().getResources().getColor(R.color.colorAccent));

                mImgVending.setColorFilter(getApplicationContext().getResources().getColor(R.color.colorAccent));
                mTextVending.setTextColor(getApplicationContext().getResources().getColor(R.color.colorAccent));

                mImgDoc.setColorFilter(getApplicationContext().getResources().getColor(R.color.colorPrimary));
                mTextDoc.setTextColor(getApplicationContext().getResources().getColor(R.color.colorPrimary));

                mImgAadhar.setColorFilter(getApplicationContext().getResources().getColor(R.color.colorAccent));
                mTextAadhar.setTextColor(getApplicationContext().getResources().getColor(R.color.colorAccent));

                if(mCardDocumentsDetails.getVisibility()==View.GONE){
                    mCardDocumentsDetails.setVisibility(View.VISIBLE);
                    mCardPersonalDetails.setVisibility(View.GONE);
                    mCardFamilyDetails.setVisibility(View.GONE);
                    mCardBankDetails.setVisibility(View.GONE);
                    mCardVendingDetails.setVisibility(View.GONE);
                    mCardAdhaarDetails.setVisibility(View.GONE);
                }


            }
        });

        mCardAadhar.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                mImgPersonal.setColorFilter(getApplicationContext().getResources().getColor(R.color.colorAccent));
                mTextPersonal.setTextColor(getApplicationContext().getResources().getColor(R.color.colorAccent));

                mImgFamily.setColorFilter(getApplicationContext().getResources().getColor(R.color.colorAccent));
                mTextFamily.setTextColor(getApplicationContext().getResources().getColor(R.color.colorAccent));

                mImgBank.setColorFilter(getApplicationContext().getResources().getColor(R.color.colorAccent));
                mTextBank.setTextColor(getApplicationContext().getResources().getColor(R.color.colorAccent));

                mImgVending.setColorFilter(getApplicationContext().getResources().getColor(R.color.colorAccent));
                mTextVending.setTextColor(getApplicationContext().getResources().getColor(R.color.colorAccent));

                mImgDoc.setColorFilter(getApplicationContext().getResources().getColor(R.color.colorAccent));
                mTextDoc.setTextColor(getApplicationContext().getResources().getColor(R.color.colorAccent));

                mImgAadhar.setColorFilter(getApplicationContext().getResources().getColor(R.color.colorPrimary));
                mTextAadhar.setTextColor(getApplicationContext().getResources().getColor(R.color.colorPrimary));

                if(mCardAdhaarDetails.getVisibility()==View.GONE){
                    mCardAdhaarDetails.setVisibility(View.VISIBLE);
                    mCardPersonalDetails.setVisibility(View.GONE);
                    mCardFamilyDetails.setVisibility(View.GONE);
                    mCardBankDetails.setVisibility(View.GONE);
                    mCardVendingDetails.setVisibility(View.GONE);
                    mCardDocumentsDetails.setVisibility(View.GONE);
                }


            }
        });

        mBtnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewSurveyDetailsActivity.this,ViewSurveyDetailsActivity.class));
            }
        });

    }

    private void setPersonalData() {

        mTextUriNo.setText("URI NUMBER: "+SingleSurveyData.getUriNumber());
        mTextBarcodeApplicationNo.setText(SingleSurveyData.getBarCode());

    }

    private void bindView() {
        mCardPersonal = (androidx.cardview.widget.CardView) findViewById(R.id.CardPersonal);
        mImgPersonal = (ImageView) findViewById(R.id.ImgPersonal);
        mTextPersonal = (TextView) findViewById(R.id.TextPersonal);
        mCardFamily = (androidx.cardview.widget.CardView) findViewById(R.id.CardFamily);
        mImgFamily = (ImageView) findViewById(R.id.ImgFamily);
        mTextFamily = (TextView) findViewById(R.id.TextFamily);
        mCardBank = (androidx.cardview.widget.CardView) findViewById(R.id.CardBank);
        mImgBank = (ImageView) findViewById(R.id.ImgBank);
        mTextBank = (TextView) findViewById(R.id.TextBank);
        mCardVendor = (androidx.cardview.widget.CardView) findViewById(R.id.CardVendor);
        mImgVending = (ImageView) findViewById(R.id.ImgVending);
        mTextVending = (TextView) findViewById(R.id.TextVending);
        mCardDocuments = (androidx.cardview.widget.CardView) findViewById(R.id.CardDocuments);
        mImgDoc = (ImageView) findViewById(R.id.ImgDoc);
        mTextDoc = (TextView) findViewById(R.id.TextDoc);
        mCardAadhar = (androidx.cardview.widget.CardView) findViewById(R.id.CardAadhar);
        mImgAadhar = (ImageView) findViewById(R.id.ImgAadhar);
        mTextAadhar = (TextView) findViewById(R.id.TextAadhar);
        mTextUriNo = (TextView) findViewById(R.id.TextUriNo);
        mCardPersonalDetails = (LinearLayout) findViewById(R.id.CardPersonalDetails);
        mTextBarcodeApplicationNo = (TextView) findViewById(R.id.TextBarcodeApplicationNo);
        mTextCorporation = (TextView) findViewById(R.id.TextCorporation);
        mTextZone = (TextView) findViewById(R.id.TextZone);
        mTextWard = (TextView) findViewById(R.id.TextWard);
        mTextArea = (TextView) findViewById(R.id.TextArea);
        mTextLocation = (TextView) findViewById(R.id.TextLocation);
        mImgVendorPhoto = (ImageView) findViewById(R.id.ImgVendorPhoto);
        mImgVendorSite = (ImageView) findViewById(R.id.ImgVendorSite);
        mTextVendorName = (TextView) findViewById(R.id.TextVendorName);
        mTextAadharNo = (TextView) findViewById(R.id.TextAadharNo);
        mTextGender = (TextView) findViewById(R.id.TextGender);
        mTextAge = (TextView) findViewById(R.id.TextAge);
        mTextDOB = (TextView) findViewById(R.id.TextDOB);
        mTextMobile = (TextView) findViewById(R.id.TextMobile);
        mTextLandline = (TextView) findViewById(R.id.TextLandline);
        mTextEducation = (TextView) findViewById(R.id.TextEducation);
        mTextFatherName = (TextView) findViewById(R.id.TextFatherName);
        mTextMotherName = (TextView) findViewById(R.id.TextMotherName);
        mTextSpouseName = (TextView) findViewById(R.id.TextSpouseName);
        mTextWidowed = (TextView) findViewById(R.id.TextWidowed);
        mTextCategory = (TextView) findViewById(R.id.TextCategory);
        mTextResidentAddress = (TextView) findViewById(R.id.TextResidentAddress);
        mTextPermanantAddress = (TextView) findViewById(R.id.TextPermanantAddress);
        mTextAnnualImcome = (TextView) findViewById(R.id.TextAnnualImcome);
        mTextCriminal = (TextView) findViewById(R.id.TextCriminal);
        mLinearCriminalCase = (LinearLayout) findViewById(R.id.LinearCriminalCase);
        mTextCriminalSNo = (TextView) findViewById(R.id.TextCriminalSNo);
        mTextCriminalDate = (TextView) findViewById(R.id.TextCriminalDate);
        mTextCriminalFIR = (TextView) findViewById(R.id.TextCriminalFIR);
        mTextPoliceName = (TextView) findViewById(R.id.TextPoliceName);
        mTextCaseStatus = (TextView) findViewById(R.id.TextCaseStatus);
        mCardFamilyDetails = (LinearLayout) findViewById(R.id.CardFamilyDetails);
        mLinearFamMember = (LinearLayout) findViewById(R.id.LinearFamMember);
        mTextMember1 = (TextView) findViewById(R.id.TextMember1);
        mTextMemberRelation = (TextView) findViewById(R.id.TextMemberRelation);
        mTextMemberAge = (TextView) findViewById(R.id.TextMemberAge);
        mTextMemberAdhaar = (TextView) findViewById(R.id.TextMemberAdhaar);
        mLinearLandDetails = (LinearLayout) findViewById(R.id.LinearLandDetails);
        mTextLandPlot = (TextView) findViewById(R.id.TextLandPlot);
        mTextHouseSize = (TextView) findViewById(R.id.TextHouseSize);
        mTextLandArea = (TextView) findViewById(R.id.TextLandArea);
        mTextLandKuccha = (TextView) findViewById(R.id.TextLandKuccha);
        mTextRentalIncome = (TextView) findViewById(R.id.TextRentalIncome);
        mTextFamSurveyed = (TextView) findViewById(R.id.TextFamSurveyed);
        mLinearFamSurveyed = (LinearLayout) findViewById(R.id.LinearFamSurveyed);
        mTextMember2 = (TextView) findViewById(R.id.TextMember2);
        mTextMemberRelation2 = (TextView) findViewById(R.id.TextMemberRelation2);
        mTextMemberAge2 = (TextView) findViewById(R.id.TextMemberAge2);
        mTextMemberAdhaar2 = (TextView) findViewById(R.id.TextMemberAdhaar2);
        mCardBankDetails = (LinearLayout) findViewById(R.id.CardBankDetails);
        mTextBankNo = (TextView) findViewById(R.id.TextBankNo);
        mTextBankName = (TextView) findViewById(R.id.TextBankName);
        mTextBranch = (TextView) findViewById(R.id.TextBranch);
        mTextIfsc = (TextView) findViewById(R.id.TextIfsc);
        mCardVendingDetails = (LinearLayout) findViewById(R.id.CardVendingDetails);
        mTextVendingType = (TextView) findViewById(R.id.TextVendingType);
        mTextNameVending = (TextView) findViewById(R.id.TextNameVending);
        mTextTime = (TextView) findViewById(R.id.TextTime);
        mTextVendingYrs = (TextView) findViewById(R.id.TextVendingYrs);
        mTextPreviousRecognized = (TextView) findViewById(R.id.TextPreviousRecognized);
        mTextTypeVehical = (TextView) findViewById(R.id.TextTypeVehical);
        mTextActiveDays = (TextView) findViewById(R.id.TextActiveDays);
        mTextStartVendingDate = (TextView) findViewById(R.id.TextStartVendingDate);
        mTextTehrabaziDoc = (TextView) findViewById(R.id.TextTehrabaziDoc);
        mTextVendingChoiceArea = (TextView) findViewById(R.id.TextVendingChoiceArea);
        mCardDocumentsDetails = (LinearLayout) findViewById(R.id.CardDocumentsDetails);
        mTextIdFront = (TextView) findViewById(R.id.TextIdFront);
        mTextIdBack = (TextView) findViewById(R.id.TextIdBack);
        mTextHistoryFront = (TextView) findViewById(R.id.TextHistoryFront);
        mTextHistoryBack = (TextView) findViewById(R.id.TextHistoryBack);
        mTextViewTeharabazi = (TextView) findViewById(R.id.TextViewTeharabazi);
        mTextUndertaking = (TextView) findViewById(R.id.TextUndertaking);
        mTextAcknowledge = (TextView) findViewById(R.id.TextAcknowledge);
        mTextOtherDocument = (TextView) findViewById(R.id.TextOtherDocument);
        mTextComments = (TextView) findViewById(R.id.TextComments);
        mTextRecording = (TextView) findViewById(R.id.TextRecording);
        mCardAdhaarDetails = (LinearLayout) findViewById(R.id.CardAdhaarDetails);
        mTextUidNo = (TextView) findViewById(R.id.TextUidNo);
        mImgProfile = (ImageView) findViewById(R.id.ImgProfile);
        mTextFullName = (TextView) findViewById(R.id.TextFullName);
        mTextSex = (TextView) findViewById(R.id.TextSex);
        mTextBirthDate = (TextView) findViewById(R.id.TextBirthDate);
        mTextHouse = (TextView) findViewById(R.id.TextHouse);
        mTextZipcode = (TextView) findViewById(R.id.TextZipcode);
        mTextPostOffice = (TextView) findViewById(R.id.TextPostOffice);
        mTextState = (TextView) findViewById(R.id.TextState);
        mTextVTC = (TextView) findViewById(R.id.TextVTC);
        mTextDistrict = (TextView) findViewById(R.id.TextDistrict);
        mTextSubDistrict = (TextView) findViewById(R.id.TextSubDistrict);
        mTextLandmark = (TextView) findViewById(R.id.TextLandmark);
        mTextStreet = (TextView) findViewById(R.id.TextStreet);
        mTextLocationAadhar = (TextView) findViewById(R.id.TextLocationAadhar);
        mTextCountry = (TextView) findViewById(R.id.TextCountry);
        mBtnDone = (Button) findViewById(R.id.BtnDone);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void SingleSurveyDetails(String CustomUrl) {

        progressDialog = CustomProgressDialog.getDialogue(ViewSurveyDetailsActivity.this);
        progressDialog.show();

        String UNiq_Id =  PrefUtils.getFromPrefs(ViewSurveyDetailsActivity.this, ApplicationConstant.URI_NO_,"");

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + PrefUtils.getFromPrefs(ViewSurveyDetailsActivity.this, ApplicationConstant.USERDETAILS.API_KEY, ""));

        ViewSurveyInterface apiservice = ApiService.getApiClient().create(ViewSurveyInterface.class);
        Call<SingleSurveyResponse> call = apiservice.SingleSurveyDetails(headers, CustomUrl);

        call.enqueue(new Callback<SingleSurveyResponse>() {
            @Override
            public void onResponse(Call<SingleSurveyResponse> call, Response<SingleSurveyResponse> response) {

                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();

                if (response.body() != null) {

                    if (response.body().isStatus()) {

                        SingleSurveyData  = response.body().getResponse();

                    } else {

                        ApplicationConstant.displayMessageDialog(ViewSurveyDetailsActivity.this,
                                "Response",
                                "Failed to get Data");
                    }

                }else {
                    try {
                        ApplicationConstant.displayMessageDialog(ViewSurveyDetailsActivity.this,
                                "Response",
                                response.errorBody().string());
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<SingleSurveyResponse> call, Throwable t) {

                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();
                ApplicationConstant.displayMessageDialog(ViewSurveyDetailsActivity.this, "Response", getString(R.string.net_speed_problem));

            }
        });
    }

}
