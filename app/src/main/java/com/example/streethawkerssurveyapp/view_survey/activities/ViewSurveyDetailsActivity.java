package com.example.streethawkerssurveyapp.view_survey.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.streethawkerssurveyapp.R;
import com.example.streethawkerssurveyapp.activities.MainActivity;
import com.example.streethawkerssurveyapp.pending_survey.activities.PendingSurveyActivity;
import com.example.streethawkerssurveyapp.response_pack.aadhar_response.AadharData;
import com.example.streethawkerssurveyapp.services_pack.ApiService;
import com.example.streethawkerssurveyapp.services_pack.ApplicationConstant;
import com.example.streethawkerssurveyapp.services_pack.CustomProgressDialog;
import com.example.streethawkerssurveyapp.utils.PrefUtils;
import com.example.streethawkerssurveyapp.view_survey.adapters.ViewCriminalCasesAdpater;
import com.example.streethawkerssurveyapp.view_survey.adapters.ViewFamilyDetailsAdpater;
import com.example.streethawkerssurveyapp.view_survey.adapters.ViewFamilySurveyedDetailsAdpater;
import com.example.streethawkerssurveyapp.view_survey.adapters.ViewLandAssetsAdpater;
import com.example.streethawkerssurveyapp.view_survey.adapters.ViewOtherDocDetailsAdpater;
import com.example.streethawkerssurveyapp.view_survey.response_pojo.AadharDetails;
import com.example.streethawkerssurveyapp.view_survey.response_pojo.CriminalCaseDetailsItem;
import com.example.streethawkerssurveyapp.view_survey.response_pojo.DocumentsData;
import com.example.streethawkerssurveyapp.view_survey.response_pojo.FamilyMembersBeenSurveyedItem;
import com.example.streethawkerssurveyapp.view_survey.response_pojo.FamilyMembersItem;
import com.example.streethawkerssurveyapp.view_survey.response_pojo.LandFixedAssetsItem;
import com.example.streethawkerssurveyapp.view_survey.response_pojo.SingleSurveyDetails;
import com.example.streethawkerssurveyapp.view_survey.response_pojo.SingleSurveyResponse;
import com.example.streethawkerssurveyapp.view_survey.services.ViewSurveyInterface;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ViewSurveyDetailsActivity extends MainActivity {

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
    private LinearLayout mCardFamilyDetails;

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

    private RecyclerView Recycler_CriminalCase;
    private RecyclerView Recycler_FamMembers;
    private RecyclerView Recycler_LandAssets;
    private RecyclerView Recycler_FamSurveyed;
    private RecyclerView viewOtherDocuments;

    private LinearLayout mCardBankDetails;
    private TextView mTextBankNo;
    private TextView mTextBankName;
    private TextView mTextBranch;
    private TextView mTextIfsc;
    private LinearLayout mCardVendingDetails;
    private TextView TextAadharVerified;
    private TextView mTextVendingType;
    private TextView mTextNameVending;
    private TextView TextTimeFrom1,TextTimeFrom2;
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
    private ImageView mImg_Biometric;
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
        setTitle(R.string.survey_details);

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

                setFamilyData();

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

                setBankData();


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

                setVendingData();

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

                setDocumentsData();

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

                setAadharData();

            }
        });

