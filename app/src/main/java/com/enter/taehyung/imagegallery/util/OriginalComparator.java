package com.enter.taehyung.imagegallery.util;

import com.enter.taehyung.imagegallery.data.ImageData;

import java.util.Comparator;

public class OriginalComparator implements Comparator<ImageData> {
    @Override
    public int compare(ImageData o1, ImageData o2) {
        return ((Integer) o1.getIdx()).compareTo((Integer) o2.getIdx());
    }
}
