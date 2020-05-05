package com.enter.taehyung.imagegallery.network;

import android.os.Bundle;

import com.enter.taehyung.imagegallery.data.ImageData;

import java.util.ArrayList;

public interface NetworkListener {
    void onResult(Bundle bundle);

    /**
     * Refactoring.
     *
     * @param state int
     * @param list ArrayList<ImageData>
     */
    void onResult(@NetworkConst.STATUS int state, ArrayList<ImageData> list);
}
