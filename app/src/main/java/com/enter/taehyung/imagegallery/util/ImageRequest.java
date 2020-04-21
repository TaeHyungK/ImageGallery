package com.enter.taehyung.imagegallery.util;

import android.view.View;

import androidx.annotation.NonNull;

public class ImageRequest {
    public @NonNull
    View view;
    public @NonNull
    View loadingView;
    public @NonNull
    String url;
    public int defaultResId;

    public ImageRequest(Builder builder) {
        view = builder.view;
        loadingView = builder.loadingView;
        url = builder.url;
        defaultResId = builder.defaultResId;
    }

    public static class Builder {
        private final @NonNull
        View view;
        private final @NonNull
        View loadingView;
        private final @NonNull
        String url;
        private int defaultResId;

        public Builder(View view, View loadingView, String url) {
            this.view = view;
            this.loadingView = loadingView;
            this.url = url;
            this.defaultResId = 0;
        }

        public Builder setDefaultResourceId(int defaultResId) {
            this.defaultResId = defaultResId;
            return this;
        }

        public ImageRequest build() {
            return new ImageRequest(this);
        }
    }
}
