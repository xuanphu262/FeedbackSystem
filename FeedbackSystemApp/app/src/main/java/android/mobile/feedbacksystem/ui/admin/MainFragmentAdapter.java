package android.mobile.feedbacksystem.ui.admin;

import android.mobile.feedbacksystem.ui.admin.device.DeviceListFragment;
import android.mobile.feedbacksystem.ui.admin.location.LocationListFragment;
import android.mobile.feedbacksystem.ui.admin.report.ReportFragment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by hoainguyen on 3/11/18.
 */

public class MainFragmentAdapter extends FragmentStatePagerAdapter {
    static final int TAB_COUNT = 3;
    DeviceListFragment mDeviceFragment;
    LocationListFragment mLocationFragment;
    ReportFragment mReportFragment;

    public MainFragmentAdapter(FragmentManager fm) {
        super(fm);
        mDeviceFragment = new DeviceListFragment();
        mLocationFragment = new LocationListFragment();
        mReportFragment = new ReportFragment();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return mDeviceFragment;
            case 1:
                return mLocationFragment;
            case 2:
                return mReportFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Device";
            case 1:
                return "Location";
            case 2:
                return "Report";
            default:
                return null;
        }
    }
}