//        mBtnDone.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(ViewSurveyDetailsActivity.this,ViewSurveyDetailsActivity.class));
//            }
//        });

    }

    private void setAadharData() {

        if (SingleSurveyData.getAadharCardDetails()!=null){
            AadharDetails AadharData = SingleSurveyData.getAadharCardDetails();

            mTextAadharNo.setText(AadharData.getAadhaarNumber());

                Glide.with(this).load(AadharData.getProfileImage())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(mImgProfile);

            mTextFullName.setText(AadharData.getFullName());
            mTextSex.setText(AadharData.getGender());
            mTextBirthDate.setText(AadharData.getDob());
            mTextHouse.setText(AadharData.getHouse());
            mTextZipcode.setText(AadharData.getZip());
            mTextPostOffice.setText(AadharData.getPo());
            mTextState.setText(AadharData.getState());
            mTextVTC.setText(AadharData.getVtc());
            mTextDistrict.setText(AadharData.getDist());
            mTextSubDistrict.setText(AadharData.getSubdist());
            if (AadharData.getLandmark()!=null){
                mTextLandmark.setText(AadharData.getLandmark());

            }
            mTextStreet.setText(AadharData.getStreet());
            mTextLocationAadhar.setText(AadharData.getLoc());
            mTextCountry.setText(AadharData.getCountry());

        }


        if (SingleSurveyData.getAadhaar_fingerprint()!=null){

            Glide.with(this).load(SingleSurveyData.getAadhaar_fingerprint())
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(mImg_Biometric);
        }

    }

    private void setDocumentsData() {

        if (SingleSurveyData.getComments()!=null){
            mTextComments.setText(SingleSurveyData.getComments());

        }


        try {
            if (!SingleSurveyData.getOther_documents().isEmpty()){

                mTextOtherDocument.setText("Other Documents List");

                List<DocumentsData> documentsDataList = SingleSurveyData.getOther_documents();

                ViewOtherDocDetailsAdpater docDetailsAdpater = new ViewOtherDocDetailsAdpater(ViewSurveyDetailsActivity.this);
                docDetailsAdpater.setDetails(documentsDataList);

                viewOtherDocuments.setAdapter(docDetailsAdpater);

            }else {
                mTextOtherDocument.setText("No Other Documents Uploaded");
            }
        } catch (Exception e) {
            e.printStackTrace();

            mTextOtherDocument.setText("No Other Documents Uploaded");
        }

        mTextIdFront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (SingleSurveyData.getIdentityProofDocumentsFront() != null){
                    ViewImage(SingleSurveyData.getIdentityProofDocumentsFront().trim());
                }else {
                    ApplicationConstant.displayMessageDialog(ViewSurveyDetailsActivity.this,"","No Document Found");
                }

            }
        });

        mTextIdBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (SingleSurveyData.getIdentityProofDocumentsBack() != null){
                    ViewImage(SingleSurveyData.getIdentityProofDocumentsBack().trim());
                }else {
                    ApplicationConstant.displayMessageDialog(ViewSurveyDetailsActivity.this,"","No Document Found");
                }

            }
        });


        mTextHistoryFront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (SingleSurveyData.getVendingHistoryProofDocumentsFront() != null){
                    ViewImage(SingleSurveyData.getVendingHistoryProofDocumentsFront().trim());
                }else {
                    ApplicationConstant.displayMessageDialog(ViewSurveyDetailsActivity.this,"","No Document Found");
                }

            }
        });


      mTextHistoryBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (SingleSurveyData.getVendingHistoryProofDocumentsBack() != null){
                    ViewImage(SingleSurveyData.getVendingHistoryProofDocumentsBack().trim());
                }else {
                    ApplicationConstant.displayMessageDialog(ViewSurveyDetailsActivity.this,"","No Document Found");
                }

            }
        });


        mTextViewTeharabazi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (SingleSurveyData.getAllotmentOfTehbazariDocument() != null){
                    ViewImage(SingleSurveyData.getAllotmentOfTehbazariDocument().trim());
                }else {
                    ApplicationConstant.displayMessageDialog(ViewSurveyDetailsActivity.this,"","No Document Found");

                }

            }
        });

        mTextUndertaking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (SingleSurveyData.getUndertakingByTheApplicant() != null){
                    ViewImage(SingleSurveyData.getUndertakingByTheApplicant().trim());
                }else {
                    ApplicationConstant.displayMessageDialog(ViewSurveyDetailsActivity.this,"","No Document Found");

                }

            }
        });

        mTextAcknowledge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (SingleSurveyData.getAcknowledgementReceipt() != null){
                    ViewImage(SingleSurveyData.getAcknowledgementReceipt().trim());
                }else {
                    ApplicationConstant.displayMessageDialog(ViewSurveyDetailsActivity.this,"","No Document Found");

                }

            }
        });

        mTextRecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (SingleSurveyData.getRecording() != null){
                    try {
                        Intent viewIntent =
                                new Intent("android.intent.action.VIEW",
                                        Uri.parse(SingleSurveyData.getRecording().get(0)));
                        startActivity(viewIntent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }else {
                    ApplicationConstant.displayMessageDialog(ViewSurveyDetailsActivity.this,"","No Recording Found");

                }

            }
        });

    }

    private void ViewImage(String imageUrl) {

        View view = getLayoutInflater().inflate(R.layout.layout_display_image,null);

        ImageView imageView = view.findViewById(R.id.imageDisplay);

        AlertDialog.Builder builder = new AlertDialog.Builder(ViewSurveyDetailsActivity.this);
        builder.setView(view);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
//        AlertDialog alertDialog = builder.create();


        Glide.with(this).load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(imageView);

        builder.create();
        builder.show();

    }

    private void setVendingData() {

        try {
            mTextVendingType.setText(SingleSurveyData.getTypeOfVending());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mTextNameVending.setText(SingleSurveyData.getNameOfVendingSite());
        TextTimeFrom1.setText(SingleSurveyData.getTimingOfVendingFrom() +" - "+SingleSurveyData.getTimingOfVendingTo());
        TextTimeFrom2.setText(SingleSurveyData.getTimingOfVendingFrom1() +" - "+SingleSurveyData.getTimingOfVendingTo1());
        mTextVendingYrs.setText(SingleSurveyData.getNumberOfYrsOfVending());
        mTextPreviousRecognized.setText(SingleSurveyData.getApplicantRecognizedAsAStreetVendor());
        mTextTypeVehical.setText(SingleSurveyData.getTypeOfStructure());
        mTextActiveDays.setText(SingleSurveyData.getNoOfDaysActive());
        mTextStartVendingDate.setText(SingleSurveyData.getDateOfStartOfVendingActivity());
        mTextTehrabaziDoc.setText(SingleSurveyData.getTehbazariAvailable());
        mTextVendingChoiceArea.setText(SingleSurveyData.getChoiceOfVendingArea());

    }

    private void setBankData() {

        mTextBankNo.setText(SingleSurveyData.getBankAccountNumber());
        mTextBankName.setText(SingleSurveyData.getBankName());
        mTextBranch.setText(SingleSurveyData.getBankBranchName());
        mTextIfsc.setText(SingleSurveyData.getBankIfsc());

    }


    private void setFamilyData() {

        try {
            if (!SingleSurveyData.getFamilyMembers().isEmpty()){

                List<FamilyMembersItem> listFamily = SingleSurveyData.getFamilyMembers();

                ViewFamilyDetailsAdpater familyDetailsAdpater = new ViewFamilyDetailsAdpater(ViewSurveyDetailsActivity.this);
                familyDetailsAdpater.setDetails(listFamily);

                Recycler_FamMembers.setAdapter(familyDetailsAdpater);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (!SingleSurveyData.getLandFixedAssets().isEmpty()){

                List<LandFixedAssetsItem> listLandAssets = SingleSurveyData.getLandFixedAssets();

                ViewLandAssetsAdpater viewLandAssetsAdpater = new ViewLandAssetsAdpater(ViewSurveyDetailsActivity.this);
                viewLandAssetsAdpater.setDetails(listLandAssets);

                Recycler_LandAssets.setAdapter(viewLandAssetsAdpater);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (!SingleSurveyData.getFamilyMembersBeenSurveyed().isEmpty()){

                mTextFamSurveyed.setText("Yes");

                List<FamilyMembersBeenSurveyedItem> listFamilySurveyed = SingleSurveyData.getFamilyMembersBeenSurveyed();

                ViewFamilySurveyedDetailsAdpater familyDetailsAdpater = new ViewFamilySurveyedDetailsAdpater(ViewSurveyDetailsActivity.this);
                familyDetailsAdpater.setDetails(listFamilySurveyed);

                Recycler_FamSurveyed.setAdapter(familyDetailsAdpater);

            }else {
                mTextFamSurveyed.setText("NO");
            }
        } catch (Exception e) {
            e.printStackTrace();

            mTextFamSurveyed.setText("NO");

        }



    }

    private void setPersonalData() {

        mTextUriNo.setText("URI NUMBER : "+SingleSurveyData.getUriNumber());

        if (SingleSurveyData.getAadhaar_verified().trim().equalsIgnoreCase("1")){
            TextAadharVerified.setVisibility(View.VISIBLE);
        }else {
            TextAadharVerified.setVisibility(View.GONE);

        }

        mTextBarcodeApplicationNo.setText(SingleSurveyData.getBarCode());
        mTextCorporation.setText(SingleSurveyData.getCorporation());
        mTextZone.setText(SingleSurveyData.getZone());
        mTextWard.setText(SingleSurveyData.getWard());
        mTextArea.setText(SingleSurveyData.getArea());

        Glide.with(this).load(SingleSurveyData.getPhotoOfTheStreetVendor())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(mImgVendorPhoto);

        Glide.with(this).load(SingleSurveyData.getPhotoOfVendorSite())
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(mImgVendorSite);

        mTextVendorName.setText(SingleSurveyData.getNameOfTheStreetVendor());

        if (SingleSurveyData.getAadhaarNumber()!=null){

            if (!SingleSurveyData.getAadhaarNumber().trim().isEmpty()){
                mTextAadharNo.setText(SingleSurveyData.getAadhaarNumber());

            }else {
                mTextAadharNo.setText("No Aadhar Card");

            }
        }else {
            mTextAadharNo.setText("No Aadhar Card");

        }

        mTextGender.setText(SingleSurveyData.getSex());
        mTextAge.setText(SingleSurveyData.getAge());
        mTextDOB.setText(SingleSurveyData.getDateOfBirth());
        mTextMobile.setText(SingleSurveyData.getContactNumber());
        mTextLandline.setText(SingleSurveyData.getLandlineNumber());
        mTextEducation.setText(SingleSurveyData.getEducationStatus());
        mTextFatherName.setText(SingleSurveyData.getNameOfFatherHusband());
        mTextMotherName.setText(SingleSurveyData.getNameOfMother());
        mTextSpouseName.setText(SingleSurveyData.getSpouseName());
        mTextWidowed.setText(SingleSurveyData.getWhetherWidowedWidower());
        mTextCategory.setText(SingleSurveyData.getCategory());
        mTextResidentAddress.setText(SingleSurveyData.getResidentialCorrespondenceAddress());
        mTextPermanantAddress.setText(SingleSurveyData.getPermanentAddress());
        mTextAnnualImcome.setText(SingleSurveyData.getAnnualIncome());


        try {
            if (SingleSurveyData.getCriminalCasePending().trim().equals("1")){
                mTextCriminal.setText("Yes");

                List<CriminalCaseDetailsItem> listCriminalCases = SingleSurveyData.getCriminalCaseDetails();


                ViewCriminalCasesAdpater criminalCasesAdpater = new ViewCriminalCasesAdpater(ViewSurveyDetailsActivity.this);
                criminalCasesAdpater.setDetails(listCriminalCases);

                Recycler_CriminalCase.setAdapter(criminalCasesAdpater);

            }else {
                mTextCriminal.setText("No");

            }
        } catch (Exception e) {
            e.printStackTrace();

            mTextCriminal.setText("No");


        }

        mTextLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

//                    String geoUri = "http://maps.google.com/maps?q=loc:" + lat + "," + lng + " (" + mTitle + ")";


                    String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?q="+SingleSurveyData.getLatitude()+","+SingleSurveyData.getLongitude());
//                    String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?q="+"18.5204"+","+"73.8567");
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

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
        TextAadharVerified = (TextView) findViewById(R.id.TextAadharVerified);

        Recycler_CriminalCase = (RecyclerView) findViewById(R.id.Recycler_CriminalCase);
        Recycler_CriminalCase.setLayoutManager(new LinearLayoutManager(ViewSurveyDetailsActivity.this));

        Recycler_FamMembers = (RecyclerView) findViewById(R.id.Recycler_FamMembers);
        Recycler_FamMembers.setLayoutManager(new LinearLayoutManager(ViewSurveyDetailsActivity.this));

        Recycler_LandAssets = (RecyclerView) findViewById(R.id.Recycler_LandAssets);
        Recycler_LandAssets.setLayoutManager(new LinearLayoutManager(ViewSurveyDetailsActivity.this));

        Recycler_FamSurveyed = (RecyclerView) findViewById(R.id.Recycler_FamSurveyed);
        Recycler_FamSurveyed.setLayoutManager(new LinearLayoutManager(ViewSurveyDetailsActivity.this));

        viewOtherDocuments = (RecyclerView) findViewById(R.id.viewOtherDocuments);
        viewOtherDocuments.setLayoutManager(new LinearLayoutManager(ViewSurveyDetailsActivity.this));


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
        mCardFamilyDetails = (LinearLayout) findViewById(R.id.CardFamilyDetails);
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
        TextTimeFrom1 = (TextView) findViewById(R.id.TextTimeFrom1);
        TextTimeFrom2 = (TextView) findViewById(R.id.TextTimeFrom2);
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
        mImg_Biometric = (ImageView) findViewById(R.id.Img_Biometric);
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
                        mCardPersonal.performClick();

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
                ApplicationConstant.displayMessageDialog(ViewSurveyDetailsActivity.this, "Response", t.getMessage().toString().trim());

            }
        });
    }

}
