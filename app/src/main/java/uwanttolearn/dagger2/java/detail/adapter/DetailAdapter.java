package uwanttolearn.dagger2.java.detail.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import uwanttolearn.dagger2.R;
import uwanttolearn.dagger2.java.pojos.GitHubUser;
import uwanttolearn.dagger2.java.pojos.GitHubUserRepository;

/**
 * Created by waleed on 22/07/2017.
 */

public class DetailAdapter extends RecyclerView.Adapter<DetailViewHolder> {

    private LayoutInflater layoutInflater;
    private List<GitHubUserRepository> gitHubUserRepositoies;

    public DetailAdapter(List<GitHubUserRepository> gitHubUserRepositoies) {
        if (gitHubUserRepositoies == null)
            throw new IllegalArgumentException("List<GitHubUserRepository> required");
        this.gitHubUserRepositoies = gitHubUserRepositoies;
    }

    @Override
    public DetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());
        return new DetailViewHolder(layoutInflater.inflate(R.layout.row_detail, parent, false));
    }

    @Override
    public void onBindViewHolder(DetailViewHolder holder, int position) {
        holder.bind(gitHubUserRepositoies.get(position));
    }

    @Override
    public int getItemCount() {
        return gitHubUserRepositoies.size();
    }

    public void add(List<GitHubUserRepository> gitHubUserRepositoies) {
        if (gitHubUserRepositoies == null) {
            throw new NullPointerException("Required List<GitHubUserRepository> but getting Null");
        }
        this.gitHubUserRepositoies.addAll(gitHubUserRepositoies);
        notifyDataSetChanged();
    }
}
