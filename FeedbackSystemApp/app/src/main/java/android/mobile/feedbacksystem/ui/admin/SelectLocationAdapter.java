package android.mobile.feedbacksystem.ui.admin;

import android.mobile.feedbacksystem.common.DataHelper;
import android.mobile.feedbacksystem.common.model.LocationModel;

import java.util.ArrayList;
import java.util.List;

import hoainguyen.lib.recyclerhelper.recycler.DataSectionRVAdapter;
import hoainguyen.lib.recyclerhelper.recycler.item.RenderItem;

public class SelectLocationAdapter extends DataSectionRVAdapter {
    @Override
    public void onStartLoadData() {
        List<RenderItem> itemList = new ArrayList<>();
        for (LocationModel locationModel : DataHelper.locationList) {
            itemList.add(new SelectLocationViewItem(locationModel));
        }

        refresh(itemList);
    }
}
