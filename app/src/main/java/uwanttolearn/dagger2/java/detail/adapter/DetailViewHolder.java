package uwanttolearn.dagger2.java.detail.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import uwanttolearn.dagger2.R;
import uwanttolearn.dagger2.java.pojos.GitHubUserRepository;

/**
 * Created by waleed on 22/07/2017.
 */

class DetailViewHolder extends RecyclerView.ViewHolder {

    private final TextView repositoryNameTextView;

    public DetailViewHolder(View itemView) {
        super(itemView);
        repositoryNameTextView = (TextView) itemView.findViewById(R.id.RowDetail_repository_name_text_view);
    }


    public void bind(GitHubUserRepository gitHubUserRepository) {
        repositoryNameTextView.setText(gitHubUserRepository.getName());
    }
}

