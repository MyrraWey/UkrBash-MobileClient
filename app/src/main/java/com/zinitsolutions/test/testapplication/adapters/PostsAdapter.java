package com.zinitsolutions.test.testapplication.adapters;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zinitsolutions.test.testapplication.R;
import com.zinitsolutions.test.testapplication.holders.IPostHolder;
import com.zinitsolutions.test.testapplication.holders.LoadingHolder;
import com.zinitsolutions.test.testapplication.holders.PictureHolder;
import com.zinitsolutions.test.testapplication.listeners.OnLoadMoreListener;
import com.zinitsolutions.test.testapplication.posts.IPost;
import com.zinitsolutions.test.testapplication.posts.LoadingPost;
import com.zinitsolutions.test.testapplication.posts.PostType;

import java.util.List;

/**
 * Created by dmitrij on 4/20/16.
 */
public class PostsAdapter extends RecyclerView.Adapter {

    private List<IPost> mPostsList;

    private OnLoadMoreListener mOnLoadMoreListener;
    private int mVisibleThreshold = 5;
    private boolean mLoadMore = false;

    public PostsAdapter(List<IPost> postsList, RecyclerView recyclerView) {
        this.mPostsList = postsList;

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int itemsCount = getItemCount();
                int lastVisible = linearLayoutManager.findLastVisibleItemPosition();

                if (mOnLoadMoreListener != null) {
                    if (!mLoadMore && itemsCount <= (lastVisible + mVisibleThreshold)) {
                        mLoadMore = true;
                        addLoadingPost();
                        mOnLoadMoreListener.onLoadMore();
                    }
                }
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        RecyclerView.ViewHolder viewHolder;

        switch (viewType) {
            case PostType.LOADING_POST:
                view = inflater.inflate(R.layout.loading_list_item, parent, false);
                viewHolder = new LoadingHolder(view);
                break;
            case PostType.PICTURE_POST:
                view = inflater.inflate(R.layout.picture_list_item, parent, false);
                viewHolder = new PictureHolder(view, parent.getContext());
                break;
            default:
                view = new View(parent.getContext());
                viewHolder = new RecyclerView.ViewHolder(view) {
                };
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((IPostHolder) holder).bind(this.mPostsList.get(position));
    }

    @Override
    public int getItemCount() {
        return mPostsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return this.mPostsList.get(position).getPostType();
    }

    public void addItems(List<IPost> posts) {
        if (this.mLoadMore) {
            this.mLoadMore = false;
            deleteLoadingPost();
        }

        int itemsCount = this.mPostsList.size();
        int length = posts.size();

        this.mPostsList.addAll(posts);
        this.notifyItemRangeInserted(itemsCount, length);
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.mOnLoadMoreListener = onLoadMoreListener;
    }

    private void addLoadingPost() {
        this.mPostsList.add(new LoadingPost());
        notifyItemInserted(this.mPostsList.size() - 1);
    }

    private void deleteLoadingPost() {
        this.mPostsList.remove(this.mPostsList.size() - 1);
        notifyItemRemoved(this.mPostsList.size());
    }

}