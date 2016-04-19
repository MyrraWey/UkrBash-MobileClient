package com.zinitsolutions.test.testapplication;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zinitsolutions.test.testapplication.API.ApiFactory;
import com.zinitsolutions.test.testapplication.models.PicturesPost;
import com.zinitsolutions.test.testapplication.models.Post;

import java.util.ArrayList;
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

    private class PostHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Post mPost;

        private TextView mTitle;
        private ImageView mImage;

        public PostHolder(View itemView) {
            super(itemView);

            this.mTitle = (TextView) itemView.findViewById(R.id.posts_list_item_title);
            this.mImage = (ImageView) itemView.findViewById(R.id.posts_list_item_image);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(
                    getActivity(),
                    this.mTitle.getText() + " clicked!",
                    Toast.LENGTH_SHORT
            ).show();
        }

        public void bind(Post post) {
            this.mPost = post;

            this.mTitle.setText(this.mPost.getTitle());
            this.mImage.setImageURI(Uri.parse(this.mPost.getThumbnail()));
        }
    }

    private class PostsAdapter extends RecyclerView.Adapter<PostHolder> {
        private List<Post> mPostsList;

        public PostsAdapter(List<Post> postsList) {
            this.mPostsList = postsList;
        }

        @Override
        public PostHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View v = inflater.inflate(R.layout.posts_list_item, parent, false);

            return new PostHolder(v);
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_posts_list, container, false);

        this.mRecyclerView = (RecyclerView) view.findViewById(R.id.posts_list_recycler_view);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        this.mPostsAdapter = new PostsAdapter(getPosts());
        this.mRecyclerView.setAdapter(this.mPostsAdapter);

        return view;
    }

    private List<Post> getPosts() {
        List<Post> posts = new ArrayList<>();

        String apiKey = getResources().getString(R.string.ukr_bash_api_key);

        Call<List<PicturesPost>> postss = ApiFactory.getUBashService().getRandomPictures(apiKey);
        postss.enqueue(this);

        return posts;
    }

    @Override
    public void onResponse(Response<List<PicturesPost>> response) {
        if (response.isSuccess()) {
            List<PicturesPost> airports = response.body();
            //TODO result processing
        }
    }

    @Override
    public void onFailure(Throwable t) {
        //TODO fil processing

        //TODO process internet missing
    }
}
