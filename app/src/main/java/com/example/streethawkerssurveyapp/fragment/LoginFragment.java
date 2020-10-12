package com.example.streethawkerssurveyapp.fragment;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.Settings;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.streethawkerssurveyapp.R;
import com.example.streethawkerssurveyapp.activities.CorporationZoneActivity;
import com.example.streethawkerssurveyapp.activities.DashboardActivity;
import com.example.streethawkerssurveyapp.response_pack.LoginResponse;
import com.example.streethawkerssurveyapp.services_pack.ApiInterface;
import com.example.streethawkerssurveyapp.services_pack.ApiService;
import com.example.streethawkerssurveyapp.services_pack.ApplicationConstant;
import com.example.streethawkerssurveyapp.services_pack.CustomProgressDialog;
import com.example.streethawkerssurveyapp.utils.PrefUtils;

import java.io.IOException;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {

    Context mContext;

    private EditText mEditUsername;
    private EditText mEditPassword;
    private ImageView mImgEyePwd;
    private CheckBox mCheck_remember;
    private Button mButtonLogin;
    private TextView mTextForgotPwd;

    ProgressDialog progressDialog;

    private String Flag_Remember = "", userName, passWord;

    android.app.AlertDialog alertDialog;
    private int READ_PHONE_REQUEST = 20;


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_login, container, false);

        bindView(rootView);

        Flag_Remember = PrefUtils.getFromPrefs(getActivity(), ApplicationConstant.USERDETAILS.FlagRemember, "");
        userName = PrefUtils.getFromPrefs(getActivity(), ApplicationConstant.USERDETAILS.UserName, "");
        passWord = PrefUtils.getFromPrefs(getActivity(), ApplicationConstant.USERDETAILS.UserPassword, "");
        onClickListners();

        if (Flag_Remember.equals("true")) {
            mEditUsername.setText(userName);
            mEditPassword.setText(passWord);

            if (!userName.trim().isEmpty() && !passWord.trim().isEmpty()){
                mCheck_remember.setChecked(true);

                mContext.startActivity(new Intent(mContext, DashboardActivity.class));
            }

        } else {
            mCheck_remember.setChecked(false);

            checkPermissionsFirst();
        }


        return rootView;
    }

    private void onClickListners() {

        mImgEyePwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (view.getId() == R.id.ImgEyePwd) {

                    if (mEditPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                        ((mImgEyePwd)).setImageResource(R.drawable.hide_pwd);

                        //Show Password
                        mEditPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    } else {
                        ((mImgEyePwd)).setImageResource(R.drawable.show_pwd);

                        //Hide Password
                        mEditPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

                    }
                }
            }
        });

        mCheck_remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mCheck_remember.isChecked()) {
                    PrefUtils.saveToPrefs(getActivity(), ApplicationConstant.USERDETAILS.FlagRemember, "true");

                } else {

                    PrefUtils.saveToPrefs(getActivity(), ApplicationConstant.USERDETAILS.FlagRemember, "false");
                    PrefUtils.saveToPrefs(getActivity(), ApplicationConstant.USERDETAILS.UserName, "");
                    PrefUtils.saveToPrefs(getActivity(), ApplicationConstant.USERDETAILS.UserPassword, "");

                }
            }
        });

        mTextForgotPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                    Intent intent=new Intent(mContext, ForgotPasswordActivity.class);
