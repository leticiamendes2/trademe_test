package listing.trademe.test.com.view.items;

import android.support.annotation.NonNull;

import listing.trademe.test.com.model.Item;
import listing.trademe.test.com.network.APIConnection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Letícia on 23/02/2018.
 */

public class ItemsListPresenter implements ItemsListContract.Presenter {

    private final ItemsListContract.View view;
    private APIConnection connection;
    private static Item item;

    public ItemsListPresenter(@NonNull ItemsListContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {
        connection = new APIConnection();
    }

    @Override
    public void loadItems(String categoryNumber) {
        view.setLoadingIndicator(true);

        if(item != null && item.getItems().size() > 0 && item.getItems().get(0).getCategoryNumber().contains(categoryNumber)) {
            view.setItems(item.getItems());
            view.setLoadingIndicator(false);
        } else {
            connection.getItems(categoryNumber, new Callback<Item>() {
                @Override
                public void onResponse(@NonNull Call<Item> call, @NonNull Response<Item> response) {
                    if(response.isSuccessful()) {
                        item = response.body();
                        assert item != null;
                        view.setItems(item.getItems());
                    } else {
                        view.showEmpty();
                    }

                    view.setLoadingIndicator(false);
                }

                @Override
                public void onFailure(@NonNull Call<Item> call, @NonNull Throwable t) {
                    t.printStackTrace();
                    view.showError();
                    view.setLoadingIndicator(false);
                }
            });
        }
    }
}
