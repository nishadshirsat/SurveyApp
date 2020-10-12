package com.example.streethawkerssurveyapp.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.example.streethawkerssurveyapp.activities.MainActivity;
import com.example.streethawkerssurveyapp.services_pack.ApplicationConstant;
import com.example.streethawkerssurveyapp.utils.PrefUtils;

import java.io.IOException;

import androidx.annotation.Nullable;

public class AudioRecordService extends Service {

    private MediaRecorder mRecorder;
    private MediaPlayer mPlayer;
    private static final String LOG_TAG = "AudioRecording";
    private static String mFileName = null;
    public static final int REQUEST_AUDIO_PERMISSION_CODE = 1;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

      final String surveyId =  intent.getStringExtra("FILE");

        Runnable r = new Runnable() {
            public void run() {

                recordAudio(surveyId);

            }
        };

        Toast.makeText(AudioRecordService.this, "Recording Started", Toast.LENGTH_LONG).show();

        Thread t = new Thread(r);
        t.start();
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

            mRecorder.stop();
            mRecorder.release();
            mRecorder = null;

//        Toast.makeText(AudioRecordService.this, "Recording Stopped", Toast.LENGTH_LONG).show();

    }

        private void recordAudio(String surveyId) {

            try {
                mFileName = ApplicationConstant.createImageFile(surveyId+"_surveyer.mp3","Recordings", AudioRecordService.this);

                PrefUtils.saveToPrefs(AudioRecordService.this,ApplicationConstant.RECORDING,mFileName);
            } catch (IOException e) {
                e.printStackTrace();
            }

            mRecorder = new MediaRecorder();
            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
            mRecorder.setOutputFile(mFileName);


//            Toast.makeText(getApplicationContext(), "Recording Stopped", Toast.LENGTH_LONG).show();

            try {
                mRecorder.prepare();
            } catch (IOException e) {
                Log.e(LOG_TAG, "prepare() failed");
            }
            mRecorder.start();

//            Handler mainHandler = new Handler(getMainLooper());
//
//            mainHandler.post(new Runnable() {
//                @Override
//                public void run() {
//                    // Do your stuff here related to UI, e.g. show toast
//                    Toast.makeText(getApplicationContext(), "I'm a toast!", Toast.LENGTH_SHORT).show();
//                }
//            });

        }

}
