package android.mobile.feedbacksystem.ui.admin.device;

import android.mobile.feedbacksystem.common.DataHelper;
import android.mobile.feedbacksystem.common.model.DeviceModel;

import java.util.ArrayList;
import java.util.List;

import hoainguyen.lib.recyclerhelper.recycler.DataSectionRVAdapter;
import hoainguyen.lib.recyclerhelper.recycler.item.RenderItem;

/**
 * Created by hoainguyen on 3/12/18.
 */

public class DeviceAdapter extends DataSectionRVAdapter {
    @Override
    public void onStartLoadData() {
        List<RenderItem> itemList = new ArrayList<>();

        for (DeviceModel deviceModel : DataHelper.deviceList) {
            itemList.add(new DeviceItemView(deviceModel));
        }
        refresh(itemList);
    }
}
