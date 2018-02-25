package listing.trademe.test.com.view.items;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import listing.trademe.test.com.R;
import listing.trademe.test.com.adapter.ItemAdapter;
import listing.trademe.test.com.model.Item;

/**
 * Created by Letícia on 23/02/2018.
 */

public class ItemsListFragment extends Fragment implements ItemsListContract.View {

    @NonNull
    private static final String CATEGORY_ID = "CATEGORY_ID";

    private ItemsListContract.Presenter presenter;

    @BindView(R.id.listItems)
    RecyclerView listItems;
    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swipeRefreshLayout;

    private String categoryId;

    public static ItemsListFragment newInstance(@Nullable String categoryId) {
        ItemsListFragment subCategoryListFragment = new ItemsListFragment();

        Bundle arguments = new Bundle();
        arguments.putString(CATEGORY_ID, categoryId);
        subCategoryListFragment.setArguments(arguments);

        return subCategoryListFragment;
    }

    @Override
    public void setPresenter(ItemsListContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onResume() {
        if(presenter == null) {
            presenter = new ItemsListPresenter(this);
        }

        presenter.start();

        categoryId = getArguments().getString(CATEGORY_ID);
        presenter.loadItems(categoryId);
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_items, container, false);
        ButterKnife.bind(this, view);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadItems(categoryId);
            }
        });

        return view;
    }

    @Override
    public void setLoadingIndicator(final boolean showIndicator) {
        if (getView() == null) {
            return;
        }

        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(showIndicator);
            }
        });
    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void showError() {

    }

    public void setItems(ArrayList<Item.ItemDetail> items) {
        ItemAdapter itemAdapter = new ItemAdapter(items);
        itemAdapter.notifyDataSetChanged();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        listItems.setLayoutManager(layoutManager);
        listItems.setHasFixedSize(true);
        listItems.setAdapter(itemAdapter);
    }

    public ItemsListContract.Presenter getPresenter() {
        return presenter;
    }
}
