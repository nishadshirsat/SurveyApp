package com.example.streethawkerssurveyapp.activities;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.streethawkerssurveyapp.BuildConfig;
import com.example.streethawkerssurveyapp.R;
import com.example.streethawkerssurveyapp.database_pack.BankingDetails;
import com.example.streethawkerssurveyapp.database_pack.DocumentsData;
import com.example.streethawkerssurveyapp.database_pack.SurveyDao;
import com.example.streethawkerssurveyapp.database_pack.SurveyDatabase;
import com.example.streethawkerssurveyapp.pending_survey.activities.PendingDocumentsScanActivity;
import com.example.streethawkerssurveyapp.response_pack.OtherDocDetails;
import com.example.streethawkerssurveyapp.response_pack.UpdateSurveyResponse;
import com.example.streethawkerssurveyapp.services.AudioRecordService;
import com.example.streethawkerssurveyapp.services_pack.ApiInterface;
import com.example.streethawkerssurveyapp.services_pack.ApiService;
import com.example.streethawkerssurveyapp.services_pack.ApplicationConstant;
import com.example.streethawkerssurveyapp.services_pack.CustomProgressDialog;
import com.example.streethawkerssurveyapp.utils.PrefUtils;
import com.example.streethawkerssurveyapp.utils.SurveyAppFileProvider;
import com.google.gson.Gson;
import com.labters.documentscanner.ImageCropActivity;
import com.labters.documentscanner.helpers.ScannerConstants;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DocumentsScanActivity extends AppCompatActivity {

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

    Uri photoURI;
    private String photoPath = "";

    private LinearLayout mLinearHead;
    private LinearLayout LinearThree;
    private LinearLayout LinearDocuments;
    private TextView TextAddAnother;
    private String OTHER_DOCUMENT="";
    private EditText dEditOtherDocument;
    private ImageView dImgOtherDocument;
    private Button dBtnUploadDoc;
    private TextView dTextDocName;
    private String OTHER_DOCUMENT_PATH="";

    private boolean Is_Uploaded = false;

    private List<OtherDocDetails> otherDocList = new ArrayList<>();


    private SurveyDatabase surveyDatabase;
    private SurveyDao surveyDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_scan);

        bindView();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("URI NO: "+ApplicationConstant.SurveyId);

        surveyDatabase = SurveyDatabase.getDatabase(DocumentsScanActivity.this);
        surveyDao = surveyDatabase.surveyDao();

        TextAddAnother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Is_Uploaded == false){

                    View viewAdd = LayoutInflater.from(DocumentsScanActivity.this).inflate(R.layout.layout_add_otherdoc, null);

                    dEditOtherDocument = (EditText)viewAdd. findViewById(R.id.EditOtherDocument);
                    dImgOtherDocument = (ImageView)viewAdd. findViewById(R.id.ImgOtherDocument);
                    dBtnUploadDoc = (Button) viewAdd.findViewById(R.id.BtnUploadDoc);
                    dTextDocName = (TextView)viewAdd. findViewById(R.id.TextDocName);

                    dImgOtherDocument.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if (dEditOtherDocument.getText().toString().trim().isEmpty()){
                                dEditOtherDocument.setError("enter document name first");
                                dEditOtherDocument.requestFocus();
                            }else {

                                OTHER_DOCUMENT = dEditOtherDocument.getText().toString().trim();
                                CONTROL = ApplicationConstant.SurveyId + "_"+dEditOtherDocument.getText().toString().trim();
                                startScan();
                            }

                        }
                    });

                    dBtnUploadDoc.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            if (ApplicationConstant.ISLOCALDB) {

                                OtherDocDetails otherDocDetails = new OtherDocDetails(OTHER_DOCUMENT,OTHER_DOCUMENT_PATH);
                                otherDocList.add(otherDocDetails);

                                dTextDocName.setText(OTHER_DOCUMENT+" Stored");
                                dTextDocName.setTextColor(getResources().getColor(R.color.green));
                                dBtnUploadDoc.setVisibility(View.GONE);
                                dEditOtherDocument.setEnabled(false);

                                Is_Uploaded = false;


                            } else if (!ApplicationConstant.isNetworkAvailable(DocumentsScanActivity.this)) {

                                ApplicationConstant.displayMessageDialog(DocumentsScanActivity.this, "No Internet Connection", "Please enable internet connection first to proceed");

                            } else {
                                UploadOtherDocument();

                            }

                        }
                    });

                    LinearDocuments.addView(viewAdd);
                    Is_Uploaded = true;

                }else {

                    ApplicationConstant.displayMessageDialog(DocumentsScanActivity.this,"","Upload "+OTHER_DOCUMENT+" First");

                }


            }
        });

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

            onBackPressed();

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

                if (mLinearHead.getVisibility() == View.VISIBLE) {

                    mLinearHead.setVisibility(View.GONE);
                    LinearOne.setVisibility(View.VISIBLE);
                    LinearTwo.setVisibility(View.GONE);
                    LinearFour.setVisibility(View.GONE);
                    LinearThree.setVisibility(View.GONE);

                }else   if (LinearOne.getVisibility() == View.VISIBLE) {

                    if (validate1()) {

                        if (ApplicationConstant.ISLOCALDB) {

                            LinearOne.setVisibility(View.GONE);
                            LinearTwo.setVisibility(View.VISIBLE);
                            LinearFour.setVisibility(View.GONE);
                            LinearThree.setVisibility(View.GONE);


                        } else if (!ApplicationConstant.isNetworkAvailable(DocumentsScanActivity.this)) {

                            ApplicationConstant.displayMessageDialog(DocumentsScanActivity.this, "No Internet Connection", "Please enable internet connection first to proceed");

                        } else {
                            UploadIdentityProof();

                        }

                    }


                } else if (LinearTwo.getVisibility() == View.VISIBLE) {

                    if (validate2()) {

                        if (ApplicationConstant.ISLOCALDB) {

                            LinearTwo.setVisibility(View.GONE);
                            LinearOne.setVisibility(View.GONE);
                            LinearFour.setVisibility(View.GONE);
                            LinearThree.setVisibility(View.VISIBLE);
                            BtnNext.setText("Submit");
                            stopService(new Intent(DocumentsScanActivity.this, AudioRecordService.class));


                        } else if (!ApplicationConstant.isNetworkAvailable(DocumentsScanActivity.this)) {

                            ApplicationConstant.displayMessageDialog(DocumentsScanActivity.this, "No Internet Connection", "Please enable internet connection first to proceed");

                        } else {
                            Upload_VendingHistoryProof();

                        }


                    }

                }
                else if (LinearThree.getVisibility() == View.VISIBLE) {

                        LinearThree.setVisibility(View.GONE);
                        LinearOne.setVisibility(View.GONE);
                        LinearTwo.setVisibility(View.GONE);
                        LinearFour.setVisibility(View.VISIBLE);


                }
