package uwanttolearn.dagger2.java.app;

import com.bumptech.glide.Glide;

import dagger.Component;
import uwanttolearn.dagger2.java.repositories.github.GitHubRepository;

/**
 * Created by waleed on 20/08/2017.
 */


@Component(modules = AppModule.class)
public interface AppComponent {

    GitHubRepository getGitHubRepository();

    Glide getGlide();

}
