package hoainguyen.lib.recyclerhelper.recycler.model;

/**
 * Created by thinh on 12/15/15.
 */
public class PaginationModel {
    public String nextUrl;
    public String previousUrl;

    public PaginationModel(String previousUrl, String nextUrl) {
        this.previousUrl = previousUrl;
        this.nextUrl = nextUrl;
    }

    public String getNextUrl() {
        return nextUrl;
    }

    public String getPreviousUrl() {
        return previousUrl;
    }
}
