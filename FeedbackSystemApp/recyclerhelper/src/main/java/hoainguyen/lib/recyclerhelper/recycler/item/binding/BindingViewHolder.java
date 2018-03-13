package hoainguyen.lib.recyclerhelper.recycler.item.binding;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Created by hoainguyen on 1/18/17.
 */

public class BindingViewHolder<B extends ViewDataBinding> extends RecyclerView.ViewHolder {
    B mBinding;

    public BindingViewHolder(B binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    public B getBinding() {
        return mBinding;
    }
}
