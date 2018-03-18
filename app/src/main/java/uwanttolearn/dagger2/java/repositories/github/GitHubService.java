package uwanttolearn.dagger2.java.repositories.github;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import uwanttolearn.dagger2.java.pojos.GitHubUser;
import uwanttolearn.dagger2.java.pojos.GitHubUserRepository;

/**
 * Created by waleed on 22/07/2017.
 */
interface GitHubService {


    @GET("/users")
    Observable<List<GitHubUser>> getUsers();

    @GET("/users/{username}/repos")
    Observable<List<GitHubUserRepository>> getUserRepositories(@Path("username") String username);
}
