package com.enter.taehyung.imagegallery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.enter.taehyung.imagegallery.Intro.IntroManager;
import com.enter.taehyung.imagegallery.ui.list.ImageConst;
import com.enter.taehyung.imagegallery.ui.list.ImageFragment;
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
        Utils.setDensity(getApplicationContext());
        IntroManager.getInstance().init(this);

        mFragmentManager = getSupportFragmentManager();
        mFragment = new ImageFragment();

        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.replace(mFrameLayout.getId(), mFragment);
        transaction.commitAllowingStateLoss();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_looks_one:
                if (mFragment instanceof ImageFragment) {
                    ((ImageFragment) mFragment).changeViewType(ImageConst.LAYOUT_TYPE.DEFAULT);
                }
                return true;
            case R.id.action_looks_two:
                if (mFragment instanceof ImageFragment) {
                    ((ImageFragment) mFragment).changeViewType(ImageConst.LAYOUT_TYPE.STAGGERED);
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
