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
import com.example.streethawkerssurveyapp.response_pack.LoginResponse;
import com.example.streethawkerssurveyapp.services_pack.ApiInterface;
import com.example.streethawkerssurveyapp.services_pack.ApiService;
import com.example.streethawkerssurveyapp.services_pack.ApplicationConstant;
import com.example.streethawkerssurveyapp.services_pack.CustomProgressDialog;

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

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_login, container, false);

        bindView(rootView);

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

                if (response.body() != null) {

                    if (response.body().isStatus()){
                        mContext.startActivity(new Intent(mContext, MainActivity.class));

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