//                else  if (TehBazari_Doc_PATH.trim().isEmpty()){
//                    ApplicationConstant.displayMessageDialog(DocumentScanActivity.this,"","Tehbazari Doc is missing");
//
//                }
                else if (Undertaking_PATH.trim().isEmpty()) {
                    ApplicationConstant.displayMessageDialog(DocumentsScanActivity.this, "", "Undertaking Doc is missing");

                } else if (Acknowledge_PATH.trim().isEmpty()) {
                    ApplicationConstant.displayMessageDialog(DocumentsScanActivity.this, "", "Acknowledge Doc is missing");

                } else if (Comments.trim().isEmpty()) {
                    ApplicationConstant.displayMessageDialog(DocumentsScanActivity.this, "", "Please Enter Comment");

                } else {

                    file_tehjabari = new File(TehBazari_Doc_PATH);

                    file_undertaking = new File(Undertaking_PATH);
                    file_acknowlegement = new File(Acknowledge_PATH);

                    if (ApplicationConstant.ISLOCALDB) {

                        insertDocumentsDetails();

                    } else if (!ApplicationConstant.isNetworkAvailable(DocumentsScanActivity.this)) {

                        ApplicationConstant.displayMessageDialog(DocumentsScanActivity.this, "No Internet Connection", "Please enable internet connection first to proceed");

                    } else {
                        Upload_Recordings();

                    }

                }
            }
        });

    }

    private boolean validate1() {

//        if (!ApplicationConstant.isNetworkAvailable(DocumentsScanActivity.this)) {
//
//            ApplicationConstant.displayMessageDialog(DocumentsScanActivity.this, "No Internet Connection", "Please enable internet connection first to proceed");
//
//            return false;
//        }else

        if (IDENTITY_PROOF_TYPE.trim().equals("Select")) {
            ApplicationConstant.displayMessageDialog(DocumentsScanActivity.this, "", "Select Identity Proof Type");
            return false;
        } else if (IDENTITY_PROOF_FRONT_PATH.trim().isEmpty()) {
            ApplicationConstant.displayMessageDialog(DocumentsScanActivity.this, "", "Capture Identity Proof ");
            return false;

        } else if (!IDENTITY_PROOF_FRONT_PATH.trim().isEmpty()) {
            file_identity_front = new File(IDENTITY_PROOF_FRONT_PATH);

        } else {
            ApplicationConstant.displayMessageDialog(DocumentsScanActivity.this, "", "Identity Proof Doc is missing");
            return false;

        }

        return true;
    }

    private boolean validate2() {
//        if (!ApplicationConstant.isNetworkAvailable(DocumentsScanActivity.this)) {
//
//            ApplicationConstant.displayMessageDialog(DocumentsScanActivity.this, "No Internet Connection", "Please enable internet connection first to proceed");
//
//            return false;
//        }else
        if (VENDING_HISTORY_PROOF_TYPE.trim().equals("Select")) {
            ApplicationConstant.displayMessageDialog(DocumentsScanActivity.this, "", "Select Vending History Proof Type");
            return false;

        } else if (VENDING_HISTORY_FRONT_PROOF_PATH.trim().isEmpty()) {
            ApplicationConstant.displayMessageDialog(DocumentsScanActivity.this, "", "Capture Vending History Proof ");
            return false;

        } else if (!VENDING_HISTORY_FRONT_PROOF_PATH.trim().isEmpty()) {
            file_vending_history_front = new File(VENDING_HISTORY_FRONT_PROOF_PATH);

        } else {
            ApplicationConstant.displayMessageDialog(DocumentsScanActivity.this, "", "Vending History Proof Doc is missing");
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

        TextAddAnother = (TextView) findViewById(R.id.TextAddAnother);
        LinearThree = (LinearLayout) findViewById(R.id.LinearThree);
        LinearDocuments = (LinearLayout) findViewById(R.id.LinearDocuments);
        LinearMain = (LinearLayout) findViewById(R.id.LinearMain);
        mLinearHead = (LinearLayout) findViewById(R.id.LinearHead);
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

        Bitmap bitmap = null;

        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
            bitmap = null;
        }
        System.gc();

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
//           Bitmap bitmap = BitmapFactory.decodeFile (photoPath);
           bitmap = ApplicationConstant.CompressedBitmap(new File(photoPath));

            ScannerConstants.selectedImageBitmap=bitmap;

            startActivityForResult(new Intent(DocumentsScanActivity.this,ImageCropActivity.class),1234);


        }else  if (requestCode == 2 && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();

            String[] filePath = { MediaStore.Images.Media.DATA };

            Cursor c = DocumentsScanActivity.this.getContentResolver().query(selectedImage,filePath, null, null, null);

            c.moveToFirst();

            int columnIndex = c.getColumnIndex(filePath[0]);

            photoPath = c.getString(columnIndex);

            c.close();

            bitmap = ApplicationConstant.CompressedBitmap(new File(photoPath));

            ScannerConstants.selectedImageBitmap=bitmap;

            startActivityForResult(new Intent(DocumentsScanActivity.this,ImageCropActivity.class),1234);


        }else
            if (requestCode == 1234 && resultCode == Activity.RESULT_OK) {
            if (ScannerConstants.selectedImageBitmap != null) {

                writeBitmap(ScannerConstants.selectedImageBitmap,new File(photoPath));

                if (CONTROL.trim().equals(ApplicationConstant.SurveyId + "_" + "IdentityProof_Front")) {
                    IDENTITY_PROOF_FRONT_PATH = photoPath;
                    ImgIdentityProofFront.setImageBitmap(ScannerConstants.selectedImageBitmap);

                }else if (CONTROL.trim().equals(ApplicationConstant.SurveyId + "_" + OTHER_DOCUMENT)) {
                    OTHER_DOCUMENT_PATH = photoPath;
                    dTextDocName.setText(OTHER_DOCUMENT);
                    Is_Uploaded = true;
                    dImgOtherDocument.setImageBitmap(ScannerConstants.selectedImageBitmap);

                } else if (CONTROL.trim().equals(ApplicationConstant.SurveyId + "_" + "IdentityProof_Back")) {
                    IDENTITY_PROOF_BACK_PATH = photoPath;
                    ImgIdentityProofBack.setImageBitmap(ScannerConstants.selectedImageBitmap);

                } else if (CONTROL.trim().equals(ApplicationConstant.SurveyId + "_" + "VendingHistory_Front")) {
                    VENDING_HISTORY_FRONT_PROOF_PATH = photoPath;
                    ImgVendingHistoryFront.setImageBitmap(ScannerConstants.selectedImageBitmap);

                } else if (CONTROL.trim().equals(ApplicationConstant.SurveyId + "_" + "VendingHistory_Back")) {
                    VENDING_HISTORY_BACK_PROOF_PATH = photoPath;
                    ImgVendingHistoryBack.setImageBitmap(ScannerConstants.selectedImageBitmap);

                } else if (CONTROL.trim().equals(ApplicationConstant.SurveyId + "_" + "TehBazari_Doc")) {
                    TehBazari_Doc_PATH = photoPath;
                    ImgTehbaziDoc.setImageBitmap(ScannerConstants.selectedImageBitmap);

                }else if (CONTROL.trim().equals(ApplicationConstant.SurveyId + "_" + "Undertaking")) {
                    Undertaking_PATH = photoPath;
                    ImgSignature.setImageBitmap(ScannerConstants.selectedImageBitmap);

                } else if (CONTROL.trim().equals(ApplicationConstant.SurveyId + "_" + "Acknowledge_Receipt")) {
                    Acknowledge_PATH = photoPath;
                    ImgAckReceipt.setImageBitmap(ScannerConstants.selectedImageBitmap);

                }
             }else {
                ApplicationConstant.displayToastMessage(DocumentsScanActivity.this,"Not Ok");
            }

        }
        else if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
