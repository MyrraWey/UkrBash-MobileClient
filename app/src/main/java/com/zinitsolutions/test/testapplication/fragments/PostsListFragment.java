package com.zinitsolutions.test.testapplication.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zinitsolutions.test.testapplication.API.ApiFactory;
import com.zinitsolutions.test.testapplication.R;
import com.zinitsolutions.test.testapplication.adapters.PostsAdapter;
import com.zinitsolutions.test.testapplication.decorators.PostListItemDecoration;
import com.zinitsolutions.test.testapplication.listeners.OnLoadMoreListener;
import com.zinitsolutions.test.testapplication.posts.IPost;
import com.zinitsolutions.test.testapplication.posts.PicturesPost;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

/**
 * Created by dmitrij on 4/19/16.
 */
public class PostsListFragment extends Fragment {

    private static final String LOADED_POSTS_LIST = "loadedPosts";
    private static final int LOAD_ITEMS_ON_REQUEST = 15;

    private RecyclerView mRecyclerView;
    private PostsAdapter mPostsAdapter;

    private List<IPost> mPicturesPosts;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_posts_list, container, false);

        this.mRecyclerView = (RecyclerView) view.findViewById(R.id.posts_list_recycler_view);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.mRecyclerView.setHasFixedSize(true);
        this.mRecyclerView.addItemDecoration(new PostListItemDecoration());

        if (this.mPicturesPosts == null) {
            if (savedInstanceState != null) {
                this.mPicturesPosts = (List<IPost>) savedInstanceState.getSerializable(LOADED_POSTS_LIST);
            } else {
                this.mPicturesPosts = new ArrayList<>();
            }

            this.mPostsAdapter = new PostsAdapter(this.mPicturesPosts, this.mRecyclerView);
            this.mRecyclerView.setAdapter(this.mPostsAdapter);

            if (this.mPicturesPosts.size() == 0) {
                loadPosts(LOAD_ITEMS_ON_REQUEST);
            }
        } else {
            this.mRecyclerView.setAdapter(this.mPostsAdapter);
        }

        this.mPostsAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                //TODO handle articles load on orientation change

                // subtract 1 because we have extra LoadingPost
                int itemsCount = mPicturesPosts.size() - 1;

                loadPosts(LOAD_ITEMS_ON_REQUEST, itemsCount);
            }
        });

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(LOADED_POSTS_LIST, (Serializable) this.mPicturesPosts);
    }

    private void loadPosts(int count) {
        Call<List<PicturesPost>> call = ApiFactory.getUBashService().getPublishedPictures(count);

        handleRetrofitResponse(call);
    }

    private void loadPosts(int count, int offset) {
        Call<List<PicturesPost>> call = ApiFactory.getUBashService().getPublishedPictures(count, offset);

        handleRetrofitResponse(call);
    }

    private void handleRetrofitResponse(Call<List<PicturesPost>> call) {
        call.enqueue(new Callback<List<PicturesPost>>() {
            @Override
            public void onResponse(Response<List<PicturesPost>> response) {
                if (response.isSuccess()) {
                    mPostsAdapter.addItems(new ArrayList<IPost>(response.body()));
                }
            }

            @Override
            public void onFailure(Throwable t) {
                //TODO fail processing

                //TODO process internet missing
            }
        });
    }

}
