package hoainguyen.lib.recyclerhelper.recycler.model;

import java.util.Arrays;
import java.util.List;

/**
 * Created by hoainguyen on 10/9/16.
 */
public class CollectionDataModel<M> {
    public M[] data;
    public PaginationModel pagination;

    public CollectionDataModel(M[] data, PaginationModel pagination) {
        this.pagination = pagination;
        this.data = data;
    }

    public CollectionDataModel(M[] data) {
        this.data = data;
        pagination = new PaginationModel("","");
    }

    public void join(CollectionDataModel<M> source) {
        M[] data = Arrays.copyOf(this.data, this.data.length + source.data.length);
        System.arraycopy(source.data, 0, data, this.data.length, source.data.length);
        this.data = data;
        this.pagination = source.pagination;
    }

    public M[] getData() {
        return data;
    }

    public PaginationModel getPagination() {
        return pagination;
    }
}
