package com.enter.taehyung.imagegallery.util;

import android.animation.ValueAnimator;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

/**
 * Glide 이미지 요청을 위한 Loader
 */
public class ImageLoader {
    private static final String TAG = ImageLoader.class.getSimpleName();

    /**
     * 이미지 요청
     *
     * @param info ImageRequest
     */
    public static void loadImage(@NonNull final ImageRequest info) {
        if(TextUtils.isEmpty(info.url)){
            Log.d(TAG, "loadImage() called. url is empty");
            return;
        }

        RequestBuilder request = Glide.with(info.view.getContext()).load(info.url);
        RequestOptions options = new RequestOptions();

        if (info.errorResId != 0) {
            options.error(info.errorResId);
        }

        Log.d(TAG, "loadImage() called. url: " + info.url);

        info.loadingView.setVisibility(View.VISIBLE);
        ValueAnimator loadingAnimator = AnimatorUtil.getInstance().makeRotationAnimator(info.loadingView, 500, 0, 360);
        loadingAnimator.setRepeatCount(ValueAnimator.INFINITE);
        loadingAnimator.setRepeatMode(ValueAnimator.RESTART);
        loadingAnimator.start();

        request.listener(new RequestListener() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target target
                    , boolean isFirstResource) {
                info.loadingView.setVisibility(View.GONE);
                loadingAnimator.cancel();

                return false;
            }

            @Override
            public boolean onResourceReady(Object resource, Object model, Target target
                    , DataSource dataSource, boolean isFirstResource) {

                info.loadingView.setVisibility(View.GONE);
                loadingAnimator.cancel();

                return false;
            }
        });

        options.transform(new FitCenter(), new RoundedCorners(Utils.changeDP2Pixel(5)));
        request.apply(options);
        request.into((ImageView) info.view);
    }
}
