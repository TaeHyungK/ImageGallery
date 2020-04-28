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

        // FIXME thumbnail을 사용했는데 글라이드에서 로드할 때의 이미지를 fetch 하는것과 썸네일을 fetch하는 것 이렇게 2개가 생기는 것인지? 아니면 하나만 생기는 것인지 물어봄
        //  thumbnail이 별도의 url을 가지고 이미지를 별도로 받아오는 것인지는 솔직하게 모르겠다고 함
        //  -> 본인도 이부분은 잘 모르겠다고 넘어감.
        RequestBuilder request = Glide.with(info.view.getContext()).load(info.url).thumbnail(0.2f);
        RequestOptions options = new RequestOptions();

        if (info.errorResId != 0) {
            options.error(info.errorResId);
        }

        Log.d(TAG, "loadImage() called. url: " + info.url);

        request.listener(new RequestListener() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target target
                    , boolean isFirstResource) {
                Log.d(TAG, "onLoadFailed() called.");
                return false;
            }

            @Override
            public boolean onResourceReady(Object resource, Object model, Target target
                    , DataSource dataSource, boolean isFirstResource) {
                Log.d(TAG, "onResourceReady() called.");
                return false;
            }
        });

        // FIXME 왜 라운딩을 했는지 + FitCenter() 쓰면서 이미지가 찌그러지는 현상있는데 알고 있는지, 어떻게 수정이 가능할 것 같은지 물어봄
        //  라운딩은 UI상 더 이쁘게 표현하기 위해 사용했다고 말씀드림.
        //  그러면서 원래는 벨류애니메이터로 로딩뷰 애니메이션 처리를 했었는데 너무 정신사나워서 썸네일로 바꿨다고도 설명드림.
        //  FitCenter가 가운데로 맞추는거라서 FitXY (?) 같은 옵션 다른걸로 주면 수정이 가능할 것 같다고 함
        //  -> 옵션 여러가지 있으니 그런 거 쓰면 수정될 것 같긴 하다고 하심.
        options.transform(new FitCenter(), new RoundedCorners(Utils.changeDP2Pixel(5)));
        request.apply(options);
        request.into((ImageView) info.view);
    }
}
