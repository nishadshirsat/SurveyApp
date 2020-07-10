package com.example.streethawkerssurveyapp.services_pack;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;

import com.example.streethawkerssurveyapp.R;

public class CustomProgressDialog extends ProgressDialog {

  public static ProgressDialog getDialogue(Activity activity) {
    CustomProgressDialog dialog = new CustomProgressDialog(activity, R.style.myDialogTheme);
    dialog.setIndeterminate(true);
    dialog.getWindow().getAttributes().gravity = Gravity.CENTER;

    dialog.setCancelable(false);
    return dialog;
  }


  public CustomProgressDialog(Context context, int theme) {
    super(context,theme);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.custom_progress_dialog);
  }

  @Override
  public void show() {
    super.show();
  }
}
