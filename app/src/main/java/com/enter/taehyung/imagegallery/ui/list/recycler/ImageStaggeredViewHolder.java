package com.enter.taehyung.imagegallery.ui.list.recycler;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.enter.taehyung.imagegallery.R;
import com.enter.taehyung.imagegallery.base.MainBaseViewHolder;
import com.enter.taehyung.imagegallery.data.ImageData;
import com.enter.taehyung.imagegallery.ui.list.ImageContract;
import com.enter.taehyung.imagegallery.util.ImageLoader;
import com.enter.taehyung.imagegallery.util.ImageRequest;

import butterknife.BindView;

public class ImageStaggeredViewHolder extends MainBaseViewHolder {
    private static final String TAG = ImageStaggeredViewHolder.class.getSimpleName();

    private ImageContract.Presenter mPresenter;

    @BindView(R.id.i_list_staggered_root)
    protected CardView mRootLayout;
    @BindView(R.id.i_list_staggered_image_view)
    protected ImageView mImageView;

    public ImageStaggeredViewHolder(@NonNull View itemView, ImageContract.Presenter presenter) {
        super(itemView);
        mPresenter = presenter;
    }

    @Override
    public void bind(int pos, Object obj) {
        itemView.setTag(R.attr.key_grid_pos, pos);

        if (obj instanceof ImageData) {
            ImageData data = (ImageData) obj;
            Log.d(TAG, "bind() called." + pos + " | " + data.getTitle() + " | " + data.getImagePath());

            final ImageRequest req = new ImageRequest.Builder(mImageView, data.getImagePath())
                    .setErrorResId(R.drawable.default_no_image)
                    .build();
            ImageLoader.loadImage(req);

            itemView.setTag(R.attr.key_grid_idx, data.getIdx());
            itemView.setTag(R.attr.key_grid_title, data.getTitle());
        }
    }
}
