package com.zinitsolutions.test.testapplication.decorators;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by MyrraWey on 25.04.2016.
 */
public class PostListItemDecoration extends RecyclerView.ItemDecoration {

    private final int mVerticalSpaceHeight = 10;

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        outRect.bottom = mVerticalSpaceHeight;
    }

}
