package com.enter.taehyung.imagegallery.network;

import androidx.annotation.IntDef;
import androidx.annotation.StringDef;

public class NetworkConst {
    public static final int TIMEOUT_MS = 10 * 1000;

    @IntDef({
            STATUS.OK
    })
    public @interface STATUS {
        int OK = 200;
    }

    @StringDef({
            BUNDLE_KEY.STATE_CODE,
            BUNDLE_KEY.DATA_IMAGE_LIST
    })
    public @interface BUNDLE_KEY {
        String STATE_CODE = "STATE_CODE";
        String DATA_IMAGE_LIST = "DATA_IMAGE_LIST";
    }

    @IntDef({
            BUNDLE_TYPE.NETWORK_EXCEPTION
    })
    public @interface BUNDLE_TYPE {
        int NETWORK_EXCEPTION = 5000;
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
    }
}
