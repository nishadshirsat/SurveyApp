package com.example.streethawkerssurveyapp;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.example.streethawkerssurveyapp.Helper.LocaleHelper;

public class MultiLanguageApp extends Application {

  @Override
  protected void attachBaseContext(Context base) {
    super.attachBaseContext(LocaleHelper.setLocale(base));
  }

  @Override
  public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    LocaleHelper.setLocale(this);
  }
}
