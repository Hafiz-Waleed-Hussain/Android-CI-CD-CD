package uwanttolearn.dagger2.java.repositories.github;

import java.util.List;

import io.reactivex.Observable;
import uwanttolearn.dagger2.java.pojos.GitHubUser;
import uwanttolearn.dagger2.java.pojos.GitHubUserRepository;

/**
 * Created by waleed on 22/07/2017.
 */

class RemoteGitHubRepository implements GitHubRepositoryDataSource {

    private final GitHubService gitHubService;

    public RemoteGitHubRepository(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }


    @Override
    public Observable<List<GitHubUser>> getUsers() {
        return gitHubService.getUsers();
    }

    @Override
    public Observable<List<GitHubUserRepository>> getRepositories(String username) {
        return gitHubService.getUserRepositories(username);
    }
}
