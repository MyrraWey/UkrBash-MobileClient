package com.zinitsolutions.test.testapplication.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.zinitsolutions.test.testapplication.R;
import com.zinitsolutions.test.testapplication.posts.IPost;

/**
 * Created by MyrraWey on 25.04.2016.
 */
public class LoadingHolder extends RecyclerView.ViewHolder implements IPostHolder {

    private ProgressBar mProgressBar;

    public LoadingHolder(View itemView) {
        super(itemView);

        this.mProgressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);
    }

    @Override
    public void bind(IPost post) {
        this.mProgressBar.setIndeterminate(true);
    }

}