//            Uri uri_image = data.getExtras().getParcelable(ScanConstants.SCANNED_RESULT);
            Uri uri_image = null;


        if (CONTROL.trim().equals(ApplicationConstant.SurveyId + "_" + "Acknowledge_Receipt")) {
                File photoFile = null;
                try {
                    String photoPath = ApplicationConstant.createImageFile(ApplicationConstant.SurveyId + "_" + "Acknowledge_Receipt.png", "Documents", DocumentsScanActivity.this);
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
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out); // bmp is your Bitmap instance
//            out.close();
            // PNG is a lossless format, the compression factor (100) is ignored
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private void Upload_Documents() {
//
//
//        String UNiq_Id = PrefUtils.getFromPrefs(DocumentScanActivity.this, ApplicationConstant.URI_NO_, "");
//        String Contact = PrefUtils.getFromPrefs(DocumentScanActivity.this, ApplicationConstant.CONTACT, "");
//
//        progressDialog = CustomProgressDialog.getDialogue(DocumentScanActivity.this);
//        progressDialog.show();
//
//        Map<String, String> headers = new HashMap<>();
//        headers.put("Authorization", "Bearer " + PrefUtils.getFromPrefs(DocumentScanActivity.this, ApplicationConstant.USERDETAILS.API_KEY, ""));
//
//        RequestBody request_identity_front =
//                RequestBody.create(MediaType.parse("image/png"), file_identity_front);
//
//        RequestBody request_identity_back =
//                RequestBody.create(MediaType.parse("image/png"), file_identity_back);
//
//        RequestBody request_vending_history_front =
//                RequestBody.create(MediaType.parse("image/png"), file_vending_history_front);
//
//        RequestBody request_vending_history_back =
//                RequestBody.create(MediaType.parse("image/png"), file_vending_history_back);
//
//        RequestBody request_tehjabari =
//                RequestBody.create(MediaType.parse("image/png"), file_tehjabari);
//
//        RequestBody request_undertaking =
//                RequestBody.create(MediaType.parse("image/png"), file_undertaking);
//
//        RequestBody request_acknowlegement =
//                RequestBody.create(MediaType.parse("image/png"), file_acknowlegement);
//
//
//// MultipartBody.Part is used to send also the actual file name
//        MultipartBody.Part body_identity_front =
//                MultipartBody.Part.createFormData("identity_proof_documents_front", file_identity_front.getName(), request_identity_front);
//
//
//        MultipartBody.Part body_vending_history_front =
//                MultipartBody.Part.createFormData("vending_history_proof_documents_front", file_vending_history_front.getName(), request_vending_history_front);
//
//
//        MultipartBody.Part body_identity_back = null;
//        if (IDENTITY_PROOF_BACK_PATH.trim().isEmpty()) {
//            body_identity_back = null;
//        } else {
//
//            body_identity_back = MultipartBody.Part.createFormData("identity_proof_documents_back", file_identity_back.getName(), request_identity_back);
//
//        }
//
//        MultipartBody.Part body_vending_history_back = null;
//        if (VENDING_HISTORY_BACK_PROOF_PATH.trim().isEmpty()) {
//            body_vending_history_back = null;
//        } else {
//
//            body_vending_history_back = MultipartBody.Part.createFormData("vending_history_proof_documents_back", file_vending_history_back.getName(), request_vending_history_back);
//
//        }
//
//        MultipartBody.Part body_tehbazari_document = null;
//        if (TehBazari_Doc_PATH.trim().isEmpty()) {
//            body_tehbazari_document = null;
//        } else {
//
//            body_tehbazari_document = MultipartBody.Part.createFormData("allotment_of_tehbazari_document", file_tehjabari.getName(), request_tehjabari);
//
//        }
//
//        MultipartBody.Part body_undertaking =
//                MultipartBody.Part.createFormData("undertaking_by_the_applicant", file_undertaking.getName(), request_undertaking);
//
//
//        MultipartBody.Part body_acknowlegement =
//                MultipartBody.Part.createFormData("acknowledgement_receipt", file_acknowlegement.getName(), request_acknowlegement);
//
//        String CORPORATION =   PrefUtils.getFromPrefs(DocumentScanActivity.this,ApplicationConstant.CORPORATION,"");
//        String ZONE =  PrefUtils.getFromPrefs(DocumentScanActivity.this,ApplicationConstant.ZONE,"");
//        String WARD =  PrefUtils.getFromPrefs(DocumentScanActivity.this,ApplicationConstant.WARD,"");
//
//        RequestBody CORPORATION_ = RequestBody.create(MediaType.parse("multipart/form-data"), CORPORATION);
//        RequestBody ZONE_ = RequestBody.create(MediaType.parse("multipart/form-data"), ZONE);
//        RequestBody WARD_ = RequestBody.create(MediaType.parse("multipart/form-data"), WARD);
//
//
//        RequestBody URI_NO_ = RequestBody.create(MediaType.parse("multipart/form-data"), UNiq_Id);
//        RequestBody Contact_ = RequestBody.create(MediaType.parse("multipart/form-data"), Contact);
//        RequestBody COMMENTS_ = RequestBody.create(MediaType.parse("multipart/form-data"), Comments);
//        RequestBody SURVEY_STATUS = RequestBody.create(MediaType.parse("multipart/form-data"), "1");
//        RequestBody IDENTITY_PROOF_TYPE_ = RequestBody.create(MediaType.parse("multipart/form-data"), IDENTITY_PROOF_TYPE);
//        RequestBody VENDING_HISTORY_PROOF_TYPE_ = RequestBody.create(MediaType.parse("multipart/form-data"), VENDING_HISTORY_PROOF_TYPE);
//
//
//        ApiInterface apiservice = ApiService.getApiClient().create(ApiInterface.class);
//        Call<UpdateSurveyResponse> call = apiservice.getUpdateDocuments(headers,
//                URI_NO_,
//                Contact_,
//                CORPORATION_,
//                ZONE_,
//                WARD_,
//                IDENTITY_PROOF_TYPE_,
//                VENDING_HISTORY_PROOF_TYPE_,
//                SURVEY_STATUS,
//                COMMENTS_,
//                body_identity_front,
//                body_identity_back,
//                body_vending_history_front,
//                body_vending_history_back,
//                body_tehbazari_document,
//                body_acknowlegement,
//                body_undertaking
//
//        );
//
//        call.enqueue(new Callback<UpdateSurveyResponse>() {
//            @Override
//            public void onResponse(Call<UpdateSurveyResponse> call, Response<UpdateSurveyResponse> response) {
//
//                if (progressDialog != null && progressDialog.isShowing())
//                    progressDialog.dismiss();
//
//                if (response.body() != null) {
//
//                    if (response.body().isStatus()) {
//
//                        AlertDialog.Builder builder = new AlertDialog.Builder(DocumentScanActivity.this);
//                        builder.setTitle("Document Details");
//                        builder.setMessage("Saved successfully");
//                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                dialogInterface.dismiss();
//                                startActivity(new Intent(DocumentScanActivity.this, DashboardActivity.class));
//                                finish();
//
//                            }
//                        });
//
//                        AlertDialog alertDialog = builder.create();
//                        alertDialog.setCancelable(false);
//                        alertDialog.setCanceledOnTouchOutside(false);
//                        alertDialog.show();
//
////                        ApplicationConstant.displayToastMessage(DocumentScanActivity.this,
////                                "Vending Details saved successfully");
//
//
//                    } else {
//
//                        ApplicationConstant.displayMessageDialog(DocumentScanActivity.this,
//                                "Response",
//                                response.body().getMessage());
//                    }
//
//                } else {
//
//                    try {
//                        ApplicationConstant.displayMessageDialog(DocumentScanActivity.this,
//                                "Response",
//                                response.errorBody().string());
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<UpdateSurveyResponse> call, Throwable t) {
//
//                if (progressDialog != null && progressDialog.isShowing())
//                    progressDialog.dismiss();
//                ApplicationConstant.displayMessageDialog(DocumentScanActivity.this, "Response", t.getMessage().toString());
//
//            }
//        });
//    }

    private void Upload_Documents() {


        String UNiq_Id = PrefUtils.getFromPrefs(DocumentsScanActivity.this, ApplicationConstant.URI_NO_, "");
        String Contact = PrefUtils.getFromPrefs(DocumentsScanActivity.this, ApplicationConstant.CONTACT, "");

        progressDialog = CustomProgressDialog.getDialogue(DocumentsScanActivity.this);
        progressDialog.show();

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + PrefUtils.getFromPrefs(DocumentsScanActivity.this, ApplicationConstant.USERDETAILS.API_KEY, ""));


        RequestBody request_tehjabari =
                RequestBody.create(MediaType.parse("image/png"), file_tehjabari);

        RequestBody request_undertaking =
                RequestBody.create(MediaType.parse("image/png"), file_undertaking);

        RequestBody request_acknowlegement =
                RequestBody.create(MediaType.parse("image/png"), file_acknowlegement);


// MultipartBody.Part is used to send also the actual file name


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

        String CORPORATION =   PrefUtils.getFromPrefs(DocumentsScanActivity.this,ApplicationConstant.CORPORATION,"");
        String ZONE =  PrefUtils.getFromPrefs(DocumentsScanActivity.this,ApplicationConstant.ZONE,"");
        String WARD =  PrefUtils.getFromPrefs(DocumentsScanActivity.this,ApplicationConstant.WARD,"");

        RequestBody CORPORATION_ = RequestBody.create(MediaType.parse("multipart/form-data"), CORPORATION);
        RequestBody ZONE_ = RequestBody.create(MediaType.parse("multipart/form-data"), ZONE);
        RequestBody WARD_ = RequestBody.create(MediaType.parse("multipart/form-data"), WARD);


        RequestBody URI_NO_ = RequestBody.create(MediaType.parse("multipart/form-data"), UNiq_Id);
        RequestBody Contact_ = RequestBody.create(MediaType.parse("multipart/form-data"), Contact);
        RequestBody COMMENTS_ = RequestBody.create(MediaType.parse("multipart/form-data"), Comments);
        RequestBody SURVEY_STATUS = RequestBody.create(MediaType.parse("multipart/form-data"), "1");


        ApiInterface apiservice = ApiService.getApiClient().create(ApiInterface.class);
        Call<UpdateSurveyResponse> call = apiservice.getUpdateDocuments(headers,
                URI_NO_,
                Contact_,
                CORPORATION_,
                ZONE_,
                WARD_,
                SURVEY_STATUS,
                COMMENTS_,
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

                        AlertDialog.Builder builder = new AlertDialog.Builder(DocumentsScanActivity.this);
                        builder.setTitle("Document Details");
                        builder.setMessage("Saved successfully");
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                startActivity(new Intent(DocumentsScanActivity.this, DashboardActivity.class));
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

                        ApplicationConstant.displayMessageDialog(DocumentsScanActivity.this,
                                "Response",
                                response.body().getMessage());
                    }

                } else {

                    try {
                        ApplicationConstant.displayMessageDialog(DocumentsScanActivity.this,
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
                ApplicationConstant.displayMessageDialog(DocumentsScanActivity.this, "Response", getString(R.string.net_speed_problem));

            }
        });
    }

    private void Upload_VendingHistoryProof() {

        file_vending_history_back = new File(VENDING_HISTORY_BACK_PROOF_PATH);

        String UNiq_Id = PrefUtils.getFromPrefs(DocumentsScanActivity.this, ApplicationConstant.URI_NO_, "");

        progressDialog = CustomProgressDialog.getDialogue(DocumentsScanActivity.this);
        progressDialog.show();

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + PrefUtils.getFromPrefs(DocumentsScanActivity.this, ApplicationConstant.USERDETAILS.API_KEY, ""));


        RequestBody request_vending_history_front =
                RequestBody.create(MediaType.parse("image/png"), file_vending_history_front);

        RequestBody request_vending_history_back =
                RequestBody.create(MediaType.parse("image/png"), file_vending_history_back);




