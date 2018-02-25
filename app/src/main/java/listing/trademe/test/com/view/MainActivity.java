package listing.trademe.test.com.view;

import android.os.Bundle;

import butterknife.ButterKnife;
import listing.trademe.test.com.R;
import listing.trademe.test.com.util.ActivityUtil;
import listing.trademe.test.com.view.categories.CategoryListPresenter;
import listing.trademe.test.com.view.categories.MainCategoryListFragment;

public class MainActivity extends BaseActiviy {

    public CategoryListPresenter mainCategoryPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        System.out.println(getClass().getSimpleName() + " onCreate");

        if (savedInstanceState != null) {
            return;
        }

        if (findViewById(R.id.subCategoriesFrame) != null) {
            isDualPane = true;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        System.out.println(getClass().getSimpleName() + " onResume");

        setCategoryPresenter();
        MainCategoryListFragment mainCategoryListFragment = MainCategoryListFragment.newInstance();
        ActivityUtil.replaceFragmentToActivity(getSupportFragmentManager(), mainCategoryListFragment, R.id.mainCategoriesFrame);
        mainCategoryListFragment.setPresenter(mainCategoryPresenter);
    }

    public void setCategoryPresenter() {
        System.out.println(getClass().getSimpleName() + " setCategoryPresenter");

        if (mainCategoryPresenter == null) {
            mainCategoryPresenter = (CategoryListPresenter) getLastCustomNonConfigurationInstance();
        }

        if (mainCategoryPresenter == null) {
            mainCategoryPresenter = new CategoryListPresenter(MainCategoryListFragment.MAIN_CATEGORY_LEVEL);
        }

        mainCategoryPresenter.setCategoryNumber(MainCategoryListFragment.MAIN_CATEGORY_LEVEL);
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return mainCategoryPresenter;
    }

}
