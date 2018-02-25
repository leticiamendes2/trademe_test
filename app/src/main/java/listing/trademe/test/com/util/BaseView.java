package listing.trademe.test.com.util;

/**
 * Created by Letícia on 23/02/2018.
 */

public interface BaseView<T> {

    void setPresenter(T presenter);
    BasePresenter getPresenter();
    void setLoadingIndicator(boolean showIndicator);
    void showEmpty();
    void showError();
}
