package com.enter.taehyung.imagegallery.ui.list.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.enter.taehyung.imagegallery.R;
import com.enter.taehyung.imagegallery.base.MainBaseAdapter;
import com.enter.taehyung.imagegallery.base.MainBaseViewHolder;
import com.enter.taehyung.imagegallery.data.ImageData;
import com.enter.taehyung.imagegallery.ui.list.ImageConst;
import com.enter.taehyung.imagegallery.ui.list.ImageContract;
import com.enter.taehyung.imagegallery.util.Utils;

import java.util.ArrayList;

public class ImageAdapter extends MainBaseAdapter {
    private static final String TAG = ImageAdapter.class.getSimpleName();

    private ImageContract.Presenter mPresenter;

    private View.OnClickListener mClickListener;
    private ArrayList<ImageData> mImageList;
    private @ImageConst.LAYOUT_TYPE int mViewType;


    public ImageAdapter(ImageContract.Presenter presenter, ArrayList<ImageData> imageList, @ImageConst.LAYOUT_TYPE int viewType) {
        this.mPresenter = presenter;
        this.mImageList = imageList;
        this.mViewType = viewType;
    }

    @NonNull
    @Override
    public MainBaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        if (ImageConst.LAYOUT_TYPE.STAGGERED == viewType) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_list_staggered, parent, false);
            ImageStaggeredViewHolder holder = new ImageStaggeredViewHolder(view, mPresenter);
            holder.itemView.setOnClickListener(mClickListener);

            return holder;
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_list_default, parent, false);
            ImageDefaultViewHolder holder = new ImageDefaultViewHolder(view, mPresenter);
            holder.itemView.setOnClickListener(mClickListener);

            return holder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MainBaseViewHolder holder, int position) {
        Object object = mImageList.get(position);
        holder.bind(position, object);
    }

    @Override
    public int getItemViewType(int position) {
        return mViewType;
    }

    @Override
    public int getItemCount() {
        return Utils.isListEmpty(mImageList) ? 0 : mImageList.size();
    }

    public int getViewType() {
        return mViewType;
    }

    public void setViewType(@ImageConst.LAYOUT_TYPE int viewType) {
        this.mViewType = viewType;
    }

    /**
     * set OnClickListener
     *
     * @param clickListener View.OnClickListener
     */
    @Override
    public void setListener(View.OnClickListener clickListener) {
        mClickListener = clickListener;
    }
}
