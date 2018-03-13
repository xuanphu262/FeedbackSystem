package android.mobile.feedbacksystem.ui.admin;

import android.mobile.feedbacksystem.ui.admin.device.DeviceListFragment;
import android.mobile.feedbacksystem.ui.admin.location.LocationListFragment;
import android.mobile.feedbacksystem.ui.admin.report.ReportFragment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hoainguyen on 3/11/18.
 */

public class MainFragmentAdapter extends FragmentPagerAdapter {


    static final int TAB_COUNT = 3;
    DeviceListFragment mDeviceFragment;
    LocationListFragment mLocationFragment;
    ReportFragment mReportFragment;
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public MainFragmentAdapter(FragmentManager fm) {
        super(fm);
        mDeviceFragment = new DeviceListFragment();
        mLocationFragment = new LocationListFragment();
        mReportFragment = new ReportFragment();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }

    public void addFrag(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }
}
