package com.enter.taehyung.imagegallery.list;

import android.content.Context;

import java.util.ArrayList;

public class ImageContract {
    public interface View {
        // 레이아웃 초기화
        // TODO jsoup 이용 이미지 불러온 후 View에 호출
        void initLayout(ArrayList<String> imageList);

        Context getAppContext();
    }

    public interface Presenter {
        // TODO jsoup 이용 이미지 파싱 처리
        void getImageList(String url);
    }
}
