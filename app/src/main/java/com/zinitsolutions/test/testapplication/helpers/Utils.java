package com.zinitsolutions.test.testapplication.helpers;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zinitsolutions.test.testapplication.R;

/**
 * Created by dmitrij on 4/20/16.
 */
public class Utils {

    public static final void loadBitmapByUrl(Context context, String targetUrl, ImageView targetView) {
        Glide.with(context)
                .load(targetUrl)
                .placeholder(R.drawable.loader)
                .into(targetView);
    }
}
