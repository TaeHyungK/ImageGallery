package com.enter.taehyung.imagegallery.list;

import androidx.annotation.IntDef;

import com.enter.taehyung.imagegallery.util.Utils;

public class ImageConst {
    public static final int ITEM_EDGE_PADDING_Y = Utils.changeDP2Pixel(5);
    public static final int ITEM_MIDDLE_MARGIN = Utils.changeDP2Pixel(10);

    @IntDef({SPAN_COUNT.BIG
            , SPAN_COUNT.HALF
    })
    public @interface SPAN_COUNT {
        int BIG = 1;
        int HALF = 2;
    }
}
