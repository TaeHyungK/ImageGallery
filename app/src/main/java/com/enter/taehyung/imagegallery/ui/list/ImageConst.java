package com.enter.taehyung.imagegallery.ui.list;

import androidx.annotation.IntDef;

import com.enter.taehyung.imagegallery.util.Utils;

public class ImageConst {
    public static final int ITEM_EDGE_PADDING_Y = Utils.changeDP2Pixel(2.5f);
    public static final int ITEM_MIDDLE_MARGIN = Utils.changeDP2Pixel(1f);

    @IntDef({SPAN_COUNT.ONE
            , SPAN_COUNT.TWO
            , SPAN_COUNT.THREE
    })
    public @interface SPAN_COUNT {
        int ONE = 1;
        int TWO = 2;
        int THREE = 3;
    }

    @IntDef({LAYOUT_TYPE.DEFAULT,
            LAYOUT_TYPE.STAGGERED
    })
    public @interface LAYOUT_TYPE {
        int DEFAULT = 100;
        int STAGGERED = 101;
    }

    @IntDef({SORT_TYPE.ORIGINAL,
            SORT_TYPE.ATOZ
    })
    public @interface SORT_TYPE {
        int ORIGINAL = 200;
        int ATOZ = 201;
    }
}
