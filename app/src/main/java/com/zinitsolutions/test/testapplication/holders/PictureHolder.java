package com.zinitsolutions.test.testapplication.holders;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zinitsolutions.test.testapplication.fragments.PicturePostFragment;
import com.zinitsolutions.test.testapplication.R;
import com.zinitsolutions.test.testapplication.activities.SingleFragmentActivity;
import com.zinitsolutions.test.testapplication.helpers.Utils;
import com.zinitsolutions.test.testapplication.posts.IPost;
import com.zinitsolutions.test.testapplication.posts.PicturesPost;

import java.util.Date;

/**
 * Created by dmitrij on 4/20/16.
 */
public class PictureHolder extends RecyclerView.ViewHolder implements IPostHolder, View.OnClickListener {

    private Context mContext;

    private PicturesPost mPicturePost;

    private RelativeLayout mParent;
    private TextView mTitle;
    private ImageView mImage;
    private TextView mAuthor;
    private TextView mDate;

    public PictureHolder(View itemView, Context context) {
        super(itemView);

        this.mContext = context;

        this.mParent = (RelativeLayout) itemView.findViewById(R.id.posts_list_item_parent);
        this.mTitle = (TextView) itemView.findViewById(R.id.posts_list_item_title);
        this.mImage = (ImageView) itemView.findViewById(R.id.posts_list_item_image);
        this.mAuthor = (TextView) itemView.findViewById(R.id.posts_list_item_author);
        this.mDate = (TextView) itemView.findViewById(R.id.posts_list_item_date);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.mPicturePost.setViewed(true);

        SingleFragmentActivity activity = (SingleFragmentActivity) this.mContext;
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        Fragment fragment = PicturePostFragment.loadFromPicturePost(this.mPicturePost);
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void bind(IPost post) {
        this.mPicturePost = (PicturesPost) post;

        if (this.mPicturePost.getViewed()) {
            this.mParent.setBackgroundResource(R.color.post_list_item_viewed);
        } else {
            this.mParent.setBackgroundResource(R.color.post_list_item_unviewed);
        }
        this.mTitle.setText(this.mPicturePost.getTitle());
        Utils.loadBitmapByUrl(this.mContext, this.mPicturePost.getThumbnail(), this.mImage);
        this.mAuthor.setText(mContext.getResources().getText(R.string.post_list_item_author_prefix) + this.mPicturePost.getAuthor());
        this.mDate.setText(getDate());
    }

    private String getDate() {
        // add milliseconds to timestamp
        Long timestamp = Long.parseLong(this.mPicturePost.getPubDate()) * 1000;
        Date date = new Date(timestamp);
        String result = DateFormat.format("dd.MM.yyyy hh:mm", date).toString();

        return result;
    }

}