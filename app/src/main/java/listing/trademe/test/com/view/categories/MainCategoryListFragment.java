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
import listing.trademe.test.com.view.MainActivity;

/**
 * Created by Letícia on 21/02/2018.
 */

public class MainCategoryListFragment extends CategoryBaseFragment {

    public static final String MAIN_CATEGORY_LEVEL = "0";

    @BindView(R.id.listCategories)
    RecyclerView listMainCategories;
    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swipeRefreshLayout;

    private OnCategorySelectedListener listener;
    public interface OnCategorySelectedListener {
        void onCategorySelected(Category category);
    }

    public static MainCategoryListFragment newInstance() {
        return new MainCategoryListFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        System.out.println(getClass().getSimpleName() + " onAttach");

        if (context instanceof OnCategorySelectedListener) {
            listener = (OnCategorySelectedListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnCategorySelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println(getClass().getSimpleName() + " onCreateView");

        View view = inflater.inflate(R.layout.fragment_main_categories, container, false);
        ButterKnife.bind(this, view);

        if(presenter == null) {
            ((MainActivity)getActivity()).setCategoryPresenter();
            presenter = ((MainActivity)getActivity()).mainCategoryPresenter;
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
    public void showResult(Category category) {
        CategoryAdapter categoryAdapter = new CategoryAdapter(category.getSubCategories(), new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Category mainCategory) {
                if(mainCategory.isNotLeaf()) {
                    if (null != listener) {
                        listener.onCategorySelected(mainCategory);
                    }
                }
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
