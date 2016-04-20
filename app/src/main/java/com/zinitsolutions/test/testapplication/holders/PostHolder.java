package com.zinitsolutions.test.testapplication.holders;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zinitsolutions.test.testapplication.R;
import com.zinitsolutions.test.testapplication.models.PicturesPost;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

/**
 * Created by dmitrij on 4/20/16.
 */
public class PostHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private Context mContext;

    private PicturesPost mPicturePost;

    private TextView mTitle;
    private ImageView mImage;
    private TextView mAuthor;
    private TextView mDate;

    public PostHolder(View itemView, Context context) {
        super(itemView);

        this.mContext = context;

        this.mTitle = (TextView) itemView.findViewById(R.id.posts_list_item_title);
        this.mImage = (ImageView) itemView.findViewById(R.id.posts_list_item_image);
        this.mAuthor = (TextView) itemView.findViewById(R.id.posts_list_item_author);
        this.mDate = (TextView) itemView.findViewById(R.id.posts_list_item_date);

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

    public void bind(PicturesPost post) {
        this.mPicturePost = post;

        this.mTitle.setText(this.mPicturePost.getTitle());
        this.mImage.setImageBitmap(loadBitmap(this.mPicturePost.getThumbnail()));
        this.mAuthor.setText(
                mContext.getResources().getText(R.string.post_list_item_author_prefix)
                        + this.mPicturePost.getAuthor()
        );
        this.mDate.setText(getDate());
    }

    /**
     * TODO fix image loading and delete changing ThreadPolicy in SingleFragmentActivity
     *
     * @param url
     * @return
     */
    private Bitmap loadBitmap(String url) {
        URL address = null;
        try {
            address = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Bitmap image = null;
        try {
            image = BitmapFactory.decodeStream(address.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }

    private String getDate() {
        // add milliseconds to timestamp
        Long timestamp = Long.parseLong(this.mPicturePost.getPubDate()) * 1000;
        Date date = new Date(timestamp);
        String result = DateFormat.format("EEEE, LLL d, yyyy hh:mm", date).toString();

        return result;
    }
}