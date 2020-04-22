package com.enter.taehyung.imagegallery.list.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.enter.taehyung.imagegallery.R;
import com.enter.taehyung.imagegallery.base.MainBaseAdapter;
import com.enter.taehyung.imagegallery.base.MainBaseViewHolder;
import com.enter.taehyung.imagegallery.data.ImageData;
import com.enter.taehyung.imagegallery.list.ImageContract;
import com.enter.taehyung.imagegallery.util.Utils;

import java.util.ArrayList;

public class ImageAdapter extends MainBaseAdapter {
    private ImageContract.Presenter mPresenter;

    private View.OnClickListener mClickListener;
    private ArrayList<ImageData> mImageList;


    public ImageAdapter(ImageContract.Presenter presenter, ArrayList<ImageData> imageList) {
        this.mPresenter = presenter;
        this.mImageList = imageList;
    }

    @NonNull
    @Override
    public MainBaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.v_list_holder, parent, false);
        ImageViewHolder holder = new ImageViewHolder(view, mPresenter);
        holder.itemView.setOnClickListener(mClickListener);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainBaseViewHolder holder, int position) {
        Object object = mImageList.get(position);
        holder.bind(position, object);
    }

    @Override
    public int getItemCount() {
        return Utils.isListEmpty(mImageList) ? 0 : mImageList.size();
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
