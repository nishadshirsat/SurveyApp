package com.example.streethawkerssurveyapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.example.streethawkerssurveyapp.R;
import com.example.streethawkerssurveyapp.adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class LoginActivity extends AppCompatActivity implements TabLayout.BaseOnTabSelectedListener{

    private TabLayout mTabs;
    private ViewPager mViewpager;

    AlertDialog alertDialog;
    private String Flag_Remember = "", userName, passWord;
    private int READ_PHONE_REQUEST = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        bindView();

        checkForPermissions();
    }

    private void bindView() {

        mViewpager = (ViewPager) findViewById(R.id.view_Details);

        mTabs = (TabLayout) findViewById(R.id.tabs);


        mTabs.addTab(mTabs.newTab().setText("Login"));
//        mTabs.addTab(mTabs.newTab().setText(""));
        mTabs.setTabGravity(TabLayout.GRAVITY_FILL);
//        mTabs.setTabTextColors(getResources().getColor(R.color.white), getResources().getColor(R.color.white));

//        mTabs.setupWithViewPager(mViewpager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(LoginActivity.this.getSupportFragmentManager(), mTabs.getTabCount());
        //Adding adapter to pager
        mViewpager.setAdapter(adapter);
        mViewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabs));


        //Adding onTabSelectedListener to swipe views
        mTabs.setOnTabSelectedListener(LoginActivity.this);

    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mViewpager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    public void checkForPermissions(){
        if (ContextCompat.checkSelfPermission(LoginActivity.this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(LoginActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(LoginActivity.this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(LoginActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(LoginActivity.this,
                Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(LoginActivity.this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(LoginActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(LoginActivity.this,
                Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(LoginActivity.this,
                    Manifest.permission.CAMERA) && ActivityCompat.shouldShowRequestPermissionRationale(LoginActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(LoginActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(LoginActivity.this,
                    Manifest.permission.READ_PHONE_STATE) && ActivityCompat.shouldShowRequestPermissionRationale(LoginActivity.this,
                    Manifest.permission.READ_CONTACTS) && ActivityCompat.shouldShowRequestPermissionRationale(LoginActivity.this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) && ActivityCompat.shouldShowRequestPermissionRationale(LoginActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)  && ActivityCompat.shouldShowRequestPermissionRationale(LoginActivity.this,
                    Manifest.permission.RECORD_AUDIO)) {
                // Show an explanation to the user asynchronously -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

//              Toast.makeText(LoginActivity.this,"WAITING FOR USER RESPONSE",Toast.LENGTH_SHORT).show();

                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setTitle("Permissions Needed");
                builder.setMessage("Want to access your camera and storage to set your profile");
                builder.setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        alertDialog.dismiss();

                        ActivityCompat.requestPermissions(LoginActivity.this,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                        Manifest.permission.READ_EXTERNAL_STORAGE,
                                        Manifest.permission.CAMERA,
                                        Manifest.permission.READ_PHONE_STATE,
                                        Manifest.permission.ACCESS_COARSE_LOCATION,
                                        Manifest.permission.ACCESS_FINE_LOCATION,
                                        Manifest.permission.RECORD_AUDIO,
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
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setTitle("Permissions Needed");
                builder.setMessage("Want to access your camera and storage to set your profile");
                builder.setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog.dismiss();

                        ActivityCompat.requestPermissions(LoginActivity.this,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                        Manifest.permission.READ_EXTERNAL_STORAGE,
                                        Manifest.permission.CAMERA,
                                        Manifest.permission.ACCESS_COARSE_LOCATION,
                                        Manifest.permission.ACCESS_FINE_LOCATION,
                                        Manifest.permission.READ_PHONE_STATE,
                                        Manifest.permission.RECORD_AUDIO,
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
//      Toast.makeText(LoginActivity.this,"Permission Granted",Toast.LENGTH_SHORT).show();
        }
    }

}
