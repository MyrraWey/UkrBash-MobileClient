package com.zinitsolutions.test.testapplication.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zinitsolutions.test.testapplication.R;
import com.zinitsolutions.test.testapplication.holders.PostHolder;
import com.zinitsolutions.test.testapplication.models.Post;

import java.util.List;

/**
 * Created by dmitrij on 4/20/16.
 */
public class PostsAdapter extends RecyclerView.Adapter<PostHolder> {
    private Context mContext;

    private List<Post> mPostsList;

    public PostsAdapter(Context context, List<Post> postsList) {
        this.mContext = context;
        this.mPostsList = postsList;
    }

    @Override
    public PostHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.mContext);
        View v = inflater.inflate(R.layout.posts_list_item, parent, false);

        return new PostHolder(v, this.mContext);
    }

    @Override
    public void onBindViewHolder(PostHolder holder, int position) {
        holder.bind(this.mPostsList.get(position));
    }

    @Override
    public int getItemCount() {
        return mPostsList.size();
    }
}