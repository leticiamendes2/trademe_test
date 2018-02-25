package listing.trademe.test.com.view.items;

import android.os.Bundle;
import android.support.annotation.NonNull;

import butterknife.ButterKnife;
import listing.trademe.test.com.R;
import listing.trademe.test.com.util.ActivityUtil;
import listing.trademe.test.com.view.BaseActiviy;

/**
 * Created by Letícia on 23/02/2018.
 */

public class ItemsListActivity extends BaseActiviy {

    @NonNull
    public static final String CATEGORY_ID = "CATEGORY_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itens);
        ButterKnife.bind(this);

        String categoryId = getIntent().getStringExtra(CATEGORY_ID);

        if (savedInstanceState == null) {
            ItemsListFragment itemsListFragment = (ItemsListFragment) getSupportFragmentManager().findFragmentById(R.id.subCategoriesFrame);
            if (itemsListFragment == null) {
                itemsListFragment = ItemsListFragment.newInstance(categoryId);
                new ItemsListPresenter(itemsListFragment);
                ActivityUtil.replaceFragmentToActivity(getSupportFragmentManager(), itemsListFragment, R.id.itemsFrame);
            }
        }
    }
}
