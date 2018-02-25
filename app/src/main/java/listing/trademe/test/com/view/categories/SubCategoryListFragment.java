package listing.trademe.test.com.view.categories;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import listing.trademe.test.com.R;
import listing.trademe.test.com.adapter.CategoryAdapter;
import listing.trademe.test.com.model.Category;
import listing.trademe.test.com.view.BaseActiviy;

/**
 * Created by Letícia on 22/02/2018.
 */

public class SubCategoryListFragment extends CategoryBaseFragment {

    @BindView(R.id.listCategories)
    RecyclerView listMainCategories;
    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swipeRefreshLayout;

    private OnSubCategorySelectedListener listener;

    public interface OnSubCategorySelectedListener {
        void onSubCategorySelected(Category category);
    }

    public static SubCategoryListFragment newInstance() {
        return new SubCategoryListFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        System.out.println(getClass().getSimpleName() + " onAttach");

        if (context instanceof OnSubCategorySelectedListener) {
            listener = (OnSubCategorySelectedListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnSubCategorySelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println(getClass().getSimpleName() + " onCreateView");

        View view = inflater.inflate(R.layout.fragment_subcategories, container, false);
        ButterKnife.bind(this, view);

        if(presenter == null) {
            String categoryId = (getActivity()).getIntent().getStringExtra(SubCategoryActivity.MAIN_CATEGORY_ID);
            System.out.println(getClass().getSimpleName() + " onCreateView - categoryId: " + categoryId);
            ((BaseActiviy)getActivity()).setSubCategoryPresenter(categoryId);
            presenter = ((BaseActiviy)getActivity()).subCategoryPresenter;
        }
        presenter.loadCategory();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadCategory();
            }
        });

        return view;
    }

    @Override
    public void showResult(final Category category) {
        System.out.println(getClass().getSimpleName() + " showResult: " + category.toString());
        CategoryAdapter categoryAdapter = new CategoryAdapter(category.getSubCategories(), new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Category item) {
                listener.onSubCategorySelected(category);
            }
        });
        categoryAdapter.notifyDataSetChanged();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        listMainCategories.setLayoutManager(layoutManager);
        listMainCategories.setHasFixedSize(true);
        listMainCategories.setAdapter(categoryAdapter);
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
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
