package com.example.streethawkerssurveyapp.adapter;



import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.streethawkerssurveyapp.fragment.LoginFragment;
import com.example.streethawkerssurveyapp.fragment.RegisterFragment;


public class ViewPagerAdapter extends FragmentPagerAdapter {

  //integer to count number of tabs
  int tabCount;

  //Constructor to the class
  public ViewPagerAdapter(FragmentManager fm, int tabCount) {
    super(fm);
    //Initializing tab count
    this.tabCount= tabCount;
  }

  //Overriding method getItem
  @Override
  public Fragment getItem(int position) {
    //Returning the current tabs
    switch (position) {
      case 0:
        LoginFragment tab1 = new LoginFragment();
        return tab1;
//      case 1:
//        RegisterFragment tab2 = new RegisterFragment();
//        return tab2;

      default:
        return null;
    }
  }

  //Overriden method getCount to get the number of tabs
  @Override
  public int getCount() {
    return tabCount;
  }
}