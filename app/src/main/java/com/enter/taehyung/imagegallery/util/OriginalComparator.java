package com.enter.taehyung.imagegallery.util;

import com.enter.taehyung.imagegallery.data.ImageData;

import java.util.Comparator;

/**
 * 정렬 설정 - ORIGINAL
 * 받아온 데이터순
 */
public class OriginalComparator implements Comparator<ImageData> {
    @Override
    public int compare(ImageData o1, ImageData o2) {
        return ((Integer) o1.getIdx()).compareTo((Integer) o2.getIdx());
    }
}
