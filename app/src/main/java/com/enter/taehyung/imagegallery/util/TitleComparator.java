package com.enter.taehyung.imagegallery.util;

import com.enter.taehyung.imagegallery.data.ImageData;

import java.util.Comparator;

/**
 * 정렬 설정 - Title
 * 데이터 타이틀순
 */
public class TitleComparator implements Comparator<ImageData> {
    @Override
    public int compare(ImageData o1, ImageData o2) {
        // FIXME OriginalComparator와 동일
        return o1.getTitle().compareTo(o2.getTitle());
    }
}
