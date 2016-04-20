package com.zinitsolutions.test.testapplication;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zinitsolutions.test.testapplication.API.ApiFactory;
import com.zinitsolutions.test.testapplication.adapters.PostsAdapter;
import com.zinitsolutions.test.testapplication.models.PicturesPost;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

/**
 * Created by dmitrij on 4/19/16.
 */
public class PostsListFragment extends Fragment implements Callback<List<PicturesPost>> {
    private RecyclerView mRecyclerView;
    private PostsAdapter mPostsAdapter;

    private class VerticalSpaceItemDecoration extends RecyclerView.ItemDecoration {

        private final int mVerticalSpaceHeight;

        public VerticalSpaceItemDecoration(int mVerticalSpaceHeight) {
            this.mVerticalSpaceHeight = mVerticalSpaceHeight;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                   RecyclerView.State state) {
            outRect.bottom = mVerticalSpaceHeight;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_posts_list, container, false);

        this.mRecyclerView = (RecyclerView) view.findViewById(R.id.posts_list_recycler_view);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //TODO replace to separate class
        this.mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            private final int mVerticalSpaceHeight = 10;

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                       RecyclerView.State state) {
                outRect.bottom = mVerticalSpaceHeight;
            }
        });

        loadPosts();

        return view;
    }

    private void loadPosts() {
        String apiKey = getResources().getString(R.string.ukr_bash_api_key);
        Call<List<PicturesPost>> posts = ApiFactory.getUBashService().getRandomPictures(apiKey, 100);
        posts.enqueue(this);
    }

    @Override
    public void onResponse(Response<List<PicturesPost>> response) {
        if (response.isSuccess()) {
            List<PicturesPost> picturesPosts = response.body();

            this.mPostsAdapter = new PostsAdapter(getActivity(), picturesPosts);
            this.mRecyclerView.setAdapter(this.mPostsAdapter);
        }
    }

    @Override
    public void onFailure(Throwable t) {
        //TODO fail processing

        //TODO process internet missing
    }
}
