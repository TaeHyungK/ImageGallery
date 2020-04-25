package com.enter.taehyung.imagegallery.util;

import com.enter.taehyung.imagegallery.data.ImageData;

import java.util.Comparator;

public class TitleComparator implements Comparator<ImageData> {
    @Override
    public int compare(ImageData o1, ImageData o2) {
        return o1.getTitle().compareTo(o2.getTitle());
    }
}
