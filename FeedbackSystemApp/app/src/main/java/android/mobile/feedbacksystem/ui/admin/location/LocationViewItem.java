package android.mobile.feedbacksystem.ui.admin.location;

import android.content.Context;
import android.mobile.feedbacksystem.R;
import android.mobile.feedbacksystem.common.model.LocationModel;
import android.mobile.feedbacksystem.ui.admin.event.EditLocationEvent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import hoainguyen.lib.recyclerhelper.recycler.item.RecyclerViewRenderItem;

/**
 * Created by hoainguyen on 3/11/18.
 */

public class LocationViewItem extends RecyclerViewRenderItem<LocationViewItem.LocationViewHolder> {
    LocationModel mLocation;

    public LocationViewItem(LocationModel location) {
        this.mLocation = location;
    }

    @Override
    public LocationViewHolder makeViewHolder(Context context) {
        return new LocationViewHolder(LayoutInflater.from(context).inflate(R.layout.item_location_layout, null));
    }

    @Override
    public void bindViewHolder(LocationViewHolder holder) {
        holder.tvLongDescription.setText(mLocation.getLongDescription());
        holder.tvShortDescription.setText(mLocation.getShortDescription());
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new EditLocationEvent(mLocation));
            }
        });
    }

    class LocationViewHolder extends RecyclerView.ViewHolder {
        TextView tvShortDescription;
        TextView tvLongDescription;
        View btnEdit;

        public LocationViewHolder(View itemView) {
            super(itemView);
            btnEdit = itemView.findViewById(R.id.btn_edit);
            tvShortDescription = (TextView) itemView.findViewById(R.id.tv_shortDescription);
            tvLongDescription = (TextView) itemView.findViewById(R.id.tv_longDescription);
        }
    }
}
