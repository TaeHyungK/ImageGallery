package com.enter.taehyung.imagegallery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.enter.taehyung.imagegallery.intro.IntroManager;
import com.enter.taehyung.imagegallery.ui.list.ImageConst;
import com.enter.taehyung.imagegallery.ui.list.ImageFragment;
import com.enter.taehyung.imagegallery.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.a_main_root)
    ConstraintLayout mRootLayout;
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
            case R.id.action_sort:
                if (mFragment instanceof ImageFragment) {
                    ((ImageFragment) mFragment).switchSortType();
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * 스플래시 화면 set OnClickListner
     */
    public void setClickListener() {
        mRootLayout.setOnClickListener(mClickListener);
    }

    /**
     * 스플래시 화면 remove OnClickListener;
     */
    public void removeClickListener() {
        mRootLayout.setOnClickListener(null);
    }

    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.a_main_root:
                    if (mFragment instanceof ImageFragment) {
                        ((ImageFragment) mFragment).requestData();
                        removeClickListener();
                    }
                    break;
            }
        }
    };
}
