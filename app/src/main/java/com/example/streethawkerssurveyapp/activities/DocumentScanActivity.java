package com.example.streethawkerssurveyapp.activities;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.example.streethawkerssurveyapp.BuildConfig;
import com.example.streethawkerssurveyapp.R;
import com.example.streethawkerssurveyapp.response_pack.UpdateSurveyResponse;
import com.example.streethawkerssurveyapp.services.AudioRecordService;
import com.example.streethawkerssurveyapp.services_pack.ApiInterface;
import com.example.streethawkerssurveyapp.services_pack.ApiService;
import com.example.streethawkerssurveyapp.services_pack.ApplicationConstant;
import com.example.streethawkerssurveyapp.services_pack.CustomProgressDialog;
import com.example.streethawkerssurveyapp.utils.PrefUtils;
import com.example.streethawkerssurveyapp.utils.SurveyAppFileProvider;
import com.scanlibrary.ScanActivity;
import com.scanlibrary.ScanConstants;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DocumentScanActivity extends AppCompatActivity {

    private LinearLayout LinearMain;
    private LinearLayout LinearOne;
    private Spinner SpinnerIdentityProof;
    private ImageView ImgIdentityProofFront;
    private ImageView ImgIdentityProofBack;
    private LinearLayout LinearTwo;
    private Spinner SpinnerVendingHistoryProof;
    private ImageView ImgVendingHistoryFront;
    private ImageView ImgVendingHistoryBack;
    private LinearLayout LinearFour;
    private ImageView ImgTehbaziDoc;
    private ImageView ImgSignature;
    private ImageView ImgAckReceipt;
    private EditText EditComments;
    private RelativeLayout relativeButtons;
    private Button BtnNext;
    private Button BtnPrevious;

    private String CONTROL = "";

    private static final int REQUEST_CODE = 99;

    private String IDENTITY_PROOF_FRONT_PATH = "";
    private String IDENTITY_PROOF_BACK_PATH = "";
    private String VENDING_HISTORY_FRONT_PROOF_PATH = "";
    private String VENDING_HISTORY_BACK_PROOF_PATH = "";


    private String Undertaking_PATH = "";
    private String Acknowledge_PATH = "";
    private String TehBazari_Doc_PATH = "";
    private String Comments = "";

    private ProgressDialog progressDialog;


    private File file_identity_front = null;
    private File file_identity_back = null;
    private File file_vending_history_front = null;
    private File file_vending_history_back = null;
    private File file_undertaking = null;
    private File file_recording = null;
    private File file_acknowlegement = null;
    private File file_tehjabari = null;

    private String Flag_Remember = "", userName, passWord;

    android.app.AlertDialog alertDialog;
    private int READ_PHONE_REQUEST = 20;

    private String IDENTITY_PROOF_TYPE = "";
    private String VENDING_HISTORY_PROOF_TYPE = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_scan);

        bindView();

        SpinnerIdentityProof.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    IDENTITY_PROOF_TYPE = adapterView.getItemAtPosition(i).toString().trim().split("-")[1].trim();
                } catch (Exception e) {
                    e.printStackTrace();
                    IDENTITY_PROOF_TYPE = "Select";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        SpinnerVendingHistoryProof.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    VENDING_HISTORY_PROOF_TYPE = adapterView.getItemAtPosition(i).toString().trim().split("-")[1].trim();
                } catch (Exception e) {
                    e.printStackTrace();
                    VENDING_HISTORY_PROOF_TYPE = "Select";

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        BtnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (LinearFour.getVisibility() == View.VISIBLE) {

                    LinearFour.setVisibility(View.GONE);
                    LinearOne.setVisibility(View.GONE);
//                    mLinearThree.setVisibility(View.VISIBLE);
                    LinearTwo.setVisibility(View.VISIBLE);

                }
//                else if (mLinearThree.getVisibility() == View.VISIBLE) {
//
//                    LinearFour.setVisibility(View.GONE);
//                    LinearOne.setVisibility(View.GONE);
//                    mLinearThree.setVisibility(View.GONE);
//                    LinearTwo.setVisibility(View.VISIBLE);
//
//                }
                else if (LinearTwo.getVisibility() == View.VISIBLE) {

                    LinearFour.setVisibility(View.GONE);
                    LinearOne.setVisibility(View.VISIBLE);
//                    mLinearThree.setVisibility(View.GONE);
                    LinearTwo.setVisibility(View.GONE);

                } else {

                    onBackPressed();

                }

            }
        });


        ImgTehbaziDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CONTROL = ApplicationConstant.SurveyId + "_" + "TehBazari_Doc";
                startScan();
            }
        });

        ImgIdentityProofFront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CONTROL = ApplicationConstant.SurveyId + "_" + "IdentityProof_Front";
                startScan();
            }
        });

        ImgIdentityProofBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CONTROL = ApplicationConstant.SurveyId + "_" + "IdentityProof_Back";
                startScan();
            }
        });

        ImgVendingHistoryFront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CONTROL = ApplicationConstant.SurveyId + "_" + "VendingHistory_Front";
                startScan();
            }
        });

        ImgVendingHistoryBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CONTROL = ApplicationConstant.SurveyId + "_" + "VendingHistory_Back";
                startScan();
            }
        });

        ImgSignature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CONTROL = ApplicationConstant.SurveyId + "_" + "Undertaking";
                startScan();
            }
        });

        ImgAckReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CONTROL = ApplicationConstant.SurveyId + "_" + "Acknowledge_Receipt";
                startScan();
            }
        });


        BtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Upload_Recordings();

                Comments = EditComments.getText().toString().trim();

                stopService(new Intent(DocumentScanActivity.this, AudioRecordService.class));


                if (LinearOne.getVisibility() == View.VISIBLE) {

                    if (validate1()) {
                        LinearOne.setVisibility(View.GONE);
                        LinearTwo.setVisibility(View.VISIBLE);
                        LinearFour.setVisibility(View.GONE);
                    }


                } else if (LinearTwo.getVisibility() == View.VISIBLE) {

                    if (validate2()) {
                        LinearTwo.setVisibility(View.GONE);
                        LinearOne.setVisibility(View.GONE);
                        LinearFour.setVisibility(View.VISIBLE);
                    }

                }
