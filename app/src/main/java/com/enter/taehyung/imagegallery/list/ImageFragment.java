package com.enter.taehyung.imagegallery.list;

import android.content.Context;
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

import com.enter.taehyung.imagegallery.R;
import com.enter.taehyung.imagegallery.base.MainBaseViewHolder;
import com.enter.taehyung.imagegallery.list.recycler.ImageAdapter;
import com.enter.taehyung.imagegallery.list.recycler.ImageItemDecoration;
import com.enter.taehyung.imagegallery.list.recycler.ImageViewHolder;

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
    }

    @Override
    public void initLayout(ArrayList<String> imageList) {
        // TODO 이미지 url 들로 recyclerView 셋팅
        Log.d(TAG, "initLayout() called. size: " + imageList.size());

        GridLayoutManager layoutManager = new GridLayoutManager(getAppContext(), ImageConst.SPAN_COUNT.BIG, RecyclerView.VERTICAL, false);
        mAdapter = new ImageAdapter(mPresenter, imageList);
        mAdapter.setListener(mClickListener);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);

        ImageItemDecoration decor = new ImageItemDecoration();
        mRecyclerView.addItemDecoration(decor);
    }

    @Override
    public Context getAppContext() {
        return getActivity().getApplicationContext();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }



    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int pos = (int) view.getTag(R.attr.key_grid_pos);

            MainBaseViewHolder baseHolder = (MainBaseViewHolder) mRecyclerView.findViewHolderForAdapterPosition(pos);
            if (baseHolder instanceof ImageViewHolder) {
                ImageViewHolder holder = (ImageViewHolder) baseHolder;

                Toast.makeText(getContext(), pos + "번째 아이템 클릭", Toast.LENGTH_SHORT).show();
            }
        }
    };
}
