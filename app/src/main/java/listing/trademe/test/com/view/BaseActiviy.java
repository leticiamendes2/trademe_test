package listing.trademe.test.com.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import listing.trademe.test.com.R;
import listing.trademe.test.com.model.Category;
import listing.trademe.test.com.util.ActivityUtil;
import listing.trademe.test.com.view.categories.CategoryListPresenter;
import listing.trademe.test.com.view.categories.MainCategoryListFragment;
import listing.trademe.test.com.view.categories.SubCategoryActivity;
import listing.trademe.test.com.view.categories.SubCategoryListFragment;
import listing.trademe.test.com.view.items.ItemsListActivity;
import listing.trademe.test.com.view.items.ItemsListFragment;
import listing.trademe.test.com.view.items.ItemsListPresenter;

/**
 * Created by Letícia on 25/02/2018.
 */

public class BaseActiviy extends AppCompatActivity implements MainCategoryListFragment.OnCategorySelectedListener, SubCategoryListFragment.OnSubCategorySelectedListener {

    boolean isDualPane = false;
    ActionBar actionBar;
    public CategoryListPresenter subCategoryPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar = getSupportActionBar();

        System.out.println(getClass().getSimpleName() + " onCreate");
    }

    @Override
    public void onCategorySelected(Category category) {
        if(isDualPane) {
            setSubCategoryPresenter(category.getNumber());
            SubCategoryListFragment subCategoryListFragment = getSubCategoryListFragment();
            subCategoryListFragment.setPresenter(subCategoryPresenter);
            ActivityUtil.replaceFragmentToActivityAddBackStack(getSupportFragmentManager(), subCategoryListFragment, R.id.mainCategoriesFrame);
        } else {
            Intent intent = new Intent(this, SubCategoryActivity.class);
            intent.putExtra(SubCategoryActivity.MAIN_CATEGORY_ID, category.getNumber());
            startActivity(intent);
        }
    }

    @Override
    public void onSubCategorySelected(Category category) {
        System.out.println(getClass().getSimpleName() + " onSubCategorySelected");

        if(isDualPane) {
            ItemsListFragment itemsListFragment = (ItemsListFragment) getSupportFragmentManager().findFragmentById(R.id.itemsFrame);
            if (itemsListFragment == null) {
                itemsListFragment = ItemsListFragment.newInstance(category.getNumber());
                new ItemsListPresenter(itemsListFragment);
                ActivityUtil.replaceFragmentToActivityAddBackStack(getSupportFragmentManager(), itemsListFragment, R.id.itemsFrame);
            } else {
                itemsListFragment.getPresenter().loadItems(category.getNumber());
            }
        } else {
            Intent intent = new Intent(this, ItemsListActivity.class);
            intent.putExtra(ItemsListActivity.CATEGORY_ID, category.getNumber());
            startActivity(intent);
        }
    }

    public void setSubCategoryPresenter(String categoryNumber) {
        System.out.println(getClass().getSimpleName() + " setSubCategoryPresenter");

        if (subCategoryPresenter == null) {
            subCategoryPresenter = (CategoryListPresenter) getLastCustomNonConfigurationInstance();
        }

        if (subCategoryPresenter == null) {
            subCategoryPresenter = new CategoryListPresenter(categoryNumber);
        }

        subCategoryPresenter.setCategoryNumber(categoryNumber);
    }

    protected SubCategoryListFragment getSubCategoryListFragment() {
        System.out.println(getClass().getSimpleName() + " getSubCategoryListFragment");
        return SubCategoryListFragment.newInstance();
    }
}
