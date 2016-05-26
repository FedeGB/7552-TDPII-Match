package com.tallerii.match.core;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

/**
 * Created by Demian on 25/05/2016.
 */
public class ImageManager {
    static public Bitmap decodeFromBase64(String base64){
        byte[] data = Base64.decode(base64, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(data, 0, data.length);
    }
}
