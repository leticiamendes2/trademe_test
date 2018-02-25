package listing.trademe.test.com.view.categories;

import android.os.Bundle;

import butterknife.ButterKnife;
import listing.trademe.test.com.R;
import listing.trademe.test.com.util.ActivityUtil;
import listing.trademe.test.com.view.BaseActiviy;

/**
 * Created by Letícia on 22/02/2018.
 */

public class SubCategoryActivity extends BaseActiviy {

    public static final String MAIN_CATEGORY_ID = "MAIN_CATEGORY_ID";
    private String categoryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcategories);
        ButterKnife.bind(this);

        System.out.println(getClass().getSimpleName() + " onCreate");

        categoryId = getIntent().getStringExtra(MAIN_CATEGORY_ID);
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println(getClass().getSimpleName() + " onStart");
        setSubCategoryPresenter(categoryId);
        SubCategoryListFragment subCategoryListFragment = getSubCategoryListFragment();
        subCategoryListFragment.setPresenter(subCategoryPresenter);
        ActivityUtil.replaceFragmentToActivity(getSupportFragmentManager(), subCategoryListFragment, R.id.subCategoriesFrame);
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return subCategoryPresenter;
    }
}
