package android.mobile.feedbacksystem.ui.admin.result;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.mobile.feedbacksystem.R;
import android.mobile.feedbacksystem.common.AppUtils;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hoainguyen.lib.recyclerhelper.collection.SectionCollectionFragment;

public class ListResultFragment extends SectionCollectionFragment {
    private static final int MULTIPLE_PERMISSIONS = 6969;
    List<String> mPermissionsList = new ArrayList<>();

    @Override
    public View getRootLayout(LayoutInflater inflater, ViewGroup container) {
        View rootView = inflater.inflate(R.layout.fragment_list_result, null);
        rootView.findViewById(R.id.btn_export).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (haveToRequestPermission()) {
                    Log.d("Report", "Request permission");
                    requestPermissions(mPermissionsList.toArray(new String[mPermissionsList.size()]), MULTIPLE_PERMISSIONS);
                } else {
                    createReportFile();
                }
            }
        });
        return rootView;
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
        if (getActivity().checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
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
                Toast.makeText(getActivity(), "App can't run without some permission", Toast.LENGTH_SHORT).show();
            } else {
                createReportFile();
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onMakeAdapters() {
        mRecyclerView.appendAdapter(new FeedbackAdapter());
    }

    private void createReportFile() {
        Log.d("Report", "Create report file!");
        try {
            boolean result = AppUtils.saveExcelFile(getActivity().getApplicationContext(), "Feedback.xlsx");
            if (result) {
                Toast.makeText(getActivity(), "Export data successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "Export data failed!", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Export data failed!", Toast.LENGTH_SHORT).show();
        }
    }
}
