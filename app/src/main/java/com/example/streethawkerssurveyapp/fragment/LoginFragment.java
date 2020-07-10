package com.example.streethawkerssurveyapp.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.streethawkerssurveyapp.R;
import com.example.streethawkerssurveyapp.activities.LoginActivity;
import com.example.streethawkerssurveyapp.activities.MainActivity;
import com.example.streethawkerssurveyapp.activities.PersonalDetailsActivity;
import com.example.streethawkerssurveyapp.response_pack.LoginResponse;
import com.example.streethawkerssurveyapp.services_pack.ApiInterface;
import com.example.streethawkerssurveyapp.services_pack.ApiService;
import com.example.streethawkerssurveyapp.services_pack.ApplicationConstant;
import com.example.streethawkerssurveyapp.services_pack.CustomProgressDialog;
import com.example.streethawkerssurveyapp.utils.PrefUtils;

import java.io.IOException;
import java.util.HashMap;

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


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_login, container, false);

        bindView(rootView);


        Flag_Remember = PrefUtils.getFromPrefs(getActivity(), ApplicationConstant.USERDETAILS.FlagRemember, "");
        userName = PrefUtils.getFromPrefs(getActivity(), ApplicationConstant.USERDETAILS.UserName, "");
        passWord = PrefUtils.getFromPrefs(getActivity(), ApplicationConstant.USERDETAILS.UserPassword, "");


        if (Flag_Remember.equals("true")) {
            mEditUsername.setText(userName);
            mEditPassword.setText(passWord);
            mCheck_remember.setChecked(true);

        } else {
            mCheck_remember.setChecked(false);
        }

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


                View view1 = inflater.inflate( R.layout.layout_forgot_password, null );
                Button btn_send_password=view1.findViewById(R.id.btn_send_password);
                ImageView image_cancel=view1.findViewById(R.id.image_cancel);

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

                if (validate()){
                    String android_id = Settings.Secure.getString(getActivity().getContentResolver(), Settings.Secure.ANDROID_ID);

                    getLoginRole(mEditUsername.getText().toString().trim(),
                            mEditPassword.getText().toString().trim(),
                            android_id);
                }
            }
        });

        return rootView;
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

        mContext=getContext();

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
        Call<LoginResponse> call = apiservice.getLoginResponse(username,password);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();

                if (response.body() != null) {

                    if (response.body().isStatus()){
                        PrefUtils.saveToPrefs(getActivity(), ApplicationConstant.USERDETAILS.UserName, mEditUsername.getText().toString().trim());
                        PrefUtils.saveToPrefs(getActivity(), ApplicationConstant.USERDETAILS.UserPassword, mEditPassword.getText().toString().trim());
                        PrefUtils.saveToPrefs(getActivity(), ApplicationConstant.USERDETAILS.API_KEY, response.body().getData().getApiKey());

                        mContext.startActivity(new Intent(mContext, PersonalDetailsActivity.class));

                    }else {

                    }


                } else {
                    try {
                        ApplicationConstant.DisplayMessageDialog(getActivity(),"",response.errorBody().string());
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

}
