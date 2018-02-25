package listing.trademe.test.com.network;

import listing.trademe.test.com.model.Category;
import listing.trademe.test.com.model.Item;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Letícia on 20/02/2018.
 */

interface API {

    @GET("v1/Categories/{number}.json")
    Call<Category> getCategories(@Path("number") String number, @Query("depth") int depth);

    @GET("v1/Search/General.json")
    Call<Item> getItems(@Header("Authorization") String authorization, @Header("Content-Type") String contentType, @Query("category") String category, @Query("rows") int rows);

}