// MultipartBody.Part is used to send also the actual file name


        MultipartBody.Part body_vending_history_front =
                MultipartBody.Part.createFormData("vending_history_proof_documents_front", file_vending_history_front.getName(), request_vending_history_front);




        MultipartBody.Part body_vending_history_back = null;
        if (VENDING_HISTORY_BACK_PROOF_PATH.trim().isEmpty()) {
            body_vending_history_back = null;
        } else {

            body_vending_history_back = MultipartBody.Part.createFormData("vending_history_proof_documents_back", file_vending_history_back.getName(), request_vending_history_back);

        }


        String CORPORATION =   PrefUtils.getFromPrefs(DocumentsScanActivity.this,ApplicationConstant.CORPORATION,"");
        String ZONE =  PrefUtils.getFromPrefs(DocumentsScanActivity.this,ApplicationConstant.ZONE,"");
        String WARD =  PrefUtils.getFromPrefs(DocumentsScanActivity.this,ApplicationConstant.WARD,"");

        RequestBody CORPORATION_ = RequestBody.create(MediaType.parse("multipart/form-data"), CORPORATION);
        RequestBody ZONE_ = RequestBody.create(MediaType.parse("multipart/form-data"), ZONE);
        RequestBody WARD_ = RequestBody.create(MediaType.parse("multipart/form-data"), WARD);


        RequestBody URI_NO_ = RequestBody.create(MediaType.parse("multipart/form-data"), UNiq_Id);
        RequestBody VENDING_HISTORY_PROOF_TYPE_ = RequestBody.create(MediaType.parse("multipart/form-data"), VENDING_HISTORY_PROOF_TYPE);


        ApiInterface apiservice = ApiService.getApiClient().create(ApiInterface.class);
        Call<UpdateSurveyResponse> call = apiservice.UploadVendingHistoryProof(headers,
                URI_NO_,
                CORPORATION_,
                ZONE_,
                WARD_,
                VENDING_HISTORY_PROOF_TYPE_,
                body_vending_history_front,
                body_vending_history_back

        );

        call.enqueue(new Callback<UpdateSurveyResponse>() {
            @Override
            public void onResponse(Call<UpdateSurveyResponse> call, Response<UpdateSurveyResponse> response) {

                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();

                if (response.body() != null) {

                    if (response.body().isStatus()) {

                        LinearTwo.setVisibility(View.GONE);
                        LinearOne.setVisibility(View.GONE);
                        LinearFour.setVisibility(View.GONE);
                        LinearThree.setVisibility(View.VISIBLE);
                        BtnNext.setText("Submit");
                        stopService(new Intent(DocumentsScanActivity.this, AudioRecordService.class));

                    } else {

                        ApplicationConstant.displayMessageDialog(DocumentsScanActivity.this,
                                "Response",
                                response.body().getMessage());
                    }

                } else {

                    try {
                        ApplicationConstant.displayMessageDialog(DocumentsScanActivity.this,
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
                ApplicationConstant.displayMessageDialog(DocumentsScanActivity.this, "Response", getString(R.string.net_speed_problem));

            }
        });
    }

    private void UploadIdentityProof() {

        file_identity_back = new File(IDENTITY_PROOF_BACK_PATH);

        String UNiq_Id = PrefUtils.getFromPrefs(DocumentsScanActivity.this, ApplicationConstant.URI_NO_, "");

        progressDialog = CustomProgressDialog.getDialogue(DocumentsScanActivity.this);
        progressDialog.show();

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + PrefUtils.getFromPrefs(DocumentsScanActivity.this, ApplicationConstant.USERDETAILS.API_KEY, ""));

        RequestBody request_identity_front =
                RequestBody.create(MediaType.parse("image/png"), file_identity_front);

        RequestBody request_identity_back =
                RequestBody.create(MediaType.parse("image/png"), file_identity_back);



// MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body_identity_front =
                MultipartBody.Part.createFormData("identity_proof_documents_front", file_identity_front.getName(), request_identity_front);



        MultipartBody.Part body_identity_back = null;
        if (IDENTITY_PROOF_BACK_PATH.trim().isEmpty()) {
            body_identity_back = null;
        } else {

            body_identity_back = MultipartBody.Part.createFormData("identity_proof_documents_back", file_identity_back.getName(), request_identity_back);

        }



        String CORPORATION =   PrefUtils.getFromPrefs(DocumentsScanActivity.this,ApplicationConstant.CORPORATION,"");
        String ZONE =  PrefUtils.getFromPrefs(DocumentsScanActivity.this,ApplicationConstant.ZONE,"");
        String WARD =  PrefUtils.getFromPrefs(DocumentsScanActivity.this,ApplicationConstant.WARD,"");

        RequestBody CORPORATION_ = RequestBody.create(MediaType.parse("multipart/form-data"), CORPORATION);
        RequestBody ZONE_ = RequestBody.create(MediaType.parse("multipart/form-data"), ZONE);
        RequestBody WARD_ = RequestBody.create(MediaType.parse("multipart/form-data"), WARD);


        RequestBody URI_NO_ = RequestBody.create(MediaType.parse("multipart/form-data"), UNiq_Id);
        RequestBody IDENTITY_PROOF_TYPE_ = RequestBody.create(MediaType.parse("multipart/form-data"), IDENTITY_PROOF_TYPE);


        ApiInterface apiservice = ApiService.getApiClient().create(ApiInterface.class);
        Call<UpdateSurveyResponse> call = apiservice.UploadIdentityProof(headers,
                URI_NO_,
                CORPORATION_,
                ZONE_,
                WARD_,
                IDENTITY_PROOF_TYPE_,
                body_identity_front,
                body_identity_back

        );

        call.enqueue(new Callback<UpdateSurveyResponse>() {
            @Override
            public void onResponse(Call<UpdateSurveyResponse> call, Response<UpdateSurveyResponse> response) {

                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();

                if (response.body() != null) {

                    if (response.body().isStatus()) {

                        LinearOne.setVisibility(View.GONE);
                        LinearTwo.setVisibility(View.VISIBLE);
                        LinearFour.setVisibility(View.GONE);
                        LinearThree.setVisibility(View.GONE);

                    } else {

                        ApplicationConstant.displayMessageDialog(DocumentsScanActivity.this,
                                "Response",
                                response.body().getMessage());
                    }

                } else {

                    try {
                        ApplicationConstant.displayMessageDialog(DocumentsScanActivity.this,
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
                ApplicationConstant.displayMessageDialog(DocumentsScanActivity.this, "Response", getString(R.string.net_speed_problem));

            }
        });
    }

    private void UploadOtherDocument() {

        File OtherDocFile = new File(OTHER_DOCUMENT_PATH);

        String UNiq_Id = PrefUtils.getFromPrefs(DocumentsScanActivity.this, ApplicationConstant.URI_NO_, "");

        progressDialog = CustomProgressDialog.getDialogue(DocumentsScanActivity.this);
        progressDialog.show();

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + PrefUtils.getFromPrefs(DocumentsScanActivity.this, ApplicationConstant.USERDETAILS.API_KEY, ""));

        RequestBody request_other_document =
                RequestBody.create(MediaType.parse("image/png"), OtherDocFile);


// MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body_other_document =
                MultipartBody.Part.createFormData("document", OtherDocFile.getName(), request_other_document);



//        MultipartBody.Part body_identity_back = null;
//        if (IDENTITY_PROOF_BACK_PATH.trim().isEmpty()) {
//            body_identity_back = null;
//        } else {
//
//            body_identity_back = MultipartBody.Part.createFormData("identity_proof_documents_back", file_identity_back.getName(), request_identity_back);
//
//        }



        String CORPORATION =   PrefUtils.getFromPrefs(DocumentsScanActivity.this,ApplicationConstant.CORPORATION,"");
        String ZONE =  PrefUtils.getFromPrefs(DocumentsScanActivity.this,ApplicationConstant.ZONE,"");
        String WARD =  PrefUtils.getFromPrefs(DocumentsScanActivity.this,ApplicationConstant.WARD,"");

        RequestBody CORPORATION_ = RequestBody.create(MediaType.parse("multipart/form-data"), CORPORATION);
        RequestBody ZONE_ = RequestBody.create(MediaType.parse("multipart/form-data"), ZONE);
        RequestBody WARD_ = RequestBody.create(MediaType.parse("multipart/form-data"), WARD);


        RequestBody URI_NO_ = RequestBody.create(MediaType.parse("multipart/form-data"), UNiq_Id);
        RequestBody OTHER_DOCUMENT_ = RequestBody.create(MediaType.parse("multipart/form-data"), OTHER_DOCUMENT);


        ApiInterface apiservice = ApiService.getApiClient().create(ApiInterface.class);
        Call<UpdateSurveyResponse> call = apiservice.UploadOtherDocument(headers,
                URI_NO_,
                CORPORATION_,
                ZONE_,
                WARD_,
                OTHER_DOCUMENT_,
                body_other_document

        );

        call.enqueue(new Callback<UpdateSurveyResponse>() {
            @Override
            public void onResponse(Call<UpdateSurveyResponse> call, Response<UpdateSurveyResponse> response) {

                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();

                if (response.body() != null) {

                    if (response.body().isStatus()) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(DocumentsScanActivity.this);
                        builder.setTitle(OTHER_DOCUMENT);
                        builder.setMessage("Uploaded successfully");
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                dTextDocName.setText(OTHER_DOCUMENT+" Uploaded");
                                dTextDocName.setTextColor(getResources().getColor(R.color.green));
                                dBtnUploadDoc.setVisibility(View.GONE);
                                dEditOtherDocument.setEnabled(false);

                                Is_Uploaded = false;

                            }
                        });

                        AlertDialog alertDialog = builder.create();
                        alertDialog.setCancelable(false);
                        alertDialog.setCanceledOnTouchOutside(false);
                        alertDialog.show();
                    } else {

                        ApplicationConstant.displayMessageDialog(DocumentsScanActivity.this,
                                "Response",
                                response.body().getMessage());
                    }

                } else {

                    try {
                        ApplicationConstant.displayMessageDialog(DocumentsScanActivity.this,
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
                ApplicationConstant.displayMessageDialog(DocumentsScanActivity.this, "Response", getString(R.string.net_speed_problem));

            }
        });
    }


    private void Upload_Recordings() {

        String recordingFile = PrefUtils.getFromPrefs(DocumentsScanActivity.this, ApplicationConstant.RECORDING, "");

//        String recordingFile = null;
//        try {
//            recordingFile = ApplicationConstant.createImageFile("6.0_surveyer.3gp","Recordings", DocumentScanActivity.this);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        file_recording = new File(recordingFile);

        String UNiq_Id = PrefUtils.getFromPrefs(DocumentsScanActivity.this, ApplicationConstant.URI_NO_, "");

        progressDialog = CustomProgressDialog.getDialogue(DocumentsScanActivity.this);
        progressDialog.show();

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + PrefUtils.getFromPrefs(DocumentsScanActivity.this, ApplicationConstant.USERDETAILS.API_KEY, ""));

        Uri recordUri = SurveyAppFileProvider.getUriForFile(DocumentsScanActivity.this,
                BuildConfig.APPLICATION_ID + ".android.fileprovider",
                file_recording);

        RequestBody request_recording =
                RequestBody.create(MediaType.parse(getContentResolver().getType(recordUri)), file_recording);


// MultipartBody.Part is used to send also the actual file name

        MultipartBody.Part body_file5 =
                MultipartBody.Part.createFormData("recording", file_recording.getName(), request_recording);

        RequestBody URI_NO_ = RequestBody.create(MediaType.parse("multipart/form-data"), UNiq_Id);

        String CORPORATION =   PrefUtils.getFromPrefs(DocumentsScanActivity.this,ApplicationConstant.CORPORATION,"");
        String ZONE =  PrefUtils.getFromPrefs(DocumentsScanActivity.this,ApplicationConstant.ZONE,"");
        String WARD =  PrefUtils.getFromPrefs(DocumentsScanActivity.this,ApplicationConstant.WARD,"");

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


                        ApplicationConstant.displayToastMessage(DocumentsScanActivity.this,
                                "Recording saved successfully");
                        Upload_Documents();


                    } else {

                        ApplicationConstant.displayToastMessage(DocumentsScanActivity.this,
                                response.body().getMessage());

                        Upload_Documents();

                    }

                } else {

                    try {
                        ApplicationConstant.displayToastMessage(DocumentsScanActivity.this,
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
                ApplicationConstant.displayToastMessage(DocumentsScanActivity.this,  getString(R.string.net_speed_problem));
                Upload_Documents();

            }
        });
    }

    public void checkForPermissions() {
        if (ContextCompat.checkSelfPermission(DocumentsScanActivity.this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(DocumentsScanActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(DocumentsScanActivity.this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(DocumentsScanActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(DocumentsScanActivity.this,
                Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(DocumentsScanActivity.this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(DocumentsScanActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(DocumentsScanActivity.this,
                Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(DocumentsScanActivity.this,
                Manifest.permission.MODIFY_AUDIO_SETTINGS)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(DocumentsScanActivity.this,
                    Manifest.permission.CAMERA) && ActivityCompat.shouldShowRequestPermissionRationale(DocumentsScanActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(DocumentsScanActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(DocumentsScanActivity.this,
                    Manifest.permission.READ_PHONE_STATE) && ActivityCompat.shouldShowRequestPermissionRationale(DocumentsScanActivity.this,
                    Manifest.permission.READ_CONTACTS) && ActivityCompat.shouldShowRequestPermissionRationale(DocumentsScanActivity.this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) && ActivityCompat.shouldShowRequestPermissionRationale(DocumentsScanActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION) && ActivityCompat.shouldShowRequestPermissionRationale(DocumentsScanActivity.this,
                    Manifest.permission.RECORD_AUDIO) && ActivityCompat.shouldShowRequestPermissionRationale(DocumentsScanActivity.this,
                    Manifest.permission.MODIFY_AUDIO_SETTINGS)) {
                // Show an explanation to the user asynchronously -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

//              Toast.makeText(DocumentScanActivity.this,"WAITING FOR USER RESPONSE",Toast.LENGTH_SHORT).show();

                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(DocumentsScanActivity.this);
                builder.setTitle("Permissions Needed");
                builder.setMessage("Want to access your camera and storage to set your profile");
                builder.setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        alertDialog.dismiss();

                        ActivityCompat.requestPermissions(DocumentsScanActivity.this,
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
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(DocumentsScanActivity.this);
                builder.setTitle("Permissions Needed");
                builder.setMessage("Want to access your camera and storage to set your profile");
                builder.setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog.dismiss();

                        ActivityCompat.requestPermissions(DocumentsScanActivity.this,
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

            selectPerticular();

//            try {
//                Intent intent = new Intent(this, ScanActivity.class);
//                intent.putExtra(ScanConstants.OPEN_INTENT_PREFERENCE, ScanConstants.OPEN_CAMERA);
//                startActivityForResult(intent, REQUEST_CODE);
//            } catch (Exception e) {
//                e.printStackTrace();
//                ApplicationConstant.displayMessageDialog(DocumentsScanActivity.this, "", e.getMessage().toString().trim());
//            }

        }
    }

    private void selectPerticular() {

        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };

        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(DocumentsScanActivity.this);

        builder.setTitle("Add Photo!");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo"))
                {

                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//          startActivityForResult(takePictureIntent, 1);

                    // Ensure that there's a camera activity to handle the intent
                    if (takePictureIntent.resolveActivity(DocumentsScanActivity.this.getPackageManager()) != null) {
                        // Create the File where the photo should go
                        File photoFile = null;
                        try {
                            photoPath = ApplicationConstant.createImageFile(CONTROL+".jpeg", "Documents", DocumentsScanActivity.this);
                            photoFile = new File(photoPath);
                        } catch (IOException ex) {
                            // Error occurred while creating the File
                        }
                        // Continue only if the File was successfully created
                        if (photoFile != null) {
                            photoURI = SurveyAppFileProvider.getUriForFile(DocumentsScanActivity.this,
                                    BuildConfig.APPLICATION_ID + ".android.fileprovider",
                                    photoFile);
                            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                            startActivityForResult(takePictureIntent, 1);

                        }
                    }
                }

                else if (options[item].equals("Choose from Gallery"))
                {

                    Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);


                }

                else if (options[item].equals("Cancel")) {

                    dialog.dismiss();
                }
            }
        });

        builder.show();
    }




//    private Bitmap decodeFile(File f) {
//        Bitmap b = null;
//
//        //Decode image size
//        BitmapFactory.Options o = new BitmapFactory.Options();
//        o.inJustDecodeBounds = true;
//
//        FileInputStream fis = null;
//        try {
//            fis = new FileInputStream(f);
//            BitmapFactory.decodeStream(fis, null, o);
//            fis.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        int IMAGE_MAX_SIZE = 1024;
//        int scale = 1;
//        if (o.outHeight > IMAGE_MAX_SIZE || o.outWidth > IMAGE_MAX_SIZE) {
//            scale = (int) Math.pow(2, (int) Math.ceil(Math.log(IMAGE_MAX_SIZE /
//                    (double) Math.max(o.outHeight, o.outWidth)) / Math.log(0.5)));
//        }
//
//        //Decode with inSampleSize
//        BitmapFactory.Options o2 = new BitmapFactory.Options();
//        o2.inSampleSize = scale;
//        try {
//            fis = new FileInputStream(f);
//            b = BitmapFactory.decodeStream(fis, null, o2);
//            fis.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        Log.d(TAG, "Width :" + b.getWidth() + " Height :" + b.getHeight());
//
//        destFile = new File(file, "img_"
//                + dateFormatter.format(new Date()).toString() + ".png");
//        try {
//            FileOutputStream out = new FileOutputStream(destFile);
//            b.compress(Bitmap.CompressFormat.PNG, 100, out);
//            out.flush();
//            out.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return b;
//    }


    @Override
    public void onBackPressed() {

        if (LinearFour.getVisibility() == View.VISIBLE) {

            LinearFour.setVisibility(View.GONE);
            LinearOne.setVisibility(View.GONE);
            LinearThree.setVisibility(View.VISIBLE);
            LinearTwo.setVisibility(View.GONE);

        }
         else if (LinearThree.getVisibility() == View.VISIBLE) {

                    LinearFour.setVisibility(View.GONE);
                    LinearOne.setVisibility(View.GONE);
                    LinearThree.setVisibility(View.GONE);
                    LinearTwo.setVisibility(View.VISIBLE);

         }
        else if (LinearTwo.getVisibility() == View.VISIBLE) {

            LinearFour.setVisibility(View.GONE);
            LinearOne.setVisibility(View.VISIBLE);
            LinearThree.setVisibility(View.GONE);
            LinearTwo.setVisibility(View.GONE);

        }  else if (LinearOne.getVisibility() == View.VISIBLE) {

            LinearFour.setVisibility(View.GONE);
            LinearOne.setVisibility(View.GONE);
            LinearThree.setVisibility(View.GONE);
            mLinearHead.setVisibility(View.VISIBLE);
            LinearTwo.setVisibility(View.GONE);

        } else {

            super.onBackPressed();

        }

    }

    public void insertDocumentsDetails() {

        String recordingFile = PrefUtils.getFromPrefs(DocumentsScanActivity.this, ApplicationConstant.RECORDING, "");

        String LocalId  = PrefUtils.getFromPrefs(DocumentsScanActivity.this,ApplicationConstant.LOCAL_SURVEYID,"");

        String json_otherDoc = new Gson().toJson(otherDocList);



        DocumentsData documentsData = new DocumentsData(
                LocalId,
                IDENTITY_PROOF_TYPE,
                IDENTITY_PROOF_FRONT_PATH,
                IDENTITY_PROOF_BACK_PATH,
                VENDING_HISTORY_PROOF_TYPE,
                VENDING_HISTORY_FRONT_PROOF_PATH,
                VENDING_HISTORY_BACK_PROOF_PATH,
                TehBazari_Doc_PATH,
                Undertaking_PATH,
                Acknowledge_PATH,
                json_otherDoc,
                Comments,
                recordingFile
               );

        new InsertAsyncTask(surveyDao).execute(documentsData);

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(DocumentsScanActivity.this);
        builder.setTitle("Document Details");
        builder.setMessage("Saved successfully in local db");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

                startActivity(new Intent(DocumentsScanActivity.this, DashboardActivity.class));
                finish();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }

    private class InsertAsyncTask extends AsyncTask<DocumentsData, Void, Void> {
        SurveyDao surveyDao;

        public InsertAsyncTask(SurveyDao surveyDao) {
            this.surveyDao = surveyDao;
        }

        @Override
        protected Void doInBackground(DocumentsData... documentsData) {
            surveyDao.insertDocumentsDetails(documentsData[0]);
            return null;
        }
    }

}
