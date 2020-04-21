package com.enter.taehyung.imagegallery.util;

import android.content.Context;
import android.util.Log;

import java.util.List;

public class Utils {
    private static final String TAG = Utils.class.getSimpleName();

    /********************************************************************************************************
     * DENSITY
     ********************************************************************************************************/
    private static float mDensity;

    public static void setDensity(Context context) {
        mDensity = context.getResources().getDisplayMetrics().density;
        Log.d(TAG, "setDensity() density: " + mDensity);
    }

    public static int changeDP2Pixel(int d) {
        return (int) (d * mDensity + 0.5f);
    }

    public static int changeDP2Pixel(float d) {
        return (int) (d * mDensity + 0.5f);
    }

    public static boolean isListEmpty(List<?> list) {
        if (list == null || list.size() == 0) {
            return true;
        }
        return false;
    }
}
