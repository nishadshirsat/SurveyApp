//package com.example.streethawkerssurveyapp.activities;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.net.Uri;
//import android.os.Bundle;
//import android.provider.MediaStore;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageView;
//
//import com.example.streethawkerssurveyapp.R;
//import com.scanlibrary.ScanActivity;
//import com.scanlibrary.ScanConstants;
//
//import java.io.IOException;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//public class DemoScanDocActivity extends AppCompatActivity {
//    private static final int REQUEST_CODE_SCAN = 13;
//    private Button dBtn_scan;
//    private static final int REQUEST_CODE = 99;
//
//    private ImageView dImage_Doc;
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_docscan);
//
//        bindViews();
//
//        dBtn_scan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                new ScanButtonClickListener(ScanConstants.OPEN_CAMERA);
//
//                Intent intent = new Intent(DemoScanDocActivity.this, ScanActivity.class);
//                intent.putExtra(ScanConstants.OPEN_INTENT_PREFERENCE, ScanConstants.OPEN_CAMERA);
//                startActivityForResult(intent, REQUEST_CODE);
//            }
//        });
//    }
//
//    private class ScanButtonClickListener implements View.OnClickListener {
//
//        private int preference;
//
//        public ScanButtonClickListener(int preference) {
//            this.preference = preference;
//        }
//
//        public ScanButtonClickListener() {
//        }
//
//        @Override
//        public void onClick(View v) {
//            startScan(preference);
//        }
//    }
//
//    private void bindViews() {
//
//        dBtn_scan = (Button) findViewById(R.id.btn_scan);
//        dImage_Doc = (ImageView) findViewById(R.id.image_Doc);
//    }
//    protected void startScan(int preference) {
//        Intent intent = new Intent(this, ScanActivity.class);
//        intent.putExtra(ScanConstants.OPEN_INTENT_PREFERENCE, preference);
//        startActivityForResult(intent, REQUEST_CODE);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
//            Uri uri = data.getExtras().getParcelable(ScanConstants.SCANNED_RESULT);
//            Bitmap bitmap = null;
//            try {
//                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
//                getContentResolver().delete(uri, null, null);
//                dImage_Doc.setImageBitmap(bitmap);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
