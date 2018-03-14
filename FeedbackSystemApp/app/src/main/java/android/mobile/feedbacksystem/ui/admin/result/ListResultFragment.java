package android.mobile.feedbacksystem.ui.admin.result;

import android.mobile.feedbacksystem.R;
import android.mobile.feedbacksystem.common.AppUtils;
import android.mobile.feedbacksystem.ui.admin.event.NavigateEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import hoainguyen.lib.recyclerhelper.collection.SectionCollectionFragment;

public class ListResultFragment extends SectionCollectionFragment {

    @Override
    public View getRootLayout(LayoutInflater inflater, ViewGroup container) {
        View rootView = inflater.inflate(R.layout.fragment_list_result, null);
        rootView.findViewById(R.id.btn_export).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               boolean result =  AppUtils.saveExcelFile(getActivity().getApplicationContext(), "myExcel.xlsx");


            }
        });
        return rootView;
    }

    @Override
    protected void onMakeAdapters() {
        mRecyclerView.appendAdapter(new FeedbackAdapter());
    }
}
