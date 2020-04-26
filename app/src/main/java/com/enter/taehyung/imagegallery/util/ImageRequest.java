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
