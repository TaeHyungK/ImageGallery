package com.enter.taehyung.imagegallery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.enter.taehyung.imagegallery.Intro.IntroManager;
import com.enter.taehyung.imagegallery.list.ImageFragment;
import com.enter.taehyung.imagegallery.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.a_main_frame)
    FrameLayout mFrameLayout;
    @BindView(R.id.a_main_loading_group)
    Group mLoadingGroup;

    private FragmentManager mFragmentManager;
    private Fragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Utils.setDensity(this);
        IntroManager.getInstance().init(this);

        mFragmentManager = getSupportFragmentManager();
        mFragment = new ImageFragment();

        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.replace(mFrameLayout.getId(), mFragment);
        transaction.commitAllowingStateLoss();
    }
}
