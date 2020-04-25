package com.enter.taehyung.imagegallery.ui.list;

import com.enter.taehyung.imagegallery.data.ImageData;

import java.util.ArrayList;

public class ImageContract {
    public interface View {
        // 레이아웃 초기화
        void initLayout(int stateCode, ArrayList<ImageData> imageList);

        // RecyclerView type change
        void changeViewType(@ImageConst.LAYOUT_TYPE int viewType);

        void switchSortType();

        // data request
        void requestData();
    }

    public interface Presenter {
        // request image data list
        void requestImageList();

        // set image data
        void setImageList(int stateCode, ArrayList<ImageData> imageList);
    }
}
