package listing.trademe.test.com.view.categories;

import android.support.annotation.NonNull;

import listing.trademe.test.com.model.Category;
import listing.trademe.test.com.network.APIConnection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Letícia on 21/02/2018.
 */

public class CategoryListPresenter implements CategoryListContract.Presenter {

    private CategoryListContract.View view;
    private APIConnection connection;
    private Category category;

    private String categoryNumber;

    public CategoryListPresenter(String categoryId) { this.categoryNumber = categoryId; }

    @Override
    public void setView(@NonNull CategoryListContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {
        connection = new APIConnection();
    }

    @Override
    public void loadCategory() {
        view.setLoadingIndicator(true);

        if(category != null && category.getNumber().equals(categoryNumber)) {
            System.out.println(getClass().getSimpleName() + " Load category from memory");
            view.showResult(category);
            view.setLoadingIndicator(false);
        } else {
            System.out.println(getClass().getSimpleName() + " Load category from server");
            connection.getCategories(categoryNumber, new Callback<Category>() {
                @Override
                public void onResponse(@NonNull Call<Category> call, @NonNull Response<Category> response) {
                    if(response.isSuccessful()) {
                        category = response.body();
                        view.showResult(category);
                    } else {
                        view.showEmpty();
                    }

                    view.setLoadingIndicator(false);
                }

                @Override
                public void onFailure(@NonNull Call<Category> call, @NonNull Throwable t) {
                    view.showError();
                    view.setLoadingIndicator(false);
                }
            });
        }
    }

    public void setCategoryNumber(String categoryNumber) {
        this.categoryNumber = categoryNumber;
    }

    public String getCategoryNumber() {
        return categoryNumber;
    }
}
