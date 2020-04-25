package com.enter.taehyung.imagegallery.ui.list.recycler;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.enter.taehyung.imagegallery.ui.list.ImageConst;

public class ImageItemDecoration extends RecyclerView.ItemDecoration {
    private int mSpace = ImageConst.ITEM_MIDDLE_MARGIN;
    private int mEdgePadding = ImageConst.ITEM_EDGE_PADDING_Y;

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        final int itemCount = state.getItemCount();
        final int itemPosition = parent.getChildAdapterPosition(view);
        if (itemPosition == 0) {
            outRect.top = mEdgePadding;
            outRect.bottom = mSpace;
        } else if (itemPosition == itemCount - 1) {
            outRect.top = mSpace;
            outRect.bottom = mEdgePadding;
        } else {
            outRect.top = mSpace;
            outRect.bottom = mSpace;
        }
    }
}