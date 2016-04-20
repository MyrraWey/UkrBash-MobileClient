package com.zinitsolutions.test.testapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by dmitrij on 4/20/16.
 */
public class Utils {
    /**
     * TODO fix image loading and delete changing ThreadPolicy in SingleFragmentActivity
     * @param url
     * @return
     */
    public static final Bitmap getBitmapByUrl(String url) {
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
}
