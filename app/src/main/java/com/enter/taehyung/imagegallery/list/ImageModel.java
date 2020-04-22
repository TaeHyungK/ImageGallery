package com.enter.taehyung.imagegallery.list;

import com.enter.taehyung.imagegallery.network.NetworkListener;
import com.enter.taehyung.imagegallery.network.NetworkManager;

public class ImageModel {
    private static final String TAG = ImageModel.class.getSimpleName();

    private ImageContract.Presenter mPresenter;

    public ImageModel(ImageContract.Presenter listPresenter) {
        mPresenter = listPresenter;
    }

    public void requestData(NetworkListener listener) {
        NetworkManager networkManager = NetworkManager.getInstance();
        networkManager.requestData(listener);
    }
}
