package com.enter.taehyung.imagegallery.ui.list;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.enter.taehyung.imagegallery.data.ImageData;
import com.enter.taehyung.imagegallery.network.NetworkConst;
import com.enter.taehyung.imagegallery.network.NetworkListener;
import com.enter.taehyung.imagegallery.network.NetworkManager;

import java.util.ArrayList;

public class ImageModel {
    private static final String TAG = ImageModel.class.getSimpleName();

    private ImageContract.Presenter mPresenter;

    public ImageModel(ImageContract.Presenter listPresenter) {
        mPresenter = listPresenter;
    }

    /**
     * 이미지 데이터 크롤링 요청
     */
    public void requestData() {
//        NetworkManager.requestData(mNetworkListener);
        NetworkManager.requestAsyncData(mNetworkListener);

    }

    private NetworkListener mNetworkListener = new NetworkListener() {
        @Override
        public void onResult(int state, ArrayList<ImageData> list) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    mPresenter.setImageList(state, list);
                }
            });
        }

        @Override
        public void onResult(Bundle bundle) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    int stateCode = bundle.getInt(NetworkConst.BUNDLE_KEY.STATE_CODE, -1);
                    ArrayList<ImageData> imageList = bundle.getParcelableArrayList(NetworkConst.BUNDLE_KEY.DATA_IMAGE_LIST);

                    mPresenter.setImageList(stateCode, imageList);
                }
            });
        }
    };
}
