package uwanttolearn.dagger2.java.repositories.github;

import java.util.List;

import io.reactivex.Observable;
import uwanttolearn.dagger2.java.pojos.GitHubUser;
import uwanttolearn.dagger2.java.pojos.GitHubUserRepository;

/**
 * Created by waleed on 22/07/2017.
 */

public class GitHubRepository implements GitHubRepositoryDataSource {

    private final GitHubRepositoryDataSource remoteGitHubRepository;

    private static GitHubRepository INSTANCE;


    public static GitHubRepository getInstance(GitHubService gitHubService) {
        if (INSTANCE == null)
            INSTANCE = new GitHubRepository(gitHubService);
        return INSTANCE;
    }

    private GitHubRepository(GitHubService gitHubService) {
        remoteGitHubRepository = new RemoteGitHubRepository(gitHubService);
    }


    @Override
    public Observable<List<GitHubUser>> getUsers() {
        return remoteGitHubRepository.getUsers();
    }

    @Override
    public Observable<List<GitHubUserRepository>> getRepositories(String username) {
        return remoteGitHubRepository.getRepositories(username);
    }
}
