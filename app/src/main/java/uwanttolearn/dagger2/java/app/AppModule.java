package uwanttolearn.dagger2.java.app;

import android.content.Context;

import com.bumptech.glide.Glide;

import dagger.Module;
import dagger.Provides;
import uwanttolearn.dagger2.java.repositories.github.GitHubRepository;
import uwanttolearn.dagger2.java.repositories.github.GitHubServiceGenerator;

/**
 * Created by waleed on 20/08/2017.
 */
@Module
public class AppModule {

    private final Context appContext;
    private final String url;

    public AppModule(Context appContext, String url) {
        this.appContext = appContext;
        this.url = url;
    }

    @Provides
    public GitHubRepository gitHubRepository() {
        return GitHubRepository.getInstance(GitHubServiceGenerator
                .gitHubService(url));
    }

    @Provides
    public Glide glide() {
        return Glide.get(appContext);
    }

}
