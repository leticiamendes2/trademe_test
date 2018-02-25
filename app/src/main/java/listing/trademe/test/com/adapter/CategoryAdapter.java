package listing.trademe.test.com.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import listing.trademe.test.com.R;
import listing.trademe.test.com.model.Category;

/**
 * Created by Letícia on 20/02/2018.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Category item);
    }

    private final List<Category> categories;
    private final OnItemClickListener listener;

    public CategoryAdapter(List<Category> categories, OnItemClickListener listener) {
        this.categories = categories;
        this.listener = listener;
    }

    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.list_item_category, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        if(categories != null) {
            return categories.size();
        } else {
            return 0;
        }
    }

    @Override
    public void onBindViewHolder(CategoryAdapter.ViewHolder holder, int position) {
        Category category = categories.get(position);
        holder.bind(category, listener);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.category_text) TextView txtCategoryName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final Category category, final OnItemClickListener listener) {
            txtCategoryName.setText(category.getName());

            if(category.isNotLeaf() || category.hasClassifieds())
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        listener.onItemClick(category);
                    }
                });
            // else set text color grey
        }
    }
}