//                else if (mLinearThree.getVisibility() == View.VISIBLE) {
//
//                    if (validate3()) {
//                        mLinearThree.setVisibility(View.GONE);
//                        LinearOne.setVisibility(View.GONE);
//                        LinearTwo.setVisibility(View.GONE);
//                        LinearFour.setVisibility(View.VISIBLE);
//
//                        BtnNext.setText("Submit");
//
//                    }
//
//                }
//                else  if (TehBazari_Doc_PATH.trim().isEmpty()){
//                    ApplicationConstant.displayMessageDialog(DocumentScanActivity.this,"","Tehbazari Doc is missing");
//
//                }
                else if (Undertaking_PATH.trim().isEmpty()) {
                    ApplicationConstant.displayMessageDialog(DocumentScanActivity.this, "", "Undertaking Doc is missing");

                } else if (Acknowledge_PATH.trim().isEmpty()) {
                    ApplicationConstant.displayMessageDialog(DocumentScanActivity.this, "", "Acknowledge Doc is missing");

                } else if (Comments.trim().isEmpty()) {
                    ApplicationConstant.displayMessageDialog(DocumentScanActivity.this, "", "Please Enter Comment");

                } else {

                    file_tehjabari = new File(TehBazari_Doc_PATH);
                    file_identity_back = new File(IDENTITY_PROOF_BACK_PATH);
                    file_vending_history_back = new File(VENDING_HISTORY_BACK_PROOF_PATH);

                    file_undertaking = new File(Undertaking_PATH);
                    file_acknowlegement = new File(Acknowledge_PATH);


                    Upload_Recordings();


//                    startActivity(new Intent(PersonalDetailsActivity.this, DocumentScanActivity.class));

                }
            }
        });

    }

    private boolean validate1() {

        if (IDENTITY_PROOF_TYPE.trim().equals("Select")) {
            ApplicationConstant.displayMessageDialog(DocumentScanActivity.this, "", "Select Identity Proof Type");
            return false;
        } else if (IDENTITY_PROOF_FRONT_PATH.trim().isEmpty()) {
            ApplicationConstant.displayMessageDialog(DocumentScanActivity.this, "", "Capture Identity Proof ");
            return false;

        } else if (!IDENTITY_PROOF_FRONT_PATH.trim().isEmpty()) {
            file_identity_front = new File(IDENTITY_PROOF_FRONT_PATH);

        } else {
            ApplicationConstant.displayMessageDialog(DocumentScanActivity.this, "", "Identity Proof Doc is missing");
            return false;

        }

        return true;
    }

    private boolean validate2() {

        if (VENDING_HISTORY_PROOF_TYPE.trim().equals("Select")) {
            ApplicationConstant.displayMessageDialog(DocumentScanActivity.this, "", "Select Vending History Proof Type");
            return false;

        } else if (VENDING_HISTORY_FRONT_PROOF_PATH.trim().isEmpty()) {
            ApplicationConstant.displayMessageDialog(DocumentScanActivity.this, "", "Capture Vending History Proof ");
            return false;

        } else if (!VENDING_HISTORY_FRONT_PROOF_PATH.trim().isEmpty()) {
            file_vending_history_front = new File(VENDING_HISTORY_FRONT_PROOF_PATH);

        } else {
            ApplicationConstant.displayMessageDialog(DocumentScanActivity.this, "", "Vending History Proof Doc is missing");
            return false;
        }

        return true;
    }

