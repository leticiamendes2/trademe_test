package listing.trademe.test.com.view.items;

import java.util.ArrayList;

import listing.trademe.test.com.model.Item;
import listing.trademe.test.com.util.BasePresenter;
import listing.trademe.test.com.util.BaseView;

/**
 * Created by Letícia on 21/02/2018.
 */

public interface ItemsListContract {

    interface View extends BaseView<Presenter> {
        void setItems(ArrayList<Item.ItemDetail> items);
    }

    interface Presenter extends BasePresenter {
        void loadItems(String categoryNumber);
    }
}
