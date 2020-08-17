package com.example.streethawkerssurveyapp.local_storage_pack.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.example.streethawkerssurveyapp.BuildConfig;
import com.example.streethawkerssurveyapp.R;
import com.example.streethawkerssurveyapp.activities.DashboardActivity;
import com.example.streethawkerssurveyapp.activities.PersonalDetailsActivity;
import com.example.streethawkerssurveyapp.activities.StartSurveyModeActivity;
import com.example.streethawkerssurveyapp.local_storage_pack.activities.LocalSurveyListActivity;
import com.example.streethawkerssurveyapp.local_storage_pack.activities.LocalSurveyListActivity;
import com.example.streethawkerssurveyapp.local_storage_pack.activities.LocalSurveyListActivity;
import com.example.streethawkerssurveyapp.local_storage_pack.activities.LocalSurveyListActivity;
import com.example.streethawkerssurveyapp.local_storage_pack.activities.LocalSurveyListActivity;
import com.example.streethawkerssurveyapp.database_pack.BankingDetails;
import com.example.streethawkerssurveyapp.database_pack.DocumentsData;
import com.example.streethawkerssurveyapp.database_pack.FamilyDetails;
import com.example.streethawkerssurveyapp.database_pack.VendingDetails;
import com.example.streethawkerssurveyapp.local_storage_pack.activities.LocalSurveyListActivity;
import com.example.streethawkerssurveyapp.local_storage_pack.activities.LocalSurveyListActivity;
import com.example.streethawkerssurveyapp.local_storage_pack.activities.LocalSurveyListActivity;
import com.example.streethawkerssurveyapp.local_storage_pack.activities.LocalSurveyListActivity;
import com.example.streethawkerssurveyapp.activities.ScannedBarcodeActivity;
import com.example.streethawkerssurveyapp.local_storage_pack.activities.LocalSurveyListActivity;
import com.example.streethawkerssurveyapp.database_pack.PersonalDetails;
import com.example.streethawkerssurveyapp.database_pack.SurveyDao;
import com.example.streethawkerssurveyapp.database_pack.SurveyDatabase;
import com.example.streethawkerssurveyapp.local_storage_pack.adapters.ViewLocalSurveyAdapter;
import com.example.streethawkerssurveyapp.local_storage_pack.activities.LocalSurveyListActivity;
import com.example.streethawkerssurveyapp.pojo_class.FamilyMembers;
import com.example.streethawkerssurveyapp.response_pack.OtherDocDetails;
import com.example.streethawkerssurveyapp.response_pack.SurveyResponse;
import com.example.streethawkerssurveyapp.response_pack.UpdateSurveyResponse;
import com.example.streethawkerssurveyapp.services.AudioRecordService;
import com.example.streethawkerssurveyapp.services_pack.ApiInterface;
import com.example.streethawkerssurveyapp.services_pack.ApiService;
import com.example.streethawkerssurveyapp.services_pack.ApplicationConstant;
import com.example.streethawkerssurveyapp.services_pack.CustomProgressDialog;
import com.example.streethawkerssurveyapp.utils.PrefUtils;
import com.example.streethawkerssurveyapp.utils.SurveyAppFileProvider;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import io.sentry.core.protocol.App;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocalSurveyListActivity extends AppCompatActivity implements ViewLocalSurveyAdapter.UploadAndRefresh {

    private androidx.recyclerview.widget.RecyclerView mRecycler_view;

    private  ViewLocalSurveyAdapter viewSurveyAdapter;
    private SurveyDatabase surveyDatabase;
    private SurveyDao surveyDao;
    private List<PersonalDetails> allSurveys = new ArrayList<>();

    private ProgressDialog progressDialog;

    private PersonalDetails surveyData;
    private FamilyDetails familyData;
    private VendingDetails vendingData;
    private BankingDetails bankingData;
    private DocumentsData documentsDetails;
    private String OtherDocJson = "";
    private List<OtherDocDetails> listOtherDoc = new ArrayList<>();
    private int Count = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_surveylist);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Local Survey List");

        surveyDatabase = SurveyDatabase.getDatabase(LocalSurveyListActivity.this);
        surveyDao = surveyDatabase.surveyDao();

        mRecycler_view = findViewById(R.id.recycler_view);


        mRecycler_view.setLayoutManager(new LinearLayoutManager(this));
        viewSurveyAdapter = new ViewLocalSurveyAdapter(this);
        mRecycler_view.setAdapter(viewSurveyAdapter);
        viewSurveyAdapter.setListner(this);

        getAllRoomData();


    }



    @Override
    public void uploadData(PersonalDetails surveyData) {

        if (!ApplicationConstant.isNetworkAvailable(LocalSurveyListActivity.this)) {

            ApplicationConstant.displayMessageDialog(LocalSurveyListActivity.this,  "Netwrok Issue !","No Internet Connection! Enable internet to upload survey");


        }else {

            this.surveyData = surveyData;

            progressDialog = CustomProgressDialog.getDialogue(LocalSurveyListActivity.this);
            progressDialog.show();

            getFamilyDetails();
        }


    }

    public void getAllRoomData(){

        new InsertGetDataTask(surveyDao).execute();

    }

    private class InsertGetDataTask extends AsyncTask<Void, Void, List<PersonalDetails>> {
        SurveyDao surveyDao;

        public InsertGetDataTask(SurveyDao surveyDao) {
            this.surveyDao = surveyDao;
        }

        @Override
        protected List<PersonalDetails> doInBackground(Void... voids) {
            List<PersonalDetails> allData = surveyDao.getAllPersonalData();
            return allData;
        }

        @Override
        protected void onPostExecute(List<PersonalDetails> personalDetails) {
            viewSurveyAdapter.setList(personalDetails);


        }
    }

    public void getFamilyDetails(){

        new GetFamilyDetailsTask(surveyDao).execute();

    }

    private class GetFamilyDetailsTask extends AsyncTask<Void, Void, List<FamilyDetails>> {
        SurveyDao surveyDao;

        public GetFamilyDetailsTask(SurveyDao surveyDao) {
            this.surveyDao = surveyDao;
        }

        @Override
        protected List<FamilyDetails> doInBackground(Void... voids) {
            List<FamilyDetails> allData = surveyDao.getAllFamilyData(surveyData.getSurvey_id());
            return allData;
        }

        @Override
        protected void onPostExecute(List<FamilyDetails> familyDetails) {

            if (familyDetails!=null && !familyDetails.isEmpty()){
                familyData = familyDetails.get(0);

            }
            getVendingDetails();
        }
    }

    public void getVendingDetails(){

        new GetVendingDetailsTask(surveyDao).execute();

    }

    private class GetVendingDetailsTask extends AsyncTask<Void, Void, List<VendingDetails>> {
        SurveyDao surveyDao;

        public GetVendingDetailsTask(SurveyDao surveyDao) {
            this.surveyDao = surveyDao;
        }

        @Override
        protected List<VendingDetails> doInBackground(Void... voids) {
            List<VendingDetails> allData = surveyDao.getAllVendingData(surveyData.getSurvey_id());
            return allData;
        }

        @Override
        protected void onPostExecute(List<VendingDetails> vendingDetails) {

            if (vendingDetails!=null && !vendingDetails.isEmpty()){
                vendingData = vendingDetails.get(0);

            }

            getBankingDetails();
        }
    }

    public void getBankingDetails(){

        new GetBankingDetailsTask(surveyDao).execute();

    }

    private class GetBankingDetailsTask extends AsyncTask<Void, Void, List<BankingDetails>> {
        SurveyDao surveyDao;

        public GetBankingDetailsTask(SurveyDao surveyDao) {
            this.surveyDao = surveyDao;
        }

        @Override
        protected List<BankingDetails> doInBackground(Void... voids) {
            List<BankingDetails> allData = surveyDao.getAllBankingData(surveyData.getSurvey_id());
            return allData;
        }

        @Override
        protected void onPostExecute(List<BankingDetails> bankingDetails) {

            if (bankingDetails!=null && !bankingDetails.isEmpty()){
                bankingData = bankingDetails.get(0);

            }
            getDocumentsDetails();
        }
    }


    public void getDocumentsDetails(){

        new GetDocumentsDetailsTask(surveyDao).execute();

    }

    private class GetDocumentsDetailsTask extends AsyncTask<Void, Void, List<DocumentsData>> {
        SurveyDao surveyDao;

        public GetDocumentsDetailsTask(SurveyDao surveyDao) {
            this.surveyDao = surveyDao;
        }

        @Override
        protected List<DocumentsData> doInBackground(Void... voids) {
            List<DocumentsData> allData = surveyDao.getAllDocumentsData(surveyData.getSurvey_id());
            return allData;
        }

        @Override
        protected void onPostExecute(List<DocumentsData> documentsData) {

            if (documentsData!=null  && !documentsData.isEmpty()){
                documentsDetails = documentsData.get(0);

            }
            GetSurveyUri();

        }
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void GetSurveyUri() {

//        String UNiq_Id =  PrefUtils.getFromPrefs(LocalSurveyListActivity.this, ApplicationConstant.URI_NO_,"");

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + PrefUtils.getFromPrefs(LocalSurveyListActivity.this, ApplicationConstant.USERDETAILS.API_KEY, ""));

        ApiInterface apiservice = ApiService.getApiClient().create(ApiInterface.class);
        Call<SurveyResponse> call = apiservice.getSurveyUriNumber(headers,"");

        call.enqueue(new Callback<SurveyResponse>() {
            @Override
            public void onResponse(Call<SurveyResponse> call, Response<SurveyResponse> response) {

                if (response.body() != null) {

                    if (response.body().isStatus()) {
                        PrefUtils.saveToPrefs(LocalSurveyListActivity.this, ApplicationConstant.SURVEY_ID, response.body().getUriNumber());
                        ApplicationConstant.SurveyId = response.body().getUriNumber();
                        PrefUtils.saveToPrefs(LocalSurveyListActivity.this, ApplicationConstant.URI_NO_, response.body().getUriNumber());


                        if (surveyData.getPhoto_of_vendor()!=null && !surveyData.getPhoto_of_vendor().isEmpty()){
                            UploadVendorPhoto();

                        }else  if (surveyData.getPhoto_of_vending_site()!=null && !surveyData.getPhoto_of_vending_site().isEmpty()){
                            UploadVendingSitePhoto();

                        }else  if (surveyData!=null){
                            SendPersonalDetails();

                        }else  if (familyData!=null){
                            UpdateFamilySurvey();

                        }else if (vendingData!=null){
                            UpdateVendingDetails();

                        }else  if (bankingData!=null){
                            UploadBankDetailsSurvey();

                        }else

                        if (documentsDetails!=null){

                            OtherDocJson =  documentsDetails.getOther_document_json();

                            if (OtherDocJson == null || OtherDocJson.trim().isEmpty()){

                                UploadIdentityProof();

                            }else {

                                try {
                                    listOtherDoc  = new Gson().fromJson(OtherDocJson, new TypeToken<List<OtherDocDetails>>(){}.getType());

                                    if (!listOtherDoc.isEmpty()){
                                        UploadOtherDocument();

                                    }else {

                                        UploadIdentityProof();

                                    }

                                } catch (JsonSyntaxException e) {
                                    e.printStackTrace();
                                    ApplicationConstant.displayMessageDialog(LocalSurveyListActivity.this,
                                            "Response",
                                            e.getMessage().toString());
                                }
                            }
                        }
                        else {
                            if (progressDialog != null && progressDialog.isShowing())
                                progressDialog.dismiss();

                            ApplicationConstant.displayToastMessage(LocalSurveyListActivity.this,

                                    "Vendor photo or vending site photo not found");

                        }


                    } else {

                        if (progressDialog != null && progressDialog.isShowing())
                            progressDialog.dismiss();

                        ApplicationConstant.displayMessageDialog(LocalSurveyListActivity.this,
                                "Response",
                                String.valueOf(response.body().isStatus()));
                    }

                }else {

                    if (progressDialog != null && progressDialog.isShowing())
                        progressDialog.dismiss();

                    try {
                        ApplicationConstant.displayMessageDialog(LocalSurveyListActivity.this,
                                "Response",
                                response.errorBody().string());
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<SurveyResponse> call, Throwable t) {

                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();
                ApplicationConstant.displayMessageDialog(LocalSurveyListActivity.this, "Response", getString(R.string.net_speed_problem));

            }
        });
    }


    private void UploadVendorPhoto() {


        File file1 = new File(surveyData.getPhoto_of_vendor());

        RequestBody request_photo =
                RequestBody.create(MediaType.parse("image/png"), file1);

        String UNiq_Id = "";


        UNiq_Id = PrefUtils.getFromPrefs(LocalSurveyListActivity.this, ApplicationConstant.URI_NO_, "");

        String CORPORATION = PrefUtils.getFromPrefs(LocalSurveyListActivity.this, ApplicationConstant.CORPORATION, "");
        String ZONE = PrefUtils.getFromPrefs(LocalSurveyListActivity.this, ApplicationConstant.ZONE, "");
        String WARD = PrefUtils.getFromPrefs(LocalSurveyListActivity.this, ApplicationConstant.WARD, "");

        RequestBody CORPORATION_ = RequestBody.create(MediaType.parse("multipart/form-data"), CORPORATION);
        RequestBody ZONE_ = RequestBody.create(MediaType.parse("multipart/form-data"), ZONE);
        RequestBody WARD_ = RequestBody.create(MediaType.parse("multipart/form-data"), WARD);

// MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body_fhoto = null;
        if (surveyData.getPhoto_of_vendor().trim().isEmpty()) {
            body_fhoto = null;
        } else {

            body_fhoto =
                    MultipartBody.Part.createFormData("photo_of_the_street_vendor", file1.getName(), request_photo);
        }

        RequestBody SURVEY_ID_ = RequestBody.create(MediaType.parse("multipart/form-data"), UNiq_Id);


        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + PrefUtils.getFromPrefs(LocalSurveyListActivity.this, ApplicationConstant.USERDETAILS.API_KEY, ""));

        ApiInterface apiservice = ApiService.getApiClient().create(ApiInterface.class);
        Call<UpdateSurveyResponse> call = apiservice.UploadVendorPhoto(headers,
                SURVEY_ID_,
                CORPORATION_,
                ZONE_,
                WARD_,
                body_fhoto
        );

        call.enqueue(new Callback<UpdateSurveyResponse>() {
            @Override
            public void onResponse(Call<UpdateSurveyResponse> call, Response<UpdateSurveyResponse> response) {

                if (response.body() != null) {

                    if (response.body().isStatus()) {


                        ApplicationConstant.displayToastMessage(LocalSurveyListActivity.this,
                                "Photo saved successfully");

                        if (surveyData.getPhoto_of_vending_site()!=null && !surveyData.getPhoto_of_vending_site().isEmpty()){
                            UploadVendingSitePhoto();

                        }else if (surveyData!=null){
                            SendPersonalDetails();

                        } else {
                            if (progressDialog != null && progressDialog.isShowing())
                                progressDialog.dismiss();

                            ApplicationConstant.displayToastMessage(LocalSurveyListActivity.this,

                                    "personal details not found");

                        }

                    } else {
                        if (progressDialog != null && progressDialog.isShowing())
                            progressDialog.dismiss();

                        ApplicationConstant.displayErrorMessage(LocalSurveyListActivity.this,
                                response.body().getErrorCode().trim());
                    }

                } else {
                    if (progressDialog != null && progressDialog.isShowing())
                        progressDialog.dismiss();

                    try {
                        ApplicationConstant.displayMessageDialog(LocalSurveyListActivity.this,
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
                ApplicationConstant.displayMessageDialog(LocalSurveyListActivity.this, "Response", getString(R.string.net_speed_problem));

            }
        });
    }

    private void UploadVendingSitePhoto() {


        File file1 = new File(surveyData.getPhoto_of_vending_site());

        RequestBody request_photo =
                RequestBody.create(MediaType.parse("image/png"), file1);

        String UNiq_Id = "";


        UNiq_Id = PrefUtils.getFromPrefs(LocalSurveyListActivity.this, ApplicationConstant.URI_NO_, "");

        String CORPORATION = PrefUtils.getFromPrefs(LocalSurveyListActivity.this, ApplicationConstant.CORPORATION, "");
        String ZONE = PrefUtils.getFromPrefs(LocalSurveyListActivity.this, ApplicationConstant.ZONE, "");
        String WARD = PrefUtils.getFromPrefs(LocalSurveyListActivity.this, ApplicationConstant.WARD, "");

        RequestBody CORPORATION_ = RequestBody.create(MediaType.parse("multipart/form-data"), CORPORATION);
        RequestBody ZONE_ = RequestBody.create(MediaType.parse("multipart/form-data"), ZONE);
        RequestBody WARD_ = RequestBody.create(MediaType.parse("multipart/form-data"), WARD);

// MultipartBody.Part is used to send also the actual file name

        MultipartBody.Part body_fhoto = null;
        if (surveyData.getPhoto_of_vending_site().trim().isEmpty()) {
            body_fhoto = null;
        } else {

            body_fhoto =
                    MultipartBody.Part.createFormData("photo_of_vendor_site", file1.getName(), request_photo);
        }


        RequestBody SURVEY_ID_ = RequestBody.create(MediaType.parse("multipart/form-data"), UNiq_Id);


        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + PrefUtils.getFromPrefs(LocalSurveyListActivity.this, ApplicationConstant.USERDETAILS.API_KEY, ""));

        ApiInterface apiservice = ApiService.getApiClient().create(ApiInterface.class);
        Call<UpdateSurveyResponse> call = apiservice.UploadVendorPlacePhoto(headers,
                SURVEY_ID_,
                CORPORATION_,
                ZONE_,
                WARD_,
                body_fhoto
        );

        call.enqueue(new Callback<UpdateSurveyResponse>() {
            @Override
            public void onResponse(Call<UpdateSurveyResponse> call, Response<UpdateSurveyResponse> response) {


                if (response.body() != null) {

                    if (response.body().isStatus()) {


                        ApplicationConstant.displayToastMessage(LocalSurveyListActivity.this,
                                "Vending Site Photo saved successfully");

                        if (surveyData!=null){
                            SendPersonalDetails();

                        }else  if (familyData!=null){
                            UpdateFamilySurvey();

                        }else    if (vendingData!=null){
                            UpdateVendingDetails();

                        }else    if (bankingData!=null){
                            UploadBankDetailsSurvey();

                        }else

                        if (documentsDetails!=null){

                            OtherDocJson =  documentsDetails.getOther_document_json();

                            if (OtherDocJson == null || OtherDocJson.trim().isEmpty()){

                                UploadIdentityProof();

                            }else {

                                try {
                                    listOtherDoc  = new Gson().fromJson(OtherDocJson, new TypeToken<List<OtherDocDetails>>(){}.getType());

                                    if (!listOtherDoc.isEmpty()){
                                        UploadOtherDocument();

                                    }else {

                                        UploadIdentityProof();

                                    }

                                } catch (JsonSyntaxException e) {
                                    e.printStackTrace();
                                    ApplicationConstant.displayMessageDialog(LocalSurveyListActivity.this,
                                            "Response",
                                            e.getMessage().toString());
                                }
                            }
                        } else {
                            if (progressDialog != null && progressDialog.isShowing())
                                progressDialog.dismiss();

                            ApplicationConstant.displayToastMessage(LocalSurveyListActivity.this,

                                    "personal details not found");

                        }



                    } else {
                        if (progressDialog != null && progressDialog.isShowing())
                            progressDialog.dismiss();

                        ApplicationConstant.displayErrorMessage(LocalSurveyListActivity.this,
                                response.body().getErrorCode().trim());
                    }

                } else {
                    if (progressDialog != null && progressDialog.isShowing())
                        progressDialog.dismiss();

                    try {
                        ApplicationConstant.displayMessageDialog(LocalSurveyListActivity.this,
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
                ApplicationConstant.displayMessageDialog(LocalSurveyListActivity.this, "Response", getString(R.string.net_speed_problem));

            }
        });
    }

    private void SendPersonalDetails() {
        String AadharDetails = "";

        if (surveyData.getAadhar_details_json() != null){
            AadharDetails = surveyData.getAadhar_details_json();
        }

        File file_biometric = new File(surveyData.getBiometric_image());

        RequestBody request_biometric =
                RequestBody.create(MediaType.parse("image/png"), file_biometric);

        MultipartBody.Part body_biometric = null;
        if (surveyData.getBiometric_image().trim().isEmpty()) {
            body_biometric = null;
        } else {

            body_biometric =
                    MultipartBody.Part.createFormData("aadhaar_fingerprint", file_biometric.getName(), request_biometric);

        }



        String UNiq_Id = "";

        UNiq_Id = PrefUtils.getFromPrefs(LocalSurveyListActivity.this, ApplicationConstant.URI_NO_, "");

        String CORPORATION = PrefUtils.getFromPrefs(LocalSurveyListActivity.this, ApplicationConstant.CORPORATION, "");
        String ZONE = PrefUtils.getFromPrefs(LocalSurveyListActivity.this, ApplicationConstant.ZONE, "");
        String WARD = PrefUtils.getFromPrefs(LocalSurveyListActivity.this, ApplicationConstant.WARD, "");

        RequestBody CORPORATION_ = RequestBody.create(MediaType.parse("multipart/form-data"), CORPORATION);
        RequestBody ZONE_ = RequestBody.create(MediaType.parse("multipart/form-data"), ZONE);
        RequestBody WARD_ = RequestBody.create(MediaType.parse("multipart/form-data"), WARD);


        RequestBody NAME_VENDOR_ = RequestBody.create(MediaType.parse("multipart/form-data"), surveyData.getName_of_vendor());
        RequestBody SURVEY_ID_ = RequestBody.create(MediaType.parse("multipart/form-data"), UNiq_Id);
        RequestBody SEX_ = RequestBody.create(MediaType.parse("multipart/form-data"), surveyData.getSex());
        RequestBody AGE_ = RequestBody.create(MediaType.parse("multipart/form-data"), surveyData.getAge());
        RequestBody DOB_ = RequestBody.create(MediaType.parse("multipart/form-data"), surveyData.getDate_of_birth());
        RequestBody CONTACT_NO_ = RequestBody.create(MediaType.parse("multipart/form-data"), surveyData.getMobile_no());
        RequestBody LANDLINE_NO_ = RequestBody.create(MediaType.parse("multipart/form-data"), surveyData.getLandline_no());
        RequestBody EDUCATION_STATUS_ = RequestBody.create(MediaType.parse("multipart/form-data"), surveyData.getEducation_status());
        RequestBody NAME_OFFATHER_HUSBAND_ = RequestBody.create(MediaType.parse("multipart/form-data"), surveyData.getName_of_father_husband());
        RequestBody NAME_MOTHER_ = RequestBody.create(MediaType.parse("multipart/form-data"), surveyData.getName_of_mother());
        RequestBody NAME_SPOUSE_ = RequestBody.create(MediaType.parse("multipart/form-data"), surveyData.getName_of_spouse());
        RequestBody WHETHER_WIDOWED_ = RequestBody.create(MediaType.parse("multipart/form-data"), surveyData.getWheather_widowed());
        RequestBody CATEGORY_ = RequestBody.create(MediaType.parse("multipart/form-data"), surveyData.getCategory());
        RequestBody RESIDENTIAL_ADDRESS_ = RequestBody.create(MediaType.parse("multipart/form-data"), surveyData.getResidential_address());
        RequestBody PERMENENT_ADDRESS_ = RequestBody.create(MediaType.parse("multipart/form-data"), surveyData.getPermenent_address());
        RequestBody AADHAR_DETAILS_ = RequestBody.create(MediaType.parse("multipart/form-data"),AadharDetails);
        RequestBody AADHAR_NO_ = RequestBody.create(MediaType.parse("multipart/form-data"),surveyData.getAadhar_number());
        RequestBody IS_CRIMINALCASE_ = RequestBody.create(MediaType.parse("multipart/form-data"), surveyData.getIs_criminal_case());
        RequestBody CRIMINALCASE_NO_ = RequestBody.create(MediaType.parse("multipart/form-data"), surveyData.getCriminal_details_json());
        RequestBody ANNUAL_INCOME_ = RequestBody.create(MediaType.parse("multipart/form-data"), surveyData.getAnnual_income());

        RequestBody LATITUDE = RequestBody.create(MediaType.parse("multipart/form-data"), "" + surveyData.getLatitude());
        RequestBody LONGITUDE = RequestBody.create(MediaType.parse("multipart/form-data"), "" + surveyData.getLongitude());
        RequestBody SCANRESULT_ = RequestBody.create(MediaType.parse("multipart/form-data"), "" + surveyData.getBarcode());


        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + PrefUtils.getFromPrefs(LocalSurveyListActivity.this, ApplicationConstant.USERDETAILS.API_KEY, ""));

        ApiInterface apiservice = ApiService.getApiClient().create(ApiInterface.class);
        Call<UpdateSurveyResponse> call = apiservice.getAddSurvey(headers,
                SURVEY_ID_,
                CORPORATION_,
                ZONE_,
                WARD_,
                SCANRESULT_,
                NAME_VENDOR_, SEX_, AGE_,
                DOB_,
                CONTACT_NO_,
                LANDLINE_NO_,
                EDUCATION_STATUS_,
                NAME_OFFATHER_HUSBAND_,
                NAME_MOTHER_,
                NAME_SPOUSE_,
                WHETHER_WIDOWED_,
                CATEGORY_,
                ANNUAL_INCOME_,
                RESIDENTIAL_ADDRESS_,
                PERMENENT_ADDRESS_,
                AADHAR_DETAILS_,
                AADHAR_NO_,
                IS_CRIMINALCASE_,
                CRIMINALCASE_NO_,
                LATITUDE,
                LONGITUDE,
                body_biometric
//                CRIMINALCASE_STATUS_
        );

        call.enqueue(new Callback<UpdateSurveyResponse>() {
            @Override
            public void onResponse(Call<UpdateSurveyResponse> call, Response<UpdateSurveyResponse> response) {



                if (response.body() != null) {

                    if (response.body().isStatus()) {

                        PrefUtils.saveToPrefs(LocalSurveyListActivity.this, ApplicationConstant.CONTACT, surveyData.getMobile_no());


                        ApplicationConstant.displayToastMessage(LocalSurveyListActivity.this,
                                "Personal Details saved successfully");

                        if (familyData!=null){
                            UpdateFamilySurvey();

                        }else  if (vendingData!=null){
                            UpdateVendingDetails();

                        }else    if (bankingData!=null){
                            UploadBankDetailsSurvey();

                        }else

                        if (documentsDetails!=null){

                            OtherDocJson =  documentsDetails.getOther_document_json();

                            if (OtherDocJson == null || OtherDocJson.trim().isEmpty()){

                                UploadIdentityProof();

                            }else {

                                try {
                                    listOtherDoc  = new Gson().fromJson(OtherDocJson, new TypeToken<List<OtherDocDetails>>(){}.getType());

                                    if (!listOtherDoc.isEmpty()){
                                        UploadOtherDocument();

                                    }else {

                                        UploadIdentityProof();

                                    }

                                } catch (JsonSyntaxException e) {
                                    e.printStackTrace();
                                    ApplicationConstant.displayMessageDialog(LocalSurveyListActivity.this,
                                            "Response",
                                            e.getMessage().toString());
                                }
                            }
                        }else {

                            if (progressDialog != null && progressDialog.isShowing())
                                progressDialog.dismiss();

//                            ApplicationConstant.displayMessageDialog(LocalSurveyListActivity.this,
//                                    "Response",
//                                    response.body().getMessage());

                            DeledeEntryDetails();
                        }


                    } else {

                        if (progressDialog != null && progressDialog.isShowing())
                            progressDialog.dismiss();

                        ApplicationConstant.displayErrorMessage(LocalSurveyListActivity.this,
                                response.body().getErrorCode().trim());
                    }

                } else {

                    if (progressDialog != null && progressDialog.isShowing())
                        progressDialog.dismiss();

                    try {
                        ApplicationConstant.displayMessageDialog(LocalSurveyListActivity.this,
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
                ApplicationConstant.displayMessageDialog(LocalSurveyListActivity.this, "Response", getString(R.string.net_speed_problem));

            }
        });
    }

    private void UpdateFamilySurvey() {

        String UNiq_Id =  PrefUtils.getFromPrefs(LocalSurveyListActivity.this,ApplicationConstant.URI_NO_,"");
        String CORPORATION =   PrefUtils.getFromPrefs(LocalSurveyListActivity.this,ApplicationConstant.CORPORATION,"");
        String ZONE =  PrefUtils.getFromPrefs(LocalSurveyListActivity.this,ApplicationConstant.ZONE,"");
        String WARD =  PrefUtils.getFromPrefs(LocalSurveyListActivity.this,ApplicationConstant.WARD,"");

        RequestBody CORPORATION_ = RequestBody.create(MediaType.parse("multipart/form-data"), CORPORATION);
        RequestBody ZONE_ = RequestBody.create(MediaType.parse("multipart/form-data"), ZONE);
        RequestBody WARD_ = RequestBody.create(MediaType.parse("multipart/form-data"), WARD);


        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + PrefUtils.getFromPrefs(LocalSurveyListActivity.this, ApplicationConstant.USERDETAILS.API_KEY, ""));

        RequestBody URI_NO_ = RequestBody.create(MediaType.parse("multipart/form-data"), UNiq_Id);
        RequestBody family_members_ = RequestBody.create(MediaType.parse("multipart/form-data"), "1");
        RequestBody json_family_ = RequestBody.create(MediaType.parse("multipart/form-data"), familyData.getFamily_details_json());
        RequestBody json_landAssets_ = RequestBody.create(MediaType.parse("multipart/form-data"), familyData.getLandassets_details_json());
        RequestBody isFamilySurveyed = RequestBody.create(MediaType.parse("multipart/form-data"), familyData.getIs_family_member_surveyed());
        RequestBody json_surveyFam_ = RequestBody.create(MediaType.parse("multipart/form-data"), familyData.getJson_surveyFam());



//        ApiInterface apiservice = ApiService.getApiClient().create(ApiInterface.class);
//        Call<UpdateSurveyResponse> call = apiservice.getUpdateFamilySurvey(headers,URI_NO
//                ,"1",json_family,json_landAssets,"1",json_surveyFam
//
//        );

        ApiInterface apiservice = ApiService.getApiClient().create(ApiInterface.class);
        Call<UpdateSurveyResponse> call = apiservice.getUpdateFamilySurvey(headers,
                URI_NO_,
                CORPORATION_,
                ZONE_,
                WARD_,
                family_members_,json_family_,json_landAssets_,isFamilySurveyed,json_surveyFam_

        );

        call.enqueue(new Callback<UpdateSurveyResponse>() {
            @Override
            public void onResponse(Call<UpdateSurveyResponse> call, Response<UpdateSurveyResponse> response) {



                if (response.body() != null) {

                    if (response.body().isStatus()) {


                        ApplicationConstant.displayToastMessage(LocalSurveyListActivity.this,
                                "family Details saved successfully");

                        if (vendingData!=null){
                            UpdateVendingDetails();

                        }else  if (bankingData!=null){
                            UploadBankDetailsSurvey();

                        }else

                        if (documentsDetails!=null){

                            OtherDocJson =  documentsDetails.getOther_document_json();

                            if (OtherDocJson == null || OtherDocJson.trim().isEmpty()){

                                UploadIdentityProof();

                            }else {

                                try {
                                    listOtherDoc  = new Gson().fromJson(OtherDocJson, new TypeToken<List<OtherDocDetails>>(){}.getType());

                                    if (!listOtherDoc.isEmpty()){
                                        UploadOtherDocument();

                                    }else {

                                        UploadIdentityProof();

                                    }

                                } catch (JsonSyntaxException e) {
                                    e.printStackTrace();
                                    ApplicationConstant.displayMessageDialog(LocalSurveyListActivity.this,
                                            "Response",
                                            e.getMessage().toString());
                                }
                            }
                        }else {
                            if (progressDialog != null && progressDialog.isShowing())
                                progressDialog.dismiss();

//                            ApplicationConstant.displayMessageDialog(LocalSurveyListActivity.this,
//                                    "Response",
//                                    response.body().getMessage());

                            DeledeEntryDetails();
                        }


                    } else {

                        if (progressDialog != null && progressDialog.isShowing())
                            progressDialog.dismiss();

                        ApplicationConstant.displayErrorMessage(LocalSurveyListActivity.this,
                                response.body().getErrorCode().trim());
                    }

                }else {
                    if (progressDialog != null && progressDialog.isShowing())
                        progressDialog.dismiss();
                    try {
                        ApplicationConstant.displayMessageDialog(LocalSurveyListActivity.this,
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
                ApplicationConstant.displayMessageDialog(LocalSurveyListActivity.this, "Response", getString(R.string.net_speed_problem));

            }
        });
    }

    private void UpdateVendingDetails() {


        String UNiq_Id = PrefUtils.getFromPrefs(LocalSurveyListActivity.this, ApplicationConstant.URI_NO_, "");

        String CORPORATION =   PrefUtils.getFromPrefs(LocalSurveyListActivity.this,ApplicationConstant.CORPORATION,"");
        String ZONE =  PrefUtils.getFromPrefs(LocalSurveyListActivity.this,ApplicationConstant.ZONE,"");
        String WARD =  PrefUtils.getFromPrefs(LocalSurveyListActivity.this,ApplicationConstant.WARD,"");


        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + PrefUtils.getFromPrefs(LocalSurveyListActivity.this, ApplicationConstant.USERDETAILS.API_KEY, ""));

        ApiInterface apiservice = ApiService.getApiClient().create(ApiInterface.class);
        Call<UpdateSurveyResponse> call = apiservice.getUpdateSurvey(headers,
                UNiq_Id,
                CORPORATION,
                ZONE,
                WARD,
                vendingData.getType_of_vending(),
                vendingData.getName_of_vending(),
                vendingData.getTiming_of_vending_from(),
                vendingData.getTiming_of_vending_to(),
                vendingData.getTiming_of_vending_from_1(),
                vendingData.getTiming_of_vending_to_1(),
                vendingData.getNo_of_days_Active(),
                vendingData.getYears_of_vending(),
                vendingData.getIs_previously_street_vendor(),
                vendingData.getType_of_structure(),
                vendingData.getStart_date_of_vending(),
                vendingData.getIs_tehbajari_document(),
                vendingData.getChoice_of_vending_area_market()

        );

        call.enqueue(new Callback<UpdateSurveyResponse>() {
            @Override
            public void onResponse(Call<UpdateSurveyResponse> call, Response<UpdateSurveyResponse> response) {



                if (response.body() != null) {

                    if (response.body().isStatus()) {

                        ApplicationConstant.displayToastMessage(LocalSurveyListActivity.this,
                                "Vending Details saved successfully");

                        if (bankingData!=null){
                            UploadBankDetailsSurvey();

                        }else

                        if (documentsDetails!=null){

                            OtherDocJson =  documentsDetails.getOther_document_json();

                            if (OtherDocJson == null || OtherDocJson.trim().isEmpty()){

                                UploadIdentityProof();

                            }else {

                                try {
                                    listOtherDoc  = new Gson().fromJson(OtherDocJson, new TypeToken<List<OtherDocDetails>>(){}.getType());

                                    if (!listOtherDoc.isEmpty()){
                                        UploadOtherDocument();

                                    }else {

                                        UploadIdentityProof();

                                    }

                                } catch (JsonSyntaxException e) {
                                    e.printStackTrace();
                                    ApplicationConstant.displayMessageDialog(LocalSurveyListActivity.this,
                                            "Response",
                                            e.getMessage().toString());
                                }
                            }
                        }else {

                            if (progressDialog != null && progressDialog.isShowing())
                                progressDialog.dismiss();

//                            ApplicationConstant.displayMessageDialog(LocalSurveyListActivity.this,
//                                    "Response",
//                                    response.body().getMessage());

                            DeledeEntryDetails();


                        }


                    } else {

                        if (progressDialog != null && progressDialog.isShowing())
                            progressDialog.dismiss();

                        ApplicationConstant.displayErrorMessage(LocalSurveyListActivity.this,
                                response.body().getErrorCode().trim());
                    }

                } else {

                    if (progressDialog != null && progressDialog.isShowing())
                        progressDialog.dismiss();

                    try {
                        ApplicationConstant.displayMessageDialog(LocalSurveyListActivity.this,
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
                ApplicationConstant.displayMessageDialog(LocalSurveyListActivity.this, "Response", getString(R.string.net_speed_problem));

            }
        });
    }

    private void UploadBankDetailsSurvey() {


        String UNiq_Id = "";

        UNiq_Id = PrefUtils.getFromPrefs(LocalSurveyListActivity.this, ApplicationConstant.URI_NO_, "");

        String CORPORATION =   PrefUtils.getFromPrefs(LocalSurveyListActivity.this,ApplicationConstant.CORPORATION,"");
        String ZONE =  PrefUtils.getFromPrefs(LocalSurveyListActivity.this,ApplicationConstant.ZONE,"");
        String WARD =  PrefUtils.getFromPrefs(LocalSurveyListActivity.this,ApplicationConstant.WARD,"");

        RequestBody CORPORATION_ = RequestBody.create(MediaType.parse("multipart/form-data"), CORPORATION);
        RequestBody ZONE_ = RequestBody.create(MediaType.parse("multipart/form-data"), ZONE);
        RequestBody WARD_ = RequestBody.create(MediaType.parse("multipart/form-data"), WARD);




        RequestBody SURVEY_ID_ = RequestBody.create(MediaType.parse("multipart/form-data"), UNiq_Id);

        RequestBody BANKACC_NO_ = RequestBody.create(MediaType.parse("multipart/form-data"), bankingData.getBank_account_number());
        RequestBody BANKNAME_ = RequestBody.create(MediaType.parse("multipart/form-data"), bankingData.getBank_name());
        RequestBody BRANCH_NAME_ = RequestBody.create(MediaType.parse("multipart/form-data"), bankingData.getBranch_name());
        RequestBody IFSC_ = RequestBody.create(MediaType.parse("multipart/form-data"), bankingData.getIfsc_code());

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + PrefUtils.getFromPrefs(LocalSurveyListActivity.this, ApplicationConstant.USERDETAILS.API_KEY, ""));

        ApiInterface apiservice = ApiService.getApiClient().create(ApiInterface.class);
        Call<UpdateSurveyResponse> call = apiservice.BankDetailsSurvey(headers,
                SURVEY_ID_,
                CORPORATION_,
                ZONE_,
                WARD_,
                BANKACC_NO_,
                BANKNAME_,
                BRANCH_NAME_,
                IFSC_
        );

        call.enqueue(new Callback<UpdateSurveyResponse>() {
            @Override
            public void onResponse(Call<UpdateSurveyResponse> call, Response<UpdateSurveyResponse> response) {


                if (response.body() != null) {

                    if (response.body().isStatus()) {


                        ApplicationConstant.displayToastMessage(LocalSurveyListActivity.this,
                                "Banking Details saved successfully");

                       OtherDocJson =  documentsDetails.getOther_document_json();

                        if (documentsDetails!=null){

                            if (OtherDocJson == null || OtherDocJson.trim().isEmpty()){

                                UploadIdentityProof();

                            }else {

                                try {
                                    listOtherDoc  = new Gson().fromJson(OtherDocJson, new TypeToken<List<OtherDocDetails>>(){}.getType());

                                    if (!listOtherDoc.isEmpty()){
                                        UploadOtherDocument();

                                    }else {

                                        UploadIdentityProof();

                                    }

                                } catch (JsonSyntaxException e) {
                                    e.printStackTrace();
                                    ApplicationConstant.displayMessageDialog(LocalSurveyListActivity.this,
                                            "Response",
                                            e.getMessage().toString());
                                }
                            }
                        }else {

                            if (progressDialog != null && progressDialog.isShowing())
                                progressDialog.dismiss();

                            DeledeEntryDetails();


                        }



                    } else {

                        if (progressDialog != null && progressDialog.isShowing())
                            progressDialog.dismiss();

                        ApplicationConstant.displayErrorMessage(LocalSurveyListActivity.this,
                                response.body().getErrorCode().trim());
                    }

                } else {

                    if (progressDialog != null && progressDialog.isShowing())
                        progressDialog.dismiss();

                    try {
                        ApplicationConstant.displayMessageDialog(LocalSurveyListActivity.this,
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
                ApplicationConstant.displayMessageDialog(LocalSurveyListActivity.this, "Response", getString(R.string.net_speed_problem));

            }
        });
    }

    private void Upload_Recordings() {


        File file_recording = new File(documentsDetails.getRecording());

        String UNiq_Id = PrefUtils.getFromPrefs(LocalSurveyListActivity.this, ApplicationConstant.URI_NO_, "");

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + PrefUtils.getFromPrefs(LocalSurveyListActivity.this, ApplicationConstant.USERDETAILS.API_KEY, ""));

        Uri recordUri = SurveyAppFileProvider.getUriForFile(LocalSurveyListActivity.this,
                BuildConfig.APPLICATION_ID + ".android.fileprovider",
                file_recording);

        RequestBody request_recording =
                RequestBody.create(MediaType.parse(getContentResolver().getType(recordUri)), file_recording);


// MultipartBody.Part is used to send also the actual file name

        MultipartBody.Part body_file5 = null;
        if (documentsDetails.getRecording().trim().isEmpty()) {
            body_file5 = null;
        } else {

            body_file5 =
                    MultipartBody.Part.createFormData("recording", file_recording.getName(), request_recording);

        }

        RequestBody URI_NO_ = RequestBody.create(MediaType.parse("multipart/form-data"), UNiq_Id);

        String CORPORATION =   PrefUtils.getFromPrefs(LocalSurveyListActivity.this,ApplicationConstant.CORPORATION,"");
        String ZONE =  PrefUtils.getFromPrefs(LocalSurveyListActivity.this,ApplicationConstant.ZONE,"");
        String WARD =  PrefUtils.getFromPrefs(LocalSurveyListActivity.this,ApplicationConstant.WARD,"");

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

                if (response.body() != null) {

                    if (response.body().isStatus()) {


                        ApplicationConstant.displayToastMessage(LocalSurveyListActivity.this,
                                "Recording saved successfully");
                        Upload_Documents();


                    } else {

                        if (progressDialog != null && progressDialog.isShowing())
                            progressDialog.dismiss();

                        ApplicationConstant.displayToastMessage(LocalSurveyListActivity.this,
                                response.body().getMessage());

                        Upload_Documents();

                    }

                } else {

                    if (progressDialog != null && progressDialog.isShowing())
                        progressDialog.dismiss();

                    try {
                        ApplicationConstant.displayToastMessage(LocalSurveyListActivity.this,
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
                ApplicationConstant.displayToastMessage(LocalSurveyListActivity.this,  getString(R.string.net_speed_problem));
                Upload_Documents();

            }
        });
    }

    private void UploadIdentityProof() {

        File file_identity_front = new File(documentsDetails.getIdentity_proof_front());
        File file_identity_back = new File(documentsDetails.getIdentity_proof_back());

        String UNiq_Id = PrefUtils.getFromPrefs(LocalSurveyListActivity.this, ApplicationConstant.URI_NO_, "");


        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + PrefUtils.getFromPrefs(LocalSurveyListActivity.this, ApplicationConstant.USERDETAILS.API_KEY, ""));

        RequestBody request_identity_front =
                RequestBody.create(MediaType.parse("image/png"), file_identity_front);

        RequestBody request_identity_back =
                RequestBody.create(MediaType.parse("image/png"), file_identity_back);



// MultipartBody.Part is used to send also the actual file name

        MultipartBody.Part body_identity_front = null;
        if (documentsDetails.getIdentity_proof_front().trim().isEmpty()) {
            body_identity_front = null;
        } else {

            body_identity_front =
                    MultipartBody.Part.createFormData("identity_proof_documents_front", file_identity_front.getName(), request_identity_front);

        }


        MultipartBody.Part body_identity_back = null;
        if (documentsDetails.getIdentity_proof_back().trim().isEmpty()) {
            body_identity_back = null;
        } else {

            body_identity_back = MultipartBody.Part.createFormData("identity_proof_documents_back", file_identity_back.getName(), request_identity_back);

        }


        String CORPORATION =   PrefUtils.getFromPrefs(LocalSurveyListActivity.this,ApplicationConstant.CORPORATION,"");
        String ZONE =  PrefUtils.getFromPrefs(LocalSurveyListActivity.this,ApplicationConstant.ZONE,"");
        String WARD =  PrefUtils.getFromPrefs(LocalSurveyListActivity.this,ApplicationConstant.WARD,"");

        RequestBody CORPORATION_ = RequestBody.create(MediaType.parse("multipart/form-data"), CORPORATION);
        RequestBody ZONE_ = RequestBody.create(MediaType.parse("multipart/form-data"), ZONE);
        RequestBody WARD_ = RequestBody.create(MediaType.parse("multipart/form-data"), WARD);


        RequestBody URI_NO_ = RequestBody.create(MediaType.parse("multipart/form-data"), UNiq_Id);
        RequestBody IDENTITY_PROOF_TYPE_ = RequestBody.create(MediaType.parse("multipart/form-data"), documentsDetails.getIdentity_proof_type());


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

                if (response.body() != null) {

                    if (response.body().isStatus()) {

                        ApplicationConstant.displayToastMessage(LocalSurveyListActivity.this,
                                "uploaded identity proof doc");

                        Upload_VendingHistoryProof();

                    } else {

                        if (progressDialog != null && progressDialog.isShowing())
                            progressDialog.dismiss();

                        ApplicationConstant.displayErrorMessage(LocalSurveyListActivity.this,
                                response.body().getErrorCode().trim());
                    }

                } else {

                    if (progressDialog != null && progressDialog.isShowing())
                        progressDialog.dismiss();

                    try {
                        ApplicationConstant.displayMessageDialog(LocalSurveyListActivity.this,
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
                ApplicationConstant.displayMessageDialog(LocalSurveyListActivity.this, "Response", getString(R.string.net_speed_problem));

            }
        });
    }

    private void Upload_VendingHistoryProof() {

        File file_vending_history_front = new File(documentsDetails.getVending_history_proof_front());
        File file_vending_history_back = new File(documentsDetails.getVending_history_proof_back());

        String UNiq_Id = PrefUtils.getFromPrefs(LocalSurveyListActivity.this, ApplicationConstant.URI_NO_, "");

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + PrefUtils.getFromPrefs(LocalSurveyListActivity.this, ApplicationConstant.USERDETAILS.API_KEY, ""));


        RequestBody request_vending_history_front =
                RequestBody.create(MediaType.parse("image/png"), file_vending_history_front);

        RequestBody request_vending_history_back =
                RequestBody.create(MediaType.parse("image/png"), file_vending_history_back);




// MultipartBody.Part is used to send also the actual file name

        MultipartBody.Part body_vending_history_front = null;
        if (documentsDetails.getVending_history_proof_front().trim().isEmpty()) {
            body_vending_history_front = null;
        } else {

            body_vending_history_front =
                    MultipartBody.Part.createFormData("vending_history_proof_documents_front", file_vending_history_front.getName(), request_vending_history_front);

        }



        MultipartBody.Part body_vending_history_back = null;
        if (documentsDetails.getVending_history_proof_back().trim().isEmpty()) {
            body_vending_history_back = null;
        } else {

            body_vending_history_back = MultipartBody.Part.createFormData("vending_history_proof_documents_back", file_vending_history_back.getName(), request_vending_history_back);

        }


        String CORPORATION =   PrefUtils.getFromPrefs(LocalSurveyListActivity.this,ApplicationConstant.CORPORATION,"");
        String ZONE =  PrefUtils.getFromPrefs(LocalSurveyListActivity.this,ApplicationConstant.ZONE,"");
        String WARD =  PrefUtils.getFromPrefs(LocalSurveyListActivity.this,ApplicationConstant.WARD,"");

        RequestBody CORPORATION_ = RequestBody.create(MediaType.parse("multipart/form-data"), CORPORATION);
        RequestBody ZONE_ = RequestBody.create(MediaType.parse("multipart/form-data"), ZONE);
        RequestBody WARD_ = RequestBody.create(MediaType.parse("multipart/form-data"), WARD);


        RequestBody URI_NO_ = RequestBody.create(MediaType.parse("multipart/form-data"), UNiq_Id);
        RequestBody VENDING_HISTORY_PROOF_TYPE_ = RequestBody.create(MediaType.parse("multipart/form-data"), documentsDetails.getVending_history_proof_type());


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


                if (response.body() != null) {

                    if (response.body().isStatus()) {

                        ApplicationConstant.displayToastMessage(LocalSurveyListActivity.this,
                                "uploaded vending history proof doc");

                        Upload_Recordings();


                    } else {

                        if (progressDialog != null && progressDialog.isShowing())
                            progressDialog.dismiss();

                        ApplicationConstant.displayErrorMessage(LocalSurveyListActivity.this,
                                response.body().getErrorCode().trim());
                    }

                } else {

                    if (progressDialog != null && progressDialog.isShowing())
                        progressDialog.dismiss();

                    try {
                        ApplicationConstant.displayMessageDialog(LocalSurveyListActivity.this,
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
                ApplicationConstant.displayMessageDialog(LocalSurveyListActivity.this, "Response", getString(R.string.net_speed_problem));

            }
        });
    }

    private void Upload_Documents() {

        File file_tehjabari = new File(documentsDetails.getAllotment_tehzabari_document());
        File file_undertaking = new File(documentsDetails.getUndertaking_doc());
        File file_acknowlegement = new File(documentsDetails.getAcknowledgement_doc());


        String UNiq_Id = PrefUtils.getFromPrefs(LocalSurveyListActivity.this, ApplicationConstant.URI_NO_, "");


        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + PrefUtils.getFromPrefs(LocalSurveyListActivity.this, ApplicationConstant.USERDETAILS.API_KEY, ""));


        RequestBody request_tehjabari =
                RequestBody.create(MediaType.parse("image/png"), documentsDetails.getAllotment_tehzabari_document());

        RequestBody request_undertaking =
                RequestBody.create(MediaType.parse("image/png"), documentsDetails.getUndertaking_doc());

        RequestBody request_acknowlegement =
                RequestBody.create(MediaType.parse("image/png"), documentsDetails.getAcknowledgement_doc());


// MultipartBody.Part is used to send also the actual file name


        MultipartBody.Part body_tehbazari_document = null;
        if (documentsDetails.getAllotment_tehzabari_document().trim().isEmpty()) {
            body_tehbazari_document = null;
        } else {

            body_tehbazari_document = MultipartBody.Part.createFormData("allotment_of_tehbazari_document", file_tehjabari.getName(), request_tehjabari);

        }

        MultipartBody.Part body_undertaking = null;
        if (documentsDetails.getUndertaking_doc().trim().isEmpty()) {
            body_undertaking = null;
        } else {

            body_undertaking =
                    MultipartBody.Part.createFormData("undertaking_by_the_applicant", file_undertaking.getName(), request_undertaking);

        }


        MultipartBody.Part body_acknowlegement = null;
        if (documentsDetails.getAcknowledgement_doc().trim().isEmpty()) {
            body_acknowlegement = null;
        } else {

            body_acknowlegement =
                    MultipartBody.Part.createFormData("acknowledgement_receipt", file_acknowlegement.getName(), request_acknowlegement);

        }


        String CORPORATION =   PrefUtils.getFromPrefs(LocalSurveyListActivity.this,ApplicationConstant.CORPORATION,"");
        String ZONE =  PrefUtils.getFromPrefs(LocalSurveyListActivity.this,ApplicationConstant.ZONE,"");
        String WARD =  PrefUtils.getFromPrefs(LocalSurveyListActivity.this,ApplicationConstant.WARD,"");

        RequestBody CORPORATION_ = RequestBody.create(MediaType.parse("multipart/form-data"), CORPORATION);
        RequestBody ZONE_ = RequestBody.create(MediaType.parse("multipart/form-data"), ZONE);
        RequestBody WARD_ = RequestBody.create(MediaType.parse("multipart/form-data"), WARD);


        RequestBody URI_NO_ = RequestBody.create(MediaType.parse("multipart/form-data"), UNiq_Id);
        RequestBody Contact_ = RequestBody.create(MediaType.parse("multipart/form-data"), surveyData.getMobile_no());
        RequestBody COMMENTS_ = RequestBody.create(MediaType.parse("multipart/form-data"), documentsDetails.getComments());
        RequestBody SURVEY_STATUS = RequestBody.create(MediaType.parse("multipart/form-data"), "1");


        ApiInterface apiservice = ApiService.getApiClient().create(ApiInterface.class);
        Call<UpdateSurveyResponse> call = apiservice.getUpdateDocuments(headers,
                URI_NO_,
                Contact_,
                CORPORATION_,
                ZONE_,
                WARD_,
//                SURVEY_STATUS,
                COMMENTS_,
                body_tehbazari_document,
                body_acknowlegement,
                body_undertaking

        );

        call.enqueue(new Callback<UpdateSurveyResponse>() {
            @Override
            public void onResponse(Call<UpdateSurveyResponse> call, Response<UpdateSurveyResponse> response) {


                if (response.body() != null) {

                    if (response.body().isStatus()) {

//                        if (progressDialog != null && progressDialog.isShowing())
//                            progressDialog.dismiss();


                        SubmitSuspendRemark(UNiq_Id);




//                        ApplicationConstant.displayToastMessage(DocumentScanActivity.this,
//                                "Vending Details saved successfully");


                    } else {

                        if (progressDialog != null && progressDialog.isShowing())
                            progressDialog.dismiss();

                        ApplicationConstant.displayErrorMessage(LocalSurveyListActivity.this,
                                response.body().getErrorCode().trim());
                    }

                } else {

                    if (progressDialog != null && progressDialog.isShowing())
                        progressDialog.dismiss();

                    try {
                        ApplicationConstant.displayMessageDialog(LocalSurveyListActivity.this,
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
                ApplicationConstant.displayMessageDialog(LocalSurveyListActivity.this, "Response", getString(R.string.net_speed_problem));

            }
        });
    }

    private void SubmitSuspendRemark(String URI) {


        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + PrefUtils.getFromPrefs(LocalSurveyListActivity.this, ApplicationConstant.USERDETAILS.API_KEY, ""));

        ApiInterface apiservice = ApiService.getApiClient().create(ApiInterface.class);
        Call<UpdateSurveyResponse> call = apiservice.SendSurveyStatus(headers,URI,"1");

        call.enqueue(new Callback<UpdateSurveyResponse>() {
            @Override
            public void onResponse(Call<UpdateSurveyResponse> call, Response<UpdateSurveyResponse> response) {

                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();

                if (response.body() != null) {

                    if (response.body().isStatus()) {

                        DeledeEntryDetails();


                    } else {

                        ApplicationConstant.displayErrorMessage(LocalSurveyListActivity.this,
                                response.body().getErrorCode().trim());
                    }

                }else {

                    try {
                        ApplicationConstant.displayMessageDialog(LocalSurveyListActivity.this,
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
                ApplicationConstant.displayMessageDialog(LocalSurveyListActivity.this, "Response", getString(R.string.net_speed_problem));

            }
        });
    }



    private void UploadOtherDocument() {

        File OtherDocFile = new File(listOtherDoc.get(Count).getDocument());

        String UNiq_Id = PrefUtils.getFromPrefs(LocalSurveyListActivity.this, ApplicationConstant.URI_NO_, "");


        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + PrefUtils.getFromPrefs(LocalSurveyListActivity.this, ApplicationConstant.USERDETAILS.API_KEY, ""));

        RequestBody request_other_document =
                RequestBody.create(MediaType.parse("image/png"), OtherDocFile);


// MultipartBody.Part is used to send also the actual file name

        MultipartBody.Part body_other_document = null;
        if (listOtherDoc.get(Count).getDocument().trim().isEmpty()) {
            body_other_document = null;
        } else {

            body_other_document =
                    MultipartBody.Part.createFormData("document", OtherDocFile.getName(), request_other_document);

        }



        String CORPORATION =   PrefUtils.getFromPrefs(LocalSurveyListActivity.this,ApplicationConstant.CORPORATION,"");
        String ZONE =  PrefUtils.getFromPrefs(LocalSurveyListActivity.this,ApplicationConstant.ZONE,"");
        String WARD =  PrefUtils.getFromPrefs(LocalSurveyListActivity.this,ApplicationConstant.WARD,"");

        RequestBody CORPORATION_ = RequestBody.create(MediaType.parse("multipart/form-data"), CORPORATION);
        RequestBody ZONE_ = RequestBody.create(MediaType.parse("multipart/form-data"), ZONE);
        RequestBody WARD_ = RequestBody.create(MediaType.parse("multipart/form-data"), WARD);


        RequestBody URI_NO_ = RequestBody.create(MediaType.parse("multipart/form-data"), UNiq_Id);
        RequestBody OTHER_DOCUMENT_ = RequestBody.create(MediaType.parse("multipart/form-data"), listOtherDoc.get(Count).getDocument_type());


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


                if (response.body() != null) {

                    if (response.body().isStatus()) {

                        Count++;
                        if (Count<listOtherDoc.size()){
                            UploadOtherDocument();
                        }else {
                            UploadIdentityProof();

                        }


                    } else {

                        if (progressDialog != null && progressDialog.isShowing())
                            progressDialog.dismiss();

                        ApplicationConstant.displayErrorMessage(LocalSurveyListActivity.this,
                                response.body().getErrorCode().trim());
                    }

                } else {

                    if (progressDialog != null && progressDialog.isShowing())
                        progressDialog.dismiss();

                    try {
                        ApplicationConstant.displayMessageDialog(LocalSurveyListActivity.this,
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
                ApplicationConstant.displayMessageDialog(LocalSurveyListActivity.this, "Response", getString(R.string.net_speed_problem));

            }
        });
    }

    public void DeledeEntryDetails(){

        new DeledeEntryDetailsTask(surveyDao).execute();

    }

    private class DeledeEntryDetailsTask extends AsyncTask<Void, Void, Boolean> {
        SurveyDao surveyDao;

        public DeledeEntryDetailsTask(SurveyDao surveyDao) {
            this.surveyDao = surveyDao;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {

            try {
                surveyDao.deletePersonalDetailsRecord(surveyData);
                surveyDao.deleteFamilyDetailsRecord(familyData);
                surveyDao.deleteVendingDetailsRecord(vendingData);
                surveyDao.deleteBankingDetailsRecord(bankingData);
                surveyDao.deleteDocumentsDetailsRecord(documentsDetails);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return true;
        }

        @Override
        protected void onPostExecute(Boolean isDeleted) {

           if (isDeleted){
               AlertDialog.Builder builder = new AlertDialog.Builder(LocalSurveyListActivity.this);
               builder.setTitle("Local Survey");
               builder.setMessage("Saved successfully to server");
               builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       dialogInterface.dismiss();

                       getAllRoomData();

                   }
               });

               AlertDialog alertDialog = builder.create();
               alertDialog.setCancelable(false);
               alertDialog.setCanceledOnTouchOutside(false);
               alertDialog.show();
           }

        }
    }
}
