package com.example.streethawkerssurveyapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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

import com.example.streethawkerssurveyapp.R;
import com.example.streethawkerssurveyapp.activities.PersonalDetailsActivity;

public class LoginFragment extends Fragment {

    Context mContext;

    private EditText mEditUsername;
    private EditText mEditPassword;
    private ImageView mImgEyePwd;
    private CheckBox mCheck_remember;
    private Button mButtonLogin;
    private TextView mTextForgotPwd;

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

                mContext.startActivity(new Intent(mContext, PersonalDetailsActivity.class));

            }
        });


        return rootView;
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
}
