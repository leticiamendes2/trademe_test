package listing.trademe.test.com.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import listing.trademe.test.com.model.Category;
import listing.trademe.test.com.model.Item;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Letícia on 20/02/2018.
 */

public class APIConnection {

    private static final String BASE_URL = "https://api.tmsandbox.co.nz/";

    private static final String API_KEY = "A1AC63F0332A131A78FAC304D007E7D1";
    private static final String API_SECRET = "EC7F18B17A062962C6930A8AE88B16C7";

    private final API api;

    public APIConnection() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
//                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        api = retrofit.create(API.class);
    }

    public void getCategories(String number, Callback<Category> callback) {
        Call<Category> call = api.getCategories(number, 1);
        call.enqueue(callback);
    }

    public void getItems(final String number, final Callback<Item> callback) {
        String authorization = "OAuth oauth_consumer_key=\""+API_KEY+"\", oauth_signature_method=\"PLAINTEXT\", oauth_signature=\""+API_SECRET+"&\"";
        String contentType = "application/x-www-form-urlencoded";

        Call<Item> call = api.getItems(authorization, contentType, number, 20);
        call.enqueue(callback);
    }
}
