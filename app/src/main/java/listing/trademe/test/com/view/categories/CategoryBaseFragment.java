package listing.trademe.test.com.view.categories;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import listing.trademe.test.com.model.Category;

/**
 * Created by Letícia on 25/02/2018.
 */

public class CategoryBaseFragment extends Fragment implements CategoryListContract.View {

    CategoryListContract.Presenter presenter;

    @Override
    public void setPresenter(@NonNull CategoryListContract.Presenter presenter) {
        this.presenter = presenter;
        this.presenter.setView(this);
        this.presenter.start();
    }

    @Override
    public CategoryListContract.Presenter getPresenter() {
        return this.presenter;
    }

    @Override
    public void showResult(Category category) { }

    @Override
    public void setLoadingIndicator(boolean showIndicator) { }

    @Override
    public void showEmpty() { }

    @Override
    public void showError() { }
}
