package com.enter.taehyung.imagegallery.network;

import androidx.annotation.IntDef;
import androidx.annotation.StringDef;

public class NetworkConst {
    public static final int TIMEOUT_MS = 15 * 1000;

    @IntDef({
            STATUS.OK,
            STATUS.FAILED,
            STATUS.NETWORK_EXCEPTION
    })
    public @interface STATUS {
        int OK = 200;
        int FAILED = 5000;
        int NETWORK_EXCEPTION = 5001;
    }

    @StringDef({
            BUNDLE_KEY.STATE_CODE,
            BUNDLE_KEY.DATA_IMAGE_LIST
    })
    public @interface BUNDLE_KEY {
        String STATE_CODE = "STATE_CODE";
        String DATA_IMAGE_LIST = "DATA_IMAGE_LIST";
    }

    @StringDef({
            PARSER_QUERY.DIV_PARENT,
            PARSER_QUERY.DIV_CHILD,
            PARSER_QUERY.IMG_TAG,
            PARSER_QUERY.IMG_ATTR_ALT,
            PARSER_QUERY.IMG_ATTR_SRC,
    })
    public @interface PARSER_QUERY {
        String DIV_PARENT = ".grid.masonry-grid.masonry-view";
        String DIV_CHILD = ".grid-item.image-item";
        String IMG_TAG = "img";
        String IMG_ATTR_ALT = "alt";
        String IMG_ATTR_SRC = "data-src";
        String IMAGE_TEXT_WRAPPER = "text-wrapper";
    }
}
