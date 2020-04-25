package com.enter.taehyung.imagegallery.ui.list;

import com.enter.taehyung.imagegallery.data.ImageData;

import java.util.ArrayList;

public class ImagePresenter implements ImageContract.Presenter {
    private static final String TAG = ImagePresenter.class.getSimpleName();

    private ImageModel mModel;
    private ImageContract.View mView;

    public ImagePresenter(ImageContract.View view) {
        mView = view;
        mModel = new ImageModel(this);
    }

    @Override
    public void requestImageList() {
        mModel.requestData();
    }

    @Override
    public void setImageList(int stateCode, ArrayList<ImageData> imageList) {
        mView.initLayout(stateCode, imageList);
    }


}
