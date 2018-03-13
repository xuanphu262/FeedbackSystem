package android.mobile.feedbacksystem.ui.admin.location;

import android.mobile.feedbacksystem.common.DataHelper;
import android.mobile.feedbacksystem.common.model.LocationModel;

import java.util.ArrayList;
import java.util.List;

import hoainguyen.lib.recyclerhelper.recycler.DataSectionRVAdapter;
import hoainguyen.lib.recyclerhelper.recycler.item.RenderItem;

/**
 * Created by hoainguyen on 3/11/18.
 */

public class LocationAdapter extends DataSectionRVAdapter {
    @Override
    public void onStartLoadData() {
        List<RenderItem> itemList = new ArrayList<>();
        for (LocationModel locationModel : DataHelper.locationList) {
            itemList.add(new LocationViewItem(locationModel));
        }

        refresh(itemList);
    }
}
