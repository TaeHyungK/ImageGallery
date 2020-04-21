package com.enter.taehyung.imagegallery.list.recycler;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.enter.taehyung.imagegallery.R;
import com.enter.taehyung.imagegallery.base.MainBaseViewHolder;
import com.enter.taehyung.imagegallery.list.ImageContract;
import com.enter.taehyung.imagegallery.util.ImageLoader;
import com.enter.taehyung.imagegallery.util.ImageRequest;

import butterknife.BindView;

public class ImageViewHolder extends MainBaseViewHolder {
    private static final String TAG = ImageViewHolder.class.getSimpleName();

    private ImageContract.Presenter mPresenter;

    @BindView(R.id.v_list_holder_root)
    protected ConstraintLayout mRootLayout;
    @BindView(R.id.v_list_holder_image_view)
    protected ImageView mImageView;
    @BindView(R.id.v_list_holder_loading_view)
    protected View mLoadingView;

    public ImageViewHolder(@NonNull View itemView, ImageContract.Presenter presenter) {
        super(itemView);
        mPresenter = presenter;
    }

    @Override
    public void bind(int pos, Object obj) {
        itemView.setTag(R.attr.key_grid_pos, pos);

        if (obj instanceof String) {
            String url = (String) obj;
            Log.d(TAG, "bind() called." + pos + " | " + url);

            mLoadingView.setBackgroundResource(R.drawable.loading_icon);
            final ImageRequest req = new ImageRequest.Builder(mImageView, mLoadingView, url)
                    .setDefaultResourceId(R.drawable.default_no_image)
                    .build();
            ImageLoader.loadImage(req);
        }
    }
}
