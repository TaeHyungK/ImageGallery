package com.enter.taehyung.imagegallery.list;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import java.util.ArrayList;

public class ImagePresenter implements ImageContract.Presenter {
    private static final String TAG = ImagePresenter.class.getSimpleName();

    private Context mContext;
    private ImageModel mModel;
    private ImageContract.View mView;

    public ImagePresenter(ImageContract.View view) {
        mView = view;
        mModel = new ImageModel(this);

        mContext = mView.getAppContext();

        getImageList("http://www.gettyimagesgallery.com/collection/sasha/");
    }

    @Override
    public void getImageList(String url) {
        if (TextUtils.isEmpty(url)) {
            Toast.makeText(mContext, "Url is empty!", Toast.LENGTH_SHORT).show();
            return;
        }

        ArrayList<String> imageList = new ArrayList<>();

        // TODO jsoup 이용 url 파싱 작업후 imageList에 http 이미지 add.

        // FIXME 테스트용 하드코딩
        imageList.add("https://www.gettyimagesgallery.com/wp-content/uploads/2018/12/GettyImages-3360485-1024x802.jpg");
        imageList.add("https://www.gettyimagesgallery.com/wp-content/uploads/2018/12/GettyImages-3347677-1024x732.jpg");
        imageList.add("https://www.gettyimagesgallery.com/wp-content/uploads/2018/12/GettyImages-2664892.jpg");
        imageList.add("https://www.gettyimagesgallery.com/wp-content/uploads/2018/12/GettyImages-3318099-769x1024.jpg");
        imageList.add("https://www.gettyimagesgallery.com/wp-content/uploads/2018/12/GettyImages-3093482.jpg");
        imageList.add("https://www.gettyimagesgallery.com/wp-content/uploads/2018/12/GettyImages-3427847-1024x775.jpg");
        imageList.add("https://www.gettyimagesgallery.com/wp-content/uploads/2018/12/GettyImages-3067733.jpg");
        imageList.add("https://www.gettyimagesgallery.com/wp-content/uploads/2018/12/GettyImages-3259681-763x1024.jpg");
        imageList.add("https://www.gettyimagesgallery.com/wp-content/uploads/2018/12/GettyImages-3359933-795x1024.jpg");
        imageList.add("https://www.gettyimagesgallery.com/wp-content/uploads/2018/12/GettyImages-2634004.jpg");


        mView.initLayout(imageList);
    }

}
