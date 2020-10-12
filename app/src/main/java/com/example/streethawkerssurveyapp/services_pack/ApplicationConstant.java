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
    public static final String ISLANGSELECTED = "false";
    public static boolean ISLOCALDB = false;
    public static String URI_NO_ = "uri_no";
    public static String SurveyId = "";
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

        if (errorCode.trim().equals("8001")) {
            message = "Authorization Header Empty";
        } else if (errorCode.trim().equals("8002")) {
            message = "Authorization Token Invalid";
        } else if (errorCode.trim().equals("8003")) {
            message = "Invalid Credentials";
        } else if (errorCode.trim().equals("8004")) {
            message = "Undefined Data";
        } else if (errorCode.trim().equals("8005")) {
            message = "User not authorized for Login";
        } else if (errorCode.trim().equals("8006")) {
            message = "Invalid Data family Members";
        } else if (errorCode.trim().equals("8007")) {
            message = "Database Save Error";
        } else if (errorCode.trim().equals("8008")) {
            message = "Phone Number Required For Sending SMS";
        } else if (errorCode.trim().equals("8009")) {
            message = "Criminal case Data Invalid";
        } else if (errorCode.trim().equals("8010")) {
            message = "Land Assets Data Invalid";
        } else if (errorCode.trim().equals("8011")) {
            message = "Survey with this barcode already present";
        } else if (errorCode.trim().equals("8012")) {
            message = "Corporation required/ Ward required/Zone required";
        } else if (errorCode.trim().equals("8013")) {
            message = "Type of Identity Required";
        } else if (errorCode.trim().equals("8014")) {
            message = "Type of Vending History Proof Required";
        } else if (errorCode.trim().equals("8015")) {
            message = "Invalid barcode scanned";
        } else if (errorCode.trim().equals("8016")) {
            message = "Survey Type is required";
        } else if (errorCode.trim().equals("8017")) {
            message = "date or search_key or type is required";
        } else if (errorCode.trim().equals("8018")) {
            message = "No Surveyors Allotted";
        }else if (errorCode.trim().equals("8019")) {
            message = "Survey with this Aadhaar Number already present";
        }else if (errorCode.trim().equals("8020")) {
            message = "Area Not Alloted";
        }else if (errorCode.trim().equals("8021")) {
            message = "Ward Not Alloted";
        }else if (errorCode.trim().equals("8022")) {
            message = "Zone Not Alloted";
        }else if (errorCode.trim().equals("8023")) {
            message = "User Account Disabled";
        }else if (errorCode.trim().equals("8024")) {
            message = "Surveyor ID Required";
        }else if (errorCode.trim().equals("8999")) {
            message = "Server Error";
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

    public static void displayAadharErrorMessage(Context context, String errorCode) {
        String message = "";

        if (errorCode.trim().equals("100")) {
            message = "Pi (basic) attributes of demographic data did not match";
        } else if (errorCode.trim().equals("200")) {
            message = "Pa (address) attributes of demographic data did not match";
        } else if (errorCode.trim().equals("300")) {
            message = "Biometric data did not match.";
        } else if (errorCode.trim().equals("310")) {
            message = "Duplicate fingers used";
        } else if (errorCode.trim().equals("311")) {
            message = "Duplicate Irises used";
        } else if (errorCode.trim().equals("312")) {
            message = "FMR and FIR cannot be used in same transaction";
        } else if (errorCode.trim().equals("313")) {
            message = "Single FIR record contains more than one finger";
        } else if (errorCode.trim().equals("314")) {
            message = "Number of FMR/FIR should not exceed 10";
        } else if (errorCode.trim().equals("315")) {
            message = "Number of IIR should not exceed 2";
        } else if (errorCode.trim().equals("316")) {
            message = "Number of FID should not exceed 1";
        } else if (errorCode.trim().equals("330")) {
            message = "Biometrics locked by Aadhaar holder";
        } else if (errorCode.trim().equals("400")) {
            message = "Invalid OTP value";
        } else if (errorCode.trim().equals("402")) {
            message = "txn value did not match with “txn” value used in Request OTP API";
        } else if (errorCode.trim().equals("500")) {
            message = "Invalid encryption of session key";
        } else if (errorCode.trim().equals("501")) {
            message = "Invalid certificate identifier in “ci” attribute of Skey";
        } else if (errorCode.trim().equals("502")) {
            message = "Invalid encryption of PID";
        } else if (errorCode.trim().equals("503")) {
            message = "Invalid encryption of Hmac";
        } else if (errorCode.trim().equals("504")) {
            message = "Session key re-initiation required due to expiry or key out of sync";
        } else if (errorCode.trim().equals("505")) {
            message = "Synchronized Key usage not allowed for the AUA";
        } else if (errorCode.trim().equals("510")) {
            message = "Invalid Auth XML format";
        } else if (errorCode.trim().equals("511")) {
            message = "Invalid PID XML format";
        } else if (errorCode.trim().equals("512")) {
            message = "Invalid Aadhaar holder consent in “rc” attribute of Auth";
        } else if (errorCode.trim().equals("520")) {
            message = "Invalid tid value";
        } else if (errorCode.trim().equals("521")) {
            message = "Invalid dc code under Meta tag";
        } else if (errorCode.trim().equals("524")) {
            message = "Invalid “mi” code under Meta tag";
        } else if (errorCode.trim().equals("527")) {
            message = "Invalid “mc” code under Meta tag";
        } else if (errorCode.trim().equals("530")) {
            message = "Invalid authenticator code.";
        } else if (errorCode.trim().equals("540")) {
            message = "Invalid Auth XML version";
        } else if (errorCode.trim().equals("541")) {
            message = "Invalid PID XML version";
        } else if (errorCode.trim().equals("542")) {
            message = "AUA not authorized for ASA. This error will be returned if AUA and\n" +
                    "ASA do not have linking in the portal";
        } else if (errorCode.trim().equals("543")) {
            message = "– Sub-AUA not associated with “AUA”. This error will be returned if\n" +
                    "Sub-AUA specified in “sa” attribute is not added as “Sub-AUA” in portal.";
        } else if (errorCode.trim().equals("550")) {
            message = "Invalid “Uses” element attributes.";
        } else if (errorCode.trim().equals("551")) {
            message = "Invalid “tid” value";
        } else if (errorCode.trim().equals("553")) {
            message = "Registered devices currently not supported. This feature is being\n" +
                    "implemented in a phased manner";
        } else if (errorCode.trim().equals("554")) {
            message = "Public devices are not allowed to be used";
        } else if (errorCode.trim().equals("555")) {
            message = "rdsId is invalid and not part of certification registry";
        } else if (errorCode.trim().equals("556")) {
            message = "rdsVer is invalid and not part of certification registry";
        } else if (errorCode.trim().equals("557")) {
            message = "dpId is invalid and not part of certification registry";
        } else if (errorCode.trim().equals("558")) {
            message = "Invalid dih";
        } else if (errorCode.trim().equals("559")) {
            message = "Device Certificat has expired";
        } else if (errorCode.trim().equals("560")) {
            message = "DP Master Certificate has expired";
        } else if (errorCode.trim().equals("561")) {
            message = "Request expired (“Pid->ts” value is older than N hours where N is\n" +
                    "a configured threshold in authentication server).";
        } else if (errorCode.trim().equals("562")) {
            message = "Timestamp value is future time (value specified “Pid->ts” is ahead\n" +
                    "of authentication server time beyond acceptable threshold)";
        } else if (errorCode.trim().equals("563")) {
            message = "Duplicate request (this error occurs when exactly same\n" +
                    "authentication request was re-sent by AUA).";
        } else if (errorCode.trim().equals("564")) {
            message = "HMAC Validation failed";
        } else if (errorCode.trim().equals("565")) {
            message = "AUA license has expired";
        } else if (errorCode.trim().equals("566")) {
            message = "Invalid non-decryptable license key";
        } else if (errorCode.trim().equals("567")) {
            message = "– Invalid input(this error occurs when unsupported characters were\n" +
                    "found in Indian language values, “lname” or “lav”).";
        } else if (errorCode.trim().equals("568")) {
            message = "Unsupported Language.";
        } else if (errorCode.trim().equals("569")) {
            message = "Digital signature verification failed (means that authentication\n" +
                    "request XML was modified after it was signed).";
        } else if (errorCode.trim().equals("570")) {
            message = "Invalid key info in digital signature (this means that certificate used\n" +
                    "for signing the authentication request is not valid – it is either expired, or\n" +
                    "does not belong to the AUA or is not created by a well-known Certification\n" +
                    "Authority)";
        } else if (errorCode.trim().equals("571")) {
            message = "PIN requires reset";
        } else if (errorCode.trim().equals("572")) {
            message = "Invalid biometric position";
        } else if (errorCode.trim().equals("573")) {
            message = "Pi usage not allowed as per license.";
        } else if (errorCode.trim().equals("574")) {
            message = "Pa usage not allowed as per license.";
        } else if (errorCode.trim().equals("575")) {
            message = "Pfa usage not allowed as per license";
        } else if (errorCode.trim().equals("576")) {
            message = "FMR usage not allowed as per license";
        } else if (errorCode.trim().equals("577")) {
            message = "FIR usage not allowed as per license.";
        } else if (errorCode.trim().equals("578")) {
            message = "IIR usage not allowed as per license";
        } else if (errorCode.trim().equals("579")) {
            message = "OTP usage not allowed as per license";
        } else if (errorCode.trim().equals("580")) {
            message = "PIN usage not allowed as per license";
        } else if (errorCode.trim().equals("581")) {
            message = "Fuzzy matching usage not allowed as per license";
        } else if (errorCode.trim().equals("582")) {
            message = "– Local language usage not allowed as per license";
        } else if (errorCode.trim().equals("586")) {
            message = "FID usage not allowed as per license. This feature is being\n" +
                    "implemented in a phased manner";
        } else if (errorCode.trim().equals("587")) {
            message = "Name space not allowed.";
        } else if (errorCode.trim().equals("588")) {
            message = "Registered device not allowed as per license.\n";
        } else if (errorCode.trim().equals("590")) {
            message = "Public device not allowed as per license";
        } else if (errorCode.trim().equals("710")) {
            message = "Missing “Pi” data as specified in “Uses";
        } else if (errorCode.trim().equals("720")) {
            message = "Missing “Pa” data as specified in Uses";
        } else if (errorCode.trim().equals("721")) {
            message = "Missing “Pfa” data as specified in “Uses”.";
        } else if (errorCode.trim().equals("730")) {
            message = "– Missing PIN data as specified in Uses";
        } else if (errorCode.trim().equals("740")) {
            message = "Missing OTP data as specified in Uses";
        } else if (errorCode.trim().equals("800")) {
            message = "Invalid biometric data.";
        } else if (errorCode.trim().equals("810")) {
            message = "Missing biometric data as specified in Uses";
        } else if (errorCode.trim().equals("811")) {
            message = "Missing biometric data in CIDR for the given Aadhaar number";
        } else if (errorCode.trim().equals("812")) {
            message = "Aadhaar holder has not done “Best Finger Detection”. Application\n" +
                    "should initiate BFD to help Aadhaar holder identify their best fingers";
        } else if (errorCode.trim().equals("820")) {
            message = "Missing or empty value for “bt” attribute in “Uses” element";
        } else if (errorCode.trim().equals("821")) {
            message = "Invalid value in the “bt” attribute of “Uses” element.";
        } else if (errorCode.trim().equals("822")) {
            message = "Invalid value in the “bs” attribute of “Bio” element within Pid";
        } else if (errorCode.trim().equals("901")) {
            message = "No authentication data found in the request (this corresponds to a\n" +
                    "scenario wherein none of the auth data – Demo, Pv, or Bios – is present).";
        } else if (errorCode.trim().equals("902")) {
            message = "Invalid “dob” value in the “Pi” element (this corresponds to a\n" +
                    "scenarios wherein “dob” attribute is not of the format “YYYY” or “YYYYMM-DD”, or the age is not in valid range).";
        } else if (errorCode.trim().equals("911")) {
            message = "Invalid “mv” value in the “Pfa” element";
        } else if (errorCode.trim().equals("912")) {
            message = "Invalid “ms” value";
        } else if (errorCode.trim().equals("913")) {
            message = "Both “Pa” and “Pfa” are present in the authentication request (Pa\n" +
                    "and Pfa are mutually exclusive)";
        } else if (errorCode.trim().equals("930")) {
            message = "Technical error that are internal to authentication server";
        } else if (errorCode.trim().equals("930")) {
            message = "Technical error that are internal to authentication server";
        } else if (errorCode.trim().equals("939")) {
            message = "Technical error that are internal to authentication server";
        } else if (errorCode.trim().equals("940")) {
            message = "Unauthorized ASA channel.";
        } else if (errorCode.trim().equals("941")) {
            message = "Unspecified ASA channel.";
        } else if (errorCode.trim().equals("950")) {
            message = "OTP store related technical error";
        } else if (errorCode.trim().equals("951")) {
            message = "Biometric lock related technical error.";
        } else if (errorCode.trim().equals("980")) {
            message = "Unsupported option";
        } else if (errorCode.trim().equals("995")) {
            message = "Aadhaar suspended by competent authority";
        } else if (errorCode.trim().equals("996")) {
            message = "Aadhaar cancelled (Aadhaar is no in authenticable status)";
        } else if (errorCode.trim().equals("997")) {
            message = "Aadhaar suspended (Aadhaar is not in authenticatable status).";
        } else if (errorCode.trim().equals("998")) {
            message = "Invalid Aadhaar Number.";
        } else if (errorCode.trim().equals("999")) {
            message = "Unknown error";
        } else {
            message = errorCode.trim();

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
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static String createImageFile(String file_image, String subfolder, Context context) throws IOException {

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

            return filePath;
        } else {

            deleteCache(context, f1);

            File f1_new = new File(sub_folder, file_image);

            filePath = f1_new.getAbsolutePath();


            return filePath;

        }
    }

    public static void deleteCache(Context context, File dir) {
        try {
            dir = context.getCacheDir();

            dir.delete();
//      deleteDir(dir);
        } catch (Exception e) {
            e.printStackTrace();
        }
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


    public static Bitmap CompressedBitmap(File file) {

        int newWidth = 700;
        int newHeight = 800;
        float scaleWidth;
        float scaleHeight;
        Bitmap compressedbitmap = null;
        Bitmap resizedBitmap = null;
        Bitmap actual_bitmap = null;
        int quantity = 50;
        long length_check;

        do {
            length_check = file.length() / 1024;

            try {

                ExifInterface ei = new ExifInterface(file.getPath());
                orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
                compressedbitmap = BitmapFactory.decodeFile(file.getPath());

                Matrix matrix = new Matrix();
                scaleWidth = ((float) newWidth) / compressedbitmap.getWidth();
                scaleHeight = ((float) newHeight) / compressedbitmap.getHeight();
                matrix.postScale(scaleWidth, scaleHeight);

                resizedBitmap = Bitmap.createBitmap(compressedbitmap, 0, 0, compressedbitmap.getWidth(), compressedbitmap.getHeight(), matrix, true);

                actual_bitmap = rotateImageIfRequired(resizedBitmap);

                actual_bitmap.compress(Bitmap.CompressFormat.JPEG, quantity, new FileOutputStream(file));


            } catch (Throwable t) {
                Log.e("ERROR", "Error compressing file." + t.toString());
                t.printStackTrace();
            }

            quantity = quantity - 10;

        } while (length_check > ApplicationConstant.IMAGE_SIZE);

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
