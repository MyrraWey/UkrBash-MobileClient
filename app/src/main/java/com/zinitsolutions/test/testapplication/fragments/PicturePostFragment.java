package com.zinitsolutions.test.testapplication.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zinitsolutions.test.testapplication.R;
import com.zinitsolutions.test.testapplication.helpers.Utils;
import com.zinitsolutions.test.testapplication.posts.PicturesPost;

/**
 * Created by dmitrij on 4/20/16.
 */
public class PicturePostFragment extends Fragment {

    private static final String PICTURE_POST_KEY = "picturePost";

    private PicturesPost mPicturesPost;

    private TextView mPostId;
    private ImageView mPostImage;
    private TextView mPostTitle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_picture_post, container, false);

        this.mPostId = (TextView) v.findViewById(R.id.picture_post_id);
        this.mPostImage = (ImageView) v.findViewById(R.id.picture_post_image);
        this.mPostTitle = (TextView) v.findViewById(R.id.picture_post_title);

        this.mPicturesPost = (PicturesPost) getArguments().getSerializable(PICTURE_POST_KEY);

        this.mPostId.setText(getResources().getString(R.string.picture_post_id_prefix) + this.mPostImage.getId());
        Utils.loadBitmapByUrl(getActivity(), this.mPicturesPost.getThumbnail(), this.mPostImage);
        this.mPostTitle.setText(this.mPicturesPost.getTitle());

        return v;
    }

    public static final Fragment loadFromPicturePost(PicturesPost picturesPost) {
        Fragment fragment = new PicturePostFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(PICTURE_POST_KEY, picturesPost);
        fragment.setArguments(bundle);

        return fragment;
    }

}
