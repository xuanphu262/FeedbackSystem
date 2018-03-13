package android.mobile.feedbacksystem.ui.admin.location;

import android.content.Context;
import android.mobile.feedbacksystem.R;
import android.mobile.feedbacksystem.common.model.LocationModel;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

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
    }

    class LocationViewHolder extends RecyclerView.ViewHolder {
        TextView tvShortDescription;
        TextView tvLongDescription;

        public LocationViewHolder(View itemView) {
            super(itemView);

            tvShortDescription = (TextView) itemView.findViewById(R.id.tv_shortDescription);
            tvLongDescription = (TextView) itemView.findViewById(R.id.tv_longDescription);
        }
    }
}
