package uwanttolearn.dagger2.java.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import uwanttolearn.dagger2.R;
import uwanttolearn.dagger2.java.app.App;
import uwanttolearn.dagger2.java.detail.DetailActivity;
import uwanttolearn.dagger2.java.home.adapter.HomeAdapter;
import uwanttolearn.dagger2.java.repositories.github.GitHubRepository;
import uwanttolearn.dagger2.java.utils.FormatString;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private Disposable disposable;

    @Inject
    GitHubRepository gitHubRepository;
    @Inject
    HomeAdapter homeAdapter;
    @Inject
    RecyclerView.LayoutManager layoutManager;
    @Inject
    FormatString formatString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        DaggerHomeActivityComponent.builder()
                .appComponent(App.getApp().getAppComponent())
                .homeActivityModule(new HomeActivityModule(this))
                .build().inject(this);
        initViews();
        initRecyclerView();
        loadData();
        homeAdapter.getClickSubject().subscribe(gitHubUser -> DetailActivity.start(this,
                gitHubUser.getLogin(), gitHubUser.getAvatarUrl()));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable != null && !disposable.isDisposed())
            disposable.dispose();
    }

    private void initViews() {
        recyclerView = (RecyclerView) findViewById(R.id.MainActivity_recycler_view);
        progressBar = (ProgressBar) findViewById(R.id.MainActivity_progress_bar);
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(homeAdapter);
    }

    private void loadData() {
        gitHubRepository.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(gitHubUsers ->
                        {
                            homeAdapter.add(gitHubUsers);
                            progressBar.setVisibility(View.GONE);
                        },
                        error -> Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show(),
                        () -> {
                        },
                        disposable -> this.disposable = disposable);
    }
}
