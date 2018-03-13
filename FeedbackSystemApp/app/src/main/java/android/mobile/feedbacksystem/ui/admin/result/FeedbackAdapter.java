package android.mobile.feedbacksystem.ui.admin.result;

import android.mobile.feedbacksystem.common.DataHelper;
import android.mobile.feedbacksystem.common.model.FeedbackModel;

import java.util.ArrayList;
import java.util.List;

import hoainguyen.lib.recyclerhelper.recycler.DataSectionRVAdapter;
import hoainguyen.lib.recyclerhelper.recycler.item.RenderItem;

/**
 * Created by hoainguyen on 3/13/18.
 */

public class FeedbackAdapter extends DataSectionRVAdapter {
    @Override
    public void onStartLoadData() {
        List<RenderItem> itemList = new ArrayList<>();
        for (FeedbackModel feedback : DataHelper.feedbackList) {
            itemList.add(new FeedbackItemView(feedback));
        }

        refresh(itemList);
    }
}
