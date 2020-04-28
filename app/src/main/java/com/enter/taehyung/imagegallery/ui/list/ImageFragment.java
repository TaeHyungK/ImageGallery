package com.enter.taehyung.imagegallery.ui.list;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.enter.taehyung.imagegallery.intro.IntroManager;
import com.enter.taehyung.imagegallery.MainActivity;
import com.enter.taehyung.imagegallery.R;
import com.enter.taehyung.imagegallery.data.ImageData;
import com.enter.taehyung.imagegallery.network.NetworkConst;
import com.enter.taehyung.imagegallery.ui.list.recycler.ImageAdapter;
import com.enter.taehyung.imagegallery.ui.list.recycler.ImageItemDecoration;
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
                ((MainActivity) getActivity()).setClickListener();
            }
            return;
        }

        if (Utils.isListEmpty(imageList)) {
            Utils.showToast(getString(R.string.toast_image_empty, stateCode));
            return;
        }

        Log.d(TAG, "initLayout() called. size: " + imageList.size());
        IntroManager.getInstance().hideIntro();

        mGridLayoutManager = new GridLayoutManager(getActivity(), ImageConst.SPAN_COUNT.THREE, RecyclerView.VERTICAL, false);
        // FIXME 제이든 면접관님이 코드 리뷰 하는걸 놓친 것 같다고 이거 왜 두개로 쓰냐고 물어보심
        //  불규칙한 격자와 규칙적인 격자 둘다 노출하고 싶은 욕심이 생겨서 했다고 함.
        //  설명하면서 불규칙한 격자 모드에서 아래로 스크롤하면 빈 부분이 생기는 이슈가 있는데 이미지 크기가 measure 되는 타이밍이 엇갈리면서 나는 이슈로 보인다고 설명드리며
        //  추후 수정할 예정이라고 말씀드림.
        //  -> 그냥 넘어감.
        mStaggeredLayoutManager= new StaggeredGridLayoutManager(ImageConst.SPAN_COUNT.THREE, RecyclerView.VERTICAL);
        mStaggeredLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);

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
            int idx = (int) view.getTag(R.attr.key_grid_idx);
            String title = (String) view.getTag(R.attr.key_grid_title);

            Utils.showToast("[" + idx + "]" + title);
        }
    };
}
