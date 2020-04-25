package com.enter.taehyung.imagegallery.data;

import android.os.Parcel;
import android.os.Parcelable;

public class ImageData implements Parcelable {
    private int idx; // origin sort 를 위해 저장 처리 필요.
    private String title;
    private String imagePath;

    public ImageData() {
    }

    public ImageData(Parcel in) {
        this.idx = in.readInt();
        this.title = in.readString();
        this.imagePath = in.readString();
    }

    public int getIdx() {
        return idx;
    }

    public String getTitle() {
        return title;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.idx);
        dest.writeString(this.title);
        dest.writeString(this.imagePath);
    }

    public static final Parcelable.Creator<ImageData> CREATOR = new Parcelable.Creator<ImageData>() {
        @Override
        public ImageData createFromParcel(Parcel source) {
            return new ImageData(source);
        }

        @Override
        public ImageData[] newArray(int size) {
            return new ImageData[size];
        }
    };

}
