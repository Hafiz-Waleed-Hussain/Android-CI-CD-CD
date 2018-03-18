package uwanttolearn.dagger2.java.repositories.github;

import java.util.List;

import io.reactivex.Observable;
import uwanttolearn.dagger2.java.pojos.GitHubUser;
import uwanttolearn.dagger2.java.pojos.GitHubUserRepository;

/**
 * Created by waleed on 22/07/2017.
 */

interface GitHubRepositoryDataSource {

    Observable<List<GitHubUser>> getUsers();

    Observable<List<GitHubUserRepository>> getRepositories(String usename);
}
