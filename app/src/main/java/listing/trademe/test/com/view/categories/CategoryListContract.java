package listing.trademe.test.com.view.categories;

import listing.trademe.test.com.model.Category;
import listing.trademe.test.com.util.BasePresenter;
import listing.trademe.test.com.util.BaseView;

/**
 * Created by Letícia on 21/02/2018.
 */

public interface CategoryListContract {

    interface View extends BaseView<Presenter> {
        void showResult(Category category);
    }

    interface Presenter extends BasePresenter {
        void setView(View view);
        void loadCategory();
    }
}
