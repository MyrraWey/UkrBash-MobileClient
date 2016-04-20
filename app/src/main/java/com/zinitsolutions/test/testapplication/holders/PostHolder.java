package com.zinitsolutions.test.testapplication.holders;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zinitsolutions.test.testapplication.R;
import com.zinitsolutions.test.testapplication.models.Post;

/**
 * Created by dmitrij on 4/20/16.
 */
public class PostHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private Context mContext;

    private Post mPost;

    private TextView mTitle;
    private ImageView mImage;

    public PostHolder(View itemView, Context context) {
        super(itemView);

        this.mContext = context;

        this.mTitle = (TextView) itemView.findViewById(R.id.posts_list_item_title);
        this.mImage = (ImageView) itemView.findViewById(R.id.posts_list_item_image);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(
                this.mContext,
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