package com.enter.taehyung.imagegallery.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

/**
 * Common한 Util class
 */
public class Utils {
    private static final String TAG = Utils.class.getSimpleName();

    private static Context mContext;
    private static float mDensity;

    public static void setDensity(Context context) {
        mContext = context;
        mDensity = context.getResources().getDisplayMetrics().density;
        Log.d(TAG, "setDensity() density: " + mDensity);
    }

    /**
     * 해상도별 dp -> pixel 단위 변환
     * @param d: int
     * @return int
     */
    public static int changeDP2Pixel(int d) {
        return (int) (d * mDensity + 0.5f);
    }

    /**
     * 해상도별 dp -> pixel 단위 변환
     * @param d: float
     * @return int
     */
    public static int changeDP2Pixel(float d) {
        return (int) (d * mDensity + 0.5f);
    }

    /**
     * List null or empty 체크
     * @param list
     * @return boolean
     */
    public static boolean isListEmpty(List<?> list) {
        if (list == null || list.size() == 0) {
            return true;
        }
        return false;
    }

    /**
     * Toast 노출
     * @param desc: String
     */
    public static void showToast(String desc) {
        Toast.makeText(mContext, desc, Toast.LENGTH_SHORT).show();
    }
}
