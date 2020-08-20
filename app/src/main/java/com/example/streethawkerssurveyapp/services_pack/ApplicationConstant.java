package com.example.streethawkerssurveyapp.services_pack;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.text.InputFilter;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.streethawkerssurveyapp.R;
import com.example.streethawkerssurveyapp.activities.LoginActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


public class ApplicationConstant {


    public static final String SURVEY_ID = "surveyId";
    public static final String RECORDING = "recording";
    public static final String CORPORATION = "corporation";
    public static final String ZONE = "zone";
    public static final String WARD = "ward";
    public static final String CONTACT = "contact";
    public static final String AADHAR_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE1OTU2ODM5NjAsInVzZXJfY2xhaW1zIjp7InNjb3BlcyI6WyJyZWFkIl19LCJqdGkiOiI5MTI0NGM0ZC0zMTA3LTQ3ZmYtODhkYS0wMjgyYmU1NWE5NGIiLCJmcmVzaCI6ZmFsc2UsInR5cGUiOiJhY2Nlc3MiLCJpZGVudGl0eSI6ImRldi5rYXNoaXRzb2x1dGlvbkBhYWRoYWFyYXBpLmlvIiwibmJmIjoxNTk1NjgzOTYwLCJleHAiOjE5MTEwNDM5NjB9.ok8zT_-ZLNBTwNQHhMyoj8ROswamn-3eZiuRorAWFUQ";
    public static final String TVC = "tvc";
    public static final String AREA = "survey_area";
    public static final String LOCAL_SURVEYID = "local_surveyid";
    public static boolean ISLOCALDB = false;
    public static String URI_NO_ = "uri_no";
    public static String SurveyId ="";
    public static final long IMAGE_SIZE = 950;

    public static int orientation;



    public class USERDETAILS {

        public static final String UserType = "usertype";
        public static final String UserName = "username";
        public static final String UserPassword = "password";
        public static final String LoginPassword = "login_password";
        public static final String EncreptedUserPassword = "encript_password";
        public static final String Name = "name";
        public static final String Email = "mail";
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
            alert11.setCanceledOnTouchOutside(false);
            alert11.show();
        }
    }

    public static void displayErrorMessage(Context context, String errorCode) {
        String message = "";

        if (errorCode.trim().equals("8001")){
            message = "Authorization Header Empty";
        } else if (errorCode.trim().equals("8002")){
            message = "Authorization Token Invalid";
        }else if (errorCode.trim().equals("8003")){
            message = "Invalid Credentials";
        }else if (errorCode.trim().equals("8004")){
            message = "Undefined Data";
        }else if (errorCode.trim().equals("8005")){
            message = "User not authorized for Login";
        }else if (errorCode.trim().equals("8006")){
            message = "Invalid Data family Members";
        }else if (errorCode.trim().equals("8007")){
            message = "Database Save Error";
        }
        else if (errorCode.trim().equals("8008")){
            message = "Phone Number Required For Sending SMS";
        } else if (errorCode.trim().equals("8009")){
            message = "Criminal case Data Invalid";
        }else if (errorCode.trim().equals("8010")){
            message = "Land Assets Data Invalid";
        }else if (errorCode.trim().equals("8011")){
            message = "Survey with this barcode already present";
        }else if (errorCode.trim().equals("8012")){
            message = "Corporation required/ Ward required/Zone required";
        }else if (errorCode.trim().equals("8013")){
            message = "Type of Identity Required";
        }else if (errorCode.trim().equals("8014")){
            message = "Type of Vending History Proof Required";
        }
        else if (errorCode.trim().equals("8015")){
            message = "Invalid barcode scanned";
        }
   else if (errorCode.trim().equals("8016")){
            message = "Aadhaar Save Error";
        }

        if (context != null) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
            builder1.setMessage(message);
            builder1.setCancelable(true);
            builder1.setTitle("");

            builder1.setPositiveButton(
                    "Okay",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.setCanceledOnTouchOutside(false);
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




    private static int calculateInSampleSize(BitmapFactory.Options options,
                                             int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            // Calculate ratios of height and width to requested height and width
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            // Choose the smallest ratio as inSampleSize value, this will guarantee a final image
            // with both dimensions larger than or equal to the requested height and width.
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;

            // This offers some additional logic in case the image has a strange
            // aspect ratio. For example, a panorama may have a much larger
            // width than height. In these cases the total pixels might still
            // end up being too large to fit comfortably in memory, so we should
            // be more aggressive with sample down the image (=larger inSampleSize).

            final float totalPixels = width * height;

            // Anything more than 2x the requested pixels we'll sample down further
            final float totalReqPixelsCap = reqWidth * reqHeight * 2;

            while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
                inSampleSize++;
            }
        }
        return inSampleSize;
    }



    public static Bitmap CompressedBitmap(File file){

        int newWidth = 700;
        int newHeight = 800;
        float scaleWidth ;
        float scaleHeight;
        Bitmap compressedbitmap = null;
        Bitmap resizedBitmap = null;
        Bitmap actual_bitmap = null;
        int quantity = 50;
        long length_check;

        do {
            length_check = file.length()/ 1024;

            try {

                ExifInterface ei = new ExifInterface(file.getPath());
                orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
                compressedbitmap = BitmapFactory.decodeFile (file.getPath ());

               Matrix matrix = new Matrix();
                scaleWidth = ((float) newWidth) / compressedbitmap.getWidth();
                scaleHeight = ((float) newHeight) / compressedbitmap.getHeight();
                matrix.postScale(scaleWidth, scaleHeight);

                resizedBitmap = Bitmap.createBitmap(compressedbitmap, 0, 0, compressedbitmap.getWidth(), compressedbitmap.getHeight(), matrix, true);

                actual_bitmap = rotateImageIfRequired(resizedBitmap);

                actual_bitmap.compress (Bitmap.CompressFormat.JPEG, quantity, new FileOutputStream(file));


            }
            catch (Throwable t) {
                Log.e("ERROR", "Error compressing file." + t.toString ());
                t.printStackTrace ();
            }

            quantity = quantity - 10;

        }while (length_check > ApplicationConstant.IMAGE_SIZE);

        return actual_bitmap;

    }

    public static Bitmap rotateImageIfRequired(Bitmap img) throws IOException {

        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                return rotateImage(img, 90);
            case ExifInterface.ORIENTATION_ROTATE_180:
                return rotateImage(img, 180);
            case ExifInterface.ORIENTATION_ROTATE_270:
                return rotateImage(img, 270);
            default:
                return img;
        }


    }

    public static Bitmap rotateImage(Bitmap img, int degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        Bitmap rotatedImg = Bitmap.createBitmap(img, 0, 0, img.getWidth(), img.getHeight(), matrix, true);
        img.recycle();
        return rotatedImg;
    }


}
