package uwanttolearn.dagger2.java.repositories.github;

import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uwanttolearn.dagger2.java.repositories.github.GitHubService;

/**
 * Created by waleed on 22/07/2017.
 */

public class GitHubServiceGenerator {

    private static GitHubService gitHubService;

    private GitHubServiceGenerator() {
    }

    public static GitHubService gitHubService(String baseUrl) {

        if (gitHubService == null) {

            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(
                    message -> Log.i("Retrofit Network", message))
                    .setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(httpLoggingInterceptor)
                    .readTimeout(5, TimeUnit.MINUTES)
                    .connectTimeout(5, TimeUnit.MINUTES).build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();

            gitHubService = retrofit.create(GitHubService.class);
        }
        return gitHubService;
    }

}
