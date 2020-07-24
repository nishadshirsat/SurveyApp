package com.example.streethawkerssurveyapp;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.example.streethawkerssurveyapp.services_pack.ApiInterface;
import com.example.streethawkerssurveyapp.services_pack.ApiService;

import java.lang.reflect.Field;
import java.util.HashMap;

import io.sentry.core.Sentry;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/**
 * Created by Tripurari Tiwari on 11/6/18.
 */
public class CrashTestApplication extends Application {
    public static CrashTestApplication instance;
    Activity activity;
    String stackTrace, message;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable ex) {
                Log.e("OS: ","Thread ");
                handleUncaughtException(thread, ex);
            }
        });
    }
    public void handleUncaughtException(Thread thread, Throwable e) {
        Log.e("OS: ","handleUncaughtException ");
        stackTrace = Log.getStackTraceString(e);
        message = e.getMessage();
//        Toast.makeText(getApplicationContext(), "Crashed-- " + stackTrace + "  " + message, Toast.LENGTH_SHORT).show();
        StringBuilder builder = new StringBuilder();
        builder.append("android : ").append(Build.VERSION.RELEASE);
        Field[] fields = Build.VERSION_CODES.class.getFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            int fieldValue = -1;
            try {
                fieldValue = field.getInt(new Object());
            } catch (IllegalArgumentException tc) {
                tc.printStackTrace();
            } catch (IllegalAccessException tc) {
                tc.printStackTrace();
            } catch (NullPointerException tc) {
                tc.printStackTrace();
            }
            if (fieldValue == Build.VERSION.SDK_INT) {
                builder.append(" : ").append(fieldName).append(" : ");
                builder.append("sdk=").append(fieldValue);
            }
        }
        Log.d("OS: ", builder.toString());
        Log.e("OS: ", builder.toString());
        Log.e("OS: ", e.getMessage());

        Sentry.captureException(e);


    }
    @Override
    public Context getApplicationContext() {
        return super.getApplicationContext();
    }
    public static CrashTestApplication getInstance() {
        return instance;
    }
}