//    private boolean validate3() {
//
//        if (!Police_Challan_PATH.trim().isEmpty()) {
//            file3 = new File(Police_Challan_PATH);
//
//        } else if (!Fine_Reciept_PATH.trim().isEmpty()) {
//            file3 = new File(Fine_Reciept_PATH);
//
//        } else if (!Certificate_PATH.trim().isEmpty()) {
//            file3 = new File(Certificate_PATH);
//
//        } else if (!Attested_Doc_PATH.trim().isEmpty()) {
//            file3 = new File(Attested_Doc_PATH);
//
//        } else {
//            ApplicationConstant.displayMessageDialog(DocumentScanActivity.this, "", "Market Association Attested Doc or Certificate is missing");
//            return false;
//        }
//
//        return true;
//    }

    private void bindView() {

        LinearMain = (LinearLayout) findViewById(R.id.LinearMain);
        LinearOne = (LinearLayout) findViewById(R.id.LinearOne);
        SpinnerIdentityProof = (Spinner) findViewById(R.id.SpinnerIdentityProof);
        ImgIdentityProofFront = (ImageView) findViewById(R.id.ImgIdentityProofFront);
        ImgIdentityProofBack = (ImageView) findViewById(R.id.ImgIdentityProofBack);
        LinearTwo = (LinearLayout) findViewById(R.id.LinearTwo);
        SpinnerVendingHistoryProof = (Spinner) findViewById(R.id.SpinnerVendingHistoryProof);
        ImgVendingHistoryFront = (ImageView) findViewById(R.id.ImgVendingHistoryFront);
        ImgVendingHistoryBack = (ImageView) findViewById(R.id.ImgVendingHistoryBack);
        LinearFour = (LinearLayout) findViewById(R.id.LinearFour);
        ImgTehbaziDoc = (ImageView) findViewById(R.id.ImgTehbaziDoc);
        ImgSignature = (ImageView) findViewById(R.id.ImgSignature);
        ImgAckReceipt = (ImageView) findViewById(R.id.ImgAckReceipt);
        EditComments = (EditText) findViewById(R.id.EditComments);
        relativeButtons = (RelativeLayout) findViewById(R.id.relative_buttons);
        BtnNext = (Button) findViewById(R.id.BtnNext);
        BtnPrevious = (Button) findViewById(R.id.BtnPrevious);
    }

    protected void startScan() {

        checkForPermissions();


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri uri_image = data.getExtras().getParcelable(ScanConstants.SCANNED_RESULT);


            if (CONTROL.trim().equals(ApplicationConstant.SurveyId + "_" + "IdentityProof_Front")) {
                File photoFile = null;
                try {
                    String photoPath = ApplicationConstant.createImageFile(ApplicationConstant.SurveyId + "_" + "IdentityProof_Front.png", "Documents", DocumentScanActivity.this);
                    photoFile = new File(photoPath);
                    IDENTITY_PROOF_FRONT_PATH = photoPath;

                    setBitmap(ImgIdentityProofFront, uri_image, photoFile);

                } catch (IOException ex) {
                    // Error occurred while creating the File
                }
            } else if (CONTROL.trim().equals(ApplicationConstant.SurveyId + "_" + "IdentityProof_Back")) {
                File photoFile = null;
                try {
                    String photoPath = ApplicationConstant.createImageFile(ApplicationConstant.SurveyId + "_" + "IdentityProof_Back.png", "Documents", DocumentScanActivity.this);
                    photoFile = new File(photoPath);
                    IDENTITY_PROOF_BACK_PATH = photoPath;

                    setBitmap(ImgIdentityProofBack, uri_image, photoFile);

                } catch (IOException ex) {
                    // Error occurred while creating the File
                }
            } else if (CONTROL.trim().equals(ApplicationConstant.SurveyId + "_" + "VendingHistory_Front")) {
                File photoFile = null;
                try {
                    String photoPath = ApplicationConstant.createImageFile(ApplicationConstant.SurveyId + "_" + "VendingHistory_Front.png", "Documents", DocumentScanActivity.this);
                    photoFile = new File(photoPath);
                    VENDING_HISTORY_FRONT_PROOF_PATH = photoPath;

                    setBitmap(ImgVendingHistoryFront, uri_image, photoFile);

                } catch (IOException ex) {
                    // Error occurred while creating the File
                }
            } else if (CONTROL.trim().equals(ApplicationConstant.SurveyId + "_" + "VendingHistory_Back")) {
                File photoFile = null;
                try {
                    String photoPath = ApplicationConstant.createImageFile(ApplicationConstant.SurveyId + "_" + "VendingHistory_Back.png", "Documents", DocumentScanActivity.this);
                    photoFile = new File(photoPath);
                    VENDING_HISTORY_BACK_PROOF_PATH = photoPath;

                    setBitmap(ImgVendingHistoryBack, uri_image, photoFile);

                } catch (IOException ex) {
                    // Error occurred while creating the File
                }
            } else if (CONTROL.trim().equals(ApplicationConstant.SurveyId + "_" + "TehBazari_Doc")) {
                File photoFile = null;
                try {
                    String photoPath = ApplicationConstant.createImageFile(ApplicationConstant.SurveyId + "_" + "TehBazari_Doc.png", "Documents", DocumentScanActivity.this);
                    photoFile = new File(photoPath);
                    TehBazari_Doc_PATH = photoPath;

                    setBitmap(ImgTehbaziDoc, uri_image, photoFile);

                } catch (IOException ex) {
                    // Error occurred while creating the File
                }
            } else if (CONTROL.trim().equals(ApplicationConstant.SurveyId + "_" + "Undertaking")) {
                File photoFile = null;
                try {
                    String photoPath = ApplicationConstant.createImageFile(ApplicationConstant.SurveyId + "_" + "Undertaking.png", "Documents", DocumentScanActivity.this);
                    photoFile = new File(photoPath);
                    Undertaking_PATH = photoPath;

                    setBitmap(ImgSignature, uri_image, photoFile);

                } catch (IOException ex) {
                    // Error occurred while creating the File
                }
            } else if (CONTROL.trim().equals(ApplicationConstant.SurveyId + "_" + "Acknowledge_Receipt")) {
                File photoFile = null;
                try {
                    String photoPath = ApplicationConstant.createImageFile(ApplicationConstant.SurveyId + "_" + "Acknowledge_Receipt.png", "Documents", DocumentScanActivity.this);
                    photoFile = new File(photoPath);
                    Acknowledge_PATH = photoPath;

                    setBitmap(ImgAckReceipt, uri_image, photoFile);

                } catch (IOException ex) {
                    // Error occurred while creating the File
                }
            }

        }
    }

    public void setBitmap(ImageView imageDisplay, Uri uri, File photoFile) {
        Bitmap bitmap = null;

        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
            bitmap = null;
        }
        System.gc();
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


        String UNiq_Id = PrefUtils.getFromPrefs(DocumentScanActivity.this, ApplicationConstant.URI_NO_, "");
        String Contact = PrefUtils.getFromPrefs(DocumentScanActivity.this, ApplicationConstant.CONTACT, "");

        progressDialog = CustomProgressDialog.getDialogue(DocumentScanActivity.this);
        progressDialog.show();

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + PrefUtils.getFromPrefs(DocumentScanActivity.this, ApplicationConstant.USERDETAILS.API_KEY, ""));

        RequestBody request_identity_front =
                RequestBody.create(MediaType.parse("image/png"), file_identity_front);

        RequestBody request_identity_back =
                RequestBody.create(MediaType.parse("image/png"), file_identity_back);

        RequestBody request_vending_history_front =
                RequestBody.create(MediaType.parse("image/png"), file_vending_history_front);

        RequestBody request_vending_history_back =
                RequestBody.create(MediaType.parse("image/png"), file_vending_history_back);

        RequestBody request_tehjabari =
                RequestBody.create(MediaType.parse("image/png"), file_tehjabari);

        RequestBody request_undertaking =
                RequestBody.create(MediaType.parse("image/png"), file_undertaking);

        RequestBody request_acknowlegement =
                RequestBody.create(MediaType.parse("image/png"), file_acknowlegement);


// MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body_identity_front =
                MultipartBody.Part.createFormData("identity_proof_documents_front", file_identity_front.getName(), request_identity_front);


        MultipartBody.Part body_vending_history_front =
                MultipartBody.Part.createFormData("vending_history_proof_documents_front", file_vending_history_front.getName(), request_vending_history_front);


        MultipartBody.Part body_identity_back = null;
        if (IDENTITY_PROOF_BACK_PATH.trim().isEmpty()) {
            body_identity_back = null;
        } else {

            body_identity_back = MultipartBody.Part.createFormData("identity_proof_documents_back", file_identity_back.getName(), request_identity_back);

        }

        MultipartBody.Part body_vending_history_back = null;
        if (VENDING_HISTORY_BACK_PROOF_PATH.trim().isEmpty()) {
            body_vending_history_back = null;
        } else {

            body_vending_history_back = MultipartBody.Part.createFormData("vending_history_proof_documents_back", file_vending_history_back.getName(), request_vending_history_back);

        }

        MultipartBody.Part body_tehbazari_document = null;
        if (TehBazari_Doc_PATH.trim().isEmpty()) {
            body_tehbazari_document = null;
        } else {

            body_tehbazari_document = MultipartBody.Part.createFormData("allotment_of_tehbazari_document", file_tehjabari.getName(), request_tehjabari);

        }

        MultipartBody.Part body_undertaking =
                MultipartBody.Part.createFormData("undertaking_by_the_applicant", file_undertaking.getName(), request_undertaking);


        MultipartBody.Part body_acknowlegement =
                MultipartBody.Part.createFormData("acknowledgement_receipt", file_acknowlegement.getName(), request_acknowlegement);

        String CORPORATION =   PrefUtils.getFromPrefs(DocumentScanActivity.this,ApplicationConstant.CORPORATION,"");
        String ZONE =  PrefUtils.getFromPrefs(DocumentScanActivity.this,ApplicationConstant.ZONE,"");
        String WARD =  PrefUtils.getFromPrefs(DocumentScanActivity.this,ApplicationConstant.WARD,"");

        RequestBody CORPORATION_ = RequestBody.create(MediaType.parse("multipart/form-data"), CORPORATION);
        RequestBody ZONE_ = RequestBody.create(MediaType.parse("multipart/form-data"), ZONE);
        RequestBody WARD_ = RequestBody.create(MediaType.parse("multipart/form-data"), WARD);


        RequestBody URI_NO_ = RequestBody.create(MediaType.parse("multipart/form-data"), UNiq_Id);
        RequestBody Contact_ = RequestBody.create(MediaType.parse("multipart/form-data"), Contact);
        RequestBody COMMENTS_ = RequestBody.create(MediaType.parse("multipart/form-data"), Comments);
        RequestBody SURVEY_STATUS = RequestBody.create(MediaType.parse("multipart/form-data"), "1");
        RequestBody IDENTITY_PROOF_TYPE_ = RequestBody.create(MediaType.parse("multipart/form-data"), IDENTITY_PROOF_TYPE);
        RequestBody VENDING_HISTORY_PROOF_TYPE_ = RequestBody.create(MediaType.parse("multipart/form-data"), VENDING_HISTORY_PROOF_TYPE);


        ApiInterface apiservice = ApiService.getApiClient().create(ApiInterface.class);
        Call<UpdateSurveyResponse> call = apiservice.getUpdateDocuments(headers,
                URI_NO_,
                Contact_,
                CORPORATION_,
                ZONE_,
                WARD_,
                IDENTITY_PROOF_TYPE_,
                VENDING_HISTORY_PROOF_TYPE_,
                SURVEY_STATUS,
                COMMENTS_,
                body_identity_front,
                body_identity_back,
                body_vending_history_front,
                body_vending_history_back,
                body_tehbazari_document,
                body_acknowlegement,
                body_undertaking

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
                                startActivity(new Intent(DocumentScanActivity.this, DashboardActivity.class));
                                finish();

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
                                String.valueOf(response.body().isStatus())+"-"+response.body().getMessage());
                    }

                } else {

                    try {
                        ApplicationConstant.displayMessageDialog(DocumentScanActivity.this,
                                "Response",
                                response.errorBody().string());
                    } catch (Exception e) {
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


    private void Upload_Recordings() {

        String recordingFile = PrefUtils.getFromPrefs(DocumentScanActivity.this, ApplicationConstant.RECORDING, "");

//        String recordingFile = null;
//        try {
//            recordingFile = ApplicationConstant.createImageFile("6.0_surveyer.3gp","Recordings", DocumentScanActivity.this);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        file_recording = new File(recordingFile);

        String UNiq_Id = PrefUtils.getFromPrefs(DocumentScanActivity.this, ApplicationConstant.URI_NO_, "");

        progressDialog = CustomProgressDialog.getDialogue(DocumentScanActivity.this);
        progressDialog.show();

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + PrefUtils.getFromPrefs(DocumentScanActivity.this, ApplicationConstant.USERDETAILS.API_KEY, ""));

        Uri recordUri = SurveyAppFileProvider.getUriForFile(DocumentScanActivity.this,
                BuildConfig.APPLICATION_ID + ".android.fileprovider",
                file_recording);

        RequestBody request_recording =
                RequestBody.create(MediaType.parse(getContentResolver().getType(recordUri)), file_recording);


// MultipartBody.Part is used to send also the actual file name

        MultipartBody.Part body_file5 =
                MultipartBody.Part.createFormData("recording", file_recording.getName(), request_recording);

        RequestBody URI_NO_ = RequestBody.create(MediaType.parse("multipart/form-data"), UNiq_Id);

        String CORPORATION =   PrefUtils.getFromPrefs(DocumentScanActivity.this,ApplicationConstant.CORPORATION,"");
        String ZONE =  PrefUtils.getFromPrefs(DocumentScanActivity.this,ApplicationConstant.ZONE,"");
        String WARD =  PrefUtils.getFromPrefs(DocumentScanActivity.this,ApplicationConstant.WARD,"");

        RequestBody CORPORATION_ = RequestBody.create(MediaType.parse("multipart/form-data"), CORPORATION);
        RequestBody ZONE_ = RequestBody.create(MediaType.parse("multipart/form-data"), ZONE);
        RequestBody WARD_ = RequestBody.create(MediaType.parse("multipart/form-data"), WARD);

//        RequestBody URI_NO_ = RequestBody.create(MediaType.parse("multipart/form-data"), "2781767");


        ApiInterface apiservice = ApiService.getApiClient().create(ApiInterface.class);
        Call<UpdateSurveyResponse> call = apiservice.getUpdateRecording(headers, URI_NO_,
                CORPORATION_,
                ZONE_,
                WARD_,
                body_file5

        );

        call.enqueue(new Callback<UpdateSurveyResponse>() {
            @Override
            public void onResponse(Call<UpdateSurveyResponse> call, Response<UpdateSurveyResponse> response) {

                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();

                if (response.body() != null) {

                    if (response.body().isStatus()) {


                        ApplicationConstant.displayToastMessage(DocumentScanActivity.this,
                                "Recording saved successfully");
                        Upload_Documents();


                    } else {

                        ApplicationConstant.displayToastMessage(DocumentScanActivity.this,
                                String.valueOf(response.body().isStatus())+"-"+response.body().getMessage());

                        Upload_Documents();

                    }

                } else {

                    try {
                        ApplicationConstant.displayToastMessage(DocumentScanActivity.this,
                                response.errorBody().string());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    Upload_Documents();


                }
            }

            @Override
            public void onFailure(Call<UpdateSurveyResponse> call, Throwable t) {

                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();
                ApplicationConstant.displayToastMessage(DocumentScanActivity.this, t.getMessage().toString());
                Upload_Documents();

            }
        });
    }

    public void checkForPermissions() {
        if (ContextCompat.checkSelfPermission(DocumentScanActivity.this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(DocumentScanActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(DocumentScanActivity.this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(DocumentScanActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(DocumentScanActivity.this,
                Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(DocumentScanActivity.this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(DocumentScanActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(DocumentScanActivity.this,
                Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(DocumentScanActivity.this,
                Manifest.permission.MODIFY_AUDIO_SETTINGS)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(DocumentScanActivity.this,
                    Manifest.permission.CAMERA) && ActivityCompat.shouldShowRequestPermissionRationale(DocumentScanActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(DocumentScanActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(DocumentScanActivity.this,
                    Manifest.permission.READ_PHONE_STATE) && ActivityCompat.shouldShowRequestPermissionRationale(DocumentScanActivity.this,
                    Manifest.permission.READ_CONTACTS) && ActivityCompat.shouldShowRequestPermissionRationale(DocumentScanActivity.this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) && ActivityCompat.shouldShowRequestPermissionRationale(DocumentScanActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION) && ActivityCompat.shouldShowRequestPermissionRationale(DocumentScanActivity.this,
                    Manifest.permission.RECORD_AUDIO) && ActivityCompat.shouldShowRequestPermissionRationale(DocumentScanActivity.this,
                    Manifest.permission.MODIFY_AUDIO_SETTINGS)) {
                // Show an explanation to the user asynchronously -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

//              Toast.makeText(DocumentScanActivity.this,"WAITING FOR USER RESPONSE",Toast.LENGTH_SHORT).show();

                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(DocumentScanActivity.this);
                builder.setTitle("Permissions Needed");
                builder.setMessage("Want to access your camera and storage to set your profile");
                builder.setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        alertDialog.dismiss();

                        ActivityCompat.requestPermissions(DocumentScanActivity.this,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                        Manifest.permission.READ_EXTERNAL_STORAGE,
                                        Manifest.permission.CAMERA,
                                        Manifest.permission.READ_PHONE_STATE,
                                        Manifest.permission.ACCESS_COARSE_LOCATION,
                                        Manifest.permission.ACCESS_FINE_LOCATION,
                                        Manifest.permission.RECORD_AUDIO,
                                        Manifest.permission.MODIFY_AUDIO_SETTINGS,
                                        Manifest.permission.READ_CONTACTS},

                                READ_PHONE_REQUEST);
                    }
                });

                builder.setNegativeButton("Deny", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog.dismiss();
                    }
                });
                alertDialog = builder.create();
                alertDialog.show();
                alertDialog.setCanceledOnTouchOutside(false);
                alertDialog.setCancelable(false);

            } else {
                // No explanation needed; request the permission
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(DocumentScanActivity.this);
                builder.setTitle("Permissions Needed");
                builder.setMessage("Want to access your camera and storage to set your profile");
                builder.setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog.dismiss();

                        ActivityCompat.requestPermissions(DocumentScanActivity.this,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                        Manifest.permission.READ_EXTERNAL_STORAGE,
                                        Manifest.permission.CAMERA,
                                        Manifest.permission.ACCESS_COARSE_LOCATION,
                                        Manifest.permission.ACCESS_FINE_LOCATION,
                                        Manifest.permission.READ_PHONE_STATE,
                                        Manifest.permission.RECORD_AUDIO,
                                        Manifest.permission.MODIFY_AUDIO_SETTINGS,
                                        Manifest.permission.READ_CONTACTS},
                                READ_PHONE_REQUEST);
                    }
                });

                builder.setNegativeButton("Deny", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog.dismiss();
                    }
                });

                alertDialog = builder.create();
                alertDialog.show();
                alertDialog.setCanceledOnTouchOutside(false);
                alertDialog.setCancelable(false);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }

        } else {
            // Permission has already been granted

            try {
                Intent intent = new Intent(this, ScanActivity.class);
                intent.putExtra(ScanConstants.OPEN_INTENT_PREFERENCE, ScanConstants.OPEN_CAMERA);
                startActivityForResult(intent, REQUEST_CODE);
            } catch (Exception e) {
                e.printStackTrace();
                ApplicationConstant.displayMessageDialog(DocumentScanActivity.this, "", e.getMessage().toString().trim());
            }

        }
    }
}
