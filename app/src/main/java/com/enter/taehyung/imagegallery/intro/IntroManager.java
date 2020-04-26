package com.enter.taehyung.imagegallery.intro;

import android.util.Log;
import android.view.View;

import androidx.constraintlayout.widget.Group;

import com.enter.taehyung.imagegallery.MainActivity;
import com.enter.taehyung.imagegallery.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IntroManager {
    private static final String TAG = IntroManager.class.getSimpleName();

    @BindView(R.id.a_main_loading_group)
    Group mLoadingGroup;

    private IntroManager() {

    }

    private static class LazyHolder {
        public static final IntroManager INSTANCE = new IntroManager();
    }

    public static IntroManager getInstance() {
        return LazyHolder.INSTANCE;
    }

    public void init(MainActivity activity) {
        ButterKnife.bind(this, activity);
    }

    public void hideIntro() {
        Log.d(TAG, "hideIntro() called.");
        mLoadingGroup.setVisibility(View.GONE);
    }
}
