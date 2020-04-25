package com.enter.taehyung.imagegallery.ui.list;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.enter.taehyung.imagegallery.Intro.IntroManager;
import com.enter.taehyung.imagegallery.MainActivity;
import com.enter.taehyung.imagegallery.R;
import com.enter.taehyung.imagegallery.base.MainBaseViewHolder;
import com.enter.taehyung.imagegallery.data.ImageData;
import com.enter.taehyung.imagegallery.network.NetworkConst;
import com.enter.taehyung.imagegallery.ui.list.recycler.ImageAdapter;
import com.enter.taehyung.imagegallery.ui.list.recycler.ImageItemDecoration;
import com.enter.taehyung.imagegallery.ui.list.recycler.ImageDefaultViewHolder;
import com.enter.taehyung.imagegallery.util.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageFragment extends Fragment implements ImageContract.View{
    private static final String TAG = ImageFragment.class.getSimpleName();

    private ImageContract.Presenter mPresenter;
    private ImageContract.View mView;

    @BindView(R.id.f_list_recycler)
    public RecyclerView mRecyclerView;

    private ImageAdapter mAdapter;
    private GridLayoutManager mGridLayoutManager;
    private StaggeredGridLayoutManager mStaggeredLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.f_list, container, false);
        ButterKnife.bind(this, viewGroup);

        return viewGroup;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mView = this;
        mPresenter = new ImagePresenter(this);

        requestData();
    }

    @Override
    public void initLayout(int stateCode, ArrayList<ImageData> imageList) {
        if (stateCode != NetworkConst.STATUS.OK) {
            Utils.showToast(getString(R.string.toast_network_failed, stateCode));

            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).addClickListener();
            }
            return;
        }

        if (Utils.isListEmpty(imageList)) {
            Utils.showToast(getString(R.string.toast_image_empty, stateCode));
            return;
        }

        Log.d(TAG, "initLayout() called. size: " + imageList.size());
        IntroManager.getInstance().hideIntro();

        mGridLayoutManager = new GridLayoutManager(getActivity(), ImageConst.SPAN_COUNT.THIRD, RecyclerView.VERTICAL, false);
        mStaggeredLayoutManager= new StaggeredGridLayoutManager(ImageConst.SPAN_COUNT.THIRD, RecyclerView.VERTICAL);

        mAdapter = new ImageAdapter(mPresenter, imageList, ImageConst.LAYOUT_TYPE.DEFAULT);
        mAdapter.setListener(mClickListener);

        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        ImageItemDecoration decor = new ImageItemDecoration();
        mRecyclerView.addItemDecoration(decor);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void changeViewType(@ImageConst.LAYOUT_TYPE int viewType) {
        if (mAdapter == null || mRecyclerView == null) {
            Log.d(TAG, "changeViewType() mAdapter or mRecyclerView is null. do nothing.");
            return;
        }

        if (mAdapter.getViewType() == viewType) {
            Log.d(TAG, "changeViewType() ViewType is same type. do nothing. ");
            return;
        }

        mAdapter.setViewType(viewType);
        mRecyclerView.setLayoutManager(viewType == ImageConst.LAYOUT_TYPE.DEFAULT ? mGridLayoutManager : mStaggeredLayoutManager);

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void switchSortType() {
        if (mAdapter == null || mRecyclerView == null) {
            Log.d(TAG, "changeSortType() mAdapter or mRecyclerView is null. do nothing.");
            return;
        }
        mAdapter.switchSortType();
    }

    @Override
    public void requestData() {
        if (mPresenter != null) {
            mPresenter.requestImageList();
        }
    }

    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int pos = (int) view.getTag(R.attr.key_grid_pos);
            String title = (String) view.getTag(R.attr.key_grid_title);

            MainBaseViewHolder baseHolder = (MainBaseViewHolder) mRecyclerView.findViewHolderForAdapterPosition(pos);
            if (baseHolder instanceof ImageDefaultViewHolder) {
                ImageDefaultViewHolder holder = (ImageDefaultViewHolder) baseHolder;

                Toast.makeText(getContext(), "[" + pos + "] " + title + " 클릭", Toast.LENGTH_SHORT).show();
            }
        }
    };
}
