package uwanttolearn.dagger2.java.home.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
import uwanttolearn.dagger2.R;
import uwanttolearn.dagger2.java.pojos.GitHubUser;

/**
 * Created by waleed on 22/07/2017.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeViewHolder> {


    private final Glide glide;
    private LayoutInflater layoutInflater;
    private Subject<GitHubUser> clickSubject;
    private List<GitHubUser> gitHubUsers;

    public HomeAdapter(List<GitHubUser> gitHubUsers, Glide glide) {
        if (gitHubUsers == null)
            throw new IllegalArgumentException("List<GitHubUser> required");
        this.gitHubUsers = gitHubUsers;
        this.clickSubject = PublishSubject.create();
        this.glide = glide;
    }


    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());
        return new HomeViewHolder(layoutInflater.inflate(R.layout.row_home, parent, false), clickSubject, glide);
    }

    @Override
    public void onBindViewHolder(HomeViewHolder holder, int position) {
        holder.bind(gitHubUsers.get(position));
    }


    @Override
    public int getItemCount() {
        return gitHubUsers.size();
    }

    public Observable<GitHubUser> getClickSubject() {
        return clickSubject;
    }

    public void add(List<GitHubUser> gitHubUsers) {
        if (gitHubUsers == null) {
            throw new NullPointerException("Required List<GitHubUser> but getting Null");
        }
        this.gitHubUsers.addAll(gitHubUsers);
        notifyDataSetChanged();
    }

}


