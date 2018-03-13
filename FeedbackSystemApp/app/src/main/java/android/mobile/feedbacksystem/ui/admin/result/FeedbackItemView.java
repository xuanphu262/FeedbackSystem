package android.mobile.feedbacksystem.ui.admin.result;

import android.content.Context;
import android.mobile.feedbacksystem.R;
import android.mobile.feedbacksystem.common.AppUtils;
import android.mobile.feedbacksystem.common.DataHelper;
import android.mobile.feedbacksystem.common.model.FeedbackModel;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Locale;

import hoainguyen.lib.recyclerhelper.recycler.item.RecyclerViewRenderItem;

/**
 * Created by hoainguyen on 3/13/18.
 */

public class FeedbackItemView extends RecyclerViewRenderItem<FeedbackItemView.FeedbackViewHolder> {
    private FeedbackModel mFeedback;
    final SimpleDateFormat DateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

    public FeedbackItemView(FeedbackModel feedback) {
        this.mFeedback = feedback;
    }

    @Override
    public FeedbackViewHolder makeViewHolder(Context context) {
        return new FeedbackViewHolder(LayoutInflater.from(context).inflate(R.layout.item_feedback_layout, null));
    }

    @Override
    public void bindViewHolder(FeedbackViewHolder holder) {
        holder.tvShort.setText(DataHelper.getLocation(mFeedback.getLocationId()).getShortDescription());
        holder.tvLong.setText(DataHelper.getLocation(mFeedback.getLocationId()).getLongDescription());
        holder.tvTime.setText(DateFormat.format(mFeedback.getCreatedDate()));

        Log.d("Rating", Integer.parseInt(mFeedback.getRating()) + "");
        holder.imgIcon.setImageResource(AppUtils.FeedbackResource[Integer.parseInt(mFeedback.getRating())]);
    }

    class FeedbackViewHolder extends RecyclerView.ViewHolder {
        TextView tvShort;
        TextView tvLong;
        TextView tvTime;
        ImageView imgIcon;

        public FeedbackViewHolder(View itemView) {
            super(itemView);
            tvShort = (TextView) itemView.findViewById(R.id.tv_shortDescription);
            tvLong = (TextView) itemView.findViewById(R.id.tv_longDescription);
            tvTime = (TextView) itemView.findViewById(R.id.tv_dateTime);
            imgIcon = (ImageView) itemView.findViewById(R.id.img_feedback);
        }
    }
}
