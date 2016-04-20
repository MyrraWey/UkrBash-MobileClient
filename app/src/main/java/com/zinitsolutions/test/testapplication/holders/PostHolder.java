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

import com.zinitsolutions.test.testapplication.PicturePostFragment;
import com.zinitsolutions.test.testapplication.R;
import com.zinitsolutions.test.testapplication.SingleFragmentActivity;
import com.zinitsolutions.test.testapplication.Utils;
import com.zinitsolutions.test.testapplication.models.PicturesPost;

import java.util.Date;

/**
 * Created by dmitrij on 4/20/16.
 */
public class PostHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private Context mContext;

    private PicturesPost mPicturePost;

    private RelativeLayout mParent;
    private TextView mTitle;
    private ImageView mImage;
    private TextView mAuthor;
    private TextView mDate;

    public PostHolder(View itemView, Context context) {
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

    public void bind(PicturesPost post) {
        this.mPicturePost = post;

        if (this.mPicturePost.getViewed()) {
            this.mParent.setBackgroundResource(R.color.post_list_item_viewed);
        } else {
            this.mParent.setBackgroundResource(R.color.post_list_item_unviewed);
        }
        this.mTitle.setText(this.mPicturePost.getTitle());
        this.mImage.setImageBitmap(Utils.getBitmapByUrl(this.mPicturePost.getThumbnail()));
        this.mAuthor.setText(mContext.getResources().getText(R.string.post_list_item_author_prefix) + this.mPicturePost.getAuthor());
        this.mDate.setText(getDate());
    }

    private String getDate() {
        // add milliseconds to timestamp
        Long timestamp = Long.parseLong(this.mPicturePost.getPubDate()) * 1000;
        Date date = new Date(timestamp);
        String result = DateFormat.format("EEEE, LLL d, yyyy hh:mm", date).toString();

        return result;
    }
}