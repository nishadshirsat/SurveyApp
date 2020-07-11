package com.example.streethawkerssurveyapp.services_pack;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.text.InputFilter;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.streethawkerssurveyapp.R;
import com.example.streethawkerssurveyapp.activities.LoginActivity;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


public class ApplicationConstant {


    public static final String SURVEY_ID = "surveyId";
    public static String URI_NO = "";
    public static String SurveyId ="1.0";


    public class USERDETAILS {

        public static final String UserType = "usertype";
        public static final String UserName = "username";
        public static final String UserPassword = "password";
        public static final String LoginPassword = "login_password";
        public static final String EncreptedUserPassword = "encript_password";
        public static final String OwnerName = "OwnerName";
        public static final String MainBalance = "MainBalance";
        public static final String LastSeen = "LastSeen";
        public static final String FlagRemember = "remember";

        public static final String Api_AgentCode = "agent_code";
        public static final String Api_USERNAME = "api_username";
        public static final String Api_PASSWORD = "api_password";
        public static final String AEPSBalance = "aeps";

        public static final String API_KEY = "";
    }



    public static void displayToastMessage(Context mContext, String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
    }

    public static void hideKeypad(Activity activity) {
        if (activity != null) {
            try {
                InputMethodManager inputMethodManager = (InputMethodManager) activity
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(activity
                        .getCurrentFocus().getWindowToken(), 0);
            } catch (Exception e) {
            }
        }
    }

    public static void DisplayMessageDialog(Activity activity, String title, String message) {
        if (activity != null) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(activity);
            builder1.setMessage(message);
            builder1.setCancelable(true);
            builder1.setTitle(title);

            builder1.setPositiveButton(
                    "Okay",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
        }
    }


    public static void DisplayMessageForDialog(Context context, String title, String message) {
        if (context != null) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
            builder1.setMessage(message);
            builder1.setCancelable(true);
            builder1.setTitle(title);

            builder1.setPositiveButton(
                    "Okay",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
        }
    }

    public static void displayMessageDialog(Context context, String title, String message) {
        if (context != null) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
            builder1.setMessage(message);
            builder1.setCancelable(true);
            builder1.setTitle(title);

            builder1.setPositiveButton(
                    "Okay",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();
        }
    }


    public static String getDaysBeetweenTwoDays(String fromDate, String toDate) {
        SimpleDateFormat myFormat = new SimpleDateFormat("MM/dd/yyyy");


        try {
            Date date1 = myFormat.parse(fromDate);
            Date date2 = myFormat.parse(toDate);
            long diff = date2.getTime() - date1.getTime();
            return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + " Days";
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "0 Days";
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static String createImageFile(String file_image, String subfolder,Context context) throws IOException {

        String filePath = "";

        String folder_main = context.getResources().getString(R.string.app_name);

        File f = new File(Environment.getExternalStorageDirectory(), folder_main);
        if (!f.exists()) {
            f.mkdirs();
        }

        File sub_folder = new File(f, subfolder);
        if (!sub_folder.exists()) {
            sub_folder.mkdirs();
        }


        File f1 = new File(sub_folder, file_image);
        if (!f1.exists()) {
            filePath = f1.getAbsolutePath();

            return  filePath;
        }else {

            deleteCache(context,f1);

            File f1_new = new File(sub_folder, file_image);

            filePath = f1_new.getAbsolutePath();


            return  filePath;

        }
    }

    public static void deleteCache(Context context, File dir) {
        try {
            dir = context.getCacheDir();

            dir.delete();
//      deleteDir(dir);
        } catch (Exception e) { e.printStackTrace();}
    }


}
