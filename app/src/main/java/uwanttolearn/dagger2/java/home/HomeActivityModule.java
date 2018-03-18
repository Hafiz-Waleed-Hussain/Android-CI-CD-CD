package uwanttolearn.dagger2.java.home;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import uwanttolearn.dagger2.java.app.App;
import uwanttolearn.dagger2.java.home.adapter.HomeAdapter;
import uwanttolearn.dagger2.java.repositories.github.GitHubRepository;
import uwanttolearn.dagger2.java.utils.FormatString;

/**
 * Created by waleed on 06/08/2017.
 */


@Module
public class HomeActivityModule {

    private final Context context;

    public HomeActivityModule(Context context) {
        this.context = context;
    }

    @Provides
    public RecyclerView.LayoutManager layoutManager() {
        return new LinearLayoutManager(context);
    }

    @Provides
    public HomeAdapter homeAdapter(Glide glide) {
        return new HomeAdapter(new ArrayList<>(), glide);
    }

    @Provides
    public FormatString formatString() {
        return new FormatString("Hello Guys");
    }
}
