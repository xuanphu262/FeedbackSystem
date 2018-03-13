package hoainguyen.lib.recyclerhelper.collection;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hoainguyen.lib.recyclerhelper.R;
import hoainguyen.lib.recyclerhelper.recycler.ExRecyclerView;

/**
 * Created by hoainguyen on 3/29/17.
 */

public abstract class SectionCollectionFragment extends Fragment {
    protected ExRecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = getRootLayout(inflater, container);
        mRecyclerView = (ExRecyclerView) rootView.findViewById(R.id.recycle_view);
        onCreateCustomView(inflater, rootView);
        onMakeAdapters();
        return rootView;
    }

    protected abstract void onMakeAdapters();

    public void onCreateCustomView(LayoutInflater inflater, View rootView) {
    }

    public View getRootLayout(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_collection_layout, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecyclerView.start();
    }
}