//                    mContext.startActivity(intent);


                View view1 = getActivity().getLayoutInflater().inflate(R.layout.layout_forgot_password, null);
                Button btn_send_password = view1.findViewById(R.id.btn_send_password);
                ImageView image_cancel = view1.findViewById(R.id.image_cancel);

                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

                final AlertDialog alertDialog = builder.create();

                alertDialog.setView(view1);
                alertDialog.getWindow().setSoftInputMode(
                        WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


                btn_send_password.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        alertDialog.dismiss();

                    }
                });
                image_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        alertDialog.dismiss();

                    }
                });
                alertDialog.show();

            }

        });

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validate()) {
                    checkForPermissions();
                }
            }
        });

    }

    private boolean validate() {
        if (!ApplicationConstant.isNetworkAvailable(getActivity())) {

            ApplicationConstant.displayMessageDialog(getActivity(), "No Internet Connection", "Please enable internet connection first to proceed");

            return false;
        } else if (mEditUsername.getText().toString().trim().isEmpty()) {
            mEditUsername.setError("Enter username");
            mEditUsername.requestFocus();

            return false;

        } else if (mEditPassword.getText().toString().trim().isEmpty()) {
            mEditPassword.setError("Enter password");
            mEditPassword.requestFocus();

            return false;

        }

        return true;

    }

    private void bindView(View rootView) {

        mContext = getContext();

        mEditUsername = (EditText) rootView.findViewById(R.id.EditUsername);
        mEditPassword = (EditText) rootView.findViewById(R.id.EditPassword);
        mImgEyePwd = (ImageView) rootView.findViewById(R.id.ImgEyePwd);
        mCheck_remember = (CheckBox) rootView.findViewById(R.id.check_remember);
        mButtonLogin = (Button) rootView.findViewById(R.id.ButtonLogin);
        mTextForgotPwd = (TextView) rootView.findViewById(R.id.TextForgotPwd);
    }


    private void getLoginRole(String username, String password, String picDeviceID) {

        progressDialog = CustomProgressDialog.getDialogue(getActivity());
        progressDialog.show();

        ApiInterface apiservice = ApiService.getApiClient().create(ApiInterface.class);
        Call<LoginResponse> call = apiservice.getLoginResponse(username, password);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();

                if (response.body() != null) {

                    if (response.body().isStatus()) {
                        PrefUtils.saveToPrefs(getActivity(), ApplicationConstant.USERDETAILS.UserName, mEditUsername.getText().toString().trim());
                        PrefUtils.saveToPrefs(getActivity(), ApplicationConstant.USERDETAILS.UserPassword, mEditPassword.getText().toString().trim());
                        PrefUtils.saveToPrefs(getActivity(), ApplicationConstant.USERDETAILS.Name, response.body().getData().getName());
                        PrefUtils.saveToPrefs(getActivity(), ApplicationConstant.USERDETAILS.Email, response.body().getData().getEmail());
                        PrefUtils.saveToPrefs(getActivity(), ApplicationConstant.USERDETAILS.UserType, response.body().getData().getRole());
                        PrefUtils.saveToPrefs(getActivity(), ApplicationConstant.USERDETAILS.API_KEY, response.body().getData().getApiKey());

                        PrefUtils.saveToPrefs(getActivity(),ApplicationConstant.CORPORATION,response.body().getData().getCorporation());
                        PrefUtils.saveToPrefs(getActivity(),ApplicationConstant.ZONE,response.body().getData().getZone());
                        PrefUtils.saveToPrefs(getActivity(),ApplicationConstant.WARD,response.body().getData().getWard());
                        PrefUtils.saveToPrefs(getActivity(),ApplicationConstant.TVC,response.body().getData().getTvc());
                        PrefUtils.saveToPrefs(getActivity(),ApplicationConstant.AREA,response.body().getData().getArea());


                        mContext.startActivity(new Intent(mContext, DashboardActivity.class));

                    } else {
                        ApplicationConstant.displayMessageDialog(getActivity(), "", response.body().getMsg());
                    }


                } else {
                    try {
                        ApplicationConstant.DisplayMessageDialog(getActivity(), "", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();
                ApplicationConstant.displayMessageDialog(getActivity(), "Response", t.getMessage());

            }
        });

    }

    public void checkForPermissions() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.MODIFY_AUDIO_SETTINGS)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.CAMERA) && ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.READ_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.READ_PHONE_STATE) && ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.READ_CONTACTS) && ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_COARSE_LOCATION) && ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION) && ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.RECORD_AUDIO) && ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.MODIFY_AUDIO_SETTINGS)) {
                // Show an explanation to the user asynchronously -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

//              Toast.makeText(getActivity(),"WAITING FOR USER RESPONSE",Toast.LENGTH_SHORT).show();

                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity());
                builder.setTitle("Permissions Needed");
                builder.setMessage("Want to access your camera and storage to set your profile");
                builder.setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        alertDialog.dismiss();

                        ActivityCompat.requestPermissions(getActivity(),
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
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity());
                builder.setTitle("Permissions Needed");
                builder.setMessage("Want to access your camera and storage to set your profile");
                builder.setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog.dismiss();

                        ActivityCompat.requestPermissions(getActivity(),
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
//            Toast.makeText(getActivity(),"Permission Granted",Toast.LENGTH_SHORT).show();

            String android_id = Settings.Secure.getString(getActivity().getContentResolver(), Settings.Secure.ANDROID_ID);

            getLoginRole(mEditUsername.getText().toString().trim(),
                    mEditPassword.getText().toString().trim(),
                    android_id);
        }
    }

    public void checkPermissionsFirst() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.MODIFY_AUDIO_SETTINGS)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.CAMERA) && ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.READ_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.READ_PHONE_STATE) && ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.READ_CONTACTS) && ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_COARSE_LOCATION) && ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION) && ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.RECORD_AUDIO) && ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.MODIFY_AUDIO_SETTINGS)) {
                // Show an explanation to the user asynchronously -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

//              Toast.makeText(getActivity(),"WAITING FOR USER RESPONSE",Toast.LENGTH_SHORT).show();

                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity());
                builder.setTitle("Permissions Needed");
                builder.setMessage("Want to access your camera and storage to set your profile");
                builder.setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        alertDialog.dismiss();

                        ActivityCompat.requestPermissions(getActivity(),
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
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity());
                builder.setTitle("Permissions Needed");
                builder.setMessage("Want to access your camera and storage to set your profile");
                builder.setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog.dismiss();

                        ActivityCompat.requestPermissions(getActivity(),
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
//            Toast.makeText(getActivity(),"Permission Granted",Toast.LENGTH_SHORT).show();

//            String android_id = Settings.Secure.getString(getActivity().getContentResolver(), Settings.Secure.ANDROID_ID);
//
//            getLoginRole(mEditUsername.getText().toString().trim(),
//                    mEditPassword.getText().toString().trim(),
//                    android_id);
        }
    }

}
