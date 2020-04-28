package com.enter.taehyung.imagegallery.util;

import android.view.View;

import androidx.annotation.NonNull;

/**
 * Glide 이미지 요청 시 필요 데이터 class
 */
public class ImageRequest {
    public @NonNull
    View view;
    public @NonNull
    String url;
    public int errorResId;

    private ImageRequest(Builder builder) {
        view = builder.view;
        url = builder.url;
        errorResId = builder.errorResId;
    }

    // FIXME 질문은 없었고 빌더 패턴 이용해서 좀 더 유연하게 처리하기 위해 사용했다고 설명드림.
    public static class Builder {
        private final @NonNull
        View view;
        private final @NonNull
        String url;
        private int errorResId;

        public Builder(View view, String url) {
            this.view = view;
            this.url = url;
            this.errorResId = 0;
        }

        public Builder setErrorResId(int errorResId) {
            this.errorResId = errorResId;
            return this;
        }

        public ImageRequest build() {
            return new ImageRequest(this);
        }
    }
}
