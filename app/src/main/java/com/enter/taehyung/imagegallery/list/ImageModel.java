package com.enter.taehyung.imagegallery.list;

public class ImageModel {
    private static final String TAG = ImageModel.class.getSimpleName();

    private ImageContract.Presenter mPresenter;

    public ImageModel(ImageContract.Presenter listPresenter) {
        mPresenter = listPresenter;
    }


}
