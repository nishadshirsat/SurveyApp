package com.example.streethawkerssurveyapp.pending_survey.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.streethawkerssurveyapp.R;
import com.example.streethawkerssurveyapp.activities.VendingDetailsActivity;
import com.example.streethawkerssurveyapp.activities.VendorsFamDetailsActivity;
import com.example.streethawkerssurveyapp.view_survey.adapters.ViewLandAssetsAdpater;
import com.example.streethawkerssurveyapp.pojo_class.FamilyMembers;
import com.example.streethawkerssurveyapp.pojo_class.LandAssets;
import com.example.streethawkerssurveyapp.response_pack.UpdateSurveyResponse;
import com.example.streethawkerssurveyapp.services_pack.ApiInterface;
import com.example.streethawkerssurveyapp.services_pack.ApiService;
import com.example.streethawkerssurveyapp.services_pack.ApplicationConstant;
import com.example.streethawkerssurveyapp.services_pack.CustomProgressDialog;
import com.example.streethawkerssurveyapp.utils.PrefUtils;
import com.example.streethawkerssurveyapp.view_survey.adapters.ViewFamilyDetailsAdpater;
import com.example.streethawkerssurveyapp.view_survey.response_pojo.FamilyMembersBeenSurveyedItem;
import com.example.streethawkerssurveyapp.view_survey.response_pojo.FamilyMembersItem;
import com.example.streethawkerssurveyapp.view_survey.response_pojo.LandFixedAssetsItem;
import com.example.streethawkerssurveyapp.view_survey.response_pojo.SingleSurveyDetails;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PendingVendorsFamDetailsActivity extends AppCompatActivity {

    private LinearLayout mLinearMain;

//    private EditText mEditFamName;
//    private EditText mEditFamRelation;
//    private EditText mEditFamAge;
//    private EditText mEditFamAadhar;

//    private EditText mEditPlot;
//    private EditText mEditHouseSize;
//    private EditText mEditArea;
//    private EditText mEditKuccha;
//    private EditText mEditRent;


    private RadioButton mRadioY;
    private RadioButton mRadioN;
    private EditText mEditName;
    private EditText mEditRelation;
    private EditText mEditAge;
    private EditText mEditAadhar;
    private Button mBtnNext;
    private Button mBtnPrevious;
    private TextView mTextAdd;
    RadioGroup RGFam;
    LinearLayout LinearYes,LinearDetails;
    private TextView TextAddAssets;

    private int FamCount = 0;
    private int FamInc = 0;

    private RecyclerView view_FamilyMembers;
    private RecyclerView view_LandAssets;

    private List<FamilyMembersItem> listFamily = new ArrayList<>();
    private List<LandFixedAssetsItem> listLandAssets = new ArrayList<>();
    private List<FamilyMembers> listSurveyedFamily = new ArrayList<>();

    String IS_Fam = "";

    ProgressDialog progressDialog;

    private LinearLayout mLinearOne,mLinearHead;
    private SingleSurveyDetails SingleSurveyData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendors_fam_details);

         SingleSurveyData = (SingleSurveyDetails) getIntent().getSerializableExtra("SurveyData");

        bindView();

        ApplicationConstant.SurveyId = PrefUtils.getFromPrefs(PendingVendorsFamDetailsActivity.this, ApplicationConstant.SURVEY_ID, "");


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("URI NO: "+ApplicationConstant.SurveyId);


        RGFam.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioSexButton = (RadioButton) findViewById(checkedId);
                IS_Fam = radioSexButton.getText().toString().trim();
                if (IS_Fam.contains("Yes")) {
                    LinearYes.setVisibility(View.VISIBLE);
                    IS_Fam = "1";

                } else {
                    LinearYes.setVisibility(View.GONE);
                }

            }
        });

        TextAddAssets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                View viewAdd = LayoutInflater.from(PendingVendorsFamDetailsActivity.this).inflate(R.layout.layout_add_land_assets, null);
                ImageView lImage_cancel = (ImageView) viewAdd.findViewById(R.id.image_cancel);
                final EditText lEditPlot = (EditText) viewAdd.findViewById(R.id.EditPlot);
                final EditText lEditHouseSize = (EditText)viewAdd. findViewById(R.id.EditHouseSize);
                final EditText lEditArea = (EditText) viewAdd.findViewById(R.id.EditArea);
                final EditText lEditKuccha = (EditText) viewAdd.findViewById(R.id.EditKuccha);
                final EditText lEditRent = (EditText) viewAdd.findViewById(R.id.EditRent);
                TextView lTextAddLandAssets = (TextView) viewAdd.findViewById(R.id.TextAddLandAssets);


                AlertDialog.Builder builder = new AlertDialog.Builder(PendingVendorsFamDetailsActivity.this);

                builder.setView(viewAdd);
                final AlertDialog alertDialog = builder.create();

                lImage_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });

                lTextAddLandAssets.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (lEditPlot.getText().toString().trim().isEmpty()){
                            lEditPlot.setError("enter plot");
                            lEditPlot.requestFocus();
                        }else  if (lEditHouseSize.getText().toString().trim().isEmpty()){
                            lEditHouseSize.setError("enter house size");
                            lEditHouseSize.requestFocus();
                        }else if (lEditArea.getText().toString().trim().isEmpty()){
                            lEditArea.setError("enter area");
                            lEditArea.requestFocus();
                        }else if (lEditKuccha.getText().toString().trim().isEmpty()){
                            lEditKuccha.setError("enter kuccha");
                            lEditKuccha.requestFocus();
                        }else if (lEditRent.getText().toString().trim().isEmpty()){
                            lEditRent.setError("enter rent");
                            lEditRent.requestFocus();
                        }else {


                            LandFixedAssetsItem landAssets = new LandFixedAssetsItem(
                                    lEditArea.getText().toString().trim(),
                                    lEditHouseSize.getText().toString().trim(),
                                    lEditKuccha.getText().toString().trim(),
                                    lEditPlot.getText().toString().trim(),
                                    lEditRent.getText().toString().trim()
                            );
                            listLandAssets.add(landAssets);

                            ViewLandAssetsAdpater landAssetsAdpater = new ViewLandAssetsAdpater(PendingVendorsFamDetailsActivity.this);
                            landAssetsAdpater.setDetails(listLandAssets);

                            view_LandAssets.setAdapter(landAssetsAdpater);
                            alertDialog.dismiss();

                        }

                    }
                });


                alertDialog.show();

            }
        });

        mTextAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (FamCount < FamInc ){
////                    viewAddMembers("Members");
////                    passengerItemAdapter1.notifyDataSetChanged();
//                } else {
//                    ApplicationConstant.DisplayMessageDialog(VendorsFamDetailsActivity.this,"","Added "+FamCount+" Family Members Already");
//                }

                View viewAdd = LayoutInflater.from(PendingVendorsFamDetailsActivity.this).inflate(R.layout.layout_add_family, null);
                final EditText fEditFamName = (EditText) viewAdd.findViewById(R.id.EditFamName);
                final EditText  fEditFamRelation = (EditText) viewAdd.findViewById(R.id.EditFamRelation);
                final EditText fEditFamAge = (EditText) viewAdd.findViewById(R.id.EditFamAge);
                final EditText fEditFamAadhar = (EditText) viewAdd.findViewById(R.id.EditFamAadhar);
               TextView fTextAddMember = (TextView)viewAdd. findViewById(R.id.TextAddMember);
                ImageView image_cancel = (ImageView)viewAdd. findViewById(R.id.image_cancel);

                AlertDialog.Builder builder = new AlertDialog.Builder(PendingVendorsFamDetailsActivity.this);

                builder.setView(viewAdd);
                final AlertDialog alertDialog = builder.create();

                image_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });

                fTextAddMember.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (fEditFamName.getText().toString().trim().isEmpty()){
                            fEditFamName.setError("enter name");
                            fEditFamName.requestFocus();
                        }else  if (fEditFamRelation.getText().toString().trim().isEmpty()){
                            fEditFamRelation.setError("enter relation");
                            fEditFamRelation.requestFocus();
                        }else if (fEditFamAge.getText().toString().trim().isEmpty()){
                            fEditFamAge.setError("enter age");
                            fEditFamAge.requestFocus();
                        }else if (fEditFamAadhar.getText().toString().trim().isEmpty()){
                            fEditFamAadhar.setError("enter aadhar");
                            fEditFamAadhar.requestFocus();
                        }else if (fEditFamAadhar.getText().toString().trim().length()!=12){
                            fEditFamAadhar.setError("enter correct aadhar");
                            fEditFamAadhar.requestFocus();
                        }else {
                            FamilyMembersItem familyMembers = new FamilyMembersItem(
                                    fEditFamRelation.getText().toString().trim(),
                                    fEditFamName.getText().toString().trim(),
                                    fEditFamAadhar.getText().toString().trim(),
                                    fEditFamAge.getText().toString().trim());


                            listFamily.add(familyMembers);
                            ViewFamilyDetailsAdpater familyDetailsAdpater = new ViewFamilyDetailsAdpater(PendingVendorsFamDetailsActivity.this);
                            familyDetailsAdpater.setDetails(listFamily);

                            view_FamilyMembers.setAdapter(familyDetailsAdpater);
                            alertDialog.dismiss();

                        }

                    }
                });


                alertDialog.show();


            }
        });

        mBtnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    onBackPressed();


            }
        });


        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mLinearHead.getVisibility() == View.VISIBLE) {

                    mLinearHead.setVisibility(View.GONE);
                    mLinearOne.setVisibility(View.VISIBLE);
                    mBtnPrevious.setVisibility(View.VISIBLE);

                    mBtnNext.setText("Submit");


                }else if (mLinearOne.getVisibility() == View.VISIBLE) {

                    GsonBuilder gsonBuilder = new GsonBuilder();
                    Gson gson = gsonBuilder.create();

                    String JSONObject_Family = gson.toJson(listFamily);

//                ApplicationConstant.DisplayMessageDialog(VendorsFamDetailsActivity.this,"",JSONObject_Family);

                    if(validate()){
                        UpdateFamilySurvey();

                    }

                }

            }
        });

        setFamilyData();


    }

    private void setFamilyData() {

        if (SingleSurveyData.getFamilyMembers() != null){

            listFamily.addAll(SingleSurveyData.getFamilyMembers());
            ViewFamilyDetailsAdpater familyDetailsAdpater = new ViewFamilyDetailsAdpater(PendingVendorsFamDetailsActivity.this);
            familyDetailsAdpater.setDetails(listFamily);

            view_FamilyMembers.setAdapter(familyDetailsAdpater);

        }

        if (SingleSurveyData.getLandFixedAssets() != null){

            listLandAssets.addAll(SingleSurveyData.getLandFixedAssets());

            ViewLandAssetsAdpater landAssetsAdpater = new ViewLandAssetsAdpater(PendingVendorsFamDetailsActivity.this);
            landAssetsAdpater.setDetails(listLandAssets);

            view_LandAssets.setAdapter(landAssetsAdpater);

        }

        if (SingleSurveyData.getFamilyMembersBeenSurveyed() != null ){

         mRadioY.setChecked(true);
         FamilyMembersBeenSurveyedItem beenSurveyedItem =  SingleSurveyData.getFamilyMembersBeenSurveyed().get(0);

         mEditName.setText(beenSurveyedItem.getFamilyMemberName());
         mEditRelation.setText(beenSurveyedItem.getFamilyMemberRelationship());
         mEditAge.setText(beenSurveyedItem.getFamilyMemberAge());
         mEditAadhar.setText(beenSurveyedItem.getFamilyMemberAdhaar());

        }else {
            mRadioN.setChecked(true);

        }

    }

    private boolean validate() {

        if (!ApplicationConstant.isNetworkAvailable(PendingVendorsFamDetailsActivity.this)) {

            ApplicationConstant.displayMessageDialog(PendingVendorsFamDetailsActivity.this, "No Internet Connection", "Please enable internet connection first to proceed");

            return false;
        }
        else if (listFamily.isEmpty()){
            ApplicationConstant.DisplayMessageDialog(PendingVendorsFamDetailsActivity.this,"","Add family members");
            return false;
        }

//        else if (listLandAssets.isEmpty()){
//            ApplicationConstant.DisplayMessageDialog(VendorsFamDetailsActivity.this,"","Add Land Assets");
//            return false;
//        }

//        else if (mEditFamName.getText().toString().trim().isEmpty()) {
//            mEditFamName.setError("Enter Family Name");
//            mEditFamName.requestFocus();
//            return false;
//        }else if (mEditFamRelation.getText().toString().trim().isEmpty()) {
//            mEditFamRelation.setError("Enter Family Relation");
//            mEditFamRelation.requestFocus();
//            return false;
//        }else if (mEditFamAge.getText().toString().trim().isEmpty()) {
//            mEditFamAge.setError("Enter Family Age");
//            mEditFamAge.requestFocus();
//            return false;
//        }else if (mEditFamAadhar.getText().toString().trim().isEmpty()) {
//            mEditFamAadhar.setError("Enter Family Aadhar No");
//            mEditFamAadhar.requestFocus();
//            return false;
//        }


//        else if (mEditPlot.getText().toString().trim().isEmpty()) {
//            mEditPlot.setError("Enter Plot");
//            mEditPlot.requestFocus();
//            return false;
//        }else if (mEditHouseSize.getText().toString().trim().isEmpty()) {
//            mEditHouseSize.setError("Enter House Size");
//            mEditHouseSize.requestFocus();
//            return false;
//        }else if (mEditArea.getText().toString().trim().isEmpty()) {
//            mEditArea.setError("Enter Area");
//            mEditArea.requestFocus();
//            return false;
//        }else if (mEditKuccha.getText().toString().trim().isEmpty()) {
//            mEditKuccha.setError("Enter Kuccha");
//            mEditKuccha.requestFocus();
//            return false;
//        }else if (mEditRent.getText().toString().trim().isEmpty()) {
//            mEditRent.setError("Enter Rent");
//            mEditRent.requestFocus();
//            return false;
//        }

//        else if (mEditPlot.getText().toString().trim().isEmpty()) {
//            mEditPlot.setError("Enter Plot");
//            mEditPlot.requestFocus();
//            return false;
//        }else if (mEditHouseSize.getText().toString().trim().isEmpty()) {
//            mEditHouseSize.setError("Enter House Size");
//            mEditHouseSize.requestFocus();
//            return false;
//        }else if (mEditArea.getText().toString().trim().isEmpty()) {
//            mEditArea.setError("Enter Area");
//            mEditArea.requestFocus();
//            return false;
//        }else if (mEditKuccha.getText().toString().trim().isEmpty()) {
//            mEditKuccha.setError("Enter Kuccha");
//            mEditKuccha.requestFocus();
//            return false;
//        }else if (mEditRent.getText().toString().trim().isEmpty()) {
//            mEditRent.setError("Enter Rent");
//            mEditRent.requestFocus();
//            return false;
//        }
        else if (LinearYes.getVisibility() == View.VISIBLE) {
            if (mEditName.getText().toString().trim().isEmpty()) {
                mEditName.setError("Enter Name");
                mEditName.requestFocus();
                return false;
            } else if (mEditRelation.getText().toString().trim().isEmpty()) {
                mEditRelation.setError("Enter Relation");
                mEditRelation.requestFocus();
                return false;
            } else if (mEditAge.getText().toString().trim().isEmpty()) {
                mEditAge.setError("Enter Age");
                mEditAge.requestFocus();
                return false;
            } else if (mEditAadhar.getText().toString().trim().isEmpty()) {
                mEditAadhar.setError("Enter Aadhar Number");
                mEditAadhar.requestFocus();
                return false;
            }
        }


        return true;
    }

    private void bindView() {
        mLinearOne = (LinearLayout) findViewById(R.id.LinearOne);
        mLinearHead = (LinearLayout) findViewById(R.id.LinearHead);
        mLinearMain = (LinearLayout) findViewById(R.id.LinearMain);

//        mEditFamName = (EditText) findViewById(R.id.EditFamName);
//        mEditFamRelation = (EditText) findViewById(R.id.EditFamRelation);
//
//        mEditFamAge = (EditText) findViewById(R.id.EditFamAge);
//        mEditFamAadhar = (EditText) findViewById(R.id.EditFamAadhar);

//        mEditPlot = (EditText) findViewById(R.id.EditPlot);
//        mEditHouseSize = (EditText) findViewById(R.id.EditHouseSize);
//        mEditArea = (EditText) findViewById(R.id.EditArea);
//        mEditKuccha = (EditText) findViewById(R.id.EditKuccha);
//        mEditRent = (EditText) findViewById(R.id.EditRent);
        mRadioY = (RadioButton) findViewById(R.id.RadioY);
        mRadioN = (RadioButton) findViewById(R.id.RadioN);
        mEditName = (EditText) findViewById(R.id.EditName);
        mEditRelation = (EditText) findViewById(R.id.EditRelation);
        mEditAge = (EditText) findViewById(R.id.EditAge);
        mEditAadhar = (EditText) findViewById(R.id.EditAadhar);
        mBtnNext = (Button) findViewById(R.id.BtnNext);
        mBtnPrevious = (Button) findViewById(R.id.BtnPrevious);
        mTextAdd = (TextView) findViewById(R.id.TextAdd);
        RGFam=(RadioGroup)findViewById(R.id.RGFam);
        LinearYes =(LinearLayout) findViewById(R.id.LinearYes);
        TextAddAssets = (TextView) findViewById(R.id.TextAddAssets);


        view_FamilyMembers = (RecyclerView) findViewById(R.id.view_FamilyMembers);
        view_LandAssets = (RecyclerView) findViewById(R.id.view_LandAssets);

        view_FamilyMembers.setLayoutManager(new LinearLayoutManager(PendingVendorsFamDetailsActivity.this));
        view_LandAssets.setLayoutManager(new LinearLayoutManager(PendingVendorsFamDetailsActivity.this));
    }


    private void UpdateFamilySurvey() {

        JSONObject objectFamily = null;



        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
            String json_family = new Gson().toJson(listFamily);

        String JSONObject_Family = gson.toJson(listFamily);

        Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
//        String json_family = prettyGson.toJson(JSONObject_Family);



//        try {
//            JSONArray jsonArray = new JSONArray(jsonArray);
//
//            objectFamily = new JSONObject(json_family);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        String JSONObject_LandAsset = gson.toJson(listLandAssets);
//        String json_landAssets = prettyGson.toJson(JSONObject_LandAsset);

        String json_landAssets = new Gson().toJson(listLandAssets);


        FamilyMembers familyMembers = new FamilyMembers(mEditName.getText().toString().trim(),
                mEditRelation.getText().toString().trim(),
                mEditAge.getText().toString().trim(),
                mEditAadhar.getText().toString().trim());

        listSurveyedFamily.add(familyMembers);

//        String JSONObject_S_Family = gson.toJson(listSurveyedFamily);
//        String json_surveyFam = prettyGson.toJson(JSONObject_S_Family);

        String json_surveyFam = new Gson().toJson(listSurveyedFamily);


        String UNiq_Id =  PrefUtils.getFromPrefs(PendingVendorsFamDetailsActivity.this,ApplicationConstant.URI_NO_,"");
        String CORPORATION =   PrefUtils.getFromPrefs(PendingVendorsFamDetailsActivity.this,ApplicationConstant.CORPORATION,"");
        String ZONE =  PrefUtils.getFromPrefs(PendingVendorsFamDetailsActivity.this,ApplicationConstant.ZONE,"");
        String WARD =  PrefUtils.getFromPrefs(PendingVendorsFamDetailsActivity.this,ApplicationConstant.WARD,"");

        RequestBody CORPORATION_ = RequestBody.create(MediaType.parse("multipart/form-data"), CORPORATION);
        RequestBody ZONE_ = RequestBody.create(MediaType.parse("multipart/form-data"), ZONE);
        RequestBody WARD_ = RequestBody.create(MediaType.parse("multipart/form-data"), WARD);

        progressDialog = CustomProgressDialog.getDialogue(PendingVendorsFamDetailsActivity.this);
        progressDialog.show();


        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + PrefUtils.getFromPrefs(PendingVendorsFamDetailsActivity.this, ApplicationConstant.USERDETAILS.API_KEY, ""));


        RequestBody URI_NO_ = RequestBody.create(MediaType.parse("multipart/form-data"), UNiq_Id);
        RequestBody family_members_ = RequestBody.create(MediaType.parse("multipart/form-data"), "1");
        RequestBody json_family_ = RequestBody.create(MediaType.parse("multipart/form-data"), json_family);
        RequestBody json_landAssets_ = RequestBody.create(MediaType.parse("multipart/form-data"), json_landAssets);
        RequestBody isFamilySurveyed = RequestBody.create(MediaType.parse("multipart/form-data"), IS_Fam);
        RequestBody json_surveyFam_ = RequestBody.create(MediaType.parse("multipart/form-data"), json_surveyFam);



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

                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();

                if (response.body() != null) {

                    if (response.body().isStatus()) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(PendingVendorsFamDetailsActivity.this);
                        builder.setTitle("Vending Family Details");
                        builder.setMessage("Saved successfully");
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                Intent intent = new Intent(PendingVendorsFamDetailsActivity.this, PendingVendingDetailsActivity.class);
                                intent.putExtra("SurveyData",SingleSurveyData);
                                startActivity(intent);
                            }
                        });

                        AlertDialog alertDialog = builder.create();
                        alertDialog.setCancelable(false);
                        alertDialog.setCanceledOnTouchOutside(false);
                        alertDialog.show();

//                        ApplicationConstant.displayToastMessage(VendorsFamDetailsActivity.this,
//                                "Vending Details saved successfully");


                    } else {

                        ApplicationConstant.displayMessageDialog(PendingVendorsFamDetailsActivity.this,
                                "Response",
                                response.body().getMessage());
                    }

                }else {

                    try {
                        ApplicationConstant.displayMessageDialog(PendingVendorsFamDetailsActivity.this,
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
                ApplicationConstant.displayMessageDialog(PendingVendorsFamDetailsActivity.this, "Response", getString(R.string.net_speed_problem));

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {

        if (mLinearHead.getVisibility() == View.VISIBLE) {

            super.onBackPressed();


        }else if (mLinearOne.getVisibility() == View.VISIBLE) {

            mLinearHead.setVisibility(View.VISIBLE);
            mLinearOne.setVisibility(View.GONE);
            mBtnPrevious.setVisibility(View.VISIBLE);

        }

    }
}
