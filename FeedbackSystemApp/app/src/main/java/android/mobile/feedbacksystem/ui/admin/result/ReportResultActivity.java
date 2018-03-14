package android.mobile.feedbacksystem.ui.admin.result;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.mobile.feedbacksystem.R;
import android.mobile.feedbacksystem.common.AppUtils;
import android.mobile.feedbacksystem.ui.admin.MainFragmentAdapter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportResultActivity extends AppCompatActivity {
    private static final int MULTIPLE_PERMISSIONS = 6969;
    List<String> mPermissionsList = new ArrayList<>();
    int mActionId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new MainFragmentAdapter(getSupportFragmentManager()));
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.view_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        MainFragmentAdapter adapter = new MainFragmentAdapter(getSupportFragmentManager());
        adapter.addFrag(new ChartResultFragment(), "Illustration");
        adapter.addFrag(new ListResultFragment(), "Result");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.admin_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_email || item.getItemId() == R.id.action_download) {
            mActionId = item.getItemId();
            onSavedExportFile();
        }
        return true;
    }

    private void onSavedExportFile() {
        if (haveToRequestPermission()) {
            Log.d("Report", "Request permission");
            requestPermissions(mPermissionsList.toArray(new String[mPermissionsList.size()]), MULTIPLE_PERMISSIONS);
        } else {
            createReportFile();
        }
    }

    private boolean haveToRequestPermission() {
        if (Build.VERSION.SDK_INT < 23)
            return false;

        return checkPermission();
    }

    /**
     * check and request all permissions that access the device and user information.
     * It will invoke {@link #addPermission(List, String)} to add all un-granted permissions to
     * check them at only one time.<br/>
     * <p>
     * Note: this action will be invoke at all screens on Android version 6 and above.
     */
    @TargetApi(Build.VERSION_CODES.M)
    private boolean checkPermission() {
        addPermission(mPermissionsList, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        addPermission(mPermissionsList, Manifest.permission.READ_EXTERNAL_STORAGE);
        return (mPermissionsList.size() > 0);
    }

    /**
     * add non-accept permission to request permission list.
     *
     * @param permissionsList list permission to request
     * @param permission      a specific permission
     * @return true if the given permission is not accepted, otherwise it return false.
     */
    @TargetApi(Build.VERSION_CODES.M)
    private boolean addPermission(List<String> permissionsList, String permission) {
        if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
            permissionsList.add(permission);
            // Check for Rationale Option
            if (!shouldShowRequestPermissionRationale(permission)) {
                return false;
            }
            return false;
        }
        return true;
    }

    /**
     * Handle the request permission result.
     *
     * @param requestCode  of that app
     * @param permissions  set of permission to check
     * @param grantResults set of grant results.
     * @see #MULTIPLE_PERMISSIONS
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == MULTIPLE_PERMISSIONS) {
            Map<String, Integer> perms = new HashMap<>();
            perms.put(Manifest.permission.READ_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
            perms.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);

            for (int i = 0; i < permissions.length; i++) {
                perms.put(permissions[i], grantResults[i]);
            }

            if (perms.get(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                    && perms.get(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "App can't run without some permission", Toast.LENGTH_SHORT).show();
            } else {
                createReportFile();
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    private void createReportFile() {
        Log.d("Report", "Create report file!");
        try {
            String dateString = new Date().getTime() + "";
            String fileName = "Feedback_Report_" + dateString + ".xls";
            File file = AppUtils.saveExcelFile(this.getApplicationContext(), fileName);
            if (file != null) {
                Toast.makeText(this, fileName +" file was successfully saved in Download folder!", Toast.LENGTH_SHORT).show();
                if (mActionId == R.id.action_email) {
                    onShareFile(file);
                }
            } else {
                Toast.makeText(this, "Export data failed!", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Export data failed!", Toast.LENGTH_SHORT).show();
        }
    }

    private void onShareFile(File file) {
        Uri emailUri = Uri.fromFile(file);
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback Report");
        sendIntent.putExtra(Intent.EXTRA_STREAM, emailUri);
        sendIntent.setType("text/html");
        startActivity(sendIntent);
    }
}
