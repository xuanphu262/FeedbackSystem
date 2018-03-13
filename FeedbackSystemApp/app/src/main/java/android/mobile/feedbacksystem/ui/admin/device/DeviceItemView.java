package android.mobile.feedbacksystem.ui.admin.device;

import android.content.Context;
import android.mobile.feedbacksystem.R;
import android.mobile.feedbacksystem.common.model.DeviceModel;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import hoainguyen.lib.recyclerhelper.recycler.item.RecyclerViewRenderItem;

/**
 * Created by hoainguyen on 3/12/18.
 */

public class DeviceItemView extends RecyclerViewRenderItem<DeviceItemView.DeviceViewHolder> {
    private DeviceModel mDevice;

    public DeviceItemView(DeviceModel device) {
        this.mDevice = device;
    }

    @Override
    public DeviceViewHolder makeViewHolder(Context context) {
        return new DeviceViewHolder(LayoutInflater.from(context).inflate(R.layout.item_devide_layout, null));
    }

    @Override
    public void bindViewHolder(DeviceViewHolder holder) {
        holder.tvLocation.setText("Location: " + mDevice.getLocationId());
        holder.tvDevice.setText("Device: " + mDevice.getDeviceName());
    }

    class DeviceViewHolder extends RecyclerView.ViewHolder {
        TextView tvDevice;
        TextView tvLocation;

        public DeviceViewHolder(View itemView) {
            super(itemView);
            tvDevice = (TextView) itemView.findViewById(R.id.tv_device);
            tvLocation = (TextView) itemView.findViewById(R.id.tv_location);
        }
    }
}
