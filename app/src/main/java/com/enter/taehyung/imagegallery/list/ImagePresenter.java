package com.enter.taehyung.imagegallery.list;

import android.content.Context;

import com.enter.taehyung.imagegallery.network.NetworkListener;

public class ImagePresenter implements ImageContract.Presenter {
    private static final String TAG = ImagePresenter.class.getSimpleName();

    private Context mContext;
    private ImageModel mModel;
    private ImageContract.View mView;

    public ImagePresenter(ImageContract.View view) {
        mView = view;
        mModel = new ImageModel(this);

        mContext = mView.getAppContext();
    }

    @Override
    public void requestImageList(NetworkListener listener) {
        mModel.requestData(listener);
    }
}
