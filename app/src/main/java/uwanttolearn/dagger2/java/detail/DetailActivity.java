package uwanttolearn.dagger2.java.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import uwanttolearn.dagger2.R;
import uwanttolearn.dagger2.java.app.App;
import uwanttolearn.dagger2.java.detail.adapter.DetailAdapter;
import uwanttolearn.dagger2.java.repositories.github.GitHubRepository;

/**
 * Created by waleed on 22/07/2017.
 */

public class DetailActivity extends AppCompatActivity {


    public static void start(Context context, String userName, String imageUrl) {
        validate(userName, imageUrl);
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(Intent.EXTRA_TEXT, userName);
        intent.putExtra("imageUrl", imageUrl);
        context.startActivity(intent);
    }

    private static void validate(String userName, String imageUrl) {
        if (userName == null)
            throw new NullPointerException("Username is null");
        if (userName.trim().isEmpty())
            throw new IllegalArgumentException("Username is empty");

        if (imageUrl == null)
            throw new NullPointerException("ImageUrl is null");
        if (imageUrl.trim().isEmpty())
            throw new IllegalArgumentException("ImageUrl is empty");
    }

    private ImageView picImageView;
    private RecyclerView repositoriesRecyclerView;
    private ProgressBar progressBar;
    private RecyclerView.LayoutManager layoutManager;
    private DetailAdapter detailAdapter;
    private GitHubRepository gitHubRepository;
    private Disposable disposable;
    private Glide glide;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        String username = getIntent().getStringExtra(Intent.EXTRA_TEXT);
        String imageUrl = getIntent().getStringExtra("imageUrl");
        validate(username, imageUrl);
        gitHubRepository = App.getApp().getAppComponent().getGitHubRepository(); // DON'T USE THIS APPROACH
        glide = App.getApp().getAppComponent().getGlide(); // DON'T USE THIS APPROACH

        initViews();
        initRecyclerView();
        loadData(username);
        loadImage(imageUrl);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }

    private void initViews() {
        repositoriesRecyclerView = (RecyclerView) findViewById(R.id.DetailActivity_recycler_view);
        picImageView = (ImageView) findViewById(R.id.DetailActivity_image_view);
        progressBar = (ProgressBar) findViewById(R.id.DetailActivity_progressBar);
    }


    private void initRecyclerView() {
        detailAdapter = new DetailAdapter(new ArrayList<>());
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        repositoriesRecyclerView.setLayoutManager(layoutManager);
        repositoriesRecyclerView.setAdapter(detailAdapter);
    }


    private void loadData(String username) {
        gitHubRepository.getRepositories(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(gitHubUserRepository -> {
                            detailAdapter.add(gitHubUserRepository);
                            progressBar.setVisibility(View.GONE);
                        }, error -> Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show(),
                        () -> {
                        },
                        disposable -> this.disposable = disposable);
    }

    private void loadImage(String imageUrl) {
        glide.with(this)
                .load(imageUrl)
                .into(picImageView);
    }

}
