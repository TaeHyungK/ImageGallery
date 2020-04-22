package com.enter.taehyung.imagegallery.list;

import android.content.Context;

import com.enter.taehyung.imagegallery.data.ImageData;
import com.enter.taehyung.imagegallery.network.NetworkListener;

import java.util.ArrayList;

public class ImageContract {
    public interface View {
        // 레이아웃 초기화
        void initLayout(ArrayList<ImageData> imageList);

        // get ApplicationContext
        Context getAppContext();
    }

    public interface Presenter {
        // request image data list
        void requestImageList(NetworkListener listener);
    }
}
