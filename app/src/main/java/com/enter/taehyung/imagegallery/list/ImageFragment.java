package com.enter.taehyung.imagegallery.list;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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
import com.enter.taehyung.imagegallery.data.ImageData;
import com.enter.taehyung.imagegallery.list.recycler.ImageAdapter;
import com.enter.taehyung.imagegallery.list.recycler.ImageItemDecoration;
import com.enter.taehyung.imagegallery.list.recycler.ImageViewHolder;
import com.enter.taehyung.imagegallery.network.NetworkConst;
import com.enter.taehyung.imagegallery.network.NetworkListener;
import com.enter.taehyung.imagegallery.util.Utils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageFragment extends Fragment implements ImageContract.View{
    private static final String TAG = ImageFragment.class.getSimpleName();

    private Context mContext;
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

        mContext = getAppContext();
        mView = this;
        mPresenter = new ImagePresenter(this);

        mPresenter.requestImageList(mNetworkListener);
    }

    private NetworkListener mNetworkListener = new NetworkListener() {
        @Override
        public void onResult(Bundle bundle) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    int stateCode = bundle.getInt(NetworkConst.BUNDLE_KEY.STATE_CODE, -1);
                    if (stateCode != NetworkConst.STATUS.OK) {
                        Toast.makeText(getAppContext(), "[" + stateCode + "] network failed..", Toast.LENGTH_SHORT).show();
                    }

                    ArrayList<ImageData> imageList = bundle.getParcelableArrayList(NetworkConst.BUNDLE_KEY.DATA_IMAGE_LIST);

                    if (Utils.isListEmpty(imageList)) {
                        Toast.makeText(getAppContext(), "[" + stateCode + "] 이미지가 없습니다.", Toast.LENGTH_SHORT).show();
                    }

                    initLayout(imageList);
                }
            });
        }
    };

    @Override
    public void initLayout(ArrayList<ImageData> imageList) {
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
            String title = (String) view.getTag(R.attr.key_grid_title);

            MainBaseViewHolder baseHolder = (MainBaseViewHolder) mRecyclerView.findViewHolderForAdapterPosition(pos);
            if (baseHolder instanceof ImageViewHolder) {
                ImageViewHolder holder = (ImageViewHolder) baseHolder;

                Toast.makeText(getContext(), "[" + pos + "] " + title + " 클릭", Toast.LENGTH_SHORT).show();
            }
        }
    };
}
