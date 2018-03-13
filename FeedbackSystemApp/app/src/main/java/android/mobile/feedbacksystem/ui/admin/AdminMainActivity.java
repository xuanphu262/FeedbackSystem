package android.mobile.feedbacksystem.ui.admin;

import android.content.Intent;
import android.mobile.feedbacksystem.R;
import android.mobile.feedbacksystem.common.DataHelper;
import android.mobile.feedbacksystem.ui.admin.device.DeviceListFragment;
import android.mobile.feedbacksystem.ui.admin.event.NavigateEvent;
import android.mobile.feedbacksystem.ui.admin.location.LocationListFragment;
import android.mobile.feedbacksystem.ui.admin.report.ReportFragment;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;

public class AdminMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TabLayout tabLayout;
    private int[] tabIcons = {
            R.drawable.ic_tab_call,
            R.drawable.ic_location_found,
            R.drawable.ic_tab_report};

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void onGetEvent(NavigateEvent event) {
        switch (event) {
            case ADD_DEVICE:
                Intent addDeviceIntent = new Intent(AdminMainActivity.this, AddDeviceActivity.class);
                startActivity(addDeviceIntent);
                break;
            case ADD_LOCATION:
                Intent addLocationIntent = new Intent(AdminMainActivity.this, AddLocationActivity.class);
                startActivity(addLocationIntent);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new MainFragmentAdapter(getSupportFragmentManager()));
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.view_tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
        try {
            DataHelper.initialData(getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_device) {
            // Handle the device action
        } else if (id == R.id.nav_report) {

        } else if (id == R.id.nav_sub_location) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }

    private void setupViewPager(ViewPager viewPager) {
        MainFragmentAdapter adapter = new MainFragmentAdapter(getSupportFragmentManager());
        adapter.addFrag(new DeviceListFragment(), "Device");
        adapter.addFrag(new LocationListFragment(), "Location");
        adapter.addFrag(new ReportFragment(), "Report");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
