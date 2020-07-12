package com.example.streethawkerssurveyapp.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.streethawkerssurveyapp.R;
import com.example.streethawkerssurveyapp.pojo_class.FamilyMembers;
import com.example.streethawkerssurveyapp.pojo_class.LandAssets;
import com.example.streethawkerssurveyapp.response_pack.UpdateSurveyResponse;
import com.example.streethawkerssurveyapp.services.AudioRecordService;
import com.example.streethawkerssurveyapp.services_pack.ApiInterface;
import com.example.streethawkerssurveyapp.services_pack.ApiService;
import com.example.streethawkerssurveyapp.services_pack.ApplicationConstant;
import com.example.streethawkerssurveyapp.services_pack.CustomProgressDialog;
import com.example.streethawkerssurveyapp.utils.PrefUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scanlibrary.ScanActivity;
import com.scanlibrary.ScanConstants;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DocumentScanActivity extends AppCompatActivity {

    private LinearLayout mLinearMain;
    private LinearLayout mLinearOne;
    private TextView mTextAddhar;
    private ImageView mImgAadharScan;
    private TextView mTextDL;
    private ImageView mImgDLScan;
    private TextView mTextVoterId;
    private ImageView mImgVoterID;
    private TextView mTextBankPassbook;
    private ImageView mImgBankPassbook;
    private LinearLayout mLinearTwo;
    private TextView mTextFestivalReceipts;
    private ImageView mImgFestivalReceipts;
    private TextView mTextTokens;
    private ImageView mImgTokens;
    private TextView mTextChallan;
    private ImageView mImgChallan;
    private TextView mTextTRChallan;
    private ImageView mImgTRChallan;
    private LinearLayout mLinearThree;
    private TextView mTextPoliceChalan;
    private ImageView mImgPoliceChalan;
    private TextView mTextReceipt;
    private ImageView mImgReceipt;
    private TextView mTextCertificate;
    private ImageView mImgCertificate;
    private TextView mTextAttestedDoc;
    private ImageView mAttestedDoc;
    private LinearLayout mLinearFour;
    private ImageView mImgTehbaziDoc;
    private ImageView mImgSignature;
    private Button mBtnNext;
    private Button mBtnPrevious;

    private String CONTROL = "";

    private static final int REQUEST_CODE = 99;
    private String AADHAR_PATH = "";
    private String DRIVING_LICENCE_PATH = "";
    private String VOTER_ID_PATH = "";
    private String BANK_PASSBOOK_PATH = "";
    private String Undertaking_PATH="";
    private String Tokens_PATH="";
    private String Challan_PATH="";
    private String Traffic_Challan_PATH="";
    private String Police_Challan_PATH="";
    private String Certificate_PATH="";
    private String TehBazari_Doc_PATH="";
    private String Attested_Doc_PATH="";
    private String FestivalReciept_PATH="";
    private String Fine_Reciept_PATH="";

    private ProgressDialog progressDialog;


    File file1 = null;
    private File file2 =null;
    private File file3 =null;
    private File file4=null;
    private File file5=null;
    private File file6=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_scan);

        bindView();

        mBtnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mLinearFour.getVisibility() == View.VISIBLE) {

                    mLinearFour.setVisibility(View.GONE);
                    mLinearOne.setVisibility(View.GONE);
                    mLinearThree.setVisibility(View.VISIBLE);
                    mLinearTwo.setVisibility(View.GONE);

                } else
                if (mLinearThree.getVisibility() == View.VISIBLE) {

                    mLinearFour.setVisibility(View.GONE);
                    mLinearOne.setVisibility(View.GONE);
                    mLinearThree.setVisibility(View.GONE);
                    mLinearTwo.setVisibility(View.VISIBLE);

                } else
                if (mLinearTwo.getVisibility() == View.VISIBLE) {

                    mLinearFour.setVisibility(View.GONE);
                    mLinearOne.setVisibility(View.VISIBLE);
                    mLinearThree.setVisibility(View.GONE);
                    mLinearTwo.setVisibility(View.GONE);

                } else {

                    onBackPressed();

                }

            }
        });

        mImgAadharScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CONTROL = ApplicationConstant.SurveyId + "_" + "Aadhar";
                startScan();
            }
        });


        mImgDLScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CONTROL = ApplicationConstant.SurveyId + "_" + "DrivingLicence";
                startScan();
            }
        });

        mImgVoterID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CONTROL = ApplicationConstant.SurveyId + "_" + "VoterId";
                startScan();
            }
        });

        mImgBankPassbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CONTROL = ApplicationConstant.SurveyId + "_" + "BankPassbook";
                startScan();
            }
        });


        mImgFestivalReceipts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CONTROL = ApplicationConstant.SurveyId + "_" + "FestivalReciept";
                startScan();
            }
        });

        mImgTokens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CONTROL = ApplicationConstant.SurveyId + "_" + "Tokens";
                startScan();
            }
        });

        mImgChallan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CONTROL = ApplicationConstant.SurveyId + "_" + "Challan";
                startScan();
            }
        });

        mImgPoliceChalan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CONTROL = ApplicationConstant.SurveyId + "_" + "Police_Challan";
                startScan();
            }
        });

        mImgTRChallan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CONTROL = ApplicationConstant.SurveyId + "_" + "Traffic_Challan";
                startScan();
            }
        });


        mImgReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CONTROL = ApplicationConstant.SurveyId + "_" + "Fine_Reciept";
                startScan();
            }
        });

        mImgCertificate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CONTROL = ApplicationConstant.SurveyId + "_" + "Certificate";
                startScan();
            }
        });

        mAttestedDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CONTROL = ApplicationConstant.SurveyId + "_" + "Attested_Doc";
                startScan();
            }
        });

        mImgTehbaziDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CONTROL = ApplicationConstant.SurveyId + "_" + "TehBazari_Doc";
                startScan();
            }
        });

        mImgSignature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CONTROL = ApplicationConstant.SurveyId + "_" + "Undertaking";
                startScan();
            }
        });


        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (mLinearOne.getVisibility() == View.VISIBLE) {

                    if (validate1()){
                        mLinearOne.setVisibility(View.GONE);
                        mLinearTwo.setVisibility(View.VISIBLE);
                        mLinearThree.setVisibility(View.GONE);
                        mLinearFour.setVisibility(View.GONE);
                    }


                } else if (mLinearTwo.getVisibility() == View.VISIBLE) {

                    if (validate2()){
                        mLinearTwo.setVisibility(View.GONE);
                        mLinearOne.setVisibility(View.GONE);
                        mLinearThree.setVisibility(View.VISIBLE);
                        mLinearFour.setVisibility(View.GONE);
                    }

                } else if (mLinearThree.getVisibility() == View.VISIBLE) {

                    if (validate3()) {
                        mLinearThree.setVisibility(View.GONE);
                        mLinearOne.setVisibility(View.GONE);
                        mLinearTwo.setVisibility(View.GONE);
                        mLinearFour.setVisibility(View.VISIBLE);

                        mBtnNext.setText("Submit");

                    }

                }else    if (TehBazari_Doc_PATH.trim().isEmpty()){
                    ApplicationConstant.displayMessageDialog(DocumentScanActivity.this,"","Tehbazari Doc is missing");

                }else if (Undertaking_PATH.trim().isEmpty()){
                    ApplicationConstant.displayMessageDialog(DocumentScanActivity.this,"","Undertaking Doc is missing");

                } else {

                    file4 = new File(TehBazari_Doc_PATH);
                    file5 = new File(Undertaking_PATH);

                    stopService(new Intent(DocumentScanActivity.this, AudioRecordService.class));

                    Upload_Documents();

//                    startActivity(new Intent(PersonalDetailsActivity.this, DocumentScanActivity.class));

                }
            }
        });

    }

    private boolean validate1() {

        if (!AADHAR_PATH.trim().isEmpty()){
            file1 = new File(AADHAR_PATH);

        }else if (!DRIVING_LICENCE_PATH.trim().isEmpty()){
            file1 = new File(DRIVING_LICENCE_PATH);

        }else if (!VOTER_ID_PATH.trim().isEmpty()){
            file1 = new File(VOTER_ID_PATH);

        }else if (!BANK_PASSBOOK_PATH.trim().isEmpty()){
            file1 = new File(BANK_PASSBOOK_PATH);

        }else {
            ApplicationConstant.displayMessageDialog(DocumentScanActivity.this,"","Identity Proof Doc is missing");
            return false;

        }

        return true;
    }

    private boolean validate2() {

        if (!FestivalReciept_PATH.trim().isEmpty()){
            file2 = new File(FestivalReciept_PATH);

        }else if (!Tokens_PATH.trim().isEmpty()){
            file2 = new File(Tokens_PATH);

        }else if (!Challan_PATH.trim().isEmpty()){
            file2 = new File(Challan_PATH);

        }else if (!Traffic_Challan_PATH.trim().isEmpty()){
            file2 = new File(Traffic_Challan_PATH);

        }else {
            ApplicationConstant.displayMessageDialog(DocumentScanActivity.this,"","Vending History Proof Doc is missing");
            return false;
        }

        return true;
    }

    private boolean validate3() {

        if (!Police_Challan_PATH.trim().isEmpty()){
            file3 = new File(Police_Challan_PATH);

        }else if (!Fine_Reciept_PATH.trim().isEmpty()){
            file3 = new File(Fine_Reciept_PATH);

        }else if (!Certificate_PATH.trim().isEmpty()){
            file3 = new File(Certificate_PATH);

        }else if (!Attested_Doc_PATH.trim().isEmpty()){
            file3 = new File(Attested_Doc_PATH);

        }else {
            ApplicationConstant.displayMessageDialog(DocumentScanActivity.this,"","Market Association Attested Doc or Certificate is missing");
            return false;
        }

        return true;
    }

    private void bindView() {

        mLinearMain = (LinearLayout) findViewById(R.id.LinearMain);
        mLinearOne = (LinearLayout) findViewById(R.id.LinearOne);
        mTextAddhar = (TextView) findViewById(R.id.TextAddhar);
        mImgAadharScan = (ImageView) findViewById(R.id.ImgAadharScan);
        mTextDL = (TextView) findViewById(R.id.TextDL);
        mImgDLScan = (ImageView) findViewById(R.id.ImgDLScan);
        mTextVoterId = (TextView) findViewById(R.id.TextVoterId);
        mImgVoterID = (ImageView) findViewById(R.id.ImgVoterID);
        mTextBankPassbook = (TextView) findViewById(R.id.TextBankPassbook);
        mImgBankPassbook = (ImageView) findViewById(R.id.ImgBankPassbook);
        mLinearTwo = (LinearLayout) findViewById(R.id.LinearTwo);
        mTextFestivalReceipts = (TextView) findViewById(R.id.TextFestivalReceipts);
        mImgFestivalReceipts = (ImageView) findViewById(R.id.ImgFestivalReceipts);
        mTextTokens = (TextView) findViewById(R.id.TextTokens);
        mImgTokens = (ImageView) findViewById(R.id.ImgTokens);
        mTextChallan = (TextView) findViewById(R.id.TextChallan);
        mImgChallan = (ImageView) findViewById(R.id.ImgChallan);
        mTextTRChallan = (TextView) findViewById(R.id.TextTRChallan);
        mImgTRChallan = (ImageView) findViewById(R.id.ImgTRChallan);
        mLinearThree = (LinearLayout) findViewById(R.id.LinearThree);
        mTextPoliceChalan = (TextView) findViewById(R.id.TextPoliceChalan);
        mImgPoliceChalan = (ImageView) findViewById(R.id.ImgPoliceChalan);
        mTextReceipt = (TextView) findViewById(R.id.TextReceipt);
        mImgReceipt = (ImageView) findViewById(R.id.ImgReceipt);
        mTextCertificate = (TextView) findViewById(R.id.TextCertificate);
        mImgCertificate = (ImageView) findViewById(R.id.ImgCertificate);
        mTextAttestedDoc = (TextView) findViewById(R.id.TextAttestedDoc);
        mAttestedDoc = (ImageView) findViewById(R.id.AttestedDoc);
        mLinearFour = (LinearLayout) findViewById(R.id.LinearFour);
        mImgTehbaziDoc = (ImageView) findViewById(R.id.ImgTehbaziDoc);
        mImgSignature = (ImageView) findViewById(R.id.ImgSignature);
        mBtnNext = (Button) findViewById(R.id.BtnNext);
        mBtnPrevious = (Button) findViewById(R.id.BtnPrevious);
    }

    protected void startScan() {
        Intent intent = new Intent(this, ScanActivity.class);
        intent.putExtra(ScanConstants.OPEN_INTENT_PREFERENCE, ScanConstants.OPEN_CAMERA);
        startActivityForResult(intent, REQUEST_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri uri_image = data.getExtras().getParcelable(ScanConstants.SCANNED_RESULT);

            if (CONTROL.trim().equals(ApplicationConstant.SurveyId + "_" + "Aadhar")) {


                File photoFile = null;
                try {
                    String photoPath = ApplicationConstant.createImageFile(ApplicationConstant.SurveyId + "_" + "Aadhar.png", "Documents", DocumentScanActivity.this);
                    photoFile = new File(photoPath);
                    AADHAR_PATH = photoPath;

                    setBitmap(mImgAadharScan, uri_image, photoFile);

                } catch (IOException ex) {
                    // Error occurred while creating the File
                }


            } else if (CONTROL.trim().equals(ApplicationConstant.SurveyId + "_" + "DrivingLicence")) {

                File photoFile = null;
                try {
                    String photoPath = ApplicationConstant.createImageFile(ApplicationConstant.SurveyId + "_" + "DrivingLicence.png", "Documents", DocumentScanActivity.this);
                    photoFile = new File(photoPath);
                    DRIVING_LICENCE_PATH = photoPath;

                    setBitmap(mImgDLScan, uri_image, photoFile);

                } catch (IOException ex) {
                    // Error occurred while creating the File
                }

            } else if (CONTROL.trim().equals(ApplicationConstant.SurveyId + "_" + "VoterId")) {

                File photoFile = null;
                try {
                    String photoPath = ApplicationConstant.createImageFile(ApplicationConstant.SurveyId + "_" + "VoterId.png", "Documents", DocumentScanActivity.this);
                    photoFile = new File(photoPath);
                    VOTER_ID_PATH = photoPath;

                    setBitmap(mImgVoterID, uri_image, photoFile);

                } catch (IOException ex) {
                    // Error occurred while creating the File
                }

            } else if (CONTROL.trim().equals(ApplicationConstant.SurveyId + "_" + "BankPassbook")) {
                File photoFile = null;
                try {
                    String photoPath = ApplicationConstant.createImageFile(ApplicationConstant.SurveyId + "_" + "BankPassbook.png", "Documents", DocumentScanActivity.this);
                    photoFile = new File(photoPath);
                    BANK_PASSBOOK_PATH = photoPath;

                    setBitmap(mImgBankPassbook, uri_image, photoFile);

                } catch (IOException ex) {
                    // Error occurred while creating the File
                }
            }
            else if (CONTROL.trim().equals(ApplicationConstant.SurveyId + "_" + "FestivalReciept")) {
                File photoFile = null;
                try {
                    String photoPath = ApplicationConstant.createImageFile(ApplicationConstant.SurveyId + "_" + "FestivalReciept.png", "Documents", DocumentScanActivity.this);
                    photoFile = new File(photoPath);
                    FestivalReciept_PATH = photoPath;

                    setBitmap(mImgFestivalReceipts, uri_image, photoFile);

                } catch (IOException ex) {
                    // Error occurred while creating the File
                }
            }


            else if (CONTROL.trim().equals(ApplicationConstant.SurveyId + "_" + "Tokens")) {
                File photoFile = null;
                try {
                    String photoPath = ApplicationConstant.createImageFile(ApplicationConstant.SurveyId + "_" + "Tokens.png", "Documents", DocumentScanActivity.this);
                    photoFile = new File(photoPath);
                    Tokens_PATH = photoPath;

                    setBitmap(mImgTokens, uri_image, photoFile);

                } catch (IOException ex) {
                    // Error occurred while creating the File
                }
            }
            else if (CONTROL.trim().equals(ApplicationConstant.SurveyId + "_" + "Challan")) {
                File photoFile = null;
                try {
                    String photoPath = ApplicationConstant.createImageFile(ApplicationConstant.SurveyId + "_" + "Challan.png", "Documents", DocumentScanActivity.this);
                    photoFile = new File(photoPath);
                    Challan_PATH = photoPath;

                    setBitmap(mImgChallan, uri_image, photoFile);

                } catch (IOException ex) {
                    // Error occurred while creating the File
                }
            }

            else if (CONTROL.trim().equals(ApplicationConstant.SurveyId + "_" + "Police_Challan")) {
                File photoFile = null;
                try {
                    String photoPath = ApplicationConstant.createImageFile(ApplicationConstant.SurveyId + "_" + "Police_Challan.png", "Documents", DocumentScanActivity.this);
                    photoFile = new File(photoPath);
                    Police_Challan_PATH = photoPath;

                    setBitmap(mImgPoliceChalan, uri_image, photoFile);

                } catch (IOException ex) {
                    // Error occurred while creating the File
                }
            }

            else if (CONTROL.trim().equals(ApplicationConstant.SurveyId + "_" + "Traffic_Challan")) {
                File photoFile = null;
                try {
                    String photoPath = ApplicationConstant.createImageFile(ApplicationConstant.SurveyId + "_" + "Traffic_Challan.png", "Documents", DocumentScanActivity.this);
                    photoFile = new File(photoPath);
                    Traffic_Challan_PATH = photoPath;

                    setBitmap(mImgTRChallan, uri_image, photoFile);

                } catch (IOException ex) {
                    // Error occurred while creating the File
                }
            }
            else if (CONTROL.trim().equals(ApplicationConstant.SurveyId + "_" + "Fine_Reciept")) {
                File photoFile = null;
                try {
                    String photoPath = ApplicationConstant.createImageFile(ApplicationConstant.SurveyId + "_" + "Fine_Reciept.png", "Documents", DocumentScanActivity.this);
                    photoFile = new File(photoPath);
                    Fine_Reciept_PATH = photoPath;

                    setBitmap(mImgReceipt, uri_image, photoFile);

                } catch (IOException ex) {
                    // Error occurred while creating the File
                }
            }
    else if (CONTROL.trim().equals(ApplicationConstant.SurveyId + "_" + "Certificate")) {
                File photoFile = null;
                try {
                    String photoPath = ApplicationConstant.createImageFile(ApplicationConstant.SurveyId + "_" + "Certificate.png", "Documents", DocumentScanActivity.this);
                    photoFile = new File(photoPath);
                    Certificate_PATH = photoPath;

                    setBitmap(mImgCertificate, uri_image, photoFile);

                } catch (IOException ex) {
                    // Error occurred while creating the File
                }
            }
    else if (CONTROL.trim().equals(ApplicationConstant.SurveyId + "_" + "Attested_Doc")) {
                File photoFile = null;
                try {
                    String photoPath = ApplicationConstant.createImageFile(ApplicationConstant.SurveyId + "_" + "Attested_Doc.png", "Documents", DocumentScanActivity.this);
                    photoFile = new File(photoPath);
                    Attested_Doc_PATH = photoPath;

                    setBitmap(mAttestedDoc, uri_image, photoFile);

                } catch (IOException ex) {
                    // Error occurred while creating the File
                }
            }

    else if (CONTROL.trim().equals(ApplicationConstant.SurveyId + "_" + "TehBazari_Doc")) {
                File photoFile = null;
                try {
                    String photoPath = ApplicationConstant.createImageFile(ApplicationConstant.SurveyId + "_" + "TehBazari_Doc.png", "Documents", DocumentScanActivity.this);
                    photoFile = new File(photoPath);
                    TehBazari_Doc_PATH = photoPath;

                    setBitmap(mImgTehbaziDoc, uri_image, photoFile);

                } catch (IOException ex) {
                    // Error occurred while creating the File
                }
            }

    else if (CONTROL.trim().equals(ApplicationConstant.SurveyId + "_" + "Undertaking")) {
                File photoFile = null;
                try {
                    String photoPath = ApplicationConstant.createImageFile(ApplicationConstant.SurveyId + "_" + "Undertaking.png", "Documents", DocumentScanActivity.this);
                    photoFile = new File(photoPath);
                    Undertaking_PATH = photoPath;

                    setBitmap(mImgSignature, uri_image, photoFile);

                } catch (IOException ex) {
                    // Error occurred while creating the File
                }
            }

        }
    }

    public void setBitmap(ImageView imageDisplay, Uri uri, File photoFile) {
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            getContentResolver().delete(uri, null, null);
            imageDisplay.setImageBitmap(bitmap);

            writeBitmap(bitmap, photoFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeBitmap(Bitmap bitmap, File filename) {
        try (FileOutputStream out = new FileOutputStream(filename)) {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out); // bmp is your Bitmap instance
//            out.close();
            // PNG is a lossless format, the compression factor (100) is ignored
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void Upload_Documents() {

        String recordingFile =  PrefUtils.getFromPrefs(DocumentScanActivity.this,ApplicationConstant.RECORDING,"");


        file6 = new File(recordingFile);

        String UNiq_Id =  PrefUtils.getFromPrefs(DocumentScanActivity.this,ApplicationConstant.URI_NO_,"");

        progressDialog = CustomProgressDialog.getDialogue(DocumentScanActivity.this);
        progressDialog.show();

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + PrefUtils.getFromPrefs(DocumentScanActivity.this, ApplicationConstant.USERDETAILS.API_KEY, ""));

        RequestBody request_file1 =
                RequestBody.create(MediaType.parse("image/png"), file1);

        RequestBody request_file2 =
                RequestBody.create(MediaType.parse("image/png"), file2);

        RequestBody request_file3 =
                RequestBody.create(MediaType.parse("image/png"), file4);

        RequestBody request_file4 =
                RequestBody.create(MediaType.parse("image/png"), file5);

        RequestBody request_file5 =
                RequestBody.create(MediaType.parse("audio/*"), file6);


// MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body_file1 =
                MultipartBody.Part.createFormData("identity_proof_documents", file1.getName(), request_file1);

        MultipartBody.Part body_file2 =
                MultipartBody.Part.createFormData("vending_history_proof_documents", file1.getName(), request_file2);

        MultipartBody.Part body_file3 =
                MultipartBody.Part.createFormData("allotment_of_tehbazari_document", file1.getName(), request_file3);


  MultipartBody.Part body_file4 =
                MultipartBody.Part.createFormData("undertaking_by_the_applicant", file1.getName(), request_file4);

  MultipartBody.Part body_file5 =
                MultipartBody.Part.createFormData("recording", file6.getName(), request_file5);

        RequestBody URI_NO_ = RequestBody.create(MediaType.parse("multipart/form-data"), UNiq_Id);


        ApiInterface apiservice = ApiService.getApiClient().create(ApiInterface.class);
        Call<UpdateSurveyResponse> call = apiservice.getUpdateDocuments(headers,URI_NO_,body_file1,body_file2
                ,body_file3,body_file5,body_file4

        );

        call.enqueue(new Callback<UpdateSurveyResponse>() {
            @Override
            public void onResponse(Call<UpdateSurveyResponse> call, Response<UpdateSurveyResponse> response) {

                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();

                if (response.body() != null) {

                    if (response.body().isStatus()) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(DocumentScanActivity.this);
                        builder.setTitle("Document Details");
                        builder.setMessage("Saved successfully");
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();

                                double count = Double.parseDouble(ApplicationConstant.SurveyId);
                                count = count + 1.0;

                                ApplicationConstant.displayToastMessage(DocumentScanActivity.this,""+count);

                                PrefUtils.saveToPrefs(DocumentScanActivity.this, ApplicationConstant.SURVEY_ID, ""+count);

                                startActivity(new Intent(DocumentScanActivity.this,PersonalDetailsActivity.class));

                            }
                        });

                        AlertDialog alertDialog = builder.create();
                        alertDialog.setCancelable(false);
                        alertDialog.setCanceledOnTouchOutside(false);
                        alertDialog.show();

//                        ApplicationConstant.displayToastMessage(DocumentScanActivity.this,
//                                "Vending Details saved successfully");


                    } else {

                        ApplicationConstant.displayMessageDialog(DocumentScanActivity.this,
                                "Response",
                                String.valueOf(response.body().isStatus()));
                    }

                }else {

                    try {
                        ApplicationConstant.displayMessageDialog(DocumentScanActivity.this,
                                "Response",
                                response.errorBody().string());
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<UpdateSurveyResponse> call, Throwable t) {

                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();
                ApplicationConstant.displayMessageDialog(DocumentScanActivity.this, "Response", t.getMessage().toString());

            }
        });
    }



}
