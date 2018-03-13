package android.mobile.feedbacksystem.ui.admin.device;

import android.content.Context;
import android.mobile.feedbacksystem.R;
import android.mobile.feedbacksystem.common.model.DeviceModel;
import android.mobile.feedbacksystem.ui.admin.event.EditDeviceEvent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import hoainguyen.lib.recyclerhelper.recycler.item.RecyclerViewRenderItem;

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
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new EditDeviceEvent(mDevice));
            }
        });
    }

    class DeviceViewHolder extends RecyclerView.ViewHolder {
        TextView tvDevice;
        TextView tvLocation;
        View btnEdit;

        public DeviceViewHolder(View itemView) {
            super(itemView);
            tvDevice = (TextView) itemView.findViewById(R.id.tv_device);
            tvLocation = (TextView) itemView.findViewById(R.id.tv_location);
            btnEdit = itemView.findViewById(R.id.btn_edit);
        }
    }
}
