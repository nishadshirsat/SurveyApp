package com.example.streethawkerssurveyapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.streethawkerssurveyapp.R;
import com.example.streethawkerssurveyapp.adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class LoginActivity extends AppCompatActivity implements TabLayout.BaseOnTabSelectedListener{

    private TabLayout mTabs;
    private ViewPager mViewpager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        bindView();
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
}
